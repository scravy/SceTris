/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import static de.fu.bakery.orm.java.filters.Filters.*;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Department;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

@Author("Julian Fleischer")
public class EditBuilding extends NewBuilding {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	public Validator<String> name$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			String $nom = manager().getBuilding(id).getName();
			if (($nom != null) && !$nom.equalsIgnoreCase(name)) {
				if (manager().getBuilding(all(eq(Building.Name, name))).size() > 0) {
					throw new ValidationException("alreadyTaken");
				}
			}
			return true;
		}
	};

	@Override
	public boolean commit() throws Exception {
		Department $dep = department != null ? manager().getDepartment(department) : null;
		manager().getBuilding(id)
				.setName(name)
				.setDepartment($dep)
				.setAddress(address)
				.pushChanges();
		return true;
	}

}
