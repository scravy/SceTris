/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

import java.util.Collection;

import de.fu.weave.User;

/**
 * 
 * @author Julian Fleischer
 */
public interface RelationManager {

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public ConnectionManager connectionManager();

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 * @since Iteration2
	 */
	public User fetchUser(int id) throws DatabaseException;

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	public Collection<? extends User> fetchUsers(Filter filter) throws DatabaseException;
}
