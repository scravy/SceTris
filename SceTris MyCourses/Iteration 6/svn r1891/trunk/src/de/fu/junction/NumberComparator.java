/* NumberComparator.java / 2:47:29 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Comparator;

import org.junit.Assert;
import org.junit.Test;

import de.fu.weave.annotation.meta.Author;

/**
 * A Comparator that compares {@see Number} objects of any special kind.
 */
@Author("Julian Fleischer")
public class NumberComparator implements Comparator<Number> {

	@Override
	public int compare(final Number $num1, final Number $num2) {
		return new BigDecimal($num1.toString()).compareTo(new BigDecimal($num2.toString()));
	}

	@Test
	public void testCompare() throws Exception {
		Assert.assertTrue(compare(7, 3) > 0);
		Assert.assertTrue(compare(7, 7.0) == 0);
		Assert.assertTrue(compare(3, 41) < 0);
		Assert.assertTrue(compare(new Long(7), new BigInteger("7")) == 0);
		Assert.assertTrue(compare(new Double(7.2), new BigInteger("7")) > 0);
	}
}
