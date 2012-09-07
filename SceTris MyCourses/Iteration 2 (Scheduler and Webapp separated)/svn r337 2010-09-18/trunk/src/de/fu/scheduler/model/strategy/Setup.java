package de.fu.scheduler.model.strategy;

public interface Setup {

    /**
     * Initializes the schedule, allocating the courses.
     * 
     * @param schedule
     *            Any uninitialized schedule.
     */
    void initialize(Schedule schedule);

}
