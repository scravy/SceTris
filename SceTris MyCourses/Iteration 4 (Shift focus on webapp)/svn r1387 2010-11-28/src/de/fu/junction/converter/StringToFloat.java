/* StringToDouble.java / 12:21:11 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import static de.fu.junction.dynamic.G.*;
import static de.fu.junction.functional.Tuple.*;

import java.util.Map;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToFloat implements StringConverter<Float> {

	@SuppressWarnings("unchecked")
	Map<String,Float> floats = mapping(
									 t("pi", (float) Math.PI),
									 t("e", (float) Math.E)
								   );

	@Override
	public Float convert(String value) {
		try {
			return Float.parseFloat(value);
		} catch (NumberFormatException e) {
			value = value.toLowerCase();
			if (floats.keySet().contains(value)) {
				return floats.get(value);
			}
		}
		return null;
	}
}
