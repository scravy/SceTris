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
@ModuleInfo(name = "my",
			author = "Julian Fleischer",
			description = "Sitemap of Scetris.")
public class My extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public My(final Fourtris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "fuberlin/my.xsl")
	public void _default(final String[] target) {

	}

	@Action(name = "courses", template = "fuberlin/my.courses.xsl")
	public void myCourses(final String[] target) {

	}

	@Action(name = "grades", template = "fuberlin/my.grades.xsl")
	public void myGrades(final String[] target) {

	}

	@Action(name = "schedule", template = "fuberlin/my.schedule.xsl")
	public void mySchedule(final String[] target) {

	}
}
