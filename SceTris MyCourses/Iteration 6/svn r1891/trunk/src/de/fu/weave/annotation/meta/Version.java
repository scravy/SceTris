/* Version.java / 1:44:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@Documented
@Retention(RetentionPolicy.CLASS)
public @interface Version {
	String value();
}
