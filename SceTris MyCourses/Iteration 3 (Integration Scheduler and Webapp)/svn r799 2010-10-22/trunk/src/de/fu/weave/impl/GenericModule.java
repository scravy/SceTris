/* GenericModule.java / 1:12:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

import de.fu.weave.Controller;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class GenericModule<R extends RelationManager> extends
		de.fu.weave.impl.frigga.GenericModule<R> {

	public GenericModule(final Controller<R> parent) {
		super(parent);
	}

}
