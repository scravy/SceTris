/* C.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import de.fu.junction.annotation.meta.Author;

/**
 * Compare functions
 */
@Author("Julian Fleischer")
final public class C {

	private static final Equals equals = new Equals();
	private static final Differs differs = new Differs();
	private static final InstanceOf instanceOf = new InstanceOf();

	/**
	 * 
	 * @param $x
	 * @return
	 */
	public static Function<Object,Boolean> eq(final Object $x) {
		return equals.asFunction($x);
	}

	/**
	 * 
	 * @param <T>
	 * @param $c
	 * @return
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> gt(final T $c) {
		return new GreaterThan<T>().asFunction($c);
	}

	/**
	 * 
	 * @param <T>
	 * @param $c
	 * @return
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> gteq(final T $c) {
		return new GreaterThanOrEquals<T>().asFunction($c);
	}

	/**
	 * 
	 * @param $class
	 * @return
	 */
	public static Function<Object,Boolean> is(final Class<?> $class) {
		return instanceOf.asFunction($class);
	}

	/**
	 * 
	 * @param <T>
	 * @param $c
	 * @return
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> lt(final T $c) {
		return new LessThan<T>().asFunction($c);
	}

	/**
	 * 
	 * @param <T>
	 * @param $c
	 * @return
	 */
	public static <T extends Comparable<? super T>> Function<T,Boolean> lteq(final T $c) {
		return new LessThanOrEquals<T>().asFunction($c);
	}

	/**
	 * 
	 * @param $x
	 * @return
	 */
	public static Function<Object,Boolean> neq(final Object $x) {
		return differs.asFunction($x);
	}
}
