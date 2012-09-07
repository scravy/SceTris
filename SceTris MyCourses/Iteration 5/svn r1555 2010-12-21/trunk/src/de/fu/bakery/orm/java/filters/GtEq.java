/* GtEq.java / 11:37:00 PM
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
public class GtEq extends AbstractFilter {

	/**
	 * 
	 * @param field
	 * @param compareValue
	 * @since Iteration2
	 */
	public GtEq(final String field, final Comparable<?> compareValue) {
		super(field, compareValue);
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" >= ?";
	}
}
