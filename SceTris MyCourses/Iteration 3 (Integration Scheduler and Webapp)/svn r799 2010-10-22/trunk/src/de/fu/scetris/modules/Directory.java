/* UniversityCalender.java / 7:12:32 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import de.fu.scetris.Scetris;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class Directory extends GenericModule<RelationManager> {
	Directory(final Scetris parent) {
		super(parent);
	}

	@Action(template = "directory.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "enroll", template = "directory.enroll.xsl")
	public void enroll(final String[] path) {

	}
}
