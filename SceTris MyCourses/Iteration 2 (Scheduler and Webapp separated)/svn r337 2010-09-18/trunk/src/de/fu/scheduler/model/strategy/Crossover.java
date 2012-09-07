package de.fu.scheduler.model.strategy;

public interface Crossover {

    /**
     * @param parent1 Any initialized schedule.
     * @param parent2 Any initialized schedule.
     * @return A new schedule offspring combining the schedules.
     */
    Schedule crossover(Schedule parent1, Schedule parent2);

}
