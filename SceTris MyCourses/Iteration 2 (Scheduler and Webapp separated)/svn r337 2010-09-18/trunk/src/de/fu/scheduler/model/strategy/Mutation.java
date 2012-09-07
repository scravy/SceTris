package de.fu.scheduler.model.strategy;

public interface Mutation {

    /**
     * Changes the allocations of courses within the schedule.
     * 
     * @param schedule Any initialized schedule.
     */
    void mutate(Schedule schedule);
    
}
