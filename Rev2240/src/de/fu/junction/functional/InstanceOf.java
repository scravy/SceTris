/* Equals.java / 2:22:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import de.fu.junction.annotation.meta.Author;

/**
 * A function that checks whether a given object is an instance of a given class.
 */
@Author("Julian Fleischer")
public class InstanceOf extends AbstractFunction2<Object,Class<?>,Boolean> {

	@Override
	public Boolean call(final Object obj, final Class<?> clazz) {
		return clazz.isAssignableFrom(obj.getClass());
	}
}
