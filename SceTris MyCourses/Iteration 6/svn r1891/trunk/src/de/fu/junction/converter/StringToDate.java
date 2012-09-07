/* StringToDate.java / 12:50:48 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import de.fu.junction.Strings;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToDate implements StringConverter<Date> {

	final DateFormat[] formats;

	public StringToDate() {
		formats = new DateFormat[] {
				new SimpleDateFormat("yy-MM-dd"),
				new SimpleDateFormat("yy-MM-dd"),
				new SimpleDateFormat("dd.MM.yy"),
				new SimpleDateFormat("dd/MM/yy")
		};
	}

	@Override
	public Date convert(String value) {
		value = Strings.stripWhitespace(value);
		for (DateFormat format : formats) {
			try {
				return format.parse(value);
			} catch (ParseException e) {
			}
		}
		return null;
	}

}
