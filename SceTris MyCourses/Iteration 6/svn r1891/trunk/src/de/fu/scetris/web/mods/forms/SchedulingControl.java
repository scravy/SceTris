/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import static de.fu.bakery.orm.java.filters.Filters.all;
import static de.fu.bakery.orm.java.filters.Filters.eq;

import java.util.List;

import de.fu.scetris.data.Program;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.scetris.scheduler.controller.SchedulerController;

public class SchedulingControl extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public String but_start;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public String but_resume;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public String but_stop;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public String but_publish;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public int department;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public int academicTerm;

	public String schedulerstatus = "";

	public SchedulingControl() {
	}

	@Override
	public boolean commit() throws Exception {
		System.out.println("commit called");
		System.out.println("buttons :: " + but_start + but_resume + but_stop
				+ but_publish + " _" + department + " _" + academicTerm);
		final SchedulerController schedulerController = SchedulerController
				.getInstance(manager());

		if (but_publish == null) {

			List<Program> programList = manager().getProgram();

			for (Program program : programList) {
				if ((program.getAcademicTerm().getId() == academicTerm)
						&& (program.getDepartment().getId() == department)) {

					schedulerController.freezeProgram(program);
				}
			}
		}

		if (but_start == null || but_resume == null) {

			// put("status", "running");
			schedulerstatus = "running";
			
			List<Program> programList = (List<Program>) manager().getProgram(
					all(eq("department", department),
							eq("academic_term", academicTerm)));

			Program program = programList.get(0);
			
			if (but_start == null) {
				schedulerController.startScheduler(program, false);
			} else if (but_resume == null) {
				schedulerController.startScheduler(program, true);
			} else {
			}

		} else if (but_resume == null) {

		} else if (but_stop == null) {
			schedulerstatus = "stopping";
			// put("status", "stopping");
			schedulerController.stopScheduler();
		} else {
		}

		System.out.println("last ss :: " + schedulerstatus);
		return true;
	}

	@Override
	public void init() {
		super.init();
	}
}
