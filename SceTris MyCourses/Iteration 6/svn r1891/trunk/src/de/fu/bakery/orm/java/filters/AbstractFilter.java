/* Filter.java / 11:44:27 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.filters;

import de.fu.bakery.orm.java.Filter;

/**
 * This is only a marker interface.
 * 
 * @author Julian Fleischer
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
