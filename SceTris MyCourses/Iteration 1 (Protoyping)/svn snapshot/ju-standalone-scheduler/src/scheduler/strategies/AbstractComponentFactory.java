package scheduler.strategies;

import scheduler.Scheduler;
import scheduler.strategies.interfaces.ComponentFactory;
import scheduler.strategies.interfaces.GeneticAlgorithm;

public abstract class AbstractComponentFactory implements ComponentFactory {
	
	protected Scheduler scheduler;
	
	public AbstractComponentFactory(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
 	
	public GeneticAlgorithm createGeneticAlgorithm() {
		return new GeneticAlgorithmImpl(scheduler);
	}
}
