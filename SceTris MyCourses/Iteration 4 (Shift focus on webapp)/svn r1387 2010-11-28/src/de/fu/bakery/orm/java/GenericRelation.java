/* GenericRelation.java / 2:42:33 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class GenericRelation implements Relation {
	protected Object timekey(final boolean update) {
		return null;
	}
}
