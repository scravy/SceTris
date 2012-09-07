/* Strings.java / 2:40:13 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import java.util.Iterator;
import java.util.Map;

import org.w3c.dom.NodeList;

import de.fu.junction.functional.AbstractFunction2;
import de.fu.junction.functional.Function;
import de.fu.junction.functional.Function2;

/**
 * String functions
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Strings {

	final public static Function<String,String> capitalize = Dynamic.function(Strings.class,
		"capitalize",
																		String.class, String.class);

	final public static Function<String,String> toLower = new Function<String,String>() {
		@Override
		public String call(final String string) {
			return string.toLowerCase();
		}
	};

	final public static Function<String,String> toUpper = new Function<String,String>() {
		@Override
		public String call(final String string) {
			return string.toLowerCase();
		}
	};

	final public static Function<String,String> trim = new Function<String,String>() {
		@Override
		public String call(final String string) {
			return string.trim();
		}
	};

	final public static Function2<String,String,String> concat = new AbstractFunction2<String,String,String>() {
		@Override
		public String call(final String string1, final String string2) {
			return string1.concat(string2);
		}
	};

	/**
	 * 
	 * @param base
	 * @param object
	 * @return
	 * @since Iteration4
	 */
	public static String append(final String base, final Object object) {
		return base + object.toString();
	}

	/**
	 * 
	 * @param base
	 * @param string
	 * @return
	 * @since Iteration4
	 */
	public static String append(final String base, final String string) {
		return base + string;
	}

	/**
	 * 
	 * @param s
	 * @return
	 * @since Iteration4
	 */
	public static String capitalize(final String s) {
		return s.substring(0, 1).toUpperCase() + s.substring(1);
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final Object delimiter, final Iterable<?> strings) {
		return implode(delimiter.toString(), strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param entryDelimiter
	 * @param keyValueDelimiter
	 * @param map
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final Object entryDelimiter, final Object keyValueDelimiter,
								 final Map<?,?> map) {
		return implode(entryDelimiter.toString(), keyValueDelimiter.toString(), map, new StringBuilder())
				.toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final Object delimiter, final Object[] strings) {
		return implode(delimiter.toString(), strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final Object delimiter, final String[] strings) {
		return implode(delimiter.toString(), strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param array
	 * @return
	 */
	public static String implode(final String delimiter, final double[] array) {
		StringBuilder stringBuilder = new StringBuilder();
		if (array.length > 0) {
			stringBuilder.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				stringBuilder.append(delimiter);
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param array
	 * @return
	 */
	public static String implode(final String delimiter, final float[] array) {
		StringBuilder stringBuilder = new StringBuilder();
		if (array.length > 0) {
			stringBuilder.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				stringBuilder.append(delimiter);
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param array
	 * @return
	 */
	public static String implode(final String delimiter, final int[] array) {
		StringBuilder stringBuilder = new StringBuilder();
		if (array.length > 0) {
			stringBuilder.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				stringBuilder.append(delimiter);
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final String delimiter, final Iterable<?> strings) {
		return implode(delimiter, strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @param result
	 * @return
	 * @since Iteration4
	 */
	public static StringBuilder implode(final String delimiter, final Iterable<?> strings,
										final StringBuilder result) {
		Iterator<?> it = strings.iterator();
		if (!it.hasNext()) {
			return result;
		}
		while (it.hasNext()) {
			result.append(it.next().toString());
			result.append(delimiter);
		}
		result.setLength(result.length() - delimiter.length());
		return result;
	}

	/**
	 * 
	 * @param delimiter
	 * @param array
	 * @return
	 */
	public static String implode(final String delimiter, final long[] array) {
		StringBuilder stringBuilder = new StringBuilder();
		if (array.length > 0) {
			stringBuilder.append(array[0]);
			for (int i = 1; i < array.length; i++) {
				stringBuilder.append(delimiter);
				stringBuilder.append(array[i]);
			}
		}
		return stringBuilder.toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param nodeList
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final String delimiter, final NodeList nodeList) {
		return implode(delimiter, nodeList, new StringBuilder()).toString();
	}

	/**
	 * Concatenates each item of the given nodeList with a given delimiter.
	 * <p>
	 * The values which are used as String-representations are the node values which can be obtained
	 * via {@see org.w3c.dom.getNodeValue()}.
	 * 
	 * @param delimiter
	 *            The delimiter to use between node values (e.g. <code>", "</code>).
	 * @param nodeList
	 * @param stringBuilder
	 *            A StringBuilder on which to append the imploded string on.
	 * @return The same stringBuilder as given via the third parameter.
	 * @since Iteration4
	 */
	public static StringBuilder implode(final String delimiter, final NodeList nodeList,
										final StringBuilder stringBuilder) {
		int len = nodeList.getLength();
		if (len > 0) {
			stringBuilder.append(nodeList.item(0).getNodeValue());
			for (int i = 1; i < len; i++) {
				stringBuilder.append(delimiter);
				stringBuilder.append(nodeList.item(0).getNodeValue());
			}
		}
		return stringBuilder;
	}

	/**
	 * Concatenates each item of the given nodeList with a given delimiter.
	 * <p>
	 * The values which are used as String-representations are the node values which can be obtained
	 * via {@see org.w3c.dom.getNodeValue()}.
	 * <p>
	 * Equivalent to calling
	 * <code>implode(delimiter, strings, new StringBuilder()).toString()</code>.
	 * 
	 * @param delimiter
	 *            The delimiter to use between node values (e.g. <code>", "</code>).
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final String delimiter, final Object[] strings) {
		return implode(delimiter, strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @param result
	 * @return
	 * @since Iteration4
	 */
	public static StringBuilder implode(final String delimiter, final Object[] strings,
										final StringBuilder result) {
		if (strings.length > 0) {
			result.append(strings[0].toString());
			for (int i = 1; i < strings.length; i++) {
				result.append(delimiter);
				result.append(strings[i].toString());
			}
		}
		return result;
	}

	/**
	 * 
	 * @param entryDelimiter
	 * @param keyValueDelimiter
	 * @param map
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final String entryDelimiter, final String keyValueDelimiter,
								 final Map<?,?> map) {
		return implode(entryDelimiter, keyValueDelimiter, map, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param entryDelimiter
	 * @param keyValueDelimiter
	 * @param map
	 * @param result
	 * @return
	 * @since Iteration4
	 */
	public static StringBuilder implode(final String entryDelimiter, final String keyValueDelimiter,
										final Map<?,?> map, final StringBuilder result) {
		if (map.size() == 0) {
			return result;
		}
		for (Object k : map.keySet()) {
			result.append(k.toString());
			result.append(keyValueDelimiter);
			result.append(map.get(k).toString());
			result.append(entryDelimiter);
		}
		result.setLength(result.length() - entryDelimiter.length());
		return result;
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @return
	 * @since Iteration4
	 */
	public static String implode(final String delimiter, final String[] strings) {
		return implode(delimiter, strings, new StringBuilder()).toString();
	}

	/**
	 * 
	 * @param delimiter
	 * @param strings
	 * @param result
	 * @return
	 * @since Iteration4
	 */
	public static StringBuilder implode(final String delimiter, final String[] strings,
										final StringBuilder result) {
		if (strings.length > 0) {
			result.append(strings[0]);
			for (int i = 1; i < strings.length; i++) {
				result.append(delimiter);
				result.append(strings[i]);
			}
		}
		return result;
	}

	/**
	 * 
	 * @param base
	 * @param string
	 * @return
	 * @since Iteration4
	 */
	public static String prepend(final String base, final String string) {
		return string + base;
	}

	/**
	 * @param value
	 * @return
	 * @since Iteration4
	 */
	public static String stripWhitespace(final String value) {
		return value.replaceAll("[ \t\n\f\r]", "");
	}
}
