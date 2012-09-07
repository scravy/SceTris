package de.fu.sandpit.tests;

import org.junit.*;
import static org.junit.Assert.*;

import java.util.Random;

import de.fu.sandpit.Sample;

public class TestSample {
	@Test
	public void testAverage() {
		Sample s = new Sample();
		s.add(10);
		s.add(20);
		assertTrue(s.getAverage() == 15);
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void testIllegalArgument() {
		Sample s = new Sample();
		s.add(-1);
	}
	
	@Test
	public void testSum() {
		Sample s = new Sample();
		Random r = new Random();
		long sum = 0;
		for (int i = 0; i < 10000000; i++) {
			int n = Math.abs(r.nextInt());
			sum += n;
			s.add(n);
		}
		assertTrue(s.getSum() == sum);
	}
}
