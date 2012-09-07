/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.Set;

import org.junit.Before;
import org.junit.Test;

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
import de.fu.scetris.scheduler.model.strategy.implementations.CrossoverImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.SetupImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.TimeCourseSlot;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;
import de.fu.weave.impl.ymir.ConnectionManagerImpl;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.util.Config;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Konrad Reiche, Hagen Mahnke
 * @since Iteration2
 */
public class CrossoverImplTest {

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
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>(
				0);

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
	 * @postcondition: The offsprings schedule map has to have the same
	 * CourseElementInstances.
	 * 
	 * @postcondition: Every CourseElementInstance occurs duration times in
	 * the time slots and do so in a continuous way.
	 * 
	 * Example: A CourseElementInstance has a duration of 4. It
	 * will be scheduled in time slots 6,7,8,9. These time slots belong to the
	 * same day
	 * 
	 * @postcondition: There is no course scheduled which overlaps through
	 * its duration in days.
	 * 
	 * @throws DatabaseException
	 * @throws IllegalArgumentException
	 * 
	 * @since Iteration2
	 */
	@Test
	public void testCrossover() throws IllegalArgumentException,
			DatabaseException {

		ScheduleImpl partner1 = schedule.copy();
		ScheduleImpl partner2 = schedule.copy();
		
		Setup setup = new SetupImpl();
		setup.initialize(partner1, configuration);
		setup.initialize(partner2, configuration);
		
		CrossoverImpl crossover = new CrossoverImpl(0.5f);

		Set<CourseElementInstance> courseSetPartner1 = partner1
				.getCourseToSlot().keySet();

		ScheduleImpl offspring = (ScheduleImpl) crossover.crossover(partner1,
				partner2, configuration);

		Set<CourseElementInstance> courseSetOffspring = offspring
				.getCourseToSlot().keySet();

		// 1. postcondition
		assertEquals(courseSetPartner1, courseSetOffspring);

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : offspring
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
			assertTrue(configuration.timeSlotsPerDay[dayNumber]
					- course.getDuration() >= 0);

			for (int i = 0; i < course.getDuration(); ++i) {

				TimeCourseSlot timeCourseSlot = offspring
						.getRoomTimeCourseSlotList().get(roomIndex)
						.getTimeCourseSlots().get(timeSlotIndex + i);

				// 2. postcondition
				assertTrue(timeCourseSlot.containsCourse(course));
			}

		}

	}
}
