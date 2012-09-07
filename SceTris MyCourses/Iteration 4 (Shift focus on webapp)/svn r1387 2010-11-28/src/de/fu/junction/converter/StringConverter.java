/* Converter.java / 11:19:24 PM
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
public interface StringConverter<T> {
	T convert(String value);
}
