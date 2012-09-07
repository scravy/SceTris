package de.fu.scetris.scheduler.genetic;

import java.util.Random;

import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

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

	public ScheduleScore check(Configuration configuration) throws SchedulingException,
			NotScheduleableException {

		Schedule schedule = null;

		try {
			Setup setup = new ProposedSchedulingSetupImpl(random, configuration);
			schedule = new ScheduleImpl(configuration);
			setup.initialize(schedule, configuration, true);

			new FitnessCalculationImpl().calculateHardFitness(schedule, schedule.getScore());
			new FitnessCalculationImpl().calculateSoftFitness(schedule, schedule.getScore());

			int numberOfHardConstraints = schedule.getScore().getNumberOfHardConstraints();
			int numberOfHardConstraintsSatisfied = schedule.getScore()
					.getNumberOfHardConstraintsSatisfied();

			int numberOfSoftConstraints = schedule.getScore().getNumberOfSoftConstraints();
			int numberOfSoftConstraintsSatisfied = schedule.getScore()
					.getNumberOfSoftConstraintsSatisfied();

			/* calculate relative score (simply using sums) */
			schedule.getScore().setSumHardConstraints(
					numberOfHardConstraints * (numberOfHardConstraints + 1) / 2);

			schedule.getScore().setSumHardConstraintsSatisfied(
					numberOfHardConstraintsSatisfied * (numberOfHardConstraintsSatisfied + 1) / 2);

			schedule.getScore().setSumSoftConstraints(
					numberOfSoftConstraints * (numberOfSoftConstraints + 1) / 2);

			schedule.getScore().setSumHardConstraintsSatisfied(numberOfSoftConstraintsSatisfied
					* (numberOfSoftConstraintsSatisfied + 1) / 2);

		} catch (DatabaseException e) {
			throw new SchedulingException(e);
		}
		
		return schedule.getScore();
	}
}
