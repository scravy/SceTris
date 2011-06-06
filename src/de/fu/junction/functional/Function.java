/* Function.java / 10:45:53 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import de.fu.junction.annotation.meta.Author;

/**
 * A function which goes from I -> O, that is: a Method which takes 1 parameter of type I and
 * returns a result of type O.
 */
@Author("Julian Fleischer")
public interface Function<I, O> {
	/**
	 * Apply an argument to that function.
	 * 
	 * @param $arg
	 * @return
	 */
	public O call(I $arg);
}
