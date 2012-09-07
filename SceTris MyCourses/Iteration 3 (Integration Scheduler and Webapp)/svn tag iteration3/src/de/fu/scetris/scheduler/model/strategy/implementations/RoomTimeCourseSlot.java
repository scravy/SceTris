/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.ArrayList;
import java.util.List;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Room;

/**
 * RoomTimeCourseTable is a room with its timetable. The timetable is
 * represented by an array of time slots.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 * 
 */
public class RoomTimeCourseSlot {

    /**
     * 
     * @since Iteration2
     */
    Room room;
    /**
     * 
     * @since Iteration2
     */
    private List<TimeCourseSlot> timeCourseSlots;

    /**
     * @param room
     * @param timeTableSize
     * @since Iteration2
     */
    public RoomTimeCourseSlot(final Room room, final int numberOfTimeSlots) {
	this.room = room;
	this.timeCourseSlots = new ArrayList<TimeCourseSlot>(numberOfTimeSlots);
	for (int i = 0; i < numberOfTimeSlots; ++i){
	    timeCourseSlots.add(new TimeCourseSlot());
	}
    }
    
    public void addCourse(int index, CourseElementInstance course) {
	timeCourseSlots.get(index).addCourse(course);
    }

    /**
     * @return
     * @since Iteration2
     */
    public Room getRoom() {
	return room;
    }

    /**
     * @return
     * @since Iteration2
     */
    public List<TimeCourseSlot> getTimeCourseSlots() {
	return timeCourseSlots;
    }

}
