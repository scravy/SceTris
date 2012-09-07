package de.fu.junction.functional;

import java.lang.reflect.Method;

import de.fu.junction.DynamicInvocationTargetException;
import de.fu.junction.annotation.meta.Author;

/**
 * A generic Function can be used to encapsulate any method into a callable object.
 * 
 * @param <I>
 *            The argument to the function.
 * @param <O>
 *            The result type of the function.
 */
@Author("Julian Fleischer")
public class GenericFunction<I, O> implements Function<I,O> {

	final Method underlyingMethod;
	final Object methodOwner;

	/**
	 * Constructs a generic, one-dimensional function from a given class.
	 * 
	 * 
	 * @param $class
	 *            The class which provides the function.
	 * @param $methodName
	 *            The name of the method.
	 * @param $argType
	 *            The type of the methods first argument.
	 * @throws SecurityException
	 *             If a security violation occured. This is normally never the case.
	 * @throws NoSuchMethodException
	 *             If the methods does not exist.
	 */
	public GenericFunction(final Class<?> $class, final String $methodName, final Class<I> $argType)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = $class.getMethod($methodName, $argType);
		methodOwner = null;
	}

	/**
	 * Constructs a generic, one-dimensional function from a given object.
	 * 
	 * 
	 * @param $obj
	 *            The objects whose class provides the function.
	 * @param $methodName
	 *            The name of the method.
	 * @param $argType
	 *            The type of the methods first argument.
	 * @throws SecurityException
	 *             If a security violation occured. This is normally never the case.
	 * @throws NoSuchMethodException
	 *             If the methods does not exist.
	 */
	public GenericFunction(final Object $obj, final String $methodName, final Class<I> $argType)
			throws SecurityException, NoSuchMethodException {
		underlyingMethod = $obj.getClass().getMethod($methodName, $argType);
		methodOwner = $obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O call(final I $arg) {
		try {
			return (O) underlyingMethod.invoke(methodOwner, $arg);
		} catch (Exception $exc) {
			throw new DynamicInvocationTargetException($exc);
		}
	}
}
