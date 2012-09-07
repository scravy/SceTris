package de.fu.junction.functional;

/**
 * 
 * 
 * @param <I>
 * @param <O>
 * @author Julian Fleischer
 * @since Iteration4
 */
public abstract class AbstractFunction2<I, J, O> implements Function2<I,J,O> {

	/**
	 * 
	 * @param arg2
	 * @return
	 * @since Iteration4
	 */
	@Override
	public Function<I,O> asFunction(final J arg2) {
		final AbstractFunction2<I,J,O> parent = this;
		return new Function<I,O>() {
			@Override
			public O call(final I arg) {
				return parent.call(arg, arg2);
			}
		};
	}

}