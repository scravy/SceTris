/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import static de.fu.weave.orm.filters.Filters.*;

import java.util.List;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Department;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.reflect.ValidationException;

@Author("Julian Fleischer")
public class NewDepartment extends Department.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    public Validator<String> name$validator = new Validator<String>() {
        @Override
        public boolean check(final String $name) throws Exception {
            if (manager().getDepartment(all(eq(Department.Name, name))).size() > 0) {
                throw new ValidationException("mustBeUnique");
            }
            return true;
        }
    };

    public Department $createdDepartment;

    public NewDepartment() {
        superordinateDepartment$alternatives.put(-1, "");
    }

    @Override
    public boolean commit() throws Exception {
        $createdDepartment = manager()
                .newDepartment(name)
                .setSuperordinateDepartment(
                    superordinateDepartment == null ? null : manager().getDepartment(
                        superordinateDepartment))
                .create();
        System.out.println(superordinateDepartment);
        return true;
    }

    @Override
    public void init() {
        super.init();
        try {
            List<Department> $departments = manager().getDepartment();
            for (Department $dep : $departments) {
                superordinateDepartment$alternatives.put($dep.id(), $dep.getName());
            }
            if ($departments.size() < 1) {
                hide("superordinateDepartment");
            }
        } catch (DatabaseException $exc) {
            System.out.println("There was an error: " + $exc);
            superordinateDepartment$alternatives = null;
        }
    }
}
