/* D.java / 12:55:20 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import de.fu.junction.functional.Function;
import de.fu.junction.functional.Function2;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Dynamic {
	/**
	 * 
	 * @param clazz
	 * @param method
	 * @return
	 * @since Iteration4
	 */
	public static Callable<?> callback(final Class<?> clazz, final String method) {
		return new Callable<Object>() {
			@Override
			public Object call(final Object... args) {
				Class<?>[] argTypes = new Class<?>[args.length];
				for (int i = 0; i < args.length; i++) {
					argTypes[i] = args[i].getClass();
				}
				try {
					return clazz.getMethod(method, argTypes).invoke(null, args);
				} catch (Exception e) {
					throw new DynamicInvocationTargetException(e);
				}
			}
		};
	}

	/**
	 * 
	 * @param obj
	 * @param method
	 * @return
	 * @since Iteration4
	 */
	public static Callable<?> callback(final Object obj, final String method) {
		final Class<?> clazz = obj.getClass();
		return new Callable<Object>() {
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
		};
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param callable
	 * @param inType
	 * @param outType
	 * @return
	 * @since Iteration4
	 */
	public static <I, O> Function<I,O> function(final Callable<?> callable,
												final Class<I> inType, final Class<O> outType) {
		try {
			return function(callable, "call", inType, outType);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param callback
	 * @param inType
	 * @param outType
	 * @return
	 * @since Iteration4
	 */
	public static <I, O> Function<I,O> function(final Callback callback,
												final Class<I> inType, final Class<O> outType) {
		try {
			return callback.getFunction(inType, outType);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param clazz
	 * @param method
	 * @param inType
	 * @param outType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public static <I, O> Function<I,O> function(final Class<?> clazz, final String method,
												final Class<I> inType, final Class<O> outType) {
		try {
			return new Callback(clazz, method).getFunction(inType, outType);
		} catch (Exception e) {
			System.out.println(e);
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param clazz
	 * @param method
	 * @param inType
	 * @param outType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public static <I, O> Function<I,O> function(final Object obj, final String method,
												final Class<I> inType, final Class<O> outType) {
		try {
			return new Callback(obj, method).getFunction(inType, outType);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param callable
	 * @param inType1
	 * @param inType2
	 * @param outType
	 * @return
	 * @since Iteration4
	 */
	public static <I, J, O> Function2<I,J,O> function2(final Callable<?> callable,
													   final Class<I> inType1, final Class<J> inType2,
													   final Class<O> outType) {
		try {
			return function2(callable, "call", inType1, inType2, outType);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param clazz
	 * @param method
	 * @param inType
	 * @param inType2
	 * @param outType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public static <I, J, O> Function2<I,J,O> function2(final Class<?> clazz, final String method,
													   final Class<I> inType, final Class<J> inType2,
													   final Class<O> outType) {
		try {
			return new Callback(clazz, method).getFunction2(inType, inType2, outType);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param obj
	 * @param method
	 * @param inType
	 * @param inType2
	 * @param outType
	 * @return
	 * @throws SecurityException
	 * @throws NoSuchMethodException
	 * @since Iteration4
	 */
	public static <I, J, O> Function2<I,J,O> function2(final Object obj, final String method,
													   final Class<I> inType, final Class<J> inType2,
													   final Class<O> outType) {
		try {
			return new Callback(obj, method).getFunction2(inType, inType2, outType);
		} catch (Exception e) {
			return null;
		}
	}

}
