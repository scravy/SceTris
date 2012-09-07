/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;


/**
 * 
 * @author Julian Fleischer
 */
public class ConnectionManagerException extends DatabaseException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3599828727359499953L;

	/**
	 * 
	 */
	public ConnectionManagerException() {

	}

	/**
	 * @param message
	 */
	public ConnectionManagerException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ConnectionManagerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public ConnectionManagerException(final Throwable cause) {
		super(cause);
	}

}
