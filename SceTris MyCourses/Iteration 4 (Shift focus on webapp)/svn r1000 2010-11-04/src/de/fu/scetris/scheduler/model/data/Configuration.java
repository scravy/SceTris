/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.data;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.TreeMap;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.weave.orm.DatabaseException;

/**
 * Configuration is set up when the data is read from the database.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 */
public class Configuration {

	public final int numberOfTimeSlots;
	public final int numberOfDays;
	public final int numberOfRooms;
	public final int numberOfCourses;

	public final int[] timeSlotsPerDay;
	public List<Room> roomList;
	public List<Person> lecturerList;
	public List<CourseElementInstance> courseList;
	public List<Timeslot> timeSlotList;
	public List<Feature> featureList;
	public List<ProposedScheduling> proposedSchedulingList;

	/**
	 * TODO: Check if entities are set, discussion: where to check this,
	 * scheduler or web interface?
	 * 
	 * @param numberOfTimeSlots
	 *            {@link #numberOfTimeSlots}
	 * @param numberOfDays
	 *            {@link #numberOfDays}
	 * @param numberOfRooms
	 *            {@link #numberOfRooms}
	 * @param numberOfCourses
	 *            {@link #numberOfCourses}
	 * @param roomList
	 *            {@link #roomList}
	 * @param lecturerList
	 *            {@link #lecturerList}
	 * @param courseList
	 *            {@link #courseList}
	 * @param featureList
	 *            {@link #featureList}
	 * @param proposedSchedulingList
	 *            {@link #proposedSchedulingList}
	 * @throws DatabaseException
	 */
	public Configuration(final List<Room> roomList,
			final List<Person> lecturerList,
			final List<CourseElementInstance> courseList,
			final List<Timeslot> timeSlotList, final List<Feature> featureList,
			final List<ProposedScheduling> proposedSchedulingList)
			throws DatabaseException {

		this.roomList = roomList;
		this.lecturerList = lecturerList;
		this.courseList = courseList;
		this.timeSlotList = timeSlotList;
		this.featureList = featureList;
		this.proposedSchedulingList = proposedSchedulingList;

		this.numberOfCourses = courseList.size();
		this.numberOfRooms = roomList.size();
		this.numberOfTimeSlots = timeSlotList.size();
		this.timeSlotsPerDay = countTimeSlotsPerDay(timeSlotList);
		this.numberOfDays = timeSlotsPerDay.length;
	}

	/**
	 * Calculates an array of integers representing the time slots per day.
	 * 
	 * @param timeSlotList
	 *            a list of time slots.
	 * @return an array representing the time slots per day.
	 * @throws DatabaseException
	 */
	public static int[] countTimeSlotsPerDay(List<Timeslot> timeSlotList)
			throws DatabaseException {

		Collections.sort(timeSlotList, new TimeSlotComparator());
		Map<Integer, Integer> dayToNumberOfTimeSlots = new TreeMap<Integer, Integer>();

		for (Timeslot timeSlot : timeSlotList) {

			int currentId = timeSlot.getDay().getId();

			if (dayToNumberOfTimeSlots.get(currentId) != null) {
				int n = dayToNumberOfTimeSlots.get(currentId);
				dayToNumberOfTimeSlots.put(currentId, n + 1);
			} else {
				dayToNumberOfTimeSlots.put(currentId, 1);
			}

		}

		int result[] = new int[dayToNumberOfTimeSlots.size()];
		int i = 0;
		for (Entry<Integer, Integer> entry : dayToNumberOfTimeSlots.entrySet()) {
			result[i] = entry.getValue();
			++i;
		}

		return result;
	}
}
