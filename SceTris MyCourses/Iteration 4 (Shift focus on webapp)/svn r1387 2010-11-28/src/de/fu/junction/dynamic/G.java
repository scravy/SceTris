/* Generics.java / 12:20:02 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.dynamic;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

import de.fu.junction.functional.Tuple;


/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class G {

	/**
	 * 
	 * 
	 * @param <A>
	 * @author Julian Fleischer
	 * @since Iteration4
	 */
	private static class Array<A> {

		/**
		 * 
		 */
		private final A[] elements;

		/**
		 * 
		 * @param elements
		 * @since Iteration4
		 */
		@SuppressWarnings("unchecked")
		private Array(final A... elements) {
			this.elements = (A[]) java.lang.reflect.Array.newInstance(elements[0].getClass(),
																	  elements.length);
			System.arraycopy(elements, 0, this.elements, 0, elements.length);
		}

		/**
		 * 
		 * @return
		 * @since Iteration4
		 */
		private A[] get() {
			return elements;
		}

	}

	/**
	 * 
	 * @param <A>
	 * @param elements
	 * @return
	 * @since Iteration4
	 */
	public static <A> A[] array(final A... elements) {
		return new Array<A>(elements).get();
	}

	/**
	 * 
	 * @param <T>
	 * @param clazz
	 * @param args
	 * @return
	 * @since Iteration4
	 */
	public static <T> T create(final Class<T> clazz, final Object... args) {
		Class<?>[] types = new Class<?>[args.length];
		for (int i = 0; i < args.length; i++) {
			types[i] = args[i].getClass();
		}
		try {
			return clazz.getConstructor(types).newInstance(args);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param name
	 * @param args
	 * @return
	 * @since Iteration4
	 */
	public static Object create(final String name, final Object... args) {
		try {
			return create(Class.forName(name), args);
		} catch (Exception e) {
			return null;
		}
	}

	/**
	 * 
	 * @param <K>
	 * @param <V>
	 * @param keyType
	 * @param valueType
	 * @param comparator
	 * @param tupels
	 * @return
	 * @since Iteration4
	 */
	public static <K, V> Map<K,V> mapping(final Class<K> keyType, final Class<V> valueType,
										  final Comparator<K> comparator,
										  final Tuple<K,V>... tupels) {
		Map<K,V> map = new TreeMap<K,V>(comparator);
		for (Tuple<K,V> tupel : tupels) {
			map.put(tupel.fst, tupel.snd);
		}
		return map;
	}

	/**
	 * 
	 * @param <K>
	 * @param <V>
	 * @param keyType
	 * @param valueType
	 * @param tupels
	 * @return
	 * @since Iteration4
	 */
	public static <K, V> Map<K,V> mapping(final Class<K> keyType, final Class<V> valueType,
										  final Tuple<K,V>... tupels) {
		Map<K,V> map = new TreeMap<K,V>();
		for (Tuple<K,V> tupel : tupels) {
			map.put(tupel.fst, tupel.snd);
		}
		return map;
	}

	/**
	 * 
	 * @param <K>
	 * @param <V>
	 * @param comparator
	 * @param tupels
	 * @return
	 * @since Iteration4
	 */
	public static <K, V> Map<K,V> mapping(final Comparator<K> comparator, final Tuple<K,V>... tupels) {
		Map<K,V> map = new TreeMap<K,V>(comparator);
		for (Tuple<K,V> tupel : tupels) {
			map.put(tupel.fst, tupel.snd);
		}
		return map;
	}

	/**
	 * 
	 * @param <K>
	 * @param <V>
	 * @param tupels
	 * @return
	 * @since Iteration4
	 */
	public static <K, V> Map<K,V> mapping(final Tuple<K,V>... tupels) {
		Map<K,V> map = new TreeMap<K,V>();
		for (Tuple<K,V> tupel : tupels) {
			map.put(tupel.fst, tupel.snd);
		}
		return map;
	}

	private G() {
	}
}
