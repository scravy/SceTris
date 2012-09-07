/* GenericController.java / 8:29:56 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;


import de.fu.junction.Tuple;
import de.fu.junction.annotation.meta.Author;
import de.fu.weave.WeavletInstantiationException;
import de.fu.weave.Module;
import de.fu.weave.orm.RelationManager;

/**
 * The GenericWeavlet offers a basic skeleton to extend from.
 */
@Author("Julian Fleischer")
public abstract class GenericWeavlet<R extends RelationManager> extends
        de.fu.weave.impl.frigg.FriggWeavlet<R> {

    /**
	 * 
	 */
    private static final long serialVersionUID = -8628825587153177862L;

    public static <R extends RelationManager> Tuple<String,Class<? extends Module<R>>> T(
        final String name,
                                                                                         final Class<? extends Module<R>> clazz) {
        return new Tuple<String,Class<? extends Module<R>>>(name, clazz);
    }

    /**
     * @param entityManagerClass
     * @param firstModule
     * @param modulesToBeLoaded
     * @throws WeavletInstantiationException
     */
    public GenericWeavlet(final Class<R> entityManagerClass,
            final Tuple<String,Class<? extends Module<R>>> firstModule,
            final Tuple<String,Class<? extends Module<R>>>... modulesToBeLoaded)
            throws WeavletInstantiationException {
        super(entityManagerClass, firstModule, modulesToBeLoaded);
    }
}
