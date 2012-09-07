package de.fu.scetris.scheduler.web;

import java.io.IOException;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.controller.SchedulerStarter;
import de.fu.weave.impl.ControllerException;
import de.fu.weave.impl.GenericController;
import de.fu.weave.orm.DatabaseException;

/* Scetris.java / 4:19:17 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

/**
 * 
 * @author Konrad Reiche
 * @since Iteration3
 */
public class Scheduling extends GenericController<RelationManager> {

	public enum State {
		IDLE, RUNNING, FINISHED
	}

	/**
	 * 
	 * @since Iteration3
	 */
	private static final long serialVersionUID = -2323259992443218522L;;

	private Thread schedulerThread;
	private SchedulerStarter schedulerController;

	/**
	 * @throws ParserConfigurationException
	 * @throws DatabaseException
	 * @throws SAXException
	 * @throws IOException
	 * @since Iteration2
	 */
	public Scheduling() throws ParserConfigurationException,
			ControllerException, IOException, SAXException, DatabaseException {
		super(de.fu.scetris.data.RelationManager.class, Status.class);
	}

	public SchedulerStarter getSchedulerController() {
		return schedulerController;
	}

	public Thread getSchedulerThread() {
		return schedulerThread;
	}

	public State getStatus() {
		if (schedulerThread == null) {
			return State.IDLE;
		} else {
			return schedulerThread.isAlive() ? State.RUNNING : State.FINISHED;
		}
	}

	@Override
	protected void initBefore(final ServletConfig servletConfig)
			throws Exception {
		loadProperties(
				servletConfig.getServletContext().getRealPath(
						"WEB-INF/scetris.properties"), "scetris.");
	}

	public void start(RelationManager relationManager)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {

		schedulerController = new SchedulerStarter(relationManager);
		schedulerThread = new Thread(schedulerController);
		schedulerThread.start();
	}

	public RoomTimetable getRoomTimeTable(Room room) {	
		return schedulerController.roomTimetableMap.get(room);
	}
}
