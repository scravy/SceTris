/* Performance.java / 4:32:48 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests.gnooool;

import static de.fu.weave.util.MD5.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.weave.orm.DatabaseException;
import de.fu.weave.tests.DatabaseTestSkeleton;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class StressTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert1000() throws DatabaseException {
		for (int i = 0; i < 1000; i++) {
			manager.createCourse("c" + i);
		}
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert10000() throws DatabaseException {
		for (int i = 0; i < 10000; i++) {
			manager.createCourse("c" + i);
		}
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert100000() throws DatabaseException {
		int i = 10000;
		for (; i > 0; i--) {
			manager.createDepartment("dep" + i);
			manager.createAttribute("attr" + i);
			manager.createPerson("first", "last", "user" + i, md5("pw" + i));
			manager.createCourse("c" + i);
			manager.createCourseElementType("ct" + i);
			manager.createYear("y" + i);
			manager.createBuilding("b" + i);
			manager.createDay("day" + i);
			manager.createFeature("f" + i);
			manager.createGroup("g" + i);
		}
		assertTrue(i == 0);
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert100000wTransaction() throws DatabaseException {
		manager.beginTransaction();
		insert100000();
		manager.commitTransaction();
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert10000withTransaction() throws DatabaseException {
		manager.beginTransaction();
		insert10000();
		manager.commitTransaction();
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void insert1000withTransaction() throws DatabaseException {
		manager.beginTransaction();
		insert1000();
		manager.commitTransaction();
	}
}
