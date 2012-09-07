package de.fu.scetris.scheduler.manager;

import static de.fu.weave.orm.filters.Filters.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.CourseRecommendedForYear;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.Algorithm;
import de.fu.scetris.scheduler.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleChecker;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Filter;

/**
 * 
 * Starts the scheduling on behalf of the current database state.
 * 
 */
@Author("Konrad Reiche")
public class SchedulerManager {

	private static SchedulerManager instance;

	public synchronized static SchedulerManager getInstance(
			final RelationManager relationManager)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {

		if (instance == null) {
			instance = new SchedulerManager(relationManager.clone());
		}

		return instance;
	}

	private final RelationManager relationManager;
	private Algorithm algorithm;
	private Thread schedulerThread;

	private SchedulerTask schedulerTask;

	private SchedulerManager(final RelationManager relationManager) {
		this.relationManager = relationManager;
		schedulerThread = new Thread(new SchedulerTask(relationManager,
				algorithm, null, false));
	}

	public synchronized void freezeProgram(final Program program)
			throws DatabaseException {
		setScheduleableLessons(program, false);
		program.setFreezed(true);
		program.pushChanges();

	}

	private synchronized List<CourseElementInstance> getCourseElementInstanceListByProgram(
			final Program program) throws DatabaseException {

		List<CourseInstance> courseInstanceList = relationManager
				.getCourseInstance(eq("program", program.getId()));

		Filter filters[] = new Filter[courseInstanceList.size()];
		for (int i = 0; i < filters.length; ++i)
			filters[i] = eq("course_instance", courseInstanceList.get(i)
					.getId());

		return relationManager.getCourseElementInstance(any(filters));

	}

	public synchronized Program getCurrentProgramBeScheduling() {
		return schedulerTask.getCurrentProgramBeingScheduled();
	}

	public synchronized ScheduleScore getCurrentSchedulingScore()
			throws Exception {

		if (schedulerTask == null)
			return null;
		else
			return schedulerTask.getAlgorithm().getBestSchedule().getScore();
	}

	public synchronized ScheduleScore getScheduleScore(final Program program)
			throws Exception {

		Configuration configuration = initializeConfiguration(program);
		ScheduleChecker scheduleChecker = new ScheduleChecker();
		return scheduleChecker.check(configuration);
	}

	public synchronized ScheduleScore getScheduleScoreWithChange(
			final Program program, final ProposedScheduling proposedScheduling)
			throws Exception {

		Configuration configuration = initializeConfiguration(program);
		configuration.proposedSchedulingList.remove(proposedScheduling);
		configuration.proposedSchedulingList.add(proposedScheduling);
		configuration.courseToProposal.put(
				proposedScheduling.getElementInstance(), proposedScheduling);

		ScheduleChecker scheduleChecker = new ScheduleChecker();
		return scheduleChecker.check(configuration);
	}

	/**
	 * Initializes {@link Configuration} with respect to the given {@link AcademicTerm} and to the
	 * given {@link Department}. When a {@link Room} has no {@link Building} or the {@link Building}
	 * has no {@link Department} it not added.
	 * 
	 * @param academicTerm
	 *            the {@link AcademicTerm} to be scheduled.
	 * @param department
	 *            the {@link Department} to be scheduled.
	 * @return
	 * @return the initialized {@link Configuration}
	 * @throws DatabaseException
	 */
	public synchronized Configuration initializeConfiguration(final Program program)
			throws DatabaseException {

		List<Room> roomList = relationManager.getRoom();

		for (Iterator<Room> it = roomList.iterator(); it.hasNext();) {
			Room room = it.next();
			if ((room.getBuilding().getDepartment() == null)
					|| !room.getBuilding().getDepartment()
							.equals(program.getDepartment())) it.remove();
		}

		Set<Person> lecturerSet = new TreeSet<Person>();

		List<CourseElementInstance> courseElementInstanceList = relationManager
				.getCourseElementInstance();

		for (Iterator<CourseElementInstance> it = courseElementInstanceList
				.iterator(); it.hasNext();) {
			CourseElementInstance courseElementInstance = it.next();

			if (!courseElementInstance.getCourseInstance().getProgram()
					.equals(program)) it.remove();
		}

		List<ProposedScheduling> proposedSchedulingList = relationManager
				.getProposedScheduling();

		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {

			ProposedScheduling proposedSchedulingOfCourse = null;
			for (ProposedScheduling proposedScheduling : proposedSchedulingList)
				if (proposedScheduling.getElementInstance().equals(
						courseElementInstance)) proposedSchedulingOfCourse = proposedScheduling;

			if (proposedSchedulingOfCourse == null) {
				ProposedScheduling newProposedScheduling = relationManager
						.createProposedScheduling(100, courseElementInstance);
				proposedSchedulingList.add(newProposedScheduling);
			}

			Person lecturer = courseElementInstance.getLecturer();
			if (lecturer != null) {
				lecturerSet.add(lecturer);				
			}
			
		}

		List<Timeslot> timeSlotList = relationManager.getTimeslot();
		List<Feature> featureList = relationManager.getFeature();

		List<CourseRecommendedForYear> courseRecommendForYearList = relationManager
				.getCourseRecommendedForYear();
		
		return new Configuration(roomList, new ArrayList<Person>(lecturerSet),
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList, courseRecommendForYearList);
	}

	public synchronized boolean isReady() {
		return !isStopping() && !isRunning();
	}

	public synchronized boolean isRunning() {
		return schedulerThread.isAlive();
	}

	/**
	 * Checks the given resources if they can match the expected constraints.
	 * 
	 * @return is it possible to schedule a constraint-free schedule.
	 */
	private boolean isScheduable(final Configuration configuration) {

		int durationSum = 0;
		for (CourseElementInstance courseElementInstance : configuration.courseList)
			durationSum += courseElementInstance.getDuration();

		int timeSpaceSum = configuration.timeSlotList.size()
				* configuration.roomList.size();

		if (configuration.timeSlotList.isEmpty() || (durationSum > timeSpaceSum)
				|| configuration.courseList.isEmpty()
				|| configuration.lecturerList.isEmpty()
				|| configuration.roomList.isEmpty()) return false;

		return true;
	}

	public synchronized boolean isStopping() {
		return schedulerThread.isInterrupted();
	}

	public synchronized void publishProgram(final Program program)
			throws DatabaseException {

		if (program.getFreezed()) {

			List<CourseInstance> courseInstanceList = relationManager
					.getCourseInstance(eq("program", program.getId()));

			Filter filters[] = new Filter[courseInstanceList.size()];
			for (int i = 0; i < filters.length; ++i)
				filters[i] = eq("course_instance", courseInstanceList.get(i)
						.getId());

			List<CourseElementInstance> courseElementInstanceList = relationManager
					.getCourseElementInstance(any(filters));

			for (CourseElementInstance courseElementInstance : courseElementInstanceList) {

				ProposedScheduling proposedScheduling = relationManager
						.getProposedScheduling(
								eq("element_instance",
										courseElementInstance.getId())).get(0);

				Timeslot timeSlot = proposedScheduling.getTimeslot();
				courseElementInstance.setStartingTimeslot(timeSlot);
				courseElementInstance.pushChanges();

				relationManager.createElementInstanceTakesPlaceInRoom(
						courseElementInstance, proposedScheduling.getRoom(),
						timeSlot);
			}

			program.setPublished(true);
			program.pushChanges();
		}
	}

	private void setScheduleableLessons(final Program program, final boolean freeze)
			throws DatabaseException {
		List<CourseInstance> courseInstanceList = relationManager
				.getCourseInstance(eq("program", program.getId()));

		Filter filters[] = new Filter[courseInstanceList.size()];
		for (int i = 0; i < filters.length; ++i)
			filters[i] = eq("course_instance", courseInstanceList.get(i)
					.getId());

		List<CourseElementInstance> courseElementInstanceList = relationManager
				.getCourseElementInstance(any(filters));

		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {
			courseElementInstance.setSchedulableLesson(freeze);
			courseElementInstance.pushChanges();
		}
	}

	/**
	 * Starts the Scheduler concurrently.
	 * <p>
	 * The {@link Configuration} is initialized with respect to the given {@link AcademicTerm} and
	 * to the given {@link Department}.
	 * <p>
	 * If the given {@link Configuration} is scheduleable, a separate Thread executing the Scheduler
	 * is started, otherwise an error arises.
	 * 
	 * @param academicTerm
	 *            the {@link AcademicTerm} to be scheduled.
	 * @param department
	 *            the {@link Department} to be scheduled.
	 * @throws Exception
	 */
	public synchronized void startScheduler(final Program program, final boolean resume)
			throws Exception {

		if (isReady()) {

			Configuration configuration = initializeConfiguration(program);
			AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
					0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);
			algorithm = GeneticFactory.getInstance().createAlgorithm(
					configuration, algorithmConfiguration);

			if (isScheduable(configuration)) {
				schedulerTask = new SchedulerTask(relationManager, algorithm, program, resume);
				schedulerThread = new Thread(schedulerTask);
				schedulerThread.start();
			} else
				throw new NotScheduleableException();
		} else
			System.err.println("Cannot start the scheduler, is not ready yet.");
	}

	public synchronized void stopScheduler() {
		schedulerThread.interrupt();
	}

	public synchronized void unfreezeProgram(final Program program)
			throws DatabaseException {
		setScheduleableLessons(program, true);
		program.setFreezed(false);
		program.pushChanges();
	}

	public synchronized void unpublishProgram(final Program program)
			throws DatabaseException {

		List<CourseElementInstance> courseElementInstanceList = getCourseElementInstanceListByProgram(program);

		Filter filters[] = new Filter[courseElementInstanceList.size()];
		for (int i = 0; i < filters.length; ++i)
			filters[i] = eq("element_instance", courseElementInstanceList
					.get(i).getId());

		relationManager.deleteElementInstanceTakesPlaceInRoom(any(filters));
		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {
			courseElementInstance.setStartingTimeslot(null);
			courseElementInstance.pushChanges();
		}

		program.setPublished(false);
		program.pushChanges();
	}
}
