/* Like.java / 11:38:05 PM
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
public class Sort implements Filter {

	public static enum Ordering {
		ASC {
			@Override
			public String toString() {
				return "ASC";
			}
		},

		DESC {
			@Override
			public String toString() {
				return "DESC";
			}
		}
	}

	public final Ordering sortOrdering;

	public final String[] sortFields;

	/**
	 * 
	 * @param fields
	 * @param ord
	 * @since Iteration4
	 */
	public Sort(final Ordering ord, final String... fields) {
		sortOrdering = ord;
		sortFields = fields;
	}

	/**
	 * 
	 * @param field
	 * @param likeString
	 * @since Iteration2
	 */
	public Sort(final String... fields) {
		sortOrdering = Ordering.ASC;
		sortFields = fields;
	}

	@Override
	public String toString() {
		return "";
	}
}
