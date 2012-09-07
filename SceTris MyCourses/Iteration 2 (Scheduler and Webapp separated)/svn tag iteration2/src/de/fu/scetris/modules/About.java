/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "about",
			author = "Julian Fleischer",
			description = "Displays information about loaded modules.")
public class About extends GenericModule<RelationManager> {

	/**
	 * 
	 * @param parent
	 *            The Controller that instantiated this Module
	 */
	public About(final Scetris parent) {
		super(parent);
	}

	/**
	 * 
	 * @param params
	 */
	@Action(template = "about.xsl")
	public void _default(final String[] params) {
		put("modules", parent.getLoadedModules());
	}

	/**
	 * 
	 * @param params
	 */
	@Action(name = "license", template = "about.license.xsl")
	public void aboutLicense(final String[] params) {

	}

	/**
	 * 
	 * @param params
	 */
	@Action(name = "raw", template = "debug.xsl")
	public void aboutScetrisRaw(final String[] params) {
		_default(params);
	}
}
