/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.Course;

public class EditCourse extends Course.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		manager().getCourse(id)
				.setName(name)
				.pushChanges();
		return true;
	}
}
