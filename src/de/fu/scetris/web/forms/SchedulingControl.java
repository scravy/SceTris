/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import static de.fu.weave.orm.filters.Filters.*;

import java.util.List;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.formsupport.AbstractForm;

public class SchedulingControl extends AbstractForm {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Field
    public int department;

    @Field
    public int academicTerm;

    public Program $showMe;

    @Override
    public boolean commit() throws Exception {

        AcademicTerm $at = manager().getAcademicTerm(academicTerm);
        Department $dept = manager().getDepartment(department);
        if (($at != null) && ($dept != null)) {
            List<Program> $programs = manager().getProgram(
                all(eq(Program.AcademicTerm, $at.getId()), eq(Program.Department, $dept.getId())));
            if ($programs.size() > 0) {
                $showMe = $programs.get(0);
                return true;
            }
        }

        return false;

        /*
         * final SchedulerManager schedulerManager = SchedulerManager .getInstance(manager());
         * 
         * try { List<Program> programList = manager().getProgram(); for (Program program :
         * programList) { if ((program.getAcademicTerm().getId() == academicTerm) &&
         * (program.getDepartment().getId() == department)) { possibleProgram = program; continue; }
         * } } catch (Exception e) {
         * System.out.println("could not resolve program from at"+academicTerm
         * +" and dept"+department); }
         * 
         * 
         * if (action.equals("Publish")) {
         * 
         * List<Program> programList = manager().getProgram();
         * 
         * for (Program program : programList) { if ((program.getAcademicTerm().getId() ==
         * academicTerm) && (program.getDepartment().getId() == department)) {
         * System.out.println("-> publishing program :: "+program.id());
         * schedulerManager.freezeProgram(program); schedulerManager.publishProgram(program); } } }
         * 
         * if ( action.equals("Start") || action.equals("Resume")) { // put("status", "running");
         * schedulerstatus = "running";
         * 
         * List<Program> programList = (List<Program>) manager().getProgram( all(eq("department",
         * department), eq("academic_term", academicTerm)));
         * 
         * Program program = programList.get(0);
         * 
         * if ( action.equals("Start")) { schedulerManager.startScheduler(program, false); } else if
         * (action.equals("Resume")) { schedulerManager.startScheduler(program, true); } else { }
         * 
         * } else if (action.equals("Conflicts")) { schedulerstatus = "showConflicts"; } else if
         * (action.equals("Stop")) { schedulerstatus = "stopping"; // put("status", "stopping");
         * schedulerManager.stopScheduler(); } else { } return true;
         */
    }
}
