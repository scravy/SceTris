/* Callable.java / 12:39:49 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.dynamic;

import java.lang.reflect.Method;

import de.fu.junction.functional.Function;
import de.fu.junction.functional.Function2;
import de.fu.junction.functional.GenericFunction;
import de.fu.junction.functional.GenericFunction2;

/**
 * A Callback allows for calling a function which is looked up by its name at runtime (that is: when
 * issuing the call).
 * <p>
 * A Callback is a reference to a method using a name. This implies that if there is more than one
 * method with that name the actual method is looked up by its name and type arguments when calling
 * the callback (using call()).
 * <p>
 * Future thoughts: A callback may respect an annotation like @Default. However, this might impose
 * to heavy load on the runtime system, a solution using the approach Projekt Lombok has taken might
 * be more effective.
 * <p>
 * The main difference
 * 
 * @param <T>
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Callback implements Callable<Object> {

	/**
	 * 
	 */
	private final Object obj;

	/**
	 * 
	 */
	private final String method;

	/**
	 * 
	 */
	private final Class<?> clazz;

	/**
	 * 
	 * @param clazz
	 * @param method
	 * @since Iteration4
	 */
	public Callback(final Class<?> clazz, final String method) {
		obj = null;
		this.clazz = clazz;
		this.method = method;
	}

	/**
	 * 
	 * @param obj
	 * @param method
	 * @since Iteration4
	 */
	public Callback(final Object obj, final String method) {
		this.obj = obj;
		this.method = method;
		clazz = obj.getClass();
	}

	/**
	 * 
	 */
	@Override
	public Object call(final Object... args) {
		Class<?>[] argTypes = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			argTypes[i] = args[i].getClass();
		}
		try {
			return clazz.getMethod(method, argTypes).invoke(obj, args);
		} catch (Exception e) {
			throw new DynamicInvocationTargetException(e);
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param intype
	 * @param outtype
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <I, O> Function<I,O> getFunction(final Class<I> intype, final Class<O> outtype)
			throws SecurityException, NoSuchMethodException {
		if (obj == null) {
			return new GenericFunction<I,O>(clazz, method, intype);
		}
		return new GenericFunction<I,O>(obj, method, intype);
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param argType
	 * @param arg2
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <I, J> Function<I,?> getFunction(final Class<I> argType, final J arg2)
			throws SecurityException, NoSuchMethodException {
		Method m = clazz.getMethod(method, argType, arg2.getClass());
		return getFunction(argType, arg2, m.getReturnType());
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param argType
	 * @param arg2
	 * @param returnType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <I, J, O> Function<I,O> getFunction(final Class<I> argType, final J arg2,
											   final Class<O> returnType)
			throws SecurityException, NoSuchMethodException {
		@SuppressWarnings("unchecked")
		Function2<I,J,O> f2 = (Function2<I,J,O>) getFunction2(argType, arg2.getClass(), returnType);
		return f2.asFunction(arg2);
	}

	/**
	 * 
	 * @param <T>
	 * @param argType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <T> Function<T,?> getFunction(final Class<T> argType)
			throws SecurityException, NoSuchMethodException {
		Method m = clazz.getMethod(method, argType);
		return getFunction(argType, m.getReturnType());
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param argType
	 * @param argType2
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <I, J> Function2<I,J,?> getFunction2(final Class<I> argType, final Class<J> argType2)
			throws SecurityException, NoSuchMethodException {
		Method m = clazz.getMethod(method, argType, argType2);
		return getFunction2(argType, argType2, m.getReturnType());
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param intype
	 * @param intype2
	 * @param outtype
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public <I, J, O> Function2<I,J,O> getFunction2(final Class<I> intype, final Class<J> intype2,
												   final Class<O> outtype)
			throws SecurityException, NoSuchMethodException {
		if (obj == null) {
			return new GenericFunction2<I,J,O>(clazz, method, intype, intype2);
		}
		return new GenericFunction2<I,J,O>(obj, method, intype, intype2);
	}
}
