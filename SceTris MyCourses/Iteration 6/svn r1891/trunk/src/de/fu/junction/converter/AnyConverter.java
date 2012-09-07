/* StaticConverter.java / 7:47:55 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.lang.reflect.Constructor;

/**
 * Same as {@see GenericConverter}, but untyped (returns an Object)
 * 
 * This class is needed as default Converter in the {@see de.fu.weave.annotation.Param}-Annotation.
 * Since unfortunately one can not state things like {@code GenericConverter<String>.class} a Type
 * is needed that does not feature any type-parameters.
 * <p>
 * The type to convert to needs to have a Converter having one parameter (and one parameter only) of
 * the type string. Example types for which a generic conversion (since their constructor do it) can
 * be done are {@see java.lang.Integer}, {@see java.math.BigDecimal} or {@see java.lang.Double}.
 * <p>
 * It’s worth noting that although the static type of the converted value is Object it’s dynamic
 * type <i>of course</i> reflects the type which was specified when constructing the converter.
 * <p>
 * This special class also features a static method which <i>is</i> type-safe both statically and
 * dynamically (@see {@link #convert(String, Class)}.
 * 
 * @author Julian Fleischer
 */
public class AnyConverter implements StringConverter<Object> {
	/**
	 * Converts a String to the specified type.
	 * <p>
	 * Same rules for the type apply as if a new converter object was to be created.
	 * 
	 * @param <T>
	 *            The Type to convert to (e.g. {@see java.lang.Float})
	 * @param value
	 * @param type
	 * @return
	 * @since Iteration4
	 */
	public static <T> T convert(final String value, final Class<T> type) {
		return new GenericConverter<T>(type).convert(value);
	}

	final private Constructor<?> constructor;

	/**
	 * Creates a new AnyConverter
	 * 
	 * @param type
	 *            A Class object representing the type to convert to
	 * @throws RuntimeException
	 *             If the type does not feature a constructor with one parameter of the type String.
	 */
	public AnyConverter(final Class<?> type) {
		try {
			constructor = type.getConstructor(String.class);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public Object convert(final String value) {
		try {
			return constructor.newInstance(value);
		} catch (Exception e) {

		}
		return null;
	}
}
