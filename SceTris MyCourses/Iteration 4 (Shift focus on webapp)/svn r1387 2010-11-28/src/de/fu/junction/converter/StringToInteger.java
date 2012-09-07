/* IntegerConverter.java / 11:51:59 PM
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
public class StringToInteger implements StringConverter<Integer> {

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	@Override
	public Integer convert(final String value) {
		try {
			return Integer.parseInt(value);
		} catch (NumberFormatException e) {
			return null;
		}
	}
}
