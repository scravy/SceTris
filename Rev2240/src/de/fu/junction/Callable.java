package de.fu.junction;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public interface Callable<ReturnType> {
	public ReturnType call(final Object... arguments);
}
