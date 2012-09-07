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

	public InvalidSyntaxException(final String arg0) {
		super(arg0);
	}

	public String getMalformedValue() {
		return getMessage();
	}
}
