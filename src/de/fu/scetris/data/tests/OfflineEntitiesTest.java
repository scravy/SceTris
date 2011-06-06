/* OfflineEntities.java / 2:49:00 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.junction.MD5.*;
import static org.junit.Assert.*;

import java.sql.Date;

import org.junit.Test;


import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Department;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Program;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class OfflineEntitiesTest {

	RelationManager manager = new RelationManager();

	@Test
	public void complexTest() throws DatabaseException {
		AcademicTerm term = manager.newAcademicTerm("Next Semester",
													Date.valueOf("1970-01-01"),
													Date.valueOf("1970-06-30"),
													Date.valueOf("1970-01-15"),
													Date.valueOf("1970-06-15"));
		assertNotNull(term);

		Person person =
				manager.newPerson("Mister", "Misterman", "herrmann", md5("randomlyGenerated"));
		assertNotNull(person);

		Person programManager =
				manager.newPerson("Misses", "Dingbat", "dingbat", md5("egonWuerdGernSterben"));
		assertNotNull(programManager);

		person.setDeletedBy(programManager);

		Department department = manager.newDepartment("Department of Mathematics");
		assertNotNull(department);

		Course course = manager.newCourse("The dark arts of Analysis");
		assertNotNull(course);

		Program program = manager.newProgram(term, department, programManager);
		assertNotNull(program);

		CourseInstance courseInstance = manager.newCourseInstance(program, course,
																  Date.valueOf("1970-01-22"),
																  Date.valueOf("1970-06-08"),
																  person);
		assertNotNull(courseInstance);
		assertEquals(courseInstance.getMainLecturer().id(), person.id());
		assertTrue(courseInstance.getMainLecturer().getDeletedBy().equals(programManager));
	}

	@Test
	public void testDepartment() throws DatabaseException {
		Department department = manager.newDepartment("Department of Computer Science");

		assertNotNull(department);
		assertEquals(department.getName(), "Department of Computer Science");
	}

	@Test
	public void testPerson() throws DatabaseException {
		Person person = manager.newPerson("Random", "Hacker", "rjhacker", md5("randomPassword"));

		assertFalse(person.isAdditionalNamesChanged());
		person.setAdditionalNames("J.");
		assertTrue(person.isAdditionalNamesChanged());

		assertNotNull(person);

		assertEquals(person.getFirstName(), "Random");
		assertEquals(person.getAdditionalNames(), "J.");
		assertEquals(person.getLastName(), "Hacker");

		assertNull(person.getDeletedBy());
	}

}
