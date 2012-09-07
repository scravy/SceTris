/* InsufficientPrivilegesException.java / 12:57:04 AM
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
public class AccessDeniedException extends Exception {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @since Iteration2
	 */
	public AccessDeniedException() {

	}

	/**
	 * @param message
	 * @since Iteration2
	 */
	public AccessDeniedException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @since Iteration2
	 */
	public AccessDeniedException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 * @since Iteration2
	 */
	public AccessDeniedException(final Throwable cause) {
		super(cause);
	}

}
