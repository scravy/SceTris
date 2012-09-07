/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;

import static de.fu.weave.orm.filters.Filters.*;

import java.util.LinkedList;
import java.util.List;

import org.junit.Test;

import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.impl.frigg.FriggModule.Validator;
import de.fu.weave.reflect.FormReflector;
import de.fu.weave.reflect.ValidationException;

public class NewPrivileges extends AbstractForm {

	/**
	 * 
	 */
	private static final long serialVersionUID = -708131192421344446L;

	public Validator<String> name$validator = new Validator<String>() {
		@Override
		public boolean check(final String $name) throws Exception {
			if (manager().getPrivilege(all(eq(Privilege.Name, "name"))).size() > 0) {
				throw new ValidationException("mustBeUnique");
			}
			return true;
		}
	};

	@Field
	@ConverterClass(ArrayConverter.class)
	@Required
	@FormControl(Control.TEXTAREA)
	public String[] privileges;

	public List<Exception> $errors = new LinkedList<Exception>();

	@Override
	public boolean commit() throws Exception {
		for (String $p : privileges) {
			try {
				manager().createPrivilege($p);
			} catch (Exception $exc) {
				$errors.add($exc);
			}
		}
		return true;
	}

	@Test
	public void testForm() throws Exception {
		FormReflector<NewPrivileges> $r = new FormReflector<NewPrivileges>(NewPrivileges.class);
		System.out.println($r.$formFields.get("privileges").$converter.convert("hallo"));
	}
}
