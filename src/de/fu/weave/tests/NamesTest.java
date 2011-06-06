/* NamesTest.java / 3:53:42 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.*;

import java.util.Random;
import java.util.Set;
import java.util.TreeSet;

import org.junit.Test;

import de.fu.weave.util.MockupNames;
import de.fu.weave.util.MockupNames.Name;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class NamesTest {

	@Test
	public void genNames() {
		Set<String> givenNames = new TreeSet<String>();

		MockupNames names = new MockupNames(new Random());
		String stringName;
		for (int i = 0; i < 50000; i++) {
			Name n = names.nextName();

			stringName = n.firstName + ":" + n.lastName;
			assertFalse(givenNames.contains(stringName));
			givenNames.add(stringName);
		}
	}

}
