package de.fu.scetris.scheduler.model.strategy.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.ElementInstanceRequiredFeature;
import de.fu.scetris.scheduler.model.data.Feature;
import de.fu.scetris.scheduler.model.data.Person;
import de.fu.scetris.scheduler.model.data.Room;
import de.fu.scetris.scheduler.model.data.RoomProvidedFeature;
import de.fu.scetris.scheduler.model.strategy.implementations.CalculateSoftFitnessImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;

public class TestCalculateSoftFitness {

    ScheduleImpl schedule;
    Configuration configuration;

    @Before
    public void initiliaze() {

	int[] timeSlotsPerDay = { 6, 6, 3 };
	int numberOfDays = 3;
	int numberOfCourses = 3;
	int numberOfTimeSlots = 30;
	int numberOfRooms = 2;

	Person lecturer1 = new Person(1);
	Person lecturer2 = new Person(2);
	
	List<Person> lecturerList = new ArrayList<Person>();
	lecturerList.add(lecturer1);
	lecturerList.add(lecturer2);
	
	Feature feature = new Feature(1, "Seat");

	List<Room> roomList = new ArrayList<Room>(2);
	Room room1 = new Room(1);
	Room room2 = new Room(2);

	List<RoomProvidedFeature> providedFeatureList = new ArrayList<RoomProvidedFeature>(
		3);
	RoomProvidedFeature providedFeature = new RoomProvidedFeature(room1,
		feature, 200);
	providedFeatureList.add(providedFeature);
	room1.setProvidedFeatureList(providedFeatureList);
	roomList.add(room1);
	roomList.add(room2);

	List<CourseElementInstance> courseList = new ArrayList<CourseElementInstance>(
		3);

	CourseElementInstance course = new CourseElementInstance(1, lecturer1, 2,
		false, null, null, null, null);

	courseList.add(course);
	courseList.add(new CourseElementInstance(2, lecturer2, 2, false, null, null,
		null, null));
	courseList.add(new CourseElementInstance(3, lecturer2, 3, false, null, null,
		null, null));

	ElementInstanceRequiredFeature requiredFeature = new ElementInstanceRequiredFeature(
		course, feature, 90, 180, 200);
	List<ElementInstanceRequiredFeature> requiredFeatureList = new ArrayList<ElementInstanceRequiredFeature>();
	requiredFeatureList.add(requiredFeature);
	
	course.setRequiredFeatureList(requiredFeatureList);
	
	configuration = new Configuration(numberOfTimeSlots,
		numberOfDays, timeSlotsPerDay, numberOfRooms, numberOfCourses,
		roomList, lecturerList, courseList, null, null);

	schedule = new ScheduleImpl(configuration);

	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 0);
	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 1);

	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 2);
	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 3);

	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 12);
	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 13);
	schedule.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 14);

	schedule.getCourseToSlot().put(courseList.get(0),
		new RoomTimeIndex(0, 0));
	schedule.getCourseToSlot().put(courseList.get(1),
		new RoomTimeIndex(0, 2));
	schedule.getCourseToSlot().put(courseList.get(2),
		new RoomTimeIndex(1, 12));
    }

    @Test
    public void testCalculateHardFitness() {

	CalculateSoftFitnessImpl fitnessCalculation = new CalculateSoftFitnessImpl();
	
	double fitness = fitnessCalculation.calculateSoftFitness(schedule,configuration);

	Assert.assertTrue(fitness == 1.0);
	
	
    }

}
