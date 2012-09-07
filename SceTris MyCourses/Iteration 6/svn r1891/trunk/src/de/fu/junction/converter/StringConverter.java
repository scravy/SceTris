/* Converter.java / 11:19:24 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

/**
 * A StringConverter provides a method to convert a String into a certain type (e.g. {@see
 * java.util.Date})
 * 
 * @author Julian Fleischer
 */
public interface StringConverter<T> {
	/**
	 * Converts the given value to the corresponding type T.
	 * 
	 * @param value
	 *            The string which is to be converted
	 * @return The converted value or null if the string-value can not be converted
	 */
	T convert(String value);
}
