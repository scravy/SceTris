/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstancePrefersRoom;
import de.fu.scetris.data.ElementInstancePrefersTimeslot;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonPrefersTimeslot;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomPrefersTimeslot;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;

/**
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 *         Schedule is the representation of one potential solution. A Schedule
 *         is valid solution if the HardFitness equals 1.0 . From the point of
 *         view of genetic algorithms a Schedule is a chromosome.
 */
public class ScheduleImpl implements Schedule, Comparable<Schedule> {

	private List<RoomTimeCourseSlot> roomTimeCourseSlotList;

	private TreeMap<CourseElementInstance, RoomTimeIndex> courseToSlot;

	private Map<CourseElementInstance, List<Boolean>> courseToConstraints;

	Map<CourseElementInstance, List<CourseElementInstance>> courseToRoomOverlap;
	Map<CourseElementInstance, List<Person>> courseToLecturerOverlap;
	Map<CourseElementInstance, Map<ElementInstancePrefersRoom, Boolean>> courseToRoomPreference;
	Map<CourseElementInstance, Map<ElementInstancePrefersTimeslot, Boolean>> courseToTimeSlotPreference;
	Map<CourseElementInstance, Map<ElementInstanceRequiresFeature, Boolean>> courseToFeatureRequirement;
	Map<Person, Map<PersonPrefersTimeslot, Boolean>> lecturerToTimeSlotPreference;
	Map<Room, Map<RoomPrefersTimeslot, Boolean>> roomToTimeSlotPreference;

	private double hardFitness;
	private double softFitness;

	private int totalSoftConstraintCount;
	private int SatisfiedSoftConstraintCount;
	private int totalHardConstraintCount;
	private int SatisfiedHardConstraintCount;

	final Configuration config;

	private void initiliazeConstraintMaps(Configuration config) {

		courseToRoomOverlap = new TreeMap<CourseElementInstance, List<CourseElementInstance>>();
		courseToLecturerOverlap = new TreeMap<CourseElementInstance, List<Person>>();
		courseToRoomPreference = new TreeMap<CourseElementInstance, Map<ElementInstancePrefersRoom, Boolean>>();
		courseToTimeSlotPreference = new TreeMap<CourseElementInstance, Map<ElementInstancePrefersTimeslot, Boolean>>();
		courseToFeatureRequirement = new TreeMap<CourseElementInstance, Map<ElementInstanceRequiresFeature, Boolean>>();
		lecturerToTimeSlotPreference = new TreeMap<Person, Map<PersonPrefersTimeslot, Boolean>>();
		roomToTimeSlotPreference = new TreeMap<Room, Map<RoomPrefersTimeslot, Boolean>>();

		for (CourseElementInstance course : config.courseList) {
			courseToRoomOverlap.put(course,
					new ArrayList<CourseElementInstance>());
			courseToLecturerOverlap.put(course, new ArrayList<Person>());
			courseToRoomPreference.put(course,
					new TreeMap<ElementInstancePrefersRoom, Boolean>());
			courseToTimeSlotPreference.put(course,
					new TreeMap<ElementInstancePrefersTimeslot, Boolean>());
			courseToFeatureRequirement.put(course,
					new TreeMap<ElementInstanceRequiresFeature, Boolean>());
		}

		for (Person lecturer : config.lecturerList) {
			lecturerToTimeSlotPreference.put(lecturer,
					new TreeMap<PersonPrefersTimeslot, Boolean>());
		}

		for (Room room : config.roomList) {
			roomToTimeSlotPreference.put(room,
					new TreeMap<RoomPrefersTimeslot, Boolean>());
		}
	}

	/**
	 * 
	 * @param config
	 * @throws DatabaseException
	 */
	public ScheduleImpl(final Configuration config) throws DatabaseException {

		initiliazeConstraintMaps(config);
		roomTimeCourseSlotList = initializeRoomTimeCourseSlotList(config);
		courseToSlot = new TreeMap<CourseElementInstance, RoomTimeIndex>();
		courseToConstraints = initializeCourseToConstraints(config.courseList);
		hardFitness = 0.0f;
		softFitness = 0.0f;
		this.config = config;
	}

	/**
	 * Schedules a course into the current schedule by a specified room and a
	 * specified time slot. The course is added to the map in order to get the
	 * starting time slot and is added as long as its duration is in the
	 * room-time-course slots.
	 * 
	 * @param course
	 *            the course to be added.
	 * @param roomIndex
	 *            the index of the room.
	 * @param timeSlotIndex
	 *            the index of the time slot.
	 */
	public void addCourse(final CourseElementInstance course,
			final int roomIndex, final int timeSlotIndex) {

		courseToSlot.put(course, new RoomTimeIndex(roomIndex, timeSlotIndex));

		for (int i = 0; i < course.getDuration(); ++i) {
			roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
					.get(timeSlotIndex + i).addCourse(course);
		}
	}

	@Override
	public int compareTo(final Schedule o) {

		if (hardFitness > o.getHardFitness()) {
			return 1;
		} else if (hardFitness < o.getHardFitness()) {
			return -1;
		} else {

			if (softFitness > o.getSoftFitness()) {
				return 1;
			} else if (softFitness < o.getSoftFitness()) {
				return -1;
			} else {
				return 0;
			}

		}
	}

	public ScheduleImpl copy() throws DatabaseException {
		return new ScheduleImpl(config);
	}

	public List<CourseElementInstance> getCourseListAt(final int roomIndex,
			final int timeSlotIndex) {

		return roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
				.get(timeSlotIndex).getCourseList();
	}

	/**
	 * @return the courseToConstraints
	 */
	public Map<CourseElementInstance, List<Boolean>> getCourseToConstraints() {
		return courseToConstraints;
	}

	/**
	 * @return the courseToSlot
	 */
	public TreeMap<CourseElementInstance, RoomTimeIndex> getCourseToSlot() {
		return courseToSlot;
	}

	/**
	 * @return the hardFitness
	 */
	@Override
	public double getHardFitness() {
		return hardFitness;
	}

	/**
	 * @return the roomTimeCourseSlotList
	 */
	public List<RoomTimeCourseSlot> getRoomTimeCourseSlotList() {
		return roomTimeCourseSlotList;
	}

	@Override
	public int getSatisfiedHardConstraintCount() {
		return SatisfiedHardConstraintCount;
	}

	@Override
	public int getSatisfiedSoftConstraintCount() {
		return SatisfiedSoftConstraintCount;
	}

	/**
	 * @return the softFitness
	 */
	@Override
	public double getSoftFitness() {
		return softFitness;
	}

	@Override
	public int getTotalHardConstraintCount() {
		return totalHardConstraintCount;
	}

	@Override
	public int getTotalSoftConstraintCount() {
		return totalSoftConstraintCount;
	}

	/**
	 * Initializes the time-space-slots of Schedule in respect to the current
	 * scheduled {@link CourseElementInstance}.
	 * 
	 * @throws DatabaseException
	 */
	public void initializeByCurrentScheduling() throws DatabaseException {

		for (CourseElementInstance courseElementInstance : config.courseList) {

			List<ElementInstanceTakesPlaceInRoom> takesPlaceInList = (List<ElementInstanceTakesPlaceInRoom>) courseElementInstance
					.whereSubjectOfElementInstanceTakesPlaceInRoom();

			if (takesPlaceInList.size() == 0) {
				continue;
			} else {
				Room room = takesPlaceInList.get(0).getRoom();
				int timeSlotIndex = config.timeSlotList
						.indexOf(courseElementInstance.getStartingTimeslot());
				int roomIndex = config.roomList.indexOf(room);
				addCourse(courseElementInstance, roomIndex, timeSlotIndex);
			}

		}

	}

	/**
	 * All courses are inserted and the size of the value list is defined by the
	 * number of constraints for a courseTable.
	 * 
	 * @param courseList
	 * 
	 * @return An initialized map of lists containing boolean which describe the
	 *         satisfaction of constraints per courseTable.
	 * @throws DatabaseException
	 */
	private TreeMap<CourseElementInstance, List<Boolean>> initializeCourseToConstraints(
			final List<CourseElementInstance> courseList)
			throws DatabaseException {

		TreeMap<CourseElementInstance, List<Boolean>> result = new TreeMap<CourseElementInstance, List<Boolean>>();

		for (CourseElementInstance course : courseList) {
			int n = 0;
			n += course.objectsOfElementInstancePrefersRoom().size();
			n += course.objectsOfElementInstancePrefersTimeslot().size();
			n += course.objectsOfElementInstanceRequiresFeature().size();

			List<Boolean> intermediateResult = new ArrayList<Boolean>();
			for (int i = 0; i < n; ++i) {
				intermediateResult.add(false);
			}

			result.put(course, intermediateResult);
		}

		return result;
	}

	/**
	 * 
	 * @param numberOfTimeSlots
	 * @return
	 */
	private List<RoomTimeCourseSlot> initializeRoomTimeCourseSlotList(
			final Configuration config) {

		List<RoomTimeCourseSlot> result = new ArrayList<RoomTimeCourseSlot>(
				config.numberOfRooms);

		for (Room room : config.roomList) {
			result.add(new RoomTimeCourseSlot(room, config.numberOfTimeSlots));
		}

		return result;
	}

	public String printSchedule() throws DatabaseException {

		String result = new String();
		int longestDay = 0;
		String dayHeader = "|   Monday  |  Tuesday  | Wednesday | Thursday  |  Friday   | Saturday  |  Sunday   |";
		String lineBreak = "+-------+";
		String border = "|       |";

		for (int i = 0; i < config.numberOfDays; ++i) {
			longestDay = Math.max(longestDay, config.timeSlotsPerDay[i]);
			lineBreak = lineBreak.concat("-----------+");
			border = border.concat("           |");
			dayHeader = dayHeader.substring(0, 12 * config.numberOfDays + 1);
		}

		dayHeader = dayHeader.concat("\n");
		lineBreak = lineBreak.concat("\n");
		border = border.concat("\n");

		int roomNumber = 1;
		int startingTime = 8;

		for (int i = 0; i < roomTimeCourseSlotList.size(); ++i) {

			result = result.concat(lineBreak);
			result = result.concat(border);
			new String();
			result = result.concat("| R" + String.format("%04d", roomNumber)
					+ " ");
			result = result.concat(dayHeader);
			result = result.concat(border);

			for (int j = 0; j < longestDay; ++j) {

				result = result.concat(lineBreak);
				result = result.concat(border);
				result = result.concat("| "
						+ String.format("%02d", startingTime) + "-"
						+ String.format("%02d", startingTime + 1) + " ");

				int offset = 0;

				for (int k = 0; k < config.numberOfDays; ++k) {

					if (config.timeSlotsPerDay[k] < j) {
						result = result.concat("|           ");
						continue;
					}

					int courseSize = getCourseListAt(roomNumber - 1, offset + j)
							.size();

					if (courseSize == 0) {
						result = result.concat("|           ");
					} else if (courseSize == 1) {
						result = result.concat("|   C"
								+ String.format(
										"%04d",
										getCourseListAt(roomNumber - 1,
												offset + j).get(0).id())
								+ "   ");
					} else {
						result = result.concat("| CONFLICT! ");
					}

					offset += config.timeSlotsPerDay[k];
				}

				result = result.concat("|\n");
				result = result.concat(border);

				startingTime += 1;
			}

			result = result.concat(lineBreak + "\n\n");

			++roomNumber;
			startingTime = 8;
		}

		return result;
	}

	public void putCourseInRoomTimeCourseSlotList(
			final CourseElementInstance course, final int roomIndex,
			final int timeSlotIndex) throws IllegalArgumentException {

		roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
				.get(timeSlotIndex).addCourse(course);
	}

	boolean removeAllCourseFromRoomTimeCourseSlotList(
			final CourseElementInstance course) throws DatabaseException {

		int roomIndex = courseToSlot.get(course).getRoomIndex();
		int timeIndex = courseToSlot.get(course).getTimeSlotIndex();
		int duration = course.getDuration();
		boolean hasRemovedAll = true;

		for (int i = timeIndex; i < timeIndex + duration; ++i) {
			hasRemovedAll &= roomTimeCourseSlotList.get(roomIndex)
					.getTimeCourseSlots().get(i).removeCourse(course);
		}

		return hasRemovedAll;
	}

	boolean removeCourseFromRoomTimeCourseSlotList(
			final CourseElementInstance course) {

		int roomIndex = courseToSlot.get(course).getRoomIndex();
		int timeIndex = courseToSlot.get(course).getTimeSlotIndex();

		return roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
				.get(timeIndex).removeCourse(course);
	}

	/**
	 * @param courseToConstraints
	 *            the courseToConstraints to set
	 */
	void setCourseToConstraints(
			final Map<CourseElementInstance, List<Boolean>> courseToConstraints) {
		this.courseToConstraints = courseToConstraints;
	}

	/**
	 * @param courseToSlot
	 *            the courseToSlot to set
	 */
	void setCourseToSlot(
			final TreeMap<CourseElementInstance, RoomTimeIndex> courseToSlot) {
		this.courseToSlot = courseToSlot;
	}

	/**
	 * @param hardFitness
	 *            the hardFitness to set
	 */
	void setHardFitness(final double hardFitness) {
		this.hardFitness = hardFitness;
	}

	/**
	 * @param roomTimeCourseSlotList
	 *            the roomTimeCourseSlotList to set
	 */
	void setRoomTimeCourseSlotList(
			final List<RoomTimeCourseSlot> roomTimeCourseSlotList) {
		this.roomTimeCourseSlotList = roomTimeCourseSlotList;
	}

	@Override
	public void setSatisfiedHardConstraintCount(
			final int satisfiedHardConstraintCount) {
		SatisfiedHardConstraintCount = satisfiedHardConstraintCount;
	}

	@Override
	public void setSatisfiedSoftConstraintCount(
			final int satisfiedSoftConstraintCount) {
		SatisfiedSoftConstraintCount = satisfiedSoftConstraintCount;
	}

	/**
	 * @param softFitness
	 *            the softFitness to set
	 */
	void setSoftFitness(final double softFitness) {
		this.softFitness = softFitness;
	}

	@Override
	public void setTotalHardConstraintCount(final int totalHardConstraintCount) {
		this.totalHardConstraintCount = totalHardConstraintCount;
	}

	@Override
	public void setTotalSoftConstraintCount(final int totalSoftConstraintCount) {
		this.totalSoftConstraintCount = totalSoftConstraintCount;
	}

	@Override
	public String toString() {

		String result = new String();

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : courseToSlot
				.entrySet()) {
			result = result.concat("C" + entry.getKey().id());
			result = result.concat(" -> Time Slot: ");
			result = result.concat(entry.getValue().getTimeSlotIndex()
					.toString());
			result = result.concat(" Room: ");
			result = result.concat(entry.getValue().getRoomIndex().toString());
			result = result.concat("\n");
		}

		result = result.concat("HardFitness: " + hardFitness + "\n");
		return result;
	}

}
