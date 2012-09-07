/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.showcase;

import static de.fu.weave.Form.Control.*;
import de.fu.junction.annotation.meta.Author;
import de.fu.junction.data.EMailAddress;
import de.fu.junction.data.Password;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module that outlines how a Form is built.
 * <p>
 * The java-code is rather uninteresting, have a look at the accomanying XSL-file.
 */
@Author("Julian Fleischer")
public class ShowCaseFormBuilder extends FriggModule<RelationManager> {

	public static class SimpleForm extends AbstractForm {

		private static final long serialVersionUID = -2519075915821314568L;

		@Field
		@Min(1)
		@Pos(1.1)
		@Required
		public String firstName;

		@Field
		@Min(1)
		@Pos(1.15)
		public String middleName;

		@Field
		@Min(1)
		@Pos(1.2)
		@Required
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
		@Required
		public String loginName;

		@Field("pw")
		@Repeat
		@Pos(2.2)
		@Required
		public Password password;

		@Field("description")
		@Pos(4.0)
		@Min(15)
		@Max(65000)
		@FormControl(TEXTAREA)
		public String ratherLongText;

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	public ShowCaseFormBuilder(final ScetrisServlet $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formBuilder.xsl")
	public void _default(final SimpleForm $simpleForm) {
		put("builder", $simpleForm);
	}

}
