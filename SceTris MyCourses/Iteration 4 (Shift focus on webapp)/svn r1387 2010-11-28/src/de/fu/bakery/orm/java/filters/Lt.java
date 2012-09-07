/* LessThan.java / 11:35:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.filters;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Lt extends AbstractFilter {

	/**
	 * 
	 * @param field
	 * @param compareValue
	 * @since Iteration2
	 */
	public Lt(final String field, final Comparable<?> compareValue) {
		super(field, compareValue);
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" < ?";
	}
}
