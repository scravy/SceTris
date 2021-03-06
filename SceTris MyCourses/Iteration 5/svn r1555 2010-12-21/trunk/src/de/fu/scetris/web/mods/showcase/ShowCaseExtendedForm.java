/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import static de.fu.weave.Form.Control.*;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
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
		public void commit() throws Exception {

		}
	}

	public static class DerivedForm extends BaseForm {

		private static final long serialVersionUID = -4007143268560772758L;

		@Field
		@Pos(1.15)
		public String subtitle;

		@Override
		public void commit() throws Exception {

		}
	}

	public ShowCaseExtendedForm(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.extendedForm.xsl")
	public void _default(final DerivedForm $simpleForm) {
		put("szimpla", $simpleForm);
	}

}
