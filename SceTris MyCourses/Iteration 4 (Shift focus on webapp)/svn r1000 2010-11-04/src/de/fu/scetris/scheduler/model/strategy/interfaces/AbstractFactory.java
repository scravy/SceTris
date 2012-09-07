/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.weave.orm.DatabaseException;

/**
 * AbstractFactory provides an interface for accessing components of the genetic algorithm.
 * Implement this interface to create a new strategy for the scheduler.
 * 
 * Algorithm uses the other components of the genetic algorithms. Algorithm gets an instance of
 * schedule as prototype in order to create new schedules.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 * @since Iteration2
 */
public interface AbstractFactory {

	public Algorithm createAlgorithm(Configuration config, AlgorithmConfiguration algoConfig) throws DatabaseException;

	public CalculateHardFitness createCalculateHardFitness(Configuration config);

	public CalculateSoftFitness createCalculateSoftFitness(Configuration config);

	public Crossover createCrossover(Configuration config, AlgorithmConfiguration algoConfig);

	public Mutation createMutation(Configuration config, AlgorithmConfiguration algoConfig);

	public Schedule createSchedule(Configuration config) throws DatabaseException;

	public Setup createSetup(Configuration config);
}
