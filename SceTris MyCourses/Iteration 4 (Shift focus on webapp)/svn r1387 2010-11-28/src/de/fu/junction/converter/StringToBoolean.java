/* StringToBoolean.java / 12:21:28 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.util.SortedSet;
import java.util.TreeSet;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToBoolean implements StringConverter<Boolean> {

	SortedSet<String> yes = new TreeSet<String>();

	public StringToBoolean() {
		yes.add("yes");
		yes.add("y");
		yes.add("true");
		yes.add("ja");
		yes.add("on");
	}

	@Override
	public Boolean convert(final String value) {
		try {
			if (Integer.parseInt(value) > 0) {
				return true;
			}
		} catch (NumberFormatException e) {
			return yes.contains(value.toLowerCase());
		}
		return false;
	}
}
