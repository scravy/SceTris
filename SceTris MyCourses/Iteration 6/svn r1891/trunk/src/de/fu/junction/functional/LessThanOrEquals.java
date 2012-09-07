/* Equals.java / 2:22:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class LessThanOrEquals<T extends Comparable<? super T>> extends
		AbstractFunction2<T,T,Boolean> {

	@Override
	public Boolean call(final T in1, final T in2) {
		return in1.compareTo(in2) <= 0;
	}
}
