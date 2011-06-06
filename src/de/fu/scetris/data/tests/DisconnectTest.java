/* Disconnect.java / 6:54:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;

import de.fu.weave.orm.ConnectionManagerException;


import static org.junit.Assert.*;


/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class DisconnectTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	@Test
	public void testDisconnect() throws ConnectionManagerException {
		manager.connectionManager().disconnect();
		assertTrue(manager.connectionManager().validate());
	}

}
