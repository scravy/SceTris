/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.CourseElementInstance;

public class NewCourseElementInstance extends CourseElementInstance.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3013251520313018738L;

	@Field
	public int count;

	
	@Override
	public boolean commit() throws Exception {
		session().setValue("cei_count", count);
		return true;
	}
	
    @Override
    public void init() {
    	session().setValue("cei_count", null);
    }
}
