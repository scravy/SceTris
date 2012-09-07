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
public class TemplateManagerException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3467951159067470766L;

	/**
	 * 
	 */
	public TemplateManagerException() {

	}

	/**
	 * 
	 * @param message
	 *            -
	 */
	public TemplateManagerException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 *            -
	 * @param cause
	 *            -
	 */
	public TemplateManagerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 *            -
	 */
	public TemplateManagerException(final Throwable cause) {
		super(cause);
	}

}
