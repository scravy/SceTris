/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.genetic;

import java.util.Random;


import de.fu.scetris.scheduler.AbstractFactory;
import de.fu.scetris.scheduler.Algorithm;
import de.fu.scetris.scheduler.Crossover;
import de.fu.scetris.scheduler.HardFitnessCalculation;
import de.fu.scetris.scheduler.Mutation;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.SoftFitnessCalculation;
import de.fu.scetris.scheduler.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.weave.orm.DatabaseException;

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
	public HardFitnessCalculation createCalculateHardFitness(
			final Configuration config) {
		return new HardFitnessCalculationImpl();
	}

	@Override
	public SoftFitnessCalculation createCalculateSoftFitness(
			final Configuration config) {
		return new SoftFitnessCalculationImpl();
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
	public Setup createSetup(final Configuration config, Random random) throws DatabaseException {
		return new GreedySetupImpl(random,config);
	}

}
