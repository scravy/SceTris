/* Description.java / 1:44:59 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation.meta;

/**
 * 
 */
@Author("Julian Fleischer")
@Description(@L(value = "Description of this item", lang = "en"))
public @interface Description {
	L[] value();
}
