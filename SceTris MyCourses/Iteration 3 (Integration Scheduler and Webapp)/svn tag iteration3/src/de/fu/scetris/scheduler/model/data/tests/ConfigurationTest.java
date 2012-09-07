package de.fu.scetris.scheduler.model.data.tests;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import de.fu.scetris.data.Day;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.weave.orm.DatabaseException;

public class ConfigurationTest {

	@Test
	public void testCountTimeSlotsPerDay() throws DatabaseException {

		RelationManager relationManager = new RelationManager();
		String weekdays[] = { "Monday", "Tuesday", "Wednesday", "Thursday",
				"Friday" };

		List<Day> dayList = new ArrayList<Day>(5);
		for (int i = 0; i < 5; ++i) {
			dayList.add(relationManager.newDay(weekdays[i]));
			dayList.get(i).setId(i+1);
		}

		int j = 0;
		int h = 8;
		List<Timeslot> timeSlotList = new ArrayList<Timeslot>(60);
		for (int i = 0; i < 60; ++i) {

			timeSlotList.add(relationManager.newTimeslot(
					dayList.get(j),
					Time.valueOf("0".concat(new Integer(h).toString()).concat(
							":00:00"))));
			++h;

			if (((i + 1) % 12) == 0) {
				++j;
				h = 8;
			}
		}
		
		int timeSlotsPerDay[] = Configuration.countTimeSlotsPerDay(timeSlotList);
		Assert.assertArrayEquals(new int[] {12,12,12,12,12}, timeSlotsPerDay);

	}
}
