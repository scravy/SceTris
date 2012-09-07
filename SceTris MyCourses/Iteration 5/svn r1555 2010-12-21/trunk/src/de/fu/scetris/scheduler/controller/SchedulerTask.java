package de.fu.scetris.scheduler.controller;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;

public class SchedulerTask implements Runnable {

	RelationManager relationManager;
	Algorithm algorithm;
	Program currentProgramBeingScheduled;
	boolean resume;
	
	public SchedulerTask(RelationManager relationManager, Algorithm algorithm, Program program, boolean resume) {
		super();
		this.relationManager = relationManager;
		this.algorithm = algorithm;
		this.currentProgramBeingScheduled = program;
		this.resume = resume;
	}

	@Override
	public void run() {

		try {
			relationManager.enableQueryCache();
			algorithm.start(resume);
			relationManager.disableQueryCache();
			
			// FIXME
			if (algorithm.getBestSchedule().getHardFitness() == 1.0) {
				relationManager.beginTransaction();
				relationManager.commitTransaction();
			}

			currentProgramBeingScheduled = null;
			
			System.out.println("Finished with hard fitness "
					+ algorithm.getBestSchedule().getHardFitness()
					+ " and soft fitness "
					+ algorithm.getBestSchedule().getSoftFitness());

		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public Program getCurrentProgramBeingScheduled() {
		return currentProgramBeingScheduled;
	}
	
	

}
