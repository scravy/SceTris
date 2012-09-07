package de.fu.scetris.scheduler.genetic.tests;

import java.io.IOException;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
import de.fu.scetris.data.CourseRecommendedForYear;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.HardFitnessCalculation;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleImpl;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.scheduler.manager.tests.SchedulerTest;
import de.fu.weave.orm.DatabaseException;

public class CalculateHardFitnessTest extends SchedulerTest {

	SchedulerManager schedulerManager;

	Configuration configuration;

	List<Program> programList;
	List<CourseElementInstance> courseElementInstanceList;
	List<Timeslot> timeSlotList;
	List<Room> roomList;
	List<Day> dayList;
	List<CourseElement> courseElementList;
	List<CourseInstance> courseInstanceList;
	List<Person> personList;
	List<Feature> featureList;

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

		for (int i = 0; i < courseElementList.size(); ++i) {
			for (int j = 0; j < courseElementList.get(i).getDuration(); j += 2) {
				CourseElementInstance courseElementInstance = relationManager
						.createCourseElementInstance(courseInstanceList.get(i),
								courseElementList.get(i), 2);
				courseElementInstance.setSchedulableLesson(true);
				courseElementInstance.setLecturer(personList.get(i + 1));
				courseElementInstance.pushChanges();
				courseElementInstanceList.add(courseElementInstance);
			}
		}

		// Feature
		featureList = new ArrayList<Feature>();
		featureList.add(relationManager.fullyCreateFeature("Sitzplatz"));
		featureList.add(relationManager.fullyCreateFeature("Whiteboard"));
		featureList.add(relationManager.fullyCreateFeature("Beamer"));

		// Room
		roomList = new ArrayList<Room>();
		roomList.add(relationManager.fullyCreateRoom("HS", "000",
				buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "046",
				buildingList.get(0)));
		roomList.add(relationManager.fullyCreateRoom("HS A", "A",
				buildingList.get(1)));

		// Day
		dayList = new ArrayList<Day>(5);
		for (int i = 0; i < 5; ++i) {
			dayList.add(relationManager.createDay(weekdays[i]));
		}

		// Time Slot
		int j = 0;
		int h = 8;
		timeSlotList = new ArrayList<Timeslot>(60);
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

		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();
		List<CourseRecommendedForYear> courseRecommendedForYearList = new ArrayList<CourseRecommendedForYear>();

		configuration = new Configuration(roomList, personList,
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList, courseRecommendedForYearList);
	}

	@Test
	public void testBaseCase() throws DatabaseException {

		HardFitnessCalculation calc = GeneticFactory.getInstance()
				.createCalculateHardFitness(configuration);
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateHardFitness(schedule, configuration);

		Assert.assertEquals(2, schedule.getScore().getNumberOfHardConstraints());
		Assert.assertEquals(1.0, schedule.getScore().getHardFitness());

		for (Map<?, ?> constraintMap : schedule.getScore()
				.getAllViolatedConstraints()) {
			Assert.assertEquals(0, constraintMap.size());
		}

		schedule.addCourse(courseElementInstanceList.get(1), 0, 0);
		calc.calculateHardFitness(schedule, configuration);

		Assert.assertEquals(0.0, schedule.getScore().getHardFitness());
		Assert.assertEquals(4, schedule.getScore().getNumberOfHardConstraints());
		Assert.assertEquals(2, schedule.getScore().getCourseToRoomOverlap()
				.size());
		Assert.assertEquals(1, schedule.getScore()
				.getlecturerToLecturerOverlap().size());
		Assert.assertEquals(2, schedule.getScore()
				.getlecturerToLecturerOverlap().get(personList.get(1)).size());
	}

	@Test
	public void testFeatureRequirementCases() throws DatabaseException {

		HardFitnessCalculation calc = GeneticFactory.getInstance()
				.createCalculateHardFitness(configuration);
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createElementInstanceRequiresFeature(
				courseElementInstanceList.get(0), featureList.get(0), 50, 50);

		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateHardFitness(schedule, configuration);

		Assert.assertEquals(3, schedule.getScore().getNumberOfHardConstraints());
		Assert.assertEquals(2, schedule.getScore()
				.getNumberOfHardConstraintsSatisfied());
		
		relationManager.deleteElementInstanceRequiresFeature();
	}

	@Test
	public void testPersonTimeSlotPreferenceCases() throws DatabaseException {

		HardFitnessCalculation calc = GeneticFactory.getInstance()
				.createCalculateHardFitness(configuration);
		ScheduleImpl schedule = new ScheduleImpl(configuration);
		
		relationManager.createPersonPrefersTimeslot(personList.get(1), timeSlotList.get(0), 100);
		relationManager.createPersonPrefersTimeslot(personList.get(2), timeSlotList.get(0), 100);
		
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		schedule.addCourse(courseElementInstanceList.get(2), 1, 0);
		calc.calculateHardFitness(schedule, configuration);
		
		//Assert.assertEquals(2.0/6.0,schedule.getScore().getHardFitness());
		System.out.println(schedule.getScore().getNumberOfHardConstraints());
		System.out.println(schedule.getScore().getNumberOfHardConstraintsSatisfied());
		System.out.println(schedule.getScore().getLecturerToTimeSlotPreference());

	}

}
