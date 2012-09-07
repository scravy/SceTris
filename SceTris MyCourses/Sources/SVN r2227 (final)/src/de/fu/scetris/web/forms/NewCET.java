/* NewCET.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.CourseElementType;

public class NewCET extends CourseElementType.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708138292494443346L;

	@Override
	public boolean commit() throws Exception {
		CourseElementType $new = manager().newCourseElementType(name);
		
		$new
				.create();
		return true;
	}
}
