/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.scheduler.genetic.tests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.nio.charset.Charset;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fu.junction.xml.XmlHelper;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.CourseRecommendedForYear;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.genetic.AlgorithmImpl;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleImpl;
import de.fu.scetris.scheduler.manager.tests.SchedulerTest;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.util.Config;

public class AlgorithmImplTest extends SchedulerTest {

	AlgorithmImpl algorithm;
	AlgorithmConfiguration algorithmConfiguration;
	private Configuration configuration;

	@Before
	public void initialize() throws DatabaseException {
		Date lecturingStart = Date.valueOf("2010-10-18");
		Date lecturingEnd = Date.valueOf("2011-02-19");

		Department deparment = relationManager
				.createDepartment("Computer Science");

		Building building = relationManager.fullyCreateBuilding("HQ Computer Science",
				"Takustraße 9, 14195 Berlin", deparment);

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
						.createCourse("Mathematik für Informatiker I : Logik und Diskrete Mathematik"));
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
		roomList.add(relationManager.fullyCreateRoom("HS", "", building));
		roomList.add(relationManager.fullyCreateRoom("SR 046", "", building));

		List<Feature> featureList = new ArrayList<Feature>(0);
		List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();

		for (CourseElementInstance course : courseElementInstanceList)
			proposedSchedulingList.add(relationManager.newProposedScheduling(100, course));

		List<Timeslot> timeSlotList = relationManager.getTimeslot();
		
		List<CourseRecommendedForYear> courseRecommendForYearList = new ArrayList<CourseRecommendedForYear>();

		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(0), timeSlotList.get(2), 90);
		relationManager.createElementInstancePrefersTimeslot(
				courseElementInstanceList.get(0), timeSlotList.get(3), 90);

		configuration = new Configuration(roomList, personList.subList(1, 3),
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList, courseRecommendForYearList);

		algorithmConfiguration = new AlgorithmConfiguration(0.3f, 0.2f, 0.8f,
				0.5f, 50, 8, 100, 1.0);

		algorithm = (AlgorithmImpl) GeneticFactory.getInstance()
				.createAlgorithm(configuration, algorithmConfiguration,
						new Random().nextLong());
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

		String xmlConfig = "<configuration><database>\n" + "<user>scetris</user>"
				+ "<password>scetris</password>" + "<host>localhost</host>"
				+ "<name>scetris</name>" + "</database></configuration>\n";
		XmlHelper xmlHelper = new XmlHelper();
		Config conf = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		assertEquals("localhost", conf.get("host"));

		relationManager = new RelationManager(new ConnectionManagerImpl(conf));
		relationManager.connectionManager().connect();

		assertTrue(relationManager.connectionManager().validate());
		assertTrue(relationManager.install());
	}

	/**
	 * Postcondition: the algorithm terminated
	 * 
	 * Postcondition: size of the list of schedules equals populationSize
	 * 
	 * Postcondition: the best schedules hard fitness equals 1.0
	 * 
	 * Postcondition: the best schedules soft fitness equals 1.0
	 * 
	 * @throws Exception
	 */
	@Test
	public void testStart() throws Exception {

		algorithm.start(false);

		System.out.println(((ScheduleImpl) algorithm.getBestSchedule())
				.printSchedule());
		System.out.println(algorithm.getGenerationNumber());

		Assert.assertTrue(algorithm.getBestSchedule().getScore().getHardFitness() == 1.0f);
		Assert.assertTrue(algorithm.getBestSchedule().getScore().getSoftFitness() == 1.0f);
	}
}
