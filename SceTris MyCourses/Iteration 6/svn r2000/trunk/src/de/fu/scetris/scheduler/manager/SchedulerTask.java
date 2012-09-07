package de.fu.scetris.scheduler.manager;

import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.Algorithm;

public class SchedulerTask implements Runnable {

	RelationManager relationManager;
	Algorithm algorithm;
	Program currentProgramBeingScheduled;
	boolean resume;
	boolean hasFailed;

	public SchedulerTask(final RelationManager relationManager, final Algorithm algorithm,
			final Program program, final boolean resume) {
		super();
		this.relationManager = relationManager;
		this.algorithm = algorithm;
		currentProgramBeingScheduled = program;
		this.resume = resume;
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

	@Override
	public void run() {

		relationManager.enableQueryCache();
		try {
			algorithm.start(resume);

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
}
