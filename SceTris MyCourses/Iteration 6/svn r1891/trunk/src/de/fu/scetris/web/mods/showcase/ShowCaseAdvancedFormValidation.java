/* ShowCase.java / 7:10:19 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.showcase;

import de.fu.junction.data.EMailAddress;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.reflect.ValidationException;

/**
 * A demo-module, which illustrates Form Validation
 */
@Author("Julian Fleischer")
public class ShowCaseAdvancedFormValidation extends FriggModule<RelationManager> {

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
		@ActiveValidator("addressValidator")
		public EMailAddress eMail;

		public HostnameValidator addressValidator = new HostnameValidator("fu-berlin.de");

		@Override
		public boolean commit() throws Exception {
			return true;
		}
	}

	/**
	 * This is the Validator for the email address field above.
	 */
	public static class HostnameValidator implements de.fu.weave.Validator<EMailAddress> {

		private final String $hostname;

		public HostnameValidator(final String $host) {
			$hostname = $host;
		}

		@Override
		public boolean check(final EMailAddress $value) throws ValidationException {
			if (!$value.getHostname().equalsIgnoreCase($hostname)) {
				throw new ValidationException("invalidSuffix");
			}
			return true;
		}

	}

	public ShowCaseAdvancedFormValidation(final Scetris $parent) {
		super($parent);
	}

	@Action(template = "showcase/showcase.formValidation.xsl")
	public void _default(final EnterEMail $simpleForm) {
		put("newsletter", $simpleForm);
	}

}
