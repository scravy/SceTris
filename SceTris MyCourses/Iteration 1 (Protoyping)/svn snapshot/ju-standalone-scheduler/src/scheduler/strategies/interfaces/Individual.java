package scheduler.strategies.interfaces;

public interface Individual extends Cloneable, Comparable<Individual> {

	Individual crossover(Individual second);
	
	void mutate();
	
	Individual createMutation();
	
	double getFitness();
	
	Individual clone();
}
