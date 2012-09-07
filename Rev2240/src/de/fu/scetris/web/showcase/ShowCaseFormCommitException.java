/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.showcase;

import java.sql.SQLException;

import org.postgresql.util.PSQLException;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A demo-module which demonstrates Exception handling on a failed commit.
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

	public ShowCaseFormCommitException(final ScetrisServlet $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formValidation.xsl")
	public void _default(final EnterEMail $simpleForm) {
		put("newsletter", $simpleForm);
	}

}
