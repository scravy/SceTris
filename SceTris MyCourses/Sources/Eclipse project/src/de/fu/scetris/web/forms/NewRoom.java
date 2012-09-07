/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.scetris.data.Building;
import de.fu.scetris.data.Room;

public class NewRoom extends Room.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    public Map<Integer,Building> $buildings = new TreeMap<Integer,Building>();

    @Multiple
    @Field
    @FormControl(Control.HIDDEN)
    public int[] featureQuantity = {};

    @Multiple
    @Field
    @FormControl(Control.HIDDEN)
    public int[] featureId = {};

    @Override
    public boolean commit() throws Exception {
        Room $room = manager().newRoom(number, manager().getBuilding(building));
        if (name != null) {
            $room.setName(name);
        }
        $room.create();
        for (int i = 0; i < featureQuantity.length; i++) {
            if (featureQuantity[i] > 0) {
                manager().createRoomProvidesFeature(
                    $room,
                    manager().getFeature(featureId[i]),
                    featureQuantity[i]);
            }
        }
        return true;
    }

    @Override
    public void init() {
        super.init();

        try {
            List<Building> $buildings = manager().getBuilding();
            for (Building $building : $buildings) {
                this.$buildings.put($building.id(), $building);
                building$alternatives.put($building.id(),
                    String.format("%s, %s", $building.getName(), $building.getAddress()));
            }
        } catch (Exception $exc) {}
    }
}
