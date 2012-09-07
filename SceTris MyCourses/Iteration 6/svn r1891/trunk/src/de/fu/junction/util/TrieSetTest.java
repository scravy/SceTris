/* TrieSetTest.java / 12:05:28 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.util;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Before;
import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

/**
 *
 */
@RunWith(Theories.class)
public class TrieSetTest {

	static int $prefixes = 0;

	@DataPoints
	public static String[] data() {
		int $num = 500;
		List<String> $strings = new ArrayList<String>();
		for (int $i = 0; $i < $num; $i++) {
			char[] $charArray = new char[$i % 20];
			for (int $j = 0; $j < $i % 20; $j++) {
				$charArray[$j] = (char) (64 + $i % 32 + (($i + $j) % 19));
			}
			$strings.add(new String($charArray));
		}
		return $strings.toArray(new String[$num]);
	}

	TrieSet<Character> $trieSet;
	static TrieSet<Character> $staticTrieSet = new TrieSet<Character>();

	static Set<String> $added = new TreeSet<String>();

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {
		$trieSet = new TrieSet<Character>();
	}

	@Theory
	public void stressTest(final String $val) {
		int $size = $staticTrieSet.size();
		assertEquals($added.contains($val), $staticTrieSet.contains($val));
		$added.add($val);
		if ($staticTrieSet.contains($val)) {
			$staticTrieSet.add($val);
			assertEquals($size, $staticTrieSet.size());
		} else {
			$staticTrieSet.add($val);
			assertEquals($size + 1, $staticTrieSet.size());
		}
	}

	/**
	 * Test method for {@link de.fu.junction.util.TrieSet#size()}.
	 */
	@Test
	public void testAddAndSize() {
		assertEquals(0, $trieSet.size());
		$trieSet.add(new Character[] { 'a', 'b' });
		assertEquals(1, $trieSet.size());
		$trieSet.add("hallo");
		assertEquals(2, $trieSet.size());
		$trieSet.add("hallo");
		assertEquals(2, $trieSet.size());
		$trieSet.add("hallo".toCharArray());
		assertEquals(2, $trieSet.size());
	}

	/**
	 * Test method for {@link de.fu.junction.util.TrieSet#contains(java.lang.Object)}.
	 */
	@Test
	public void testContainsObject() {
		assertFalse($trieSet.contains("hallo"));
		assertFalse($trieSet.contains("hall"));
		$trieSet.add("hallo");
		assertTrue($trieSet.contains("hallo"));
		assertFalse($trieSet.contains("hall"));
	}

	@Test
	public void testEmptyWord() {
		assertFalse($trieSet.contains(""));
		assertEquals(0, $trieSet.size());
		$trieSet.add("");
		assertTrue($trieSet.contains(""));
		assertEquals(1, $trieSet.size());
	}

	@Test
	public void testHasPrefix() {
		assertTrue($trieSet.hasPrefix(""));
		assertFalse($trieSet.hasPrefix("hell"));
		$trieSet.add("hello");
		assertTrue($trieSet.hasPrefix(""));
		assertTrue($trieSet.hasPrefix("h"));
		assertTrue($trieSet.hasPrefix("he"));
		assertTrue($trieSet.hasPrefix("hel"));
		assertTrue($trieSet.hasPrefix("hell"));
		assertTrue($trieSet.hasPrefix("hello"));
		assertFalse($trieSet.hasPrefix("hello "));
		assertFalse($trieSet.hasPrefix("hello w"));
		assertFalse($trieSet.hasPrefix("hello wo"));
	}
}
