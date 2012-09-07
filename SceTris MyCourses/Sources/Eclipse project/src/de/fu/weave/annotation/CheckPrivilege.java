/* CheckPrivilege.java / 8:36:24 AM
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

import de.fu.junction.annotation.meta.Author;

/**
 * A marker Annotation which is just needed by {@see de.fu.scetris.build.GatherPrivileges} to find
 * all privileges for automatic creation of those during setup.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
@Author("Julian Fleischer")
public @interface CheckPrivilege {
    String[] value();
}
