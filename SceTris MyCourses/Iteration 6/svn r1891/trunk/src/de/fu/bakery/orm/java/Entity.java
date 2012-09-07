/* Entity.java / 9:26:25 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public interface Entity extends Relation {
	/**
	 * 
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	void pullChanges() throws DatabaseException;

	/**
	 * 
	 * @param depth
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	void pullChanges(int depth) throws DatabaseException;
}
