/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import static de.fu.junction.Generics.*;
import static de.fu.junction.Tuple.*;

import java.sql.Date;
import java.util.Map;

import de.fu.junction.data.EMailAddress;
import de.fu.junction.data.Password;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.Form;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCaseFormControls extends FriggModule<RelationManager> {

	public static class SimpleForm extends AbstractForm {

		/**
		 * 
		 */
		private static final long serialVersionUID = 8721190338812192054L;

		@Field
		public Integer number;

		@Field
		@Min(0)
		@Max(100)
		public int integer;

		@Field
		public String string;

		@Field
		public String stringDef = "default value";

		@Field
		public Date date;

		@Field
		public boolean checkbox;

		@Field
		public EMailAddress eMailAddress;

		@Field
		public Password password;

		@Field
		@FormControl(Form.Control.TEXTAREA)
		public String textarea;

		@Field
		@Alternatives("someStringOptions")
		public String someString;

		public String[] someStringOptions = { "one", "two", "three", "four" };

		@Field
		@Alternatives("someOptions")
		public String anotherString = "theAnswerIsWhatIsTheQuestion";

		@SuppressWarnings("unchecked")
		public Map<String,Integer> someOptions = mapping(
			t("scriptKiddiesAreAllLeet", 1337),
			t("theAnswerIsWhatIsTheQuestion", 42),
			t("illuminati", 23),
			t("äschtKöllnischWassa", 4711));

		@Field
		@Alternatives("someNumberOptions")
		public Integer someNumber = 13;

		public Integer[] someNumberOptions = { 2, 3, 5, 7, 11, 13, 17, 19 };

		@Override
		public boolean commit() throws Exception {
			return false;
		}
	}

	public ShowCaseFormControls(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formControls.xsl")
	public void _default(final SimpleForm $simpleForm) {
		$simpleForm.someStringOptions = new String[] { "Takka Tukka Land", "Lummerland", "Nether",
				"The Void", "Magnetberg" };

		put("szimpla", $simpleForm);
	}
}
