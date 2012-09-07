/* Function.java / 10:45:53 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

/**
 * A function which goes from I -> O, that is: a Method which takes 1 parameter of type I and
 * returns a result of type O.
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Function<I, O> {
	/**
	 * 
	 * @param input
	 * @return
	 * @since Iteration4
	 */
	public O call(I input);
}
