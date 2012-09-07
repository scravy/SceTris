package de.fu.scetris.scheduler.manager.tests;

import static org.junit.Assert.assertTrue;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.xml.sax.SAXException;

import de.fu.junction.xml.XmlHelper;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.util.Config;

public abstract class SchedulerTest {

	protected static RelationManager relationManager;

	public static final String XML_CONFIG = "<config><database>\n" + "<user>scetris</user>"
			+ "<password>scetris</password>" + "<host>localhost</host>"
			+ "<name>scetris</name>" + "</database></config>\n";

	@BeforeClass
	public static void setUpBeforeClass() throws ParserConfigurationException, IOException, SAXException {

		XmlHelper xmlHelper = new XmlHelper();
		Config config = new Config(xmlHelper, xmlHelper.newDocument(XML_CONFIG.getBytes(Charset
				.forName("UTF-8"))), "database");

		relationManager = new RelationManager(new ConnectionManagerImpl(config));
	}

	@Before
	public void setUp() throws Exception {
		relationManager.connectionManager().connect();
		assertTrue(relationManager.install());
	}

	@AfterClass
	public static void teardownAfterClass() {
		relationManager = null;
		System.gc();
	}

	@After
	public void teardown() throws Exception {
		// FIXME: Leaves the DB in a bad state, broken.
		//relationManager.connectionManager().disconnect();
	}

}
