/* Exporter.java / 7:07:43 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.Config;
import de.fu.weave.impl.ymir.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.Relation;
import de.fu.weave.xml.XmlHelper;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class Exporter {

	/**
	 * 
	 * @param args
	 * @since Iteration3
	 */
	@SuppressWarnings("unchecked")
	public static void main(final String[] args) throws DatabaseException, SQLException {
		Transformer transformer = null;
		PrintStream out = System.out;
		Properties properties = new Properties();
		properties.setProperty("name", "scetris");
		properties.setProperty("user", "scetris");
		properties.setProperty("host", "localhost");
		properties.setProperty("port", "5432");
		boolean authenticate = false;
		boolean withSchema = false;
		String clazz = null;
		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("--help") || args[i].equals("-h")) {
					System.out.println("java de.fu.scetris.Exporter [option]* [relation]");
					System.exit(0);
				} else if (args[i].equals("--with-xml-schema") || args[i].equals("-s")) {
					withSchema = true;
				} else if (args[i].equals("--database") || args[i].equals("-d")) {
					properties.setProperty("name", args[++i]);
				} else if (args[i].equals("--hostname") || args[i].equals("-h")) {
					properties.setProperty("host", args[++i]);
				} else if (args[i].equals("--username") || args[i].equals("-u")) {
					properties.setProperty("user", args[++i]);
				} else if (args[i].equals("--authenticate") || args[i].equals("-a")) {
					authenticate = true;
				} else if (args[i].equals("--port") || args[i].equals("-p")) {
					properties.setProperty("port", args[++i]);
				} else if (args[i].equals("--xsl-transform") || args[i].equals("-t")) {
					try {
						transformer = new XmlHelper().newTransformer(args[++i]);
					} catch (TransformerConfigurationException e) {
						System.err.println("Ooutch, TransformerConfigurationExcepion!");
						e.printStackTrace();
						System.exit(1);
					} catch (ParserConfigurationException e) {
						System.err.println("Ooutch, ParserConfigurationExcepion!");
						e.printStackTrace();
						System.exit(1);
					}
				} else if (args[i].equals("--config") || args[i].equals("-c")) {
					InputStream in = null;
					try {
						in = new FileInputStream(args[++i]);
					} catch (FileNotFoundException e) {
						System.err.println("Properties file " + args[i] + " does not exist");
						System.exit(1);
					}
					try {
						properties.loadFromXML(in);
					} catch (InvalidPropertiesFormatException e) {
						System.err.println();
						System.exit(1);
					} catch (IOException e) {
						System.err.println("Outch. An I/O-Exception occured.\n" + e.getMessage());
						System.exit(1);
					}
				} else {
					clazz = args[i];
				}
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Illagel command line arguments (see --help).");
			System.exit(1);
		}
		if (authenticate) {
			String pw =
					System.console()
							.readPassword("Enter password for "
													  + properties.getProperty("user")
													  + '@' + properties.getProperty("host") + ": ")
							.toString();
			properties.setProperty("password", pw);
		}

		ConnectionManager connMgr = new ConnectionManagerImpl(new Config(properties));
		RelationManager manager = new RelationManager(connMgr);
		connMgr.connect();

		SQLXML xml;
		if (clazz == null) {
			xml = manager.export(withSchema);
		} else {
			Class<? extends Relation> theClazz = null;
			String clazzName = "de.fu.scetris.data." + clazz;
			try {
				theClazz = (Class<? extends Relation>) Class.forName(clazzName);
			} catch (ClassNotFoundException e) {
				System.err.println(clazzName + " does not exist");
				System.exit(1);
			}
			xml = manager.export(theClazz, withSchema);
		}
		if (transformer == null) {
			int c;
			InputStream xmlStream = xml.getBinaryStream();
			try {
				try {
					while ((c = xmlStream.read()) != -1) {
						out.write(c);
					}
				} finally {
					xmlStream.close();
				}
			} catch (IOException e) {
				System.err.println("Outch. An I/O-Exception occured.\n" + e.getMessage());
				System.exit(1);
			}
		} else {
			StreamSource source = xml.getSource(StreamSource.class);
			StreamResult result = new StreamResult(out);
			try {
				transformer.transform(source, result);
			} catch (TransformerException e) {
				System.err.println("Exception during XSL Transformation:\n" + e.getMessage());
				System.exit(1);
			}
		}
	}
}
