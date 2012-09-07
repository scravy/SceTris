/* Any.java / 2:33:54 AM
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
public class All implements Filter {
	public final Filter[] filters;

	public All(final Filter... filters) {
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
			sql.append(") AND ");
		}
		sql.setLength(sql.length() - 5);
		return sql.toString();
	}
}
