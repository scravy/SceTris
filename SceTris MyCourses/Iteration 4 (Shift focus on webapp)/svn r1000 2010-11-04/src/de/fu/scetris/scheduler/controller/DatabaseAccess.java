package de.fu.scetris.scheduler.controller;

import java.io.IOException;
import java.nio.charset.Charset;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.Config;
import de.fu.weave.impl.ymir.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManagerException;
import de.fu.weave.xml.XmlHelper;

/**
 * Holds static informations about the database connection.
 * 
 * @author Konrad Reiche
 * 
 */
public class DatabaseAccess {

	// XXX: bogus, connection information should be stored in a text-file
	static String USER = "scetris";
	static String USER_PASSWORD = "scetris";
	static String HOST = "localhost";
	static String DB_PASSWORD = "scetris";

	/**
	 * Based on the given static database configuration strings a connection is
	 * established to the database in order to return access to the
	 * object-relational mapping.
	 * 
	 * @return the access objects for the relational mapping.
	 * @throws ParserConfigurationException
	 * @throws IOException
	 * @throws SAXException
	 * @throws ConnectionManagerException
	 */
	static RelationManager connect() throws ParserConfigurationException,
			IOException, SAXException, ConnectionManagerException {

		String xmlConfig = "<config><database>\n" + "<user>" + USER + "</user>"
				+ "<password>" + USER_PASSWORD + "</password>" + "<host>"
				+ HOST + "</host>" + "<name>" + DB_PASSWORD + "</name>"
				+ "</database></config>\n";

		XmlHelper xmlHelper = new XmlHelper();

		Config config = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		RelationManager relationManager = new RelationManager(
				new ConnectionManagerImpl(config));

		relationManager.connectionManager.connect();

		return relationManager;
	}
}
