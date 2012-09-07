/* SpecialAction.java / 3:03:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

import java.util.Map;

import de.fu.weave.orm.RelationManager;



/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
abstract class SpecialAction<R extends RelationManager> extends FriggModule<R> {
	SpecialAction(final FriggWeavlet<R> parent) {
		super(parent);
	}

	abstract void doIt(final Map<String,String[]> parameters) throws SpecialActionException;
}
