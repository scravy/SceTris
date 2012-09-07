/* Tupel.java / 2:44:23 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.util;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Array<A> {

	/**
	 * 
	 * @param <A>
	 * @param elements
	 * @return
	 * @since Iteration4
	 */
	public static <A> A[] array(final A... elements) {
		return new Array<A>(elements).get();
	}

	/**
	 * 
	 */
	private final A[] elements;

	/**
	 * 
	 * @param elements
	 * @since Iteration4
	 */
	private Array(final A... elements) {
		this.elements = elements;
	}

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	private A[] get() {
		return elements;
	}

}
