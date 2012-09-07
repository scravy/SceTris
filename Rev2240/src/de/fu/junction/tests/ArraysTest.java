/* ArraysTest.java / 6:12:54 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static junit.framework.Assert.*;

import org.junit.Test;

import de.fu.junction.Arrays;

/**
 *
 */
public class ArraysTest {
	@Test
	public void testContains() {
		int[] $a = { 1, 2, 3, 4, 5 };
		assertTrue(Arrays.contains($a, 1));
		assertTrue(Arrays.contains($a, 2));
		assertTrue(Arrays.contains($a, 3));
		assertTrue(Arrays.contains($a, 4));
		assertTrue(Arrays.contains($a, 5));
		assertFalse(Arrays.contains($a, 0));
	}

	@Test
	public void testShuffle() {
		int[] $a = { 1, 2, 3, 4, 5 };
		Arrays.shuffle($a);
		assertTrue(Arrays.contains($a, 1));
		assertTrue(Arrays.contains($a, 2));
		assertTrue(Arrays.contains($a, 3));
		assertTrue(Arrays.contains($a, 4));
		assertTrue(Arrays.contains($a, 5));
		assertEquals(5, $a.length);
	}
}
