/* Description.java / 1:44:59 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation.meta;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@Title(@L(value = "Description of this item", lang = "en"))
public @interface Title {
	L[] value();
}
