/* NumberComparator.java / 2:47:29 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.math.BigDecimal;
import java.util.Comparator;

import de.fu.junction.annotation.meta.Author;

/**
 * A Comparator that compares {@see Number} objects of any special kind.
 */
@Author("Julian Fleischer")
public class NumberComparator implements Comparator<Number> {

	@Override
	public int compare(final Number $num1, final Number $num2) {
		return new BigDecimal($num1.toString()).compareTo(new BigDecimal($num2.toString()));
	}

}
