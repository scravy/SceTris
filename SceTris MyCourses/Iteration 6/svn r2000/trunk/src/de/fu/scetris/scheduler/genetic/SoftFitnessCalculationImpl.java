/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.genetic;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
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
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.SoftFitnessCalculation;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.weave.orm.DatabaseException;

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
			Set<ElementInstancePrefersRoom> violatedCourseRoomPreferences = new HashSet<ElementInstancePrefersRoom>();
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
					} else {
						violatedCourseRoomPreferences.add(coursePreferredRoom);
					}
				}
			}

			if (!violatedCourseRoomPreferences.isEmpty()) {
				schedule.getScore().getCourseToRoomPreference()
						.put(course, violatedCourseRoomPreferences);
			}

			Set<ElementInstancePrefersTimeslot> violatedCourseTimeSlotPreferences = new HashSet<ElementInstancePrefersTimeslot>();
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
					} else {
						violatedCourseTimeSlotPreferences
								.add(coursePreferredTimeSlot);
					}

				}
			}

			if (!violatedCourseTimeSlotPreferences.isEmpty()) {
				schedule.getScore().getCourseToTimeSlotPreference()
						.put(course, violatedCourseTimeSlotPreferences);
			}

			Set<ElementInstanceRequiresFeature> violatedCourseFeatureRequirements = new HashSet<ElementInstanceRequiresFeature>();
			for (ElementInstanceRequiresFeature courseRequiresFeature : course
					.whereSubjectOfElementInstanceRequiresFeature()) {

				maximumScore += 1;

				boolean isFeatureProvided = false;
				Room room = ((ScheduleImpl) schedule)
						.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

				for (RoomProvidesFeature providedFeature : room
						.whereSubjectOfRoomProvidesFeature()) {

					int neededQuanity = courseRequiresFeature
							.getQuantityBetter();

					if (providedFeature.getFeature().equals(
							courseRequiresFeature.getFeature())
							&& (providedFeature.getQuantity() >= neededQuanity)) {

						scheduledScore += 1;
						isFeatureProvided = true;
						break;
					}
				}

				if (!isFeatureProvided) {
					violatedCourseFeatureRequirements
							.add(courseRequiresFeature);
				}
			}

			if (!violatedCourseFeatureRequirements.isEmpty()) {
				schedule.getScore().getCourseToFeatureRequirement()
						.put(course, violatedCourseFeatureRequirements);
			}
		}

		for (Person lecturer : config.lecturerList) {

			Set<PersonPrefersTimeslot> violatedPersonPrefersTimeslots = new HashSet<PersonPrefersTimeslot>();
			for (PersonPrefersTimeslot prefersTimeSlot : lecturer
					.whereSubjectOfPersonPrefersTimeslot()) {

				boolean isPersonSatisfied = false;

				if (prefersTimeSlot.getPriority() < 100) {
					maximumScore += 1;

					int preferredTimeSlotIndex = prefersTimeSlot.getTimeslot()
							.id();

					List<CourseElementInstance> courses = new ArrayList<CourseElementInstance>();
					courses.addAll(config.courseList);

					CourseElementInstance c;
					for (Iterator<CourseElementInstance> it = courses
							.iterator(); it.hasNext();) {
						c = it.next();
						if (!c.getLecturer().equals(lecturer)) {
							it.remove();
						}
					}

					for (CourseElementInstance givenCourse : courses) {

						RoomTimeIndex roomTimeIndex = ((ScheduleImpl) schedule)
								.getCourseToSlot().get(givenCourse);

						if (roomTimeIndex != null) {
							int timeSlotIndexOfGivenCourse = roomTimeIndex
									.getTimeSlotIndex();

							if (timeSlotIndexOfGivenCourse == preferredTimeSlotIndex - 1) {
								scheduledScore += 1;
								isPersonSatisfied = true;
								break;
							}
						}
					}
				}

				if (!isPersonSatisfied) {
					violatedPersonPrefersTimeslots.add(prefersTimeSlot);
				}
			}
			if (!violatedPersonPrefersTimeslots.isEmpty()) {
				schedule.getScore().getLecturerToTimeSlotPreference()
						.put(lecturer, violatedPersonPrefersTimeslots);
			}
		}

		for (Room room : config.roomList) {

			Set<RoomPrefersTimeslot> violatedRoomTimeSlotPreferences = new HashSet<RoomPrefersTimeslot>();
			for (RoomPrefersTimeslot prefersTimeSlot : room
					.whereSubjectOfRoomPrefersTimeslot()) {

				boolean isRoomSatisfied = false;

				if (prefersTimeSlot.getPriority() < 100) {

					maximumScore += 1;
					
					int preferredTimeSlot = prefersTimeSlot.getTimeslot().id();

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
					if (!isRoomSatisfied) {
						violatedRoomTimeSlotPreferences.add(prefersTimeSlot);
					}
				}
			}

			if (!violatedRoomTimeSlotPreferences.isEmpty()) {
				schedule.getScore().getRoomToTimeSlotPreference()
						.put(room, violatedRoomTimeSlotPreferences);
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
