/* InvalidSyntaxException.java / 11:44:39 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

/**
 *
 */
@SuppressWarnings("serial")
public class InvalidSyntaxException extends Exception {

	public InvalidSyntaxException() {
		super();
	}

	public InvalidSyntaxException(final String arg0) {
		super(arg0);
	}

	public InvalidSyntaxException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public InvalidSyntaxException(final Throwable arg0) {
		super(arg0);
	}

}
