/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import de.fu.weave.orm.DatabaseException;

/**
 * 
 *
 */
public interface Algorithm {

	/**
	 * Starts the algorithm.
	 * @throws DatabaseException 
	 */
	public void start() throws DatabaseException;

	/**
	 * Stops the algorithm.
	 */
	public void stop();
	
	public Schedule getBestSchedule() throws DatabaseException;

}
