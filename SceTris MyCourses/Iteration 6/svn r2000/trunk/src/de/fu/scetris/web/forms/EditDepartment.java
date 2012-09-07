/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.weave.orm.filters.Filters.*;
import de.fu.scetris.data.Department;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

public class EditDepartment extends NewDepartment {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	public Validator<String> name$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (!manager().getDepartment(id).getName().equalsIgnoreCase(name)) {
				if (manager().getDepartment(all(eq(Department.Name, name))).size() > 0) {
					throw new ValidationException("alreadyTaken");
				}
			}
			return true;
		}
	};

	public Validator<Integer> superordinateDepartment$validator = new Validator<Integer>() {
		@Override
		public boolean check(final Integer $id) throws Exception {
			if ($id == id) {
				throw new ValidationException("canNotBeSubordinateToItself");
			}
			return true;
		}
	};

	@Override
	public boolean commit() throws Exception {
		manager().getDepartment(id)
				.setName(name)
				.setSuperordinateDepartment(manager().getDepartment(superordinateDepartment))
				.pushChanges();
		return true;
	}
}
