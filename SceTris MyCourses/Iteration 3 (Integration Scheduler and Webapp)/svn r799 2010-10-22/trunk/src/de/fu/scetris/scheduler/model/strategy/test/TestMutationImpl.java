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

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.*;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.MutationImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.TimeCourseSlot;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Konrad Reiche, Hagen Mahnke
 * @since Iteration2
 */
public class TestMutationImpl {

    ScheduleImpl schedule;
    Configuration configuration;

    List<CourseElementInstance> courseList;

    /**
     * 
     * @since Iteration2
     */
    @Before
    public void initialize() {

	RelationManager relationManager = new RelationManager(null);

	int[] TimeSlotsPerDay = { 6, 6, 3 };
	int numberOfDays = 3;
	int numberOfCourses = 3;
	int numberOfTimeSlots = 30;
	int numberOfRooms = 2;

	List<Room> roomList = new ArrayList<Room>(2);
	roomList.add(relationManager.newRoom(null, relationManager.newBuilding(null)));
	roomList.add(relationManager.newRoom(null, relationManager.newBuilding(null)));

	courseList = new ArrayList<CourseElementInstance>(3);

	AcademicTerm at = relationManager.newAcademicTerm(null, null, null, null, null);
	Department d = relationManager.newDepartment(null);
	Person pm = relationManager.newPerson(null, null, null, null);
	Program p = relationManager.newProgram(at, d, pm);
	Course c = relationManager.newCourse(null);
	
	CourseInstance ci = relationManager.newCourseInstance(p, null, null, pm);
	CourseElement ce = relationManager.newCourseElement(c, 0);
	
	courseList.add(relationManager.newCourseElementInstance(ci, ce, 2));
	courseList.add(relationManager.newCourseElementInstance(ci, ce, 2));
	courseList.add(relationManager.newCourseElementInstance(ci, ce, 3));

	configuration = new Configuration(numberOfTimeSlots, numberOfDays,
		TimeSlotsPerDay, numberOfRooms, numberOfCourses, roomList,
		null, courseList, null, null);

	schedule = new ScheduleImpl(configuration);

	schedule.getRoomTimeCourseSlotList().get(0).addCourse(0,
		courseList.get(0));
	schedule.getRoomTimeCourseSlotList().get(0).addCourse(1,
		courseList.get(0));

	schedule.getRoomTimeCourseSlotList().get(0).addCourse(2,
		courseList.get(1));
	schedule.getRoomTimeCourseSlotList().get(0).addCourse(3,
		courseList.get(1));

	schedule.getRoomTimeCourseSlotList().get(1).addCourse(12,
		courseList.get(2));
	schedule.getRoomTimeCourseSlotList().get(1).addCourse(13,
		courseList.get(2));
	schedule.getRoomTimeCourseSlotList().get(1).addCourse(14,
		courseList.get(2));

	schedule.getCourseToSlot().put(courseList.get(0),
		new RoomTimeIndex(0, 0));
	schedule.getCourseToSlot().put(courseList.get(1),
		new RoomTimeIndex(0, 2));
	schedule.getCourseToSlot().put(courseList.get(2),
		new RoomTimeIndex(1, 12));
    }

    /**
     * 
     * (1) Postcondition: The mutated schedule map has to have the same
     * CourseElementInstances as before.
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
     * @throws DatabaseException
     * 
     * @since Iteration2
     */
    @Test
    public void testMutate() throws DatabaseException {

	MutationImpl mutation = new MutationImpl(1.0f);

	Set<CourseElementInstance> courseSetOld = schedule.getCourseToSlot()
		.keySet();

	mutation.mutate(schedule, configuration);

	Set<CourseElementInstance> courseSetNew = schedule.getCourseToSlot()
		.keySet();

	// (1)
	Assert.assertEquals(courseSetOld, courseSetNew);

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

	    // (3)
	    Assert.assertTrue(configuration.timeSlotsPerDay[dayNumber]
		    - course.getDuration() >= 0);

	    for (int i = 0; i < course.getDuration(); ++i) {

		TimeCourseSlot timeCourseSlot = schedule
			.getRoomTimeCourseSlotList().get(roomIndex)
			.getTimeCourseSlots().get(timeSlotIndex + i);

		// (2)
		Assert.assertTrue(timeCourseSlot.containsCourse(course));
	    }

	}

    }
}
