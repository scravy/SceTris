/* OfflineCache.java / 1:36:57 AM
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
public class OfflineCache {

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public void flush() {

	}

	/**
	 * 
	 * @param clazz
	 * @param id
	 * @since Iteration3
	 */
	public <E extends Entity> E getEntity(final Class<E> clazz, final Comparable<?> id) {
		return null;
	}

	/**
	 * 
	 * @param <E>
	 * @param clazz
	 * @param id
	 * @return
	 * @since Iteration3
	 */
	public <E extends Entity> E getEntity(final Class<E> clazz, final int id) {
		return null;
	}

	/**
	 * 
	 * @param <R>
	 * @param clazz
	 * @param subject
	 * @param object
	 * @return
	 * @since Iteration3
	 */
	public <R extends Relationship> R getRelationship(final Class<R> clazz, final Entity subject,
													  final Entity object) {
		return null;
	}

	/**
	 * 
	 * @param entity
	 * @since Iteration3
	 */
	public void store(final Entity entity) {

	}

	/**
	 * 
	 * @param relationship
	 * @since Iteration3
	 */
	public void store(final Relationship relationship) {

	}
}
