package scheduler.strategies.interfaces;

import scheduler.Observer;

public interface GeneticAlgorithm {
	public Individual run(Observer observer);
}
