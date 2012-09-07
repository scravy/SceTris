/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map.Entry;
import java.util.Random;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.Algorithm;
import de.fu.scetris.scheduler.Crossover;
import de.fu.scetris.scheduler.FitnessCalculation;
import de.fu.scetris.scheduler.Mutation;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

/**
 * Algorithm delegates its own thread life cycle to a ExecutorService
 */
public class AlgorithmImpl implements Algorithm {

	Schedule schedule;
	Crossover crossover;
	Mutation mutation;
	FitnessCalculation fitnessCalculation;
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

	AlgorithmImpl(final Schedule schedule, final Crossover crossover, final Mutation mutation,
			final FitnessCalculation fitnessCalculation, final Setup setup,
			final Configuration config, final AlgorithmConfiguration algoConfig,
			final Random random) {

		this.schedule = schedule;
		this.crossover = crossover;
		this.mutation = mutation;
		this.fitnessCalculation = fitnessCalculation;
		this.setup = setup;
		this.config = config;
		this.algoConfig = algoConfig;

		populationSize = algoConfig.getPopulationSize();
		replaceByGeneration = algoConfig.getReplaceByGeneration();
		trackBest = algoConfig.getTrackBest();
		crossoverProbability = algoConfig.getCrossoverProbability();
		mutationProbability = algoConfig.getMutationProbability();
		this.random = random;

		bestSchedules = new ArrayList<Schedule>();
		currentSchedules = new ArrayList<Schedule>(populationSize);
	}

	private void checkAndAddToBest(final Schedule schedule) throws DatabaseException {

		bestSchedules.add(schedule);
		Collections.sort(bestSchedules);

		while (bestSchedules.size() > trackBest)
			bestSchedules.remove(0);

		bestHardFitness = getBestSchedule().getScore().getHardFitness();
		bestSoftFitness = getBestSchedule().getScore().getSoftFitness();
	}

	@Override
	public Schedule getBestSchedule() {

		if (bestSchedules.size() == 0) return new ScheduleImpl(config);

		return bestSchedules.get(bestSchedules.size() - 1);
	}

	public List<Schedule> getBestSchedules() {
		return bestSchedules;
	}

	@Override
	public int getGenerationNumber() {
		return generationNumber;
	}

	public List<Schedule> getScheduleList() {
		return currentSchedules;
	}

	private void insertIntoCurrentSchedules(final Schedule offspring) {

		int dropOutIndex;
		Schedule dropOut;

		do {
			dropOutIndex = random.nextInt(populationSize);
			dropOut = currentSchedules.get(dropOutIndex);
		} while (bestSchedules.contains(dropOut));

		currentSchedules.set(dropOutIndex, offspring);

	}

	private boolean minimumRequirementsMet() throws DatabaseException {

		double minimumSoftFitness = algoConfig.getMinimumSoftFitness();
		Schedule bestSchedule = bestSchedules.get(bestSchedules.size() - 1);

		if ((bestSchedule.getScore().getHardFitness() == 1.0)
				&& (bestSchedule.getScore().getSoftFitness() >= minimumSoftFitness)) return true;
		else
			return false;
	}

	/**
	 * Updates for each <code>CourseElementInstance</code> its scheduled starting
	 * <code>TimeSlot</code>.
	 * 
	 * Iterates the schedule map and sets the corresponding time slot.
	 * 
	 * @throws DatabaseException
	 */
	private void setProposedScheduling() throws DatabaseException {

		ScheduleImpl bestSchedule = (ScheduleImpl) getBestSchedule();

		for (Entry<CourseElementInstance,RoomTimeIndex> entry : bestSchedule
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

	/**
	 * Algorithm initializes a population of schedules and calls crossover and mutation until a
	 * optimum <code>Schedule</code> is found.
	 * <p>
	 * First a list of <code>Schedules</code> is initialized which will be the population. The
	 * initialization is controlled by a chosen Setup strategy. The size of the list is based on the
	 * parameter population size.
	 * <p>
	 * The following process is done in a loop until an optimum schedule is found. The schedule is
	 * an optimum if all hard constraints and all soft constraints are satisfied.
	 * <p>
	 * (1) If the best schedule has hard and soft fitness equal to 1.0 an optimum schedule is found.
	 * <p>
	 * (2) A new list of schedules is initialized with the size of replacements per generation.
	 * <p>
	 * (3) For each slot in the replacement list a new schedule will be added.
	 * <p>
	 * (4) This schedule is formed by the offspring of a crossover operation on two random schedules
	 * from the schedule list. If the crossover probability was not big enough the first Schedule of
	 * both will be returned instead.
	 * <p>
	 * (5) Before the offspring is added the mutation operation will be called on the schedule. If
	 * the mutation probability is not big enough, the offspring will just be added.
	 * <p>
	 * (6) If the offspring is one of the best schedules it will replace an old schedule
	 * <p>
	 * (7) Step 1-6 is repeated until (1) becomes true.
	 * 
	 * @throws Exception
	 * 
	 * @see de.fu.scetris.scheduler.Algorithm#start()
	 */
	@Override
	public void start(final boolean resume) throws SchedulingException, NotScheduleableException {

		for (int i = 0; i < populationSize; ++i) {
			ScheduleImpl schedule;

			schedule = new ScheduleImpl(config);

			try {

				if (Thread.interrupted()) {
					setProposedScheduling();
					return;
				}

				if ((i == 0) && resume) setup.initialize(schedule, config, true);
				else
					setup.initialize(schedule, config, false);
			} catch (Exception e) {
				throw new SchedulingException(e);
			}

			fitnessCalculation.calculateHardFitness(schedule, schedule.getScore());
			fitnessCalculation.calculateSoftFitness(schedule, schedule.getScore());

			try {
				currentSchedules.add(schedule);
				checkAndAddToBest(schedule);

				if (minimumRequirementsMet() || Thread.currentThread().isInterrupted()) {
					System.out.println("Stopping the scheduler.");
					setProposedScheduling();
					return;
				}
			} catch (DatabaseException e) {
				throw new SchedulingException();
			}
		}
		try {

			while (!Thread.currentThread().isInterrupted()) {

				++generationNumber;
				if (minimumRequirementsMet()) break;

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
						fitnessCalculation.calculateHardFitness(offspring, offspring.getScore());
						fitnessCalculation.calculateSoftFitness(offspring, offspring.getScore());
						insertIntoCurrentSchedules(offspring);
						checkAndAddToBest(offspring);
					}
					// Mutate
					if (random.nextFloat() < mutationProbability) {
						bestSchedules.remove(parentOne);
						mutation.mutate(parentOne, config);
						fitnessCalculation.calculateHardFitness(parentOne, parentOne.getScore());
						fitnessCalculation.calculateSoftFitness(parentOne, parentOne.getScore());
						checkAndAddToBest(parentOne);
					}
				}
			}

			setProposedScheduling();

		} catch (DatabaseException e) {
			throw new SchedulingException(e);
		}
	}

	@Override
	public void stop() {

	}
}
