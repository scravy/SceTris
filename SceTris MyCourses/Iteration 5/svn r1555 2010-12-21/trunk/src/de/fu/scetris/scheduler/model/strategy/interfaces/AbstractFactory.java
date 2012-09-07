/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

import java.util.Random;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;

/**
 * AbstractFactory provides an interface for accessing components of the genetic algorithm.
 * Implement this interface to create a new strategy for the scheduler.
 * 
 * Algorithm uses the other components of the genetic algorithms. Algorithm gets an instance of
 * schedule as prototype in order to create new schedules.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 * @since Iteration2
 */
public interface AbstractFactory {

	public Algorithm createAlgorithm(Configuration config, AlgorithmConfiguration algoConfig, long seed) throws DatabaseException;
	
	public Algorithm createAlgorithm(Configuration config, AlgorithmConfiguration algoConfig) throws DatabaseException;

	public CalculateHardFitness createCalculateHardFitness(Configuration config);

	public CalculateSoftFitness createCalculateSoftFitness(Configuration config);

	public Crossover createCrossover(Configuration config, AlgorithmConfiguration algoConfig, Random random);

	public Mutation createMutation(Configuration config, AlgorithmConfiguration algoConfig, Random random);

	public Schedule createSchedule(Configuration config) throws DatabaseException;

	public Setup createSetup(Configuration config, Random random) throws DatabaseException;
}
