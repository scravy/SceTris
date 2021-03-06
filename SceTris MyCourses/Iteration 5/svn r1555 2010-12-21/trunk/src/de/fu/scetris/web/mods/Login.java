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
public class Login extends FriggModule<RelationManager> {

	public Login(final Scetris parent) {
		super(parent);
	}

	@Action(template = "login.xsl")
	public void _default(final String[] target) {

	}
}
