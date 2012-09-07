/* F.java / 12:59:45 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.functional;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import de.fu.junction.Callable;
import de.fu.junction.annotation.meta.Author;

/**
 * Collection of static methods for functional programming in Java.
 */
@Author("Julian Fleischer")
final public class F {

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param string
	 * @return
	 */
	public static <T> boolean all(final Function<? super Character,Boolean> f, final String string) {
		for (int i = 0; i < string.length(); i++)
			if (!f.call(string.charAt(i))) return false;
		return true;
	}

	/**
	 * Checks if all elements in the Iterable object satisfy the condition imposed by the specified
	 * Function.
	 * 
	 * @param <T>
	 * @param f
	 *            The boolean function which checks the condition
	 * @param iterable
	 * @return true if the invocation of f on all elements equals true, false otherwise
	 */
	public static <T> boolean all(final Function<? super T,Boolean> f, final Iterable<T> iterable) {
		for (T elem : iterable)
			if (!f.call(elem)) return false;
		return true;
	}

	/**
	 * Checks if all elements in the Iterable object satisfy the condition imposed by the specified
	 * Function.
	 * 
	 * @param <T>
	 * @param f
	 *            The boolean function which checks the condition
	 * @param array
	 * @return true if the invocation of f on all elements equals true, false otherwise
	 * @since Iteration4
	 */
	public static <T> boolean all(final Function<? super T,Boolean> f, final T[] array) {
		for (T elem : array)
			if (!f.call(elem)) return false;
		return true;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param array
	 * @return
	 */
	public static <T> boolean any(final Function<? super Character,Boolean> f, final String string) {
		for (int i = 0; i < string.length(); i++)
			if (f.call(string.charAt(i))) return true;
		return false;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param array
	 * @return
	 */
	public static <T> boolean any(final Function<? super T,Boolean> f, final Iterable<T> array) {
		for (T elem : array)
			if (f.call(elem)) return true;
		return false;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param array
	 * @return
	 */
	public static <T> boolean any(final Function<? super T,Boolean> f, final T[] array) {
		for (T elem : array)
			if (f.call(elem)) return true;
		return false;
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> drain(final Function<? super T,Boolean> f, final Iterable<T> c) {
		ArrayList<T> result = new ArrayList<T>();
		for (T t : c)
			if (!f.call(t)) result.add(t);
		return result;
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 */
	public static <T> List<T> drain(final Function<? super T,Boolean> f, final List<T> c) {
		ArrayList<T> result = new ArrayList<T>(c.size());
		for (T t : c)
			if (!f.call(t)) result.add(t);
		return result;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param array
	 * @return
	 */
	public static <T> T[] drain(final Function<? super T,Boolean> f, final T[] array) {
		if (array.length == 0) return array;
		ArrayList<T> result = new ArrayList<T>(array.length);
		for (int i = 0; i < array.length; i++)
			if (!f.call(array[i])) result.add(array[i]);
		@SuppressWarnings("unchecked")
		T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(), result.size());
		for (int i = 0; i < result.size(); i++)
			resultArray[i] = result.get(i);
		return resultArray;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param c
	 */
	public static <T> void drainInPlace(final Function<? super T,Boolean> f, final Iterable<T> c) {
		Iterator<T> it = c.iterator();
		for (T t = it.next(); it.hasNext();)
			if (f.call(t)) it.remove();
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param string
	 * @return
	 * @since Iteration4
	 */
	public static <T> String dropWhile(final Function<? super Character,Boolean> f, final String string) {
		int i;
		for (i = 0; i < string.length(); i++)
			if (!f.call(string.charAt(i))) break;
		return string.substring(i, string.length());
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param iterable
	 * @return
	 * @since Iteration4
	 */
	public static <T> List<T> dropWhile(final Function<? super T,Boolean> f, final Iterable<T> iterable) {
		List<T> list = new LinkedList<T>();
		Iterator<T> it = iterable.iterator();
		T t;
		while (it.hasNext()) {
			t = it.next();
			if (!f.call(t)) {
				list.add(t);
				break;
			}
		}
		while (it.hasNext())
			list.add(it.next());
		return list;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param array
	 * @return
	 * @since Iteration4
	 */
	public static <T> T[] dropWhile(final Function<? super T,Boolean> f, final T[] array) {
		if (array.length == 0) return array;
		int i = 0;
		for (T elem : array) {
			if (!f.call(elem)) break;
			i++;
		}
		@SuppressWarnings("unchecked")
		T[] resultArray = (T[]) Array.newInstance(array[0].getClass(), i);
		System.arraycopy(array, i, resultArray, 0, array.length - i);
		return resultArray;
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] filter(final Function<? super T,Boolean> f, final Iterable<T> c) {
		if (c.iterator().hasNext()) {
			ArrayList<T> result = new ArrayList<T>();
			for (T t : c)
				if (f.call(t)) result.add(t);
			T[] resultArray = (T[]) Array.newInstance(result.get(0).getClass(), result.size());
			for (int i = 0; i < result.size(); i++)
				resultArray[i] = result.get(i);
			return resultArray;
		}
		return (T[]) new Object[0];
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 * @since Iteration4
	 */
	public static <T> List<T> filter(final Function<? super T,Boolean> f, final List<T> list) {
		ArrayList<T> result = new ArrayList<T>(list.size());
		for (T t : list)
			if (f.call(t)) result.add(t);
		return result;
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 * @since Iteration4
	 */
	public static <T> T[] filter(final Function<? super T,Boolean> f, final T[] array) {
		if (array.length == 0) return array;
		ArrayList<T> result = new ArrayList<T>(array.length);
		for (int i = 0; i < array.length; i++)
			if (f.call(array[i])) result.add(array[i]);
		@SuppressWarnings("unchecked")
		T[] resultArray = (T[]) Array.newInstance(array.getClass().getComponentType(), result.size());
		for (int i = 0; i < result.size(); i++)
			resultArray[i] = result.get(i);
		return resultArray;
	}

	/**
	 * 
	 * @param <T>
	 * @return
	 * @throws UnsupportedOperationException
	 *             If the Iterables Iterator does not support remove()
	 * @since Iteration4
	 */
	public static <T> void filterInPlace(final Function<? super T,Boolean> f, final Iterable<T> iterable) {
		Iterator<T> it = iterable.iterator();
		while (it.hasNext()) {
			T t = it.next();
			if (!f.call(t)) it.remove();
		}
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param f
	 * @param current
	 * @param iterable
	 * @return
	 * @since Iteration4
	 */
	public static <I, J> I foldl(final Function2<I,J,I> f, I current, final Iterable<J> iterable) {
		for (J elem : iterable)
			current = f.call(current, elem);
		return current;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param f
	 * @param current
	 * @param arr
	 * @return
	 * @since Iteration4
	 */
	public static <I, J> I foldl(final Function2<I,J,I> f, I current, final J[] arr) {
		for (int i = 0; i < arr.length; i++)
			current = f.call(current, arr[i]);
		return current;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param f
	 * @param current
	 * @param arr
	 * @return
	 * @since Iteration4
	 */
	public static <I, J> J foldr(final Function2<I,J,J> f, J current, final I[] arr) {
		for (int i = 0; i < arr.length; i++)
			current = f.call(arr[i], current);
		return current;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param f
	 * @param current
	 * @param iterable
	 * @return
	 * @since Iteration4
	 */
	public static <I, J> J foldr(final Function2<I,J,J> f, J current, final Iterable<I> iterable) {
		for (I elem : iterable)
			current = f.call(elem, current);
		return current;
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param <L>
	 * @param callback
	 * @param c
	 * @param target
	 * @param additionalArgs
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <I, O, L extends List<O>> L map(final Callable<? super I> callback,
													 final I[] c,
													 final L target,
													 final Object... additionalArgs) {
		Object[] args = new Object[additionalArgs.length + 1];
		for (int i = 0; i < additionalArgs.length; i++)
			args[i + 1] = additionalArgs[i];
		for (I elem : c) {
			args[0] = elem;
			target.add((O) callback.call(args));
		}
		return target;
	}

	/**
	 * Applies the functional map function onto an already existing list.
	 * 
	 * @param <I>
	 * @param <O>
	 * @param <L>
	 * @param callback
	 * @param c
	 * @param target
	 * @param additionalArgs
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <I, O, L extends List<O>> L map(final Callable<? super I> callback,
													 final Iterable<I> c,
													 final L target,
													 final Object... additionalArgs) {
		Object[] args = new Object[additionalArgs.length + 1];
		for (int i = 0; i < additionalArgs.length; i++)
			args[i + 1] = additionalArgs[i];
		for (I elem : c) {
			args[0] = elem;
			target.add((O) callback.call(args));
		}
		return target;
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param function
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <I, O> O[] map(final Function<? super I,O> function, final I[] c) {
		if (c.length > 0) {
			ArrayList<O> result = new ArrayList<O>(c.length);
			for (I elem : c)
				result.add(function.call(elem));
			O[] array = (O[]) Array.newInstance(result.get(0).getClass(), result.size());
			for (int i = 0; i < result.size(); i++)
				array[i] = result.get(i);
			return array;
		}
		return (O[]) new Object[0];
	}

	/**
	 * 
	 * @param <I>
	 * @param <O>
	 * @param function
	 * @param c
	 * @return
	 * @since Iteration4
	 */
	public static <I, O> List<O> map(final Function<? super I,O> function, final Iterable<I> c) {
		List<O> result = new LinkedList<O>();
		for (I elem : c)
			result.add(function.call(elem));
		return result;
	}

	/**
	 * 
	 * @param <T>
	 * @param callable
	 * @param c
	 * @param additionalArgs
	 * @since Iteration4
	 */
	public static <T> void mapInPlace(final Callable<T> callable, final T[] c,
									  final Object... additionalArgs) {
		Object[] args = new Object[additionalArgs.length + 1];
		for (int i = 0; i < additionalArgs.length; i++)
			args[i + 1] = additionalArgs[i];
		for (int i = 0; i < c.length; i++) {
			args[0] = c[i];
			c[i] = callable.call(args);
		}
	}

	/**
	 * 
	 * @param <X>
	 * @param <X>
	 * @param function
	 * @param c
	 * @since Iteration4
	 */
	public static <T> void mapInPlace(final Function<? super T,T> function, final T[] c) {
		for (int i = 0; i < c.length; i++)
			c[i] = function.call(c[i]);
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param string
	 * @return
	 * @since Iteration4
	 */
	public static <T> String takeWhile(final Function<? super Character,Boolean> f, final String string) {
		int i;
		for (i = 0; i < string.length(); i++)
			if (!f.call(string.charAt(i))) break;
		return string.substring(0, i);
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @param iterable
	 * @return
	 * @since Iteration4
	 */
	public static <T> List<T> takeWhile(final Function<? super T,Boolean> f, final Iterable<T> iterable) {
		List<T> list = new LinkedList<T>();
		Iterator<T> it = iterable.iterator();
		T t;
		while (it.hasNext()) {
			t = it.next();
			if (!f.call(t)) break;
			list.add(t);
		}
		return list;
	}

	/**
	 * 
	 * @param <T>
	 * @param f
	 * @return
	 * @since Iteration4
	 */
	public static <T> T[] takeWhile(final Function<? super T,Boolean> f, final T[] array) {
		if (array.length == 0) return array;
		int i = 0;
		for (T elem : array) {
			if (!f.call(elem)) break;
			i++;
		}
		@SuppressWarnings("unchecked")
		T[] resultArray = (T[]) Array.newInstance(array[0].getClass(), i);
		System.arraycopy(array, 0, resultArray, 0, i);
		return resultArray;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param f
	 * @param a
	 * @param b
	 * @return
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <I, J, O> O[] zipWith(final Function2<I,J,O> f, final I[] a, final J[] b) {
		int size = Math.min(a.length, b.length);
		if (size == 0) return (O[]) new Object[0];
		O fst = f.call(a[0], b[0]);
		O[] resultArray = (O[]) Array.newInstance(fst.getClass(), size);
		resultArray[0] = fst;
		for (int i = 1; i < size; i++)
			resultArray[i] = f.call(a[i], b[i]);
		return resultArray;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param f
	 * @param a
	 * @param b
	 * @return
	 * @since Iteration4
	 */
	@SuppressWarnings("unchecked")
	public static <I, J, O> O[] zipWith(final Function2<I,J,O> f, final I[] a, final List<J> b) {
		int size = Math.min(a.length, b.size());
		if (size == 0) return (O[]) new Object[0];
		O fst = f.call(a[0], b.get(0));
		O[] resultArray = (O[]) Array.newInstance(fst.getClass(), size);
		resultArray[0] = fst;
		for (int i = 1; i < size; i++)
			resultArray[i] = f.call(a[i], b.get(i));
		return resultArray;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param f
	 * @param a
	 * @param b
	 * @return
	 * @throws IllegalArgumentException
	 * @since Iteration4
	 */
	public static <I, J, O> List<O> zipWith(final Function2<I,J,O> f, final List<I> a, final J[] b)
			throws IllegalArgumentException {
		int size = Math.min(a.size(), b.length);
		List<O> result = new ArrayList<O>(size);
		for (int i = 0; i < size; i++)
			result.add(f.call(a.get(i), b[i]));
		return result;
	}

	/**
	 * 
	 * @param <I>
	 * @param <J>
	 * @param <O>
	 * @param f
	 * @param a
	 * @param b
	 * @return
	 * @since Iteration4
	 */
	public static <I, J, O> List<O> zipWith(final Function2<I,J,O> f, final List<I> a, final List<J> b)
			throws IllegalArgumentException {
		int size = Math.min(a.size(), b.size());
		List<O> result = new ArrayList<O>(size);
		for (int i = 0; i < size; i++)
			result.add(f.call(a.get(i), b.get(i)));
		return result;
	}

	private F() {}
}
