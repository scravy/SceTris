/* DatabaseTest.java / 1:16:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.Config;
import de.fu.weave.impl.ginnungagap.ConnectionManagerImpl;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class DatabaseTestSkeleton {

	protected static RelationManager manager;

	final public static String xmlConfig = "<config><database>\n"
			+ "<user>scetris-testing</user>"
			+ "<password>kleinerAffeMitHut</password>"
			+ "<host>localhost</host>"
			+ "<name>scetris-testing</name>"
			+ "</database></config>\n";

	@BeforeClass
	public static void _setup() throws IOException, ParserConfigurationException, SAXException,
			DatabaseException {
		XmlHelper xmlHelper = new XmlHelper();
		Config conf =
				new Config(xmlHelper, xmlHelper.newDocument(xmlConfig.getBytes(Charset
						.forName("UTF-8"))), "database");

		assertEquals("localhost", conf.get("host"));

		manager = new RelationManager(new ConnectionManagerImpl(conf));
	}

	protected void _init() throws Exception {
		// does nothing, *may* be overriden to extend @Before-method
	}

	@Before
	public void prepare() throws Exception {
		manager.connectionManager.connect();
		assertTrue(manager.install());

		_init();
	}
}
