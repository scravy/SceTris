/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 *
 */
public interface CalculateSoftFitness {

	/**
	 * @param schedule
	 *            Any initialized schedule.
	 * @return A floating point number between 0.0 and 1.0 representing the
	 *         fitness of the schedule concerning the soft constraints. Where
	 *         0.0 is the worst and 1.0 the best.
	 * @throws DatabaseException 
	 */
	public double calculateSoftFitness(Schedule schedule, Configuration config) throws DatabaseException;

}
