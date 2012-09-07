package de.fu.junction.dynamic;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Callable<T> {
	public T call(final Object... arguments);
}