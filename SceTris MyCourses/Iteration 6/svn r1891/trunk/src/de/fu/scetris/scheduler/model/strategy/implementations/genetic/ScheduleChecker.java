package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import java.util.Random;

import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.ScheduleScore;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;

/**
 * Scores the given Schedule in means of its hard fitness and soft fitness.
 * 
 * @author Konrad Reiche
 * 
 */
public class ScheduleChecker {

	Random random;

	public ScheduleChecker() {
		this(new Random());
	}

	public ScheduleChecker(Random random) {
		this.random = random;
	}

	public ScheduleScore check(Configuration configuration) throws Exception {

		Setup setup = new GreedySetupImpl(random, configuration);
		Schedule schedule = new ScheduleImpl(configuration);
		setup.initialize(schedule, configuration, true);

		new HardFitnessCalculationImpl().calculateHardFitness(schedule, configuration);
		new SoftFitnessCalculationImpl().calculateSoftFitness(schedule, configuration);

		int numberOfHardConstraints = schedule.getScore().getNumberOfHardConstraints();
		int numberOfHardConstraintsSatisfied = schedule.getScore().getNumberOfHardConstraintsSatisfied();

		int numberOfSoftConstraints = schedule.getScore().getNumberOfSoftConstraints();
		int numberOfSoftConstraintsSatisfied = schedule.getScore().getNumberOfSoftConstraintsSatisfied();

		/* calculate relative score (simply using sums) */
		schedule.getScore().setSumHardConstraints(
				numberOfHardConstraints * (numberOfHardConstraints + 1) / 2);

		schedule.getScore().setSumHardConstraintsSatisfied(
				numberOfHardConstraintsSatisfied * (numberOfHardConstraintsSatisfied + 1) / 2);

		schedule.getScore().setSumSoftConstraints(
				numberOfSoftConstraints * (numberOfSoftConstraints + 1) / 2);

		schedule.getScore().setSumHardConstraintsSatisfied(numberOfSoftConstraintsSatisfied
				* (numberOfSoftConstraintsSatisfied + 1) / 2);

		return schedule.getScore();
	}
}
