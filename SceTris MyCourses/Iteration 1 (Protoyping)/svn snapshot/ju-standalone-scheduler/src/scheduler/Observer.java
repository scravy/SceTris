package scheduler;

import scheduler.strategies.interfaces.Individual;


public interface Observer {
	public void done(int generation, Individual bestSchedule);
	public void nextGeneration(int generation, Individual[] population, java.util.TreeSet<Individual> best);
}
