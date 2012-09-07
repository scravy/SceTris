/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.weave.orm.filters.Filters.*;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Attribute;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

@Author("Julian Fleischer")
public class NewRole extends Role.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	public Validator<String> name$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (manager().getRole(all(eq(Role.Name, name))).size() > 0) {
				throw new ValidationException("mustBeUnique");
			}
			return true;
		}
	};

	public Map<Integer,Attribute> $attributes = new TreeMap<Integer,Attribute>();

	public Map<Integer,String> $namedAttributes = new TreeMap<Integer,String>();

	public Map<Integer,Privilege> $privileges = new TreeMap<Integer,Privilege>();

	public Map<Integer,String> $namedPrivileges = new TreeMap<Integer,String>();

	@Field
	@Multiple
	@Alternatives("$namedAttributes")
	@Pos(21)
	public int[] attributes = {};

	@Field
	@Multiple
	@Alternatives("$namedPrivileges")
	@Pos(20)
	public int[] privileges = {};

	public Role $roleCreated;

	@Override
	public boolean commit() throws Exception {
		init();
		$roleCreated = manager().createRole(name);
		for (int $id : privileges) {
			System.out.println("Privilege: " + $id);
			Privilege $privilege = $privileges.get($id);
			manager().createRoleImpliesPrivilege($roleCreated, $privilege);
		}
		return true;
	}

	@Override
	public void init() {
		try {
			List<Privilege> $privileges = manager().getPrivilege();
			for (Privilege $privilege : $privileges) {
				this.$privileges.put($privilege.id(), $privilege);
				$namedPrivileges.put($privilege.id(), $privilege.getName());
			}
			List<Attribute> $attributes = manager().getAttribute();
			for (Attribute $attribute : $attributes) {
				this.$attributes.put($attribute.id(), $attribute);
				$namedAttributes.put($attribute.id(), $attribute.getName());
			}
		} catch (Exception $exc) {
		}
	}
}
