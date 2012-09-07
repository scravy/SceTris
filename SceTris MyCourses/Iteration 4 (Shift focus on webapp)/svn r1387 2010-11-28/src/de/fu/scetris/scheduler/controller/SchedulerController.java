package de.fu.scetris.scheduler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.Course;
import de.fu.scetris.scheduler.model.data.DayTimetable;
import de.fu.scetris.scheduler.model.data.RoomTimetable;
import de.fu.scetris.scheduler.model.data.ScheduleScore;
import de.fu.scetris.scheduler.model.data.TimeSlot;
import de.fu.scetris.scheduler.model.data.TimeSlotComparator;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.GeneticFactory;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.ScheduleChecker;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;

/**
 * 
 * Starts the scheduling on behalf of the current database state.
 * 
 * @author Konrad Reiche
 * 
 */
public class SchedulerController {

	private static SchedulerController instance;
	private final RelationManager relationManager;

	private Algorithm algorithm;
	private Thread schedulerThread;
	private SchedulerTask schedulerTask;

	private SchedulerController(final RelationManager relationManager) {
		this.relationManager = relationManager;
		schedulerThread = new Thread(new SchedulerTask(relationManager,
				algorithm, null));
	}

	public static SchedulerController getInstance(
			final RelationManager relationManager)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {

		if (instance == null) {
			instance = new SchedulerController(relationManager);
		}

		return instance;
	}

	public ScheduleScore getScheduleScore(Program program)
			throws DatabaseException {
		
		Configuration configuration = initializeConfiguration(program);
		ScheduleChecker scheduleChecker = new ScheduleChecker(configuration);
		return scheduleChecker.check(algorithm.getBestSchedule());
	}

	public void commitProposedSchedule(
			CourseElementInstance courseElementInstance)
			throws DatabaseException {
		relationManager.beginTransaction();
		courseElementInstance.pushChanges();
		relationManager.commitTransaction();
	}

	public Collection<RoomTimetable> getSchedulePresentation()
			throws DatabaseException {

		Collection<RoomTimetable> result = new ArrayList<RoomTimetable>();
		List<Room> roomList = (List<Room>) relationManager.getRoom();
		List<Timeslot> timeSlotList = (List<Timeslot>) relationManager
				.getTimeslot();
		List<CourseElementInstance> courseElementInstanceList = (List<CourseElementInstance>) relationManager
				.getCourseElementInstance();
		Collections.sort(timeSlotList, new TimeSlotComparator());
		int timeSlotsPerDay[] = Configuration
				.countTimeSlotsPerDay(timeSlotList);

		for (Room room : roomList) {

			int n = 0;
			Collection<DayTimetable> dayTimetableCollection = new ArrayList<DayTimetable>(
					timeSlotsPerDay.length);
			for (int i = 0; i < timeSlotsPerDay.length; ++i) {

				Collection<TimeSlot> timeSlotCollection = new ArrayList<TimeSlot>(
						timeSlotsPerDay[i]);
				for (int j = 0; j < timeSlotsPerDay[i]; ++j) {
					timeSlotCollection.add(new TimeSlot(timeSlotList.get(n)
							.getStartingTime().toString().substring(0, 5)));
					++n;
				}

				dayTimetableCollection.add(new DayTimetable(timeSlotList.get(
						n - 1).getDay(), timeSlotList.get(n - 1).getDay()
						.getName(), timeSlotCollection));
			}

			result.add(new RoomTimetable(room, room.getName(),
					dayTimetableCollection));
		}

		// XXX: Missing ORM functions for instance
		// getElementInstanceTakesPlaceInRoom
		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {

			ElementInstanceTakesPlaceInRoom takesPlaceInRoom = null;

			for (Room room : roomList) {
				List<ElementInstanceTakesPlaceInRoom> takesPlaceInRoomList = ((List<ElementInstanceTakesPlaceInRoom>) relationManager
						.getElementInstanceTakesPlaceInRoom(
								courseElementInstance, room));

				if (takesPlaceInRoomList.size() > 0) {
					takesPlaceInRoom = takesPlaceInRoomList.get(0);
					break;
				}
			}

			if (takesPlaceInRoom == null) {
				continue;
			}

			int k = 0;
			for (RoomTimetable roomTimetable : result) {

				if (roomTimetable.getRoom().equals(takesPlaceInRoom.getRoom())) {

					for (DayTimetable dayTimetable : ((List<RoomTimetable>) result)
							.get(k).getDayTimetableCollection()) {

						if (dayTimetable.getDay().equals(
								courseElementInstance.getStartingTimeslot()
										.getDay())) {

							int timeSlotIndex = timeSlotList
									.indexOf(courseElementInstance
											.getStartingTimeslot());

							for (int i = 0; i < timeSlotsPerDay.length; ++i) {
								if (timeSlotIndex < 12) {
									break;
								}
								timeSlotIndex -= timeSlotsPerDay[i];
							}

							String lecturerDisplayName = courseElementInstance
									.getCourseInstance().getMainLecturer()
									.getFirstName()
									+ " "
									+ courseElementInstance.getCourseInstance()
											.getMainLecturer().getLastName();
							Course course = new Course(lecturerDisplayName,
									courseElementInstance.getCourseInstance()
											.getInstanceOf().getName());

							for (int i = 0; i < courseElementInstance
									.getDuration(); ++i) {
								((List<TimeSlot>) dayTimetable
										.getTimeSlotCollection())
										.get(timeSlotIndex + i)
										.getCourseCollection().add(course);
							}

							break;
						}
					}

					break;
				}
				++k;
			}

		}

		return result;

	}

	/**
	 * Initializes {@link Configuration} with respect to the given
	 * {@link AcademicTerm} and to the given {@link Department}. When a
	 * {@link Room} has no {@link Building} or the {@link Building} has no
	 * {@link Department} it not added.
	 * 
	 * @param academicTerm
	 *            the {@link AcademicTerm} to be scheduled.
	 * @param department
	 *            the {@link Department} to be scheduled.
	 * @return
	 * @return the initialized {@link Configuration}
	 * @throws DatabaseException
	 */
	public Configuration initializeConfiguration(Program program)
			throws DatabaseException {

		Department department = program.getDepartment();
		List<Room> roomList = (List<Room>) relationManager.getRoom();

		for (Iterator<Room> it = roomList.iterator(); it.hasNext();) {
			Room room = it.next();
			if (room.getBuilding() == null
					|| room.getBuilding().getDepartment() == null
					|| !room.getBuilding().getDepartment().equals(department)) {
				it.remove();
			}
		}

		List<Person> lecturerList = new ArrayList<Person>();

		List<CourseElementInstance> courseElementInstanceList = (List<CourseElementInstance>) relationManager
				.getCourseElementInstance();

		for (Iterator<CourseElementInstance> it = courseElementInstanceList
				.iterator(); it.hasNext();) {
			CourseElementInstance courseElementInstance = it.next();

			if (!courseElementInstance.getCourseInstance().getProgram()
					.equals(program)) {
				it.remove();
			}
		}

		for (CourseElementInstance courseElementInstance : courseElementInstanceList) {
			lecturerList.add(courseElementInstance.getCourseInstance()
					.getMainLecturer());
		}

		List<Timeslot> timeSlotList = (List<Timeslot>) relationManager
				.getTimeslot();
		List<Feature> featureList = (List<Feature>) relationManager
				.getFeature();
		List<ProposedScheduling> proposedSchedulingList = (List<ProposedScheduling>) relationManager
				.getProposedScheduling();

		return new Configuration(roomList, lecturerList,
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList);
	}

	public boolean isReady() {
		return !isRunning() && !isStopping();
	}

	public boolean isRunning() {
		if (schedulerTask != null) {
			try {
				System.out.println(schedulerTask.algorithm.getBestSchedule()
						.getHardFitness());
			} catch (DatabaseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		return schedulerThread.isAlive();
	}

	/**
	 * Checks the given resources if they can match the expected constraints.
	 * 
	 * @return is it possible to schedule a constraint-free schedule.
	 */
	private boolean isScheduable(Configuration configuration) {

		int durationSum = 0;
		for (CourseElementInstance courseElementInstance : configuration.courseList) {
			durationSum += courseElementInstance.getDuration();
		}

		int timeSpaceSum = configuration.timeSlotList.size()
				* configuration.roomList.size();

		if (configuration.timeSlotList.isEmpty()
				|| configuration.courseList.isEmpty()
				|| configuration.lecturerList.isEmpty()
				|| configuration.roomList.isEmpty()) {

			return false;
		} else if (durationSum > timeSpaceSum) {
			return false;
		}

		return true;
	}

	public boolean isStopping() {
		return schedulerThread.isInterrupted();
	}

	/**
	 * Starts the Scheduler concurrently.
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
	 * @throws Exception
	 */
	public void startScheduler(Program program) throws Exception {

		Configuration configuration = initializeConfiguration(program);
		AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
				0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);
		algorithm = GeneticFactory.getInstance().createAlgorithm(configuration,
				algorithmConfiguration);

		if (isScheduable(configuration)) {
			schedulerTask = new SchedulerTask(relationManager, algorithm,
					program);
			schedulerThread = new Thread(schedulerTask);
			schedulerThread.start();
		} else {
			throw new Exception("Not scheduleable!");
		}
	}

	public void stopScheduler() {
		schedulerThread.interrupt();
	}
	
	public Program getCurrentProgramBeScheduling() {
		
		return schedulerTask.getCurrentProgramBeingScheduled();
	}
}
