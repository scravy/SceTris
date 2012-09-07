/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@ModuleInfo(name = "rights",
			author = "Julian Fleischer",
			description = "Administration of Roles & Privileges",
			requires = Requirement.DATABASE)
public class PrivilegesAdmin extends FriggModule<RelationManager> {

	public PrivilegesAdmin(final Scetris parent) {
		super(parent);
	}

	@Action(template = "usermanagement/rights.home.xsl", requiresPrivilege = "rights:home")
	public void _default(final String[] path) {

	}

	@Action(name = "delete", template = "usermanagement/rights.delete.xsl", requiresPrivilege = "rights:delete")
	public void deleteUser(final String[] path) {

	}

	@Action(name = "edit", template = "usermanagement/rights.edit.xsl", requiresPrivilege = "rights:edit")
	public void editUser(final String[] path) {

	}

	@Action(name = "list", template = "usermanagement/rights.list.xsl", requiresPrivilege = "rights:list")
	public void listUsers(final String[] path,
						  @Arg(name = "page", intDefault = 0) final int pageNumber,
						  @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("rights", relationManager.getRole(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
	}

	@Action(name = "new", template = "usermanagement/rights.new.xsl", requiresPrivilege = "rights:new")
	public void newUser(final String[] path) {

	}
}
