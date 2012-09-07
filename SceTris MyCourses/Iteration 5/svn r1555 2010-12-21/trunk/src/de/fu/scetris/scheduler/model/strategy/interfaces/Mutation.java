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
public interface Mutation {

    /**
     * 
     * Changes the allocations of courses within the schedule. The percentage of
     * courses being reallocated is set by the mutation size.
     * 
     * @param schedule
     *            any initialized schedule.
     * @param cfg
     *            any configuration with initialized roomTimeCourseSlotList and
     *            courseToSlot.
     * @throws DatabaseException 
     * 
     */
    public void mutate(Schedule schedule, Configuration cfg) throws DatabaseException;

}
