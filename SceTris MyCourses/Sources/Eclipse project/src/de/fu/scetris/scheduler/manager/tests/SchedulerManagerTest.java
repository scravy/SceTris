/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.manager.tests;

import static de.fu.weave.orm.filters.Filters.eq;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.weave.orm.DatabaseException;

public class SchedulerManagerTest extends SchedulerTest {

	SchedulerManager schedulerManager;

	List<Program> programList;
	List<CourseElementInstance> courseElementInstanceList;
	List<Timeslot> timeSlotList;
	List<Room> roomList;
	List<Day> dayList;
	List<CourseElement> courseElementList;
	List<CourseInstance> courseInstanceList;
	List<Person> personList;

	@Before
	public void initialize() throws DatabaseException,
			ParserConfigurationException, IOException, SAXException {

		// Miscellaneous
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");

		// Department, Building, Academic Term
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(relationManager.createDepartment("Informatik"));
		departmentList.add(relationManager.createDepartment("Mathematik"));

		List<Building> buildingList = new ArrayList<Building>();
		buildingList.add(relationManager.fullyCreateBuilding(
				"Informatik Hauptgebäude", "Takustraße 9, 14195 Berlin",
				departmentList.get(0)));

		buildingList.add(relationManager
				.createBuilding("Arnimallee 22, 14195 Berlin"));

		AcademicTerm academicTerm = relationManager.createAcademicTerm(
				"WS 2010/2011", Date.valueOf("2010-10-01"),
				Date.valueOf("2011-03-31"), lecturingStart, lecturingEnd);

		// Person
		personList = new ArrayList<Person>();
		personList.add(relationManager.createPerson("Elfriede", "Fehr",
				"efehr", "123456"));
		personList.add(relationManager.createPerson("Heinz", "Schweppe",
				"hschweppe", "password"));
		personList.add(relationManager.createPerson("Klaus", "Kriegel",
				"kkriegel", "12345678"));
		personList.add(relationManager.createPerson("Achim", "Liers", "aliers",
				"1234"));
		personList.add(relationManager.createPerson("Holger", "Reich",
				"hreich", "12345"));

		// Program
		programList = new ArrayList<Program>();
		programList.add(relationManager.createProgram(academicTerm,
				departmentList.get(0), personList.get(0)));
		programList.add(relationManager.createProgram(academicTerm,
				departmentList.get(1), personList.get(4)));

		// Course
		List<Course> courseList = new ArrayList<Course>();
		courseList
				.add(relationManager
						.createCourse("Algorithmik und Programmierung I : Funktionale Programmierung"));
		courseList
				.add(relationManager
						.createCourse("Mathematik für Informatiker I : Logik und Diskrete Mathematik"));
		courseList
				.add(relationManager
						.createCourse("Technische Informatik I : Grundlagen der Elektrotechnik"));
		courseList.add(relationManager.createCourse("Analysis I"));

		// Course Instance
		courseInstanceList = new ArrayList<CourseInstance>();
		courseInstanceList.add(relationManager.createCourseInstance(
				programList.get(0), courseList.get(0), lecturingStart,
				lecturingEnd, personList.get(1)));
		courseInstanceList.add(relationManager.createCourseInstance(
				programList.get(0), courseList.get(1), lecturingStart,
				lecturingEnd, personList.get(2)));
		courseInstanceList.add(relationManager.createCourseInstance(
				programList.get(0), courseList.get(2), lecturingStart,
				lecturingEnd, personList.get(3)));
		courseInstanceList.add(relationManager.createCourseInstance(
				programList.get(1), courseList.get(3), lecturingStart,
				lecturingEnd, personList.get(4)));

		// Course Element
		courseElementList = new ArrayList<CourseElement>(3);
		courseElementList.add(relationManager.createCourseElement(
				courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
				courseList.get(1), 4));
		courseElementList.add(relationManager.createCourseElement(
				courseList.get(2), 2));
		courseElementList.add(relationManager.createCourseElement(
				courseList.get(3), 4));

		// Course Element Instance
		courseElementInstanceList = new ArrayList<CourseElementInstance>();

		for (int i = 0; i < courseElementList.size(); ++i)
			for (int j = 0; j < courseElementList.get(i).getDuration(); j += 2) {
				CourseElementInstance courseElementInstance = relationManager
						.createCourseElementInstance(courseInstanceList.get(i),
								courseElementList.get(i), 2);
				courseElementInstance.setLecturer(courseInstanceList.get(i).getMainLecturer());
				courseElementInstance.setSchedulableLesson(true);
				courseElementInstance.pushChanges();
				courseElementInstanceList.add(courseElementInstance);
			}

		// Room
		roomList = new ArrayList<Room>();
		roomList.add(relationManager.fullyCreateRoom("HS", "000",
				buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "046",
				buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("HS A", "A",
				buildingList.get(1)));

		// Time Slot
		timeSlotList = relationManager.getTimeslot();

		// Schedule Course Element Instances manually by Proposed Scheduling
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>(
				3);
		proposedSchedulingList.add(relationManager.createProposedScheduling(
				100, courseElementInstanceList.get(0)));
		proposedSchedulingList.get(0).setTimeslot(timeSlotList.get(2));
		proposedSchedulingList.get(0).setRoom(roomList.get(0));
		proposedSchedulingList.get(0).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(
				100, courseElementInstanceList.get(1)));
		proposedSchedulingList.get(1).setTimeslot(timeSlotList.get(26));
		proposedSchedulingList.get(1).setRoom(roomList.get(0));
		proposedSchedulingList.get(1).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(
				100, courseElementInstanceList.get(2)));
		proposedSchedulingList.get(2).setTimeslot(timeSlotList.get(12));
		proposedSchedulingList.get(2).setRoom(roomList.get(0));
		proposedSchedulingList.get(2).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(
				100, courseElementInstanceList.get(3)));
		proposedSchedulingList.get(3).setTimeslot(timeSlotList.get(38));
		proposedSchedulingList.get(3).setRoom(roomList.get(0));
		proposedSchedulingList.get(3).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(
				100, courseElementInstanceList.get(4)));
		proposedSchedulingList.get(4).setTimeslot(timeSlotList.get(2));
		proposedSchedulingList.get(4).setRoom(roomList.get(0));
		proposedSchedulingList.get(4).pushChanges();

		// Initialize SchedulerManager
		schedulerManager = SchedulerManager.getInstance(relationManager);
	}

	@Test
	public void testFreezeProgram() throws DatabaseException {

		Assert.assertFalse(relationManager
				.getProgram(eq("id", programList.get(0).getId())).get(0)
				.getFreezed());

		schedulerManager.freezeProgram(programList.get(0));

		Assert.assertTrue(relationManager
				.getProgram(eq("id", programList.get(0).getId())).get(0)
				.getFreezed());

		for (CourseElementInstance courseElementInstance : relationManager
				.getCourseElementInstance())
			if (courseElementInstance.getCourseInstance().getProgram()
					.equals(programList.get(0)))
				Assert.assertEquals(false,
						courseElementInstance.getSchedulableLesson());

		schedulerManager.unfreezeProgram(programList.get(0));

		Assert.assertFalse(relationManager
				.getProgram(eq("id", programList.get(0).getId())).get(0)
				.getFreezed());

		for (CourseElementInstance courseElementInstnace : courseElementInstanceList) {
			Assert.assertEquals(true,
					courseElementInstnace.getSchedulableLesson());
			Assert.assertFalse(relationManager
					.getProgram(eq("id", programList.get(0).getId())).get(0)
					.getPublished());
		}
	}

	@Test
	public void testGetInstance() throws Exception {
		Assert.assertNotNull(SchedulerManager.getInstance(relationManager));
		Assert.assertNull(schedulerManager.getCurrentSchedulingScore());
	}

	@Test
	public void testGetScheduleScore() throws Exception {

		ScheduleScore score = schedulerManager.getScheduleScore(programList
				.get(0));
		Assert.assertNotNull(score);

		Assert.assertEquals(10, score.getNumberOfHardConstraints());
		Assert.assertEquals(8, score.getNumberOfHardConstraintsSatisfied());

		Assert.assertEquals(0, score.getNumberOfSoftConstraints());
		Assert.assertEquals(0, score.getNumberOfSoftConstraintsSatisfied());

		Assert.assertEquals(
				2,
				score.getCourseConflictsRoom()
						.get(roomList.get(0)).size());
		Assert.assertTrue(score.getCourseConflictsRoom()
				.get(roomList.get(0))
				.contains(courseElementInstanceList.get(4)));

		Assert.assertEquals(
				2,
				score.getCourseConflictsRoom()
						.get(roomList.get(0)).size());
		Assert.assertTrue(score.getCourseConflictsRoom()
				.get(roomList.get(0))
				.contains(courseElementInstanceList.get(0)));
	}

	@Test
	public void testGetScheduleScoreWithChange() throws Exception {

		ProposedScheduling proposedScheduling = relationManager
				.newProposedScheduling(100, courseElementInstanceList.get(4));
		proposedScheduling.setRoom(roomList.get(0));
		proposedScheduling.setTimeslot(timeSlotList.get(52));
		proposedScheduling.pushChanges();

		ScheduleScore score = schedulerManager.getScheduleScoreWithChange(
				programList.get(0), proposedScheduling);

		Assert.assertEquals(10, score.getNumberOfHardConstraints());
		Assert.assertEquals(10, score.getNumberOfHardConstraintsSatisfied());

		Assert.assertEquals(0, score.getNumberOfSoftConstraints());
		Assert.assertEquals(0, score.getNumberOfSoftConstraintsSatisfied());

		Assert.assertEquals(0, score.getCourseConflictsRoom().size());
		Assert.assertEquals(0, score.getCourseConflictsRoom().size());
	}

	@Test
	public void testIsScheduleable() {

		boolean isScheduleable = true;
		try {
			schedulerManager.startScheduler(programList.get(1), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

		isScheduleable = true;
		try {
			relationManager.deleteRoom();
			schedulerManager.startScheduler(programList.get(0), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

		isScheduleable = true;
		try {
			relationManager.deletePerson();
			schedulerManager.startScheduler(programList.get(0), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

		isScheduleable = true;
		try {
			relationManager.deleteCourseElementInstance();
			schedulerManager.startScheduler(programList.get(0), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

		isScheduleable = true;
		try {
			relationManager.deleteTimeslot();
			schedulerManager.startScheduler(programList.get(0), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

		isScheduleable = true;
		try {
			relationManager.createTimeslot(dayList.get(0),
					Time.valueOf("08:00:00"));

			for (int i = 0; i < courseElementList.size(); ++i)
				for (int j = 0; j < courseElementList.get(i).getDuration(); j += 2) {
					CourseElementInstance courseElementInstance = relationManager
							.createCourseElementInstance(
									courseInstanceList.get(i),
									courseElementList.get(i), 2);
					courseElementInstance.setSchedulableLesson(true);
					courseElementInstance.pushChanges();
					courseElementInstanceList.add(courseElementInstance);
				}

			schedulerManager.startScheduler(programList.get(0), false);
		} catch (Exception e) {
			isScheduleable = false;
		} finally {
			Assert.assertFalse(isScheduleable);
		}

	}

	/**
	 * A Program which was not been freezed will be published after freeze.
	 * 
	 */
	@Test
	public void testPublishNoneFreezedProgram() throws DatabaseException {

		Program program = programList.get(0);

		schedulerManager.publishProgram(program);

		Program freshProgramFetch = relationManager.getProgram(
				eq("id", program.getId())).get(0);
		boolean isFreezed = freshProgramFetch.getFreezed();
		boolean isPublished = freshProgramFetch.getPublished();

		Assert.assertTrue(isFreezed);
		Assert.assertTrue(isPublished);
	}

	@Test
	public void testPublishProgram() throws DatabaseException {

		ProposedScheduling proposedScheduling = relationManager
				.getProposedScheduling(eq("element_instance", 5)).get(0);
		proposedScheduling.setTimeslot(timeSlotList.get(4));
		proposedScheduling.pushChanges();

		schedulerManager.freezeProgram(programList.get(0));
		Assert.assertFalse(programList.get(0).getPublished());

		schedulerManager.publishProgram(programList.get(0));

		Assert.assertTrue(relationManager
				.getProgram(eq("id", programList.get(0).getId())).get(0)
				.getPublished());

		for (CourseElementInstance courseElementInstance : courseElementInstanceList
				.subList(0, 4)) {

			List<ProposedScheduling> proposedSchedulingList = relationManager
					.getProposedScheduling(eq("element_instance",
							courseElementInstance.getId()));
			Assert.assertEquals(1, proposedSchedulingList.size());

			Room room = proposedSchedulingList.get(0).getRoom();

			Assert.assertEquals(
					1,
					relationManager.getElementInstanceTakesPlaceInRoom(
							courseElementInstance, room).size());

			Assert.assertNotNull(relationManager
					.getCourseElementInstance(
							eq("id", courseElementInstance.getId())).get(0)
					.getStartingTimeslot());

		}

		schedulerManager.unpublishProgram(programList.get(0));

		for (CourseElementInstance courseElementInstance : courseElementInstanceList
				.subList(0, 4)) {

			List<ProposedScheduling> proposedSchedulingList = relationManager
					.getProposedScheduling(eq("element_instance",
							courseElementInstance.getId()));
			Assert.assertEquals(1, proposedSchedulingList.size());

			Room room = proposedSchedulingList.get(0).getRoom();

			Assert.assertEquals(
					0,
					relationManager.getElementInstanceTakesPlaceInRoom(
							courseElementInstance, room).size());

			Assert.assertNull(relationManager
					.getCourseElementInstance(
							eq("id", courseElementInstance.getId())).get(0)
					.getStartingTimeslot());
		}
	}

	/**
	 * Postcondition: the algorithm has terminated.
	 * 
	 * Postcondition: the startingTimeSlot of every CourseElementInstance is
	 * set.
	 * 
	 * Postcondition: the relation elementInstanceTakesPlaceIn has as many as
	 * entries as courses are available.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStart() throws Exception {

		Assert.assertFalse(schedulerManager.isRunning());
		Assert.assertTrue(schedulerManager.isReady());

		schedulerManager.startScheduler(programList.get(0), false);

		// Test multiple scheduler start prevention
		schedulerManager.startScheduler(programList.get(0), false);

		boolean hasDoneOnce = false;
		ScheduleScore score;
		while (!schedulerManager.isReady())
			if (!hasDoneOnce) {
				Assert.assertEquals(programList.get(0),
						schedulerManager.getCurrentProgramBeScheduling());
				hasDoneOnce = true;
				score = schedulerManager.getScheduleScore(programList.get(0));
			}

		score = schedulerManager.getScheduleScore(programList.get(0));
		Assert.assertEquals(score.getHardFitness(), 1.0);

		List<ProposedScheduling> proposedSchedulingList = relationManager
				.getProposedScheduling();
		for (ProposedScheduling proposedScheduling : proposedSchedulingList) {
			Assert.assertNotNull(proposedScheduling.getElementInstance());
			Assert.assertNotNull(proposedScheduling.getRoom());
		}
	}

	@Test
	public void testStopScheduler() throws Exception {

		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(1), timeSlotList.get(30), 100);
		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(2), timeSlotList.get(30), 100);
		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(3), timeSlotList.get(30), 100);

		Assert.assertFalse(schedulerManager.isRunning());
		Assert.assertTrue(schedulerManager.isReady());
		schedulerManager.startScheduler(programList.get(0), false);

		if (!schedulerManager.isReady() || schedulerManager.isRunning()) {
			Assert.assertNotNull(schedulerManager.getCurrentSchedulingScore());
			schedulerManager.stopScheduler();
		}

		while (schedulerManager.isStopping() || !schedulerManager.isReady()) {

		}

		Assert.assertFalse(schedulerManager.isStopping());
		Assert.assertFalse(schedulerManager.isRunning());
		Assert.assertTrue(schedulerManager.isReady());
	}
}
