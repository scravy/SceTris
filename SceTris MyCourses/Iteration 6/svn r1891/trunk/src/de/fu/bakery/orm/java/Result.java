/* Result.java / 12:28:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

import java.util.Collection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class Result<T extends Relation> {
	final private T singleResult;
	final private Collection<T> results;

	/**
	 * 
	 * @param entities
	 * @since Iteration3
	 */
	public Result(final Collection<T> entities) {
		singleResult = null;
		results = entities;
	}

	/**
	 * 
	 * @param relation
	 * @since Iteration3
	 */
	public Result(final T relation) {
		singleResult = relation;
		results = null;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public T getResult() {
		return singleResult;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public Collection<T> getResults() {
		return results;
	}
}
