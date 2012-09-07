package scheduler.strategies;

import java.util.Arrays;
import java.util.Random;
import java.util.TreeSet;

import scheduler.Observer;
import scheduler.Scheduler;
import scheduler.strategies.interfaces.GeneticAlgorithm;
import scheduler.strategies.interfaces.Individual;


public class GeneticAlgorithmImpl implements GeneticAlgorithm {
	private Scheduler scheduler;
	private Random rand;
	
	
	public GeneticAlgorithmImpl(Scheduler scheduler) {
		this.scheduler = scheduler;
		rand = scheduler.getRand();
	}
	

	public Individual run(Observer observer) {
		TreeSet<Individual> bestIndividuals = new TreeSet<Individual>();
		Individual[] population = scheduler.getFactory().createIndividualArray(scheduler.getPerGeneration());
		
		int generation = 0;
		
		// Initialisierung der ersten Generation
		for (int i = 0; i < population.length; i++) {
			population[i] = scheduler.getFactory().createIndividual();
			bestIndividuals.add(population[i]);
		}
		
		do {
			observer.nextGeneration(generation++, population, bestIndividuals);
			
			// Neue Generation
			Individual[] bestIndividualsArray = bestIndividuals.toArray(scheduler.getFactory().createIndividualArray(0));
			for (int i = 0; i < bestIndividualsArray.length; i++) {
				Individual parent = bestIndividualsArray[rand.nextInt(bestIndividualsArray.length)];
				// Rekombination durch Kreuzung
				population[i] = parent.crossover(bestIndividualsArray[rand.nextInt(bestIndividualsArray.length)]);
				// Mutation
				population[i].mutate();
			}
			
			bestIndividuals.addAll(Arrays.asList(population));
			
			// Selektion
			while (bestIndividuals.size() > scheduler.getMaxBest()) {
				bestIndividuals.pollFirst();
			}
		} while (bestIndividuals.last().getFitness() < 1);
		
		observer.done(generation, bestIndividuals.last());
		return bestIndividuals.last();

	}
}
