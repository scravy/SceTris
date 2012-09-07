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
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

@Author("Julian Fleischer")
public class NewUser extends Person.Form {

	private static final long serialVersionUID = -708132292494443346L;

	public LoginNameSanitizer loginName$converter = new LoginNameSanitizer();

	public Validator<String> loginName$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (manager().getPerson(all(eq(Person.LoginName, loginName))).size() > 0) {
				throw new ValidationException("alreadyTaken");
			}
			return true;
		}
	};

	public Map<Integer,Privilege> $privileges = new TreeMap<Integer,Privilege>();

	public Map<Integer,String> $namedPrivileges = new TreeMap<Integer,String>();

	public Map<Integer,Role> $roles = new TreeMap<Integer,Role>();

	public Map<Integer,String> $namedRoles = new TreeMap<Integer,String>();

	@Field
	@Multiple
	@Alternatives("$namedPrivileges")
	@Pos(20)
	public int[] privileges = {};

	@Field
	@Multiple
	@Alternatives("$namedRoles")
	@Pos(21)
	public int[] roles = {};

	@Override
	public boolean commit() throws Exception {
		Person $new = manager().newPerson(firstName, lastName, loginName,
			loginPassword.getMD5());
		if (emailAddress != null) {
			$new.setEmailAddress(emailAddress.toString());
		}
		if (user() instanceof Person) {
			$new.setCreatedBy((Person) user());
		}
		manager().beginTransaction();
		if ((privileges.length > 0) || (roles.length > 0)) {
			init();
		}
		$new
				.setIsSuperuser(isSuperuser)
				.setAdditionalNames(additionalNames)
				.setWorkingHours(workingHours)
				.setIsLecturer(isLecturer)
				.setIsStudent(isStudent)
				.create();
		for (int $privilege : privileges) {
			manager().createPersonHasPrivilege($new, $privileges.get($privilege));
		}
		for (int $role : roles) {
			manager().createPersonHasRole($new, $roles.get($role));
		}
		manager().commitTransaction();
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
			List<Role> $roles = manager().getRole();
			for (Role $role : $roles) {
				this.$roles.put($role.id(), $role);
				$namedRoles.put($role.id(), $role.getName());
			}
		} catch (Exception $exc) {
		}
	}
}
