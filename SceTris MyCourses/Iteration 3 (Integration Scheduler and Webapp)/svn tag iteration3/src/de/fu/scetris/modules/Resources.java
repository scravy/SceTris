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
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A module to display a start-page.
 * 
 * @author Andre Zoufahl
 */
@ModuleInfo(name = "ressources",
			author = "Andre Zoufahl",
			description = "Provides more menus to do CRUD on entities that are categorized as a ressource.",
			menu = "ressources",
			requires = Requirement.DATABASE)
public class Resources extends GenericModule<RelationManager> {

	/**
	 * 
	 * @see GenericModule
	 * @param parent
	 *            See description of {@link Action}
	 */
	public Resources(final Scetris parent) {
		super(parent);
	}

	/**
	 * The default action which displays the start-page (this is /start)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(template = "ressources.xsl")
	public void _default(final String[] target) {
		put("modules", getReflector());
	}

	/**
	 * A sitemap action that generates a listing of available modules and actions (this is
	 * /start/sitemap/)
	 * 
	 * @param target
	 *            See description of {@link Action}
	 */
	@Action(name = "room", template = "default-entities.xsl")
	public void createRooms(final String[] target) {
		put("target", target);
	} 

	@Action(name = "department", template = "default-entities.xsl")
	public void createDepartments(final String[] target) {
		put("target", target);
	} 	

	@Action(name = "building", template = "default-entities.xsl")
	public void createBuildings(final String[] target) throws DatabaseException {
		put("data", relationManager.getBuilding());
		put("target", target);
	} 		

	@Action(name = "CourseInstance", template = "default-entities.xsl")
	public void createCI(final String[] target) {
		put("target", target);
	} 
	
	@Action(name = "CourseElement", template = "default-entities.xsl")
	public void createCE(final String[] target) {
		put("target", target);
	} 	
}
