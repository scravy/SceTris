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
public interface Crossover {

    /**
     * @param parent1
     *            any initialized schedule.
     * @param parent2
     *            any initialized schedule.
     * @param config
     *            any configuration.
     * @return A new schedule offspring combining the schedules.
     * @throws DatabaseException 
     * @throws IllegalArgumentException 
     */
    public Schedule crossover(Schedule parent1, Schedule parent2,
	    Configuration config) throws IllegalArgumentException, DatabaseException;

}
