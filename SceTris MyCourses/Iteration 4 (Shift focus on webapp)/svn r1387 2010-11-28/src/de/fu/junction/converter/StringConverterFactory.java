/* ConverterFactory.java / 11:20:08 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.junction.converter;

import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;

import de.fu.weave.util.ClassesComparator;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringConverterFactory {

	/**
	 * @return
	 * @since Iteration4
	 */
	public static StringConverterFactory newFactory() {
		return new StringConverterFactory();
	}

	private final Map<Class<?>,StringConverter<?>> converters =
			new TreeMap<Class<?>,StringConverter<?>>(ClassesComparator.comparator);

	public StringConverterFactory() {
		StringConverter<?> c;
		converters.put(int.class, c = new StringToInteger());
		converters.put(Integer.class, c);
		converters.put(long.class, c = new StringToLong());
		converters.put(Long.class, c);
		converters.put(float.class, c = new StringToFloat());
		converters.put(Float.class, c);
		converters.put(double.class, c = new StringToDouble());
		converters.put(Double.class, c);
		converters.put(boolean.class, c = new StringToBoolean());
		converters.put(Boolean.class, c);

		converters.put(java.sql.Date.class, c = new StringToSQLDate());
		converters.put(java.util.Date.class, c = new StringToDate());
		converters.put(java.sql.SQLXML.class, c = new StringToSQLXML());
		converters.put(java.sql.Time.class, c = new StringToSQLTime());
		converters.put(java.sql.Timestamp.class, c = new StringToSQLTimestamp());
		try {
			converters.put(Document.class, c = new StringToDOMDocument());
		} catch (ConverterInstantiationException e) {
		}
	}

	@SuppressWarnings("unchecked")
	public <T> StringConverter<T> newConverter(final Class<T> type) {
		if (converters.containsKey(type)) {
			return (StringConverter<T>) converters.get(type);
		}
		return new GenericConverter<T>(type);
	}

}
