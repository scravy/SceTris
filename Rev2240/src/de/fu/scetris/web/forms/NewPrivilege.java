/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.weave.orm.filters.Filters.*;
import de.fu.scetris.data.Privilege;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

public class NewPrivilege extends Privilege.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708131192421344446L;

	public Validator<String> name$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (manager().getPrivilege(all(eq(Privilege.Name, name))).size() > 0) {
				throw new ValidationException("mustBeUnique");
			}
			return true;
		}
	};

	public Privilege $privilegeCreated;

	@Override
	public boolean commit() throws Exception {
		$privilegeCreated = manager().createPrivilege(name);
		return true;
	}
}
