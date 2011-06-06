package de.fu.scetris.scheduler.genetic.tests;

import java.io.IOException;
import java.sql.Date;
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
import de.fu.scetris.scheduler.FitnessCalculation;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleImpl;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.scheduler.manager.tests.SchedulerTest;
import de.fu.weave.orm.DatabaseException;

public class CalculateSoftFitnessTest extends SchedulerTest {

	SchedulerManager schedulerManager;

	Configuration configuration;

	List<Program> programList;
	List<Course> courseList;
	List<CourseElementInstance> courseElementInstanceList;
	List<Timeslot> timeSlotList;
	List<Room> roomList;
	List<Day> dayList;
	List<CourseElement> courseElementList;
	List<CourseInstance> courseInstanceList;
	List<Person> personList;
	List<Feature> featureList;
	Year year;

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
		courseList = new ArrayList<Course>();
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

		// Year
		year = relationManager.createYear("Wintersemester 2010/2011");

		// Feature
		featureList = new ArrayList<Feature>();
		featureList.add(relationManager.createFeature("Sitzplatz"));
		featureList.add(relationManager.createFeature("Whiteboard"));
		featureList.add(relationManager.createFeature("Beamer"));

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

		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();
		List<CourseRecommendedForYear> courseRecommendedForYearList = new ArrayList<CourseRecommendedForYear>();

		configuration = new Configuration(roomList, personList,
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList, courseRecommendedForYearList);
	}

	@Test
	public void testCourseElementInstancePrefersRoom() throws DatabaseException, SchedulingException {

		FitnessCalculation calc = GeneticFactory.getInstance()
				.createFitnessCalculation();
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createElementInstancePrefersRoom(courseElementInstanceList.get(0),
				roomList.get(0), 30);

		schedule.addCourse(courseElementInstanceList.get(0), 1, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(0, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(1, schedule.getScore().getCourseConflictsRoomPreference().size());

		schedule = new ScheduleImpl(configuration);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(0, schedule.getScore().getCourseConflictsRoomPreference().size());

		relationManager.deleteElementInstancePrefersRoom();
	}

	@Test
	public void testCourseElementInstanceTimeSlotPrefersTimeSlots() throws SchedulingException,
			DatabaseException {

		FitnessCalculation calc = GeneticFactory.getInstance()
				.createFitnessCalculation();
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createElementInstancePrefersTimeslot(courseElementInstanceList.get(0),
				timeSlotList.get(0), 30);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 1);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(0, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(1, schedule.getScore().getCourseConflictsTimeSlotPreference().size());

		schedule = new ScheduleImpl(configuration);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(0, schedule.getScore().getCourseConflictsTimeSlotPreference().size());

		relationManager.deleteElementInstancePrefersTimeslot();
	}

	@Test
	public void testFeatureRequirementCases() throws DatabaseException, SchedulingException {

		FitnessCalculation calc = GeneticFactory.getInstance()
				.createFitnessCalculation();
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createElementInstanceRequiresFeature(
				courseElementInstanceList.get(0), featureList.get(0), 50, 100);

		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(0, schedule.getScore()
				.getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(1, schedule.getScore().getCourseConflictsFeatureRequirement().size());

		schedule = new ScheduleImpl(configuration);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);

		relationManager.createRoomProvidesFeature(roomList.get(0), featureList.get(0), 100);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(1, schedule.getScore()
				.getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(0, schedule.getScore().getCourseConflictsFeatureRequirement().size());

		relationManager.deleteElementInstanceRequiresFeature();
		relationManager.deleteRoomProvidesFeature();
	}

	@Test
	public void testPersonPrefersTimeSlot() throws SchedulingException, DatabaseException {

		FitnessCalculation calc = GeneticFactory.getInstance()
				.createFitnessCalculation();
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createPersonPrefersTimeslot(personList.get(1), timeSlotList.get(0), 30);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 1);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(0, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(1, schedule.getScore().getLecturerConflictsTimeSlotPreference().size());

		schedule = new ScheduleImpl(configuration);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(0, schedule.getScore().getLecturerConflictsTimeSlotPreference().size());

		relationManager.deletePersonPrefersTimeslot();
	}

	@Test
	public void testRoomPrefersTimeSlot() throws SchedulingException, DatabaseException {

		FitnessCalculation calc = GeneticFactory.getInstance()
				.createFitnessCalculation();
		ScheduleImpl schedule = new ScheduleImpl(configuration);

		relationManager.createRoomPrefersTimeslot(roomList.get(0), timeSlotList.get(0), 30);
		schedule.addCourse(courseElementInstanceList.get(0), 1, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(0, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(1, schedule.getScore().getRoomConflictsTimeSlotPreference().size());

		schedule = new ScheduleImpl(configuration);
		schedule.addCourse(courseElementInstanceList.get(0), 0, 0);
		calc.calculateSoftFitness(schedule, schedule.getScore());

		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraints());
		Assert.assertEquals(1, schedule.getScore().getNumberOfSoftConstraintsSatisfied());
		Assert.assertEquals(0, schedule.getScore().getRoomConflictsTimeSlotPreference().size());

		relationManager.deleteRoomPrefersTimeslot();
	}
}
