/* GenericsTest.java / 7:28:36 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static de.fu.junction.dynamic.G.*;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class GenericsTest {

	public static class LocalClass<T> {
		@SuppressWarnings("unchecked")
		public T[] makeArray(final T str1, final T str2, final T str3) {
			return array(str1, str2, str3);
		}
	}

	@Test
	public void testArray() {
		Integer[] intArray = array(7, 3, 4);
		assertTrue(intArray.getClass().getComponentType().equals(Integer.class));
		assertTrue(intArray instanceof Integer[]);
		assertArrayEquals(new Integer[] { 7, 3, 4 }, intArray);
	}

	@Test
	public void testCreate() {
		assertEquals("Hello World", create("java.lang.String", "Hello World"));
		assertEquals("", create(String.class));
		assertEquals("Bye Bye", create(String.class, "Bye Bye"));
		assertEquals(new Integer("1"), create("java.lang.Integer", "1"));
	}

	@Test
	public void testGenericArrayCreation() {
		LocalClass<Integer> intClass = new LocalClass<Integer>();
		Integer[] intArray = intClass.makeArray(1, 2, 3);
		assertTrue(intArray.getClass().getComponentType().equals(Integer.class));
		assertTrue(intArray instanceof Integer[]);
		assertArrayEquals(new Integer[] { 1, 2, 3 }, intArray);

		LocalClass<Character> charClass = new LocalClass<Character>();
		Character[] charArray = charClass.makeArray('a', 'b', 'c');
		assertTrue(charArray.getClass().getComponentType().equals(Character.class));
		assertTrue(charArray instanceof Character[]);
		assertArrayEquals(new Character[] { 'a', 'b', 'c' }, charArray);
	}
}
