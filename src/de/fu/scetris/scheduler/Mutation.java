/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler;

import de.fu.scetris.scheduler.data.Configuration;
import de.fu.weave.orm.DatabaseException;

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
