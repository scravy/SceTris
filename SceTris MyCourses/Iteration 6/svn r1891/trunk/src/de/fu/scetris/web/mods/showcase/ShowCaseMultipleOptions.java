/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import java.util.Map;

import de.fu.junction.Generics;
import de.fu.junction.Tuple;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCaseMultipleOptions extends FriggModule<RelationManager> {

	public static class SimpleForm extends AbstractForm {

		private static final long serialVersionUID = -1634373371101108946L;

		@Required
		@Field
		public String required;

		@Field
		@Multiple
		@Alternatives("$options")
		public int[] options = { 3, 7 };

		@SuppressWarnings("unchecked")
		public Map<Integer,String> $options = Generics.mapping(
			Tuple.t(1, "Eins"),
			Tuple.t(2, "Zwei"),
			Tuple.t(3, "Drei"),
			Tuple.t(4, "Vier"),
			Tuple.t(5, "Fünf"),
			Tuple.t(6, "Sechs"),
			Tuple.t(7, "Sieben"),
			Tuple.t(8, "Acht"),
			Tuple.t(9, "Neun"),
			Tuple.t(0, "Zehn"));

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	public ShowCaseMultipleOptions(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.multipleOptions.xsl")
	public void _default(final SimpleForm $simpleForm) {
		put("szimpla", $simpleForm);
	}

}
