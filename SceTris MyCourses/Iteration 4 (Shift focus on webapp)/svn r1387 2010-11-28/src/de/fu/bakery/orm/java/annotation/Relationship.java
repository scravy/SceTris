/* Relationship.java / 7:46:08 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.annotation;

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
@Retention(RetentionPolicy.CLASS)
@Target(ElementType.TYPE)
public @interface Relationship {
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
	Class<? extends de.fu.bakery.orm.java.Entity> object();

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	Class<? extends de.fu.bakery.orm.java.Entity> subject();

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	Key[] uniqueKeys() default {};
}
