package de.fu.scheduler.model.strategy;

/**
 * @author Hagen Mahnke, Konrad Reiche
 * 
 *         AbstractFactory provides an interface for accessing components of the
 *         genetic algorithm. Implement this interface to create a new strategy
 *         for the scheduler.
 * 
 *         Algorithm uses the other components of the genetic algorithms.
 *         Algorithm gets an instance of schedule as prototype in order to
 *         create new schedules.
 * 
 */
public interface AbstractFactory {

    public Algorithm createAlgorithm(Schedule schedule, Crossover crossover,
	    Mutation mutation, CalculateHardFitness calculateHardFitness,
	    CalculateSoftFitness calculateSoftFitness, Setup setup);

    public Schedule createSchedule();

    public Crossover createCrossover();

    public Mutation createMutation();

    public CalculateHardFitness createCalculateHardFitness();

    public CalculateSoftFitness createCalculateSoftFitness();
    
    public Setup createSetup();
}
