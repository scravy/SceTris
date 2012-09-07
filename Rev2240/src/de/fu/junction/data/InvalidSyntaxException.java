/* InvalidSyntaxException.java / 11:44:39 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import de.fu.junction.annotation.meta.Author;

/**
 * Thrown to indicate invalid syntax.
 */
@Author("Julian Fleischer")
public class InvalidSyntaxException extends Exception {

	private static final long serialVersionUID = 315682624683250832L;

	/**
	 * Default constructor with the malformed value.
	 * 
	 * @param $arg
	 *            The malformed value.
	 */
	public InvalidSyntaxException(final String $arg) {
		super($arg);
	}

	public String getMalformedValue() {
		return getMessage();
	}
}
