/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

/**
 * RoomTimeIndex is a pair of Integers representing the room and the time slot
 * chosen for a course. RoomTimeIndex is used as a value being mapped by
 * CourseElementInstance in order to find its position.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 * 
 */
public class RoomTimeIndex {

    /**
     * 
     * @since Iteration2
     */
    Integer roomIndex;
    /**
     * 
     * @since Iteration2
     */
    Integer timeSlotIndex;

    public RoomTimeIndex(Integer roomIndex, Integer timeSlotIndex) {
	this.roomIndex = roomIndex;
	this.timeSlotIndex = timeSlotIndex;
    }

    /**
     * @return the roomIndex
     */
    public Integer getRoomIndex() {
	return roomIndex;
    }

    /**
     * @return the timeSlotIndex
     */
    public Integer getTimeSlotIndex() {
	return timeSlotIndex;
    }
}
