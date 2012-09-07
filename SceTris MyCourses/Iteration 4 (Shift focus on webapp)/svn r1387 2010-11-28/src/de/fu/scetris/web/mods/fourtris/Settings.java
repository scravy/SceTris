/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.fourtris;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Fourtris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module to display a start-page.
 * 
 * @author Julian Fleischer
 */
@ModuleInfo(name = "settings",
			author = "Julian Fleischer",
			description = "Sitemap of Scetris.")
public class Settings extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Settings(final Fourtris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "fuberlin/settings.xsl")
	public void _default(final String[] target) {

	}
}
