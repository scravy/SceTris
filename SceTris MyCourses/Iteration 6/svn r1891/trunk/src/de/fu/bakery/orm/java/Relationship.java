/* Relationship.java / 10:08:46 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

import de.fu.weave.annotation.meta.Author;

@Author("Julian Fleischer")
public interface Relationship extends Relation {

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	Entity object() throws DatabaseException;

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	Entity subject() throws DatabaseException;
}
