/* Function.java / 10:45:53 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

/**
 * A function which goes from I -> J -> O, that is: a Method which takes 2 parameters and returns a
 * result of type O.
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Function2<I, J, O> {
	/**
	 * Retrieve a function which takes one parameter of type I only.
	 * 
	 * Essentially the resulting function is the same Function as this Function (i.e. the underlying
	 * method stays the same), but the given parameter is always applied as second parameter (i.e.
	 * the parameter which is of type J).
	 * 
	 * @param arg
	 * @return
	 * @since Iteration4
	 */
	public Function<I,O> asFunction(J arg);

	/**
	 * 
	 * @param arg
	 * @return
	 */
	public Function<J,O> asFunctionFlipped(I arg);

	/**
	 * Call this function with the parameters of type I and J and retrieve the result of type O.
	 * 
	 * @param in1
	 * @param in2
	 * @return
	 * @since Iteration4
	 */
	public O call(I in1, J in2);
}
