/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.weave.util.MD5.md5;

import java.util.Collection;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.Scetris;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 * @author Julian Fleischer/Andre Zoufahl
 * @since Iteration2/3
 */
@ModuleInfo(name = "users",
			author = "Julian Fleischer",
			description = "User Administration",
			requires = Requirement.DATABASE)
public class UserAdmin extends FriggModule<RelationManager> {

	public UserAdmin(final Scetris parent) {
		super(parent);
	}

	@Action(template = "usermanagement/usermanagement.menu.xsl")
	public void _default(final String[] path) {

	}

	@Action(name = "delete",
			template = "usermanagement/users.delete.xsl",
			requiresPrivilege = "users:delete")
	public void deleteUser(final String[] path) {

	}

	@Action(name = "edit", template = "usermanagement/users.edit.xsl", requiresPrivilege = "users:edit")
	public void editUser(final String[] path,
						 @Arg(name = "user", stringDefault = "") final String userName)
			throws DatabaseException {
		put("users", relationManager.getPerson(eq("login_name", userName)));
	}

	@Action(name = "editsubmit",
			template = "usermanagement/users.show.xsl",
			requiresPrivilege = "users:edit")
	public void editUser(final String[] path,
						 @Arg(name = "firstname", stringDefault = "") final String firstname,
						 @Arg(name = "loginname", stringDefault = "") final String loginname,
						 @Arg(name = "loginpw", stringDefault = "") final String password,
						 @Arg(name = "superuser", intDefault = 0) final int superuser,
						 @Arg(name = "lastname", stringDefault = "") final String lastname)
			throws DatabaseException {

		put("firstname", firstname);
		put("lastname", lastname);
		put("loginname", loginname);
		put("loginpw", password);
		put("loginpwcrypt", md5(password));
		Collection<Person> tmp2 = relationManager.getPerson(eq("login_name", loginname));

		Person tmp;
		if (!tmp2.isEmpty()) {
			tmp = tmp2.iterator().next();
		} else {
			throw new DatabaseException("no such user available >" + loginname + "<");
		}

		if (!firstname.equals("") && !lastname.equals("")) {
			tmp.setFirstName(firstname);
			tmp.setLastName(lastname);
			tmp.setCreatedBy(null);
			tmp.setDeletedBy(null);
			tmp.pushChanges();
		}
		put("users", tmp);

	}

	@Action(name = "list", template = "usermanagement/users.list.xsl", requiresPrivilege = "users:list")
	public void listUsers(final String[] path,
						  @Arg(name = "page", intDefault = 0) final int pageNumber,
						  @Arg(name = "limit", intDefault = 20) final int perPage)
			throws DatabaseException {
		put("users", relationManager.getPerson(pageNumber * perPage, perPage));
		put("limit", perPage);
		put("page", pageNumber);
	}

	@Action(name = "new", template = "usermanagement/users.new.xsl", requiresPrivilege = "users:new")
	public void newUser(final String[] path,
						@Arg(name = "firstname", stringDefault = "") final String firstname,
						@Arg(name = "loginname", stringDefault = "") final String loginname,
						@Arg(name = "loginpw", stringDefault = "") final String password,
						@Arg(name = "superuser", intDefault = 0) final int superuser,
						@Arg(name = "lastname", stringDefault = "") final String lastname)
			throws DatabaseException {
		put("firstname", firstname);
		put("lastname", lastname);
		put("loginname", loginname);
		put("loginpw", password);
		put("loginpwcrypt", md5(password));
		if (!firstname.equals("") && !lastname.equals("") && !password.equals("")) {
			de.fu.scetris.data.Person tmp =
					relationManager.newPerson(firstname, lastname, loginname, md5(password));
			if (superuser > 0) {
				tmp.setIsSuperuser(true);
			}
			tmp.create();
		}
	}

	@Action(name = "search",
			template = "usermanagement/users.search.xsl",
			requiresPrivilege = "users:search")
	public void searchUsers(final String[] path,
							@Arg(name = "searchName", stringDefault = "") final String searchName,
							@Arg(name = "searchMail", stringDefault = "") final String searchMail)
			throws DatabaseException {
		put("searchName", searchName);
		put("searchMail", searchMail);
		String likeName = searchName.length() > 0 ? "%" + searchName + "%" : "";
		String likeMail = searchMail.length() > 0 ? "%" + searchMail + "%" : "";
		put("users", relationManager.getPerson(any(like("login_name", likeName),
												   like("first_name", likeName),
												   like("last_name", likeName),
												   like("email_address", likeMail))));
	}

	@Action(name = "show", template = "usermanagement/users.show.xsl", requiresPrivilege = "users:show")
	public void showUser(final String[] path,
						 @Arg(name = "user", stringDefault = "") final String userName)
			throws DatabaseException {
		put("users", relationManager.getPerson(eq("login_name", userName)));
	}
}
