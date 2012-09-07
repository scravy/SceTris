/* Validator.java / 1:44:48 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * A Validator can be used for checking the semantic validity of a value.
 * 
 * @param <T>
 */
public interface Validator<T> {
	public boolean check(final T value) throws Exception;
}
