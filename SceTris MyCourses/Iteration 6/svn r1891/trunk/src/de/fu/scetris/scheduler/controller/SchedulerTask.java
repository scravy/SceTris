package de.fu.scetris.scheduler.controller;

import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.model.strategy.interfaces.Algorithm;

public class SchedulerTask implements Runnable {

	RelationManager relationManager;
	Algorithm algorithm;
	Program currentProgramBeingScheduled;
	boolean resume;
	boolean hasFailed;

	public SchedulerTask(RelationManager relationManager, Algorithm algorithm,
			Program program, boolean resume) {
		super();
		this.relationManager = relationManager;
		this.algorithm = algorithm;
		this.currentProgramBeingScheduled = program;
		this.resume = resume;
	}

	@Override
	public void run() {

		relationManager.enableQueryCache();
		try {
			algorithm.start(resume);

			relationManager.beginTransaction();
			relationManager.commitTransaction();
			currentProgramBeingScheduled = null;

			System.out.println("Finished with hard fitness "
					+ algorithm.getBestSchedule().getScore().getHardFitness()
					+ " and soft fitness "
					+ algorithm.getBestSchedule().getScore().getSoftFitness());

		} catch (Exception e) {
			hasFailed = true;
			return;
		} finally {
			relationManager.disableQueryCache();
		}
	}

	public Algorithm getAlgorithm() {
		return algorithm;
	}

	public Program getCurrentProgramBeingScheduled() {
		return currentProgramBeingScheduled;
	}

	public boolean hasFailed() {
		return hasFailed;
	}
}
