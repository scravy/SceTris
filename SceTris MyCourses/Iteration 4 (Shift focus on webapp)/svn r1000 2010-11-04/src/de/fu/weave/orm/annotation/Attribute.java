/* Attribute.java / 1:30:14 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.annotation;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
@Documented
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface Attribute {
	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	String name();

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	boolean nullable() default false;

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean partialKey() default false;

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean primary() default false;

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	Class<? extends de.fu.weave.orm.Entity>[] ref() default {};

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	boolean serial() default false;

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	String[] use() default {};
}
