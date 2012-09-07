/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import java.util.TreeMap;

import de.fu.junction.Base64;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCaseFormReuse extends FriggModule<RelationManager> {

	public static class Something extends AbstractForm {

		private static final long serialVersionUID = -1634373881101108946L;

		@Required
		@Min(2)
		@Field
		@Pos(1)
		public String title;

		@Required
		@Min(10)
		@FormControl(Control.TEXTAREA)
		@Field
		@Pos(2)
		public String content;

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	public ShowCaseFormReuse(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formReuse.xsl")
	public void _default(final Something $simpleForm) {
		String $default = Base64.encode(new TreeMap<String,String>());
		String $pagesString = session().getValue("pages", $default);
		@SuppressWarnings("unchecked")
		TreeMap<String,String> $pages = Base64.decode($pagesString, TreeMap.class);
		if ($simpleForm.isSuccessfullyCommitted()) {
			$pages.put($simpleForm.title, $simpleForm.content);
			session().setValue("pages", Base64.encode($pages));

			$simpleForm.reset();
		}
		put("reuse", $simpleForm);
		put("pages", $pages);
	}
}
