package de.fu.scheduler.model.strategy;

public interface CalculateSoftFitness {

    /**
     * @param schedule
     *            Any initialized schedule.
     * @return A floating point number between 0.0 and 1.0 representing the
     *         fitness of the schedule concerning the soft constraints. Where
     *         0.0 is the worst and 1.0 the best.
     */
    double calculateSoftFitness(Schedule schedule);
    
}
