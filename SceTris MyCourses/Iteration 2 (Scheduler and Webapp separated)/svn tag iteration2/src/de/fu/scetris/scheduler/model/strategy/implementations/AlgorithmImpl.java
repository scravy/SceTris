/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateHardFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateSoftFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.Crossover;
import de.fu.scetris.scheduler.model.strategy.interfaces.Mutation;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;
import de.fu.weave.orm.DatabaseException;

/**
 *
 */
public class AlgorithmImpl implements Algorithm {

	Schedule schedule;
	Crossover crossover;
	Mutation mutation;
	CalculateHardFitness calculateHardFitness;
	CalculateSoftFitness calculateSoftFitness;
	Setup setup;

	Configuration config;
	AlgorithmConfiguration algoConfig;

	List<Schedule> bestSchedules;
	List<Schedule> currentSchedules;

	int currentGeneration;
	public double bestHardFitness;
	public double bestSoftFitness;

	int populationSize;
	int replaceByGeneration;
	int trackBest;

	float crossoverProbability;
	float mutationProbability;

	public AlgorithmImpl(Schedule schedule, Crossover crossover,
			Mutation mutation, CalculateHardFitness calculateHardFitness,
			CalculateSoftFitness calculateSoftFitness, Setup setup,
			Configuration config, AlgorithmConfiguration algoConfig) {

		this.schedule = schedule;
		this.crossover = crossover;
		this.mutation = mutation;
		this.calculateHardFitness = calculateHardFitness;
		this.calculateSoftFitness = calculateSoftFitness;
		this.setup = setup;
		this.config = config;
		this.algoConfig = algoConfig;

		this.populationSize = algoConfig.getPopulationSize();
		this.replaceByGeneration = algoConfig.getReplaceByGeneration();
		this.trackBest = algoConfig.getTrackBest();
		this.crossoverProbability = algoConfig.getCrossoverProbability();
		this.mutationProbability = algoConfig.getMutationProbability();

		bestSchedules = new ArrayList<Schedule>();
		currentSchedules = new ArrayList<Schedule>(populationSize);
	}

	/**
	 * Algorithm initializes a population of schedules and calls crossover and
	 * mutation until a optimum <code>Schedule</code> is found.
	 * <p>
	 * First a list of <code>Schedules</code> is initialized which will be the
	 * population. The initialization is controlled by a chosen Setup strategy.
	 * The size of the list is based on the parameter population size.
	 * <p>
	 * The following process is done in a loop until an optimum schedule is
	 * found. The schedule is an optimum if all hard constraints and all soft
	 * constraints are satisfied.
	 * <p>
	 * (1) If the best schedule has hard and soft fitness equal to 1.0 an
	 * optimum schedule is found.
	 * <p>
	 * (2) A new list of schedules is initialized with the size of replacements
	 * per generation.
	 * <p>
	 * (3) For each slot in the replacement list a new schedule will be added.
	 * <p>
	 * (4) This schedule is formed by the offspring of a crossover operation on
	 * two random schedules from the schedule list. If the crossover probability
	 * was not big enough the first Schedule of both will be returned instead.
	 * <p>
	 * (5) Before the offspring is added the mutation operation will be called
	 * on the schedule. If the mutation probability is not big enough, the
	 * offspring will just be added.
	 * <p>
	 * (6) If the offspring is one of the best schedules it will replace an old
	 * schedule
	 * <p>
	 * (7) Step 1-6 is repeated until (1) becomes true.
	 * 
	 * @throws DatabaseException
	 * 
	 * @see de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm#start()
	 */
	@Override
	public void start() throws DatabaseException {

		Random rnd = new Random();

		for (int i = 0; i < populationSize; ++i) {
			ScheduleImpl schedule = new ScheduleImpl(config);
			setup.initialize(schedule, config);
			schedule.setHardFitness(calculateHardFitness.calculateHardFitness(
					schedule, config));
			schedule.setSoftFitness(calculateSoftFitness.calculateSoftFitness(
					schedule, config));
			currentSchedules.add(schedule);
			checkAndAddToBest(schedule);
		}

		while (true) {
			if (minimumRequirementsMet()) {
				setStartingTimeSlots();
				break;
			}

			for (int i = 0; i < replaceByGeneration; ++i) {

				Schedule parentOne = currentSchedules.get(rnd
						.nextInt(populationSize));

				// CrossOver
				if (rnd.nextFloat() <= crossoverProbability) {
					Schedule parentTwo = currentSchedules.get(rnd
							.nextInt(populationSize));
					Schedule offspring = crossover.crossover(parentOne,
							parentTwo, config);
					((ScheduleImpl) offspring)
							.setHardFitness(calculateHardFitness
									.calculateHardFitness(offspring, config));
					((ScheduleImpl) offspring)
							.setSoftFitness(calculateSoftFitness
									.calculateSoftFitness(offspring, config));
					insertIntoCurrentSchedules(offspring);
					checkAndAddToBest(offspring);
				}

				// Mutate
				if (rnd.nextFloat() < mutationProbability) {
					bestSchedules.remove(parentOne);
					mutation.mutate(parentOne, config);
					((ScheduleImpl) parentOne)
							.setHardFitness(calculateHardFitness
									.calculateHardFitness(parentOne, config));
					((ScheduleImpl) parentOne)
							.setSoftFitness(calculateSoftFitness
									.calculateSoftFitness(parentOne, config));
					checkAndAddToBest(parentOne);
				}
			}

			++currentGeneration;
		}

	}

	/**
	 * Updates for each <code>CourseElementInstance</code> its scheduled
	 * starting <code>TimeSlot</code>.
	 * 
	 * Iterates the schedule map and sets the corresponding time slot.
	 * 
	 * @throws DatabaseException
	 */
	private void setStartingTimeSlots() throws DatabaseException {

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) getBestSchedule())
				.getCourseToSlot().entrySet()) {
			entry.getKey().setStartingTimeslot(config.timeSlotList.get(entry.getValue().timeSlotIndex));
			entry.getKey().pushChanges();
		}

	}

	private boolean minimumRequirementsMet() {

		double minimumSoftFitness = algoConfig.getMinimumSoftFitness();
		Schedule bestSchedule = bestSchedules.get(bestSchedules.size() - 1);

		if (bestSchedule.getHardFitness() == 1.0
				&& bestSchedule.getSoftFitness() >= minimumSoftFitness) {
			return true;
		} else {
			return false;
		}
	}

	private void insertIntoCurrentSchedules(Schedule offspring) {

		Random rnd = new Random();
		int dropOutIndex;
		Schedule dropOut;

		do {
			dropOutIndex = rnd.nextInt(populationSize);
			dropOut = currentSchedules.get(dropOutIndex);
		} while (bestSchedules.contains(dropOut));

		currentSchedules.set(dropOutIndex, offspring);

	}

	private void checkAndAddToBest(Schedule schedule) {

		bestSchedules.add(schedule);
		Collections.sort(bestSchedules);

		while (bestSchedules.size() > trackBest) {
			bestSchedules.remove(0);
		}

		bestHardFitness = bestSchedules.get(bestSchedules.size() - 1)
				.getHardFitness();
		bestSoftFitness = bestSchedules.get(bestSchedules.size() - 1)
				.getSoftFitness();

	}

	// NOTE does this stay a stub?
	@Override
	public void stop() {

	}

	public Schedule getBestSchedule() throws DatabaseException {

		if (bestSchedules.size() == 0) {
			return new ScheduleImpl(config);
		}

		return bestSchedules.get(bestSchedules.size() - 1);
	}

	public List<Schedule> getBestSchedules() {
		return bestSchedules;
	}

	public List<Schedule> getScheduleList() {
		return currentSchedules;
	}

	public int getCurrentGeneration() {
		return currentGeneration;
	}

}
