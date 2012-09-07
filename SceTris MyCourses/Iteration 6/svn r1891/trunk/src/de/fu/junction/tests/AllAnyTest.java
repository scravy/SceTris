/* AllAnyTest.java / 7:30:46 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.junction.functional.C;
import de.fu.junction.functional.F;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class AllAnyTest {

	@Test
	public void testAllEquals() {
		Integer[] numbers1 = new Integer[] { 1, 1, 1 };
		Integer[] numbers2 = new Integer[] { 1, 2, 3 };
		assertTrue(F.all(C.eq(1), numbers1));
		assertFalse(F.all(C.eq(1), numbers2));
		assertFalse(F.all(C.eq(2), numbers2));
		assertFalse(F.all(C.eq(3), numbers2));
		assertFalse(F.all(C.eq(4), numbers2));
	}

	@Test
	public void testAllEqualsString() {
		String string1 = "aaa";
		String string2 = "bbb";
		String string3 = "abc";
		assertTrue(F.all(C.eq('a'), string1));
		assertFalse(F.all(C.eq('b'), string1));
		assertFalse(F.all(C.eq('c'), string1));
		assertFalse(F.all(C.eq('a'), string2));
		assertTrue(F.all(C.eq('b'), string2));
		assertFalse(F.all(C.eq('c'), string2));
		assertFalse(F.all(C.eq('a'), string3));
		assertFalse(F.all(C.eq('b'), string3));
		assertFalse(F.all(C.eq('c'), string3));
	}

	@Test
	public void testAnyEquals() {
		Integer[] numbers1 = new Integer[] { 1, 1, 1 };
		Integer[] numbers2 = new Integer[] { 1, 2, 3 };
		assertTrue(F.any(C.eq(1), numbers1));
		assertFalse(F.any(C.eq(2), numbers1));
		assertFalse(F.any(C.eq(3), numbers1));
		assertFalse(F.any(C.eq(4), numbers1));
		assertTrue(F.any(C.eq(1), numbers2));
		assertTrue(F.any(C.eq(2), numbers2));
		assertTrue(F.any(C.eq(3), numbers2));
		assertFalse(F.any(C.eq(4), numbers2));
	}

}
