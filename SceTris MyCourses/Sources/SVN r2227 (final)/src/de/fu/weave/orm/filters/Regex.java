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
public class Regex extends AbstractFilter {

	/**
	 * 
	 * @param field
	 * @param regex
	 * @since Iteration2
	 */
	public Regex(final String field, final String regex) {
		super(field, regex);
	}

	@Override
	public String toString() {
		return "\"" + fieldName + "\" ~ ?";
	}
}
