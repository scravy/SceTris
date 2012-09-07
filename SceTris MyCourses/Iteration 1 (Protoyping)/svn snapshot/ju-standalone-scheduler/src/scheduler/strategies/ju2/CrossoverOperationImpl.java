package scheduler.strategies.ju2;

import java.util.Random;

import scheduler.Scheduler;
import scheduler.strategies.interfaces.CrossoverOperation;
import scheduler.strategies.interfaces.Individual;


public class CrossoverOperationImpl implements CrossoverOperation {
	@SuppressWarnings("unused")
	private Scheduler scheduler;
	
	
	@SuppressWarnings("unused")
	private Random rand;
	
	
	public CrossoverOperationImpl(Scheduler scheduler) {
		this.scheduler = scheduler;
		rand = scheduler.getRand();
	}
	

	public IndividualImpl crossover(Individual p1, Individual p2) {
		if (p2 instanceof IndividualImpl) {
			IndividualImpl parent2 = (IndividualImpl) p2;
			// crossover is defined to produce NEW individuals (see also revision 163)
			return parent2.clone();
		}
		throw new IllegalArgumentException();
	}
}
