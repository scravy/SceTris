/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.formsupport.AbstractForm;

public class ShowTimetableByLecturer extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public int ps_id;
	
	@Field
	public int ps_dura;



	@Override
	public boolean commit() throws Exception {
		ProposedScheduling $ps = manager().getProposedScheduling(ps_id);

		session().setValue("x_ps", $ps);
		session().setValue("x_ps_dura", ps_dura);
		return true;
	}

	@Override
	public void init() {
	}
}
