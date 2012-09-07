/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.junction.functional.F.*;
import static de.fu.weave.orm.filters.Filters.*;

import java.sql.Timestamp;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.orm.filters.Filters;

@Author("Julian Fleischer")
public class DeleteUsers extends AbstractForm {
	private static final long serialVersionUID = -708132292494443346L;

	@Field
	@Multiple
	public Integer[] users;

	@Override
	public boolean commit() throws Exception {
		if ((users == null) || (users.length == 0)) {
			return false;
		}
		// FIXME: Permissions
		manager().beginTransaction();
		for (Person $person : manager().getPerson(any(map(Filters.eq(Person.Id), users)))) {
			try {
				$person
						.setDeleted(new Timestamp(System.currentTimeMillis()))
						.setLoginName("~" + $person.getLoginName() + ":" + $person.id())
						.setIsSuperuser(false)
						.setEmailAddress(null)
						.setLoginPassword("~")
						.pushChanges();
			} catch (Exception $exc) {
				addMessage("", "", $exc);
				manager().abortTransaction();
				return false;
			}
		}
		manager().commitTransaction();
		return true;
	}
}
