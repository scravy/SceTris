/* Id.java / 11:38:14 PM
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
public class Id implements Filter {
	/**
	 * 
	 */
	final public int id;

	/**
	 * 
	 * @param id
	 * @since Iteration2
	 */
	public Id(final int id) {
		this.id = id;
	}
}
