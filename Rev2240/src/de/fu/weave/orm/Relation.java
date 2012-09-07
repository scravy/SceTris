/* Relation.java / 9:06:01 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;


import de.fu.junction.annotation.meta.Author;

/**
 * A Relation is either an {@see Entity} or a {@see Relationship}. This is a object-oriented
 * approach to map the relational model.
 * <p>
 * Relation objects are active records, i.e. they manage database access and transfer themselves.
 * However, all of them are supported by a {@see RelationManager}.
 * 
 * @since Iteration2
 */
@Author("Julian Fleischer")
public interface Relation extends Comparable<Relation> {

    /**
     * Created the record in the database.
     * 
     * @return
     * @since Iteration2
     */
    Relation create() throws DatabaseException;

    /**
     * Deletes the record from the database.
     * 
     * @since Iteration2
     */
    void delete() throws DatabaseException;

    /**
     * Checks whether this record has a counter part in the database.
     * 
     * @return
     * @since Iteration4
     */
    boolean exists();

    /**
     * Publishes local changes to the database.
     * 
     * @since Iteration2
     */
    void pushChanges() throws DatabaseException;
}
