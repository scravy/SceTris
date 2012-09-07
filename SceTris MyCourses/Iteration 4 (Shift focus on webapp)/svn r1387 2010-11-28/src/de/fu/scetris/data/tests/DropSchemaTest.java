/* Disconnect.java / 6:54:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;

import de.fu.bakery.orm.java.tests.DatabaseTestSkeleton;



/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class DropSchemaTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * 
	 * @since Iteration4
	 */
	@Test
	public void testDisconnect() throws Exception {
		manager.dropSchema();
	}

}
