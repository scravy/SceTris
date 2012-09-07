/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.weave.orm.DatabaseException;

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

	private ScheduleScore score;
	final Configuration config;

	/**
	 * 
	 * @param configuration
	 * @throws DatabaseException
	 */
	public ScheduleImpl(final Configuration configuration) throws DatabaseException {

		roomTimeCourseSlotList = initializeRoomTimeCourseSlotList(configuration);
		courseToSlot = new TreeMap<CourseElementInstance, RoomTimeIndex>();
		score = new ScheduleScore(configuration);

		this.config = configuration;
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

		double hardFitness = getScore().getHardFitness();
		double softFitness = getScore().getSoftFitness();

		if (getScore().getHardFitness() > o.getScore().getHardFitness()) {
			return 1;
		} else if (hardFitness < o.getScore().getHardFitness()) {
			return -1;
		} else {

			if (softFitness > o.getScore().getSoftFitness()) {
				return 1;
			} else if (softFitness < o.getScore().getSoftFitness()) {
				return -1;
			} else {
				return 0;
			}

		}
	}

	public List<CourseElementInstance> getCourseListAt(final int roomIndex,
													   final int timeSlotIndex) {

		return roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
				.get(timeSlotIndex).getCourseList();
	}

	/**
	 * @return the courseToSlot
	 */
	public TreeMap<CourseElementInstance, RoomTimeIndex> getCourseToSlot() {
		return courseToSlot;
	}

	/**
	 * @return the roomTimeCourseSlotList
	 */
	public List<RoomTimeCourseSlot> getRoomTimeCourseSlotList() {
		return roomTimeCourseSlotList;
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
												  final CourseElementInstance course,
												  final int roomIndex,
												  final int timeSlotIndex)
			throws IllegalArgumentException {

		roomTimeCourseSlotList.get(roomIndex).getTimeCourseSlots()
				.get(timeSlotIndex).addCourse(course);
	}

	boolean removeAllCourseFromRoomTimeCourseSlotList(
													  final CourseElementInstance course)
			throws DatabaseException {

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
	 * @param courseToSlot
	 *            the courseToSlot to set
	 */
	void setCourseToSlot(
						 final TreeMap<CourseElementInstance, RoomTimeIndex> courseToSlot) {
		this.courseToSlot = courseToSlot;
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

		result = result.concat("HardFitness: " + getScore().getHardFitness() + "\n");
		return result;
	}

	@Override
	public ScheduleScore getScore() {
		return score;
	}

}
