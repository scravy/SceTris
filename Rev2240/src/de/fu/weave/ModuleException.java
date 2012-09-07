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
 * @since Iteration2
 */
public class ModuleException extends Exception {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -2681857174373559990L;

	/**
	 * 
	 * @since Iteration2
	 */
	public ModuleException() {

	}

	/**
	 * 
	 * @since Iteration2
	 * @param message
	 */
	public ModuleException(final String message) {
		super(message);
	}

	/**
	 * 
	 * @param message
	 * @param cause
	 * @since Iteration2
	 */
	public ModuleException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * 
	 * @param cause
	 * @since Iteration2
	 */
	public ModuleException(final Throwable cause) {
		super(cause);
	}

}
