/* Arrays.java / 1:12:37 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.util.HashSet;
import java.util.NavigableSet;
import java.util.Set;
import java.util.TreeSet;

import de.fu.weave.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class Arrays {
	public static Set<Boolean> asSet(final boolean[] $array) {
		TreeSet<Boolean> $set = new TreeSet<Boolean>();
		for (boolean $i : $array) {
			$set.add($i);
		}
		return $set;
	}

	public static NavigableSet<Double> asSet(final double[] $array) {
		TreeSet<Double> $set = new TreeSet<Double>();
		for (double $i : $array) {
			$set.add($i);
		}
		return $set;
	}

	public static NavigableSet<Integer> asSet(final int[] $array) {
		TreeSet<Integer> $set = new TreeSet<Integer>();
		for (int $i : $array) {
			$set.add($i);
		}
		return $set;
	}

	public static NavigableSet<Long> asSet(final long[] $array) {
		TreeSet<Long> $set = new TreeSet<Long>();
		for (long $i : $array) {
			$set.add($i);
		}
		return $set;
	}

	public static <T> Set<T> asSet(final T[] $array) {
		HashSet<T> $set = new HashSet<T>();
		for (T $o : $array) {
			$set.add($o);
		}
		return $set;
	}
}
