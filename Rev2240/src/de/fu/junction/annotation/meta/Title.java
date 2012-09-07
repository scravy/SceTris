/* Description.java / 1:44:59 PM
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
@Retention(RetentionPolicy.RUNTIME)
@Title(@L(value = "Description of this item", lang = "en"))
public @interface Title {
	L[] value();
}
