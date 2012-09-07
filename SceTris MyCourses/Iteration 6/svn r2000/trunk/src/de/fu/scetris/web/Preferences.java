/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module which offers tools for setting personal preferences, such as changing ones password or
 * email-address
 */
@Author("Julian Fleischer")
public class Preferences extends FriggModule<RelationManager> {

	public Preferences(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "preferences/preferences.xsl")
	public void _default() {

	}
}
