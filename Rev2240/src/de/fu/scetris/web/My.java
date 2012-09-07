/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import static de.fu.weave.orm.filters.Filters.all;
import static de.fu.weave.orm.filters.Filters.eq;

import java.util.ArrayList;
import java.util.List;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.AcademicTerm;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonTakesPartInElementInstance;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A module to display a start-page.
 */
@Author({ "Andre Zoufahl"})
public class My extends FriggModule<RelationManager> {

	public My(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "my/my.xsl")
	public void _default() throws DatabaseException {
		String $term = manager().getConfig("currentTerm", (String)null);
		if($term != null) {
			List<AcademicTerm> $ats = manager().getAcademicTerm(all(eq(AcademicTerm.Name, $term)));
			if($ats.size() > 0) {
				put("currentTerm", $ats.get(0));
			}
		}
	}

	@Action(template = "my/my.courses.xsl")
	public void courses(final String[] target) throws DatabaseException {
		if($session.getUser() instanceof Person) {
			Person $p = (Person) $session.getUser();
			List<PersonTakesPartInElementInstance> $mycourseelements = $p.whereSubjectOfPersonTakesPartInElementInstance();
			for(PersonTakesPartInElementInstance $x : $mycourseelements) {
				$x.getElementInstance().getCourseElement().getType();
				$x.getElementInstance().getCourseInstance().getInstanceOf();
				$x.getElementInstance().getStartingTimeslot().getDay();
			}
	        put("mycourseelements", $mycourseelements);
        }
	}

	/* NOT USED */
	@Action(template = "my/my.grades.xsl")
	public void grades() {

	}

	@Action(template = "my/my.schedule.xsl")
	public void schedule() throws DatabaseException {
		if ($session.getUser().id() > 0) {
			Person $p = (Person) $session.getUser();
			
			/* courserelated */
			List<PersonTakesPartInElementInstance> $ptpiei = $p.whereSubjectOfPersonTakesPartInElementInstance();			
			List<ElementInstanceTakesPlaceInRoom> $eitpir = new ArrayList<ElementInstanceTakesPlaceInRoom>();
			for (PersonTakesPartInElementInstance $x : $ptpiei) {
				CourseElementInstance $cei = $x.getElementInstance();
				$eitpir.addAll($cei.whereSubjectOfElementInstanceTakesPlaceInRoom());
				$cei.getLecturer();
				$cei.getCourseElement().getType();
				$cei.getCourseElement().getPartOf();
			}
			
			for(ElementInstanceTakesPlaceInRoom $x : $eitpir) {
				$x.getRoom().getBuilding().getDepartment();
			}
			put("ptpiei", $ptpiei);
			put("eitpir", $eitpir);
			
			/* timetable related */
			put("timeslots", manager().getTimeslot());
			put("days", manager().getDay());
		}
	}
}
