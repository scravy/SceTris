/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonTakesPartInElementInstance;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A module to display a start-page.
 */
@Author("Julian Fleischer")
public class My extends FriggModule<RelationManager> {

	public My(final Scetris parent) {
		super(parent);
	}

	@Action(template = "my/my.xsl")
	public void _default() {}

	@Action(name = "courses", template = "my/my.courses.xsl")
	public void myCourses(final String[] target) throws DatabaseException {

	}

	@Action(name = "grades", template = "my/my.grades.xsl")
	public void myGrades() {

	}

	@Action(name = "schedule", template = "my/my.schedule.xsl")
	public void mySchedule() throws DatabaseException {
		if (session.getUser().id() > 0) {
			Person tmp = getRelationManager().getPerson(session.getUser().id());
			put("mycourseinstances", tmp.whereSubjectOfPersonTakesPartInElementInstance());
			for (PersonTakesPartInElementInstance x : tmp
					.whereSubjectOfPersonTakesPartInElementInstance()) {
				put("roomsofcei", x.getElementInstance().whereSubjectOfElementInstanceTakesPlaceInRoom());
			}
			put("timeslots", getRelationManager().getTimeslot());
			put("days", getRelationManager().getDay());
		}
	}
}
