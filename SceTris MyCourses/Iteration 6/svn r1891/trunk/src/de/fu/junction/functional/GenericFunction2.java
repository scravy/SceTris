package de.fu.junction.functional;

import java.lang.reflect.Method;

import de.fu.junction.DynamicInvocationTargetException;

/**
 * 
 * 
 * @param <I>
 * @param <O>
 * @author Julian Fleischer
 * @since Iteration4
 */
public class GenericFunction2<I, J, O> extends AbstractFunction2<I,J,O> {

	final Method underlyingMethod;
	final Object methodOwner;

	/**
	 * 
	 * @param clazz
	 * @param methodName
	 * @param argType
	 * @param argType2
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public GenericFunction2(final Class<?> clazz, final String methodName,
							final Class<I> argType, final Class<J> argType2)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = clazz.getMethod(methodName, argType, argType2);
		methodOwner = null;
	}

	/**
	 * 
	 * @param obj
	 * @param methodName
	 * @param argType
	 * @param argType2
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public GenericFunction2(final Object obj, final String methodName,
							final Class<I> argType, final Class<J> argType2)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = obj.getClass().getMethod(methodName, argType, argType2);
		methodOwner = obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O call(final I arg1, final J arg2) {
		try {
			return (O) underlyingMethod.invoke(methodOwner, arg1, arg2);
		} catch (Exception e) {
			throw new DynamicInvocationTargetException(e);
		}
	}

}