/* NewCourseAttribute.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import de.fu.scetris.data.CourseAttribute;

public class NewCourseAttribute extends CourseAttribute.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	public CourseAttribute $createdCA;

	
	@Override
	public boolean commit() throws Exception {
		$createdCA = manager().newCourseAttribute(name)
			.setUniqueValue(uniqueValue)
			.setType(type)
			.setRequired(required)
			.create();
		System.out.println($createdCA);
		return true;

		
//		$createdDepartment = manager().newDepartment(name)
//		.setSuperordinateDepartment(manager().getDepartment(superordinateDepartment))
//		.create();
//System.out.println(superordinateDepartment);
	}
}
