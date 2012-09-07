/* Utils.java / 2:43:16 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static de.fu.junction.Generics.*;
import static de.fu.junction.Tuple.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.junction.Generics;
import de.fu.junction.MD5;
import de.fu.junction.Strings;
import de.fu.junction.Tuple;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class UtilsTest {
	@SuppressWarnings("unchecked")
	Tuple<String,String>[] md5data = array(
											t("", "d41d8cd98f00b204e9800998ecf8427e"),
											t("md5", "1bc29b36f623ba82aaf6724fd3b16718"),
											t("\n", "68b329da9893e34099c7d8ad5cb9c940")
			);

	@SuppressWarnings("unchecked")
	Tuple<String,String>[] stringDara = array(
											   t("small", "Small"),
											   t("CAMEL", "CAMEL"),
											   t("cASE", "CASE")
			);

	@SuppressWarnings("unchecked")
	public <A> A[] makeTestArray(final Class<A> clazz) throws Exception {
		return Generics.array(clazz.newInstance());
	}

	@Test
	public void testCapitalize() {
		for (Tuple<String,String> data : md5data) {
			assertEquals(data.snd, MD5.md5(data.fst));
		}
	}

	@Test
	public void testCreateByClass() {
		String string = create(String.class, "Hello World");
		assertNotNull(string);
		assertEquals("Hello World", string);
	}

	@Test
	public void testCreateStringArray() {
		Object string = create("java.lang.String", "Hello World");
		assertNotNull(string);
		assertEquals("Hello World", string);
	}

	@Test
	public void testGenericArrayType() throws Exception {
		String[] x = makeTestArray(String.class);
		assertEquals(1, x.length);
		assertEquals("java.lang.String", x[0].getClass().getCanonicalName());
		assertEquals("java.lang.String[]", x.getClass().getCanonicalName());
	}

	@Test
	public void testImplode() throws Exception {
		String coolCat = Strings.implode(":", new String[] { "a", "bb", "ccc", "dddd" });
		assertEquals("a:bb:ccc:dddd", coolCat);
		coolCat = Strings.implode(":", new Object[0]);
		assertEquals("", coolCat);
	}

	@Test
	public void testMD5() {
		for (Tuple<String,String> data : md5data) {
			assertEquals(data.snd, MD5.md5(data.fst));
		}
	}

	@Test
	public void testStripWhitespace() {
		assertEquals(Strings.stripWhitespace("    "), "");
		assertEquals(Strings.stripWhitespace("\n"), "");
		assertEquals(Strings.stripWhitespace("Hallo Welt"), "HalloWelt");
		assertEquals(Strings.stripWhitespace("Hello World\n"), "HelloWorld");
	}
}
