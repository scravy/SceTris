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
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 */
@Author("Julian Fleischer")
public class Index extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Index(final Scetris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "index/index.xsl")
	public void _default() {
		put("modules", getParent().getLoadedModules());
	}
}
