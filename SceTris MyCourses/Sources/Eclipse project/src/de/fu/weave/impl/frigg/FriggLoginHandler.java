/* LoginHandler.java / 2:54:23 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;


import static de.fu.junction.MD5.*;
import static de.fu.weave.orm.filters.Filters.*;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Map;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.ModuleException;
import de.fu.weave.User;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.RelationManager;

/**
 * Handles the special action <code>login</code>
 * <p>
 * When a user attempts to log in, he has to provide login credentials, i.e. a token which uniquely
 * identifies him (his unique username or email-address) and a password.
 */
@Author({ "Julian Fleischer", "André Zoufahl" })
public class FriggLoginHandler<R extends RelationManager> extends SpecialAction<R> {

    /**
     * Standard constructor.
     * 
     * @param $parent
     *            The Weavlet that uses this special action.
     */
    FriggLoginHandler(final FriggWeavlet<R> $parent) {
        super($parent);
    }

    @Override
    void doIt(final Map<String,String[]> $parameters) throws SpecialActionException {
        if ($session.isUserLoggedIn()) {
            throw new SpecialActionException("You are already logged in.");
        }

        String $username, $password;
        try {
            $username = $parameters.get("username")[0];
            $password = $parameters.get("password")[0];
        } catch (ArrayIndexOutOfBoundsException e) {
            throw new SpecialActionException("No login credentials given", e);
        } catch (NullPointerException e) {
            throw new SpecialActionException("No login credentials given", e);
        }
        Collection<? extends User> $users;
        try {
            $users = relationManager.fetchUsers(all(any(
                eq("login_name", $username),
                eq("email_address", $username)),
                eq("login_password", md5($password))));
        } catch (DatabaseException $exc) {
            throw new SpecialActionException($exc);
        }

        if ($users.isEmpty()) {
            throw new SpecialActionException(new AccessDeniedException(
                    "Unknown user or wrong password."));
        } else {
            $session.setUser($users.iterator().next());
        }
    }

    @Override
    public void invokeAction(final String actionName, final String[] params,
                             final Map<String,String[]> requestParams)
            throws IllegalAccessException, InvocationTargetException, ModuleException {
        // gibbet nicht.

    }

}
