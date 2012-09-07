/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler;

import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

/**
 * @author Hagen Mahnke, Konrad Reiche
 * 
 *         Setup provides the method to initilize a <code>Schedule</code>.
 * 
 */
public interface Setup {

	/**
	 * Initializes the schedule with allocating the courses.
	 * 
	 * Initiliaze a <code>Schedule</code> where the <code>Schedule</code>
	 * constructor only provides an initilization where its fields initiliazed
	 * to empty ones.
	 * 
	 * Initiliaze the <code>Schedule</code> with allocating each given course to
	 * a time slot.
	 * 
	 * @param schedule
	 *            any uninitialized schedule.
	 * 
	 * @param configuration
	 *            any configuration with initialized roomTimeCourseSlotList and
	 *            courseToSlot.
	 * @throws NotScheduleableException 
	 * @throws DatabaseException
	 * @throws Exception
	 */
	public void initialize(Schedule schedule, Configuration config, boolean isResumedSchedule)
			throws SchedulingException, NotScheduleableException;

}
