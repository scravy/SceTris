/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.data;

import java.util.List;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;

/**
 * Configuration is set up when the data is read from the database.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * 
 */
public class Configuration {

	/**
	 * 
	 */
	public final int numberOfTimeSlots;

	/**
	 * 
	 */
	public final int numberOfDays;

	/**
	 * 
	 */
	public final int numberOfRooms;

	/**
	 * 
	 */
	public final int numberOfCourses;

	public final int[] timeSlotsPerDay;

	/**
	 * 
	 */
	public List<Room> roomList;

	/**
	 * 
	 */
	public List<Person> lecturerList;

	/**
	 * 
	 */
	public List<CourseElementInstance> courseList;

	/**
	 * 
	 */
	public List<Feature> featureList;

	/**
	 * 
	 */
	public List<ProposedScheduling> proposedSchedulingList;

	/**
	 * TODO: Check if entities are set, discussion: where to check this, scheduler or web interface?
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
	 */
	public Configuration(final int numberOfTimeSlots, final int numberOfDays,
			final int[] timeSlotsPerDay, final int numberOfRooms, final int numberOfCourses,
			final List<Room> roomList, final List<Person> lecturerList,
			final List<CourseElementInstance> courseList, final List<Feature> featureList,
			final List<ProposedScheduling> proposedSchedulingList) {

		this.numberOfTimeSlots = numberOfTimeSlots;
		this.numberOfDays = numberOfDays;
		this.timeSlotsPerDay = timeSlotsPerDay;
		this.numberOfRooms = numberOfRooms;
		this.numberOfCourses = numberOfCourses;
		this.roomList = roomList;
		this.lecturerList = lecturerList;
		this.courseList = courseList;
		this.featureList = featureList;
		this.proposedSchedulingList = proposedSchedulingList;
	}

}
