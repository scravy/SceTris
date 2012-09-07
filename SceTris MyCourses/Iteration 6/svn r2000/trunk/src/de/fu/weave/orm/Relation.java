/* Relation.java / 9:06:01 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public interface Relation extends Comparable<Relation> {

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	Relation create() throws DatabaseException;

	/**
	 * 
	 * 
	 * @since Iteration2
	 */
	void delete() throws DatabaseException;

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean exists();

	/**
	 * 
	 * 
	 * @since Iteration2
	 */
	void pushChanges() throws DatabaseException;
}
