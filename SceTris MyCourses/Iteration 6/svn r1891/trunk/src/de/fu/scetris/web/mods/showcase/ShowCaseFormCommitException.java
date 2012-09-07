/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * A demo-module.
 */
@Author("Julian Fleischer")
public class ShowCaseFormCommitException extends FriggModule<RelationManager> {

	public static class EnterEMail extends ShowCaseFormValidation.EnterEMail {

		@SuppressWarnings("serial")
		static class ThisWillHappenAllTheTimeException extends Exception {
			public ThisWillHappenAllTheTimeException(final String $message, final Exception $cause) {
				super($message, $cause);
			}
		}

		private static final long serialVersionUID = -4768552268624177772L;

		@Override
		public boolean commit() throws Exception {
			throw new ThisWillHappenAllTheTimeException(
					"In this demo, this Exception is always thrown.",
					new DatabaseException(
							new SQLException(
									new PSQLException("Duplicate value.", null))));
		}
	}

	public ShowCaseFormCommitException(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formValidation.xsl")
	public void _default(final EnterEMail $simpleForm) {
		put("newsletter", $simpleForm);
	}

}
