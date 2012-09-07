package de.fu.scetris.scheduler.model.data;

import java.util.Comparator;

import de.fu.scetris.data.Timeslot;
import de.fu.weave.orm.DatabaseException;

public class TimeSlotComparator implements Comparator<Timeslot> {

	@Override
	public int compare(Timeslot t1, Timeslot t2) {

		try {
			if (t1.getDay().getId() > t2.getDay().getId()) {
				return 1;
			} else if (t1.getDay().getId() < t2.getDay().getId()) {
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
