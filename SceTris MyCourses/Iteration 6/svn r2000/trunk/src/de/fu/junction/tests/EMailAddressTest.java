/* EMailAddressTest.java / 11:47:18 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.experimental.theories.DataPoints;
import org.junit.experimental.theories.Theories;
import org.junit.experimental.theories.Theory;
import org.junit.runner.RunWith;

import static de.fu.junction.Generics.array;
import static de.fu.junction.Tuple.t;
import de.fu.junction.Tuple;
import de.fu.junction.data.EMailAddress;
import de.fu.junction.data.InvalidSyntaxException;
import de.fu.junction.data.MalformedEMailAddressException;
import de.fu.junction.data.MalformedHostnameException;

/**
 * Tests {@see de.fu.junction.data.EMailAddress}
 */
@RunWith(Theories.class)
public class EMailAddressTest {

	@SuppressWarnings("unchecked")
	@DataPoints
	public static Tuple<String,Boolean>[] addressProvider() {
		return array(
			t("@example.org", false),
			t("@", false),
			t("\"randy\"@example.org", true),
			t("random.j.hacker@123.de", true),
			t("abc@def.tv", true),
			t("", false),
			t("kalua", false),
			t("me\\@you@she.com", false),
			t("\"me\\@you\"@she.com", true),
			t("max+urgent@gmail.net", true),
			t("ordinary.address@ordinary.host.com", true));
	}

	@Theory
	public void testAddresses(final Tuple<String,Boolean> $address) {
		if ($address.snd) {
			try {
				EMailAddress $eMail = new EMailAddress($address.fst);
				assertEquals($address.fst, $eMail.toString());
			} catch (InvalidSyntaxException $exc) {
				fail("Incorrectly rejected valid address");
			}
		} else {
			try {
				new EMailAddress($address.fst);
				fail("Incorrectly validated invalid address");
			} catch (InvalidSyntaxException $exc) {
			}
		}
	}

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
