package de.fu.scetris.scheduler.manager;

import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.Algorithm;

/**
 * The {@link Runnable} which handles the scheduling process.
 * 
 * @author Konrad Reiche
 * 
 */
public class SchedulerTask implements Runnable {

	/**
	 * Managing the connection and interaction with the database.
	 */
	RelationManager relationManager;

	/**
	 * The applied algorithm for the scheduling process.
	 */
	Algorithm algorithm;

	/**
	 * The current program which is being scheduled.
	 */
	Program currentProgramBeingScheduled;

	/**
	 * Whether this scheduling task is a resume or a fresh start.
	 */
	boolean resume;

	/**
	 * Flag to indicate this task has failed.
	 */
	boolean hasFailed;
	
	Exception failingException;

	/**
	 * Constructor which sets all required fields of this object.
	 * 
	 * @param relationManager
	 *            Managing the connection and interaction with the database.
	 * @param algorithm
	 *            The applied algorithm for the scheduling process.
	 * @param program
	 *            The current program which is being scheduled.
	 * @param resume
	 *            Whether this scheduling task is a resume or a fresh start.
	 */
	public SchedulerTask(final RelationManager relationManager, final Algorithm algorithm,
			final Program program, final boolean resume) {
		super();
		this.relationManager = relationManager;
		this.algorithm = algorithm;
		currentProgramBeingScheduled = program;
		this.resume = resume;
	}

	/**
	 * @return The applied algorithm for the scheduling process.
	 */
	public Algorithm getAlgorithm() {
		return algorithm;
	}

	/**
	 * @return The current program which is being scheduled.
	 */
	public Program getCurrentProgramBeingScheduled() {
		return currentProgramBeingScheduled;
	}

	/**
	 * @return Flag to indicate this task has failed.
	 */
	public boolean hasFailed() {
		return hasFailed;
	}

	/**
	 * Runs the algorithm until it is finished or this thread was interrupted.
	 * <p>
	 * Before starting the algorithm the query cache of the
	 * {@link RelationManager} is enabled in order to improve the performance.
	 * <p>
	 * If the scheduling was interrupted due to any exception the task will be
	 * flagged indicating it has failed.
	 * <p>
	 * In any case the query cache is disabled again.
	 */
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
			failingException = e;
			e.printStackTrace();
		} finally {
			relationManager.disableQueryCache();
		}
	}
}
