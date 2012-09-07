/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import static de.fu.bakery.orm.java.filters.Filters.all;
import static de.fu.bakery.orm.java.filters.Filters.any;
import static de.fu.bakery.orm.java.filters.Filters.eq;
import static de.fu.bakery.orm.java.filters.Filters.isNull;
import static de.fu.bakery.orm.java.filters.Filters.sort;

import java.util.List;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.junction.Parse;
import de.fu.scetris.data.Course;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Role;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A listing of people involved at this institution.
 */
@Author("Julian Fleischer, Andre Zoufahl")
public class People extends FriggModule<RelationManager> {

	public People(final Scetris parent) {
		super(parent);
	}

	@Action(template = "people/people.xsl")
	public void _default() throws DatabaseException {
		put("lecturer",
				manager().getPerson(all(isNull(Person.Deleted), sort(Person.LastName))));
	}
	
	@Action(template = "people/people.lecturer.xsl")
	public void showLecturer(final String[] $path) throws DatabaseException {
		if ($path.length > 0) {
			List<Person> $persons = manager().getPerson(
				any(eq(Person.Id, Parse.parseInt($path[0], -1)), eq(Person.LoginName, $path[0])));
			if ($persons.size() > 0) {
				Person $p = $persons.get(0);
				put("person", $p);
				List<Privilege> $privileges = $p.objectsOfPersonHasPrivilege();
				List<Role> $roles = $p.objectsOfPersonHasRole();
				List<Course> $courses = $p.objectsOfPersonGivesCourse();
				List<CourseInstance> $enrolledCourseInstances = $p.objectsOfPersonEnrolledInCourseInstance();
				List<CourseInstance> $courseInstances = $p.objectsOfPersonEnrolledInCourseInstance();
				put("privileges", $privileges);
				put("roles", $roles);
				put("courses", $courses);
				put("enrolledCourseInstances", $enrolledCourseInstances);
				put("courseInstances", $courseInstances);
				return;
			}
		}
		put("no-such-user");
	}
}
