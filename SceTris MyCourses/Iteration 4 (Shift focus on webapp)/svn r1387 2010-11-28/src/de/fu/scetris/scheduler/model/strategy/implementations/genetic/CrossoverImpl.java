/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.Iterator;
import java.util.Random;
import java.util.Map.Entry;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Crossover;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;

/**
 * 
 *
 */
public class CrossoverImpl implements Crossover {

	private float crossoverPoints;

	Random random;

	public CrossoverImpl(float crossoverPoints, Random random) {
		this.crossoverPoints = crossoverPoints;
		this.random = random;
	}

	/**
	 * Crossover takes two <code>Schedules</code> and produces a new offspring
	 * <code>Schedule</code> by mixing both their course allocations.
	 * <p>
	 * First the crossover points are generated in order to decide where to mix
	 * the <code>Schedules</code>. The crossover points will be represented by a
	 * boolean field with a length equal to the number of courses.
	 * <p>
	 * As both <code>Schedules</code> have the same number of courses their maps
	 * are equal in size. Therefore the crossover will happen by iterating over
	 * one of the schedules maps.
	 * <p>
	 * The first schedule to start with will be random. The
	 * <code>Schedules</code> map will be copied alternating from both partner
	 * <code>Schedules</code> to the new offspring. At the same time the course
	 * will be inserted duration times into the slots.
	 * <p>
	 * If a crosspoint is reached the <code>Schedule</code> from which the map
	 * is being copied is switched.
	 * 
	 * @throws DatabaseException
	 * @throws IllegalArgumentException
	 * 
	 * @see de.fu.scetris.scheduler.model.strategy.interfaces.Crossover#crossover(de.fu.scetris.scheduler.model.strategy.interfaces.Schedule,
	 *      de.fu.scetris.scheduler.model.strategy.interfaces.Schedule)
	 */
	@Override
	public Schedule crossover(final Schedule parent1, final Schedule parent2,
			Configuration config) throws IllegalArgumentException,
			DatabaseException {

		Schedule offspring = new ScheduleImpl(config);

		boolean hasCrossoverPoint[] = new boolean[config.numberOfCourses];
		int numberOfCrossoverPoints = (int) (crossoverPoints * config.numberOfCourses);

		for (int i = 0; i < numberOfCrossoverPoints; ++i) {

			while (true) {
				int point = random.nextInt(config.numberOfCourses);
				if (!hasCrossoverPoint[point]) {
					hasCrossoverPoint[point] = true;
					break;
				}
			}
		}

		Iterator<Entry<CourseElementInstance, RoomTimeIndex>> itParent1 = ((ScheduleImpl) parent1)
				.getCourseToSlot().entrySet().iterator();
		Iterator<Entry<CourseElementInstance, RoomTimeIndex>> itParent2 = ((ScheduleImpl) parent2)
				.getCourseToSlot().entrySet().iterator();

		// decides which parents mapping will be copied
		boolean turn = random.nextBoolean();

		// go through all courses
		for (int i = 0; i < config.numberOfCourses; ++i) {

			// it is necessary to always iterate over both maps, so no course is
			// left out or copied twice			
			Entry<CourseElementInstance, RoomTimeIndex> entryParent1 = itParent1
					.next();
			Entry<CourseElementInstance, RoomTimeIndex> entryParent2 = itParent2
					.next();

			// NOTE Probably better if this happens in if/else Statement
			CourseElementInstance courseParent1 = entryParent1.getKey();
			CourseElementInstance courseParent2 = entryParent2.getKey();
			RoomTimeIndex indicesParent1 = entryParent1.getValue();
			RoomTimeIndex indicesParent2 = entryParent2.getValue();

			// NOTE I'd prefer to have a "working copy" which is just a variable
			// referencing the map from which the courses are currently taken
			if (turn || !courseParent1.getSchedulableLesson()) {

				// enter course into the map representation of the allocation
				((ScheduleImpl) offspring).getCourseToSlot().put(courseParent1,
						indicesParent1);

				// enter course into the slot representation of the allocation
				for (int j = 0; j < courseParent1.getDuration(); ++j) {

					((ScheduleImpl) offspring)
							.putCourseInRoomTimeCourseSlotList(courseParent1,
									indicesParent1.getRoomIndex(),
									indicesParent1.getTimeSlotIndex() + j);
				}

			} else {

				// enter course into the map representation of the allocation
				((ScheduleImpl) offspring).getCourseToSlot().put(courseParent2,
						indicesParent2);
				// enter course into the slot representation of the allocation
				for (int j = 0; j < courseParent2.getDuration(); ++j) {

					((ScheduleImpl) offspring)
							.putCourseInRoomTimeCourseSlotList(courseParent2,
									indicesParent2.getRoomIndex(),
									indicesParent2.getTimeSlotIndex() + j);
				}
			}

			// the other parent will be used now
			if (hasCrossoverPoint[i]) {
				turn = !turn;
			}

		}

		return offspring;
	}

}
