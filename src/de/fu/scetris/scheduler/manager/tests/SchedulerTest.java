package de.fu.scetris.scheduler.manager.tests;

import static org.junit.Assert.assertTrue;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Properties;

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

	@BeforeClass
	public static void setUpBeforeClass() throws ParserConfigurationException, IOException, SAXException {

		FileInputStream propertieFile = new FileInputStream("build.properties");
		Properties properties = new Properties();
		properties.load(propertieFile);
		propertieFile.close();

		String user = properties.getProperty("scetris.webapp.db.username");
		String password = properties.getProperty("scetris.webapp.db.password");
		String host = properties.getProperty("scetris.webapp.db.hostname");
		String name = properties.getProperty("scetris.webapp.db.database");

		String xmlConfig = "<configuration><database>\n" + "<user>" + user + "</user>"
				+ "<password>" + password + "</password>" + "<host>" + host
				+ "</host>" + "<name>" + name + "</name>"
				+ "</database></configuration>\n";
		XmlHelper xmlHelper = new XmlHelper();
		Config conf = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		relationManager = new RelationManager(new ConnectionManagerImpl(conf));
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
