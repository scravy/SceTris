/* OutdatedRecordException.java / 9:47:22 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class OutdatedRecordException extends DatabaseException {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = 8772443548310322827L;

	public OutdatedRecordException() {
		super();
	}

	public OutdatedRecordException(final String message) {
		super(message);
	}

	public OutdatedRecordException(final String message, final Throwable cause) {
		super(message, cause);
	}

	public OutdatedRecordException(final Throwable cause) {
		super(cause);
	}

}
