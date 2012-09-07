/* Fourtris.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.web;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.Person;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.mods.Admin;
import de.fu.scetris.web.mods.ImportExport;
import de.fu.scetris.web.mods.Lectures;
import de.fu.scetris.web.mods.Login;
import de.fu.scetris.web.mods.My;
import de.fu.scetris.web.mods.People;
import de.fu.scetris.web.mods.Preferences;
import de.fu.scetris.web.mods.showcase.ShowCase;
import de.fu.scetris.web.mods.showcase.ShowCaseAcgtForms;
import de.fu.scetris.web.mods.showcase.ShowCaseExtendedForm;
import de.fu.scetris.web.mods.showcase.ShowCaseFormBuilder;
import de.fu.scetris.web.mods.showcase.ShowCaseFormControls;
import de.fu.scetris.web.mods.showcase.ShowCaseFormValidation;
import de.fu.scetris.web.mods.showcase.ShowCaseMultipleForms;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.Module;
import de.fu.weave.User;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.impl.GenericController;
import de.fu.weave.output.JSON;
import de.fu.weave.output.LaTeX;
import de.fu.weave.output.Plain;
import de.fu.weave.output.XSLFO;
import de.fu.weave.reflect.ActionReflector;

/**
 * 
 */
@Author("Julian Fleischer")
public class Scetris extends GenericController<RelationManager> {

	private static final long serialVersionUID = -2323259992443218522L;

	/**
	 * @throws ParserConfigurationException
	 * @throws ControllerInstantiationException
	 */
	@SuppressWarnings("unchecked")
	public Scetris() throws ControllerInstantiationException {
		super(RelationManager.class,
				t("my", My.class),

				// “ordinary” modules
				t("preferences", Preferences.class),
				t("people", People.class),
				t("lectures", Lectures.class),
				t("admin", Admin.class),
				t("imex", ImportExport.class),

				// “special” modules
				t("login", Login.class),

				// german paths for some modules
				t("einstellungen", Preferences.class),
				t("personen", People.class),

				t("showcase", ShowCase.class),
				t("showcase_formBuilder", ShowCaseFormBuilder.class),
				t("showcase_formControls", ShowCaseFormControls.class),
				t("showcase_formValidation", ShowCaseFormValidation.class),
				t("showcase_multipleForms", ShowCaseMultipleForms.class),
				t("showcase_extendedForm", ShowCaseExtendedForm.class),
				t("showcase_acgtForms", ShowCaseAcgtForms.class));
	}

	@Override
	protected void beforeAction(final Module<RelationManager> $module,
								final ActionReflector<? extends Module<RelationManager>> $action)
			throws Exception {
		User $currentUser = $module.getSession().getUser();
		if ($currentUser instanceof Person) {
			Person $currentPerson = (Person) $currentUser;
			$module.put("mycourses", $currentPerson.whereSubjectOfPersonEnrolledInCourseInstance());
		}
	}

	@Override
	protected void beforeInitialization(final ServletConfig $servletConfig) throws Exception {
		try {
			loadProperties($servletConfig.getServletContext().getRealPath("WEB-INF/scetris.properties"),
						   "scetris.");
		} catch (Exception $e) {

		}
		if (!loadOutputConverter(Plain.class, "plain")) {
			throw new RuntimeException("BOOGA");
		}
		if (!loadOutputConverter(XSLFO.class, "pdf")) {
			throw new RuntimeException("Ook? Ook!");
		}
		if (!loadOutputConverter(JSON.class, "json")) {
			throw new RuntimeException("Oompa.");
		}
		if (!loadOutputConverter(LaTeX.class, "latex")) {
			throw new RuntimeException("Kaaanooooth...");
		}
	}

}
