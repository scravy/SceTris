/* GenericEntity.java / 2:40:41 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class GenericEntity extends GenericRelation implements Entity {
	public abstract int compareTo(Entity entity);

	@Override
	public int compareTo(final Relation relation) {
		if (relation instanceof Entity) {
			return compareTo((Entity) relation);
		}
		return 2;
	}
}
