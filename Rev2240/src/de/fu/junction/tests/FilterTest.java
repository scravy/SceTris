/* FilterTest.java / 7:28:47 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import java.util.Arrays;

import org.junit.Test;

import static org.junit.Assert.*;

import de.fu.junction.Strings;
import de.fu.junction.functional.C;
import de.fu.junction.functional.F;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class FilterTest {
	@Test
	public void testFilterArrayWithInstanceOf() {
		Object[] objs = new Object[] { "hallo", "welt", 7, Math.PI };
		assertArrayEquals(new Object[] { "hallo", "welt" }, F.filter(C.is(String.class), objs));
		assertArrayEquals(new Object[] { 7, Math.PI }, F.filter(C.is(Number.class), objs));
		assertArrayEquals(new Double[] { Math.PI }, F.filter(C.is(Double.class), objs));
	}

	@Test
	public void testFilterListWithLessThan() {
		Integer[] ints = new Integer[] { 1, 2, 3, 4, 5, 6 };
		assertEquals("1:2:3", Strings.implode(":", F.filter(C.lt(4), Arrays.asList(ints))));
	}
}
