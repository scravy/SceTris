/* ErrorHandler.java / 1:40:50 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Controller;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
class FriggErrorHandler<R extends RelationManager> extends FriggModule<R> {

	/**
	 * @param parent
	 * @since Iteration2
	 */
	public FriggErrorHandler(final Controller<R> parent) {
		super(parent);
	}

}
