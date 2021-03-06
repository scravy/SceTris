/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.web.forms.LoginUser;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;

/**
 * 
 */
@Author("Julian Fleischer")
public class Login extends FriggModule<RelationManager> {

	public Login(final ScetrisServlet parent) {
		super(parent);
	}

	@Action(template = "login.xsl")
	public void _default(final String[] target) {

	}
	
	
	@Action(template = "login.reworked.xsl")
	public void loginUser(final LoginUser $user) {
		if ($user.isSuccessfullyCommitted()) {
			$user.reset();
			setRedirect("admin");
		}
		put("loginUser", $user);
	}	
}
