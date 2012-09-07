/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Map.Entry;
import java.util.Set;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.MutationImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.SetupImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.TimeCourseSlot;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.util.Config;
import de.fu.weave.xml.XmlHelper;

/**
 * Tests the Mutation operation implementation.
 * 
 * @author Konrad Reiche, Hagen Mahnke
 * @since Iteration2
 */
public class MutationImplTest {

	private static RelationManager relationManager;
	private Configuration configuration;
	private ScheduleImpl schedule;

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

		String xmlConfig = "<config><database>\n"
				+ "<user>scetris-testing</user>"
				+ "<password>kleinerAffeMitHut</password>"
				+ "<host>localhost</host>" + "<name>scetris-testing</name>"
				+ "</database></config>\n";
		XmlHelper xmlHelper = new XmlHelper();
		Config conf = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		assertEquals("localhost", conf.get("host"));

		relationManager = new RelationManager(new ConnectionManagerImpl(conf));
		relationManager.connectionManager.connect();

		assertTrue(relationManager.connectionManager.validate());
		assertTrue(relationManager.install());
	}

	@Before
	public void initialize() throws DatabaseException {
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");


		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		Building building = relationManager
				.createBuilding("Takustra�e 9, 14195 Berlin");

		Department deparment = relationManager
				.createDepartment("Computer Science");

		AcademicTerm academicTerm = relationManager.createAcademicTerm(
				"Winter semester 2010/2011", Date.valueOf("2010-10-01"),
				Date.valueOf("2011-03-31"), lecturingStart, lecturingEnd);

		List<Person> personList = new ArrayList<Person>(4);
		personList.add(relationManager.createPerson("Elfriede", "Fehr",
				"efehr", "123456"));
		personList.add(relationManager.createPerson("Heinz", "Schweppe",
				"hschweppe", "password"));
		personList.add(relationManager.createPerson("Klaus", "Kriegel",
				"kkriegel", "12345678"));
		personList.add(relationManager.createPerson("Achinm", "Liers",
				"aliers", "1234"));

		Program program = relationManager.createProgram(academicTerm,
				deparment, personList.get(0));

		List<Course> courseList = new ArrayList<Course>(3);
		courseList
				.add(relationManager
						.createCourse("Algorithmik und Programmierung I : Funktionale Programmierung"));
		courseList
				.add(relationManager
						.createCourse("Mathematik f�r Informatiker I : Logik und Diskrete Mathematik"));
		courseList
				.add(relationManager
						.createCourse("Technische Informatik I : Grundlagen der Elektrotechnik"));

		List<CourseInstance> courseInstanceList = new ArrayList<CourseInstance>(
				3);
		courseInstanceList.add(relationManager.createCourseInstance(program,
				courseList.get(0), lecturingStart, lecturingEnd,
				personList.get(1)));
		courseInstanceList.add(relationManager.createCourseInstance(program,
				courseList.get(1), lecturingStart, lecturingEnd,
				personList.get(2)));
		courseInstanceList.add(relationManager.createCourseInstance(program,
				courseList.get(2), lecturingStart, lecturingEnd,
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
		roomList.add(relationManager.createRoom("HS", building));
		roomList.add(relationManager.createRoom("SR 046", building));

		List<Feature> featureList = new ArrayList<Feature>(0);
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();
		

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
					Time.valueOf("0".concat(new Integer(h).toString()).concat(
							":00:00"))));
			++h;

			if (((i + 1) % 12) == 0) {
				++j;
				h = 8;
			}
		}
		
		proposedSchedulingList.add(relationManager.newProposedScheduling(100,
				courseElementInstanceList.get(1)));
		proposedSchedulingList.get(0).setTimeslot(timeSlotList.get(0));
		proposedSchedulingList.get(0).setRoom(roomList.get(0));
		courseElementInstanceList.get(1).setSchedulableLesson(false);

		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(0), timeSlotList.get(2), 100);
		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(0), timeSlotList.get(3), 100);

		configuration = new Configuration(roomList,
				personList.subList(1, 3), courseElementInstanceList,
				timeSlotList, featureList, proposedSchedulingList);

		schedule = new ScheduleImpl(configuration);

		schedule.addCourse(courseElementInstanceList.get(0), 0, 2);
		schedule.addCourse(courseElementInstanceList.get(1), 0, 26);
		schedule.addCourse(courseElementInstanceList.get(2), 1, 16);
		schedule.addCourse(courseElementInstanceList.get(3), 0, 12);
		schedule.addCourse(courseElementInstanceList.get(4), 0, 38);
		schedule.addCourse(courseElementInstanceList.get(5), 1, 0);
		schedule.addCourse(courseElementInstanceList.get(6), 0, 52);
		schedule.addCourse(courseElementInstanceList.get(7), 1, 48);
	}

	/**
	 * 
	 * @postcondition: the mutated schedule map has to have the same
	 *                 CourseElementInstances as before.
	 * 
	 * @postcondition: every CourseElementInstance occurs duration times in the
	 *                 time slots and do so in a continuous way.
	 * 
	 *                 Example: A CourseElementInstance has a duration of 4. It
	 *                 will be scheduled in time slots 6,7,8,9. These time slots
	 *                 belong to the same day
	 * 
	 * @postcondition: There is no course scheduled which overlaps through its
	 *                 duration in days.
	 * 
	 * @throws DatabaseException
	 * 
	 * @since Iteration2
	 */
	@Test
	public void testMutate() throws DatabaseException {

		new SetupImpl(new Random()).initialize(schedule, configuration,false);
		MutationImpl mutation = new MutationImpl(1.0f,new Random());

		Set<CourseElementInstance> courseSetOld = schedule.getCourseToSlot()
				.keySet();

		mutation.mutate(schedule, configuration);

		Set<CourseElementInstance> courseSetNew = schedule.getCourseToSlot()
				.keySet();

		// 1. postcondition
		assertEquals(courseSetOld, courseSetNew);

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : schedule
				.getCourseToSlot().entrySet()) {

			CourseElementInstance course = entry.getKey();
			int roomIndex = entry.getValue().getRoomIndex();
			int timeSlotIndex = entry.getValue().getTimeSlotIndex();

			int maxTimeSlots = configuration.timeSlotsPerDay[0];
			int dayNumber = 0;

			while (timeSlotIndex > maxTimeSlots) {
				maxTimeSlots += configuration.timeSlotsPerDay[dayNumber];
				++dayNumber;
			}

			// 3. postcondition
			Assert.assertTrue(configuration.timeSlotsPerDay[dayNumber]
					- course.getDuration() >= 0);

			for (int i = 0; i < course.getDuration(); ++i) {

				TimeCourseSlot timeCourseSlot = schedule
						.getRoomTimeCourseSlotList().get(roomIndex)
						.getTimeCourseSlots().get(timeSlotIndex + i);

				// 2. postcondition
				Assert.assertTrue(timeCourseSlot.containsCourse(course));
			}

		}

	}
}
