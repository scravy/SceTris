/* T.java / 5:10:35 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.math.BigInteger;

/**
 * Type juggling.
 */
public class Types {
	public final static NumberComparator $numberComparator = new NumberComparator();

	public static boolean isArray(final Class<?> $class) {
		return $class.isArray();
	}

	public static boolean isArray(final Object $obj) {
		return $obj.getClass().isArray();
	}

	public static boolean isBoolean(final boolean $b) {
		return true;
	}

	public static boolean isBoolean(final Class<?> $class) {
		return Boolean.class.isAssignableFrom($class) || boolean.class.isAssignableFrom($class);
	}

	public static boolean isBoolean(final Object $b) {
		if ($b == null) {
			return false;
		}
		return $b instanceof Boolean;
	}

	/**
	 * Checks whether a value is empty
	 * <p>
	 * If you pass a Number (that is, a primitive type or something that implements {@see Number}),
	 * this method will check whether it is equal to zero or not.
	 * <p>
	 * If you pass a String, this method will check whether it is the empty string (and return true)
	 * or not (and return false).
	 * <p>
	 * If you pass null if will return true.
	 * 
	 * @param $obj
	 * @return
	 */
	public static boolean isEmpty(final Object $obj) {
		if ($obj == null) {
			return true;
		}
		if ($obj instanceof Number) {
			return $numberComparator.compare((Number) $obj, 0) == 0;
		}
		if ($obj instanceof String) {
			return $obj.equals("");
		}
		return false;

	}

	public static boolean isFloating(final Class<?> $class) {
		return Double.class.isAssignableFrom($class)
				|| Float.class.isAssignableFrom($class)
				|| double.class.isAssignableFrom($class)
				|| float.class.isAssignableFrom($class);
	}

	public static boolean isFloating(final Object $obj) {
		return ($obj instanceof Double)
				|| ($obj instanceof Float);
	}

	public static boolean isIntegral(final Class<?> $class) {
		return Integer.class.isAssignableFrom($class)
				|| Long.class.isAssignableFrom($class)
				|| BigInteger.class.isAssignableFrom($class)
				|| Short.class.isAssignableFrom($class)
				|| Byte.class.isAssignableFrom($class)
				|| int.class.isAssignableFrom($class)
				|| long.class.isAssignableFrom($class)
				|| short.class.isAssignableFrom($class)
				|| byte.class.isAssignableFrom($class);
	}

	public static boolean isIntegral(final Object $num) {
		return ($num instanceof Long)
				|| ($num instanceof Integer)
				|| ($num instanceof BigInteger)
				|| ($num instanceof Short)
				|| ($num instanceof Byte);
	}

	public static boolean isNatural(final double $n) {
		return Math.floor($n) == $n;
	}

	public static boolean isNatural(final long $n) {
		return true;
	}

	public static boolean isNatural(final Number $b) {
		return $b.doubleValue() == $b.longValue();
	}

	/**
	 * Checks whether the given value is null or not.
	 * <p>
	 * Thanks to autoboxing you can do so with primitive data-types, too. This function is needed in
	 * automatically generated code, where it may be unknown whether the type is a primitive one or
	 * not (primitive types can not be compared to null using ==).
	 * 
	 * @param obj
	 * @return
	 */
	public static boolean isNull(final Object $obj) {
		return $obj == null;
	}

	public static boolean isNumber(final Class<?> $class) {
		if (isPrimitive($class)) {
			return !$class.equals(char.class) && !$class.equals(boolean.class);
		}
		return Number.class.isAssignableFrom($class);
	}

	public static boolean isNumber(final Object $obj) {
		if ($obj == null) {
			return false;
		}
		return $obj instanceof Number;
	}

	/**
	 * 
	 */
	public static boolean isPrimitive(final Class<?> $class) {
		return !(Object.class.isAssignableFrom($class));
	}

	public static boolean isString(final Class<?> $class) {
		return $class.equals(String.class);
	}

	public static boolean isString(final Object $obj) {
		return String.class.isAssignableFrom($obj.getClass());
	}
}
