/* ActionException.java / 11:01:28 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class SpecialActionException extends Exception {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -444424513365185537L;

	/**
	 * 
	 * @since Iteration2
	 */
	public SpecialActionException() {
		//
	}

	/**
	 * @param message
	 * @since Iteration2
	 */
	public SpecialActionException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @since Iteration2
	 */
	public SpecialActionException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 * @since Iteration2
	 */
	public SpecialActionException(final Throwable cause) {
		super(cause);
	}

}
