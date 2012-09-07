/* Key.java / 3:14:22 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.annotation;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public @interface Key {
	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	String[] value();
}
