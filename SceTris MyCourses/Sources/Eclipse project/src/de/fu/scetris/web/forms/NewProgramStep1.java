/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import java.util.List;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.weave.User;

public class NewProgramStep1 extends Program.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    /* Override the two fields freezed and publish for creation */
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int programManager;
    
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public boolean freezed;

    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public boolean published;

    @Override
    public boolean commit() throws Exception {
        /* If no course is selected, deny request(TODO:: use validator instead) */
        init();

        AcademicTerm $at = manager().getAcademicTerm(academicTerm);
        Department $dept = manager().getDepartment(department);
        
        Person $currentPerson = null;
        User $currentUser = session().getUser();
        if ($currentUser instanceof Person) {
            $currentPerson = (Person) $currentUser;
        } else
        	return false;
        
        Program $new = manager().newProgram($at, $dept, $currentPerson);
        $new
                .setFreezed(false)
                .setPublished(false);
        session().setValue("newpro", $new);
        return true;
    }

    @Override
    public void init() {
        try {
            List<AcademicTerm> $ats = manager().getAcademicTerm();
            for (AcademicTerm $at : $ats) {
                academicTerm$alternatives.put($at.id(), $at.getName());
            }

            List<Department> $depts = manager().getDepartment();
            for (Department $dept : $depts) {
                department$alternatives.put($dept.id(), $dept.getName());
            }
            
        } catch (Exception $exc) {}
    }
}
