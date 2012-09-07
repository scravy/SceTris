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
import java.sql.SQLException;
import java.util.InvalidPropertiesFormatException;
import java.util.Properties;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.Config;
import de.fu.weave.impl.ginnungagap.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class Installer {

	/**
	 * 
	 * @param args
	 * @since Iteration3
	 */
	public static void main(final String[] args) throws DatabaseException, SQLException {
		Properties properties = new Properties();
		properties.setProperty("name", "scetris");
		properties.setProperty("user", "scetris");
		properties.setProperty("host", "localhost");
		properties.setProperty("port", "5432");
		boolean authenticate = false;
		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("--help") || args[i].equals("-h")) {
					System.out.println("java de.fu.scetris.Installer [option]*");
					System.exit(0);
				} else if (args[i].equals("--database") || args[i].equals("-d")) {
					properties.setProperty("name", args[++i]);
				} else if (args[i].equals("--hostname") || args[i].equals("-h")) {
					properties.setProperty("host", args[++i]);
				} else if (args[i].equals("--username") || args[i].equals("-u")) {
					properties.setProperty("user", args[++i]);
				} else if (args[i].equals("--authenticate") || args[i].equals("-a")) {
					authenticate = true;
				} else if (args[i].equals("--password") || args[i].equals("-P")) {
					properties.setProperty("password", args[++i]);
				} else if (args[i].equals("--port") || args[i].equals("-p")) {
					properties.setProperty("port", args[++i]);
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
		System.out.println("Setting up database " + properties.getProperty("name")
						   + " as " + properties.getProperty("user")
						   + '@' + properties.getProperty("host") + ':'
						   + properties.getProperty("port"));
		manager.install();
		System.out.println("Done.");
	}
}
