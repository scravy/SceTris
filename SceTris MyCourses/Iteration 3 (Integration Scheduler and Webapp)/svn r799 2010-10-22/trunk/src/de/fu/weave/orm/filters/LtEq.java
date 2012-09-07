/* LtEq.java / 11:37:10 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class LtEq extends AbstractFilter {

	/**
	 * 
	 * @param field
	 * @param compareValue
	 * @since Iteration2
	 */
	public LtEq(final String field, final Comparable<?> compareValue) {
		super(field, compareValue);
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" <= ?";
	}
}
