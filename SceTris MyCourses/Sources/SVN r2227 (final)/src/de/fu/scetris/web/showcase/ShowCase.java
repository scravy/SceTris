/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.showcase;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCase extends FriggModule<RelationManager> {

	public ShowCase(final ScetrisServlet $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.xsl")
	public void _default() {

	}

}
