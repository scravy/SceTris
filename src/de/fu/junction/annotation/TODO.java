/* TODO.java / 11:16:57 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation;

import de.fu.junction.annotation.meta.Author;

/**
 * Indicates that there is some functionality missing (and which).
 */
@Author("Julian Fleischer")
public @interface TODO {
	/**
	 * 
	 * @return What has to be done about it.
	 */
	String value();
}
