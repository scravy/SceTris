package de.fu.scetris;

import javax.xml.parsers.ParserConfigurationException;

import de.fu.scetris.data.RelationManager;
import de.fu.weave.impl.frigga.ControllerException;
import de.fu.weave.impl.frigga.GenericController;

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
	public Scetris() throws ParserConfigurationException, ControllerException {
		super(de.fu.scetris.data.RelationManager.class);
	}

}
