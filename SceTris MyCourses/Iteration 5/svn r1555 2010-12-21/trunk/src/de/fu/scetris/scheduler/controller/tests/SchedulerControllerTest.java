/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.controller.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
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
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.controller.SchedulerController;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.util.Config;
import de.fu.weave.xml.XmlHelper;

public class SchedulerControllerTest {

	SchedulerController schedulerController;
	RelationManager relationManager;
	Program program;

	@Before
	public void initialize() throws DatabaseException,
			ParserConfigurationException, IOException, SAXException {
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");

		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		Department department = relationManager.createDepartment("Informatik");

		Building building = relationManager.fullyCreateBuilding(department,
																"Informatik Hauptgebäude",
																"Takustraße 9, 14195 Berlin");

		AcademicTerm academicTerm = relationManager.createAcademicTerm(
																	   "Winter semester 2010/2011",
																	   Date.valueOf("2010-10-01"),
																	   Date.valueOf("2011-03-31"),
																	   lecturingStart, lecturingEnd);

		List<Person> personList = new ArrayList<Person>(4);
		personList.add(relationManager.createPerson("Elfriede", "Fehr",
													"efehr", "123456"));
		personList.add(relationManager.createPerson("Heinz", "Schweppe",
													"hschweppe", "password"));
		personList.add(relationManager.createPerson("Klaus", "Kriegel",
													"kkriegel", "12345678"));
		personList.add(relationManager.createPerson("Achim", "Liers", "aliers",
													"1234"));

		program = relationManager.createProgram(academicTerm,
												department, personList.get(0));

		List<Course> courseList = new ArrayList<Course>(3);
		courseList
				.add(relationManager
						.createCourse("Algorithmik und Programmierung I : Funktionale Programmierung"));
		courseList
				.add(relationManager
						.createCourse("Mathematik für Informatiker I : Logik und Diskrete Mathematik"));
		courseList
				.add(relationManager
						.createCourse("Technische Informatik I : Grundlagen der Elektrotechnik"));

		List<CourseInstance> courseInstanceList = new ArrayList<CourseInstance>(
				3);
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

		List<CourseElement> courseElementList = new ArrayList<CourseElement>(6);
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 2));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 2));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 2));

		List<CourseElementInstance> courseElementInstanceList = new ArrayList<CourseElementInstance>(
				8);
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(0),
											 courseElementList.get(0), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(0),
											 courseElementList.get(0), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(0),
											 courseElementList.get(1), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(1),
											 courseElementList.get(2), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(1),
											 courseElementList.get(2), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(1),
											 courseElementList.get(3), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(2),
											 courseElementList.get(4), 2));
		courseElementInstanceList.add(relationManager
				.createCourseElementInstance(courseInstanceList.get(2),
											 courseElementList.get(5), 2));

		List<Room> roomList = new ArrayList<Room>(2);
		roomList.add(relationManager.fullyCreateRoom("HS", "", building));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "", building));

		List<Day> dayList = new ArrayList<Day>(5);
		for (int i = 0; i < 5; ++i) {
			dayList.add(relationManager.createDay(weekdays[i]));
		}

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

		relationManager.createElementInstancePrefersTimeslot(
															 courseElementInstanceList.get(0),
															 timeSlotList.get(2), 90);
		relationManager.createElementInstancePrefersTimeslot(
															 courseElementInstanceList.get(0),
															 timeSlotList.get(3), 90);

		schedulerController = SchedulerController.getInstance(relationManager);
	}

	/**
	 * The connection is established and the scheme is created.
	 * 
	 * @postcondition: the connection has been established.
	 * 
	 * @postcondition: the scheme is created.
	 * 
	 * @throws Exception
	 */
	@Before
	public void setUp() throws Exception {

		String xmlConfig = "<config><database>\n" + "<user>scetris</user>"
				+ "<password>scetris</password>" + "<host>localhost</host>"
				+ "<name>scetris</name>" + "</database></config>\n";
		XmlHelper xmlHelper = new XmlHelper();
		Config conf = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		assertEquals("localhost", conf.get("host"));

		relationManager = new RelationManager(new ConnectionManagerImpl(conf));
		relationManager.connectionManager.connect();

		assertTrue(relationManager.connectionManager.validate());
		assertTrue(relationManager.install());
	}

	/**
	 * Postcondition: the algorithm has terminated.
	 * 
	 * Postcondition: the startingTimeSlot of every CourseElementInstance is set.
	 * 
	 * Postcondition: the relation elementInstanceTakesPlaceIn has as many as entries as courses are
	 * available.
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStart() throws Exception {

		schedulerController.startScheduler(program,false);

		while (!schedulerController.isReady()) {
			;
		}

		Collection<CourseElementInstance> courseElementInstanceCollection = relationManager
				.getCourseElementInstance();

		for (CourseElementInstance course : courseElementInstanceCollection) {
			Assert.assertNotNull(course.getStartingTimeslot());

			Assert.assertTrue(course
					.whereSubjectOfElementInstanceTakesPlaceInRoom().size() == 1);
		}

	}

}
