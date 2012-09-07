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
import java.util.Map.Entry;
import java.util.Set;
import java.util.TreeSet;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstancePrefersRoom;
import de.fu.scetris.data.ElementInstancePrefersTimeslot;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonPrefersTimeslot;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomPrefersTimeslot;
import de.fu.scetris.data.RoomProvidesFeature;
import de.fu.scetris.data.Year;
import de.fu.scetris.scheduler.HardFitnessCalculation;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.weave.orm.DatabaseException;

/**
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 */
public class HardFitnessCalculationImpl implements HardFitnessCalculation {

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
	 * @see de.fu.scetris.scheduler.HardFitnessCalculation#calculateHardFitness(de.fu.scetris.scheduler.Schedule)
	 */
	@Override
	public void calculateHardFitness(final Schedule schedule,
			final Configuration config) throws DatabaseException {

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
			Set<CourseElementInstance> roomOverlappingCourses = new TreeSet<CourseElementInstance>();

			for (int i = 0; i < course.getDuration(); ++i) {

				List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
						.getCourseListAt(roomIndex, timeSlotIndex);

				if (courseList.size() > 1) {
					roomOverlappingCourses.addAll(courseList);
					roomOverlap = true;
				}
			}

			if (!roomOverlap) {
				scheduledScore += 1;
			} else {
				// remove the course itself in order to show only the other
				// courses
				// which are in conflict
				roomOverlappingCourses.remove(course);
				// add courses which overlap with the course
				schedule.getScore().getCourseToRoomOverlap()
						.put(course, roomOverlappingCourses);
			}

			// Check rule (3)
			boolean lecturerOverlap = false;
			Set<CourseElementInstance> lecturerOverlappingCourses = new TreeSet<CourseElementInstance>();

			for (int i = 0; i < config.numberOfRooms; ++i) {
				for (int j = 0; j < course.getDuration(); ++j) {

					List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
							.getCourseListAt(i, timeSlotIndex + j);

					for (CourseElementInstance checkedCourse : courseList) {
						if (checkedCourse.getLecturer() != null
								&& !checkedCourse.equals(course)
								&& checkedCourse.getLecturer().equals(
										course.getLecturer())) {

							lecturerOverlap = true;
							lecturerOverlappingCourses.add(checkedCourse);
						}
					}
				}
			}
			if (!lecturerOverlap) {
				scheduledScore += 1;
			} else {
				// add courses which overlap with a lecturer in time
				lecturerOverlappingCourses.add(course);
				schedule.getScore().getlecturerToLecturerOverlap()
						.put(course.getLecturer(), lecturerOverlappingCourses);
			}

			// Check rule of year constraint
			if (config.courseToRecommendedYear.get(course) != null) {
				maximumScore += 1;

				if (yearConstraintIsSatisfied(course, entry.getValue(),
						schedule, config)) {
					scheduledScore += 1;
				}
			}

			// Check rule (4)
			Set<ElementInstancePrefersRoom> violatedCourseRoomPreferences = new HashSet<ElementInstancePrefersRoom>();
			for (ElementInstancePrefersRoom coursePreferredRoom : course
					.whereSubjectOfElementInstancePrefersRoom()) {

				if (coursePreferredRoom.getPriority() == 100) {
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
			for (ElementInstancePrefersTimeslot coursePrefersTimeSlot : course
					.whereSubjectOfElementInstancePrefersTimeslot()) {

				if (coursePrefersTimeSlot.getPriority() == 100) {
					maximumScore += 1;

					int preferredTimeSlotIndex = config.timeSlotList
							.indexOf(coursePrefersTimeSlot.getTimeslot());

					if ((preferredTimeSlotIndex >= timeSlotIndex)
							&& (preferredTimeSlotIndex <= timeSlotIndex
									+ course.getDuration())) {
						scheduledScore += 1;
					} else {
						violatedCourseTimeSlotPreferences
								.add(coursePrefersTimeSlot);
					}
				}
			}

			if (!violatedCourseTimeSlotPreferences.isEmpty()) {
				schedule.getScore().getCourseToTimeSlotPreference()
						.put(course, violatedCourseTimeSlotPreferences);
			}

			Set<ElementInstanceRequiresFeature> violatedCourseFeatureRequirements = new HashSet<ElementInstanceRequiresFeature>();
			for (ElementInstanceRequiresFeature courseRequiredFeature : course
					.whereSubjectOfElementInstanceRequiresFeature()) {

				maximumScore += 1;
				boolean isFeatureProvided = false;

				Room room = ((ScheduleImpl) schedule)
						.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

				for (RoomProvidesFeature providedFeature : room
						.whereSubjectOfRoomProvidesFeature()) {

					int neededQuanity = courseRequiredFeature.getQuantityMin();

					if (providedFeature.getFeature().equals(
							courseRequiredFeature.getFeature())) {

						if (providedFeature.getQuantity() >= neededQuanity) {
							scheduledScore += 1;
							isFeatureProvided = true;
							break;
						}
					}

					if (!isFeatureProvided) {
						violatedCourseFeatureRequirements
								.add(courseRequiredFeature);
					}

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

				if (prefersTimeSlot.getPriority() == 100) {
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

				if (prefersTimeSlot.getPriority() == 100) {

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
				}

				if (!isRoomSatisfied) {
					violatedRoomTimeSlotPreferences.add(prefersTimeSlot);
				}
			}

			if (!violatedRoomTimeSlotPreferences.isEmpty()) {
				schedule.getScore().getRoomToTimeSlotPreference()
						.put(room, violatedRoomTimeSlotPreferences);
			}
		}

		schedule.getScore().setNumberOfHardConstraints(maximumScore);
		schedule.getScore().setNumberOfHardConstraintsSatisfied(scheduledScore);

		schedule.getScore().setHardFitness(
				((double) scheduledScore / (double) maximumScore));
	}

	private boolean yearConstraintIsSatisfied(CourseElementInstance course,
			RoomTimeIndex roomTimeIndex, Schedule schedule,
			Configuration configuration) {

		boolean isSatisfied = true;
		Set<CourseElementInstance> conflictedCourses = new TreeSet<CourseElementInstance>();
		Year year = configuration.courseToRecommendedYear.get(course);

		for (int i = 0; i < course.getDuration(); ++i) {
			for (int j = 0; j < configuration.roomList.size(); ++j) {

				List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
						.getCourseListAt(j, roomTimeIndex.getTimeSlotIndex()
								+ i);

				for (CourseElementInstance courseElementInstance : courseList) {
					Year assignedYear = configuration.courseToRecommendedYear
							.get(courseElementInstance);
					if (!course.equals(courseElementInstance)
							&& assignedYear != null
							&& assignedYear.equals(year)) {

						conflictedCourses.add(course);
						conflictedCourses.add(courseElementInstance);
						isSatisfied = false;
					}
				}
			}
		}

		if (!isSatisfied) {
			schedule.getScore().getYearConstraintToConflictedCourses()
					.put(year, conflictedCourses);
		}

		return isSatisfied;
	}
}
