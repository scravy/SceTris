/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import static de.fu.weave.orm.filters.Filters.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import de.fu.junction.Arrays;
import static de.fu.junction.MD5.md5;
import de.fu.junction.data.Password;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.Role;
import de.fu.weave.annotation.CheckPrivilege;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.orm.DatabaseException;
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

    @SuppressWarnings("unchecked")
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
                .setHomeDepartment($departments.get(homeDepartment))
                .setIsLecturer(isLecturer)
                .setIsStudent(isStudent);
        if (user().hasPrivilege("edit.password", "user" + id) && (loginPassword != null)) {
            $currentPerson.setLoginPassword(md5(loginPassword.getPassword()));
        }
        // this applies the changes
        $currentPerson.pushChanges();

        // calculate changes regarding roles
        Set<Integer> $currentRoles = Arrays.as(HashSet.class, getRoles($currentPerson));
        Set<Integer> $newRoles = Arrays.as(HashSet.class, roles);
        Set<Integer> $removeRoles = new HashSet<Integer>($currentRoles);
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
        Set<Integer> $currentPrivileges = Arrays.as(HashSet.class, getPrivileges($currentPerson));
        Set<Integer> $newPrivileges = Arrays.as(HashSet.class, privileges);
        Set<Integer> $removePrivileges = new TreeSet<Integer>($currentPrivileges);
        $removePrivileges.removeAll($newPrivileges);
        $newPrivileges.removeAll($currentPrivileges);

        for (Integer $role : $removePrivileges) {
            manager().deletePersonHasPrivilege($currentPerson, $privileges.get($role));
        }
        for (Integer $role : $newPrivileges) {
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
    @CheckPrivilege("admin.editUser/password")
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
