package de.fu.scetris.scheduler.model.data;

public class AlgorithmConfiguration {

	private float mutationSize;
	private float mutationProbability;
	private float crossoverProbability;
	private float crossoverPoints;
	private int trackBest;
	private int replaceByGeneration;
	private int populationSize;
	private double minimumSoftFitness;

	public AlgorithmConfiguration(float mutationSize,
			float mutationProbability, float crossoverProbability,
			float crossoverPoints, int trackBest, int replaceByGeneration,
			int populationSize, double minimumSoftFitness) {

		if (trackBest >= populationSize) {
			trackBest = populationSize - 1;
		}

		this.mutationSize = mutationSize;
		this.mutationProbability = mutationProbability;
		this.crossoverProbability = crossoverProbability;
		this.crossoverPoints = crossoverPoints;
		this.trackBest = trackBest;
		this.replaceByGeneration = replaceByGeneration;
		this.populationSize = populationSize;
		this.minimumSoftFitness = minimumSoftFitness;
	}

	public float getMutationSize() {
		return mutationSize;
	}

	public float getMutationProbability() {
		return mutationProbability;
	}

	public float getCrossoverProbability() {
		return crossoverProbability;
	}

	public float getCrossoverPoints() {
		return crossoverPoints;
	}

	public int getTrackBest() {
		return trackBest;
	}

	public int getReplaceByGeneration() {
		return replaceByGeneration;
	}

	public int getPopulationSize() {
		return populationSize;
	}

	public double getMinimumSoftFitness() {
		return minimumSoftFitness;
	}

}
