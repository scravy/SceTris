/* LogoutHandler.java / 2:54:34 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import java.lang.reflect.InvocationTargetException;
import java.util.Map;

import de.fu.weave.ModuleException;
import de.fu.weave.orm.RelationManager;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class LogoutHandler<R extends RelationManager> extends SpecialAction<R> {

	LogoutHandler(final GenericController<R> parent) {
		super(parent);
	}

	@Override
	void doIt(final Map<String,String[]> parameters) {
		session.setUser(null);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.Module#invokeAction(java.lang.String, java.lang.String[], java.util.Map)
	 */
	@Override
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {
		// TODO Auto-generated method stub

	}
}
