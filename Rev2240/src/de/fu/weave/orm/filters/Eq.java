/* Equals.java / 11:35:22 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;

import de.fu.weave.orm.Filter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Eq implements Filter {
	public final String fieldName;
	public final Object compareValue;

	public Eq(final String field, final Object value) {
		super();
		fieldName = field;
		compareValue = value;
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" = ?";
	}
}
