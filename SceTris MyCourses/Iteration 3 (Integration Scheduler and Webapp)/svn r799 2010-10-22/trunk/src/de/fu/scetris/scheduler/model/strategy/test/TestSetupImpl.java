/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseElement;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.SetupImpl;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;
import de.fu.weave.orm.DatabaseException;

/**
 * Tests the functionalit of the initiliaze method.
 * 
 * @author Konrad Reiche, Hagen Mahnke
 * @since Iteration2
 * 
 */
public class TestSetupImpl {

	ScheduleImpl schedule;
	Configuration configuration;
	RelationManager relationManager = new RelationManager();
	List<CourseElementInstance> courseList;

	@Before
	public void initialize() {

		int[] timeSlotsPerDay = { 6, 6, 3 };
		int numberOfDays = 3;
		int numberOfCourses = 3;
		int numberOfTimeSlots = 30;
		int numberOfRooms = 2;

		List<Room> roomList = new ArrayList<Room>(2);
		Building building = relationManager.newBuilding(null);
		roomList.add(relationManager.newRoom(null, building));
		roomList.add(relationManager.newRoom(null, building));

		courseList = new ArrayList<CourseElementInstance>(3);

		AcademicTerm academicTerm = relationManager.newAcademicTerm(null, null,
				null, null, null);
		Department department = relationManager.newDepartment(null);
		Person programManager = relationManager.newPerson(null, null, null,
				null);
		Program program = relationManager.newProgram(academicTerm, department,
				programManager);

		Person mainLecturer = relationManager.newPerson(null, null, null, null);
		Course course = relationManager.newCourse(null);
		CourseInstance courseInstance = relationManager.newCourseInstance(
				program, course, null, null, mainLecturer);
		CourseElement courseElement = relationManager.newCourseElement(course,
				7);

		courseList.add(relationManager.newCourseElementInstance(courseInstance,
				courseElement, 2));
		courseList.add(relationManager.newCourseElementInstance(courseInstance,
				courseElement, 2));
		courseList.add(relationManager.newCourseElementInstance(courseInstance,
				courseElement, 3));

		configuration = new Configuration(numberOfTimeSlots, numberOfDays,
				timeSlotsPerDay, numberOfRooms, numberOfCourses, roomList,
				null, courseList, null, null);

	}

	/**
	 * Postcondition: Every course from the course list provided by the
	 * configuration was inserted into the map.
	 * 
	 * Postconditon: Every course occurs duration times in the time slots.
	 * 
	 * @throws DatabaseException
	 */
	@Test
	public void testInitialize() throws DatabaseException {

		schedule = new ScheduleImpl(configuration);
		Setup setup = new SetupImpl();
		setup.initialize(schedule, configuration);

		for (CourseElementInstance course : courseList) {
			Assert.assertTrue(schedule.getCourseToSlot().containsKey(course));

			int roomIndex = schedule.getCourseToSlot().get(course)
					.getRoomIndex();
			int timeSlotIndex = schedule.getCourseToSlot().get(course)
					.getTimeSlotIndex();
			for (int i = 0; i < course.getDuration(); ++i) {

				List<CourseElementInstance> courseList = schedule
						.getCourseListAt(roomIndex, timeSlotIndex + i);

				Assert.assertTrue(courseList.contains(course));
			}
		}
	}
}
