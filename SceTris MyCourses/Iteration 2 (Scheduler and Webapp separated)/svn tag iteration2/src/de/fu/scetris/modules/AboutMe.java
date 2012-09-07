/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@ModuleInfo(name = "aboutme",
			author = "Julian Fleischer",
			description = "Displays Information about the User currently logged in",
			requires = { Requirement.DATABASE, Requirement.LOGGED_IN })
public class AboutMe extends GenericModule<RelationManager> {

	public AboutMe(final Scetris parent) {
		super(parent);
	}

	@Action(template = "about.me.xsl")
	public void _default(final String[] path) {
		// get my courses etc and pass them to the Template-Enigne
	}

}
