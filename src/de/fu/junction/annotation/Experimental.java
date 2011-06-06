/* Experimental.java / 11:16:45 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import de.fu.junction.annotation.meta.Author;

/**
 * Indicates that a certain feature is not intended for production use.
 */
@Retention(RetentionPolicy.RUNTIME)
@Author("Julian Fleischer")
public @interface Experimental {

}
