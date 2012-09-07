/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.Map.Entry;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.Room;
import de.fu.scetris.scheduler.model.strategy.implementations.CrossoverImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.TimeCourseSlot;

/**
 * 
 * @author Konrad Reiche, Hagen Mahnke
 * @since Iteration2
 */
public class TestCrossoverImpl {

    ScheduleImpl partner1;
    ScheduleImpl partner2;
    Configuration configuration;

    List<CourseElementInstance> courseList;

    /**
     * 
     * @since Iteration2
     */
    @Before
    public void initialize() {

	int[] timeSlotsPerDay = { 6, 6, 3 };
	int numberOfDays = 3;
	int numberOfCourses = 3;
	int numberOfTimeSlots = 30;
	int numberOfRooms = 2;

	List<Room> roomList = new ArrayList<Room>(2);
	roomList.add(new Room(1));
	roomList.add(new Room(2));

	courseList = new ArrayList<CourseElementInstance>(3);

	courseList.add(new CourseElementInstance(1, null, 2, false, null, null,
		null, null));
	courseList.add(new CourseElementInstance(2, null, 2, false, null, null,
		null, null));
	courseList.add(new CourseElementInstance(3, null, 3, false, null, null,
		null, null));

	Collections.sort(courseList);

	configuration = new Configuration(numberOfTimeSlots, numberOfDays,
		timeSlotsPerDay, numberOfRooms, numberOfCourses, roomList,
		null, courseList, null, null);

	partner1 = new ScheduleImpl(configuration);
	partner2 = new ScheduleImpl(configuration);

	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 0);
	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 1);

	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 2);
	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 3);

	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 0);
	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 1);
	partner1.putCourseInRoomTimeCourseSlotList(courseList.get(2), 1, 2);

	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 0);
	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(1), 0, 1);

	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 2);
	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(0), 0, 3);

	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(2), 0, 12);
	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(2), 0, 13);
	partner2.putCourseInRoomTimeCourseSlotList(courseList.get(2), 0, 14);

	partner1.getCourseToSlot().put(courseList.get(0),
		new RoomTimeIndex(0, 0));
	partner1.getCourseToSlot().put(courseList.get(1),
		new RoomTimeIndex(0, 2));
	partner1.getCourseToSlot().put(courseList.get(2),
		new RoomTimeIndex(1, 0));
	
	partner2.getCourseToSlot().put(courseList.get(0),
		new RoomTimeIndex(1, 0));
	partner2.getCourseToSlot().put(courseList.get(1),
		new RoomTimeIndex(0, 2));
	partner2.getCourseToSlot().put(courseList.get(2),
		new RoomTimeIndex(1, 12));
    }

    /**
     * 
     * (1) Postcondition: The offsprings schedule map has to have the same
     * CourseElementInstances.
     * 
     * (2) Postcondition: Every CourseElementInstance occurs duration times in
     * the time slots and do so in a continous way.
     * 
     * Example: The CourseElementInstance 'SampleCourse' has a duration of 4. It
     * will be scheduled in time slots 6,7,8,9. These time slots belong to the
     * same day
     * 
     * (3) Postcondition: There is no course scheduled which overlaps through
     * its duration in days.
     * 
     * @since Iteration2
     */
    @Test
    public void testCrossover() {

	CrossoverImpl crossover = new CrossoverImpl(0.5f);

	Set<CourseElementInstance> courseSetPartner1 = partner1
		.getCourseToSlot().keySet();

	ScheduleImpl offspring = (ScheduleImpl) crossover.crossover(partner1,
		partner2, configuration);

	Set<CourseElementInstance> courseSetOffspring = offspring
		.getCourseToSlot().keySet();

	// (1)
	Assert.assertEquals(courseSetPartner1, courseSetOffspring);

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

	    // (3)
	    Assert.assertTrue(configuration.timeSlotsPerDay[dayNumber]
		    - course.getDuration() >= 0);

	    for (int i = 0; i < course.getDuration(); ++i) {

		TimeCourseSlot timeCourseSlot = offspring
			.getRoomTimeCourseSlotList().get(roomIndex)
			.getTimeCourseSlots().get(timeSlotIndex + i);

		// (2)
		Assert.assertTrue(timeCourseSlot.containsCourse(course));
	    }

	}

    }
}
