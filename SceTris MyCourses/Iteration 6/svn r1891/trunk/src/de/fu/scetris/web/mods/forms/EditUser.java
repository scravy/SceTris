/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import static de.fu.bakery.orm.java.filters.Filters.*;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.junction.Arrays;
import de.fu.junction.data.Password;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.ValidationException;

public class EditUser extends NewUser {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	public Password loginPassword;

	public Validator<String> loginName$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (!manager().getPerson(id).getLoginName().equalsIgnoreCase(loginName)) {
				if (manager().getPerson(all(eq(Person.LoginName, loginName))).size() > 0) {
					throw new ValidationException("alreadyTaken");
				}
			}
			return true;
		}
	};

	public EditUser() {
		super();
		disable("loginName");
	}

	@Override
	public boolean commit() throws Exception {
		// the following loads additional info which was available when XMLing the form, too
		init();

		// this sets the new values
		Person $currentPerson = manager().getPerson(id)
				.setAdditionalNames(additionalNames)
				.setEmailAddress(emailAddress == null ? null : emailAddress.toString())
				.setFirstName(firstName)
				.setIsSuperuser(isSuperuser)
				.setLastName(lastName)
				.setLoginName(loginName)
				.setIsLecturer(isLecturer)
				.setIsStudent(isStudent)
				.setWorkingHours(workingHours);
		if (user().hasPrivilege("edit.password", "user" + id) && (loginPassword != null)) {
			$currentPerson.setLoginPassword(loginPassword.getPassword());
		}
		// this applies the changes
		$currentPerson.pushChanges();

		// calculate changes regarding roles
		Set<Integer> $currentRoles = Arrays.asSet(getRoles($currentPerson));
		Set<Integer> $newRoles = Arrays.asSet(roles);
		Set<Integer> $removeRoles = new TreeSet<Integer>($currentRoles);
		$removeRoles.removeAll($newRoles);
		$newRoles.removeAll($currentRoles);

		// apply those changes
		for (int $role : $removeRoles) {
			manager().deletePersonHasRole($currentPerson, $roles.get($role));
		}
		for (int $role : $newRoles) {
			manager().createPersonHasRole($currentPerson, $roles.get($role));
		}

		// same with privileges
		Set<Integer> $currentPrivileges = Arrays.asSet(getPrivileges($currentPerson));
		Set<Integer> $newPrivileges = Arrays.asSet(privileges);
		Set<Integer> $removePrivileges = new TreeSet<Integer>($currentPrivileges);
		$removePrivileges.removeAll($newPrivileges);
		$newPrivileges.removeAll($currentPrivileges);

		for (int $role : $removePrivileges) {
			manager().deletePersonHasPrivilege($currentPerson, $privileges.get($role));
		}
		for (int $role : $newPrivileges) {
			manager().createPersonHasPrivilege($currentPerson, $privileges.get($role));
		}

		// everything’s ok
		return true;
	}

	protected int[] getPrivileges(final Person $p) {
		try {
			List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
			int[] $privilegesArray = new int[$privileges.size()];
			int $i = 0;
			for (Privilege $privilege : $privileges) {
				$privilegesArray[$i++] = $privilege.id();
			}
			return $privilegesArray;
		} catch (DatabaseException $exc) {
			return null;
		}
	}

	protected int[] getRoles(final Person $p) {
		try {
			List<Role> $roles = $p.objectsOfPersonHasRole();
			int[] $rolesArray = new int[$roles.size()];
			int $i = 0;
			for (Role $privilege : $roles) {
				$rolesArray[$i++] = $privilege.id();
			}
			return $rolesArray;
		} catch (DatabaseException $exc) {
			return null;
		}
	}

	@Override
	public void init() {
		super.init();
		if (!user().hasPrivilege("edit:password")
				&& !user().hasPrivilege("edit:password", "user:" + id)) {
			hide("loginPassword");
		}
	}

	@Override
	public EditUser setValues(final Person $p) {
		super.setValues($p);
		privileges = getPrivileges($p);
		roles = getRoles($p);
		return this;
	}
}
