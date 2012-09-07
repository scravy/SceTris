/* Utils.java / 2:43:16 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static de.fu.weave.util.Array.*;
import static de.fu.weave.util.Tupel.*;
import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.weave.util.MD5;
import de.fu.weave.util.Tupel;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class UtilsTest {
	@SuppressWarnings("unchecked")
	Tupel<String,String>[] md5data = array(
											t("", "d41d8cd98f00b204e9800998ecf8427e"),
											t("md5", "1bc29b36f623ba82aaf6724fd3b16718"),
											t("\n", "68b329da9893e34099c7d8ad5cb9c940")
			);

	@SuppressWarnings("unchecked")
	Tupel<String,String>[] stringDara = array(
											   t("small", "Small"),
											   t("CAMEL", "CAMEL"),
											   t("cASE", "CASE")
			);

	@Test
	public void testCapitalize() {
		for (Tupel<String,String> data : md5data) {
			assertEquals(data.snd, MD5.md5(data.fst));
		}
	}

	@Test
	public void testMD5() {
		for (Tupel<String,String> data : md5data) {
			assertEquals(data.snd, MD5.md5(data.fst));
		}
	}
}
