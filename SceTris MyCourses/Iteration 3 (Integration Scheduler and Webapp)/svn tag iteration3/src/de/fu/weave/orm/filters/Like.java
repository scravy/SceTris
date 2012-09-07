/* Like.java / 11:38:05 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Like extends AbstractFilter {

	/**
	 * 
	 * @param field
	 * @param likeString
	 * @since Iteration2
	 */
	public Like(final String field, final String likeString) {
		super(field, likeString);
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" ILIKE ?";
	}
}
