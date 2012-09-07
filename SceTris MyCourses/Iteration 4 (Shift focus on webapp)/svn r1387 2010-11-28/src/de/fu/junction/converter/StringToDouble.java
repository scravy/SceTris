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
public class StringToDouble implements StringConverter<Double> {

	@SuppressWarnings("unchecked")
	Map<String,Double> doubles = mapping(
									 t("pi", Math.PI),
									 t("e", Math.E)
								   );

	@Override
	public Double convert(String value) {
		try {
			return Double.parseDouble(value);
		} catch (NumberFormatException e) {
			value = value.toLowerCase();
			if (doubles.keySet().contains(value)) {
				return doubles.get(value);
			}
		}
		return null;
	}
}
