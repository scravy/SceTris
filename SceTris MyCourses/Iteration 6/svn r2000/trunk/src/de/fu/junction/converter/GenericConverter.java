/* GenericConverter.java / 11:21:31 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.lang.reflect.Constructor;

/**
 * A GenericConverter converts a String to anything which has its own means for parsing a value from
 * a String
 * 
 * There is also a version of this class without the need for a Type-parameter which returns a value
 * which has the static type Object.
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class GenericConverter<T> implements StringConverter<T> {
	final private Constructor<T> constructor;

	/**
	 * Creates a new GenericConverter
	 * <p>
	 * The type to convert to needs to have a Converter having one parameter (and one parameter
	 * only) of the type string. Example types for which a generic conversion (since their
	 * constructor do it) can be done are {@see java.lang.Integer}, {@see java.math.BigDecimal} or
	 * {@see java.lang.Double}.
	 * 
	 * @param type
	 *            The type to convert to.
	 * @throws RuntimeException
	 *             If the type does not feature a constructor with one parameter of the type String.
	 */
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

		}
		return null;
	}
}
