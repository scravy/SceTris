package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.ScheduleScore;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;

/**
 * Scores the given Schedule in means of its hard fitness and soft fitness.
 * 
 * @author Konrad Reiche
 * 
 */
public class ScheduleChecker {

	Configuration configuration;

	public ScheduleChecker(final Configuration configuration) {
		this.configuration = configuration;
	}

	public ScheduleScore check(Schedule schedule) throws DatabaseException {
		new CalculateHardFitnessImpl().calculateHardFitness(schedule,
				configuration);
		new CalculateSoftFitnessImpl().calculateSoftFitness(schedule,
				configuration);

		int numberOfSoftConstraints = schedule.getTotalSoftConstraintCount();
		int numberOfSoftConstraintsSatisfied = schedule
				.getSatisfiedSoftConstraintCount();
		int numberOfHardConstraintsSatisfied = schedule
				.getSatisfiedHardConstraintCount();
		int numberOfHardConstraints = schedule.getTotalHardConstraintCount();

		/* calculate relative score (simply using sums) */
		int sumHardConstraints = numberOfHardConstraints*(numberOfHardConstraints+1)/2;
		int sumHardConstraintsSatisfied = numberOfHardConstraintsSatisfied*(numberOfHardConstraintsSatisfied+1)/2;
		int sumSoftConstraints = numberOfSoftConstraints*(numberOfSoftConstraints+1)/2;		
		int sumSoftConstraintsSatisfied = numberOfSoftConstraintsSatisfied*(numberOfSoftConstraintsSatisfied+1)/2;
		
		return new ScheduleScore(numberOfSoftConstraints,
				numberOfSoftConstraintsSatisfied, numberOfHardConstraints,
				numberOfHardConstraintsSatisfied, sumSoftConstraints,
				sumSoftConstraintsSatisfied, sumHardConstraints,
				sumHardConstraintsSatisfied);

	}
}
