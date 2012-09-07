/* DatabaseTest.java / 1:16:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.tests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.util.Config;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class DatabaseTestSkeleton {

	/**
	 * 
	 * @since Iteration3
	 */
	protected static RelationManager manager;

	/**
	 * 
	 * @since Iteration4
	 */
	protected static XmlHelper xmlHelper;

	final public static String xmlConfig = "<config><database>\n"
			+ "<user>scetris-testing</user>"
			+ "<password>kleinerAffeMitHut</password>"
			+ "<host>localhost</host>"
			+ "<name>scetris-testing</name>"
			+ "</database></config>\n";

	public static void _install() {

	}

	@BeforeClass
	public static void _setup() throws IOException, ParserConfigurationException, SAXException,
			DatabaseException {
		xmlHelper = new XmlHelper();
		Config conf =
				new Config(xmlHelper, xmlHelper.newDocument(xmlConfig.getBytes(Charset
						.forName("UTF-8"))), "database");

		manager = new RelationManager(new ConnectionManagerImpl(conf));
	}

	@AfterClass
	public static void _teardown() {
		manager = null;
		System.gc();
	}

	protected boolean _doInstall() {
		return true;
	}

	protected void _init() throws Exception {
		// does nothing, *may* be overriden to extend @Before-method
	}

	@Before
	public void prepare() throws Exception {
		manager.connectionManager().connect();
		if (_doInstall()) {
			assertTrue(manager.install());
		}

		_init();
	}

	@After
	public void teardown() throws Exception {
		manager.connectionManager().disconnect();
	}
}
