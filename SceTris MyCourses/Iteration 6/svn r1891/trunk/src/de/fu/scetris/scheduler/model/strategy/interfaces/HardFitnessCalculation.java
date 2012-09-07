/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.scheduler.model.data.Configuration;

/**
 * 
 *
 */
public interface HardFitnessCalculation {

	/**
	 * @param schedule
	 *            Any initialized schedule.
	 * @return A floating point number between 0.0 and 1.0 representing the
	 *         fitness of the schedule concerning the hard constraints. Where
	 *         0.0 is the worst and 1.0 the best.
	 * @throws DatabaseException
	 */
	public void calculateHardFitness(Schedule schedule, Configuration config)
			throws DatabaseException;

}
