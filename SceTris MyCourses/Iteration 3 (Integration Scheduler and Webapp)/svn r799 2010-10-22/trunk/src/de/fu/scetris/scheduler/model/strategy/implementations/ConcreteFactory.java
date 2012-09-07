/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

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
import de.fu.weave.orm.DatabaseException;

/**
 * The Scheduler typically needs only one instance of a ConcreteFactory per
 * component family. So it is usually best implemented as a Singleton.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 */
public class ConcreteFactory implements AbstractFactory {

    /**
	 * 
	 */
    private static ConcreteFactory instance;

    /**
     * TODO: Describe this item and its purpose
     * 
     * @return ?
     */
    public static ConcreteFactory getInstance() {

	if (instance == null) {
	    instance = new ConcreteFactory();
	}
	return instance;
    }

    /**
	 * 
	 */
    private ConcreteFactory() {

    }

    @Override
    public Algorithm createAlgorithm(Configuration config,
	    AlgorithmConfiguration algoConfig) throws DatabaseException {
	return new AlgorithmImpl(createSchedule(config), createCrossover(
		config, algoConfig), createMutation(config, algoConfig),
		createCalculateHardFitness(config),
		createCalculateSoftFitness(config), createSetup(config), config, algoConfig);
    }

    @Override
    public CalculateHardFitness createCalculateHardFitness(Configuration config) {
	return new CalculateHardFitnessImpl();
    }

    @Override
    public CalculateSoftFitness createCalculateSoftFitness(Configuration config) {
	return new CalculateSoftFitnessImpl();
    }

    @Override
    public Crossover createCrossover(Configuration config,
	    AlgorithmConfiguration algoConfig) {
	return new CrossoverImpl(algoConfig.getCrossoverPoints());
    }

    @Override
    public Mutation createMutation(Configuration config,
	    AlgorithmConfiguration algoConfig) {
	return new MutationImpl(algoConfig.getMutationSize());
    }

    @Override
    public Schedule createSchedule(Configuration config) throws DatabaseException {
	return new ScheduleImpl(config);
    }

    @Override
    public Setup createSetup(Configuration config) {
	return new SetupImpl();
    }
}
