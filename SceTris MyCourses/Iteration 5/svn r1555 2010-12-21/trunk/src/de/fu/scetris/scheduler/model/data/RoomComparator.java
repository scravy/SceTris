package de.fu.scetris.scheduler.model.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;

public class RoomComparator implements Comparator<Room> {

	Random random;

	public RoomComparator(Random random) {
		super();
		this.random = random;
	}

	@Override
	public int compare(Room o1, Room o2) {

		Integer roomScore1 = 0;
		Integer roomScore2 = 0;

		try {
			List<Feature> featureList1 = o1.objectsOfRoomProvidesFeature();
			List<Feature> featureList2 = o2.objectsOfRoomProvidesFeature();
			Collections.sort(featureList1);
			Collections.sort(featureList2);

			Map<Feature, Integer> featureToQuantityRoom1 = new TreeMap<Feature, Integer>();
			Map<Feature, Integer> featureToQuantityRoom2 = new TreeMap<Feature, Integer>();

			for (RoomProvidesFeature roomProvidesFeature : o1
					.whereSubjectOfRoomProvidesFeature()) {
				featureToQuantityRoom1.put(roomProvidesFeature.getFeature(),
						roomProvidesFeature.getQuantity());
			}

			for (RoomProvidesFeature roomProvidesFeature : o2
					.whereSubjectOfRoomProvidesFeature()) {
				featureToQuantityRoom2.put(roomProvidesFeature.getFeature(),
						roomProvidesFeature.getQuantity());
			}

			for (int i = 0; i < featureList1.size(); ++i) {

				int r1 = featureToQuantityRoom1.get(featureList1.get(i));
				int r2 = featureToQuantityRoom2.get(featureList2.get(i));

				if (r1 > r2) {
					++roomScore1;
				} else if (r1 < r2) {
					++roomScore2;
				}
			}

		} catch (DatabaseException e) {
			e.printStackTrace();
		}

		if (roomScore1 == roomScore2) {

			if (random.nextBoolean()) {
				return -1;
			} else {
				return 1;
			}

			// return new Integer(o1.getId()).compareTo(new
			// Integer(o2.getId()));
		}

		return roomScore1.compareTo(roomScore2);
	}
}
