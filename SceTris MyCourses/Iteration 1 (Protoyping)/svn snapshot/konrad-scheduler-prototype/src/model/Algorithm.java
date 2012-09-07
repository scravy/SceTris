package model;

import java.util.Random;
import java.util.Vector;

public class Algorithm {

    Vector<Schedule> chromosomes = new Vector<Schedule>();

    Vector<Boolean> bestFlags = new Vector<Boolean>();

    Vector<Integer> bestChromosomes = new Vector<Integer>();

    int currentBestSize;

    int replaceByGeneration;

    ScheduleObserver oberserver;

    private Schedule prototype;

    int currentGeneration;

    AlgorithmState state;

    static Algorithm instance;

    public enum AlgorithmState {
	AS_USER_STOPED, AS_CRITERIA_STOPPED, AS_RUNNING
    };

    // Initialize genetic algorithm
    public Algorithm(int numberOfChromosomes, int replaceByGeneration,
	    int trackBest, Schedule prototype, ScheduleObserver observer) {

	this.prototype = prototype;

	// there should be at least 2 chromosomes in population
	if (numberOfChromosomes < 2)
	    numberOfChromosomes = 2;

	// and algorithm should track at least on of best chromosomes
	if (trackBest < 1)
	    trackBest = 1;

	if (replaceByGeneration < 1)
	    replaceByGeneration = 1;
	else if (replaceByGeneration > numberOfChromosomes - trackBest)
	    replaceByGeneration = numberOfChromosomes - trackBest;

	// reserve space for population
	chromosomes.setSize(numberOfChromosomes);
	bestFlags.setSize(numberOfChromosomes);

	// reserve space for best chromosome group
	bestChromosomes.setSize(trackBest);

	// clear population
	for (int i = chromosomes.size() - 1; i >= 0; --i) {
	    chromosomes.set(i, null);
	    bestFlags.set(i, false);
	}
    }

    /**
     * Class Algorithm implements the Singleton Pattern FIXME: Ju: Why?
     */
    public static Algorithm getInstance() {
	// global instance doesn't exist?
	if (instance == null) {

	    // make prototype of chromosomes
	    Schedule prototype = new Schedule(2, 2, 80, 3);
	    // make new global instance of algorithm using chromosome prototype
	    instance = new Algorithm(100, 8, 5, prototype,
		    new ScheduleObserver());
	}
	return instance;
    }

    /**
	 *
	 */
    public static void freeInstance() {
	// TODO: rework, freeInstance is c++ stuff isnt it?
	// Ju: yes, it is. Javas GC does that stuff for us,
	// however, Java DOES provide finalize()
	instance = null;
    }

    /**
	 *
	 */
    public void start() {
	Schedule best = null;
	Random r = new Random();

	if (prototype == null) {
	    return;
	}

	// do not ruin already running algorithm

	if (state == AlgorithmState.AS_RUNNING) {
	    return;
	}
	
	state = AlgorithmState.AS_RUNNING;

	// TODO: locking

	// TODO: notify observer

	// clear the best chromosomes
	clearBest();

	// initialize new population with chromosomes randomly built using
	// prototype

	for (int i = 0; i < chromosomes.size(); ++i) {

	    // add new chromosome to population
	    chromosomes.set(i, prototype.makeNewFromPrototype());
	    addToBest(i);
	}

	currentGeneration = 0;

	while (true) {

	    if (state != AlgorithmState.AS_RUNNING) {
		break;
	    }

	    best = getBestChromosome();

	    // algorithm has reached criteria?

	    if (best.getFitness() >= 1) {
		state = AlgorithmState.AS_CRITERIA_STOPPED;
		break;
	    }
	}
	// produce offspring
	Vector<Schedule> offspring = new Vector<Schedule>(replaceByGeneration);

	for (int j = 0; j < replaceByGeneration; j++) {
	    // select parent randomly

	    Schedule parent1 = chromosomes.elementAt(r.nextInt()
		    % chromosomes.size());
	    Schedule parent2 = chromosomes.elementAt(r.nextInt()
		    % chromosomes.size());

	    offspring.set(j, parent1.crossover(parent2));
	    offspring.elementAt(j).mutation();
	}

	// replace chromosome of current operation with offspring

	for (int k = 0; k < replaceByGeneration; k++) {
	    int ci;

	    do {
		// select chromosome for replacement randomly
		ci = r.nextInt() % chromosomes.size();

		// protect best chromosome from replacement
	    } while (isinBest(ci));

	    // replace chromosomes
	    chromosomes.setElementAt(null, ci);

	    // try to add new chromosomes in best chromosome group
	    addToBest(ci);
	}

	// algorithm has found new best chromosome
	if (best != getBestChromosome()) {
	    // notify observer
	}

	currentGeneration++;
    }

    /**
	 *
	 */
    public void stop() {
	if (state == AlgorithmState.AS_RUNNING) {
	    state = AlgorithmState.AS_USER_STOPED;
	}
    }

    /**
	 *
	 */
    public Schedule getBestChromosome() {
	return chromosomes.elementAt(bestChromosomes.elementAt(0));
    }

    /**
	 *
	 */
    public int getCurrentGeneration() {
	return currentGeneration;
    }

    /**
	 *
	 */
    public ScheduleObserver getObserver() {
	return oberserver;
    }

    private void addToBest(int chromosomeIndex) {
	// don't add if new chromosome hasn't fitness big enough for best
	// chromosome group
	// or it is already in the group?

	if (currentBestSize == bestChromosomes.size()
		&& chromosomes.elementAt(
			bestChromosomes.elementAt(currentBestSize-1))
			.getFitness() >= chromosomes.elementAt(chromosomeIndex)
			.getFitness() || bestFlags.elementAt(chromosomeIndex)) {
	    return;
	}

	// find place for new chromosome
	int i = currentBestSize;

	for (; i > 0; i--) {
	    // group is not full?

	    if (i < bestChromosomes.size()) {
		// position of new chromosome found?
		if (chromosomes.elementAt(bestChromosomes.elementAt(i - 1))
			.getFitness() > chromosomes.elementAt(chromosomeIndex)
			.getFitness()) {
		    break;
		}

		// move chromosomes to make room for new
		bestChromosomes.set(i, bestChromosomes.elementAt(i - 1));

	    } else {
		// group is full remove worst chromosomes in the group
		bestFlags.set(bestChromosomes.elementAt(i - 1), false);
	    }
	}

	// store chromosome in best chromosome group
	bestChromosomes.set(i, chromosomeIndex);
	bestFlags.set(chromosomeIndex, true);

	// increase current size if it has not reached the limit yet
	if (currentBestSize < bestChromosomes.size()) {
	    currentBestSize++;
	}
    }

    private boolean isinBest(int chromosomIndex) {
	return bestFlags.elementAt(chromosomIndex);
    }

    private void clearBest() {
	for (int i = bestFlags.size() - 1; i >= 0; --i) {
	    bestFlags.set(i, false);
	}

	currentBestSize = 0;
    }

    public Schedule getPrototype() {
	return prototype;
    }

}
