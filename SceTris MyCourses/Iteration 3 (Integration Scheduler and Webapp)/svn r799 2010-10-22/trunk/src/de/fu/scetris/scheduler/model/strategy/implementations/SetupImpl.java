/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.Random;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;
import de.fu.weave.orm.DatabaseException;

/**
 * @author Konrad Reiche
 * 
 */
public class SetupImpl implements Setup {

    /**
     * Allocates the given courses to randomly generated starting time slots.
     * <p>
     * Initialize generates a random starting time slot for each course in the
     * following way:
     * <p>
     * First the day of the course is generated from 0 to the number of
     * configured days. Then - based on the number of time slots per day and the
     * course duration - the time slot is generated. The latest possible
     * starting time slot for the course is going to be in the range of 0 and
     * time slots per day - course duration. Last the room is generated in a
     * range from 0 to the number of rooms.
     * <p>
     * It is, however, possible the day does not provide enough time slots. This
     * will occur when time slots per day - course duration < 0. A valid day is
     * searched within a loop until the day is found.
     * <p>
     * The course is then inserted as often as the size of its duration. This is
     * followed by an entry to the map with the starting time slot.
     * @throws DatabaseException 
     * 
     * @see de.fu.scetris.scheduler.model.strategy.interfaces.Setup#initialize(de.fu.scetris.scheduler.model.strategy.interfaces.Schedule)
     */
    @Override
    public void initialize(final Schedule schedule, final Configuration config) throws DatabaseException {

	Random rnd = new Random();

	for (CourseElementInstance course : config.courseList) {
	    int day;
	    int latestPossibleStartSlot;

	    // find a suitable day and the corresponding latest possible time slot 
	    do {
		day = rnd.nextInt(config.numberOfDays);
		latestPossibleStartSlot = config.timeSlotsPerDay[day]
			- course.getDuration();
	    } while (latestPossibleStartSlot < 0);

	    // choose a room
	    int roomIndex = rnd.nextInt(config.numberOfRooms);
	    // get the actual starting time slot relative to the day
	    int timeSlotIndex = rnd.nextInt(latestPossibleStartSlot + 1);

	    // and find the total position of that time slot
	    for (int i = 0; i < day; ++i) {
		timeSlotIndex += config.timeSlotsPerDay[i];
	    }

	    // set course in the time slots
	    for (int i = 0; i < course.getDuration(); ++i) {
		((ScheduleImpl) schedule).putCourseInRoomTimeCourseSlotList(
			course, roomIndex, timeSlotIndex + i);
	    }

	    // put course into the mapping
	    ((ScheduleImpl) schedule).getCourseToSlot().put(course,
		    new RoomTimeIndex(roomIndex, timeSlotIndex));
	}

    }
}
