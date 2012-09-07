package model;


import java.util.*;


/**
 * Population is the OO-representation of a single population (=generation).
 * It performs the process of selection.
 */
class Population {
	
	/**
	 * Stores Chromosomes/Invidivuals of this population
	 */
	protected Vector<Schedule> schedules;
	
	/**
	 * Stores the Chromosomes that have the best fitness
	 * in the current population. There is an upper bound
	 * of how many best chromosomes are remembered.
	 * The number is defined by the parameter "trackBest"
	 * in the standard-constructor.
	 *
	 * It is a TreeSet since we want to have access to
	 * the worst and bestest elements.
	 */
	protected TreeSet<Schedule> bestSchedules;
	
	/**
	 * How many chromosomes should be retained in
	 * bestSchedules?
	 */
	protected int maximumNumberOfBestSchedules;
	
	/**
	 * Contains a prototype for this population.
	 * New Chromosomes/Individuals/Schedules are built based
	 * on this prototype.
	 * NOTE: I don't know yet exactly why this is done.
	 */
	protected Schedule prototype;
	
	/**
	 * Stored the Scheduler it belongs to. The Scheduler holds
	 * configuration and data (can be retrieved via getDataSource()).
	 * See also the Classes DataSource within this package.
	 */
	protected Scheduler parent;
	
	
	/**
	 * STANDARD-CONSTRUCTOR
	 * @param int numberOfChromosomes How many Chromosomes should be created in this population?
	 * @param int trackBest How many Chromosomes should be retained per Iteration
	 */
	public Population(Scheduler parent, TreeSet<Schedule> bestSchedules) {
		this.parent = parent;
		this.schedules = new Vector<Schedule>(parent.getDataSource().getNumberOfChromosomesPerGeneration());
		this.bestSchedules = bestSchedules;
	}
	
	/**
	 * is used to create individuals. In the CPP-Solution this was "Algorithm.start()"
	 *
	 * Sketch:
	 *   - First there should be chromosomes made of a certain prototype
	 *   - Than they should be crossed over randomly and mutated
	 *   - Only the best from this and the best retrieved from Scheduler
	 *     via the Constructor should be retained and stored to the best
	 * NOTE: I'm not sure whether this is correct. To my understanding
	 *       it would make more sense to take the best chromosomes into
	 *       account from the beginning. (have a look at the cpp source,
	 *       I maybe have gotten something wrong)
	 */
	public void breed() {
		// first we create the individuals (we populate our generation)
		for (int i = 0; i < schedules.size(); i++) {
			Schedule possibleChromosome = new Schedule(this.parent.getDataSource());
			schedules.set(i, possibleChromosome);
			// every chromosome is added to bestChromosomes
			// (not-so-good ones are filtered later on)
			bestSchedules.add(possibleChromosome);
		}
		// Since we don't want to store more bestChromosomes than
		// we need to (maximumNumberOfBestSchedules) we poll as many
		// bad schedules from bestChromosomes as needed to reduce its
		// size to less-than-or-equals the size of max..Num..Best...
		while (bestSchedules.size() > this.parent.getDataSource().getMaximumNumberOfBestChromosomes()) {
			bestSchedules.pollFirst();
			// NOTE: I don't know if it is usefull to do
			// something with what is polled out
		}
	}
	
}
