/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import static de.fu.junction.functional.F.*;
import static de.fu.weave.orm.filters.Filters.*;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.PersonHasRole;
import de.fu.scetris.data.Role;
import de.fu.scetris.data.RoleImpliesAttribute;
import de.fu.scetris.data.RoleImpliesPrivilege;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.orm.filters.Filters;

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
        synchronized (manager()) {
            manager().beginTransaction();
            for (Role $role : manager().getRole(any(map(Filters.eq(Role.Id), roles)))) {
                for (PersonHasRole $rel : $role.whereObjectOfPersonHasRole()) {
                    $rel.delete();
                }
                for (RoleImpliesAttribute $rel : $role.whereSubjectOfRoleImpliesAttribute()) {
                    $rel.delete();
                }
                for (RoleImpliesPrivilege $rel : $role.whereSubjectOfRoleImpliesPrivilege()) {
                    $rel.delete();
                }
                $role.delete();
            }
            manager().commitTransaction();
        }
        return true;
    }
}
