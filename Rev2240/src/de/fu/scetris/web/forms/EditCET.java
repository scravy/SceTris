/* EditCET.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.CourseElementType;

public class EditCET extends CourseElementType.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -728132292494443346L;

	@Override
	public boolean commit() throws Exception {
		manager().getCourseElementType(id)
				.setName(name)
				.pushChanges();
		return true;
	}
}
