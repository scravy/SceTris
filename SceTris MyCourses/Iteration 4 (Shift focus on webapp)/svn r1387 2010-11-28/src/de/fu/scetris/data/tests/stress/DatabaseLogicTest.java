/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests.stress;

import static org.junit.Assert.*;

import org.junit.Test;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;
import de.fu.scetris.data.Course;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class DatabaseLogicTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void checkCircularReferencesInCourseDependencies() throws DatabaseException {
		Course course1 = manager.createCourse("Course1");
		Course course2 = manager.createCourse("Course2");
		assertNotNull(course1);
		assertNotNull(course2);
		try {
			manager.createCourseRequiresCourse(course1, course2);
			manager.createCourseRequiresCourse(course2, course1);
			fail("circular references created");
		} catch (DatabaseException e) {
		}
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void checkLessEasyCircularReferencesInCourseDependencies() throws DatabaseException {
		Course course1 = manager.createCourse("Course1");
		Course course2 = manager.createCourse("Course2");
		Course course3 = manager.createCourse("Course3");
		assertNotNull(course1);
		assertNotNull(course2);
		assertNotNull(course3);
		manager.createCourseRequiresCourse(course1, course2);
		manager.createCourseRequiresCourse(course2, course3);
		try {
			manager.createCourseRequiresCourse(course3, course1);
			fail("circular references created");
		} catch (DatabaseException e) {
		}
	}

}
