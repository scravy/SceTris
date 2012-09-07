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
public class Differs extends AbstractFunction2<Object,Object,Boolean> {

	@Override
	public Boolean call(final Object in1, final Object in2) {
		return !in1.equals(in2);
	}

}
