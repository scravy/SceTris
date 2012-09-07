/* Fourtris.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.web;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.mods.fourtris.Facilities;
import de.fu.scetris.web.mods.fourtris.Help;
import de.fu.scetris.web.mods.fourtris.ImportExport;
import de.fu.scetris.web.mods.fourtris.Index;
import de.fu.scetris.web.mods.fourtris.Lectures;
import de.fu.scetris.web.mods.fourtris.Login;
import de.fu.scetris.web.mods.fourtris.My;
import de.fu.scetris.web.mods.fourtris.People;
import de.fu.scetris.web.mods.fourtris.Resources;
import de.fu.scetris.web.mods.fourtris.Scheduler;
import de.fu.scetris.web.mods.fourtris.Search;
import de.fu.scetris.web.mods.fourtris.Settings;
import de.fu.scetris.web.mods.fourtris.Foobar;
import de.fu.scetris.web.mods.fourtris.UserMgmt;
import de.fu.scetris.web.mods.fourtris.Configuration;
import de.fu.scetris.web.mods.fourtris.Coursemanagement;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.impl.GenericController;
import de.fu.weave.output.JSON;
import de.fu.weave.output.LaTeX;
import de.fu.weave.output.Plain;
import de.fu.weave.output.XSLFO;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Fourtris extends GenericController<RelationManager> {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -2323259992443218522L;

	/**
	 * @throws ParserConfigurationException
	 * @throws ControllerInstantiationException
	 * @since Iteration2
	 */
	@SuppressWarnings("unchecked")
	public Fourtris() throws ControllerInstantiationException {
		super(de.fu.scetris.data.RelationManager.class, 
				My.class, 
				Index.class, 
				Help.class,
				Settings.class, 
				UserMgmt.class, 
				ImportExport.class, 
				Lectures.class, 
				People.class,
				Search.class, 
				Facilities.class, 
				Resources.class, 
				Configuration.class, 
				Coursemanagement.class, 
				Foobar.class, 
				Login.class,
				Scheduler.class);
	}

	@Override
	protected void initBefore(final ServletConfig servletConfig) throws Exception {
		try {
			loadProperties(servletConfig.getServletContext().getRealPath("WEB-INF/scetris.properties"),
						   "scetris.");
		} catch (Exception e) {

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
