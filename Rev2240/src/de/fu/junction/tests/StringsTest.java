/* StringsTest.java / 1:21:53 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.junit.Test;

import de.fu.junction.Strings;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringsTest {
	@Test
	public void testArray() {
		Character[] array = new Character[] { 'a', 'b' };
		assertEquals("a:b", Strings.implode(":", array));
	}

	@Test
	public void testArrayWithObjectDelimiter() {
		Character[] array = new Character[] { 'a', 'b' };
		assertEquals("a:b", Strings.implode(':', array));
	}

	@Test
	public void testEmptyArray() {
		Object[] array = new Object[0];
		assertEquals("", Strings.implode("supercalifragilistcexpialidocious", array));
	}

	@Test
	public void testEmptyMap() {
		Map<?,?> map = new HashMap<Object,Object>();
		assertEquals("", Strings.implode("\n", "supercalifragilistcexpialidocious", map));
	}

	@Test
	public void testIterable() {
		List<Character> list = new LinkedList<Character>();
		list.add('a');
		list.add('b');
		assertEquals("a:b", Strings.implode(":", list));
	}

	@Test
	public void testIterableWithObjectDelimiter() {
		List<Character> list = new LinkedList<Character>();
		list.add('a');
		list.add('b');
		assertEquals("a:b", Strings.implode(':', list));
	}

	@Test
	public void testMap() {
		Map<Integer,Double> map = new TreeMap<Integer,Double>();
		map.put(0, 0.0);
		map.put(1, Math.PI);
		map.put(2, Math.E);
		assertEquals("0,0.0;1,3.141592653589793;2,2.718281828459045", Strings.implode(";", ",", map));
	}

	@Test
	public void testMapWithObjectDelimiter() {
		Map<Integer,Double> map = new TreeMap<Integer,Double>();
		map.put(0, 0.0);
		map.put(1, Math.PI);
		map.put(2, Math.E);
		assertEquals("0,0.0;1,3.141592653589793;2,2.718281828459045", Strings.implode(';', ',', map));
	}
}
