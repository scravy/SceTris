/* Author.java / 10:31:30 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * Associates information about one or more authors with a class.
 */
@Documented
@Retention(RetentionPolicy.CLASS)
@Author("Julian Fleischer")
public @interface Author {
	/**
	 * The names of the authors as Strings.
	 */
	String[] value();
}
