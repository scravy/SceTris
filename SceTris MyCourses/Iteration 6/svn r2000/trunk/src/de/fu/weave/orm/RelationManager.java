/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.util.Collection;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.User;

/**
 * 
 */
@Author("Julian Fleischer")
public interface RelationManager extends Cloneable {
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
	 * @return
	 */
	public RelationManager clone();

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
