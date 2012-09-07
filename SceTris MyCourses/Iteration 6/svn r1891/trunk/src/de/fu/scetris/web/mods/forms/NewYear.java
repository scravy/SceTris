/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import de.fu.scetris.data.Year;

public class NewYear extends Year.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		Year $new = manager().newYear(name);
		$new.create();
		return true;
	}
}
