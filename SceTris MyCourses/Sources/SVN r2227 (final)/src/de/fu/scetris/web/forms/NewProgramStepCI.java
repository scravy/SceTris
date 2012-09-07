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

import de.fu.scetris.data.Course;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;

public class NewProgramStepCI extends Program.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    /* Override the two fields freezed and publish for creation */
    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int academicTerm;

    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public int department;

    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public boolean freezed;

    @de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
    public boolean published;

    public Map<Integer,Course> $courses = new TreeMap<Integer,Course>();
    public Map<Integer,String> $namedCourses = new TreeMap<Integer,String>();

    @Field
    @Multiple
    @Alternatives("$namedCourses")
    @Pos(10)
    public int[] courses = {};

    @Override
    public boolean commit() throws Exception {
        /* If no course is selected, deny request(TODO:: use validator instead) */
        if (courses.length < 1) {
            return false;
        }
        init();

        Person $pm = manager().getPerson(programManager);
        Program $new = (Program) session().getValue("newpro");

        manager().beginTransaction();
        $new.setProgramManager($pm).create();

        for (int $course : courses) {
            manager().createCourseInstance($new, $courses.get($course),
                    $new.getAcademicTerm().getStartLessons(),
                    $new.getAcademicTerm().getEndLessons(), $pm);
        }
        manager().commitTransaction();

        /* IMPORTANT */
        id = $new.getId();
        return true;
    }

    @Override
    public void init() {
        super.init();
        try {
            Program $new = (Program) session().getValue("newpro");

            List<Person> $lecturer = manager().getPerson(
                    all(eq(Person.IsLecturer, true),
                            eq(Person.HomeDepartment, $new.getDepartment()
                                    .getId())));
            for (Person $lect : $lecturer) {
                programManager$alternatives.put($lect.getId(),
                        $lect.getLastName() + ", " + $lect.getFirstName());
            }

            List<Course> $tmp = manager().getCourse(
                all(eq(Course.Department, $new.getDepartment().getId())));
            for (Course $x : $tmp) {
                $courses.put($x.id(), $x);
                $namedCourses.put($x.id(), $x.getName());
            }

        } catch (Exception $exc) {}
    }

}
