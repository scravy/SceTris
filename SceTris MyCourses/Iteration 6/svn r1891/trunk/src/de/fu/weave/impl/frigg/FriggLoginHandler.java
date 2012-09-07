/* LoginHandler.java / 2:54:23 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

import static de.fu.bakery.orm.java.filters.Filters.*;
import static de.fu.junction.MD5.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;
import de.fu.bakery.orm.java.DatabaseException;
import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.ModuleException;
import de.fu.weave.User;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class FriggLoginHandler<R extends RelationManager> extends SpecialAction<R> {

	FriggLoginHandler(final FriggController<R> parent) {
		super(parent);
	}

	@Override
	void doIt(final Map<String,String[]> parameters) throws SpecialActionException {
		if (session.isUserLoggedIn()) {
			throw new SpecialActionException("You are already logged in.");
		}

		String username, password;
		try {
			username = parameters.get("username")[0];
			password = parameters.get("password")[0];
		} catch (ArrayIndexOutOfBoundsException e) {
			throw new SpecialActionException("No login credentials given", e);
		} catch (NullPointerException e) {
			throw new SpecialActionException("No login credentials given", e);
		}
		Collection<? extends User> users;
		try {
			users =
					relationManager.fetchUsers(all(any(eq("login_name", username),
													   eq("email_address", username)),
												   eq("login_password", md5(password))));
		} catch (DatabaseException e) {
			throw new SpecialActionException(e);
		}

		if (users.isEmpty()) {
			throw new SpecialActionException(new AccessDeniedException(
					"Unknown user or wrong password."));
		} else {
			session.setUser(users.iterator().next());
		}
	}

	@Override
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {
		// gibbet nicht.

	}

}
