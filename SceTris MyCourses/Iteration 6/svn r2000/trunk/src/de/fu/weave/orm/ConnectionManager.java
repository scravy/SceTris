/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.sql.Connection;

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
public interface ConnectionManager extends Cloneable {

	public ConnectionManager clone();

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
	 * @return Whether the connection has been successfully established or not
	 */
	public boolean connect() throws ConnectionManagerException;

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public void disconnect();

	/**
	 * 
	 * @return The Connection
	 * @since Iteration2
	 */
	public Connection getConnection();

	/**
	 * Checks if the connection is alive or not
	 * 
	 * @return false if the connection is not alive
	 */
	public boolean isConnected();

	/**
	 * Like isConnected but will automatically try to reconnect
	 * 
	 * @return false it the connection is neither alive nor can be established
	 * @throws ConnectionManagerException
	 * @since Iteration3
	 */
	public boolean validate() throws ConnectionManagerException;
}
