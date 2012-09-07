/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Julian Fleischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Commit {

	/**
	 * TODO: Describe this item.
	 * 
	 * @return The name of the action of which this is the Commit for.
	 */
	String action();

	/**
	 * TODO: Describe this item.
	 * 
	 * @return The name of the action to execute after the Commit.
	 */
	String after();

}
