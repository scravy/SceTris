/* Arg.java / 9:24:41 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.cli.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Arg {

	/**
	 * 
	 */
	Class<?>[] params = {};

	/**
	 * 
	 */
	String[] shortOpt = {};

	/**
	 * 
	 */
	String[] longOpt = {};

}
