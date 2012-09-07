/* Disconnect.java / 6:54:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import org.junit.Test;



import static org.junit.Assert.*;


/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class ConfigTest extends DatabaseTestSkeleton {

	@Test
	public void testDifferentTypes() throws Exception {
		manager.setConfig("junk", "yard");
		assertEquals(7, manager.getConfig("junk", 7));
		manager.setConfig("junk", "17");
		assertEquals(17.0, manager.getConfig("junk", 7.0), 0);
		assertEquals(17, manager.getConfig("junk", 7));
		manager.setConfig("junk", 771938280235434L);
		assertEquals(771938280235434L, manager.getConfig("junk", 0L));
		manager.setConfig("junk", Math.PI);
		assertEquals(Math.PI, manager.getConfig("junk", Math.E), 0);
		manager.setConfig("junk", true);
		assertEquals(true, manager.getConfig("junk", false));
		assertEquals(true, manager.getConfig("junk", true));
		manager.setConfig("junk", false);
		assertEquals(false, manager.getConfig("junk", false));
		assertEquals(false, manager.getConfig("junk", true));
	}

	/**
	 * 
	 * 
	 * @since Iteration4
	 */
	@Test
	public void testGetDefault() throws Exception {
		assertEquals("bar", manager.getConfig("foo", "bar"));
	}

	@Test
	public void testInteger() throws Exception {
		assertEquals(23, manager.getConfig("foo", 23));
		manager.setConfig("foo", "1337");
		assertEquals(1337, manager.getConfig("foo", 23));
	}

	@Test
	public void testSetAndRetrieveValue() throws Exception {
		manager.setConfig("foo", "something");
		assertEquals("something", manager.getConfig("foo", ""));
	}

	@Test
	public void testUpdateAndRetrieveValue() throws Exception {
		assertEquals("", manager.getConfig("foo1", ""));
		assertEquals("", manager.getConfig("foo2", ""));
		manager.setConfig("foo1", "some1");
		manager.setConfig("foo2", "no1");
		assertEquals("some1", manager.getConfig("foo1", ""));
		assertEquals("no1", manager.getConfig("foo2", ""));
		manager.setConfig("foo2", "nonono");
		assertEquals("nonono", manager.getConfig("foo2", ""));
		assertEquals("some1", manager.getConfig("foo1", ""));
	}
}
