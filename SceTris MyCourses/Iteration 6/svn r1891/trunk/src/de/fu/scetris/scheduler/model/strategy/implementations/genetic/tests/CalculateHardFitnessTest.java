package de.fu.scetris.scheduler.model.strategy.implementations.genetic.tests;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Assert;
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
import de.fu.scetris.data.CourseRecommendedForYear;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.data.Year;
import de.fu.scetris.scheduler.controller.tests.SchedulerTest;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.ScheduleScore;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.HardFitnessCalculationImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.genetic.ScheduleImpl;

public class CalculateHardFitnessTest extends SchedulerTest {

	Configuration configuration;
	ScheduleImpl schedule;

	@Before
	public void initialize() throws DatabaseException,
			ParserConfigurationException, IOException, SAXException {

		// Miscellaneous
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");

		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		// Department, Building, Academic Term
		Department department = relationManager.createDepartment("Informatik");

		Building building = relationManager
				.fullyCreateBuilding("Informatik Hauptgebäude",
																"Takustraße 9, 14195 Berlin", department);

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
		personList.add(relationManager.createPerson("Frank", "Hoffmann", "fhoffmann",
				"12345"));

		// Program
		Program program = relationManager.createProgram(academicTerm,
												department, personList.get(0));

		// Course
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
		courseList
				.add(relationManager
						.createCourse("Mathematik für Informatiker III : Lineare Algebra"));

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

		courseInstanceList.add(relationManager.createCourseInstance(program, courseList.get(2),
				lecturingStart, lecturingEnd, personList.get(3)));
		courseInstanceList.add(relationManager.createCourseInstance(program, courseList.get(3),
				lecturingStart, lecturingEnd, personList.get(4)));

		// Course Element
		List<CourseElement> courseElementList = new ArrayList<CourseElement>();
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(0), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(1), 4));
		courseElementList.add(relationManager.createCourseElement(
																  courseList.get(2), 2));
		courseElementList.add(relationManager.createCourseElement(courseList.get(3), 4));

		// Course Element Instance
		List<CourseElementInstance> courseElementInstanceList = new ArrayList<CourseElementInstance>(
				8);
		for (int i = 0; i < courseElementList.get(0).getDuration(); i += 2) {
			courseElementInstanceList.add(relationManager
					.createCourseElementInstance(courseInstanceList.get(0),
							courseElementList.get(0), 2));
		}

		for (int i = 0; i < courseElementList.get(1).getDuration(); i += 2) {
			courseElementInstanceList.add(relationManager
					.createCourseElementInstance(courseInstanceList.get(1),
							courseElementList.get(1), 2));
		}

		for (int i = 0; i < courseElementList.get(2).getDuration(); i += 2) {
			courseElementInstanceList.add(relationManager
					.createCourseElementInstance(courseInstanceList.get(2),
							courseElementList.get(2), 2));
		}

		for (int i = 0; i < courseElementList.get(3).getDuration(); i += 2) {
			courseElementInstanceList.add(relationManager
					.createCourseElementInstance(courseInstanceList.get(3),
							courseElementList.get(3), 2));
		}

		// Room
		List<Room> roomList = new ArrayList<Room>(2);
		roomList.add(relationManager.fullyCreateRoom("HS", "000", building));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "046", building));

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

		// Feature
		List<Feature> featureList = new ArrayList<Feature>();

		// Proposed Scheduling
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();
		for (CourseElementInstance course : courseElementInstanceList) {
			proposedSchedulingList.add(relationManager.createProposedScheduling(100, course));
		}

		// Year
		Year Erstsemester = relationManager.createYear("1. Semester");
		Year Drittsemester = relationManager.createYear("3. Semester");

		// Course Recommended For Year
		List<CourseRecommendedForYear> courseRecommendForYearList = new ArrayList<CourseRecommendedForYear>();
		courseRecommendForYearList.add(relationManager.createCourseRecommendedForYear(courseList.get(0),
				Erstsemester));
		courseRecommendForYearList.add(relationManager.createCourseRecommendedForYear(courseList.get(1),
				Erstsemester));
		courseRecommendForYearList.add(relationManager.createCourseRecommendedForYear(courseList.get(2),
				Erstsemester));
		courseRecommendForYearList.add(relationManager.createCourseRecommendedForYear(courseList.get(3),
				Drittsemester));

		// Initialize Configuration and Schedule
		configuration = new Configuration(roomList,
				personList.subList(1, 4), courseElementInstanceList,
				timeSlotList, featureList, proposedSchedulingList, courseRecommendForYearList);

		schedule = new ScheduleImpl(configuration);

		// Schedule courses manually
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		schedule.addCourse(courseElementInstanceList.get(1), 1, 0);
		schedule.addCourse(courseElementInstanceList.get(2), 0, 2);
		schedule.addCourse(courseElementInstanceList.get(3), 0, 4);
		schedule.addCourse(courseElementInstanceList.get(4), 0, 6);
		schedule.addCourse(courseElementInstanceList.get(5), 1, 6);
		schedule.addCourse(courseElementInstanceList.get(6), 1, 8);
	}

	@Test
	public void testCalculateHardFitness() throws DatabaseException {

		HardFitnessCalculationImpl fitnessCalculation = new HardFitnessCalculationImpl();
		fitnessCalculation.calculateHardFitness(schedule, configuration);

		ScheduleScore score = schedule.getScore();

		Assert.assertEquals(21, score.getNumberOfHardConstraints());
		Assert.assertEquals(19, score.getNumberOfHardConstraintsSatisfied());

	}

}
