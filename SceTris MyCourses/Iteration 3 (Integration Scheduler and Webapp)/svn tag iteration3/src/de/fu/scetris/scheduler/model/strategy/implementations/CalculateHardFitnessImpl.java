/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.List;
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
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateHardFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.weave.orm.DatabaseException;

/**
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 */
public class CalculateHardFitnessImpl implements CalculateHardFitness {

	/**
	 * Calculates the hard fitness for a given schedule.
	 * <p>
	 * Hard fitness is the fitness that considers constraints which have to be
	 * fulfilled. If they are not fulfilled the courses cannot take place.
	 * <p>
	 * Rules of assignment:
	 * <p>
	 * (1) Each course has a scheduled score and a maximum score where the
	 * maximum score for each course consists of at least these 2 points: No
	 * overlapping in room allocation and no overlapping in lecturer allocation.
	 * The maximum score is increased by 1 for each constraint.
	 * <p>
	 * (2) If there is not more than 1 course in the same room at the same time:
	 * score + 1
	 * <p>
	 * (3) If the course lecturer has no other course at the same time: score +
	 * 1
	 * <p>
	 * (4) If any other constraint of a course is fulfilled: score + 1
	 * <p>
	 * (5) The total score is the sum of all course scores
	 * <p>
	 * (6) Hard fitness = scheduled scores / maximum scores
	 * <p>
	 * As the fitness value is defined by the position of the course it is
	 * calculated by iterating over the course entries of the
	 * <code>Schedule</code> map.
	 * <p>
	 * First rule (2) and (3) will be tested. Second the constraints of a course
	 * will be checked one after another. In each iteration step the maximum
	 * score and the schedule score will be saved and added to the final fitness
	 * at the end of an iteration step.
	 * 
	 * @throws DatabaseException
	 * 
	 * @see de.fu.scetris.scheduler.model.strategy.interfaces.CalculateHardFitness#calculateHardFitness(de.fu.scetris.scheduler.model.strategy.interfaces.Schedule)
	 */
	@Override
	public double calculateHardFitness(final Schedule schedule,
			Configuration config) throws DatabaseException {

		int scheduledScore = 0;
		int maximumScore = 0;

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) schedule)
				.getCourseToSlot().entrySet()) {

			maximumScore += 2;

			CourseElementInstance course = entry.getKey();
			int roomIndex = entry.getValue().getRoomIndex();
			int timeSlotIndex = entry.getValue().getTimeSlotIndex();

			// Check rule (2)
			boolean roomOverlap = false;
			for (int i = 0; i < course.getDuration(); ++i) {

				if (((ScheduleImpl) schedule).getCourseListAt(roomIndex,
						timeSlotIndex).size() > 1) {
					roomOverlap = true;
					break;
				}
			}
			if (!roomOverlap) {
				scheduledScore += 1;
			}

			// Check rule (3)
			boolean lecturerOverlap = false;
			for (int i = 0; i < config.numberOfRooms; ++i) {
				for (int j = 0; j < course.getDuration(); ++j) {

					List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
							.getCourseListAt(i, timeSlotIndex + j);

					for (CourseElementInstance checkedCourse : courseList) {
						if (!checkedCourse.equals(course)
								&& checkedCourse
										.getCourseInstance()
										.getMainLecturer()
										.equals(course.getCourseInstance()
												.getMainLecturer())) {

							lecturerOverlap = true;
							break;
						}
					}
				}
			}
			if (!lecturerOverlap) {
				scheduledScore += 1;
			}

			// Check rule (4)
			for (ElementInstancePrefersRoom coursePrefersRoom : course
					.whereSubjectOfElementInstancePrefersRoom()) {

				if (coursePrefersRoom.getPriority() == 100) {
					maximumScore += 1;

					Room preferredRoom = coursePrefersRoom.getRoom();
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

				if (coursePrefersTimeSlot.getPriority() == 100) {
					maximumScore += 1;

					int preferredTimeSlotIndex = config.timeSlotList.indexOf(coursePrefersTimeSlot.getTimeslot());
					
					if (preferredTimeSlotIndex >= timeSlotIndex
							&& preferredTimeSlotIndex <= timeSlotIndex
									+ course.getDuration()) {
						scheduledScore += 1;
					}
				}
			}

			for (ElementInstanceRequiresFeature courseRequiredFeature : course
					.whereSubjectOfElementInstanceRequiresFeature()) {

				maximumScore += 1;

				Room room = ((ScheduleImpl) schedule)
						.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

				for (RoomProvidesFeature providedFeature : room
						.whereSubjectOfRoomProvidesFeature()) {

					int neededQuanity;
					if (courseRequiredFeature.getPriority() == 100) {
						neededQuanity = courseRequiredFeature.getQuantityMin();
					} else {
						neededQuanity = courseRequiredFeature
								.getQuantityBetter();
					}

					if (providedFeature.getFeature().equals(
							courseRequiredFeature.getFeature())
							&& providedFeature.getQuantity() >= neededQuanity) {

						scheduledScore += 1;
						break;
					}
				}

			}
		}

		for (Person lecturer : config.lecturerList) {

			for (PersonPrefersTimeslot prefersTimeSlot : lecturer
					.whereSubjectOfPersonPrefersTimeslot()) {

				if (prefersTimeSlot.getPriority() == 100) {
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

			for (RoomPrefersTimeslot prefersTimeSlot : room
					.whereSubjectOfRoomPrefersTimeslot()) {

				if (prefersTimeSlot.getPriority() == 100) {

					int preferredTimeSlot = prefersTimeSlot.getTimeslot()
							.getId();

					for (RoomTimeCourseSlot roomTimeCourseSlot : ((ScheduleImpl) schedule)
							.getRoomTimeCourseSlotList()) {

						if (roomTimeCourseSlot.getRoom().equals(room)) {

							if (roomTimeCourseSlot.getTimeCourseSlots()
									.get(preferredTimeSlot).getCourseList()
									.size() > 0) {
								scheduledScore += 1;
								break;
							}

						}
					}
				}
			}
		}

		return ((double) scheduledScore / (double) maximumScore);
	}
}
