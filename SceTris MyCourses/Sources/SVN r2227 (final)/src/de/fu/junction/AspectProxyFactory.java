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

import de.fu.junction.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class AspectProxyFactory<T> implements ProxyFactory<T> {

	public static abstract class AspectAction implements Callable<Void> {

		@Override
		public Void call(final Object... $args) {

			return null;
		}

		abstract public void call(
			final Object $object,
			final Object $proxy,
			final Method $method,
			final Object... $args);

	}

	class AspectProxy implements InvocationHandler {
		final T $object;
		final Callable<?> $before;
		final Callable<?> $after;

		AspectProxy(final T $object, final Callable<?> $before, final Callable<?> $after) {
			this.$object = $object;
			this.$before = $before;
			this.$after = $after;
		}

		@Override
		public Object invoke(final Object $proxy, final Method $method, final Object[] $args)
			throws Throwable {
			if ($before != null) $before.call($object, $proxy, $method, $args);
			try {
				return $method.invoke($object, $args);
			} catch (InvocationTargetException $exc) {
				throw $exc.getTargetException();
			} catch (Exception $exc) {
				throw new RuntimeException("Unexpected invocation: " + $exc.getMessage());
			} finally {
				if ($after != null) $after.call();
			}
		}
	}

	Callable<?> $before;
	Callable<?> $after;

	@Override
	@SuppressWarnings("unchecked")
	public T newInstance(final T $object) {
		return (T) Proxy.newProxyInstance(
			$object.getClass().getClassLoader(),
			$object.getClass().getInterfaces(),
			new AspectProxy($object, $before, $after));
	}

	public AspectProxyFactory<T> setAfter(final Callable<?> $after) {
		this.$after = $after;
		return this;
	}

	public AspectProxyFactory<T> setBefore(final Callable<?> $before) {
		this.$before = $before;
		return this;
	}

}
