/* DatabaseLogicTest.java / 1:36:44 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.tests;

import static de.fu.weave.orm.filters.Filters.*;

import java.util.Collection;

import org.junit.Test;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class AndresBugTest extends DatabaseTestSkeleton {

	/**
	 * 
	 * 
	 * @since Iteration4
	 */
	@Test
	public void test() throws Exception {
		RelationManager relationManager = manager;

		Building b = relationManager.createBuilding("Whateverest 0");
		Room r = relationManager.createRoom("R055", b);

		Feature f1 = relationManager.createFeature("Dia Projector");
		Feature f2 = relationManager.createFeature("Overhead Projector");

		relationManager.createRoomProvidesFeature(r, f1, 1);
		relationManager.createRoomProvidesFeature(r, f2, 1);

		String data = Integer.toString(r.id());
		String roomname = r.getName();
		String building = Integer.toString(b.id());
		String[] featurekeys = new String[] { f1.getName(), f2.getName() };
		String[] features = new String[] { "2", "1" };

		Collection<Room> rooms = relationManager.getRoom(eq("id", Integer.parseInt(data)));
		Room room = rooms.iterator().next();
		room.setName(roomname);
		Collection<Building> tmp = relationManager.getBuilding(eq("id", Integer.parseInt(building)));
		room.setBuilding(tmp.iterator().next());
		room.pushChanges();

		for (int i = 0; i < featurekeys.length; i++) {
			int quantity = 0;
			if (features[i] != "") {
				quantity = Integer.parseInt(features[i]);
			}

			Collection<Feature> fs = relationManager.getFeature(eq("name", featurekeys[i]));
			Feature f = fs.iterator().next();
			Collection<RoomProvidesFeature> rpf = relationManager.getRoomProvidesFeature(room, f);
			if (!rpf.isEmpty()) {
				RoomProvidesFeature rel = rpf.iterator().next();
				// room has this feature already, override it
				if (quantity > 0) {
					rel.setQuantity(quantity);
					rel.pushChanges();
				} else {
					rel.delete();
				}
			} else {
				if (quantity > 0) {
					RoomProvidesFeature prov = relationManager.newRoomProvidesFeature(room, f, quantity);
					prov.create();
				}
			}
		}
	}
}
