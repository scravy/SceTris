/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * 
 * 
 * @author Julian Fleischer
 */
public class UnknownModuleException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7558834501893250274L;

	/**
	 * 
	 */
	public UnknownModuleException() {

	}

	/**
	 * 
	 * @param message
	 */
	public UnknownModuleException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public UnknownModuleException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public UnknownModuleException(final Throwable cause) {
		super(cause);
	}

}
