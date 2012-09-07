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

import de.fu.weave.Controller;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.annotation.Action;
import de.fu.weave.annotation.Commit;
import de.fu.weave.annotation.ModuleInfo;
import de.fu.weave.annotation.Requirement;
import de.fu.weave.orm.RelationManager;
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
	public static <M extends Module<? extends RelationManager>> ModuleReflector<M> forClass(final Class<M> clazz)
			throws ModuleException {
		return new ModuleReflector<M>(clazz);
	}

	/**
	 * 
	 * @since Iteration2
	 */
	private final Map<String,de.fu.weave.reflect.ActionReflector<M>> actionMethods;

	/**
	 * {@link #isDatabaseRequired()}
	 * 
	 * @since Iteration2
	 */
	private final boolean isDatabaseRequired;

	/**
	 * {@link #isLoginRequired()}
	 * 
	 * @since Iteration2
	 */
	private final boolean isLoginRequired;

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
	private final ModuleInfo moduleInfo;

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
			moduleInfo = moduleClass.getAnnotation(ModuleInfo.class);
			name = moduleInfo.name();
		} catch (NullPointerException e) {
			throw new ModuleException("Module is not properly annotated (lacks @ModuleInfo)", e);
		}

		boolean dbRequired = false;
		boolean loginRequired = false;
		for (Requirement req : moduleInfo.requires()) {
			if (req.equals(Requirement.DATABASE)) {
				dbRequired = true;
			} else if (req.equals(Requirement.LOGGED_IN)) {
				loginRequired = true;
			}
		}
		isLoginRequired = loginRequired;

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

		Map<String,Method> commitMethods = new TreeMap<String,Method>();
		Map<String,Method> rawActionMethods = new TreeMap<String,Method>();

		Method[] methods = moduleClass.getMethods();
		boolean isHavingDefaultAction = false;
		for (Method method : methods) {
			if (method.isAnnotationPresent(Action.class)) {
				Action annotation = method.getAnnotation(Action.class);
				if (rawActionMethods.containsKey(annotation.name())) {
					throw new ModuleException("Same name for two Actions in " + name);
				}
				if (annotation.name().equals("")) {
					isHavingDefaultAction = true;
					if (!method.getName().equals("_default")) {
						throw new ModuleException(
								"Invalid Module "
										+ name
										+ ": Default action MUST have the name _default, its actual name is "
										+ method.getName());
					}
				}
				rawActionMethods.put(annotation.name(), method);
			} else if (method.isAnnotationPresent(Commit.class)) {
				Commit annotation = method.getAnnotation(Commit.class);
				if (commitMethods.containsKey(annotation.action())) {
					throw new ModuleException("Same name for two CommitActions in " + name);
				}
				commitMethods.put(annotation.action(), method);
			}
		}
		if (!isHavingDefaultAction) {
			throw new ModuleException("Invalid Module " + name
					+ ": Does not feature a default action.");
		}
		for (String name : commitMethods.keySet()) {
			if (!rawActionMethods.containsKey(name)) {
				throw new ModuleException("CommitAction " + name
						+ " refers to an Action that does not exist (in " + name + " / "
						+ moduleClass + ")");
			}
			Commit commitAnnotation = commitMethods.get(name).getAnnotation(Commit.class);
			String afterCommit = commitAnnotation.after();
			if (!rawActionMethods.containsKey(afterCommit)) {
				throw new ModuleException("CommitAction " + name + "refers to an Action "
						+ commitAnnotation.after()
						+ " to be executed after Commit that does not exist (in " + name + " / "
						+ moduleClass + ")");
			}
		}
		actionMethods = new TreeMap<String,de.fu.weave.reflect.ActionReflector<M>>();
		for (String name : rawActionMethods.keySet()) {
			de.fu.weave.reflect.ActionReflector<M> method =
					new de.fu.weave.reflect.ActionReflector<M>(this, rawActionMethods.get(name),
							commitMethods.get(name));
			if (method.isPermissionRequired()) {
				dbRequired = true;
			}
			actionMethods.put(name, method);
		}
		isDatabaseRequired = dbRequired;
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
	public Collection<de.fu.weave.reflect.ActionReflector<M>> getActions() {
		return actionMethods.values();
	}

	/**
	 * Retrieves the Description annotated to this Module.
	 * 
	 * @return A String describing the functionality of the Module.
	 */
	@XmlAttribute("description")
	public String getDescription() {
		return moduleInfo.description();
	}

	/**
	 * Retrieves the Homepage-URL annotated to this Module.
	 * 
	 * @return An URL to the Modules Homepage.
	 */
	@XmlAttribute("homepage")
	public String getHomepage() {
		return moduleInfo.homepage();
	}

	/**
	 * Retrieves the Title of the License under whose terms this Module is to be distributed
	 * 
	 * @return The Title of the License
	 */
	@XmlAttribute("license")
	public String getLicense() {
		return moduleInfo.license();
	}

	/**
	 * Return the Name of the Module as accessible via Web
	 * 
	 * @since Iteration2
	 * @return The Name as a String
	 */
	@XmlAttribute("menu")
	public String getMenu() {
		return moduleInfo.menu();
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
	 * Retrieves the Version annotated to this Module.
	 * 
	 * @since Iteration2
	 * @return The Modules Version as a String.
	 */
	@XmlAttribute("version")
	public String getVersion() {
		return moduleInfo.version();
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
	 * Retrieve whether the Module requires a Connection to the Database or not
	 * 
	 * @return true or false
	 * @since Iteration2
	 */
	@XmlAttribute("dbRequired")
	public boolean isDatabaseRequired() {
		return this.isDatabaseRequired;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("loginRequired")
	public boolean isLoginRequired() {
		return isLoginRequired;
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

}
