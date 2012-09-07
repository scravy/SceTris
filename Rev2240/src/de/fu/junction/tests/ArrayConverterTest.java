/* ArrayConverterTest.java / 10:11:15 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.junction.converter.ArrayConverter;

/**
 *
 */
public class ArrayConverterTest {

	@Test
	public void testArrayConverter() {
		String[] $s = new ArrayConverter().convert("   Hallo\n  Welt\t\nDa\n\nDraußen");
		assertEquals($s[0], "Hallo");
		assertEquals($s[1], "Welt");
		assertEquals($s[2], "Da");
		assertEquals($s[3], "Draußen");
	}
}
