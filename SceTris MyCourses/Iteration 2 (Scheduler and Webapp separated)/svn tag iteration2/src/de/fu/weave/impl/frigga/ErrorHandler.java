/* ErrorHandler.java / 1:40:50 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import de.fu.weave.Controller;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
class ErrorHandler<R extends RelationManager> extends GenericModule<R> {

	/**
	 * @param parent
	 * @since Iteration2
	 */
	public ErrorHandler(final Controller<R> parent) {
		super(parent);
	}

}
