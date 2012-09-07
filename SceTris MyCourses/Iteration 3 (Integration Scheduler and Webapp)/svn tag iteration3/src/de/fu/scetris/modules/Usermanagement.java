/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.impl.frigga.GenericModule;

/**
 * A module to insert new Buildings.
 * 
 * @author Andre Zoufahl
 */
@ModuleInfo(name = "usermanagement",
			author = "Andre Zoufahl",
			description = "Interface to manage all user-related stuff",
			menu = "usermanagement")
public class Usermanagement extends GenericModule<RelationManager> {

	/**
	 * 
	 * @see GenericModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Usermanagement(final Scetris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "usermanagement.xsl")
	public void _default(final String[] target) {
		put("modules", getReflector());
	}
}
