/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import static de.fu.weave.orm.filters.Filters.*;

import java.util.ArrayList;
import java.util.List;

import de.fu.junction.Parse;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A Module for Course Management.
 */
@Author({ "Hagen Mahnke" })
public class Courses extends FriggModule<RelationManager> {

	public Courses(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "courses/courses.xsl")
	public void _default() throws DatabaseException {

	}

	/* Will only work when program is published ! */
	@Action(template = "courses/courses.showCourseInstance.xsl")
	public void showCourseInstance(final String[] $path) throws DatabaseException {
		if ($path.length > 0) {
			List<CourseInstance> $cis = manager().getCourseInstance(
					any(eq(CourseInstance.Id, Parse.parseInt($path[0], -1))));
			if ($cis.size() > 0) {
				CourseInstance $ci = $cis.get(0);

				$ci.getInstanceOf();
				$ci.getMainLecturer();
				$ci.getProgram().getAcademicTerm();

				List<CourseElementInstance> $ceis = manager().getCourseElementInstance(
					any(eq("course_instance", $ci.getId())));
				List<ElementInstanceTakesPlaceInRoom> $roomceis = new ArrayList<ElementInstanceTakesPlaceInRoom>();
				for (CourseElementInstance $cei_i : $ceis) {
					$cei_i.getLecturer();
					$cei_i.getCourseElement().getType();
					$cei_i.getStartingTimeslot().getDay();
					$roomceis.addAll($cei_i.whereSubjectOfElementInstanceTakesPlaceInRoom());
				}

				for (ElementInstanceTakesPlaceInRoom $tmp : $roomceis)
					$tmp.getRoom().getBuilding();

				put("ci", $ci);
				put("ci_parts", $ceis);
				put("roomceis", $roomceis);

				// CourseInstance tmp = manager().getCourseInstance(courseinstanceid);
				// put("courseinstanceinfo", tmp);
				// Collection<CourseElementInstance> ceis = manager()
				// .getCourseElementInstance(eq("course_instance", tmp.getId()));
				// put("cei", ceis);
				// Collection<ElementInstanceTakesPlaceInRoom> roomceis = new
				// ArrayList<ElementInstanceTakesPlaceInRoom>();
				// for (CourseElementInstance cei : ceis) {
				// roomceis.addAll(cei.whereSubjectOfElementInstanceTakesPlaceInRoom());
				// }
				// put("roomceis", roomceis);

				return;
			}
		} else
			setRedirect("courses/listCourseInstances/");
	}
}
