/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.genetic.tests;


import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

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
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.genetic.FitnessCalculationImpl;
import de.fu.scetris.scheduler.genetic.GeneticFactory;
import de.fu.scetris.scheduler.genetic.ScheduleImpl;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.scheduler.manager.tests.SchedulerTest;
import de.fu.weave.orm.DatabaseException;

public class GreedySetupImplTest extends SchedulerTest {

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

        // Feature
        List<Feature> featureList = new ArrayList<Feature>();
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

        // create Room provides Feature
        relationManager.createRoomProvidesFeature(roomList.get(0),
                featureList.get(0), 250);


        // Time Slot
        timeSlotList = relationManager.getTimeslot();

        // create Course Element Instance requires Feature constraints
        relationManager.createElementInstanceRequiresFeature(
                courseElementInstanceList.get(3), featureList.get(0), 200, 200);

        // create Course Element Instance prefers Time Slot and Room
        relationManager.createElementInstancePrefersRoom(
                courseElementInstanceList.get(0), roomList.get(0), 100);
        relationManager.createElementInstancePrefersTimeslot(
                courseElementInstanceList.get(0), timeSlotList.get(0), 100);

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

        List<CourseRecommendedForYear> courseRecommendedForYearList = new ArrayList<CourseRecommendedForYear>();

        configuration = new Configuration(roomList, personList,
                courseElementInstanceList, timeSlotList, featureList,
                proposedSchedulingList, courseRecommendedForYearList);
    }

    /**
     * @postcondition: every course from the course list provided by the configuration was inserted
     *                 into the map.
     * 
     * @postcondition: every course occurs duration times in the time slots.
     * 
     * @postcondition: every course is allocated in a room satisfying its feature constraints.
     * 
     * @postcondition: a course with room and time slot preference is actually scheduled in the
     *                 specified room at the specified time.
     * 
     * @postcondition: there are no gaps in the scheduled timetable because every course has the
     *                 duration of two time slots.
     * 
     * @postcondition: there must not be more then one course at the same time in the same room.
     * 
     * @postcondition: the hard and soft fitness of the initialized schedule is 1.0
     */
    @Test
    public void testInitialize() throws Exception {

        ScheduleImpl schedule = new ScheduleImpl(configuration);
        Setup setup = GeneticFactory.getInstance().createSetup(configuration,
                new Random());
        setup.initialize(schedule, configuration, false);

        for (CourseElementInstance course : configuration.courseList) {
            assertTrue(schedule.getCourseToSlot().containsKey(course));

            int roomIndex = schedule.getCourseToSlot().get(course)
                    .getRoomIndex();
            int timeSlotIndex = schedule.getCourseToSlot().get(course)
                    .getTimeSlotIndex();
            for (int i = 0; i < course.getDuration(); ++i) {

                List<CourseElementInstance> courseList = schedule
                        .getCourseListAt(roomIndex, timeSlotIndex + i);

                assertTrue(courseList.contains(course));
            }

            // @postcondition: every course is allocated in a room satisfying
            // its feature constraints.
            Room scheduledRoom = schedule.getRoomTimeCourseSlotList()
                    .get(roomIndex).getRoom();

            List<Feature> requiredFeatureList = course
                    .objectsOfElementInstanceRequiresFeature();

            List<Feature> providedFeatureList = scheduledRoom
                    .objectsOfRoomProvidesFeature();

            for (Feature requiredFeature : requiredFeatureList) {
                Assert.assertTrue(providedFeatureList.contains(requiredFeature));
            }
        }

        // @postcondition: a course with room and time slot preference is
        // actually scheduled in the specified room at the specified time.
        CourseElementInstance courseWithPreferences = courseElementInstanceList
                .get(0);
        Assert.assertTrue(schedule.getCourseListAt(0, 0).contains(
                courseWithPreferences));

        // @postcondition: there are no gaps in the scheduled timetable because
        // every course has the duration of two time slots.

        for (int i = 0; i < roomList.size(); ++i) {
            for (int j = 0; j < timeSlotList.size(); ++j) {

                // if the current time slot has no course scheduled but the next
                // time slot, which is not the time slot of the followed day,
                // has a course scheduled, then there is a gap
                if ((j != 59) && (schedule.getCourseListAt(i, j).size() == 0)
                        && (((j + 1) % 12) != 0)
                        && (schedule.getCourseListAt(i, j + 1).size() > 0)) {
                    Assert.assertTrue(false);
                    break;
                }

            }
        }

        new FitnessCalculationImpl().calculateHardFitness(schedule, schedule.getScore());
        new FitnessCalculationImpl().calculateSoftFitness(schedule, schedule.getScore());

        // @postcondition: there must not be more then one course at the same
        // time in the same room.
        for (Collection<CourseElementInstance> valueSet : schedule.getScore()
                .getCourseConflictsRoom().values()) {
            Assert.assertTrue(valueSet.isEmpty());
        }

        // @postcondition: the hard and soft fitness of the initialized schedule
        // is 1.0.
        Assert.assertEquals(1.0, schedule.getScore().getHardFitness());
        Assert.assertEquals(1.0, schedule.getScore().getSoftFitness());
    }
}
