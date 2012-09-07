/* SynchronizedProxyFactoryTest.java / 1:48:20 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import static org.junit.Assert.*;

import java.util.concurrent.Semaphore;

import org.junit.Before;
import org.junit.Test;

import de.fu.junction.SynchronizedProxyFactory;

/**
 *
 */
public class SynchronizedProxyFactoryTest {

	public static interface A {

		int getValue();

		void increment();
	}

	public static class AImpl implements A {

		private int $x = 0;

		@Override
		public int getValue() {
			return $x;
		}

		@Override
		public void increment() {
			$x++;
		}

	}

	public static interface B {
		void doExclusive();
	}

	public static class BImpl implements B {

		@Override
		public void doExclusive() {
			try {
				Thread.sleep(500);
			} catch (InterruptedException $wannaSeeThisHappen) {
				throw new RuntimeException($wannaSeeThisHappen);
			}
		}

	}

	/**
	 * @throws java.lang.Exception
	 */
	@Before
	public void setUp() throws Exception {

	}

	/**
	 * Test method for {@link de.fu.junction.SynchronizedProxyFactory#newInstance(java.lang.Object)}
	 * .
	 */
	@Test
	public void testSequential() {
		A $x = new AImpl();
		A $y = new AImpl();
		assertNotNull($x);

		SynchronizedProxyFactory<A> $proxyFactory = SynchronizedProxyFactory
				.newFactory(A.class);
		A $xProxy = $proxyFactory.newInstance($x);
		A $yProxy = $proxyFactory.newInstance($y);

		$xProxy.increment();

		assertEquals(1, $x.getValue());
		assertEquals(1, $xProxy.getValue());
		assertEquals(0, $y.getValue());
		assertEquals(0, $yProxy.getValue());
	}

	@Test
	public void testThreads() {
		B $x = new BImpl();

		SynchronizedProxyFactory<B> $proxyFactory = SynchronizedProxyFactory
				.newFactory(B.class);
		final B $xProxy = $proxyFactory.newInstance($x);

		final Semaphore $barrier = new Semaphore(0);
		Thread $t1 = new Thread(new Runnable() {
			@Override
			public void run() {
				$xProxy.doExclusive();
				$barrier.release(1);
			}
		});
		Thread $t2 = new Thread(new Runnable() {
			@Override
			public void run() {
				$xProxy.doExclusive();
				$barrier.release(1);
			}
		});
		long $time = System.currentTimeMillis();
		$t1.start();
		$t2.start();
		try {
			$barrier.acquire(2);
		} catch (InterruptedException $wannaSeeThisHappen) {
			throw new RuntimeException($wannaSeeThisHappen);
		}
		assertTrue(System.currentTimeMillis() - $time >= 1000);
	}
}
