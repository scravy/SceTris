package de.fu.scetris.scheduler.controller.tests;

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

import de.fu.bakery.orm.java.DatabaseException;
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
import de.fu.scetris.scheduler.controller.SchedulerController;
import de.fu.scetris.scheduler.controller.SchedulerTask;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.GeneticFactory;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;

public class SchedulerTaskTest extends SchedulerTest {

	SchedulerTask schedulerTask;
	Algorithm algorithm;
	AlgorithmConfiguration algorithmConfiguration;
	Configuration configuration;

	@Before
	public void initialize() throws DatabaseException,
			ParserConfigurationException, IOException, SAXException {

		// Miscellaneous
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");

		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		// Department, Building, Academic Term
		List<Department> departmentList = new ArrayList<Department>();
		departmentList.add(relationManager.createDepartment("Informatik"));
		departmentList.add(relationManager.createDepartment("Mathematik"));

		List<Building> buildingList = new ArrayList<Building>();
		buildingList.add(relationManager.fullyCreateBuilding(
			"Informatik Hauptgebäude",
			"Takustraße 9, 14195 Berlin",
			departmentList.get(0)));

		buildingList.add(relationManager.createBuilding("Arnimallee 22, 14195 Berlin"));

		AcademicTerm academicTerm = relationManager.createAcademicTerm(
																	   "WS 2010/2011",
																	   Date.valueOf("2010-10-01"),
																	   Date.valueOf("2011-03-31"),
																	   lecturingStart, lecturingEnd);

		// Person
		List<Person> personList = new ArrayList<Person>();
		personList.add(relationManager.createPerson("Elfriede", "Fehr",
													"efehr", "123456"));
		personList.add(relationManager.createPerson("Heinz", "Schweppe",
													"hschweppe", "password"));
		personList.add(relationManager.createPerson("Klaus", "Kriegel",
													"kkriegel", "12345678"));
		personList.add(relationManager.createPerson("Achim", "Liers", "aliers",
													"1234"));
		personList.add(relationManager.createPerson("Holger", "Reich", "hreich",
				"12345"));

		// Program
		Program program = relationManager.createProgram(academicTerm, departmentList.get(0),
				personList.get(0));

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
		courseList
				.add(relationManager
						.createCourse("Analysis I"));

		// Course Instance
		List<CourseInstance> courseInstanceList = new ArrayList<CourseInstance>();
		courseInstanceList.add(relationManager.createCourseInstance(program,
																	courseList.get(0), lecturingStart,
																	lecturingEnd,
																	personList.get(1)));
		courseInstanceList.add(relationManager.createCourseInstance(program,
																	courseList.get(1), lecturingStart,
																	lecturingEnd,
																	personList.get(2)));
		courseInstanceList.add(relationManager.createCourseInstance(program,
																	courseList.get(2), lecturingStart,
																	lecturingEnd,
																	personList.get(3)));
		courseInstanceList.add(relationManager.createCourseInstance(program,
				courseList.get(3), lecturingStart,
				lecturingEnd,
				personList.get(4)));

		// Course Element
		List<CourseElement> courseElementList = new ArrayList<CourseElement>(3);
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(1), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(2), 2));
		courseElementList.add(relationManager.createCourseElement(
				  courseList.get(3), 4));

		// Course Element Instance
		List<CourseElementInstance> courseElementInstanceList = new ArrayList<CourseElementInstance>();

		for (int i = 0; i < courseElementList.size(); ++i) {
			for (int j = 0; j < courseElementList.get(i).getDuration(); j += 2) {
				CourseElementInstance courseElementInstance = relationManager
						.createCourseElementInstance(courseInstanceList.get(i),
								courseElementList.get(i), 2);
				courseElementInstance.setSchedulableLesson(true);
				courseElementInstance.pushChanges();
				courseElementInstanceList.add(courseElementInstance);
			}
		}

		// Room
		List<Room> roomList = new ArrayList<Room>();
		roomList.add(relationManager.fullyCreateRoom("HS", "000", buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "046", buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("HS A", "A", buildingList.get(1)));

		// Day
		List<Day> dayList = new ArrayList<Day>(5);
		for (int i = 0; i < 5; ++i) {
			dayList.add(relationManager.createDay(weekdays[i]));
		}

		// Time Slot
		int j = 0;
		int h = 8;
		List<Timeslot> timeSlotList = new ArrayList<Timeslot>(60);
		for (int i = 0; i < 60; ++i) {

			timeSlotList.add(relationManager.createTimeslot(
															dayList.get(j),
															Time.valueOf("0".concat(new Integer(h)
																							.toString())
																	.concat(
																			":00:00"))));
			++h;

			if (((i + 1) % 12) == 0) {
				++j;
				h = 8;
			}
		}

		// Schedule Course Element Instances manually by Proposed Scheduling
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>(3);
		proposedSchedulingList.add(relationManager.createProposedScheduling(100,
				courseElementInstanceList.get(0)));
		proposedSchedulingList.get(0).setTimeslot(timeSlotList.get(2));
		proposedSchedulingList.get(0).setRoom(roomList.get(0));
		proposedSchedulingList.get(0).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(100,
				courseElementInstanceList.get(1)));
		proposedSchedulingList.get(1).setTimeslot(timeSlotList.get(26));
		proposedSchedulingList.get(1).setRoom(roomList.get(0));
		proposedSchedulingList.get(1).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(100,
				courseElementInstanceList.get(2)));
		proposedSchedulingList.get(2).setTimeslot(timeSlotList.get(12));
		proposedSchedulingList.get(2).setRoom(roomList.get(0));
		proposedSchedulingList.get(2).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(100,
				courseElementInstanceList.get(3)));
		proposedSchedulingList.get(3).setTimeslot(timeSlotList.get(38));
		proposedSchedulingList.get(3).setRoom(roomList.get(0));
		proposedSchedulingList.get(3).pushChanges();

		proposedSchedulingList.add(relationManager.createProposedScheduling(100,
				courseElementInstanceList.get(4)));
		proposedSchedulingList.get(4).setTimeslot(timeSlotList.get(2));
		proposedSchedulingList.get(4).setRoom(roomList.get(0));
		proposedSchedulingList.get(4).pushChanges();

		configuration = SchedulerController.getInstance(relationManager)
				.initializeConfiguration(program);

		algorithmConfiguration = new AlgorithmConfiguration(
				0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);

		algorithm = GeneticFactory.getInstance().createAlgorithm(configuration,
				algorithmConfiguration);

		schedulerTask = new SchedulerTask(relationManager, algorithm, program, false);
	}

	@Test
	public void testRun() {

		Assert.assertEquals(algorithm, schedulerTask.getAlgorithm());
		configuration.roomList.clear();

		new Thread(schedulerTask).run();

		Assert.assertTrue(schedulerTask.hasFailed());
	}
}
