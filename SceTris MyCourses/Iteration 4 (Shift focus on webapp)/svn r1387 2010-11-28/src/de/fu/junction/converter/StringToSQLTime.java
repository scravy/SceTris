/* StringToSQLTime.java / 12:51:18 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import de.fu.junction.functional.S;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToSQLTime implements StringConverter<Time> {

	final DateFormat[] formats;

	public StringToSQLTime() {
		formats = new DateFormat[] {
				new SimpleDateFormat("H:m:s.S"),
				new SimpleDateFormat("H:m:s"),
				new SimpleDateFormat("H:m")
		};
	}

	@Override
	public Time convert(String value) {
		value = S.stripWhitespace(value);
		for (DateFormat format : formats) {
			try {
				return new Time(format.parse(value).getTime());
			} catch (ParseException e) {
			}
		}
		return null;
	}
}
