/* FormReflectorTest.java / 11:29:27 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import java.lang.reflect.Array;

import org.junit.Test;

/**
 *
 */
public class FormReflectorTest {

	@Test
	public void test() {
		Object $x = Array.newInstance(int.class, 7);
		System.out.println($x.getClass());
		System.out.println($x.getClass().getComponentType());
	}

}
