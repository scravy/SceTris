/* Any.java / 2:33:54 AM
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
public class Any implements Filter {
	public final Filter[] filters;

	/**
	 * 
	 * @param filters
	 * @since Iteration2
	 */
	public Any(final Filter... filters) {
		this.filters = filters;
	}

	/**
	 * 
	 */
	@Override
	public String toString() {
		StringBuilder sql = new StringBuilder();
		for (Filter filter : filters) {
			sql.append("(");
			sql.append(filter);
			sql.append(") OR ");
		}
		sql.setLength(sql.length() - 4);
		return sql.toString();
	}
}
