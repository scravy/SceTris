package de.fu.junction.functional;

import java.lang.reflect.Method;

import de.fu.junction.dynamic.DynamicInvocationTargetException;

/**
 * 
 * 
 * @param <I>
 * @param <O>
 * @author Julian Fleischer
 * @since Iteration4
 */
public class GenericFunction<I, O> implements Function<I,O> {

	final Method underlyingMethod;
	final Object methodOwner;

	public GenericFunction(final Class<?> clazz, final String methodName, final Class<I> argType)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = clazz.getMethod(methodName, argType);
		methodOwner = null;
	}

	public GenericFunction(final Object obj, final String methodName, final Class<I> argType)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = obj.getClass().getMethod(methodName, argType);
		methodOwner = obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O call(final I arg) {
		try {
			return (O) underlyingMethod.invoke(methodOwner, arg);
		} catch (Exception e) {
			throw new DynamicInvocationTargetException(e);
		}
	}
}