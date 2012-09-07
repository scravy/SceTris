/* ModuleNotLoadedException.java / 10:30:24 AM
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
public class ModuleNotLoadedException extends Exception {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = 1968700012593633L;

	/**
	 * 
	 * @since Iteration4
	 */
	public ModuleNotLoadedException() {

	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public ModuleNotLoadedException(final String arg0) {
		super(arg0);
	}

	/**
	 * @param arg0
	 * @param arg1
	 * @since Iteration4
	 */
	public ModuleNotLoadedException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	/**
	 * @param arg0
	 * @since Iteration4
	 */
	public ModuleNotLoadedException(final Throwable arg0) {
		super(arg0);
	}

}
