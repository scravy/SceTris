/* ControllerInstantiationException.java / 6:43:04 PM
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
public class ControllerInstantiationException extends Exception {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = 6949889700553312409L;

	/**
	 * 
	 * @since Iteration4
	 */
	public ControllerInstantiationException() {
		super();
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public ControllerInstantiationException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @since Iteration4
	 */
	public ControllerInstantiationException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public ControllerInstantiationException(final Throwable arg0) {
		super(arg0);
	}

}
