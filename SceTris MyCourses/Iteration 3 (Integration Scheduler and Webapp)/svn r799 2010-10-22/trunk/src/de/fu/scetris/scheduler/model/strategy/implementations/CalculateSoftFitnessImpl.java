/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.Map.Entry;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstancePrefersRoom;
import de.fu.scetris.data.ElementInstancePrefersTimeslot;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonPrefersTimeslot;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomPrefersTimeslot;
import de.fu.scetris.data.RoomProvidesFeature;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateSoftFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * 
 * @author Julian Fleischer
 */
public class CalculateSoftFitnessImpl implements CalculateSoftFitness {

    @Override
    public double calculateSoftFitness(final Schedule schedule,
	    final Configuration config) throws DatabaseException {

	int scheduledScore = 0;
	int maximumScore = 0;

	for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) schedule)
		.getCourseToSlot().entrySet()) {

	    CourseElementInstance course = entry.getKey();
	    int roomIndex = entry.getValue().getRoomIndex();
	    int timeSlotIndex = entry.getValue().getTimeSlotIndex();

	    // Check rule (4)
	    for (ElementInstancePrefersRoom coursePreferredRoom : course
		    .whereSubjectOfElementInstancePrefersRoom()) {

		if (coursePreferredRoom.getPriority() < 100) {
		    maximumScore += 1;

		    Room preferredRoom = coursePreferredRoom.getRoom();
		    Room actualRoom = ((ScheduleImpl) schedule)
			    .getRoomTimeCourseSlotList().get(roomIndex)
			    .getRoom();

		    if (preferredRoom.equals(actualRoom)) {
			scheduledScore += 1;
		    }
		}
	    }

	    for (ElementInstancePrefersTimeslot coursePrefersTimeSlot : course
		    .whereSubjectOfElementInstancePrefersTimeslot()) {

		if (coursePrefersTimeSlot.getPriority() < 100) {
		    maximumScore += 1;

		    int preferredTimeSlotIndex = coursePrefersTimeSlot
			    .getTimeslot().getId();

		    if (preferredTimeSlotIndex >= timeSlotIndex
			    && preferredTimeSlotIndex <= timeSlotIndex
				    + course.getDuration()) {
			scheduledScore += 1;
		    }

		}
	    }

	    for (ElementInstanceRequiresFeature courseRequiresFeature : course
		    .whereSubjectOfElementInstanceRequiresFeature()) {

		Room room = ((ScheduleImpl) schedule)
			.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

		for (RoomProvidesFeature providedFeature : room
			.whereSubjectOfRoomProvidesFeature()) {

		    int neededQuanity;
		    if (courseRequiresFeature.getPriority() < 100) {
			maximumScore += 1;
			neededQuanity = courseRequiresFeature
				.getQuantityBetter();

			if (providedFeature.getFeature().equals(
				courseRequiresFeature.getFeature())
				&& providedFeature.getQuantity() == neededQuanity) {

			    scheduledScore += 1;
			    break;
			}
		    }
		}

	    }
	}

	for (Person lecturer : config.lecturerList) {

	    for (PersonPrefersTimeslot prefersTimeSlot : lecturer
		    .whereSubjectOfPersonPrefersTimeslot()) {

		if (prefersTimeSlot.getPriority() < 100) {
		    maximumScore += 1;

		    int preferredTimeSlotIndex = prefersTimeSlot.getTimeslot()
			    .getId();

		    for (CourseElementInstance course : lecturer
			    .objectsOfPersonTakesPartInElementInstance()) {

			int timeSlotIndex = ((ScheduleImpl) schedule)
				.getCourseToSlot().get(course)
				.getTimeSlotIndex();

			if (timeSlotIndex == preferredTimeSlotIndex) {
			    scheduledScore += 1;
			    break;
			}
		    }

		}
	    }
	}

	for (Room room : config.roomList) {

	    for (RoomPrefersTimeslot prefersTimeSlot : room.whereSubjectOfRoomPrefersTimeslot()) {

		if (prefersTimeSlot.getPriority() < 100) {

		    int preferredTimeSlot = prefersTimeSlot.getTimeslot()
			    .getId();

		    for (RoomTimeCourseSlot roomTimeCourseSlot : ((ScheduleImpl) schedule)
			    .getRoomTimeCourseSlotList()) {

			if (roomTimeCourseSlot.getRoom().equals(room)) {

			    if (roomTimeCourseSlot.getTimeCourseSlots().get(
				    preferredTimeSlot).getCourseList().size() > 0) {
				scheduledScore += 1;
				break;
			    }

			}
		    }
		}
	    }
	}

	if (maximumScore == 0) {
	    return 1.0;
	} else {
	    return ((double) scheduledScore / (double) maximumScore);
	}

    }
}
