package scheduler.strategies.interfaces;


public interface ComponentFactory {
	
	CrossoverOperation createCrossoverOperation();
	
	MutateOperation createMutateOperation();
	
	SetupFunction createSetupFunction();
	
	FitnessFunction createFitnessFunction();
	
	GeneticAlgorithm createGeneticAlgorithm();
	
	Individual createIndividual();
	
	Individual[] createIndividualArray(int size);
}
