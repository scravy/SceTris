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
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Role;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A listing of people involved at this institution.
 */
@Author({ "Julian Fleischer", "Andre Zoufahl" })
public class People extends FriggModule<RelationManager> {

	public People(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "people/people.xsl")
	public void _default() throws DatabaseException {
		put("lecturer",
				manager().getPerson(
					all(isNull(Person.Deleted), sort(Person.LastName), eq(Person.IsLecturer, true))));
	}

	@Action(template = "people/people.lecturer.xsl")
	public void showLecturer(final String[] $path) throws DatabaseException {
		if ($path.length > 0) {
			List<Person> $persons = manager().getPerson(
				any(eq(Person.Id, Parse.parseInt($path[0], -1)), eq(Person.LoginName, $path[0])));
			if ($persons.size() > 0) {
				Person $p = $persons.get(0);
				/* Person related */
				List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
				List<Role> $roles = $p.objectsOfPersonHasRole();
				List<CourseInstance> $enrolledCourseInstances = $p
						.objectsOfPersonEnrolledInCourseInstance();
				put("person", $p);
				put("privileges", $privileges);
				put("roles", $roles);
				put("enrolledCourseInstances", $enrolledCourseInstances);				
				
				
				/* Lecturer related */
				List<CourseElementInstance> $ceis = manager().getCourseElementInstance(any(eq("lecturer", $p.getId())));
				List<ElementInstanceTakesPlaceInRoom> $room = new ArrayList<ElementInstanceTakesPlaceInRoom>();
				for(CourseElementInstance $cei : $ceis) {
					$cei.getCourseElement().getType();
					
					$room.addAll($cei.whereSubjectOfElementInstanceTakesPlaceInRoom());					
				}
				
				for(ElementInstanceTakesPlaceInRoom $tmp: $room) {
					$tmp.getRoom().getBuilding();
				}
				put("ceis", $ceis);
				put("tpir", $room);				
						

				/* TIMETABLEGRID-RELATED */
				put("days", manager().getDay());
				put("timeslots", manager().getTimeslot());
				
				return;
			}
		}
		put("no-such-user");
	}
}
