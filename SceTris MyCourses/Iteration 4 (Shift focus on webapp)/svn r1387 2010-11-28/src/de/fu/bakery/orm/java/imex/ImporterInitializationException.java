/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.imex;

import java.sql.SQLException;

/**
 * 
 * @author Julian Fleischer
 */
public class ImporterInitializationException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498040625505350242L;

	/**
	 * 
	 */
	public ImporterInitializationException() {
		super();
	}

	/**
	 * 
	 * @param exception
	 */
	public ImporterInitializationException(final SQLException exception) {
		super(exception.getMessage(), exception);
	}

	/**
	 * 
	 * @param message
	 */
	public ImporterInitializationException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ImporterInitializationException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public ImporterInitializationException(final Throwable cause) {
		super(cause);
	}
}
