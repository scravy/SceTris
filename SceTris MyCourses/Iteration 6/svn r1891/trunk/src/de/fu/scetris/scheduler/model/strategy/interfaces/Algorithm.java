/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.scheduler.model.data.NotScheduleableException;
import de.fu.scetris.scheduler.model.data.SchedulingException;

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
	public void stop() throws InterruptedException;

	public Schedule getBestSchedule() throws DatabaseException;

	public int getGenerationNumber();
}
