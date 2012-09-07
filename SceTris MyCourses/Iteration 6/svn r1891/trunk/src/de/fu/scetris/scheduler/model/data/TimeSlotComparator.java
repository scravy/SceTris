package de.fu.scetris.scheduler.model.data;

import java.util.Comparator;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Timeslot;

public class TimeSlotComparator implements Comparator<Timeslot> {

	@Override
	public int compare(final Timeslot t1, final Timeslot t2) {

		try {
			if (t1.getDay().id() > t2.getDay().id()) {
				return 1;
			} else if (t1.getDay().id() < t2.getDay().id()) {
				return -1;
			} else {

				if (t1.getStartingTime()
						.compareTo(t2.getStartingTime()) == 1) {
					return 1;
				} else if (t1.getStartingTime().compareTo(
														  t2.getStartingTime()) == -1) {
					return -1;
				} else {
					return 0;
				}
			}
		} catch (DatabaseException e) {
			throw new RuntimeException(e);
		}
	}
}
