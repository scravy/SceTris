/* TakeWhileTest.java / 7:28:57 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;

import de.fu.junction.Strings;
import de.fu.junction.functional.C;
import de.fu.junction.functional.F;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class TakeDropTest {

	@Test
	public void testArrayDropWhile() {
		Integer[] numbers = new Integer[] { 1, 1, 1, 3, 4, 5 };
		numbers = F.takeWhile(C.eq(1), numbers);
		assertArrayEquals(new Integer[] { 1, 1, 1 }, numbers);
	}

	@Test
	public void testArrayTakeWhile() {
		Integer[] numbers = new Integer[] { 1, 1, 1, 3, 4, 5 };
		numbers = F.dropWhile(C.eq(1), numbers);
		assertArrayEquals(new Integer[] { 3, 4, 5 }, numbers);
	}

	@Test
	public void testIterableDropWhile() {
		Integer[] numbers = new Integer[] { 1, 1, 1, 3, 4, 5 };
		List<Integer> list = Arrays.asList(numbers);
		assertEquals("3:4:5", Strings.implode(":", F.dropWhile(C.eq(1), list)));
	}

	@Test
	public void testIterableTakeWhile() {
		Integer[] numbers = new Integer[] { 1, 1, 1, 3, 4, 5 };
		List<Integer> list = Arrays.asList(numbers);
		assertEquals("1:1:1", Strings.implode(":", F.takeWhile(C.eq(1), list)));
	}

	@Test
	public void testStringDropWhile() {
		String s = "aaabbbcccaaa";
		assertEquals("bbbcccaaa", F.dropWhile(C.eq('a'), s));
	}

	@Test
	public void testStringTakeWhile() {
		String s = "aaabbbcccaaa";
		assertEquals("aaa", F.takeWhile(C.eq('a'), s));
	}
}
