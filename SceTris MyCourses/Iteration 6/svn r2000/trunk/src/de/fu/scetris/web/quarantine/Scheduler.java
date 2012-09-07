/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.quarantine;

import java.io.IOException;
import java.util.List;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.scheduler.manager.SchedulerManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 */
@Author("Konrad Reiche")
public class Scheduler extends FriggModule<RelationManager> {

	SchedulerManager schedulerManager;

	public Scheduler(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "scheduling.control.xsl")
	public void _default(final String[] path)
			throws ParserConfigurationException, IOException, SAXException,
			DatabaseException {}

	@Commit(action = "control", after = "control")
	public void sendCommandCommit(
								  final String[] path,
								  @Arg(name = "command", stringDefault = "idle") final String command,
								  @Arg(name = "academicTerm") final int academicTerm,
								  @Arg(name = "department") final int department) throws Exception {

		final SchedulerManager schedulerManager = SchedulerManager
				.getInstance(relationManager);

		if (command.equals("freeze")) {

			List<Program> programList = relationManager.getProgram();

			for (Program program : programList) {
				if ((program.getAcademicTerm().getId() == academicTerm)
						&& (program.getDepartment().getId() == department)) {

					schedulerManager.freezeProgram(program);
					return;
				}
			}

		}

		if (command.equals("start") || command.equals("resume")) {

			put("status", "running");

			List<Program> programList = (List<Program>) relationManager
					.getProgram();

			for (Program program : programList) {
				if ((program.getAcademicTerm().id() == academicTerm)
						&& (program.getDepartment().id() == department)) {

					if (command.equals("start")) {
						schedulerManager.startScheduler(program, false);
					} else if (command.equals("resume")) {
						schedulerManager.startScheduler(program, true);
					} else {
						assert (false);
					}
					break;
				}
			}
		} else if (command.equals("resume")) {

		} else if (command.equals("stop")) {
			put("status", "stopping");
			schedulerManager.stopScheduler();
		} else {
			assert (false);
		}
	}

	@Action(name = "control", template = "scheduling.control.xsl")
	public void showPanel(final String[] path)
			throws Exception {

		final SchedulerManager schedulerManager = SchedulerManager
				.getInstance(relationManager);

		put("academicTerms", relationManager.getAcademicTerm());
		put("departments", relationManager.getDepartment());

		if (schedulerManager.isReady()) {
			put("status", "ready");
		} else if (schedulerManager.isStopping()) {
			put("status", "stopping");
		} else if (schedulerManager.isRunning()) {
			put("status", "running");

			Program currentProgramBeingScheduled = schedulerManager
					.getCurrentProgramBeScheduling();

			put("scheduleScore",
					schedulerManager
							.getScheduleScore(currentProgramBeingScheduled));
		} else {
			assert (false);
		}
	}
}
