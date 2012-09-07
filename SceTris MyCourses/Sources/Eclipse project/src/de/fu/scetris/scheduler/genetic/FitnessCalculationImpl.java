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
import de.fu.scetris.scheduler.FitnessCalculation;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

public class FitnessCalculationImpl implements FitnessCalculation {

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
	 * @throws SchedulingException
	 * 
	 * @throws DatabaseException
	 * 
	 * @see de.fu.scetris.scheduler.HardFitnessCalculation#calculateHardFitness(de.fu.scetris.scheduler.Schedule)
	 */
	@Override
	public void calculateHardFitness(Schedule schedule, ScheduleScore score) throws SchedulingException {

		int[] currentScore = { 0, 0 };
		ScheduleImpl scheduleImpl = (ScheduleImpl) schedule;
		scheduleImpl.getScore().clearConstraintMaps();

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) schedule)
				.getCourseToSlot().entrySet()) {

			CourseElementInstance course = entry.getKey();
			RoomTimeIndex roomTimeIndex = entry.getValue();

			try {
				checkRoomTimeConstraint(scheduleImpl, course, roomTimeIndex, currentScore);
				checkLecturerConstraint(scheduleImpl, course, roomTimeIndex, currentScore);

				checkFeaturesConstraint(scheduleImpl, course, roomTimeIndex, currentScore, false);

				checkCourseTimeSlotConstraints(scheduleImpl, course, roomTimeIndex, currentScore, false);
				checkCourseRoomConstraint(scheduleImpl, course, roomTimeIndex, currentScore, false);

				checkYearConstraints(scheduleImpl, course, roomTimeIndex, currentScore);
			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		for (Person lecturer : scheduleImpl.getConfiguration().lecturerList) {

			try {
				checkPersonTimeSlotConstraint(scheduleImpl, lecturer, currentScore, false);
			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		for (Room room : scheduleImpl.getConfiguration().roomList) {

			try {
				checkRoomTimeSlotConstraint(scheduleImpl, room, currentScore, false);
			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		schedule.getScore().setNumberOfHardConstraints(currentScore[1]);
		schedule.getScore().setNumberOfHardConstraintsSatisfied(currentScore[0]);

		schedule.getScore().setHardFitness(
				((double) currentScore[0] / (double) currentScore[1]));
	}

	public void checkRoomTimeSlotConstraint(ScheduleImpl schedule, Room room, int[] currentScore,
											 boolean checkSoftContraints) throws DatabaseException {

		Set<RoomPrefersTimeslot> violatedRoomTimeSlotPreferences = new HashSet<RoomPrefersTimeslot>();
		for (RoomPrefersTimeslot prefersTimeSlot : room
				.whereSubjectOfRoomPrefersTimeslot()) {

			boolean isRoomSatisfied = false;

			if (prefersTimeSlot.getPriority() == 100 && checkSoftContraints) {
				continue;
			}

			increaseMaximumScore(currentScore);

			int preferredTimeSlot = prefersTimeSlot.getTimeslot().id()-1;

			for (RoomTimeCourseSlot roomTimeCourseSlot : ((ScheduleImpl) schedule)
						.getRoomTimeCourseSlotList()) {

				if (roomTimeCourseSlot.getRoom().equals(room)) {

					if (roomTimeCourseSlot.getTimeCourseSlots()
								.get(preferredTimeSlot).getCourseList()
								.size() > 0) {
						increaseScheduledScore(currentScore);
						isRoomSatisfied = true;
						break;
					}
				}
			}

			if (!isRoomSatisfied) {
				violatedRoomTimeSlotPreferences.add(prefersTimeSlot);
			}
		}

		if (!violatedRoomTimeSlotPreferences.isEmpty()) {
			schedule.getScore().getRoomConflictsTimeSlotPreference()
					.put(room, violatedRoomTimeSlotPreferences);
		}
	}

	public void checkPersonTimeSlotConstraint(ScheduleImpl schedule, Person lecturer,
											   int[] currentScore, boolean checkSoftConstraints)
			throws DatabaseException {

		Set<PersonPrefersTimeslot> violatedPersonPrefersTimeslots = new HashSet<PersonPrefersTimeslot>();
		for (PersonPrefersTimeslot prefersTimeSlot : lecturer
				.whereSubjectOfPersonPrefersTimeslot()) {

			boolean isPersonSatisfied = false;

			if (prefersTimeSlot.getPriority() == 100 && checkSoftConstraints) {
				continue;
			}

			increaseMaximumScore(currentScore);

			int preferredTimeSlotIndex = prefersTimeSlot.getTimeslot()
						.id();

			List<CourseElementInstance> courses = new ArrayList<CourseElementInstance>();
			courses.addAll(schedule.getConfiguration().courseList);

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
						increaseScheduledScore(currentScore);
						isPersonSatisfied = true;
						break;
					}
				}
			}

			if (!isPersonSatisfied) {
				violatedPersonPrefersTimeslots.add(prefersTimeSlot);
			}
		}
		if (!violatedPersonPrefersTimeslots.isEmpty()) {
			schedule.getScore().getLecturerConflictsTimeSlotPreference()
					.put(lecturer, violatedPersonPrefersTimeslots);
		}

	}

	public void checkCourseRoomConstraint(ScheduleImpl schedule,
												  CourseElementInstance course,
												  RoomTimeIndex roomTimeIndex, int[] currentScore,
										  boolean checkSoftConstraints)
			throws DatabaseException {

		Set<ElementInstancePrefersRoom> violatedCourseRoomPreferences = new HashSet<ElementInstancePrefersRoom>();
		for (ElementInstancePrefersRoom coursePreferredRoom : course
				.whereSubjectOfElementInstancePrefersRoom()) {

			if (coursePreferredRoom.getPriority() == 100 & checkSoftConstraints) {
				continue;
			}

			increaseMaximumScore(currentScore);

			Room preferredRoom = coursePreferredRoom.getRoom();
			Room actualRoom = schedule.getRoomTimeCourseSlotList().get(roomTimeIndex.getRoomIndex())
						.getRoom();

			if (preferredRoom.equals(actualRoom)) {
				increaseScheduledScore(currentScore);
			} else {
				violatedCourseRoomPreferences.add(coursePreferredRoom);
			}

		}

		if (!violatedCourseRoomPreferences.isEmpty()) {
			schedule.getScore().getCourseConflictsRoomPreference()
					.put(course, violatedCourseRoomPreferences);
		}
	}

	public void checkFeaturesConstraint(ScheduleImpl schedule, CourseElementInstance course,
											 RoomTimeIndex roomTimeIndex, int[] currentScore,
										boolean checkSoftConstraints)
			throws DatabaseException {

		Set<ElementInstanceRequiresFeature> violatedCourseFeatureRequirements = new HashSet<ElementInstanceRequiresFeature>();
		for (ElementInstanceRequiresFeature courseRequiredFeature : course
				.whereSubjectOfElementInstanceRequiresFeature()) {

			increaseMaximumScore(currentScore);
			boolean isFeatureProvided = false;

			Room room = schedule.getRoomTimeCourseSlotList().get(roomTimeIndex.getRoomIndex()).getRoom();

			for (RoomProvidesFeature providedFeature : room
					.whereSubjectOfRoomProvidesFeature()) {

				int neededQuanity = courseRequiredFeature.getQuantityMin();
				if (checkSoftConstraints) {
					neededQuanity = courseRequiredFeature.getQuantityBetter();
				}

				if (providedFeature.getFeature().equals(
						courseRequiredFeature.getFeature())) {

					if (providedFeature.getQuantity() >= neededQuanity) {
						increaseScheduledScore(currentScore);
						isFeatureProvided = true;
						break;
					}
				}
			}
			if (!isFeatureProvided) {
				violatedCourseFeatureRequirements
						.add(courseRequiredFeature);
			}
		}

		if (!violatedCourseFeatureRequirements.isEmpty()) {
			schedule.getScore().getCourseConflictsFeatureRequirement()
					.put(course, violatedCourseFeatureRequirements);
		}
	}

	/**
	 * (2) If there is not more than 1 course in the same room at the same time:
	 * score + 1
	 * 
	 * @param currentScore
	 */
	public void checkRoomTimeConstraint(ScheduleImpl schedule, CourseElementInstance course,
												 RoomTimeIndex roomTimeIndex, int[] currentScore) {

		increaseMaximumScore(currentScore);
		boolean roomOverlap = false;
		Set<CourseElementInstance> roomOverlappingCourses = new TreeSet<CourseElementInstance>();

		for (int i = 0; i < course.getDuration(); ++i) {

			List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
					.getCourseListAt(roomTimeIndex.getRoomIndex(), roomTimeIndex.getTimeSlotIndex());

			if (courseList.size() > 1) {
				roomOverlappingCourses.addAll(courseList);
				roomOverlap = true;
			}
		}

		if (!roomOverlap) {
			increaseScheduledScore(currentScore);
		} else {
			// Room in which the conflict occurs
			Room room = schedule.getRoomTimeCourseSlotList().get(roomTimeIndex.getRoomIndex())
					.getRoom();
			// add courses which overlap with the course

			Set<CourseElementInstance> currentRoomOverlappingCourses = schedule.getScore()
					.getCourseConflictsRoom().get(room);

			if (currentRoomOverlappingCourses != null) {
				roomOverlappingCourses.addAll(currentRoomOverlappingCourses);
			}
			
			schedule.getScore().getCourseConflictsRoom()
						.put(room, roomOverlappingCourses);
		}
	}

	public void checkLecturerConstraint(ScheduleImpl schedule, CourseElementInstance course,
													RoomTimeIndex roomTimeIndex, int[] currentScore)
			throws DatabaseException {

		increaseMaximumScore(currentScore);
		boolean lecturerOverlap = false;
		Set<CourseElementInstance> lecturerOverlappingCourses = new TreeSet<CourseElementInstance>();

		for (int i = 0; i < schedule.getConfiguration().numberOfRooms; ++i) {
			for (int j = 0; j < course.getDuration(); ++j) {

				List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
						.getCourseListAt(i, roomTimeIndex.getTimeSlotIndex() + j);

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
			increaseScheduledScore(currentScore);
		} else {
			// add courses which overlap with a lecturer in time
			lecturerOverlappingCourses.add(course);
			schedule.getScore().getCourseConflictsLecturer()
					.put(course.getLecturer(), lecturerOverlappingCourses);
		}
	}

	public void checkYearConstraints(ScheduleImpl schedule, CourseElementInstance course,
											  RoomTimeIndex roomTimeIndex, int[] currentScore) {

		if (schedule.getConfiguration().courseToRecommendedYear.get(course) == null) {
			return;
		}

		increaseMaximumScore(currentScore);

		boolean isSatisfied = true;
		Set<CourseElementInstance> conflictedCourses = new TreeSet<CourseElementInstance>();
		Year year = schedule.getConfiguration().courseToRecommendedYear.get(course);

		for (int i = 0; i < course.getDuration(); ++i) {
			for (int j = 0; j < schedule.getConfiguration().roomList.size(); ++j) {

				List<CourseElementInstance> courseList = ((ScheduleImpl) schedule)
						.getCourseListAt(j, roomTimeIndex.getTimeSlotIndex()
								+ i);

				for (CourseElementInstance courseElementInstance : courseList) {
					Year assignedYear = schedule.getConfiguration().courseToRecommendedYear
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

		if (isSatisfied) {
			increaseScheduledScore(currentScore);
		} else {
			schedule.getScore().getCourseConflictsYearConstraint()
						.put(year, conflictedCourses);
		}
	}

	public void checkCourseTimeSlotConstraints(ScheduleImpl schedule,
															  CourseElementInstance course,
															  RoomTimeIndex roomTimeIndex,
															  int[] currentScore,
											   boolean checkSoftConstraints)
			throws DatabaseException {

		Set<ElementInstancePrefersTimeslot> violatedCourseTimeSlotPreferences = new HashSet<ElementInstancePrefersTimeslot>();
		for (ElementInstancePrefersTimeslot coursePrefersTimeSlot : course
				.whereSubjectOfElementInstancePrefersTimeslot()) {

			if (coursePrefersTimeSlot.getPriority() == 100 & checkSoftConstraints) {
				continue;
			}

			increaseMaximumScore(currentScore);

			int preferredTimeSlotIndex = schedule.getConfiguration().timeSlotList
						.indexOf(coursePrefersTimeSlot.getTimeslot());

			if ((preferredTimeSlotIndex >= roomTimeIndex.getTimeSlotIndex())
						&& (preferredTimeSlotIndex <= roomTimeIndex.getTimeSlotIndex()
								+ course.getDuration())) {
				increaseScheduledScore(currentScore);
			} else {
				violatedCourseTimeSlotPreferences
							.add(coursePrefersTimeSlot);
			}
		}

		if (!violatedCourseTimeSlotPreferences.isEmpty()) {
			schedule.getScore().getCourseConflictsTimeSlotPreference()
					.put(course, violatedCourseTimeSlotPreferences);
		}
	}

	@Override
	public void calculateSoftFitness(Schedule schedule, ScheduleScore score) throws SchedulingException {

		int[] currentScore = { 0, 0 };
		ScheduleImpl scheduleImpl = (ScheduleImpl) schedule;

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) schedule)
				.getCourseToSlot().entrySet()) {

			CourseElementInstance course = entry.getKey();
			RoomTimeIndex roomTimeIndex = entry.getValue();

			try {
				checkFeaturesConstraint(scheduleImpl, course, roomTimeIndex, currentScore, true);

				checkCourseTimeSlotConstraints(scheduleImpl, course, roomTimeIndex, currentScore, true);
				checkCourseRoomConstraint(scheduleImpl, course, roomTimeIndex, currentScore, true);

				checkYearConstraints(scheduleImpl, course, roomTimeIndex, currentScore);

			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		for (Person lecturer : scheduleImpl.getConfiguration().lecturerList) {

			try {
				checkPersonTimeSlotConstraint(scheduleImpl, lecturer, currentScore, true);
			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		for (Room room : scheduleImpl.getConfiguration().roomList) {

			try {
				checkRoomTimeSlotConstraint(scheduleImpl, room, currentScore, true);
			} catch (DatabaseException e) {
				throw new SchedulingException(e);
			}
		}

		schedule.getScore().setNumberOfSoftConstraints(currentScore[1]);
		schedule.getScore().setNumberOfSoftConstraintsSatisfied(currentScore[0]);

		if (currentScore[1] == 0) {
			schedule.getScore().setSoftFitness(1.0);
		} else {
			schedule.getScore().setSoftFitness(
					((double) currentScore[0] / (double) currentScore[1]));
		}
	}

	private void increaseScheduledScore(int[] currentScore) {
		++currentScore[0];
	}

	private void increaseMaximumScore(int[] currentScore) {
		++currentScore[1];
	}

}
