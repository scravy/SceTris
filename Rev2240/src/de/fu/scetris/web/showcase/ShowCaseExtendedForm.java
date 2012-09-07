/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.showcase;

import static de.fu.weave.Form.Control.*;
import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module which shows how to build a Form that extends another one.
 */
@Author("Julian Fleischer")
public class ShowCaseExtendedForm extends FriggModule<RelationManager> {

	public static class BaseForm extends AbstractForm {

		private static final long serialVersionUID = 3373286063001137723L;

		@Field
		@Min(1)
		@Pos(1.1)
		public String title;

		@Field
		@Min(1)
		@Pos(1.2)
		@FormControl(TEXTAREA)
		public String description;

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	public static class DerivedForm extends BaseForm {

		private static final long serialVersionUID = -4007143268560772758L;

		@Field
		@Pos(1.15)
		public String subtitle;

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	public ShowCaseExtendedForm(final ScetrisServlet $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.extendedForm.xsl")
	public void _default(final DerivedForm $simpleForm) {
		put("szimpla", $simpleForm);
	}

}
