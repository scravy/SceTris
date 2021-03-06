/* M.java / 2:38:04 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import de.fu.junction.Dynamic;
import de.fu.junction.annotation.Experimental;
import de.fu.junction.annotation.meta.Author;

/**
 * Math functions (experimental)
 */
@Author("Julian Fleischer")
@Experimental
public final class M {

	public static final Function2<Double,Double,Double> pow = Dynamic.function2(M.class, "pow",
																				 double.class,
																				 double.class,
																				 double.class);
	public static final Function<Double,Double> square = pow.asFunction(2.0);
	public static final Function<Double,Double> sqrt = pow.asFunction(0.5);

}
