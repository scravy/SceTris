/* LogoutHandler.java / 2:54:34 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;


import de.fu.weave.ModuleException;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class FriggLogoutHandler<R extends RelationManager> extends SpecialAction<R> {

	FriggLogoutHandler(final FriggController<R> parent) {
		super(parent);
	}

	@Override
	void doIt(final Map<String,String[]> parameters) {
		session.setUser(null);
	}

	@Override
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {

	}
}
