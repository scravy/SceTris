/* Function.java / 10:45:53 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import de.fu.junction.annotation.meta.Author;

/**
 * A function that takes two parameters and results in one.
 * <p>
 * A function which goes from I -> J -> O, that is: a Method which takes 2 parameters and returns a
 * result of type O.
 */
@Author("Julian Fleischer")
public interface Function2<I, J, O> {
	/**
	 * Retrieve a function which takes one parameter of type I only.
	 * <p>
	 * Essentially the resulting function is the same Function as this Function (i.e. the underlying
	 * method stays the same), but the given parameter is always applied as second parameter (i.e.
	 * the parameter which is of type J).
	 * 
	 * @param $arg
	 *            The argument to apply as second parameter.
	 * @return A two-dimensional Function-object representing this function which takes only one
	 *         parameter.
	 */
	public Function<I,O> asFunction(J $arg);

	/**
	 * Retrieve a function which takes one parameter of type J only.
	 * <p>
	 * Essentially the resulting function is the same Function as this Function (i.e. the underlying
	 * method stays the same), but the given parameter is always applied as first parameter (i.e.
	 * the parameter which is of type I).
	 * 
	 * @param $arg
	 *            The argument to apply as first parameter.
	 * @return A two-dimensional Function-object representing this function which takes only one
	 *         parameter.
	 */
	public Function<J,O> asFunctionFlipped(I $arg);

	/**
	 * Call this function with the parameters of type I and J and retrieve the result of type O.
	 * 
	 * @param $in1
	 *            The first argument to apply.
	 * @param $in2
	 *            The second argument to apply.
	 * @return The result of the application of the two arguments.
	 */
	public O call(I $in1, J $in2);
}
