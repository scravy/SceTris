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
public interface Relation {

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	void create() throws DatabaseException;

	/**
	 * 
	 * 
	 * @since Iteration2
	 */
	void delete() throws DatabaseException;

	/**
	 * 
	 * 
	 * @since Iteration2
	 */
	void pushChanges() throws DatabaseException;
}
