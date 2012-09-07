/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.Random;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.AbstractFactory;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateHardFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateSoftFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.Crossover;
import de.fu.scetris.scheduler.model.strategy.interfaces.Mutation;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;

/**
 * The Scheduler typically needs only one instance of a ConcreteFactory per
 * component family. So it is usually best implemented as a Singleton.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 */
public class GeneticFactory implements AbstractFactory {

	/**
	 * 
	 */
	private static GeneticFactory instance;

	public static GeneticFactory getInstance() {

		if (instance == null) {
			instance = new GeneticFactory();
		}
		return instance;
	}

	/**
	 * 
	 */
	private GeneticFactory() {

	}

	@Override
	public Algorithm createAlgorithm(final Configuration config,
			final AlgorithmConfiguration algoConfig) throws DatabaseException {

		long seed = new Random().nextLong();
		return createAlgorithm(config, algoConfig, seed);
	}

	@Override
	public Algorithm createAlgorithm(Configuration config,
			AlgorithmConfiguration algoConfig, long seed)
			throws DatabaseException {
		
		Random random = new Random(seed);

		return new AlgorithmImpl(createSchedule(config), createCrossover(
				config, algoConfig, random), createMutation(config, algoConfig,
				random), createCalculateHardFitness(config),
				createCalculateSoftFitness(config),
				createSetup(config, random), config, algoConfig, random);
	}

	@Override
	public CalculateHardFitness createCalculateHardFitness(
			final Configuration config) {
		return new CalculateHardFitnessImpl();
	}

	@Override
	public CalculateSoftFitness createCalculateSoftFitness(
			final Configuration config) {
		return new CalculateSoftFitnessImpl();
	}

	@Override
	public Crossover createCrossover(final Configuration config,
			final AlgorithmConfiguration algoConfig, Random random) {
		return new CrossoverImpl(algoConfig.getCrossoverPoints(), random);
	}

	@Override
	public Mutation createMutation(final Configuration config,
			final AlgorithmConfiguration algoConfig, Random random) {
		return new MutationImpl(algoConfig.getMutationSize(), random);
	}

	@Override
	public Schedule createSchedule(final Configuration config)
			throws DatabaseException {
		return new ScheduleImpl(config);
	}

	@Override
	public Setup createSetup(final Configuration config, Random random) {
		return new SetupImpl(random);
	}

}
