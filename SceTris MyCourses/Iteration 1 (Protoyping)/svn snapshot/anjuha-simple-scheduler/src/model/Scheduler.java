package model;

import java.util.*;
import model.data.*;

/**
 * The scheduler acts as the central controller of the execution of the
 * algorithm. It creates new populations and breeds new ones until
 * the desired quality is reached.
 */
public class Scheduler {
	
	/**
	 * The dataSource where the data comes from we work with
	 */
	protected DataSource dataSource;
	
	/**
	 * The set of bestChromosomes. It is maintained by the Scheduler
	 * since we need to maintain it over time.
	 */
	protected TreeSet<Schedule> bestChromosomes;
	
	
	/**
	 * A new Scheduler is associated with a source of data
	 * from which we gain information on what to schedule
	 * and how.
	 *
	 * A dataSource also provides Configuration.
	 */
	public Scheduler(DataSource dataSource) {
		this.dataSource = dataSource;
	}
	
	
	/**
	 * Starts the solving
	 */
	public void solve(Observer observer, Configuration config) {
		bestChromosomes = new TreeSet<Schedule>();
		
		for (int generation = 0 ;; generation++) {
			observer.nextLevelOfEvolutionReached(generation, bestChromosomes.last());
			
			Population currentGeneration = new Population(this, bestChromosomes);
			currentGeneration.breed();
			
			if (bestChromosomes.last().evaluateFitness() >= 1.0) {
				observer.desiredResultReached(generation, bestChromosomes.last());
			}
			

		}
	}
	
	/**
	 * 
	 */
	public DataSource getDataSource() {
		return dataSource;
	}
}
