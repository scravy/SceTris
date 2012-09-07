/* ErrorHandler.java / 1:40:50 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.skuld;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Controller;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
class SkuldErrorHandler<R extends RelationManager> extends SkuldModule<R> {

	/**
	 * @param parent
	 * @since Iteration2
	 */
	public SkuldErrorHandler(final Controller<R> parent) {
		super(parent);
	}

}
