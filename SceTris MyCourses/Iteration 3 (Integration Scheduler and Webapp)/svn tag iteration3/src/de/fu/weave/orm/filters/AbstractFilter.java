/* Filter.java / 11:44:27 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;

import de.fu.weave.orm.Filter;

/**
 * This is only a marker interface.
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public abstract class AbstractFilter implements Filter {
	/**
	 * 
	 */
	final String fieldName;

	/**
	 * 
	 */
	public final Comparable<?> compareValue;

	public AbstractFilter(final String field, final Comparable<?> compare) {
		fieldName = field;
		compareValue = compare;
	}
}
