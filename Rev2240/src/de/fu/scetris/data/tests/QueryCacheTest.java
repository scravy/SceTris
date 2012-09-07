/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Course;

@Author("Julian Fleischer")
public class QueryCacheTest extends DatabaseTestSkeleton {

	@Test
	public void testQueryCache() throws Exception {
		manager.enableQueryCache();

		int $id = manager.createCourse("one").id();

		Course[] $c = new Course[5];
		$c[0] = manager.getCourse($id);
		$c[1] = manager.getCourse($id);
		$c[2] = manager.getCourse($id);
		$c[3] = manager.getCourse($id);
		$c[4] = manager.getCourse($id);

		for (Course $cc : $c) {
			assertEquals("one", $cc.getName());
		}
	}
}
