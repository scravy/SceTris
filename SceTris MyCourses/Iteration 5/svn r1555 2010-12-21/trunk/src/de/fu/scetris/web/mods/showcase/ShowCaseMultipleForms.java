/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import static de.fu.weave.Form.Control.*;
import de.fu.junction.data.EMailAddress;
import de.fu.junction.data.Password;
import de.fu.junction.data.Range;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCaseMultipleForms extends FriggModule<RelationManager> {

	public static class SimpleForm extends AbstractForm {

		private static final long serialVersionUID = -1634373881101108946L;

		@Field
		@Min(1)
		@Pos(1.1)
		public String firstName;

		@Field
		@Min(1)
		@Pos(1.2)
		public String lastName;

		@Field("mail")
		@Repeat
		@Pos(1.3)
		public EMailAddress eMail;

		@Field
		@Min(3)
		@Max(8)
		@Pos(2.1)
		@Only("abcdefghijklmnopqrstuvwxyz")
		public String loginName;

		@Field("pw")
		@Repeat
		@Pos(2.2)
		public Password password;

		@Field("numeric")
		@Pos(3.1)
		public Integer aNumber;

		@Field("slider1")
		@Pos(3.2)
		@Min(0)
		@Max(1000)
		@Step(50)
		public Integer anotherNumber;

		@Field("slider2")
		@Pos(3.3)
		@Min(0)
		@Max(100)
		@Step(1)
		public Range aRange;

		@Field("description")
		@Pos(4.0)
		@Min(15)
		@Max(65000)
		@FormControl(TEXTAREA)
		public String ratherLongText;

		@Override
		public void commit() throws Exception {

		}
	}

	public ShowCaseMultipleForms(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.multipleForms.xsl")
	public void _default(final SimpleForm $simpleForm) {
		put("szimpla", $simpleForm);
	}

}
