/* ValidationException.java / 1:24:04 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

/**
 *
 */
public class ValidationException extends Exception {

	private static final long serialVersionUID = 6975770457043370075L;

	/**
	 * 
	 */
	public ValidationException() {}

	/**
	 * @param arg0
	 */
	public ValidationException(final String arg0) {
		super(arg0);
	}

}
