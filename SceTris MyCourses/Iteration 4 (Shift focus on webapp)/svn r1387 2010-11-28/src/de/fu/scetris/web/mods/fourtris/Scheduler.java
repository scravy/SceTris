/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.controller.SchedulerController;
import de.fu.scetris.web.Fourtris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 * @author Konrad Reiche
 * @since Iteration3
 */
@ModuleInfo(name = "scheduler", author = "Konrad Reiche", description = "Controls the Scheduler", requires = { Requirement.DATABASE })
public class Scheduler extends FriggModule<RelationManager> {

	SchedulerController schedulerController;

	public Scheduler(final Fourtris parent) {
		super(parent);
	}

	@Action(template = "scheduling.control.xsl")
	public void _default(final String[] path)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {
	}

	@Commit(action = "control", after = "control")
	public void sendCommandCommit(
			final String[] path,
			@Arg(name = "command", stringDefault = "idle") final String command,
			@Arg(name = "academicTerm") final int academicTerm,
			@Arg(name = "department") final int department) throws Exception {

		final SchedulerController schedulerController = SchedulerController
				.getInstance(relationManager);

		if (command.equals("start")) {
			put("status", "running");

			List<Program> programList = (List<Program>) relationManager
					.getProgram();

			System.out.println(academicTerm);
			System.out.println(department);

			for (Program program : programList) {
				if ((program.getAcademicTerm().id() == academicTerm)
						&& (program.getDepartment().id() == department)) {

					schedulerController.startScheduler(program);
					break;
				}
			}

		} else if (command.equals("stop")) {
			put("status", "stopping");
			schedulerController.stopScheduler();
		} else {
			assert (false);
		}
	}

	@Action(name = "control", template = "scheduling.control.xsl")
	public void showPanel(final String[] path)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {

		final SchedulerController schedulerController = SchedulerController
				.getInstance(relationManager);

		put("academicTerms", relationManager.getAcademicTerm());
		put("departments", relationManager.getDepartment());

		if (schedulerController.isReady()) {
			put("status", "ready");
			put("roomTimetable", schedulerController.getSchedulePresentation());
		} else if (schedulerController.isStopping()) {
			put("status", "stopping");
		} else if (schedulerController.isRunning()) {
			put("status", "running");

			Program currentProgramBeingScheduled = schedulerController
					.getCurrentProgramBeScheduling();

			put("scheduleScore", schedulerController.getScheduleScore(currentProgramBeingScheduled));
		} else {
			assert (false);
		}
	}
}
