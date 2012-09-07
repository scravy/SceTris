/* MalformedEMailAddressException.java / 11:22:33 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import de.fu.junction.annotation.meta.Author;

/**
 * Thrown to indicate that the mailbox part of an email address is malformed.
 */
@Author("Julian Fleischer")
public class MalformedMailboxPartException extends InvalidSyntaxException {

	private static final long serialVersionUID = -7003601370608169901L;

	/**
	 * Default constructor with an exact reason.
	 * 
	 * @param $arg
	 *            The exact reason.
	 */
	public MalformedMailboxPartException(final String $arg) {
		super($arg);
	}

}
