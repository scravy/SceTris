/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler;

import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 *
 */
public interface Algorithm {

	/**
	 * Starts the algorithm.
	 * 
	 * @throws DatabaseException
	 * @throws InterruptedException
	 * @throws Exception 
	 */
	public void start(boolean resume) throws SchedulingException, NotScheduleableException;

	/**
	 * Stops the algorithm.
	 * 
	 * @throws InterruptedException
	 */
	public void stop();

	public Schedule getBestSchedule();

	public int getGenerationNumber();
}
