package de.fu.scetris.scheduler.model.data;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.TreeMap;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;

/**
 * Compares two rooms by assigning each a score. The score is based on the
 * quantities of their features. Only two rooms with the same features can be
 * compared.
 * 
 * @author Konrad Reiche
 * 
 */
public class RoomComparator implements Comparator<Room> {

	Random random;
	Map<Room, Map<Feature, Integer>> roomToFeaturesQuantity;
	boolean hasRandomResults;

	public RoomComparator(Random random, List<Room> roomList,
			boolean hasRandomResults) throws DatabaseException {
		this.random = random;
		this.hasRandomResults = hasRandomResults;
		roomToFeaturesQuantity = new TreeMap<Room, Map<Feature, Integer>>();

		for (Room room : roomList) {

			List<Feature> featureList = room.objectsOfRoomProvidesFeature();
			Collections.sort(featureList);

			Map<Feature, Integer> featureToQuantity = new TreeMap<Feature, Integer>();
			roomToFeaturesQuantity.put(room, featureToQuantity);

			for (RoomProvidesFeature roomProvidesFeature : room
					.whereSubjectOfRoomProvidesFeature()) {

				featureToQuantity.put(roomProvidesFeature.getFeature(),
						roomProvidesFeature.getQuantity());
			}
		}
	}

	@Override
	public int compare(Room room1, Room room2) {

		Integer roomScore1 = 0;
		Integer roomScore2 = 0;

		if (roomToFeaturesQuantity.get(room1).size() != roomToFeaturesQuantity
				.get(room2).size()) {
			throw new RuntimeException("The Rooms are not comparable.");
		}

		for (Entry<Feature, Integer> entry : roomToFeaturesQuantity.get(room1)
				.entrySet()) {

			Integer room1FeatureQuantity = entry.getValue();
			Integer room2FeatureQuantity = roomToFeaturesQuantity.get(room2)
					.get(entry.getKey());

			roomScore1 += room1FeatureQuantity.compareTo(room2FeatureQuantity);

		}

		if (roomScore1 == roomScore2 && hasRandomResults) {
			if (random.nextBoolean()) {
				return -1;
			} else {
				return 1;
			}
		} else if (roomScore1 == roomScore2) {
			return new Integer(room1.getId()).compareTo(new Integer(room2
					.getId()));
		} else {
			return roomScore1.compareTo(roomScore2);
		}
	}
}
