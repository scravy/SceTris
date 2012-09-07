/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Mutation;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;

/**
 * 
 *
 */
public class MutationImpl implements Mutation {

	
	Random random;
	
	/**
	 * mutationSize indicates the percentage of courses being rescheduled. It is a floating point
	 * value between 0.0 and 1.0
	 */
	private final float mutationSize;
	
	
	public MutationImpl(final float mutationSize, Random random) {
		this.mutationSize = mutationSize;
		this.random = random;
	}

	/**
	 * Mutate moves the courses randomly to a new position.
	 * <p>
	 * Mutate randomly generates a sorted list of integers representing indices of map entries
	 * containing <code>mutationSize</code> and <code>numberOfCourses</code> numbers. Then Mutate
	 * iterates over the Entries of the Map selecting those entries that correspond to the generated
	 * indices. The next steps are performed on these Entries.
	 * <p>
	 * For the selected course two variables will be generated. The first variable is to determine
	 * the room where the course will take place. The second variable is to determine what the first
	 * time slot is when the course will take place.
	 * <p>
	 * The second variables is generated in two steps. First, the day is generated, because every
	 * day might have different numbers of time slots. Second, the actual starting time slot is
	 * generated by number of time slots - course duration.
	 * <p>
	 * With the courseToSlot map the chosen course is selected in the roomTimeCourseSlotList and
	 * deleted as often as the size of its duration. At the same time it is added to the new
	 * position as often as the size of its duration.
	 * <p>
	 * With the changed roomTimeCourseSlotList the courseToSlot map values are overwritten to the
	 * new starting index.
	 * 
	 * @throws DatabaseException
	 */
	@Override
	public void mutate(final Schedule schedule, final Configuration config)
			throws DatabaseException {

		ScheduleImpl scheduleImpl = (ScheduleImpl) schedule;
		int numberOfCoursesToMove = Math.round(mutationSize
				* config.numberOfCourses);
		
		List<Integer> indicesOfCoursesToMove = new ArrayList<Integer>();

		// TODO: Comment on this
		if (numberOfCoursesToMove == 0) {
			return;
		}

		for (int i = 0; i < numberOfCoursesToMove; ++i) {
			indicesOfCoursesToMove.add(random.nextInt(config.numberOfCourses));
		}
		Collections.sort(indicesOfCoursesToMove);

		Iterator<Entry<CourseElementInstance,RoomTimeIndex>> iterator = scheduleImpl
				.getCourseToSlot().entrySet().iterator();

		int i = 0;
		
		Entry<CourseElementInstance,RoomTimeIndex> entry = iterator.next();		
		for (Integer courseIndex : indicesOfCoursesToMove) {

			while (i < courseIndex) {
				++i;
				entry = iterator.next();
			}

			CourseElementInstance course = entry.getKey();
			
			if (!course.getSchedulableLesson()) {
				continue;
			}

			int roomIndex;
			int timeSlotIndex;

			/*
			 * These two variables and the next while statement are used to choose a timeSlot that
			 * guarantees that the Course will be scheduled in a continuous way.
			 */
			int day;
			int latestPossibleStartSlot;

			do {
				day = random.nextInt(config.numberOfDays);
				latestPossibleStartSlot = config.timeSlotsPerDay[day]
						- course.getDuration();
			} while (latestPossibleStartSlot < 0);

			roomIndex = random.nextInt(config.numberOfRooms);
			timeSlotIndex = random.nextInt(latestPossibleStartSlot + 1);

			for (int j = 0; j < day; ++j) {
				timeSlotIndex += config.timeSlotsPerDay[j];
			}

			/*
			 * Delete Courses at old position. Insert Courses at new position.
			 */
			scheduleImpl.removeAllCourseFromRoomTimeCourseSlotList(course);
			for (int j = 0; j < course.getDuration(); ++j) {
				scheduleImpl.putCourseInRoomTimeCourseSlotList(course,
															   roomIndex, timeSlotIndex + j);
			}

			scheduleImpl.getCourseToSlot().put(course,
											   new RoomTimeIndex(roomIndex, timeSlotIndex));
		}
	}

}
