package de.fu.scetris.web;

import javax.servlet.ServletConfig;
import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.mods.fourtris.Start;
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
public class Fourtris extends GenericController<RelationManager> {

	/**
	 * 
	 * @since Iteration2
	 */
	private static final long serialVersionUID = -2323259992443218522L;

	/**
	 * @throws ParserConfigurationException
	 * @since Iteration2
	 */
	public Fourtris() throws ParserConfigurationException, ControllerException {
		super(de.fu.scetris.data.RelationManager.class, Start.class);
	}

	@Override
	protected void initBefore(final ServletConfig servletConfig) throws Exception {
		loadProperties(servletConfig.getServletContext().getRealPath("WEB-INF/fourtris.properties"),
					   "scetris.");
	}
}
