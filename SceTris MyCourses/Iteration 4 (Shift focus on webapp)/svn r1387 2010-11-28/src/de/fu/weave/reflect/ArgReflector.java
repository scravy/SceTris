/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.reflect;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.junction.converter.StringConverterFactory;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.annotation.Arg;

/**
 * 
 * @author Julian Fleischer
 */
public class ArgReflector {
	/**
	 * 
	 */
	final static StringConverterFactory converters = new StringConverterFactory();

	/**
	 * 
	 * {@link #Argument(de.fu.scetris.web.reflect.Action,Class,Arg)}
	 * 
	 * @since Iteration2
	 * 
	 */
	final private ActionReflector<? extends Module<? extends RelationManager>> parent;

	/**
	 * {@link #Argument(de.fu.scetris.web.reflect.Action,Class,Arg)}
	 * 
	 * @since Iteration2
	 */
	final private Class<?> type;

	/**
	 * See the description of {@link Arg}
	 * 
	 * @since Iteration2
	 */
	final private Object defaultValue;

	/**
	 * See {@link Arg#name()}
	 * 
	 * @since Iteration2
	 */
	final private String name;

	/**
	 * @since Iteration3
	 */
	final private boolean isTarget;

	/**
	 * Construct a new {@link Arg}-Reflector
	 * <p>
	 * It is package private (default) since it should only be constructed by {@link Module}.
	 * 
	 * @param actionMethod
	 * @param paramType
	 * @param argument
	 * @throws ModuleException
	 * @since Iteration2
	 */
	ArgReflector(final ActionReflector<? extends Module<? extends RelationManager>> actionMethod,
			final Class<?> paramType, final Arg argument) throws ModuleException {
		parent = actionMethod;
		if (paramType.equals(boolean.class)) {
			type = Boolean.class;
		} else if (paramType.equals(int.class)) {
			type = Integer.class;
		} else if (paramType.equals(long.class)) {
			type = Long.class;
		} else if (paramType.equals(float.class)) {
			type = Float.class;
		} else if (paramType.equals(double.class)) {
			type = Double.class;
		} else {
			type = paramType;
		}
		name = argument.name();
		if (name.length() < 1) {
			throw new ModuleException("Name for Parameter \"" + argument.name()
					+ "\" in ActionMethod \"" + parent.getName() + "\" of Module \""
					+ parent.getModule().getName() + "\" is too short.");
		}
		if (type.equals(String.class) || type.equals(String[].class)) {
			defaultValue = argument.stringDefault();
		} else if (type.equals(Integer[].class)) {
			defaultValue = argument.intDefault();
		} else if (type.equals(Integer.class)) {
			defaultValue = argument.intDefault();
		} else if (type.equals(Long.class)) {
			defaultValue = argument.longDefault();
		} else if (type.equals(Float.class)) {
			defaultValue = argument.floatDefault();
		} else if (type.equals(Double.class)) {
			defaultValue = argument.doubleDefault();
		} else if (type.equals(Boolean.class)) {
			defaultValue = argument.booleanDefault();
		} else {
			throw new ModuleException("Unsupported Type \"" + type.getCanonicalName()
					+ "\"for Parameter \"" + argument.name() + "\" in ActionMethod \""
					+ parent.getName() + "\" of Module \"" + parent.getModule().getName() + "\".");
		}

		isTarget = argument.isTarget();
	}

	/**
	 * 
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	public Object forValue(final String[] value) {
		if (value == null) {
			return getDefaultValue();
		}
		if (value.length < 1) {
			return getDefaultValue();
		} else if (type.equals(String.class)) {
			return value[0];
		} else if (type.equals(String[].class)) {
			return value;
		}
		try {
			Object convertedValue = converters.newConverter(type).convert(value[0]);
			if (convertedValue != null) {
				return convertedValue;
			} else {
				return getDefaultValue();
			}
		} catch (RuntimeException e) {
			throw new RuntimeException("Unsupported Type \"" + type.getCanonicalName()
					+ "\"for Parameter \"" + name + "\" in ActionMethod \"" + parent.getName()
					+ "\" of Module \"" + parent.getModule().getName()
					+ "\". This shouldn’t happend since it was already checked in a Constructor.", e);
		}
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	public ActionReflector<? extends Module<? extends RelationManager>> getAction() {
		return parent;
	}

	/**
	 * 
	 * @return
	 */
	public Object getDefaultValue() {
		return defaultValue;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return name;
	}

	public ActionReflector<? extends Module<? extends RelationManager>> getParent() {
		return parent;
	}

	/**
	 * 
	 * @return
	 */
	public Class<?> getType() {
		return type;
	}

	public boolean isTarget() {
		return isTarget;
	}

	/**
	 * 
	 * @return
	 */
	@Override
	public String toString() {
		return type.getCanonicalName() + " " + name + "() default " + defaultValue;
	}
}
