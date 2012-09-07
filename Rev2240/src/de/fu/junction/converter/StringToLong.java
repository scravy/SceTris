/* StringToLong.java / 12:21:20 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToLong implements StringConverter<Long> {

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	@Override
	public Long convert(final String value) {
		try {
			return Long.parseLong(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}

}
