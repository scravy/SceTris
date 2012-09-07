/* Localized.java / 1:45:24 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation.meta;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@Retention(RetentionPolicy.CLASS)
public @interface L {
	String lang() default "en";

	String value();
}
