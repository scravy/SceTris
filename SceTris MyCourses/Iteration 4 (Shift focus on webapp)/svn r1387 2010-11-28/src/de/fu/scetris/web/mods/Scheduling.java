/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module to display a start-page.
 * 
 * @author Andre Zoufahl
 */
@ModuleInfo(name = "scheduling",
			author = "Andre Zoufahl",
			description = "Provides more menus to do CRUD on entities that are categorized as a ressource.",
			menu = "scheduling")
public class Scheduling extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Scheduling(final Scetris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "ressources.xsl")
	public void _default(final String[] target) {
		put("modules", getReflector());
	}

	/**
	 * A sitemap action that generates a listing of available modules and actions (this is
	 * /start/sitemap/)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(name = "room", template = "default-entities.xsl")
	public void createRooms(final String[] target) {
		put("modules", getReflector());
	}
}
