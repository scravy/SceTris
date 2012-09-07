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
 * Maps a param in an {@link Action} to a param of the HTTP request
 * <p>
 * 
 * @author Julian Fleischer
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.PARAMETER)
@Documented
public @interface Arg {
	/**
	 * Default value if the annotated param is of type double.
	 * 
	 * @return The default value
	 */
	boolean booleanDefault() default false;

	/**
	 * Default value if the annotated param is of type double.
	 * 
	 * @return The default value
	 */
	double doubleDefault() default 0;

	/**
	 * Default value if the annotated param is of type float.
	 * 
	 * @return The default value
	 */
	float floatDefault() default 0;

	/**
	 * Default value if the annotated param is of type int.
	 * 
	 * @return The default value
	 */
	int intDefault() default 0;

	/**
	 * Denotes if this param contains the target of a required Permission.
	 * <p>
	 * See {@link Action#requiresPrivilege()}
	 * 
	 * @return ?
	 */
	boolean isTarget() default false;

	/**
	 * Default value if the annotated param is of type long.
	 * 
	 * @return The default value
	 */
	long longDefault() default 0;

	/**
	 * The name of the parameter.
	 * 
	 * @return The name.
	 */
	String name();

	/**
	 * Default value if the annotated param is of type String or String[].
	 * 
	 * @return The default value
	 */
	String stringDefault() default "";
}
