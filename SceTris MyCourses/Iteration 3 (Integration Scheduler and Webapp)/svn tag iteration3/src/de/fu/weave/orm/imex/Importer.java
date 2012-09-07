/* Importer.java / 8:53:37 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.imex;

import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.ImportException;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Importer<T> {
	/**
	 * 
	 * @param source
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	void doImport(T source) throws ImportException;

	/**
	 * 
	 * @param manager
	 * @since Iteration4
	 */
	void setManager(RelationManager manager);
}
