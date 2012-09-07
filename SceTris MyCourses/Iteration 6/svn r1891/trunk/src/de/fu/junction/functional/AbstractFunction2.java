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

	@Override
	public Function<I,O> asFunction(final J $arg2) {
		final AbstractFunction2<I,J,O> $parent = this;
		return new Function<I,O>() {
			@Override
			public O call(final I $arg) {
				return $parent.call($arg, $arg2);
			}
		};
	}

	@Override
	public Function<J,O> asFunctionFlipped(final I $arg) {
		final AbstractFunction2<I,J,O> $parent = this;
		return new Function<J,O>() {
			@Override
			public O call(final J $arg2) {
				return $parent.call($arg, $arg2);
			}
		};
	}

}
