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
import de.fu.weave.orm.DatabaseException;

/**
 * 
 */
@Author("Julian Fleischer, Andre Zoufahl")
public class Lectures extends FriggModule<RelationManager> {

	public Lectures(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "lectures/lectures.xsl")
	public void _default(final String[] target) throws DatabaseException {
		put("courses", manager().getCourseInstance());
	}
}
