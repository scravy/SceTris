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
 * A module which offers tools for setting personal preferences, such as changing ones password or
 * email-address
 */
@Author("Julian Fleischer")
public class Preferences extends FriggModule<RelationManager> {

	public Preferences(final Scetris parent) {
		super(parent);
	}

	@Action(template = "preferences/preferences.xsl")
	public void _default() {

	}
}
