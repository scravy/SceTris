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

import de.fu.junction.data.InvalidSyntaxException;
import de.fu.weave.Form;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.ParamReflector;
import de.fu.weave.User;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Arg;
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

	private final ModuleReflector<M> parentModule;

	private final Method method;

	private final Method commitMethod = null;

	private final String name;

	private final String template;

	private final String[] requiredPrivileges;

	private final Class<? extends Exception>[] handledExceptions;

	private final ParamReflector[] arguments;

	private final ParamReflector[] commitArguments = null;

	private final String afterCommit = null;

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
	 */
	ActionReflector(final ModuleReflector<M> parent, final Method method)
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

		if (!method.isAnnotationPresent(Action.class)) {
			throw new ModuleException(method.getName() + " is not properly annotated");
		}
		Action annotation = method.getAnnotation(Action.class);

		if (annotation.name().equals("") && !method.getName().equals("_default")) {
			this.name = method.getName();
		} else {
			this.name = annotation.name();
		}
		this.template = annotation.template();
		requiredPrivileges = annotation.requiresPrivilege();
		Arrays.sort(requiredPrivileges);
		handledExceptions = annotation.handles();

	}

	/**
	 * @param $method
	 * @throws ModuleException
	 */
	private ParamReflector[] analyzeMethod(final Method $method) throws ModuleException {
		Class<?>[] $types = $method.getParameterTypes();
		Annotation[][] $annotations = $method.getParameterAnnotations();
		ParamReflector[] $args = new ParamReflector[$types.length];
		for (int i = 0; i < $types.length; i++) {
			Arg $arg = null;
			Class<?> $type = $types[i];
			$args[i] = null;
			if (Form.class.isAssignableFrom($type)) {
				// it is a parameter of the type Module.Form
				try {
					@SuppressWarnings("unchecked")
					Class<? extends Form> $class = (Class<? extends Form>) $type;
					$args[i] = FormReflector.newInstance($class);
				} catch (FormReflectorException $e) {
					throw new ModuleException($e);
				}
			} else {
				// it is a parameter annotated using @Arg
				for (Annotation annotation : $annotations[i]) {
					if (annotation instanceof Arg) {
						$arg = (Arg) annotation;
						$args[i] = new ArgReflector(this, $type, $arg);
					}
				}
			}
		}
		return $args;
	}

	public String getAfterCommit() {
		return afterCommit;
	}

	public ParamReflector[] getArguments() {
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

	/**
	 * 
	 * @return
	 */
	public String[] getRequiredPrivileges() {
		return requiredPrivileges;
	}

	/**
	 * 
	 * @return
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
	 * @param $module
	 * @param $method
	 * @param $arguments
	 * @param $pathParts
	 * @param $requestParams
	 * @throws InvocationTargetException
	 */
	private <F extends Form> void invokeMethod(final Module<? extends RelationManager> $module,
		final Method $method,
		final ParamReflector[] $arguments, final String[] $pathParts,
		final Map<String,String[]> $requestParams)
			throws InvocationTargetException {
		Object[] $args = new Object[$arguments.length];
		int $i = 0;
		if (($arguments.length > 0) && ($arguments[0] == null)) {
			$args[$i++] = $pathParts;
		}
		F $validatedForm = null;
		for (; $i < $arguments.length; $i++) {
			if ($arguments[$i] instanceof ArgReflector) {
				ArgReflector $reflector = (ArgReflector) $arguments[$i];
				$args[$i] = $reflector.forValue($requestParams.get($reflector.getName()));
			} else if ($arguments[$i] instanceof FormReflector) {
				@SuppressWarnings("unchecked")
				FormReflector<F> $reflector = (FormReflector<F>) $arguments[$i];
				boolean $committed = false;
				F $form = $reflector.newInstance();
				$form.setRelationManager($module.manager());
				$form.setUser($module.session().getUser());
				$form.setSession($module.session());
				String $formType = null;
				String $formName = null;
				if ($requestParams.containsKey("~formType")) {
					String[] $formTypeArray = $requestParams.get("~formType");
					if ($formTypeArray.length == 1) {
						$formType = $formTypeArray[0];
					}
					if ($requestParams.containsKey("~formName")) {
						String[] $formNameArray = $requestParams.get("~formName");
						if ($formNameArray.length == 1) {
							$formName = $formNameArray[0];
						}
					}
				}
				if ($form.getClass().getCanonicalName().equals($formType) && ($formName != null)) {
					$committed = true;
					for (String $fieldName : $reflector.getFieldNames()) {
						String[] $paramArray = $requestParams.get($formName + '.' + $fieldName);
						try {
							if ($reflector.setField($form, $fieldName, $paramArray)) {
								$reflector.validate($form, $fieldName);
							}
						} catch (InvalidSyntaxException $exc) {
							$form.addMessage($fieldName, $exc.getMalformedValue(), $exc);
						} catch (ValidationException $exc) {
							$form.addMessage($fieldName,
								$reflector.getField($form, $fieldName).toString(), $exc);
						} catch (RequiredFieldMissingException $exc) {
							$form.addMessage($fieldName, "", $exc);
						} catch (RuntimeException $exc) {
							throw $exc;
						} catch (Exception $exc) {
							$form.addMessage($fieldName,
								$reflector.getField($form, $fieldName).toString(), $exc);
						}
					}
				}
				if (!$form.hasMessages() && $committed) {
					$validatedForm = $form;
				}
				$args[$i] = $form;
			}
		}
		if ($validatedForm != null) {
			try {
				if ($validatedForm.commit()) {
					$validatedForm.addMessage("", "success", null);
				}
			} catch (Exception $exc) {
				$validatedForm.addMessage("", "", $exc);
			}
		}
		try {
			$method.invoke($module, $args);
		} catch (InvocationTargetException $e) {
			throw $e;
		} catch (Exception $e) {
			throw new RuntimeException($e);
		}
	}

	/**
	 * 
	 * @param $exc
	 * @return
	 * @since Iteration4
	 */
	public boolean isExceptionExplicitlyHandled(final Class<? extends Exception> $exc) {
		for (Class<? extends Exception> exception : handledExceptions) {
			if (exception.equals($exc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param $exc
	 * @return
	 * @since Iteration3
	 */
	public boolean isExceptionHandled(final Class<? extends Exception> $exc) {
		for (Class<? extends Exception> exception : handledExceptions) {
			if (exception.isAssignableFrom($exc)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 
	 * @param $exc
	 * @return
	 * @since Iteration3
	 */
	public boolean isExceptionHandled(final Exception $exc) {
		return isExceptionHandled($exc.getClass());
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
	 * @param $user
	 * @return
	 */
	public boolean isUserAllowedToExecute(final User $user) {
		for (String privilege : requiredPrivileges) {
			if (!$user.hasPrivilege(privilege)) {
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
