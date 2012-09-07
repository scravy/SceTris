/* Parse.java / 7:09:36 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

/**
 *
 */
public class Parse {

	public static Float parseFloat(final String $value, final Float $default) {
		try {
			return Float.parseFloat($value);
		} catch (NumberFormatException $exc) {
			return $default;
		}
	}

	public static Double parseInt(final String $value, final Double $default) {
		try {
			return Double.parseDouble($value);
		} catch (NumberFormatException $exc) {
			return $default;
		}
	}

	public static Integer parseInt(final String $value, final Integer $default) {
		try {
			return Integer.parseInt($value);
		} catch (NumberFormatException $exc) {
			return $default;
		}
	}

	public static Long parseLong(final String $value, final Long $default) {
		try {
			return Long.parseLong($value);
		} catch (NumberFormatException $exc) {
			return $default;
		}
	}

}
