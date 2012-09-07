package de.fu.junction.functional;

import de.fu.junction.annotation.meta.Author;

/**
 * A common base class that implements {@link Function2}
 * 
 * @param <I>
 *            The type of the first parameter to this function
 * @param <O>
 *            The type of the second parameter to this function
 */
@Author("Julian Fleischer")
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
