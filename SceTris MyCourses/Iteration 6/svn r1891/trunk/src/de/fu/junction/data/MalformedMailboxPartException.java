/* MalformedEMailAddressException.java / 11:22:33 AM
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
public class MalformedMailboxPartException extends InvalidSyntaxException {

	/**
	 * @param arg0
	 */
	public MalformedMailboxPartException(final String arg0) {
		super(arg0);
	}

}
