/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import java.util.List;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Department;

public class NewBuilding extends Building.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -129132292342494443L;

	public NewBuilding() {
		department$alternatives.put(-1, "");
	}

	@Override
	public boolean commit() throws Exception {
		manager().newBuilding(address)
				.setName(name)
				.setDepartment(manager().getDepartment(department))
				.create();
		return true;
	}

	@Override
	public void init() {
		super.init();
		try {
			List<Department> $departments = manager().getDepartment();
			for (Department $dep : $departments) {
				department$alternatives.put($dep.id(), $dep.getName());
			}
		} catch (DatabaseException $exc) {
			System.out.println("There was an error: " + $exc);
			department$alternatives = null;
		}
	}
}
