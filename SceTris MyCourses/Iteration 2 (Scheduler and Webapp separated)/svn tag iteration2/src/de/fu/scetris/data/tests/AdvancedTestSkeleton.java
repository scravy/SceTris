/* EntitiesAndRelationships.java / 6:07:41 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.weave.util.MD5.*;
import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;

import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Group;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.Year;
import de.fu.weave.tests.DatabaseTestSkeleton;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class AdvancedTestSkeleton extends DatabaseTestSkeleton {

	protected int PERSONS = 300;
	protected int LECTURERS = 15;
	protected int COURSES = 40;
	protected int GROUPS = 8;

	protected Person[] persons;
	protected Person[] lecturers;
	protected Group[] groups;
	protected Course[] courses;
	protected CourseInstance[] instances;
	protected Year year;
	protected Program program;
	protected Department department;
	protected AcademicTerm term;
	protected Person admin;
	protected Person pm;

	/**
	 * 
	 * @since Iteration3
	 */
	@Override
	protected void _init() throws Exception {
		TestNames names = new TestNames();

		manager.beginTransaction();

		persons = new Person[PERSONS];
		lecturers = new Person[LECTURERS];
		groups = new Group[GROUPS];
		courses = new Course[COURSES];
		instances = new CourseInstance[COURSES];
		year = manager.createYear("this year");

		admin = manager.newPerson("The", "One", "admin", "disabled");
		admin.setIsSuperuser(true);
		admin.create();
		pm = manager.newPerson("Program", "Manager", "some_guy", "disabled");
		pm.create();

		for (int i = 0; i < PERSONS; i++) {
			TestNames.Name name = names.nextName();
			persons[i] = manager.createPerson(name.firstName, name.lastName,
											  "user" + i, md5("pw" + i));
		}
		for (int i = 0; i < LECTURERS; i++) {
			TestNames.Name name = names.nextName();
			lecturers[i] = manager.createPerson(name.firstName, name.lastName,
												"lecturer" + i, md5("pw" + i));
		}

		for (char c = 'A', i = 0; c <= 'F'; c++, i++) {
			groups[i] = manager.createGroup("Group" + c);
		}

		term = manager.createAcademicTerm("this term",
													   Date.valueOf("1970-01-01"),
													   Date.valueOf("1970-06-30"),
													   Date.valueOf("1970-01-15"),
													   Date.valueOf("1970-06-15"));
		department = manager.createDepartment("The department");
		program = manager.createProgram(term, department, pm);

		for (int i = 0; i < COURSES; i++) {
			courses[i] = manager.createCourse("Course " + i);
			instances[i] = manager.createCourseInstance(program, courses[i],
														Date.valueOf("1970-01-15"),
														Date.valueOf("1970-01-15"),
														lecturers[i % LECTURERS]);
		}

		manager.commitTransaction();
	}

	/**
	 * Since this is a skeleton there is only the empty test, which serves as indicator for the
	 * initialization working or not.
	 * 
	 * @since Iteration3
	 */
	@Test
	public void createManyEntities() {
		assertTrue(true);
	}
}
