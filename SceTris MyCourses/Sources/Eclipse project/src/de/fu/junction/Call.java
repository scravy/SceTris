/* Call.java / 12:12:25 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import static de.fu.junction.Dynamic.*;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Call<ReturnType> implements Callable<ReturnType> {
	/**
	 * 
	 */
	private final Callable<ReturnType> callable;

	/**
	 * 
	 */
	private final Object[] args;

	/**
	 * 
	 * @param callable
	 * @param args
	 * @since Iteration4
	 */
	public Call(final Callable<ReturnType> callable, final Object... args) {
		this.callable = callable;
		this.args = args;
	}

	/**
	 * 
	 * @param clazz
	 * @param method
	 * @param args
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public Call(final Class<?> clazz, final String method, final Object... args) {
		callable = (Callable<ReturnType>) callback(clazz, method);
		this.args = args;
	}

	/**
	 * 
	 * @param obj
	 * @param method
	 * @param args
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public Call(final Object obj, final String method, final Object... args) {
		callable = (Callable<ReturnType>) callback(obj, method);
		this.args = args;
	}

	@Override
	public ReturnType call(final Object... arguments) {
		for (int i = 0; (i < args.length) && (i < arguments.length); i++) {
			args[i] = arguments[i];
		}
		return callable.call(args);
	}
}
