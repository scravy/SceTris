/* NewProgramStep2.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;

public class NewProgramStepMainLecturer extends Program.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Field
    @Multiple
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int[] ci = {};

    @Field
    @Multiple
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int[] ml = {};

    @Override
    public boolean commit() throws Exception {
        manager().beginTransaction();
        for (int $i = 0; $i < ci.length; $i++) {
            CourseInstance $ci = manager().getCourseInstance(ci[$i]);
            Person $p = manager().getPerson(ml[$i]);
            $ci
                    .setMainLecturer($p)
                    .pushChanges();

        }
        manager().commitTransaction();
        return true;
    }

    @Override
    public void init() {

    }
}
