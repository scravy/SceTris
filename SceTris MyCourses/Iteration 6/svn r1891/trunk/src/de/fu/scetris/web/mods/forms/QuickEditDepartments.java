/* QuickEditDepartments.java / 7:20:43 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.formsupport.AbstractForm;

public class QuickEditDepartments extends AbstractForm {

	private static final long serialVersionUID = 4389605158990029474L;

	@Field
	@Alternatives("departments")
	public int department;

	public Map<Integer,String> departments = new TreeMap<Integer,String>();

	@Override
	public boolean commit() throws Exception {
		return true;
	}

	@Override
	public void init() {
		try {
			List<Department> $deps = manager().getDepartment();
			for (Department $dep : $deps) {
				departments.put($dep.id(), $dep.getName());
			}
		} catch (DatabaseException $exc) {

		}
	}

}
