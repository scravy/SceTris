/* GenericConverter.java / 11:21:31 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.lang.reflect.Constructor;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class GenericConverter<T> implements StringConverter<T> {
	final private Constructor<T> constructor;

	public GenericConverter(final Class<T> type) {
		try {
			constructor = type.getConstructor(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public T convert(final String value) {
		try {
			return constructor.newInstance(value);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
}
