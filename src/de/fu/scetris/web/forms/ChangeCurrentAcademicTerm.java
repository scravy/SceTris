/* EditCET.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import java.util.List;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Configuration;
import de.fu.scetris.data.formsupport.AbstractForm;
import static de.fu.weave.orm.filters.Filters.*;

public class ChangeCurrentAcademicTerm extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -728132292494443346L;

	@Field
	@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
	public int at;	
	
	@Override
	public boolean commit() throws Exception {
		AcademicTerm $at = manager().getAcademicTerm(at);
		List<Configuration> $cs = manager().getConfiguration(all(eq(Configuration.Key, "currentTerm")));
		if($cs.size() > 0 ){
			$cs.get(0).setValue($at.getName());
			$cs.get(0).pushChanges();
		} else {
			manager().fullyCreateConfiguration("currentTerm", $at.getName());
		}
		return true;
	}
}
