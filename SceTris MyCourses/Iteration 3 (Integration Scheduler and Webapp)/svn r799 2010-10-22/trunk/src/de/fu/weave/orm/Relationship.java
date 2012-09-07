/* Relationship.java / 10:08:46 PM
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
public interface Relationship extends Relation {

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	Entity getObject() throws DatabaseException;

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	Entity getSubject() throws DatabaseException;
}
