package de.fu.scetris.scheduler.manager;

import static de.fu.weave.orm.filters.Filters.any;
import static de.fu.weave.orm.filters.Filters.eq;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.TestDataGenerator;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.CourseRecommendedForYear;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
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
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleChecker;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Filter;

/**
 * Scheduler Manager provides methods to control the scheduler and to get
 * information about its state.
 * <p>
 * Scheduler Manager implements a Singleton, therefore only one Scheduler
 * Manager can exist at a time. Scheduler Manager has essentially two states:
 * (1) ready for a new scheduling job, (2) running a current scheduling job or
 * (3) stopping a current scheduling job.
 * <p>
 * Further methods are provided to check the score of specified {@link Program}
 * objects.
 * 
 * @author Konrad Reiche
 * 
 */
public class SchedulerManager {

	/**
	 * The last scheduled Program. This field is null until
	 * {@link SchedulerManager#getInstance(RelationManager)} is called the first
	 * time.
	 */
	public static Program lastScheduled = null;	
	
	/**
	 * The Singleton object. This field is null until
	 * {@link SchedulerManager#getInstance(RelationManager)} is called the first
	 * time.
	 */
	private static SchedulerManager instance;

	/**
	 * The applied algorithm for the scheduling process.
	 */
	private Algorithm algorithm;

	/**
	 * Managing the connection and interaction with the database.
	 */
	private final RelationManager relationManager;

	/**
	 * This runnable is executed in a separate thread handling a scheduling
	 * process.
	 */
	private SchedulerTask schedulerTask;

	/**
	 * The thread executing {@link SchedulerManager#schedulerTask}.
	 */
	private Thread schedulerThread;

	/**
	 * Lazy instantiation of the Singleton object. When this method is called
	 * the first time {@link SchedulerManager#instance} is set with the instance
	 * of <code>SchedulerManager</code>.
	 * 
	 * @param relationManager
	 *            Managing the connection and interaction with the database.
	 * @return the only instance of this class.
	 */
	public synchronized static SchedulerManager getInstance(final RelationManager relationManager) {

		if (instance == null) {
			instance = new SchedulerManager(relationManager.clone());
		}
		return instance;
	}

	/**
	 * Instantiates by setting the {@link RelationManager} and creating a
	 * scheduling thread in order to enable state requests to the scheduler.
	 * 
	 * @param relationManager
	 * @Managing the connection and interaction with the database.
	 */
	private SchedulerManager(final RelationManager relationManager) {
		this.relationManager = relationManager;
		schedulerThread = new Thread(new SchedulerTask(relationManager,
				algorithm, null, false));
	}

	/**
	 * Freezes an arbitrary {@link Program}.
	 * <p>
	 * Sets the {@link Program} attribute <code>freezed</code> to
	 * <code>true</code>.
	 * 
	 * @param program
	 *            The program to freeze.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	public synchronized void freezeProgram(final Program program)
			throws DatabaseException {
		setScheduleableLessons(program, false);
		program.setFreezed(true);
		program.pushChanges();
	}

	/**
	 * Filters all {@link CourseElementInstance} by a specified {@link Program}.
	 * 
	 * @param program
	 *            The specified {@link Program}.
	 * @return A {@link List} of {@link CourseElementInstance} objects.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	private synchronized List<CourseElementInstance> getCourseElementInstanceListByProgram(final Program program)
			throws DatabaseException {

		List<CourseInstance> courseInstanceList = relationManager
				.getCourseInstance(eq("program", program.getId()));

		Filter filters[] = new Filter[courseInstanceList.size()];
		for (int i = 0; i < filters.length; ++i)
			filters[i] = eq("course_instance", courseInstanceList.get(i)
					.getId());

		return relationManager.getCourseElementInstance(any(filters));
	}

	/**
	 * @return If there is a current {@link Program} being scheduled it is
	 *         returned, otherwise null is returned.
	 */
	public synchronized Program getCurrentProgramBeScheduling() {
		return schedulerTask.getCurrentProgramBeingScheduled();
	}

	/**
	 * @return If there is a current {@link Program} being scheduled its
	 *         {@link ScheduleScore} is returned, otherwise null is returned.
	 * 
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	public synchronized ScheduleScore getCurrentSchedulingScore() throws DatabaseException {

		if (schedulerTask == null) {
			return null;
		} else {
			return schedulerTask.getAlgorithm().getBestSchedule().getScore();
		}
	}

	/**
	 * @param program
	 *            The {@link Program} which {@link ScheduleScore} should be
	 *            calculated.
	 * @return The {@link ScheduleScore} of the defined {@link Program}.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 * @throws SchedulingException
	 *             If the scheduling process was interrupted due to an internal
	 *             exception.
	 * @throws NotScheduleableException
	 *             If the {@link Program} has not proposed schedule or the
	 *             resources are not sufficient for a proposed schedule.
	 */
	public synchronized ScheduleScore getScheduleScore(final Program program)
			throws SchedulingException, DatabaseException, NotScheduleableException {

		Configuration configuration = initializeConfiguration(program);
		ScheduleChecker scheduleChecker = new ScheduleChecker();
		return scheduleChecker.check(configuration);
	}

	/**
	 * If a {@link ProposedScheduling} is changed due to manually scheduling
	 * this method can be called in order to get the new {@link ScheduleScore}.
	 * 
	 * @param program
	 *            The {@link Program} which is manually scheduled.
	 * @param proposedScheduling
	 *            The new {@link ProposedScheduling} to check with the rest of
	 *            the scheduling.
	 * @return The {@link ScheduleScore} of the new proposed schedule.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 * @throws SchedulingException
	 *             If the scheduling process was interrupted due to an internal
	 *             exception.
	 * @throws NotScheduleableException
	 *             If the new {@link Program} has not proposed schedule or the
	 *             resources are not sufficient for a proposed schedule.
	 */
	public synchronized ScheduleScore getScheduleScoreWithChange(final Program program,
																 final ProposedScheduling proposedScheduling)
			throws DatabaseException, SchedulingException, NotScheduleableException {

		Configuration configuration = initializeConfiguration(program);
		configuration.proposedSchedulingList.remove(proposedScheduling);
		configuration.proposedSchedulingList.add(proposedScheduling);
		configuration.courseToProposal.put(
				proposedScheduling.getElementInstance(), proposedScheduling);

		ScheduleChecker scheduleChecker = new ScheduleChecker();
		return scheduleChecker.check(configuration);
	}

	/**
	 * Initializes {@link Configuration} with respect to the given
	 * {@link AcademicTerm} and to the given {@link Department}. When a
	 * {@link Room} has no {@link Building} or the {@link Building} has no
	 * {@link Department} it is not added.
	 * 
	 * @param academicTerm
	 *            the {@link AcademicTerm} to be scheduled.
	 * @param department
	 *            the {@link Department} to be scheduled.
	 * @return
	 * @return the initialized {@link Configuration}
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	public synchronized Configuration initializeConfiguration(final Program program)
			throws DatabaseException {

		List<Room> roomList = relationManager.getRoom();

		for (Iterator<Room> it = roomList.iterator(); it.hasNext();) {
			Room room = it.next();
			if ((room.getBuilding().getDepartment() == null)
					|| !room.getBuilding().getDepartment()
							.equals(program.getDepartment()))
				it.remove();
		}

		Set<Person> lecturerSet = new TreeSet<Person>();

		List<CourseElementInstance> courseElementInstanceList = relationManager
				.getCourseElementInstance();

		for (Iterator<CourseElementInstance> it = courseElementInstanceList
				.iterator(); it.hasNext();) {
			CourseElementInstance courseElementInstance = it.next();

			if (!courseElementInstance.getCourseInstance().getProgram()
					.equals(program))
				it.remove();
		}

		List<ProposedScheduling> proposedSchedulingList = relationManager
				.getProposedScheduling();

		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {

			ProposedScheduling proposedSchedulingOfCourse = null;
			for (ProposedScheduling proposedScheduling : proposedSchedulingList)
				if (proposedScheduling.getElementInstance().equals(
						courseElementInstance))
					proposedSchedulingOfCourse = proposedScheduling;

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

	/**
	 * The scheduler is ready when it is not stopping and not running at the
	 * moment.
	 * 
	 * @return Whether the scheduler is ready.
	 */
	public synchronized boolean isReady() {
		return !isStopping() && !isRunning();
	}

	/**
	 * The scheduler is running when the thread is alive.
	 * 
	 * @return Whether the scheduler is running.
	 */
	public synchronized boolean isRunning() {
		return schedulerThread.isAlive();
	}

	/**
	 * Checks the specified resources if they can match the expected
	 * constraints.
	 * <p>
	 * If the duration sum of all {@link CourseElementInstance} objects is less
	 * than the sum of all {@link Timeslot} objects there can not be a schedule
	 * with no overlapping courses.
	 * <p>
	 * If there are no courses, no lecturers or no room there can not be a
	 * schedule either.
	 * 
	 * @param configuration
	 *            The {@link Configuration} specifying the given resources to
	 *            schedule.
	 * 
	 * @return Whether it possible to schedule a constraint-free schedule.
	 */
	private boolean isScheduable(final Configuration configuration) {

		int durationSum = 0;
		for (CourseElementInstance courseElementInstance : configuration.courseList)
			durationSum += courseElementInstance.getDuration();

		int timeSpaceSum = configuration.timeSlotList.size()
				* configuration.roomList.size();

		if (configuration.timeSlotList.isEmpty()
				|| (durationSum > timeSpaceSum)
				|| configuration.courseList.isEmpty()
				|| configuration.lecturerList.isEmpty()
				|| configuration.roomList.isEmpty())
			return false;

		return true;
	}

	/**
	 * The scheduler is stopping when its master thread was interrupted.
	 * 
	 * @return Whether the scheduling process is stopping.
	 */
	public synchronized boolean isStopping() {
		return schedulerThread.isInterrupted();
	}

	/**
	 * Published a specified {@link Program}.
	 * <p>
	 * A {@link Program} must be freezed in order to be published. If the
	 * {@link Program} was freezed yet a call to
	 * {@link SchedulerManager#freezeProgram(Program)} is made and the method is
	 * calling itself again.
	 * 
	 * @param program
	 *            The {@link Program} which should be published.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	public synchronized void publishProgram(final Program program)
			throws DatabaseException {

		if (program.getFreezed()) {

			List<CourseInstance> courseInstanceList = relationManager
					.getCourseInstance(eq("program", program.getId()));

			List<ElementInstanceTakesPlaceInRoom> elementInstanceTakesPlaceInRoomList = relationManager
					.getElementInstanceTakesPlaceInRoom();

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

				boolean hasEntry = false;
				for (ElementInstanceTakesPlaceInRoom takesPlaceInRoom : elementInstanceTakesPlaceInRoomList) {

					if (takesPlaceInRoom.getElementInstance().equals(courseElementInstance)) {
						hasEntry = true;
						takesPlaceInRoom.setRoom(proposedScheduling.getRoom());
						takesPlaceInRoom.setTimeslot(timeSlot);
						takesPlaceInRoom.pushChanges();
						break;
					}
				}

				if (!hasEntry) {
					relationManager.createElementInstanceTakesPlaceInRoom(
							courseElementInstance, proposedScheduling.getRoom(),
							timeSlot);
				}
				
				courseElementInstance.pushChanges();
			}

			program.setPublished(true);
			program.pushChanges();
		} else {
			freezeProgram(program);
			publishProgram(program);
		}
	}

	/**
	 * Sets the {@link CourseElementInstance} attribute scheduleable lessons to
	 * the specified boolean.
	 * <p>
	 * A {@link CourseElementInstance} with scheduleable lessons set to false
	 * will not be moved to a new position in the scheduling process.
	 * 
	 * @param program
	 *            The {@link Program} of which the {@link CourseElementInstance}
	 *            objects should be changed.
	 * @param freeze
	 *            Whether the {@link CourseElementInstance} objects of the
	 *            specified {@link Program} should be scheduleable or not.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	private void setScheduleableLessons(final Program program,
										final boolean freeze) throws DatabaseException {
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
	 * Starts the scheduling process.
	 * <p>
	 * The {@link Configuration} is initialized with respect to the given
	 * {@link AcademicTerm} and to the given {@link Department}.
	 * <p>
	 * If the given {@link Configuration} is scheduleable, a separate Thread
	 * executing the Scheduler is started, otherwise an error arises.
	 * 
	 * @param academicTerm
	 *            the {@link AcademicTerm} to be scheduled.
	 * @param department
	 *            the {@link Department} to be scheduled.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 * @throws NotScheduleableException
	 */
	public synchronized void startScheduler(final Program program,
											final boolean resume) throws DatabaseException,
			NotScheduleableException {

		if (isReady()) {
			Configuration configuration = initializeConfiguration(program);
			AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
					0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);
			algorithm = GeneticFactory.getInstance().createAlgorithm(
					configuration, algorithmConfiguration);

			if (isScheduable(configuration)) {
				lastScheduled = program;
				schedulerTask = new SchedulerTask(relationManager, algorithm,
						program, resume);
				schedulerThread = new Thread(schedulerTask);
				schedulerThread.start();
			} else {
				throw new NotScheduleableException();
			}
		} else {
			System.err.println("Cannot start the scheduler, is not ready yet.");
		}
	}

	/**
	 * The scheduled is stopped by interruptings its master thread. Interrupting
	 * the master thread tells to cancel the algorithm on the next possible
	 * step.
	 */
	public synchronized void stopScheduler() {
		schedulerThread.interrupt();
	}

	/**
	 * Unfreezes an arbitrary {@link Program}.
	 * <p>
	 * Sets the {@link Program} attribute <code>freezed</code> to
	 * <code>false</code>.
	 * 
	 * @param program
	 *            The program to freeze.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
	public synchronized void unfreezeProgram(final Program program)
			throws DatabaseException {
		setScheduleableLessons(program, true);
		program.setFreezed(false);
		program.pushChanges();
	}

	/**
	 * Unpublished a specified {@link Program}.
	 * 
	 * @param program
	 *            The {@link Program} which should be unpublished.
	 * @throws DatabaseException
	 *             If an underlying exception regarding the database connection
	 *             is thrown.
	 */
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
	
	public static void main(String[] args) throws IOException, SAXException, DatabaseException, ParserConfigurationException, SchedulingException, NotScheduleableException {
		
		TestDataGenerator tdg = new TestDataGenerator(new Random(), false);
		getInstance(tdg.getRelationManager()).getScheduleScore(tdg.getRelationManager().getProgram(1));
	}
}
