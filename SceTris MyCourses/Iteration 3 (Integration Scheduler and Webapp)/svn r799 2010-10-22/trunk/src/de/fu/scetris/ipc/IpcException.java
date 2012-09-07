/* IpcException.java / 5:04:06 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;

/**
 * An exception that provides information on the interprocess communcation.
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class IpcException extends Exception {

	/**
	 * 
	 * @since Iteration3
	 */
	private static final long serialVersionUID = 1022633411315876053L;

	/**
	 * 
	 * @since Iteration3
	 */
	public IpcException() {

	}

	/**
	 * @param message
	 * @since Iteration3
	 */
	public IpcException(final String message) {
		super(message);
	}

	/**
	 * @param message
	 * @param cause
	 * @since Iteration3
	 */
	public IpcException(final String message, final Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 * @since Iteration3
	 */
	public IpcException(final Throwable cause) {
		super(cause);
	}

}
