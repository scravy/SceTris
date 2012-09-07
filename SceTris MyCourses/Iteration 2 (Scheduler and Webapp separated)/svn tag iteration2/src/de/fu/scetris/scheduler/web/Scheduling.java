package de.fu.scetris.scheduler.web;

import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.controller.SchedulerStarter;
import de.fu.weave.impl.ControllerException;
import de.fu.weave.impl.GenericController;

/* Scetris.java / 4:19:17 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Scheduling extends GenericController<RelationManager> {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -2323259992443218522L;

	private enum State { IDLE, RUNNING, FINISHED };
	
	private Thread schedulerThread;

	/**
	 * @throws ParserConfigurationException
	 * @since Iteration2
	 */
	@SuppressWarnings("unchecked")
	public Scheduling() throws ParserConfigurationException, ControllerException {
		super(de.fu.scetris.data.RelationManager.class, Status.class);

		schedulerThread = new Thread(new SchedulerStarter());
		schedulerThread.start();
	}

	public State getStatus() {
		if (schedulerThread == null) {
			return State.IDLE;
		} else {
			return schedulerThread.isAlive() ? State.RUNNING : State.FINISHED;
		}
	}
}
