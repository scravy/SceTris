/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.Map;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.User;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
import de.fu.weave.annotation.Commit;
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

	private final ModuleReflector<M> parentModule;

	private final Method method;

	private final Method commitMethod;

	private final String name;

	private final String template;

	private final String[] requiredPrivileges;

	private final Class<? extends Exception>[] handledExceptions;

	private final ArgReflector[] arguments;

	private final ArgReflector[] commitArguments;

	private final String afterCommit;

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
		if (method == null) {
			throw new IllegalArgumentException("No method given");
		}
		if (parent == null) {
			throw new IllegalArgumentException("No parent module given");
		}
		parentModule = parent;
		this.method = method;
		arguments = analyzeMethod(method);

		Action annotation = method.getAnnotation(Action.class);
		if (annotation == null) {
			throw new ModuleException(method.getName() + " is not properly annotated");
		}

		this.name = annotation.name();
		this.template = annotation.template();
		requiredPrivileges = annotation.requiresPrivilege();
		Arrays.sort(requiredPrivileges);
		handledExceptions = annotation.handles();

		Commit commitAnnotation;
		if ((commitMethod != null)
				&& ((commitAnnotation = commitMethod.getAnnotation(Commit.class)) != null)) {
			afterCommit = commitAnnotation.after();
			analzyeCommitMethod(commitMethod);
			commitArguments = analyzeMethod(commitMethod);
		} else {
			afterCommit = null;
			commitArguments = new ArgReflector[0];
		}
		this.commitMethod = commitMethod;
	}

	/**
	 * @param method2
	 * @throws ModuleException
	 * @since Iteration4
	 */
	private ArgReflector[] analyzeMethod(final Method method) throws ModuleException {
		Class<?>[] types = method.getParameterTypes();
		Annotation[][] annotations = method.getParameterAnnotations();
		ArgReflector[] args = new ArgReflector[types.length];
		for (int i = 0; i < types.length; i++) {
			Arg arg = null;
			Class<?> type = types[i];
			args[i] = null;
			for (Annotation annotation : annotations[i]) {
				if (annotation instanceof Arg) {
					arg = (Arg) annotation;
					args[i] = new ArgReflector(this, type, arg);
				}
			}
		}
		return args;
	}

	/**
	 * @param commitMethod2
	 * @since Iteration4
	 */
	private void analzyeCommitMethod(final Method commitMethod) {

	}

	public String getAfterCommit() {
		return afterCommit;
	}

	public ArgReflector[] getArguments() {
		return Arrays.copyOf(arguments, arguments.length);
	}

	public Method getCommitMethod() {
		return commitMethod;
	}

	public Class<? extends Exception>[] getHandledExceptions() {
		return handledExceptions;
	}

	public ModuleReflector<M> getModule() {
		return parentModule;
	}

	@XmlAttribute("name")
	public String getName() {
		return name;
	}

	@Deprecated
	public String getRequiredPrivilege() {
		if (requiredPrivileges.length > 0) {
			return requiredPrivileges[0];
		}
		return "";
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	public String[] getRequiredPrivileges() {
		return requiredPrivileges;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("template")
	public String getTemplateName() {
		return template;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public boolean hasCommitAction() {
		return commitMethod != null;
	}

	public boolean hasRequiredPrivilegeTarget() {
		return false;
	}

	/**
	 * 
	 * @param obj
	 * @param pathParts
	 * @param requestParams
	 * @throws InvocationTargetException
	 * @since Iteration2
	 */
	public void invokeAction(final Module<? extends RelationManager> obj, final String[] pathParts,
							 final Map<String,String[]> requestParams) throws InvocationTargetException {
		invokeMethod(obj, method, arguments, pathParts, requestParams);
	}

	/**
	 * 
	 * @param obj
	 * @param pathParts
	 * @param requestParams
	 * @throws InvocationTargetException
	 * @since Iteration3
	 */
	public void invokeCommitAction(final Module<? extends RelationManager> obj,
								   final String[] pathParts,
								   final Map<String,String[]> requestParams)
			throws InvocationTargetException {
		if (commitMethod == null) {
			return;
		}
		invokeMethod(obj, commitMethod, commitArguments, pathParts, requestParams);
	}

	/**
	 * 
	 * @param obj
	 * @param method
	 * @param arguments
	 * @param pathParts
	 * @param requestParams
	 * @throws InvocationTargetException
	 * @since Iteration4
	 */
	private void invokeMethod(final Module<? extends RelationManager> obj, final Method method,
							  final ArgReflector[] arguments, final String[] pathParts,
								 final Map<String,String[]> requestParams)
			throws InvocationTargetException {
		Object[] $args = new Object[arguments.length];
		int $i = 0;
		if (arguments[0] == null) {
			$args[$i++] = pathParts;
		}
		for (; $i < arguments.length; $i++) {
			$args[$i] = arguments[$i].forValue(requestParams.get(arguments[$i].getName()));
		}
		try {
			method.invoke(obj, $args);
		} catch (InvocationTargetException $e) {
			throw $e;
		} catch (Exception $e) {
			throw new RuntimeException($e);
		}
	}

	/**
	 * 
	 * @param exc
	 * @return
	 * @since Iteration4
	 */
	public boolean isExceptionExplicitlyHandled(final Class<? extends Exception> exc) {
		for (Class<? extends Exception> exception : handledExceptions) {
			if (exception.equals(exc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param exc
	 * @return
	 * @since Iteration3
	 */
	public boolean isExceptionHandled(final Class<? extends Exception> exc) {
		for (Class<? extends Exception> exception : handledExceptions) {
			if (exception.isAssignableFrom(exc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param exc
	 * @return
	 * @since Iteration3
	 */
	public boolean isExceptionHandled(final Exception exc) {
		return isExceptionHandled(exc.getClass());
	}

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	public boolean isPrivilegeRequired() {
		return requiredPrivileges.length > 0;
	}

	/**
	 * 
	 * @param user
	 * @return
	 * @since Iteration4
	 */
	public boolean isUserAllowedToExecute(final User user) {
		for (String privilege : requiredPrivileges) {
			if (!user.hasPrivilege(privilege)) {
				return false;
			}
		}
		return true;
	}

	@Override
	public String toString() {
		return "Action " + name;
	}

}
