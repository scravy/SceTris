package de.fu.scetris.web;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.modules.Scheduling;
import de.fu.scetris.web.mods.PrivilegesAdmin;
import de.fu.scetris.web.mods.Start;
import de.fu.scetris.web.mods.UserAdmin;
import de.fu.scetris.web.mods.Ressources;
import de.fu.scetris.web.mods.testing.Sandbox;
import de.fu.weave.impl.ControllerException;
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
	 * @throws ParserConfigurationException
	 * @since Iteration2
	 */
	@SuppressWarnings("unchecked")
	public Scetris() throws ParserConfigurationException, ControllerException {
		super(de.fu.scetris.data.RelationManager.class, Start.class, Sandbox.class,
				UserAdmin.class, PrivilegesAdmin.class, Scheduling.class, Ressources.class, de.fu.scetris.web.mods.Configuration.class);
	}

	@Override
	protected void initBefore(final ServletConfig servletConfig) throws Exception {
		loadProperties(servletConfig.getServletContext().getRealPath("WEB-INF/scetris.properties"),
					   "scetris.");
	}
}
