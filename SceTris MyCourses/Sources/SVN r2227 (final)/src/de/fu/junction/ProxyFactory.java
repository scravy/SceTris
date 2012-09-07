/* ProxyFactory.java / 10:58:43 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

/**
 *
 */
public interface ProxyFactory<T> {

	/**
	 * 
	 * @param $object
	 * @return
	 */
	T newInstance(final T $object);
}
