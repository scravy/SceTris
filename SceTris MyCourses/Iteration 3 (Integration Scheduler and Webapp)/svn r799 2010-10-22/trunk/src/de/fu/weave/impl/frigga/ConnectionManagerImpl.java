/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import java.sql.Connection;
import java.sql.DriverManager;
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
	 * The lastException is being stored here if connect() could not succeed in attempting a
	 * connection.
	 */
	private SQLException lastException = null;

	/**
	 * Creates the ConnectionManager with a given Configuration
	 * 
	 * @param cfg
	 *            The Configuration to be used by this ConnectionManager
	 */
	public ConnectionManagerImpl(final Config cfg) {
		config = cfg;
	}

	/**
	 * Attempts to establish a connection.
	 * <p>
	 * If the Driver is not available an Exception will be thrown. Two possibilities may lead to an
	 * Exception being thrown: (1) The database driver could not be loaded (is it in the path?), or
	 * (2) The driver is incorrectly configured (wrong username/password/...). If some other kind of
	 * Exception occurs it will not be thrown but catched and stored. It can be retrieved using
	 * getLastException().
	 * 
	 * @throws ConnectionManagerException
	 *             If the database-driver is not available
	 */
	@Override
	public void connect() throws ConnectionManagerException {
		try {
			Class.forName("org.postgresql.Driver");
			String url = "jdbc:postgresql://" + config.get("host") + '/' + config.get("name");
			connection =
					DriverManager.getConnection(url, config.get("user"), config.get("password"));
			lastException = null;
		} catch (ClassNotFoundException e) {
			throw new ConnectionManagerException(e);
		} catch (SQLException e) {
			lastException = e;
		}
	}

	@Override
	public Connection getConnection() {
		return connection;
	}

	@Override
	public ConnectionManagerException getLastException() {
		if (lastException != null) {
			return new ConnectionManagerException(lastException);
		}
		return null;
	}

	@Override
	public boolean validate() throws ConnectionManagerException {
		boolean valid = true;
		// XXX: Is this smart?
		if (lastException != null) {
			throw new ConnectionManagerException(lastException);
		}
		try {
			valid = connection.isValid(0);
		} catch (SQLException e) {
			valid = false;
		}
		if (!valid) {
			try {
				if (!connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
			}
			connect();
			valid = lastException == null;
		}
		if (!valid) {
			connection = null;
		}
		return valid;
	}

}
