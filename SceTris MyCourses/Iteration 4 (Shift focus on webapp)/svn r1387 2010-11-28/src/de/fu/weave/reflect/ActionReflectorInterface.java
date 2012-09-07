/* ActionReflectorInterface.java / 5:38:09 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Module;
import de.fu.weave.xml.annotation.XmlAttribute;

/**
 *
 * @param <M>
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface ActionReflectorInterface<M extends Module<? extends RelationManager>> {

	/**
	 * Returns what action is to be executed after commiting
	 * <p>
	 * See {@link de.fu.weave.annotation.Commit}.
	 * 
	 * @return The name of the Action
	 * @since Iteration2
	 */
	String getAfterCommit();

	/**
	 * Return the Arguments of this Action.
	 * 
	 * @return An Array of Arguments
	 * @since Iteration2
	 */
	ArgReflector[] getArguments();

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	Method getCommitMethod();

	/**
	 * Returns what Exceptions are handled by this action natively
	 * 
	 * @return An array of Classes of Exceptions
	 * @since Iteration2
	 */
	Class<? extends Exception>[] getHandledExceptions();

	/**
	 * Return the Module this Action belongs to
	 * 
	 * @return The Module Reflector of the parent Module
	 * @since Iteration2
	 */
	ModuleReflector<M> getModule();

	/**
	 * Return the name of this action as it is accessible via Web.
	 * 
	 * @return The name of this action.
	 * @since Iteration2
	 */
	@XmlAttribute("name")
	String getName();

	/**
	 * Get the name of the Permission which is required to execute this action.
	 * <p>
	 * See {@link de.fu.weave.annotation.Action#requiresPrivilege()}.
	 * 
	 * @return The name of the permission.
	 * @since Iteration2
	 */
	@XmlAttribute("requiredPermission")
	String getRequiredPermission();

	/**
	 * Return the name of the template that this action requires
	 * 
	 * @return The name of the Template.
	 * @since Iteration2
	 */
	@XmlAttribute("template")
	String getTemplateName();

	/**
	 * Return whether this action does have a Commit Action
	 * <p>
	 * See {@link de.fu.weave.annotation.Commit}.
	 * 
	 * @return true of false
	 */
	boolean hasCommitAction();

	@XmlAttribute("hasTarget")
	boolean hasRequiredPrivilegeTarget();

	/**
	 * Invokes the action on a Module object
	 * 
	 * @param obj
	 *            The Module instance to invoke the method on
	 * @param pathParts
	 *            An array of elements: the path requested
	 * @param requestParams
	 *            A Map of parameters form a HttpRequest or something
	 * @throws InvocationTargetException
	 *             If the invoked action itself failed
	 * @throws IllegalAccessException
	 *             If the invoked action was not accessible since it is not public or so
	 * @since Iteration2
	 */
	void invokeAction(final Module<? extends RelationManager> obj, final String[] pathParts,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException;

	/**
	 * 
	 * @param obj
	 *            The Module instance to invoke the method on
	 * @param args
	 *            An array of elements: the path requested
	 * @throws IllegalAccessException
	 *             If the invoked action was not accessible since it is not public or so
	 * @throws InvocationTargetException
	 *             If the invoked action itself failed
	 * @since Iteration2
	 */
	void invokeCommitAction(final Module<? extends RelationManager> obj,
								   final String[] pathParts, final Map<String,String[]> requestParams)
			throws IllegalAccessException,
			InvocationTargetException;

	/**
	 * Check whether this Action handles the specified Exception itself.
	 * 
	 * @param exc
	 *            A Class instance of the Exception to check
	 * @return true if the Exception is handled by the Action
	 * @since Iteration2
	 */
	boolean isExceptionHandled(final Class<? extends Exception> exc);

	/**
	 * Check whether this Action handles the specified Exception itself.
	 * 
	 * @param exc
	 *            An instance of an Exception to check.
	 * @return true if the Exception is handled by the Action
	 * @since Iteration2
	 */
	boolean isExceptionHandled(final Exception exc);

	/**
	 * Check whether a certain permission is needed to execute this Action.
	 * <p>
	 * This is the case if the requiredPermission is the empty String.
	 * 
	 * @return true if a certain permission is needed
	 * @since Iteration2
	 */
	@XmlAttribute("permissionRequired")
	boolean isPermissionRequired();

	String toString();

}