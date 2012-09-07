/* Validator.java / 1:44:48 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import de.fu.weave.reflect.ValidationException;

/**
 * A Validator can be used for checking the semantic validity of a value.
 * 
 * @param <T>
 */
public interface Validator {
	public boolean check(final Object value) throws ValidationException;
}
