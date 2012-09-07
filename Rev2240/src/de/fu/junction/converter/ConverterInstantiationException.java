/* ConverterInstantiationException.java / 12:55:50 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class ConverterInstantiationException extends Exception {

	/**
	 * 
	 * @since Iteration4
	 */
	private static final long serialVersionUID = 1L;

	public ConverterInstantiationException() {
		super();
	}

	public ConverterInstantiationException(final String arg0) {
		super(arg0);
	}

	public ConverterInstantiationException(final String arg0, final Throwable arg1) {
		super(arg0, arg1);
	}

	public ConverterInstantiationException(final Throwable arg0) {
		super(arg0);
	}

}
