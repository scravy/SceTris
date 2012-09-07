/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.util.Collection;

import de.fu.weave.User;

/**
 * 
 * @author Julian Fleischer
 */
public interface RelationManager {

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
	public Collection<? extends User> fetchUsers(Filter... filters) throws DatabaseException;
}
