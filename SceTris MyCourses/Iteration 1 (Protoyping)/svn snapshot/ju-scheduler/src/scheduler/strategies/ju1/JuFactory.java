package scheduler.strategies.ju1;

import scheduler.Scheduler;
import scheduler.strategies.AbstractComponentFactory;

public class JuFactory extends AbstractComponentFactory {

	protected Scheduler scheduler;
	
	public JuFactory(Scheduler scheduler) {
		super(scheduler);
		this.scheduler = scheduler;
	}
	
	@Override
	public CrossoverOperationImpl createCrossoverOperation() {
		return new CrossoverOperationImpl(scheduler);
	}

	@Override
	public MutateOperationImpl createMutateOperation() {
		return new MutateOperationImpl(scheduler);
	}

	@Override
	public SetupFunctionImpl createSetupFunction() {
		return new SetupFunctionImpl(scheduler);
	}

	@Override
	public FitnessFunctionImpl createFitnessFunction() {
		return new FitnessFunctionImpl(scheduler);
	}
	
	@Override
	public IndividualImpl createIndividual() {
		return new IndividualImpl(scheduler);
	}
	
	@Override
	public IndividualImpl[] createIndividualArray(int size) {
		return new IndividualImpl[size]; 
	}
}
