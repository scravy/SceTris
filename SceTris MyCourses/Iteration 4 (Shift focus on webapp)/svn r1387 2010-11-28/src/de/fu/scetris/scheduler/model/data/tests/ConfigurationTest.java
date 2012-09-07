/* ConfigurationTest.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.data.tests;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;


import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.Configuration;

public class ConfigurationTest {

	@Test
	public void testCountTimeSlotsPerDay() throws DatabaseException {

		RelationManager relationManager = new RelationManager();
		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		List<Day> dayList = new ArrayList<Day>(5);
		for (int i = 0; i < 5; ++i) {
			dayList.add(relationManager.newDay(weekdays[i]));
			/*
			 * XXX: Ids are not to be set manually.
			 * 
			 * setId(...) has been removed from ORM @ 2010-11-22
			 * 
			 * Why do you need to set the id anyway?
			 */
			// dayList.get(i).setId(i + 1);
		}

		int j = 0;
		int h = 8;
		List<Timeslot> timeSlotList = new ArrayList<Timeslot>(60);
		for (int i = 0; i < 60; ++i) {
			Time start = Time.valueOf("0".concat(new Integer(h).toString()).concat(":00:00"));
			timeSlotList.add(relationManager.newTimeslot(dayList.get(j), start));
			++h;

			if (((i + 1) % 12) == 0) {
				++j;
				h = 8;
			}
		}

		int timeSlotsPerDay[] = Configuration.countTimeSlotsPerDay(timeSlotList);
		Assert.assertArrayEquals(new int[] { 12, 12, 12, 12, 12 }, timeSlotsPerDay);

	}
}
