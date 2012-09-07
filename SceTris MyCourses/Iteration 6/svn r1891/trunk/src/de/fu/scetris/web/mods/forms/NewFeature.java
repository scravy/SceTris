/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import de.fu.scetris.data.Person;

public class NewFeature extends Person.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		return true;
	}
}
