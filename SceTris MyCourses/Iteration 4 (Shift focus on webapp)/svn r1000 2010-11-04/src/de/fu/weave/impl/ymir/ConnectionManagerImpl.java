/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.ymir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import de.fu.weave.Config;
import de.fu.weave.orm.ConnectionManager;
import de.fu.weave.orm.ConnectionManagerException;

/**
 * Handles database connectivity.
 * <p>
 * The sole purpose of this Manager is to maintain a Connection to the database. When a Servlet is
 * initiated, it will call connect() to establish a permanent link to the database. However, it will
 * not throw an error if the connection could not be established due to timeout or something like
 * that. If a connection is needed Modules should use validate() which will test if the connection
 * is alive and attempt to reconnect in case it is not.
 * <p>
 * In case validate() returns false the last Exception will be stored instead of thrown. It might be
 * retrieved using getLastException()
 * 
 * @author Julian Fleischer
 */
public class ConnectionManagerImpl implements ConnectionManager {
	/**
	 * The connection-object to the database.
	 * <p>
	 * This will be null if no connecting is established at the moment, be it due to connect()
	 * having failed or validate() having spot an already established link being broken.
	 * <p>
	 * It is package private (default) since it oughts to be read by EntityManager (for instance)
	 * which is in the same package.
	 */
	private Connection connection = null;

	/**
	 * The parents configuration wrapped by Config.
	 */
	private final Config config;

	/**
	 * Creates the ConnectionManager with a given Configuration
	 * 
	 * @param cfg
	 *            The Configuration to be used by this ConnectionManager
	 */
	public ConnectionManagerImpl(final Config cfg) {
		config = cfg;
	}

	@Override
	public boolean connect() throws ConnectionManagerException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + config.get("host") + ':'
						 + config.get("port", 5432) + '/' + config.get("name");
			String user = config.get("user");
			connection = DriverManager.getConnection(url, user, config.get("password"));
			return true;
		} catch (ClassNotFoundException e) {
			throw new ConnectionManagerException(e);
		} catch (SQLException e) {
			throw new ConnectionManagerException(e);
		}
	}

	@Override
	public void disconnect() {
		try {
			connection.close();
		} catch (SQLException e) {
			// the all famous noop
		}
		// connection = null;
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public boolean isConnected() {
		if (connection == null) {
			return false;
		}
		try {
			ResultSet result = connection.createStatement().executeQuery("SELECT ping();");
			result.next();
			return result.getBoolean(1);
		} catch (SQLException e) {
			return false;
		}
	}

	@Override
	public boolean validate() throws ConnectionManagerException {
		if (!isConnected()) {
			return connect();
		}
		return true;
	}
}
