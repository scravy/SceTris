/* Enrollment.aj / 1:31:30 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import java.sql.PreparedStatement;
import java.sql.SQLException;

import de.fu.scetris.data.CourseInstance;
import de.fu.scetris.data.Person;

/**
 *
 * @author Julian Fleischer
 * @since Iteration3
 */
public privileged aspect Enrollment {

	public boolean CourseInstance.enroll(Person person) {
		String query = "SELECT enroll(?, ?);";
		try {
    		PreparedStatement stmt = this.manager.connectionManager.getConnection().prepareStatement(query);
    		stmt.setString(1, person.getLoginName());
    		stmt.setInt(2, this.id());
    		stmt.execute();
    		return true;
		} catch (SQLException e) {
			return false;
		}
	}
	
	public boolean Person.enroll(CourseInstance course) {
		return course.enroll(this);
	}
	
}
