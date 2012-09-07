/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.data.formsupport.AbstractForm;

public class EditTimetableByLecturer extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public int newts;




	@Override
	public boolean commit() throws Exception {
		ProposedScheduling $ps = (ProposedScheduling) session().getValue("x_ps");
		session().setValue("x_ps", null);
		if($ps == null) {
			return true;
		}
		Timeslot $ts = manager().getTimeslot(newts);
		$ps.setTimeslot($ts).pushChanges();
		return true;
	}

	@Override
	public void init() {
	}
}
