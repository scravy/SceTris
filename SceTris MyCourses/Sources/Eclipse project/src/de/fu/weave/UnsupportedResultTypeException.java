/* UnsupportedResultTypeException.java / 11:30:46 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class UnsupportedResultTypeException extends Exception {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = -6854279962707063128L;

	/**
	 * 
	 * @since Iteration4
	 */
	public UnsupportedResultTypeException() {
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public UnsupportedResultTypeException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @since Iteration4
	 */
	public UnsupportedResultTypeException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public UnsupportedResultTypeException(final Throwable arg0) {
		super(arg0);
	}

}
