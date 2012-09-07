/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

import java.util.Collection;

import de.fu.weave.User;
import de.fu.weave.annotation.meta.Author;

/**
 * 
 */
@Author("Julian Fleischer")
public interface RelationManager {
	/**
	 * 
	 */
	public void abortTransaction() throws DatabaseException;

	/**
	 * 
	 */
	public void beginTransaction() throws DatabaseException;

	/**
	 * 
	 */
	public void commitTransaction() throws DatabaseException;

	/**
	 * 
	 * @return
	 */
	public ConnectionManager connectionManager();

	/**
	 * 
	 * @param id
	 * @return
	 * @throws DatabaseException
	 */
	public User fetchUser(int id) throws DatabaseException;

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	public Collection<? extends User> fetchUsers(Filter filter) throws DatabaseException;
}
