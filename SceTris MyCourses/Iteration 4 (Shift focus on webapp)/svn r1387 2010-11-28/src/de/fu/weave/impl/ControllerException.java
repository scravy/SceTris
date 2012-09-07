/* ControllerException.java / 9:59:22 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class ControllerException extends Exception {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -6211693817357617776L;

	/**
	 * 
	 * @since Iteration2
	 */
	public ControllerException() {

	}

	/**
	 * @param message
	 * @since Iteration2
	 */
	public ControllerException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @since Iteration2
	 */
	public ControllerException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 * @since Iteration2
	 */
	public ControllerException(final Throwable cause) {
		super(cause);
	}

}
