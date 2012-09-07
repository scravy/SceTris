/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.Map.Entry;

import de.fu.bakery.orm.java.DatabaseException;
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
import de.fu.scetris.scheduler.model.strategy.interfaces.SoftFitnessCalculation;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;

/**
 * 
 * 
 * @author Julian Fleischer
 */
public class SoftFitnessCalculationImpl implements SoftFitnessCalculation {

	@Override
	public void calculateSoftFitness(final Schedule schedule,
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

						schedule.getScore().getCourseToRoomPreference().get(course)
								.put(coursePreferredRoom, true);
					} else {
						schedule.getScore().getCourseToRoomPreference().get(course)
								.put(coursePreferredRoom, false);
					}
				}
			}

			for (ElementInstancePrefersTimeslot coursePreferredTimeSlot : course
					.whereSubjectOfElementInstancePrefersTimeslot()) {

				if (coursePreferredTimeSlot.getPriority() < 100) {
					maximumScore += 1;

					int preferredTimeSlotIndex = config.timeSlotList
							.indexOf(coursePreferredTimeSlot.getTimeslot());

					if ((preferredTimeSlotIndex >= timeSlotIndex)
							&& (preferredTimeSlotIndex <= timeSlotIndex
									+ course.getDuration())) {
						scheduledScore += 1;

						schedule.getScore().getCourseToTimeSlotPreference().get(course)
								.put(coursePreferredTimeSlot, true);
					} else {
						schedule.getScore().getCourseToTimeSlotPreference().get(course)
								.put(coursePreferredTimeSlot, false);
					}

				}
			}

			for (ElementInstanceRequiresFeature courseRequiresFeature : course
					.whereSubjectOfElementInstanceRequiresFeature()) {

				Room room = ((ScheduleImpl) schedule)
						.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

				for (RoomProvidesFeature providedFeature : room
						.whereSubjectOfRoomProvidesFeature()) {

					boolean isFeatureProvided = false;

					int neededQuanity = courseRequiresFeature.getQuantityBetter();

					if (providedFeature.getFeature().equals(
																courseRequiresFeature.getFeature())
								&& (providedFeature.getQuantity() == neededQuanity)) {

						scheduledScore += 1;
						isFeatureProvided = true;
						break;
					}

					schedule.getScore().getCourseToFeatureRequirement().get(course)
							.put(courseRequiresFeature, isFeatureProvided);
				}

			}
		}

		for (Person lecturer : config.lecturerList) {

			for (PersonPrefersTimeslot prefersTimeSlot : lecturer
					.whereSubjectOfPersonPrefersTimeslot()) {

				boolean isPersonSatisfied = false;

				if (prefersTimeSlot.getPriority() < 100) {
					maximumScore += 1;

					int preferredTimeSlotIndex = prefersTimeSlot.getTimeslot()
							.id();

					for (CourseElementInstance course : lecturer
							.objectsOfPersonTakesPartInElementInstance()) {

						int timeSlotIndex = ((ScheduleImpl) schedule)
								.getCourseToSlot().get(course)
								.getTimeSlotIndex();

						if (timeSlotIndex == preferredTimeSlotIndex) {
							scheduledScore += 1;
							isPersonSatisfied = true;
							break;
						}
					}
				}

				schedule.getScore().getLecturerToTimeSlotPreference().get(lecturer)
						.put(prefersTimeSlot, isPersonSatisfied);
			}
		}

		for (Room room : config.roomList) {

			for (RoomPrefersTimeslot prefersTimeSlot : room
					.whereSubjectOfRoomPrefersTimeslot()) {

				boolean isRoomSatisfied = false;

				if (prefersTimeSlot.getPriority() < 100) {

					int preferredTimeSlot = prefersTimeSlot.getTimeslot()
							.id();

					for (RoomTimeCourseSlot roomTimeCourseSlot : ((ScheduleImpl) schedule)
							.getRoomTimeCourseSlotList()) {

						if (roomTimeCourseSlot.getRoom().equals(room)) {

							if (roomTimeCourseSlot.getTimeCourseSlots()
									.get(preferredTimeSlot).getCourseList()
									.size() > 0) {
								scheduledScore += 1;
								isRoomSatisfied = true;
								break;
							}
						}
					}
				}

				schedule.getScore().getRoomToTimeSlotPreference().get(room)
						.put(prefersTimeSlot, isRoomSatisfied);
			}
		}

		schedule.getScore().setNumberOfSoftConstraints(maximumScore);
		schedule.getScore().setNumberOfSoftConstraintsSatisfied(scheduledScore);

		double softFitness;
		if (maximumScore == 0) {
			softFitness = 1.0;
		} else {
			softFitness = ((double) scheduledScore / (double) maximumScore);
		}

		schedule.getScore().setSoftFitness(softFitness);

	}
}
