/* Importer.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.imex;

import java.util.concurrent.BlockingQueue;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.RelationManager;



/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Importer<T> {

	/**
	 * 
	 * 
	 * @author Julian Fleischer
	 * @since Iteration4
	 */
	interface Info {
	}

	/**
	 * 
	 * 
	 * @author Julian Fleischer
	 * @since Iteration4
	 */
	interface Warning {
	}

	/**
	 * 
	 * @param source
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	void doImport(T source) throws ImportException;

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	BlockingQueue<Object> getMessages();

	/**
	 * 
	 * @param manager
	 * @since Iteration4
	 */
	void setManager(RelationManager manager);
}
