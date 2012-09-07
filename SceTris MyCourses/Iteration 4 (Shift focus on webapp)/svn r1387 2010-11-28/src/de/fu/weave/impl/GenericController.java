/* GenericController.java / 8:29:56 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

import javax.xml.parsers.ParserConfigurationException;


import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.Module;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public abstract class GenericController<R extends RelationManager> extends
		de.fu.weave.impl.frigg.FriggController<R> {

	/**
	 * 
	 * @since Iteration3
	 */
	private static final long serialVersionUID = -8628825587153177862L;

	/**
	 * 
	 * @param entityManagerClass
	 * @param modulesToBeLoaded
	 * @throws ParserConfigurationException
	 * @throws ControllerException
	 * @since Iteration3
	 */
	public GenericController(final Class<R> entityManagerClass,
			final Class<? extends Module<R>> firstModule,
			final Class<? extends Module<R>>... modulesToBeLoaded)
			throws ControllerInstantiationException {
		super(entityManagerClass, firstModule, modulesToBeLoaded);
	}

}
