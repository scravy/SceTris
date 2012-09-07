/* NumberComparatorTest.java / 3:06:10 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

import de.fu.junction.NumberComparator;

/**
 *
 */
public class NumberComparatorTest {

	@Test
	public void testCompare() throws Exception {
		NumberComparator $c = new NumberComparator();
		Assert.assertTrue($c.compare(7, 3) > 0);
		Assert.assertTrue($c.compare(7, 7.0) == 0);
		Assert.assertTrue($c.compare(3, 41) < 0);
		Assert.assertTrue($c.compare(new Long(7), new BigInteger("7")) == 0);
		Assert.assertTrue($c.compare(new Double(7.2), new BigInteger("7")) > 0);
	}
}
