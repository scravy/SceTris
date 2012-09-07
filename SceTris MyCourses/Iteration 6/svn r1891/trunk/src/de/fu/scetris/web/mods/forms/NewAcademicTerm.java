/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;

import static de.fu.bakery.orm.java.filters.Filters.*;

import java.sql.Date;
import java.util.Collection;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.filters.UnEq;
import de.fu.scetris.data.AcademicTerm;
import de.fu.weave.reflect.ValidationException;

public class NewAcademicTerm extends AcademicTerm.Form {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708132292494443346L;

	@Override
	public boolean commit() throws Exception {
		if (!validAcaTermDates(start, end, startLessons, endLessons, null)) {
			return false;
		}
		AcademicTerm $new = manager().createAcademicTerm(name, start, end, startLessons, endLessons);
		return true;
	}

	protected boolean validAcaTermDates(final Date start, final Date end,
			  final Date startlesson, final Date endlesson,
			  final AcademicTerm exclude) throws DatabaseException, ValidationException {

		UnEq filter = null;
		if (exclude != null) {
			filter = not(eq("id", exclude.id()));
		}
		Collection<AcademicTerm> rest = manager().getAcademicTerm(filter);
		for (AcademicTerm x : rest) {
			if (!start.after(x.getEnd()) || end.before(x.getStart())) {
				addMessage("name", "mist", new ValidationException("overlapping with at " + x.getName()));
			}
		}

		if (startlesson.before(start) || startlesson.after(end)) {
			addMessage("startLessons", startLessons.toString(), new ValidationException(
					"start of lessons not within term"));
		}

		if (endlesson.after(end) || endlesson.before(start)) {
			addMessage("endLessons", endLessons.toString(), new ValidationException(
					"end of lessons not within term"));
		}

		if (end.before(start)) {
			addMessage("end", end.toString(), new ValidationException("enddate before startdate"));
		}

		if (hasMessages()) {
			throw new ValidationException("error in dataset");
		}

		return true;
	}
}
