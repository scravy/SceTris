/* StringToSQLDate.java / 12:50:40 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.converter;

import java.sql.Date;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class StringToSQLDate extends StringToDate {

	/**
	 * 
	 */
	public StringToDate converter = new StringToDate();

	@Override
	public Date convert(final String value) {
		java.util.Date date = converter.convert(value);
		if (date == null) {
			return null;
		}
		return new Date(date.getTime());
	}

}
