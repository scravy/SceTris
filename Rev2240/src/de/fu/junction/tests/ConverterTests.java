/* ConverterTests.java / 3:01:07 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.tests;

import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Calendar;

import org.junit.Test;
import static org.junit.Assert.*;

import de.fu.junction.converter.StringConverter;
import de.fu.junction.converter.StringConverterFactory;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class ConverterTests {

	static StringConverterFactory converters = new StringConverterFactory();

	@Test
	public void testStringToDate() {
		StringConverter<java.util.Date> converter = converters.newConverter(java.util.Date.class);
		java.util.Date date = converter.convert("2010-09-12");
		assertNotNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertEquals(2010, cal.get(Calendar.YEAR));
		assertEquals(9, cal.get(Calendar.MONTH) + 1);
		assertEquals(12, cal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testStringToSQLDate() {
		StringConverter<Date> converter = converters.newConverter(Date.class);
		Date date = converter.convert("2010-09-12");
		assertNotNull(date);
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		assertEquals(2010, cal.get(Calendar.YEAR));
		assertEquals(9, cal.get(Calendar.MONTH) + 1);
		assertEquals(12, cal.get(Calendar.DAY_OF_MONTH));
	}

	@Test
	public void testStringToSQLTime() {
		StringConverter<Time> converter = converters.newConverter(Time.class);
		Time time = converter.convert("10:17:03");
		assertNotNull(time);
		Calendar cal = Calendar.getInstance();
		cal.setTime(time);
		assertEquals(10, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(17, cal.get(Calendar.MINUTE));
		assertEquals(3, cal.get(Calendar.SECOND));
	}

	@Test
	public void testStringToSQLTimestamp() {
		StringConverter<Timestamp> converter = converters.newConverter(Timestamp.class);
		Timestamp timestamp = converter.convert("2010-10-25T13:29:39");
		Calendar cal = Calendar.getInstance();
		cal.setTime(timestamp);
		System.out.println(timestamp);
		assertEquals(2010, cal.get(Calendar.YEAR));
		assertEquals(10, cal.get(Calendar.MONTH) + 1);
		assertEquals(25, cal.get(Calendar.DAY_OF_MONTH));
		assertEquals(13, cal.get(Calendar.HOUR_OF_DAY));
		assertEquals(29, cal.get(Calendar.MINUTE));
		assertEquals(39, cal.get(Calendar.SECOND));
	}
}
