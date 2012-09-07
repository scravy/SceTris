/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.scetris.data.Year;

public class EditYear extends NewYear {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		// the following loads additional info which was available when XMLing the form, too
		init();

		// this sets the new values
		Year $currentYear = manager().getYear(id)
				.setName(name);

		// this applies the changes
		$currentYear.pushChanges();

		// everything’s ok
		return true;
	}

	@Override
	public void init() {
		super.init();
	}

	@Override
	public EditYear setValues(final Year $p) {
		super.setValues($p);
		return this;
	}
}
