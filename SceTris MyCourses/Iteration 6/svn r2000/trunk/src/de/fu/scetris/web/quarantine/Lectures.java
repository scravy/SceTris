/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.quarantine;

import static de.fu.weave.orm.filters.Filters.*;

import java.util.ArrayList;
import java.util.Collection;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.ElementInstanceTakesPlaceInRoom;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 */
@Author("Julian Fleischer")
public class Lectures extends FriggModule<RelationManager> {

	/**
	 * 
	 * @see FriggModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Lectures(final ScetrisServlet parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 * @throws DatabaseException
	 */
	@Action(template = "fuberlin/lectures.xsl")
	public void _default(final String[] target) {

	}

	@Commit(action = "my-recommendations", after = "")
	public void enrollinCI(final String[] target,
						   @Arg(name = "enrollin", intDefault = 0) final int courseinstanceid)
			throws DatabaseException {
		if ((courseinstanceid > 0) && (session.getUser().id() > 0)) {
			Person tmp = manager().getPerson(session.getUser().id());
			System.out.println(tmp.getId() + " bucht " + courseinstanceid);
			tmp.enroll(manager().getCourseInstance(courseinstanceid));
		}
	}

	@Action(name = "courseinstanceinfo", template = "fuberlin/lectures.courseinstance.info.xsl")
	public void showCourseInstanceInfo(final String[] target,
									   @Arg(name = "data", intDefault = 0) final int courseinstanceid)
			throws DatabaseException {
		CourseInstance tmp = manager().getCourseInstance(courseinstanceid);
		put("courseinstanceinfo", tmp);
		Collection<CourseElementInstance> ceis = manager()
				.getCourseElementInstance(eq("course_instance", tmp.getId()));
		put("cei", ceis);
		Collection<ElementInstanceTakesPlaceInRoom> roomceis = new ArrayList<ElementInstanceTakesPlaceInRoom>();
		for (CourseElementInstance cei : ceis) {
			roomceis.addAll(cei.whereSubjectOfElementInstanceTakesPlaceInRoom());
		}
		put("roomceis", roomceis);
	}

	@Action(name = "my-recommendations", template = "fuberlin/lectures.my-recommendations.xsl")
	public void showMyRecommendations(final String[] target) throws DatabaseException {
		put("courseinstances", manager().getCourseInstance());
	}

	@Action(name = "my-institutes", template = "fuberlin/lectures.my-institutes.xsl")
	public void showRecommendedLectures(final String[] target) {

	}
}
