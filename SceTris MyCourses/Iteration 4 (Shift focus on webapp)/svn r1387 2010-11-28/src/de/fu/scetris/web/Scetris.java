package de.fu.scetris.web;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.mods.PrivilegesAdmin;
import de.fu.scetris.web.mods.Start;
import de.fu.scetris.web.mods.UserAdmin;
import de.fu.scetris.web.mods.fourtris.Configuration;
import de.fu.scetris.web.mods.fourtris.Coursemanagement;
import de.fu.scetris.web.mods.fourtris.Resources;
import de.fu.scetris.web.mods.testing.Sandbox;
import de.fu.weave.ControllerInstantiationException;
import de.fu.weave.impl.GenericController;

/* Scetris.java / 4:19:17 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Scetris extends GenericController<RelationManager> {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -2323259992443218522L;

	/**
	 * @throws ControllerInstantiationException
	 * @throws ParserConfigurationException
	 * @since Iteration2
	 */
	@SuppressWarnings("unchecked")
	public Scetris() throws ControllerInstantiationException {
		super(de.fu.scetris.data.RelationManager.class, Start.class, Sandbox.class,
				UserAdmin.class, PrivilegesAdmin.class, Resources.class, Configuration.class,
				Coursemanagement.class);
	}

	@Override
	protected void initBefore(final ServletConfig servletConfig) throws Exception {
		loadProperties(servletConfig.getServletContext().getRealPath("WEB-INF/scetris.properties"),
					   "scetris.");
	}
}
