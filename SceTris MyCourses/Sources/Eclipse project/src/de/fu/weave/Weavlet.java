/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;


import java.util.Collection;

import javax.servlet.ServletContext;

import de.fu.junction.annotation.meta.Author;
import de.fu.junction.xml.XmlHelper;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.reflect.ModuleReflector;

/**
 * A Marker-Interface that marks Servlets as a Controller in terms of the MVC-Pattern
 * <p>
 * Typically the controller will care about Resources, Modules, Permissions, and Templating, while
 * the actual business logic is to be found in the sub-package "model".
 */
@Author("Julian Fleischer")
public interface Weavlet<R extends RelationManager> extends Controller<R> {

    /**
     * Retrieves the servlet context of this weavlet.
     * <p>
     * A weavlet gets to know it’s servlet context at initialization time. However, it stores a
     * reference to it’s context so that it is accessible later on.
     * 
     * @return The ServletContext of this Weavlet.
     */
    ServletContext getContext();

    /**
     * Get all loaded Modules.
     * <p>
     * See {@link de.fu.weave.reflect.ModuleReflector}
     * 
     * (Retrieve a collection of Modules currently loaded in this Controller)
     * 
     * @return A Collection of Module-Reflectors.
     */
    Collection<ModuleReflector<? extends Module<R>>> getLoadedModules();

    /**
     * Retrieve the {@see XmlHelper} used by this Weavlet.
     * 
     * @return An {@see XmlHelper}-Instance.
     */
    XmlHelper getXmlHelper();

    /**
     * Check if a given Module is loaded
     * 
     * @param module
     *            The Name of the Module (not its qualified Name)
     * @return true if loaded, or false otherwise.
     */
    boolean isModuleLoaded(String $name);

    /**
     * Loads a module as specified by its class instance.
     * 
     * @param $module
     *            The instance of the {@see java.lang.Class} of the module.
     * @return The name under which the module was loaded.
     */
    String loadModule(Class<? extends Module<R>> $module) throws ModuleException;

    /**
     * Loads an output converter into this Weavlet.
     * 
     * @param $converter
     *            The Class instance of the Converter which is to be loaded.
     * @param $name
     *            The name under which the converter shall be known.
     */
    boolean loadOutputConverter(Class<? extends OutputConverter> $converter, String $name);

}
