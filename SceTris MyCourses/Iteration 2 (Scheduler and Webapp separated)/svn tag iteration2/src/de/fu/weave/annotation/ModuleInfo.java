/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * 
 * @author Julian Fleischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface ModuleInfo {
	/**
	 * 
	 * @return The authors of the annotated Module as an Array of Strings.
	 */
	String[] author() default "";

	/**
	 * 
	 * @return The description of the annotated Module, or the empty string.
	 */
	String description() default "";

	/**
	 * 
	 * @return The homepage of the annotated Module, or the empty string.
	 */
	String homepage() default "";

	/**
	 * Denotes the license under which the annotated Module is distributed.
	 * 
	 * @return The name of the license.
	 */
	String license() default "";

	/**
	 * The name of the Module.
	 * <p>
	 * The Module will be accessible via the name given here. Naturally this
	 * needs to be a unique identifier, therefore the Controller will throw an
	 * Exception when attempting to load a Module that defines a name twice.
	 * Typically the action will be located at
	 * <tt>/<servlet-path>/<module-name></tt>
	 * 
	 * @return The name of the module.
	 */
	String name();

	/**
	 * Requirements of this Module.
	 * <p>
	 * See {@link Requirement}.
	 * 
	 * @return An array of Requirements (may be empty)
	 */
	Requirement[] requires() default {};

	/**
	 * The version of the annotated Module.
	 * <p>
	 * The string may be anything, including the empty String.
	 * 
	 * @return A string identifying this version.
	 */
	String version() default "";

	/**
	 * The menuidentifier of the annotated Module.
	 * Needed to reload the correct submenu
	 * <p>
	 * The string may be anything, including the empty String.
	 * 
	 * @return A string identifying the menuposition.
	 */
	String menu() default "";
}
