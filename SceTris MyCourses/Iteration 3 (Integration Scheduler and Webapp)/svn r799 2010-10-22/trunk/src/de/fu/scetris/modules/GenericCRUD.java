/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.modules;

import static de.fu.weave.util.MD5.md5;
import de.fu.scetris.Scetris;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigga.GenericModule;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@ModuleInfo(name = "crud",
			author = "Julian Fleischer",
			description = "Provides generic methods for working with a Database.",
			requires = Requirement.DATABASE)
public class GenericCRUD extends GenericModule<RelationManager> {

	/**
	 * The Controller that instantiated this Module
	 * 
	 * @param parent
	 * @since Iteration2
	 */
	public GenericCRUD(final Scetris parent) {
		super(parent);
	}

	@Action(template = "crud.xsl",
			requiresPrivilege = "crud:start",
			handles = AccessDeniedException.class)
	public void _default(final String[] params) {

	}

	/**
	 * 
	 * @param params
	 * @since Iteration2
	 */
	@Action(name = "entities", template = "debug.xsl")
	public void aboutEntities(final String[] params) {
		// put("schema", entityManager.getSchema());
	}

	/**
	 * 
	 * @param params
	 *            See description of {@link Action}
	 * @since Iteration2
	 */
	@Action(name = "edit", template = "crud.edit.xsl", requiresPrivilege = "crud:edit")
	public void editEntities(final String[] params) {

	}

	/**
	 * 
	 * @param params
	 * @throws DatabaseException
	 * @since Iteration3
	 */
	@Action(name = "amelie", template = "debug.xsl")
	public void insertSuperUser(final String[] params) throws DatabaseException {

		Person mike =
				relationManager.createPerson("Mike", "Narkol" + Math.random(), "steinbeisserchen"
						+ Math.random(), md5("amelie"));
		put("mike", mike.id());

		Person sally =
				relationManager.newPerson("Sally", "x" + Math.random(), "xx" + Math.random(),
										  md5("yjippie"));
		sally.setAdditionalNames("Sandy");
		sally.setDeletedBy(mike);
		sally.create();

		sally.delete();

		Building t9 = relationManager.createBuilding("Takustraße " + Math.random());

		Room hs1 = relationManager.createRoom("HS 00" + Math.random(), t9);
		hs1.setName("Großer Hörsaal " + Math.random());
		relationManager.createRoom("HS002 - " + Math.random(), t9);

		for (int i = 0; i < 120; i++) {
			relationManager.createRoom("R" + i, t9);
		}
	}

	/**
	 * 
	 * @param params
	 *            See description of {@link Action}
	 * @since Iteration2
	 */
	@Action(name = "list", template = "crud.list.xsl", handles = DatabaseException.class)
	public void listEntities(final String[] params) throws DatabaseException {

		put("persons", relationManager.getPerson());

	}
}
