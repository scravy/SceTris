/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.sql.SQLException;

/**
 * 
 * @author Julian Fleischer
 */
public class ImportException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1498040625505350242L;

	/**
	 * 
	 */
	public ImportException() {
		super();
	}

	/**
	 * 
	 * @param exception
	 */
	public ImportException(final SQLException exception) {
		super(exception.getMessage(), exception);
	}

	/**
	 * 
	 * @param message
	 */
	public ImportException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public ImportException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public ImportException(final Throwable cause) {
		super(cause);
	}
}
