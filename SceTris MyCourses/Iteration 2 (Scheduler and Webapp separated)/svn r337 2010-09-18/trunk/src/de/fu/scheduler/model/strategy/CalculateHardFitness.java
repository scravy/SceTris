package de.fu.scheduler.model.strategy;

public interface CalculateHardFitness {

    /**
     * @param schedule
     *            Any initialized schedule.
     * @return A floating point number between 0.0 and 1.0 representing the
     *         fitness of the schedule concerning the hard constraints. Where
     *         0.0 is the worst and 1.0 the best.
     */
    double calculateHardFitness(Schedule schedule);

}
