/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.junction.functional.F.*;
import de.fu.bakery.orm.java.filters.Filters;
import de.fu.scetris.data.Role;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.annotation.meta.Author;

@Author("Julian Fleischer")
public class DeleteRoles extends AbstractForm {
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	@Multiple
	public Integer[] roles;

	@Override
	public boolean commit() throws Exception {
		if ((roles == null) || (roles.length == 0)) {
			return false;
		}
		// FIXME: Permissions
		manager().beginTransaction();
		for (Role $role : manager().getRole(any(map(Filters.eq(Role.Id), roles)))) {
			// delete $role
		}
		manager().commitTransaction();
		return true;
	}
}
