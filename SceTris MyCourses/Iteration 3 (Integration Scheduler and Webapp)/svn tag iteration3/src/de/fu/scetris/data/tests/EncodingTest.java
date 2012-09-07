/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.weave.util.MD5.*;
import static org.junit.Assert.*;

import org.junit.Test;
import de.fu.scetris.data.Person;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.tests.DatabaseTestSkeleton;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class EncodingTest extends DatabaseTestSkeleton {

	/**
	 * Runs the tests on the command line
	 * 
	 * @param args
	 */
	public static void main(final String args[]) {
		org.junit.runner.JUnitCore
				.main("de.fu.scetris.data.tests.gnooool.EncodingTest");
	}

	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Test
	public void checkEncoding() throws DatabaseException {
		Person testPerson = manager.createPerson("Jördis", "Jormungand", "jjomungand", md5("blabla"));
		Person dbPerson = manager.getPerson(testPerson.id());
		assertEquals("Jördis", dbPerson.getFirstName());
		System.out.println("[" + dbPerson.getFirstName() + " vs. Jördis]");
	}
}
