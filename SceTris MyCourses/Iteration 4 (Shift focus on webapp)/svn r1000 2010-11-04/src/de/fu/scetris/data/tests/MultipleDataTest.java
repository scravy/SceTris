/* DataTest.java / 6:32:26 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import de.fu.weave.tests.DatabaseTestSkeleton;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@RunWith(Theories.class)
public class MultipleDataTest extends DatabaseTestSkeleton {

	@DataPoints
	public static String[] names = new String[] { "a", "b", "c" };

	@Theory
	public void test(final String name, final String name2) {
		System.out.println(name + " / " + name2);
	}
}
