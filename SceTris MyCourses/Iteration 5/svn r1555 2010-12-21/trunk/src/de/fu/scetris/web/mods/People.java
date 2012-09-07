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
 * A listing of people involved at this institution.
 */
@Author("Julian Fleischer")
public class People extends FriggModule<RelationManager> {

	public People(final Scetris parent) {
		super(parent);
	}

	@Action(template = "people/people.xsl")
	public void _default() {

	}
}
