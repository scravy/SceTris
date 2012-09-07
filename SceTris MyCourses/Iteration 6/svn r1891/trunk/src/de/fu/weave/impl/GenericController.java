/* GenericController.java / 8:29:56 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.junction.Tuple;
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

	public static <R extends RelationManager> Tuple<String,Class<? extends Module<R>>> t(final String name,
																						 final Class<? extends Module<R>> clazz) {
		return new Tuple<String,Class<? extends Module<R>>>(name, clazz);
	}

	/**
	 * @param entityManagerClass
	 * @param firstModule
	 * @param modulesToBeLoaded
	 * @throws ControllerInstantiationException
	 * @since Iteration4
	 */
	public GenericController(final Class<R> entityManagerClass,
			final Tuple<String,Class<? extends Module<R>>> firstModule,
			final Tuple<String,Class<? extends Module<R>>>... modulesToBeLoaded)
			throws ControllerInstantiationException {
		super(entityManagerClass, firstModule, modulesToBeLoaded);
	}
}
