/* Like.java / 11:38:05 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.filters;

import de.fu.bakery.orm.java.Filter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class IsNull implements Filter {

	private final String fieldName;

	/**
	 * 
	 * @param field
	 * @param likeString
	 * @since Iteration2
	 */
	public IsNull(final String field) {
		fieldName = field;
	}

	@Override
	public String toString() {
		// http://www.postgresql.org/docs/8.0/interactive/functions-comparison.html
		return "\"" + fieldName + "\" IS NULL";
	}
}
