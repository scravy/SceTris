package de.fu.junction.functional;

import java.lang.reflect.Method;

import de.fu.junction.DynamicInvocationTargetException;
import de.fu.junction.annotation.meta.Author;

/**
 * A generic Function can be used to encapsulate any method into a callable object.
 * 
 * @param <I>
 *            The first argument the function takes.
 * @param <O>
 *            The second argument the function takes.
 */
@Author("Julian Fleischer")
public class GenericFunction2<I, J, O> extends AbstractFunction2<I,J,O> {

	final Method $underlyingMethod;
	final Object $methodOwner;

	/**
	 * Constructs a generic, two-dimensional function from a given class.
	 * 
	 * @param $class
	 *            The class which provides the function.
	 * @param $methodName
	 *            The name of the method.
	 * @param $argType
	 *            The type of the methods first argument.
	 * @param $argType2
	 *            The type of the methods second argument.
	 * @throws SecurityException
	 *             If a security violation occured. This is normally never the case.
	 * @throws NoSuchMethodException
	 *             If the methods does not exist.
	 */
	public GenericFunction2(final Class<?> $class, final String $methodName,
							final Class<I> $argType, final Class<J> $argType2)
			throws SecurityException, NoSuchMethodException {
		$underlyingMethod = $class.getMethod($methodName, $argType, $argType2);
		$methodOwner = null;
	}

	/**
	 * Constructs a generic, two-dimensional function from a given object.
	 * 
	 * @param $obj
	 *            The objects whose class provides the function.
	 * @param $methodName
	 *            The name of the method.
	 * @param $argType
	 *            The type of the methods first argument.
	 * @param $argType2
	 *            The type of the methods second argument.
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 *             If the method does not exist.
	 */
	public GenericFunction2(final Object $obj, final String $methodName,
							final Class<I> $argType, final Class<J> $argType2)
			throws SecurityException, NoSuchMethodException {
		$underlyingMethod = $obj.getClass().getMethod($methodName, $argType, $argType2);
		$methodOwner = $obj;
	}

	@SuppressWarnings("unchecked")
	@Override
	public O call(final I $arg1, final J $arg2) {
		try {
			return (O) $underlyingMethod.invoke($methodOwner, $arg1, $arg2);
		} catch (Exception $exc) {
			throw new DynamicInvocationTargetException($exc);
		}
	}

}
