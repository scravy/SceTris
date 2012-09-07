/* Strings.java / 2:40:13 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.util;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Strings {

	/**
	 * 
	 * @param s
	 * @return
	 * @since Iteration4
	 */
	public static String capitalize(final String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

}
