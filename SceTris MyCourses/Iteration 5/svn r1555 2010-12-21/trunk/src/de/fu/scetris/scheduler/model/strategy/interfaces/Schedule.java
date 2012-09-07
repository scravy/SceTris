/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.interfaces;

/**
 * Modeling the schedule.
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 */
public interface Schedule extends Comparable<Schedule> {

	public double getHardFitness();

	public int getSatisfiedHardConstraintCount();

	public int getSatisfiedSoftConstraintCount();

	public double getSoftFitness();

	public int getTotalHardConstraintCount();

	public int getTotalSoftConstraintCount();

	public void setSatisfiedHardConstraintCount(int count);

	public void setSatisfiedSoftConstraintCount(int count);

	public void setTotalHardConstraintCount(int count);

	public void setTotalSoftConstraintCount(int count);
}
