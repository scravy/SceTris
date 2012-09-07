/* Sets.java / 1:48:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import de.fu.junction.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class Sets {
	/**
	 * 
	 * @param $array
	 * @return
	 */
	public static Set<Object> makeSet(final byte[] $array) {
		Set<Object> $set = new HashSet<Object>();
		for (Byte $e : $array) {
			$set.add($e);
		}
		return $set;
	}

	/**
	 * 
	 * @param <T>
	 * @param $coll
	 * @return
	 */
	public static Set<Object> makeSet(final Collection<?> $coll) {
		return new HashSet<Object>($coll);
	}

	/**
	 * 
	 * @param $array
	 * @return
	 */
	public static Set<Object> makeSet(final int[] $array) {
		Set<Object> $set = new HashSet<Object>();
		for (Integer $e : $array) {
			$set.add($e);
		}
		return $set;
	}

	/**
	 * 
	 * @param $array
	 * @return
	 */
	public static Set<Object> makeSet(final long[] $array) {
		Set<Object> $set = new HashSet<Object>();
		for (Long $e : $array) {
			$set.add($e);
		}
		return $set;
	}

	/**
	 * 
	 * @param $coll
	 * @return
	 */
	public static Set<Object> makeSet(final Object $coll) {
		if ($coll instanceof int[]) {
			return makeSet((int[]) $coll);
		} else if ($coll instanceof long[]) {
			return makeSet((long[]) $coll);
		} else if ($coll instanceof byte[]) {
			return makeSet((byte[]) $coll);
		} else if ($coll instanceof Object[]) {
			return makeSet((Object[]) $coll);
		} else if ($coll instanceof Collection) {
			return makeSet((Collection<?>) $coll);
		}
		return null;
	}

	/**
	 * 
	 * @param $array
	 * @return
	 */
	public static Set<Object> makeSet(final Object[] $array) {
		Set<Object> $set = new HashSet<Object>();
		for (Object $e : $array) {
			$set.add($e);
		}
		return $set;
	}
}
