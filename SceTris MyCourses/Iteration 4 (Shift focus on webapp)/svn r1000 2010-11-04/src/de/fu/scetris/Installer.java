/* Exporter.java / 7:07:43 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris;

import java.io.Console;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.Config;
import de.fu.weave.impl.ymir.ConnectionManagerImpl;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.ConnectionManagerException;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class Installer {

	public static String escape(final String str) {
		return "\"" + str.replace("\"", "\\\"") + "\"";
	}

	public static String escapeString(final String str) {
		return "'" + str.replace("'", "\\'") + "'";
	}

	/**
	 * 
	 * @param args
	 * @since Iteration3
	 */
	public static void main(String[] args) {
		String username = null;
		String database = null;
		String hostname = "localhost";
		int port = 5432;
		String password = null;
		boolean authenticate = false;

		String superuser = null;
		String masterPassword = null;
		String adminDb = null;
		boolean Authenticate = false;
		boolean fullInstall = false;

		boolean verbose = false;
		boolean debug = false;
		// String logfile = null;

		if (args.length == 0) {
			args = new String[1];
			args[0] = "--help";
		}
		try {
			for (int i = 0; i < args.length; i++) {
				if (args[i].equals("--help") || args[i].equals("-h")) {
					System.out.println("java de.fu.scetris.Installer [option]+");
					System.out.println();
					System.out.println("  -d / --database  <string>");
					System.out.println("  -H / --hostname  <string>");
					System.out.println("  -u / --username  <string>");
					System.out.println("  -P / --port      <number>");
					System.out.println("  -a / --authenticate");
					System.out.println("  -p / --password  <string> (not recommended, better use -a)");
					System.out.println();
					System.out.println("  -D / --admin-db  <string>");
					System.out.println("  -S / --superuser <string>");
					System.out.println("  -A / --Authenticate");
					System.out.println("  -M / --master-pw <string> (not recommended, better use -A)");
					System.out.println();
					System.out.println("  -v / --verbose");/*
														    * System.out.println(
														    * "  -l / --logfile   <logfile>");
														    */
					System.out.println("  --debug");
					System.exit(0);
				} else if (args[i].equals("--database") || args[i].equals("-d")) {
					database = args[++i];
				} else if (args[i].equals("--hostname") || args[i].equals("-H")) {
					hostname = args[++i];
				} else if (args[i].equals("--username") || args[i].equals("-U")
						|| args[i].equals("-u")) {
					username = args[++i];
				} else if (args[i].equals("--authenticate") || args[i].equals("-a")) {
					authenticate = true;
				} else if (args[i].equals("--Authenticate") || args[i].equals("-A")) {
					Authenticate = true;
				} else if (args[i].equals("--password") || args[i].equals("-p")) {
					password = args[++i];
				} else if (args[i].equals("--master-pw") || args[i].equals("-M")) {
					masterPassword = args[++i];
				} else if (args[i].equals("--port") || args[i].equals("-P")) {
					port = Integer.parseInt(args[++i]);
				} else if (args[i].equals("--verbose") || args[i].equals("-v")) {
					verbose = true;
				} else if (args[i].equals("--superuser") || args[i].equals("-S")) {
					superuser = args[++i];
				} else if (args[i].equals("--admin-db") || args[i].equals("-D")) {
					adminDb = args[++i];
				} else if (args[i].equals("--debug")) {
					debug = true;
				}/*
				  * else if (args[i].equals("--logfile") || args[i].equals("-l")) { logfile =
				  * args[++i]; }
				  */
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			System.err.println("Illagel command line arguments (see --help).");
			System.exit(1);
		} catch (NumberFormatException e) {
			System.err.println("A number should be a string consisting of digits.");
			System.exit(1);
		}

		if (debug) {
			verbose = true;
		}
		fullInstall = (adminDb != null) && (database != null) && (superuser != null);

		Console c = System.console();
		if (authenticate || Authenticate) {
			if (c == null) {
				System.err.println("Failed to get console for authentication. Try -P / -M. See --help.");
			} else {
				if (authenticate) {
					String msg = "Enter password for " + username + '@' + hostname + ':' + port + ": ";
					password = new String(c.readPassword(msg).toString());
				}
				if (Authenticate) {
					String msg = "Enter password for " + superuser + '@' + hostname + ':' + port + ": ";
					masterPassword = new String(c.readPassword(msg));
				}
			}
		}

		if (fullInstall) {
			if (verbose) {
				System.out.println("Doing a complete install.");
			}
			Properties adminProperties = new Properties();
			adminProperties.setProperty("name", adminDb);
			adminProperties.setProperty("user", superuser);
			adminProperties.setProperty("port", Integer.toString(port));
			adminProperties.setProperty("host", hostname);
			adminProperties.setProperty("password", masterPassword);
			ConnectionManager adminConnection = new ConnectionManagerImpl(new Config(adminProperties));
			try {
				adminConnection.connect();
			} catch (ConnectionManagerException e) {
				System.err.println("ConnectionManagerException: " + e.getMessage());
				System.exit(1);
			}
			String query = "";
			try {
				Connection connection = adminConnection.getConnection();

				if (verbose) {
					System.out.println("Dropping database " + database + " if it exists");
				}
				query = "DROP DATABASE IF EXISTS " + escape(database) + ";";
				if (debug) {
					System.out.println("[debug] " + query);
				}
				connection.createStatement().execute(query);

				if (username != null) {
					if (verbose) {
						System.out.println("Dropping role " + username + " if it exists");
					}
					query = "DROP ROLE IF EXISTS " + escape(username) + ";";
					if (debug) {
						System.out.println("[debug] " + query);
					}
					connection.createStatement().execute(query);
					if (verbose) {
						System.out.println("Creating role " + username);
					}
					query =
							"CREATE ROLE " + escape(username) + " LOGIN PASSWORD "
									+ escapeString(password)
									+ " NOSUPERUSER INHERIT NOCREATEDB NOCREATEROLE";
					if (debug) {
						System.out.println("[debug] " + query);
					}
					connection.createStatement().execute(query);
				}

				if (username == null) {
					username = superuser;
					password = masterPassword;
				}

				if (verbose) {
					System.out.println("Creating " + database + '@' + hostname + " with owner "
							+ username);
				}
				query = "CREATE DATABASE " + escape(database) + " WITH OWNER = " + escape(username)
						+ " ENCODING = 'UNICODE' CONNECTION LIMIT = -1 TEMPLATE template0;";
				if (debug) {
					System.out.println("[debug] " + query);
				}
				connection.createStatement().execute(query);
			} catch (SQLException e) {
				System.err.println("SQLException: " + e.getMessage());
				if (debug) {
					System.err.println("[debug] Query was " + query);
				}
				System.exit(1);
			} finally {
				adminConnection.disconnect();
			}

		}

		Properties properties = new Properties();
		properties.setProperty("name", database);
		properties.setProperty("user", username);
		properties.setProperty("port", Integer.toString(port));
		properties.setProperty("host", hostname);
		properties.setProperty("password", password);
		ConnectionManager connMgr = new ConnectionManagerImpl(new Config(properties));
		RelationManager manager = new RelationManager(connMgr);
		try {
			connMgr.connect();
		} catch (ConnectionManagerException e) {
			System.err.println("ConnectionManagerException: " + e.getMessage());
			System.exit(1);
		}
		if (verbose) {
			System.out.println("Setting up database " + database + " as " + username + '@' + hostname);
		}
		try {
			manager.install();
		} catch (DatabaseException e) {
			System.err.println("DatabaseException: " + e.getMessage());
			System.exit(1);
		} finally {
			connMgr.disconnect();
		}
		System.exit(0);
	}
}
