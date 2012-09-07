package de.fu.scetris.scheduler;

import de.fu.scetris.scheduler.data.ScheduleScore;
import de.fu.scetris.scheduler.data.SchedulingException;

public interface FitnessCalculation {

	public void calculateHardFitness(Schedule schedule, ScheduleScore score) throws SchedulingException;
	
	public void calculateSoftFitness(Schedule schedule, ScheduleScore score) throws SchedulingException;
}
