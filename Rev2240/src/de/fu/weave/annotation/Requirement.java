/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.annotation;

/**
 * Enumeration of things that may be needed by a module.
 * <p>
 */
@Deprecated
public enum Requirement {
    /**
     * The Module requires access to the Database
     */
    DATABASE,

    /**
     * The Module requires that the user is logged in.
     */
    LOGGED_IN
}
