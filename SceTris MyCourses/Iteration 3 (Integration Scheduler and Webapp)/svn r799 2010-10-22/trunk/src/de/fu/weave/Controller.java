/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.util.Collection;

import de.fu.weave.orm.RelationManager;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.XmlHelper;

/**
 * A Marker-Interface that marks Servlets as a Controller in terms of the MVC-Pattern
 * <p>
 * Typically the controller will care about Resources, Modules, Permissions, and Templating, while
 * the actual business logic is to be found in the sub-package "model".
 * 
 * @author Julian Fleischer
 */
public interface Controller<R extends RelationManager> {

	/**
	 * Get all loaded Modules.
	 * <p>
	 * See {@link de.fu.weave.reflect.ModuleReflector}
	 * 
	 * (Retrieve a collection of Modules currently loaded in this Controller)
	 * 
	 * @return A Collection of Module-Reflectors.
	 * @since Iteration2
	 */
	public Collection<ModuleReflector<? extends Module<R>>> getLoadedModules();

	/**
	 * Retrieve the XMLHelper used by this Controller
	 * 
	 * @return An XMLHelper-Instance
	 */
	XmlHelper getXmlHelper();
}
