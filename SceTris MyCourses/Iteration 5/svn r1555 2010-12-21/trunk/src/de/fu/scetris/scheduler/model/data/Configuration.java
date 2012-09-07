/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.data;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;

/**
 * Configuration is set up when the data is read from the database.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 */
public class Configuration {

	/**
	 * Calculates an array of integers representing the time slots per day.
	 * 
	 * @param timeSlotList
	 *            a list of time slots.
	 * @return an array representing the time slots per day.
	 * @throws DatabaseException
	 */
	public static int[] countTimeSlotsPerDay(final List<Timeslot> timeslotList)
			throws DatabaseException {

		Collections.sort(timeslotList, new TimeSlotComparator());
		Map<Day, Integer> dayToNumberOfTimeSlots = new TreeMap<Day, Integer>();

		for (Timeslot timeslot : timeslotList) {
			Day day = timeslot.getDay();
			int numberOf = 0;
			if (dayToNumberOfTimeSlots.containsKey(day)) {
				numberOf = dayToNumberOfTimeSlots.get(day);
			}
			numberOf += 1;
			dayToNumberOfTimeSlots.put(day, numberOf);
		}
		int[] result = new int[dayToNumberOfTimeSlots.size()];
		int index = 0;
		for (Integer x : dayToNumberOfTimeSlots.values()) {
			result[index++] = x;
		}

		return result;
	}

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

	public Map<CourseElementInstance, ProposedScheduling> courseToProposal;

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

		numberOfCourses = courseList.size();
		numberOfRooms = roomList.size();
		numberOfTimeSlots = timeSlotList.size();
		timeSlotsPerDay = countTimeSlotsPerDay(timeSlotList);
		numberOfDays = timeSlotsPerDay.length;

		courseToProposal = new HashMap<CourseElementInstance, ProposedScheduling>();

		for (CourseElementInstance courseElementInstance : courseList) {

			for (ProposedScheduling proposedScheduling : proposedSchedulingList) {
				if (proposedScheduling.getElementInstance().equals(
						courseElementInstance)) {
					courseToProposal.put(courseElementInstance,
							proposedScheduling);
				}
			}

		}
	}
}
