/* MapTest.java / 7:29:40 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import de.fu.junction.Callback;
import de.fu.junction.Strings;
import de.fu.junction.functional.F;
import de.fu.junction.functional.Function;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class MapTest {

	public static String append(final String string) {
		return string + " appendix";
	}

	public static String appendAnything(final String string, final String anything) {
		return string + anything;
	}

	@Test
	public void testMap() throws SecurityException, NoSuchMethodException {
		String[] strings = new String[] { "a", "b", "cc", "ddd" };
		Callback c = new Callback(getClass(), "append");
		Function<String,String> f = c.getFunction(String.class, String.class);
		String[] newStrings = F.map(f, strings);
		assertEquals("java.lang.String", newStrings[0].getClass().getCanonicalName());
		assertEquals("java.lang.String[]", newStrings.getClass().getCanonicalName());
		assertEquals(newStrings.length, strings.length);
		for (int i = 0; i < strings.length; i++) {
			assertEquals(newStrings[i], strings[i] + " appendix");
		}
	}

	@Test
	public void testMapWithTarget() {
		List<String> list = F.map(new Callback(Strings.class, "append"),
								  new String[] { "name", "age", "size" },
								  new ArrayList<String>(),
								  " ASC");
		assertTrue(list instanceof ArrayList);
		String result = Strings.implode(", ", list);
		assertEquals("name ASC, age ASC, size ASC", result);
	}
}
