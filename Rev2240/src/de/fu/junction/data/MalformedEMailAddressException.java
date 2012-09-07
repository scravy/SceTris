/* MalformedEMailAddressException.java / 11:22:33 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import de.fu.junction.annotation.meta.Author;

/**
 * Thrown to indicate that an email address is malformed.
 */
@Author("Julian Fleischer")
public class MalformedEMailAddressException extends InvalidSyntaxException {

	private static final long serialVersionUID = -208361807004161346L;

	/**
	 * The default constructor with an exact reason.
	 * 
	 * @param $arg
	 *            The exact reason.
	 */
	public MalformedEMailAddressException(final String $arg) {
		super($arg);
	}

}
