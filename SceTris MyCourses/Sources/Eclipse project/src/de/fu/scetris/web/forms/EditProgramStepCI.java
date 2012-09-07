/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.weave.orm.filters.Filters.all;
import static de.fu.weave.orm.filters.Filters.eq;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.fu.junction.Arrays;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.weave.orm.DatabaseException;

public class EditProgramStepCI extends Program.Form {

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

	public Map<Integer, Course> $courses = new TreeMap<Integer, Course>();
	public Map<Integer, String> $namedCourses = new TreeMap<Integer, String>();

	@Field
	@Multiple
	@Alternatives("$namedCourses")
	@Pos(10)
	public int[] courses = {};

    @SuppressWarnings("unchecked")
	@Override
	public boolean commit() throws Exception {
		/* If no course is selected, deny request(TODO:: use validator instead) */
		if (courses.length < 1) {
			return false;
		}
		init();

		Person $pm = manager().getPerson(programManager);
		Program $current = manager().getProgram(id);

		manager().beginTransaction();
		$current
			.setProgramManager($pm)
			.pushChanges();

	
        // calculate changes regarding courses
        Set<Integer> $currentRoles = Arrays.as(HashSet.class, getCourses($current));
        Set<Integer> $newRoles = Arrays.as(HashSet.class, courses);
        Set<Integer> $removeRoles = new HashSet<Integer>($currentRoles);
        $removeRoles.removeAll($newRoles);
        $newRoles.removeAll($currentRoles);

        // apply those changes
        for (int $role : $removeRoles) {
            //del ($new, $courses.get($role));
        	manager().deleteCourseInstance(eq(CourseInstance.InstanceOf, $courses.get($role).getId()));
        }
        for (int $role : $newRoles) {
        	manager().createCourseInstance($current, $courses.get($role),
					$current.getAcademicTerm().getStartLessons(),
					$current.getAcademicTerm().getEndLessons(), $pm);
            //create ($new, $courses.get($role));
        }		   
		
		manager().commitTransaction();

		/* IMPORTANT */
		id = $current.getId();
		return true;
	}

	@Override
	public void init() {
		try {
			Program $new = manager().getProgram(id);

			List<Person> $lecturer = manager().getPerson(
					all(eq(Person.IsLecturer, true),
							eq(Person.HomeDepartment, $new.getDepartment()
									.getId())));
			for (Person $lect : $lecturer) {
				programManager$alternatives.put($lect.getId(),
						$lect.getLastName() + ", " + $lect.getFirstName());
			}

			List<Course> $tmp = manager().getCourse(all(eq(Course.Department, $new.getDepartment().getId())));
			for (Course $x : $tmp) {
				$courses.put($x.id(), $x);
				$namedCourses.put($x.id(), $x.getName());
			}

		} catch (Exception $exc) {
		}
	}
	
	
    @Override
    public EditProgramStepCI setValues(final Program $p) {
        super.setValues($p);
        courses = getCourses($p);
        return this;
    }
	
	protected int[] getCourses(Program $p) {
        try {
            List<Course> $courses = manager().getCourse(eq(Course.Department, $p.getDepartment().getId()));
          
            List<CourseInstance> $tmp = manager().getCourseInstance(all(eq(CourseInstance.Program, $p.getId())));                   
      
            int[] $rolesArray = new int[$tmp.size()];
            int $i = 0;
            for (CourseInstance $privilege : $tmp) {
                $rolesArray[$i++] = $privilege.getInstanceOf().getId();
            }
            return $rolesArray;
        } catch (DatabaseException $exc) {
            return null;
        }
	}		
}
