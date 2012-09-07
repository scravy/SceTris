/* MalformedEMailAddressException.java / 11:22:33 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

/**
 * Thrown to indicate that the hostname portion of an email address is invalid.
 */
public class MalformedHostnameException extends InvalidSyntaxException {

	private static final long serialVersionUID = -8279494039562654662L;

	/**
	 * Default constructor with an exact reason.
	 * 
	 * @param $arg
	 *            The exact reason.
	 */
	public MalformedHostnameException(final String $arg) {
		super($arg);
	}

}
