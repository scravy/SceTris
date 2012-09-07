package de.fu.scetris;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.junction.annotation.meta.Author;
import de.fu.junction.xml.XmlHelper;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.ConnectionManagerImpl;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.util.Config;

/**
 * 
 */
@Author("Konrad Reiche")
class TestDataSetup {

	/**
	 * 
	 * @param path
	 * @return
	 * @throws IOException
	 */
	public static Map<String,List<String>> convertDirectoryToLists(final String path) throws IOException {

		Map<String,List<String>> result = new TreeMap<String,List<String>>();
		File directory = new File(path);

		String[] files = directory.list(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return !name.startsWith(".");
			}
		});
		for (String file : files) {
			String filePath = path + "/" + file;
			result.put(file.substring(0, file.length() - 4), convertTextFileToList(filePath));
		}
		return result;
	}

	/**
	 * Converts an arbitrary text file to a list of Strings each representing a line of the text
	 * file.
	 */
	public static List<String> convertTextFileToList(final String path)
			throws IOException {

		List<String> result = new ArrayList<String>();

		BufferedReader reader = new BufferedReader(new InputStreamReader(
				new DataInputStream(new FileInputStream(path))));

		String textLine;
		while ((textLine = reader.readLine()) != null)
			if (!textLine.equals("")) result.add(textLine);

		reader.close();
		return result;
	}

	/**
	 * Connects to the database based on build.properties. Based on doInstall the current database
	 * schema is dropped and recreated.
	 * 
	 * @return the {@link RelationManager} in order to access the data of the database.
	 */
	public static RelationManager createRelationManager(
														final boolean installDatabaseSchema)
			throws IOException, SAXException,
			DatabaseException, ParserConfigurationException {

		RelationManager relationManager;
		FileInputStream propertieFile = new FileInputStream("build.properties");
		Properties properties = new Properties();
		properties.load(propertieFile);
		propertieFile.close();

		String user = properties.getProperty("scetris.webapp.db.username");
		String password = properties.getProperty("scetris.webapp.db.password");
		String host = properties.getProperty("scetris.webapp.db.hostname");
		String name = properties.getProperty("scetris.webapp.db.database");

		String xmlConfig = "<config><database>\n" + "<user>" + user + "</user>"
				+ "<password>" + password + "</password>" + "<host>" + host
				+ "</host>" + "<name>" + name + "</name>"
				+ "</database></config>\n";
		XmlHelper xmlHelper = new XmlHelper();
		Config conf = new Config(xmlHelper, xmlHelper.newDocument(xmlConfig
				.getBytes(Charset.forName("UTF-8"))), "database");

		relationManager = new RelationManager(new ConnectionManagerImpl(conf));
		relationManager.connectionManager().connect();

		if (installDatabaseSchema) relationManager.install();

		return relationManager;
	}

}
