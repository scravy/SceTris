/* Localized.java / 1:45:24 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 */
@Author("Julian Fleischer")
@Retention(RetentionPolicy.CLASS)
public @interface L {
	String lang() default "en";

	String value();
}
