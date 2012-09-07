/* Controller.java / 11:17:54 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;


import de.fu.junction.annotation.meta.Author;
import de.fu.weave.orm.RelationManager;

/**
 *
 */
@Author("Julian Fleischer")
public interface Controller<R extends RelationManager> {

    /**
     * Returns the RelationManager which is used by this weavlet.
     * 
     * @return The RelationManager used by this weavlet.
     */
    R manager();
}
