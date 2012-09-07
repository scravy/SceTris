/* EMailAddressTest.java / 11:47:18 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import org.junit.Test;

import de.fu.junction.data.EMailAddress;
import de.fu.junction.data.MalformedEMailAddressException;
import de.fu.junction.data.MalformedHostnameException;

/**
 * Tests {@see de.fu.junction.data.EMailAddress}
 */
public class EMailAddressTest {

	@Test(expected = MalformedEMailAddressException.class)
	public void testInvalidAddress() throws Exception {
		EMailAddress $a = new EMailAddress("someone");
		fail($a.toString());
	}

	@Test(expected = MalformedHostnameException.class)
	public void testInvalidHostname() throws Exception {
		EMailAddress $a = new EMailAddress("somelone@somewhere");
		fail($a.toString());
	}

	@Test
	public void testToString() throws Exception {
		EMailAddress $a = new EMailAddress("someone@example.org");
		assertEquals("someone@example.org", $a.toString());
	}

}
