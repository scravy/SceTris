/* DynamicInvocationTargetException.java / 10:36:58 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.dynamic;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class DynamicInvocationTargetException extends RuntimeException {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @since Iteration4
	 */
	public DynamicInvocationTargetException() {
		super();
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public DynamicInvocationTargetException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @since Iteration4
	 */
	public DynamicInvocationTargetException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public DynamicInvocationTargetException(final Throwable arg0) {
		super(arg0);
	}

}
