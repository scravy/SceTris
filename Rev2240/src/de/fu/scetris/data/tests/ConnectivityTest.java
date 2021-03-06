/* Connectivity.java / 1:24:45 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.junit.BeforeClass;
import org.junit.Test;
import org.xml.sax.SAXException;

import de.fu.junction.xml.XmlHelper;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManagerException;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.util.Config;

/**
 * 
 * @author Julian Fleischer
 */
public class ConnectivityTest {

	protected static RelationManager manager;

	@BeforeClass
	public static void _setup() throws IOException, ParserConfigurationException, SAXException,
			DatabaseException {
		XmlHelper xmlHelper = new XmlHelper();
		Config conf =
				new Config(xmlHelper, xmlHelper.newDocument(DatabaseTestSkeleton.xmlConfig
						.getBytes(Charset.forName("UTF-8"))), "database");

		manager = new RelationManager(new ConnectionManagerImpl(conf));
	}

	@Test
	public void connect() throws ConnectionManagerException {
		manager.connectionManager().connect();
	}

	@Test
	public void ping() throws ConnectionManagerException {
		manager.connectionManager().connect();
		manager.connectionManager().validate();
	}

	@Test
	public void reconnect() throws ConnectionManagerException {
		manager.connectionManager().connect();
		manager.connectionManager().disconnect();
		manager.connectionManager().validate();
	}
}
