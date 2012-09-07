/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.showcase;

import de.fu.junction.annotation.meta.Author;
import de.fu.junction.data.EMailAddress;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.reflect.ValidationException;

/**
 * A demo-module, which illustrates Form Validation
 */
@Author("Julian Fleischer")
public class ShowCaseFormValidation extends FriggModule<RelationManager> {

	/**
	 * A Form which provides an email-address field
	 */
	public static class EnterEMail extends AbstractForm {

		private static final long serialVersionUID = -4768553068624170778L;

		/**
		 * The email address field
		 * <p>
		 * Note, that since it is {@see Required} the form will not be committed without validating
		 * first. The name ("mail") is optional, one could also just have stated {@code @Field}.
		 * <p>
		 * Via {@see Validator} a Validator is referenced, which is created for validating that
		 * specific field. The Validator referenced here is {@see HostnameValidator} which is a
		 * local class.
		 */
		@Field("mail")
		@Required
		@ValidatorClass(HostnameValidator.class)
		public EMailAddress eMail;

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	/**
	 * This is the Validator for the email address field above.
	 * <p>
	 * It checks for the hostname to be “fu-berlin.de”
	 */
	public static class HostnameValidator extends Validator<EMailAddress> {

		@Override
		public boolean check(final EMailAddress $value) throws ValidationException {
			if (!$value.getHostname().equalsIgnoreCase("fu-berlin.de")) {
				throw new ValidationException("invalidSuffix");
			}
			return true;
		}

	}

	public ShowCaseFormValidation(final ScetrisServlet $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formValidation.xsl")
	public void _default(final EnterEMail $simpleForm) {
		put("newsletter", $simpleForm);
	}

}
