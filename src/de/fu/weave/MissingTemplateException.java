/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class MissingTemplateException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6589660997769272697L;

	/**
	 * 
	 */
	public MissingTemplateException() {

	}

	/**
	 * 
	 * @param message
	 */
	public MissingTemplateException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 */
	public MissingTemplateException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 */
	public MissingTemplateException(final Throwable cause) {
		super(cause);
	}

}
