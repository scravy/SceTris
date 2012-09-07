/* StringToSQLTimestamp.java / 12:51:31 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import de.fu.junction.Strings;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToSQLTimestamp implements StringConverter<Timestamp> {

	final DateFormat[] formats;

	/**
	 * 
	 * @since Iteration4
	 */
	public StringToSQLTimestamp() {
		formats = new DateFormat[] {
				new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
		};
	}

	@Override
	public Timestamp convert(String value) {
		value = Strings.stripWhitespace(value);
		for (DateFormat format : formats) {
			try {
				return new Timestamp(format.parse(value).getTime());
			} catch (ParseException e) {
			}
		}
		return null;
	}

}
