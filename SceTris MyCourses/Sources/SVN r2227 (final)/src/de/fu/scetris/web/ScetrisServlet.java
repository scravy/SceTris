/* Fourtris.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.web;


import java.sql.Timestamp;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.BasicConfigurator;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonEnrolledInCourseInstance;
import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.showcase.ShowCase;
import de.fu.scetris.web.showcase.ShowCaseAcgtForms;
import de.fu.scetris.web.showcase.ShowCaseAdvancedFormValidation;
import de.fu.scetris.web.showcase.ShowCaseExtendedForm;
import de.fu.scetris.web.showcase.ShowCaseFormBuilder;
import de.fu.scetris.web.showcase.ShowCaseFormCommitException;
import de.fu.scetris.web.showcase.ShowCaseFormControls;
import de.fu.scetris.web.showcase.ShowCaseFormReuse;
import de.fu.scetris.web.showcase.ShowCaseFormValidation;
import de.fu.scetris.web.showcase.ShowCaseMultipleOptions;
import de.fu.weave.Module;
import de.fu.weave.Session;
import de.fu.weave.User;
import de.fu.weave.WeavletInstantiationException;
import de.fu.weave.impl.GenericWeavlet;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.output.JSON;
import de.fu.weave.output.LaTeX;
import de.fu.weave.output.Plain;
import de.fu.weave.output.XSLFO;
import de.fu.weave.reflect.ActionReflector;

/**
 * The Scetris Servlet.
 * <p>
 * The Scetris Servlet is a customized version of an abstract Weavlet.
 */
@Author("Julian Fleischer")
public class ScetrisServlet extends GenericWeavlet<RelationManager> {

    private static final long serialVersionUID = -2323259992443218522L;

    final protected long $start = System.currentTimeMillis();

    /**
     * The Standard-Constructor.
     * <p>
     * Basically this constructor calls its superordinate Constructor ({@see
     * de.fu.weave.impl.frigg.FriggWeavlet#FriggWeavlet(Class, de.fu.junction.Tuple,
     * de.fu.junction.Tuple...)}) with a list of Modules to be loaded by the Weave-Controller
     * (Weavlet).
     * 
     * @throws ParserConfigurationException
     * @throws WeavletInstantiationException
     */
    @SuppressWarnings("unchecked")
    public ScetrisServlet() throws WeavletInstantiationException {
        super(RelationManager.class,
                T("my", My.class),

                // “ordinary” modules
                T("preferences", Preferences.class),
                T("people", People.class),
                T("lectures", Lectures.class),
                T("admin", Admin.class),
                T("courses", Courses.class),
                T("imex", ImportExport.class),
                T("help", Help.class),

                // “special” modules
                T("login", Login.class),

                // german paths for some modules
                T("einstellungen", Preferences.class),
                T("personen", People.class),
                T("hilfe", Help.class),

                T("showcase", ShowCase.class),
                T("showcase_formBuilder", ShowCaseFormBuilder.class),
                T("showcase_formControls", ShowCaseFormControls.class),
                T("showcase_formValidation", ShowCaseFormValidation.class),
                T("showcase_advancedFormValidation", ShowCaseAdvancedFormValidation.class),
                T("showcase_formCommitException", ShowCaseFormCommitException.class),
                T("showcase_formMultipleOptions", ShowCaseMultipleOptions.class),
                T("showcase_extendedForm", ShowCaseExtendedForm.class),
                T("showcase_acgtForms", ShowCaseAcgtForms.class),
                T("showcase_formReuse", ShowCaseFormReuse.class));
    }

    @Override
    protected void afterInitialization(final ServletConfig $servletConfig) throws Exception {
        $logger.info("Scetris startup time: " + (System.currentTimeMillis() - $start) + "ms");
    }

    @Override
    protected void afterLogin(final Session $session) {
        Person $user = ((Person) $session.getUser());
        try {
            $user
                    .setLoginCount($user.getLoginCount() + 1)
                    .setLastLogin(new Timestamp(System.currentTimeMillis()))
                    .pushChanges();
        } catch (DatabaseException $exc) {
            $logger.warn("Could not update login time");
        }
    }

    /**
     * Scetris specific stuff that is executed before an Action is executed.
     * <p>
     * Basically this checks whether the user is logged in or not and inserts a list of privileges
     * as well as users the user is enrolled in into the result Document.
     */
    @Override
    protected void beforeAction(final Module<RelationManager> $module,
                                final ActionReflector<? extends Module<RelationManager>> $action)
            throws Exception {
        User $currentUser = $module.session().getUser();
        if ($currentUser instanceof Person) {
            Person $currentPerson = (Person) $currentUser;

            List<PersonEnrolledInCourseInstance> $mycourses = $currentPerson
                    .whereSubjectOfPersonEnrolledInCourseInstance();
            for (PersonEnrolledInCourseInstance $x : $mycourses) {
                $x.getCourseInstance().getInstanceOf();
            }
            $module.put("mycourses", $mycourses);

            Element $p = $module.put("privileges");
            Document $d = $module.getDocument();
            for (Privilege $priv : $currentPerson.objectsOfPersonHasPrivilege()) {
                $p.appendChild($d.createElement($priv.getName()));
            }
        } else {
            $module.put("privileges");
        }
    }

    /**
     * Scetris specific initialization (triggered before servlet initialization)
     * <p>
     * This takes care of configuration, logging and loading output converters. Note that default
     * converters (XHTML, HTML, XML) are natively enabled and need not be loaded.
     */
    @Override
    protected void beforeInitialization(final ServletConfig $servletConfig) throws Exception {
        // String $configFile = $servletConfig.getInitParameter("configFile");
        // try {
        // if ($configFile == null) {
        // $configFile = "log4j.properties";
        // }
        // } catch (Exception $exc) {}
        // if (new File($configFile).isFile()) {
        // PropertyConfigurator.configure($servletConfig.getServletContext().getRealPath(
        // "WEB-INF/" + $configFile));
        // } else {
        // BasicConfigurator.configure();
        // $logger.setLevel(Level.INFO);
        // }
        BasicConfigurator.configure();
        try {
            loadProperties($servletConfig.getServletContext().getRealPath("WEB-INF/scetris.properties"),
                   "scetris.");
        } catch (Exception $exc) {}
        if (!loadOutputConverter(Plain.class, "plain")) {
            $logger.warn("Could not load OutputConverter for text/plain (plain)");
        }
        if (!loadOutputConverter(XSLFO.class, "pdf")) {
            $logger.warn("Could not load OutputConverter for application/pdf (pdf)");
        }
        if (!loadOutputConverter(JSON.class, "json")) {
            $logger.warn("Could not load OutputConverter for JSON");
        }
        if (!loadOutputConverter(LaTeX.class, "latex")) {
            $logger.warn("Could not load OutputConverter for LaTeX");
        }
    }

}
