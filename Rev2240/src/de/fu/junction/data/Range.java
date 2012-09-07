/* Range.java / 1:53:31 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.data;

import de.fu.junction.annotation.meta.Author;

/**
 * A range of values.
 */
@Author("Julian Fleischer")
@Deprecated
public class Range {
	public final double $lower;
	public final double $upper;
	public final double $step;

	public Range(final double $a, final double $b) {
		$step = 0.1;
		if ($a > $b) {
			$upper = $a;
			$lower = $b;
		} else {
			$upper = $b;
			$lower = $a;
		}
	}

	public Range(final double $a, final double $b, final double $step) {
		this.$step = $step;
		if ($a > $b) {
			$upper = $a;
			$lower = $b;
		} else {
			$upper = $b;
			$lower = $a;
		}
	}

	public Range(final long $a, final long $b) {
		$step = 1;
		if ($a > $b) {
			$upper = $a;
			$lower = $b;
		} else {
			$upper = $b;
			$lower = $a;
		}
	}

	public Range(final long $a, final long $b, final long $step) {
		this.$step = $step;
		if ($a > $b) {
			$upper = $a;
			$lower = $b;
		} else {
			$upper = $b;
			$lower = $a;
		}
	}

	/**
	 * Returns the lower bound of this range.
	 * 
	 * @return The lower bound of this range.
	 */
	public double getLower() {
		return $lower;
	}

	/**
	 * Returns the upper bound of this range.
	 * 
	 * @return The upper bound of this range.
	 */
	public double getUpper() {
		return $upper;
	}
}
