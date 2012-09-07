/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.Array;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Map;

import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * An ActionMethod represents a Method in a Module which can be executed by the Controller. It’s
 * behvior is determined by Annotations from {@link de.fu.weave.annotation}.
 * <p>
 * 
 * @author Julian Fleischer
 * @param <M>
 *            The Class of the Module to which the Action belongs that is to be reflected
 * @since Iteration2
 */
public class ActionReflector<M extends Module<? extends RelationManager>> {
	/**
	 * The parent of this Action (a {@link ModuleReflector}).
	 * 
	 * @since Iteration2
	 */
	final private ModuleReflector<M> parentModule;

	/**
	 * {@link #getName()}
	 * 
	 * @since Iteration2
	 */
	final private String name;

	/**
	 * {@link #getTemplateName()}
	 * 
	 * @since Iteration2
	 */
	final private String template;

	/**
	 * The reflector of the annotated Method
	 * 
	 * @since Iteration2
	 */
	final private Method method;

	/**
	 * The reflector of the commit Method, if there is a Commit Method
	 * <p>
	 * {@link de.fu.weave.annotation.Commit}
	 * 
	 * @since Iteration2
	 */
	final private Method commitMethod;

	/**
	 * {@link #getAfterCommit()}
	 * 
	 * @since Iteration2
	 */
	final private String afterCommit;

	/**
	 * {@link #getArguments()}
	 * 
	 * @since Iteration2
	 */
	final private ArgReflector[] arguments;

	/**
	 * {@link #getArguments()}
	 * 
	 * @since Iteration2
	 */
	final private ArgReflector[] commitArguments;
	/**
	 * {@link #getRequiredPermission()}
	 * 
	 * @since Iteration2
	 */
	final private String requiredPermission;

	/**
	 * {@link #getHandledExceptions()}
	 * 
	 * @since Iteration2
	 */
	final private Class<? extends Exception>[] exceptionsHandled;

	/**
	 * @since Iteration3
	 */
	final private ArgReflector permissionTargetReflector;

	/**
	 * This is the one and only Constructor for this Class.
	 * <p>
	 * It is typically called from within a Module Reflector ({@link ModuleReflector}), which passes
	 * itself as parent of this action. If checks if the action is valid, that includes, but is not
	 * limited to:
	 * <ol>
	 * <li>Is the annotated method public?</li>
	 * <li>Are the Parameters well-defined?</li>
	 * <li>...</li>
	 * </ol>
	 * 
	 * @param parent
	 *            The modules this Action belongs to.
	 * @param method
	 *            The Method reflector of the annotated Method.
	 * @param commitMethod
	 *            The Method to be used on committing things. This may be null if such a Method does
	 *            not exist.
	 * @throws ModuleException
	 *             If the action is invalid.
	 * @since Iteration2
	 */
	ActionReflector(final ModuleReflector<M> parent, final Method method, final Method commitMethod)
			throws ModuleException {
		parentModule = parent;
		de.fu.weave.annotation.Action annotation =
				method.getAnnotation(de.fu.weave.annotation.Action.class);
		name = annotation.name();
		template = annotation.template();
		ArgReflector targetReflector = null;

		this.method = method;

		if (annotation.requiresPrivilege().length() > 0) {
			requiredPermission = annotation.requiresPrivilege();
		} else {
			requiredPermission = null;
		}

		exceptionsHandled = annotation.handles();

		Annotation[][] annotations = method.getParameterAnnotations();
		Class<?>[] parameterTypes = method.getParameterTypes();
		if (annotations.length != parameterTypes.length) {
			throw new ModuleException("Action \"" + name + "\" in Module \""
					+ parentModule.getName() + "\" is invalid.");
		}
		if (parameterTypes.length < 1) {
			throw new ModuleException("Action \"" + name + "\" in Module \""
					+ parentModule.getName() + "\" is invalid: Must take at least one parameter");
		}
		if (!parameterTypes[0].equals(String[].class)) {
			throw new ModuleException("Action \"" + name + "\" in Module \""
					+ parentModule.getName()
					+ "\" is invalid: First argument must be of type String[]");
		}
		arguments = new ArgReflector[parameterTypes.length];
		arguments[0] = null;
		for (int i = 1; i < parameterTypes.length; i++) {
			Class<?> paramType = parameterTypes[i];
			Annotation[] paramAnnotations = annotations[i];

			arguments[i] = null;
			for (Annotation anno : paramAnnotations) {
				if (anno instanceof Arg) {
					Arg argument = (Arg) anno;
					arguments[i] = new ArgReflector(this, paramType, argument);
					if (arguments[i].isTarget()) {
						targetReflector = arguments[i];
					}
				}
			}
			if (arguments[i] == null) {
				throw new ModuleException(
						"\" Action \""
								+ name
								+ "\" in Module \""
								+ parentModule.getName()
								+ "\" is invalid: all parameters, except the first one, must have a @Param-Annotation");
			}
		}
		this.permissionTargetReflector = targetReflector;
		this.commitMethod = commitMethod;

		// -- commit
		if (commitMethod != null) {
			annotations = commitMethod.getParameterAnnotations();
			parameterTypes = commitMethod.getParameterTypes();
			if (annotations.length != parameterTypes.length) {
				throw new ModuleException("CommitAction \"" + name + "\" in Module \""
						+ parentModule.getName() + "\" is invalid.");
			}
			if (parameterTypes.length < 1) {
				throw new ModuleException("CommitAction \"" + name + "\" in Module \""
						+ parentModule.getName() + "\" is invalid: Must take at least one parameter");
			}
			if (!parameterTypes[0].equals(String[].class)) {
				throw new ModuleException("Action \"" + name + "\" in Module \""
						+ parentModule.getName()
						+ "\" is invalid: First argument must be of type String[]");
			}
			commitArguments = new ArgReflector[parameterTypes.length];
			commitArguments[0] = null;
			for (int i = 1; i < parameterTypes.length; i++) {
				Class<?> paramType = parameterTypes[i];
				Annotation[] paramAnnotations = annotations[i];

				commitArguments[i] = null;
				for (Annotation anno : paramAnnotations) {
					if (anno instanceof Arg) {
						Arg argument = (Arg) anno;
						commitArguments[i] = new ArgReflector(this, paramType, argument);
						if (commitArguments[i].isTarget()) {
							targetReflector = arguments[i];
						}
					}
				}
				if (commitArguments[i] == null) {
					throw new ModuleException(
							"\" Action \""
									+ name
									+ "\" in Module \""
									+ parentModule.getName()
									+ "\" is invalid: all parameters, except the first one, must have a @Param-Annotation");
				}
			}
		} else {
			commitArguments = new ArgReflector[] {};
		}
		// -- commit

		afterCommit =
				(commitMethod == null) ? null : commitMethod.getAnnotation(Commit.class).after();
	}

	/**
	 * Returns what action is to be executed after commiting
	 * <p>
	 * See {@link de.fu.weave.annotation.Commit}.
	 * 
	 * @return The name of the Action
	 * @since Iteration2
	 */
	public String getAfterCommit() {
		return afterCommit;
	}

	/**
	 * Return the Arguments of this Action.
	 * 
	 * @return An Array of Arguments
	 * @since Iteration2
	 */
	public ArgReflector[] getArguments() {
		return arguments;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public Method getCommitMethod() {
		return this.commitMethod;
	}

	/**
	 * Returns what Exceptions are handled by this action natively
	 * 
	 * @return An array of Classes of Exceptions
	 * @since Iteration2
	 */
	public Class<? extends Exception>[] getHandledExceptions() {
		return this.exceptionsHandled;
	}

	/**
	 * Return the Module this Action belongs to
	 * 
	 * @return The Module Reflector of the parent Module
	 * @since Iteration2
	 */
	public ModuleReflector<M> getModule() {
		return parentModule;
	}

	/**
	 * Return the name of this action as it is accessible via Web.
	 * 
	 * @return The name of this action.
	 * @since Iteration2
	 */
	@XmlAttribute("name")
	public String getName() {
		return name;
	}

	/**
	 * Get the name of the Permission which is required to execute this action.
	 * <p>
	 * See {@link de.fu.weave.annotation.Action#requiresPrivilege()}.
	 * 
	 * @return The name of the permission.
	 * @since Iteration2
	 */
	@XmlAttribute("requiredPermission")
	public String getRequiredPermission() {
		return requiredPermission;
	}

	/**
	 * Return the name of the template that this action requires
	 * 
	 * @return The name of the Template.
	 * @since Iteration2
	 */
	@XmlAttribute("template")
	public String getTemplateName() {
		return template;
	}

	/**
	 * Return whether this action does have a Commit Action
	 * <p>
	 * See {@link de.fu.weave.annotation.Commit}.
	 * 
	 * @return true of false
	 */
	public boolean hasCommitAction() {
		return commitMethod != null;
	}

	@XmlAttribute("hasTarget")
	public boolean hasRequiredPrivilegeTarget() {
		return permissionTargetReflector != null;
	}

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
	public void invokeAction(final Module<? extends RelationManager> obj, final String[] pathParts,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException {
		Object[] callParams = new Object[arguments.length];
		Class<?>[] parameters = this.method.getParameterTypes();
		int i = 0;
		if (parameters.length > 0) {
			if (!parameters[0].isAnnotationPresent(Arg.class)) {
				if (parameters[0].equals(String[].class)) {
					callParams[i++] = pathParts;
				} else {
					throw new RuntimeException(
							"First argument of action-method is neither annotated via @Arg nor of "
									+ "type String[]. This should have been checked already, and it’s"
									+ "terribly wrong!");
				}
			}
		}
		for (; i < arguments.length; i++) {
			if (arguments[i] != null) {
				callParams[i] = arguments[i].forValue(requestParams.get(arguments[i].getName()));
				if (arguments[i].getType().isArray() && !callParams[i].getClass().isArray()) {
					Object paramValue = callParams[i];
					callParams[i] = Array.newInstance(arguments[i].getType().getComponentType(), 1);
					Array.set(callParams[i], 0, paramValue);
				}
			}
		}
		try {
			method.invoke(obj, callParams);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"This really should not have happened. Action "
							+ this.name
							+ " in Module "
							+ this.parentModule.getName()
							+ " was called with illegal arguments. This should have been checked previously!");
		}
	}

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
	public void invokeCommitAction(final Module<? extends RelationManager> obj,
								   final String[] pathParts, final Map<String,String[]> requestParams)
			throws IllegalAccessException,
			InvocationTargetException {
		Object[] callParams = new Object[commitArguments.length];
		Class<?>[] parameters = commitMethod.getParameterTypes();
		int i = 0;
		if (parameters.length > 0) {
			if (!parameters[0].isAnnotationPresent(Arg.class)) {
				if (parameters[0].equals(String[].class)) {
					callParams[i++] = pathParts;
				} else {
					throw new RuntimeException(
							"First argument of commit-action-method is neither annotated via @Arg nor of "
									+ "type String[]. This should have been checked already, and it’s"
									+ "terribly wrong!");
				}
			}
		}
		for (; i < commitArguments.length; i++) {
			if (commitArguments[i] != null) {
				callParams[i] =
						commitArguments[i].forValue(requestParams.get(commitArguments[i].getName()));
				if (commitArguments[i].getType().isArray() && !callParams[i].getClass().isArray()) {
					Object paramValue = callParams[i];
					callParams[i] =
							Array.newInstance(commitArguments[i].getType().getComponentType(), 1);
					Array.set(callParams[i], 0, paramValue);
				}
			}
		}
		try {
			commitMethod.invoke(obj, callParams);
		} catch (IllegalArgumentException e) {
			throw new RuntimeException(
					"This really should not have happened. CommitAction "
							+ this.name
							+ " in Module "
							+ this.parentModule.getName()
							+ " was called with illegal arguments. This should have been checked previously!");
		}
	}

	/**
	 * Check whether this Action handles the specified Exception itself.
	 * 
	 * @param exc
	 *            A Class instance of the Exception to check
	 * @return true if the Exception is handled by the Action
	 * @since Iteration2
	 */
	public boolean isExceptionHandled(final Class<? extends Exception> exc) {
		for (Class<? extends Exception> exception : exceptionsHandled) {
			if (exception.isAssignableFrom(exc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * Check whether this Action handles the specified Exception itself.
	 * 
	 * @param exc
	 *            An instance of an Exception to check.
	 * @return true if the Exception is handled by the Action
	 * @since Iteration2
	 */
	public boolean isExceptionHandled(final Exception exc) {
		return isExceptionHandled(exc.getClass());
	}

	/**
	 * Check whether a certain permission is needed to execute this Action.
	 * <p>
	 * This is the case if the requiredPermission is the empty String.
	 * 
	 * @return true if a certain permission is needed
	 * @since Iteration2
	 */
	@XmlAttribute("permissionRequired")
	public boolean isPermissionRequired() {
		return requiredPermission != null;
	}

}
