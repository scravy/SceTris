/* Like.java / 11:38:05 PM
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
public class SortDesc extends Sort {

	public SortDesc(final String... fields) {
		super(Ordering.DESC, fields);
	}
}
