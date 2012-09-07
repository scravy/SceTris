/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Controller;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.annotation.Action;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * This class is a Reflection-Class and reflects {@link de.fu.weave.Module}.
 * <p>
 * It offers information about a Module, extracted from its Source and its Annotations.
 * 
 * @param <M>
 *            The Name of the Class of the Model that this Reflector actually is about. That class
 *            MUST implement {@link de.fu.weave.Module}.
 * @author Julian Fleischer
 * @since Iteration2
 */
public class ModuleReflector<M extends Module<? extends RelationManager>> {
	/**
	 * Returns a Reflector for a Module given by its Class instance
	 * 
	 * @param <M>
	 *            The Classname of the Module a Reflector is to be created for
	 * @param clazz
	 *            The instance of the Class to reflect as a Module (can be retrieved via
	 *            <tt>NameOfModule.class</tt>)
	 * @return A new Reflector (instance of this class) reflecting the in Module declared in clazz
	 * @since Iteration2
	 * @throws ModuleException
	 *             If the Module itself is invalid
	 */
	public static <M extends Module<? extends RelationManager>> ModuleReflector<M> forClass(
		final Class<M> clazz)
			throws ModuleException {
		return new ModuleReflector<M>(clazz);
	}

	/**
	 * 
	 * @since Iteration2
	 */
	private final Map<String,de.fu.weave.reflect.ActionReflector<M>> actionMethods;

	/**
	 * {@link #getType()}
	 * 
	 * @since Iteration2
	 */
	private final Class<M> moduleClass;

	/**
	 * 
	 * @since Iteration2
	 */
	private final String name;

	/**
	 * 
	 * @since Iteration2
	 */
	private final Constructor<M> constructor;

	/**
	 * The one and only constructor.
	 * <p>
	 * It will automatically check the Module that this Reflector will be about for validity, that
	 * includes (but is not limited to)
	 * <ol>
	 * <li>The Module features a proper constructur, that is: a public Constructor like the
	 * following: <tt>public Constructor(Controller parent)</tt></li>
	 * <li>No {@link de.fu.weave.annotation.Action} occurs twice</li>
	 * <li>The Module features a default action (whose name is the empty string)</li>
	 * <li>The Method that is annotated to be the default action is named <tt>_default</tt></li>
	 * </ol>
	 * 
	 * @param moduleClass
	 *            See above.
	 * @throws ModuleException
	 *             If the Module itself is invalid
	 */
	public ModuleReflector(final Class<M> moduleClass) throws ModuleException {
		try {
			name = moduleClass.getSimpleName();
		} catch (NullPointerException e) {
			throw new ModuleException("Module is not properly annotated (lacks @ModuleInfo)", e);
		}

		try {
			Constructor<M> theConstructor = null;
			for (Constructor<?> constructor : moduleClass.getConstructors()) {
				Class<?>[] parameters = constructor.getParameterTypes();
				if (parameters.length == 1) {
					if (Controller.class.isAssignableFrom(parameters[0])) {
						@SuppressWarnings("unchecked")
						// XXX: Quite ugly. Dont know better.
						Constructor<M> tmpConstructor = (Constructor<M>) constructor;
						theConstructor = tmpConstructor;
					}
				}
			}
			if (theConstructor == null) {
				throw new NoSuchMethodException();
			}
			if (!Modifier.isPublic(theConstructor.getModifiers())) {
				throw new ModuleException("Invalid Module " + name
						+ ": Constructor is not accessible");
			}
			this.constructor = theConstructor;
		} catch (NoSuchMethodException e) {
			throw new ModuleException("Invalid Module (" + name
					+ "): does not feature proper constructor.");
		}

		this.moduleClass = moduleClass;

		Map<String,Method> rawActionMethods = new TreeMap<String,Method>();

		Method[] methods = moduleClass.getMethods();
		boolean isHavingDefaultAction = false;
		for (Method method : methods) {
			if (method.isAnnotationPresent(Action.class)) {
				Action annotation = method.getAnnotation(Action.class);
				if (annotation.name().equals("")) {
					if (method.getName().equals("_default")) {
						isHavingDefaultAction = true;
					}
				}
				rawActionMethods.put(method.toString(), method);
			}
		}
		if (!isHavingDefaultAction) {
			throw new ModuleException("Invalid Module " + name
					+ ": Does not feature a default action.");
		}
		actionMethods = new TreeMap<String,ActionReflector<M>>();
		for (String name : rawActionMethods.keySet()) {
			ActionReflector<M> method = new ActionReflector<M>(this, rawActionMethods.get(name));
			actionMethods.put(method.getName(), method);
		}
	}

	/**
	 * Retrieve information about a certain Action that the reflected Module provides
	 * 
	 * @param name
	 *            The Name of the Action as it is accessible via Web
	 * @return The Reflector of the Action of null if the Action does not exist
	 */
	public de.fu.weave.reflect.ActionReflector<M> getAction(final String name) {
		return actionMethods.get(name);
	}

	/**
	 * Retrieve information about the Actions that the reflected Module provides
	 * 
	 * @return A Collection of Action Reflectors
	 */
	@XmlCollection("actions")
	public Collection<ActionReflector<M>> getActions() {
		return actionMethods.values();
	}

	/**
	 * Return the Name of the Module as accessible via Web
	 * 
	 * @since Iteration2
	 * @return The Name as a String
	 */
	@XmlAttribute("name")
	public String getName() {
		return name;
	}

	/**
	 * Return the Cannonical Class Name of the Module
	 * 
	 * @since Iteration2
	 * @return The Class Name as a String
	 */
	@XmlAttribute("class")
	public String getQualifiedName() {
		return moduleClass.getCanonicalName();
	}

	/**
	 * The Class Instance of the Module M, where M is the Type Parameter of this Reflector
	 * 
	 * @since Iteration2
	 * @return The Class Instance
	 */
	public Class<M> getType() {
		return moduleClass;
	}

	/**
	 * Check if a Module features a certain action.
	 * 
	 * @param name
	 *            The name of the action (See {@link de.fu.weave.reflect.ActionReflector#getName()}
	 *            ).
	 * @return true or false - if the module provides the desired action or not
	 * @since Iteration2
	 */
	public boolean hasAction(final String name) {
		return actionMethods.containsKey(name);
	}

	/**
	 * 
	 * @param parent
	 *            A Module always belongs to a Controller, this is that Controller
	 * @return A newly created instance of the Module this Reflector is about
	 * @throws SecurityException
	 *             If the current SecurityContext does not allow loading the class
	 * @throws InvocationTargetException
	 *             If an Exception inside the newly created Module occurs (something in its
	 *             constructor goes wrong)
	 */
	public M newInstance(final Controller<? extends RelationManager> parent)
			throws SecurityException, InvocationTargetException {
		try {
			return constructor.newInstance(parent);
		} catch (InstantiationException e) {
			throw new RuntimeException("Module can not be instantiated (Module " + this.name
					+ "). This means that the Module is abstract or something like that."
					+ "This should not happend since it was already checked "
					+ "(or should have been) when constructing this class ("
					+ this.getQualifiedName() + "). Something has gone terribly wrong!");
		} catch (IllegalAccessException e) {
			throw new RuntimeException("Module can not be accessed (Module " + this.name
					+ "). This means that the Module is not public or something like that."
					+ "This should not happend since it was already checked "
					+ "(or should have been) when constructing this class ("
					+ this.getQualifiedName() + "). Something has gone terribly wrong!");
		}
	}

	@Override
	public String toString() {
		return "Module " + name;
	}
}
