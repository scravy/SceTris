/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * A module to display a start-page.
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "start",
			author = "Julian Fleischer",
			description = "Startpage of Scetris.",
			requires = Requirement.DATABASE)
public class Start extends GenericModule<RelationManager> {

	/**
	 * 
	 * @see GenericModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Start(final Scetris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "presentation.start.xsl")
	public void _default(final String[] target) {

	}

	/**
	 * A sitemap action that generates a listing of available modules and actions (this is
	 * /start/sitemap/)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(name = "sitemap", template = "presentation.sitemap.xsl")
	public void showSitemap(final String[] target) {
		put("modules", getParent().getLoadedModules());
	}
}
