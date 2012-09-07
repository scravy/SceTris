/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;


import de.fu.scetris.data.AcademicTerm;

public class EditAcademicTerm extends NewAcademicTerm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		// the following loads additional info which was available when XMLing the form, too
		init();
		
		// this sets the new values
		AcademicTerm $currentYear = manager().getAcademicTerm(id);
		if(!validAcaTermDates(start, end, startLessons, endLessons, $currentYear))
			return false;
		
		$currentYear
				.setName(name)
				.setStart(start)
				.setEnd(end)
				.setStartLessons(startLessons)
				.setEndLessons(endLessons);
		
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
	public EditAcademicTerm setValues(final AcademicTerm $p) {
		super.setValues($p);
		return this;
	}
}
