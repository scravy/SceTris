/* C.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

/**
 * Compare functions
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
final public class C {

	private static final Equals equals = new Equals();
	private static final Differs differs = new Differs();
	private static final InstanceOf instanceOf = new InstanceOf();

	/**
	 * 
	 * @param x
	 * @return
	 * @since Iteration4
	 */
	public static Function<Object,Boolean> eq(final Object x) {
		return equals.asFunction(x);
	}

	/**
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> gt(final T c) {
		return new GreaterThan<T>().asFunction(c);
	}

	/**
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> gteq(final T c) {
		return new GreaterThanOrEquals<T>().asFunction(c);
	}

	/**
	 * 
	 * @param clazz
	 * @return
	 * @since Iteration4
	 */
	public static Function<Object,Boolean> is(final Class<?> clazz) {
		return instanceOf.asFunction(clazz);
	}

	/**
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> lt(final T c) {
		return new LessThan<T>().asFunction(c);
	}

	/**
	 * 
	 * @param <T>
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> lteq(final T c) {
		return new LessThanOrEquals<T>().asFunction(c);
	}

	/**
	 * 
	 * @param x
	 * @return
	 * @since Iteration4
	 */
	public static Function<Object,Boolean> neq(final Object x) {
		return differs.asFunction(x);
	}
}
