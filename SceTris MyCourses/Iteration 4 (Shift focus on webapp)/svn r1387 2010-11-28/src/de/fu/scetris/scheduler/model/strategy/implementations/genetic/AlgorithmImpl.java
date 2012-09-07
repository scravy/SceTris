/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateHardFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.CalculateSoftFitness;
import de.fu.scetris.scheduler.model.strategy.interfaces.Crossover;
import de.fu.scetris.scheduler.model.strategy.interfaces.Mutation;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;

/**
 * Algorithm delegates its own thread lifecycle to a ExecutorService
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

	int generationNumber;
	public double bestHardFitness;
	public double bestSoftFitness;

	int populationSize;
	int replaceByGeneration;
	int trackBest;

	float crossoverProbability;
	float mutationProbability;

	Random random;

	AlgorithmImpl(Schedule schedule, Crossover crossover, Mutation mutation,
			CalculateHardFitness calculateHardFitness,
			CalculateSoftFitness calculateSoftFitness, Setup setup,
			Configuration config, AlgorithmConfiguration algoConfig,
			Random random) {

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
		this.random = random;

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
	 * @throws InterruptedException
	 * 
	 * @see de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm#start()
	 */
	public void start() throws DatabaseException, InterruptedException {

		for (int i = 0; i < populationSize; ++i) {
			ScheduleImpl schedule = new ScheduleImpl(config);
			
			if (i == 0) {
				setup.initialize(schedule, config,true);				
			} else {
				setup.initialize(schedule, config,false);
			}
			
			schedule.setHardFitness(calculateHardFitness.calculateHardFitness(
					schedule, config));
			schedule.setSoftFitness(calculateSoftFitness.calculateSoftFitness(
					schedule, config));
			currentSchedules.add(schedule);
			checkAndAddToBest(schedule);
		}

		while (!Thread.currentThread().isInterrupted()) {

			++generationNumber;
			if (minimumRequirementsMet()) {
				break;
			}

			for (int i = 0; i < replaceByGeneration; ++i) {

				Schedule parentOne;
				while ((parentOne = currentSchedules.get(random
						.nextInt(populationSize))).equals(getBestSchedule())) {

				}

				// CrossOver
				if (random.nextFloat() <= crossoverProbability) {
					Schedule parentTwo = currentSchedules.get(random
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
				if (random.nextFloat() < mutationProbability) {
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
		}

		setProposedScheduling();
	}

	/**
	 * Updates for each <code>CourseElementInstance</code> its scheduled
	 * starting <code>TimeSlot</code>.
	 * 
	 * Iterates the schedule map and sets the corresponding time slot.
	 * 
	 * @throws DatabaseException
	 */
	private void setProposedScheduling() throws DatabaseException {

		ScheduleImpl bestSchedule = (ScheduleImpl) getBestSchedule();

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : bestSchedule
				.getCourseToSlot().entrySet()) {

			CourseElementInstance course = entry.getKey();
			ProposedScheduling proposedScheduling = config.courseToProposal
					.get(course);

			int roomIndex = entry.getValue().getRoomIndex();
			Room scheduledRoom = ((ScheduleImpl) schedule)
					.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

			proposedScheduling.setTimeslot(config.timeSlotList.get(entry
					.getValue().timeSlotIndex));

			proposedScheduling.setRoom(scheduledRoom);
			proposedScheduling.setCreatedBy(config.lecturerList.get(0));
			proposedScheduling.pushChanges();

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

		int dropOutIndex;
		Schedule dropOut;

		do {
			dropOutIndex = random.nextInt(populationSize);
			dropOut = currentSchedules.get(dropOutIndex);
		} while (bestSchedules.contains(dropOut));

		currentSchedules.set(dropOutIndex, offspring);

	}

	private void checkAndAddToBest(Schedule schedule) throws DatabaseException {

		bestSchedules.add(schedule);
		Collections.sort(bestSchedules);

		while (bestSchedules.size() > trackBest) {
			bestSchedules.remove(0);
		}

		bestHardFitness = getBestSchedule().getHardFitness();
		bestSoftFitness = getBestSchedule().getSoftFitness();
	}

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

	public int getGenerationNumber() {
		return generationNumber;
	}
}