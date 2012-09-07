/* Version.java / 1:44:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation.meta;

import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Author("Julian Fleischer")
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface Version {
	String value();
}
