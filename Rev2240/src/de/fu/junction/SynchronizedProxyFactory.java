/* SynchronizedProxyFactory.java / 10:59:40 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 *
 */
public class SynchronizedProxyFactory<T> implements ProxyFactory<T> {

	private class SynchronizedProxy implements InvocationHandler {
		final T $object;

		SynchronizedProxy(final T $object) {
			this.$object = $object;
		}

		@Override
		public Object invoke(final Object $proxy, final Method $method, final Object[] $args)
			throws Throwable {
			try {
				synchronized ($object) {
					return $method.invoke($object, $args);
				}
			} catch (InvocationTargetException $exc) {
				throw $exc.getTargetException();
			} catch (Exception $exc) {
				throw new RuntimeException("Unexpected invocation: " + $exc.getMessage());
			}
		}
	}

	public static <T> SynchronizedProxyFactory<T> newFactory(final Class<T> $class) {
		if ($class.isInterface()) return new SynchronizedProxyFactory<T>();
		return null;
	}

	private SynchronizedProxyFactory() {
		super();
	}

	@Override
	@SuppressWarnings("unchecked")
	public T newInstance(final T $object) {
		return (T) Proxy.newProxyInstance(
			$object.getClass().getClassLoader(),
			$object.getClass().getInterfaces(),
			new SynchronizedProxy($object)
				);
	}

}
