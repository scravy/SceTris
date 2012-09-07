/* InstallTest.java / 4:53:01 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;



/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class InstallTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	@Test
	public void testInstall() throws DatabaseException {
		manager.connectionManager.connect();
		assertTrue(manager.install());
	}

}
