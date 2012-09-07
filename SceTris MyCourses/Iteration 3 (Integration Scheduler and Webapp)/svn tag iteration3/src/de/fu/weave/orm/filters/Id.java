/* Id.java / 11:38:14 PM
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
