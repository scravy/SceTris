package de.fu.scetris.scheduler.model.data.tests;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.Building;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.controller.tests.SchedulerTest;
import de.fu.scetris.scheduler.model.data.RoomComparator;

public class RoomComparatorTest extends SchedulerTest {

	List<Room> roomList;

	@Before
	public void init() throws DatabaseException {
		roomList = new ArrayList<Room>();

		Building building = relationManager.createBuilding("73 Test Avenue,"
				+ " California Springs, CA 92926");

		Feature seat = relationManager.createFeature("Seat");
		Feature whiteboard = relationManager.createFeature("whiteboard");
		Feature projector = relationManager.createFeature("projector");

		Integer roomNumber = new Integer(0);

		for (int i = 0; i < 4; ++i) {
			Room room = relationManager.createRoom(roomNumber.toString(),
					building);
			roomList.add(room);
			++roomNumber;
		}

		relationManager.createRoomProvidesFeature(roomList.get(0), seat, 200);
		relationManager.createRoomProvidesFeature(roomList.get(1), seat, 50);
		relationManager.createRoomProvidesFeature(roomList.get(2), seat, 300);

		relationManager.createRoomProvidesFeature(roomList.get(0), whiteboard,
				1);
		relationManager.createRoomProvidesFeature(roomList.get(1), whiteboard,
				2);
		relationManager.createRoomProvidesFeature(roomList.get(2), whiteboard,
				2);

		relationManager
				.createRoomProvidesFeature(roomList.get(3), projector, 1);
	}

	@Test
	public void testCompare() throws DatabaseException {

		RoomComparator roomComparator = new RoomComparator(new Random(),
				roomList, false);

		Integer room0Id = roomList.get(0).getId();
		Integer room1Id = roomList.get(1).getId();

		try {
			// Room[0] has more seats and Room[1] has more whiteboards
			Assert.assertEquals(
					roomComparator.compare(roomList.get(0), roomList.get(1)),
					room0Id.compareTo(room1Id));

			// Room[0] is compared to itself.
			Assert.assertEquals(
					roomComparator.compare(roomList.get(0), roomList.get(0)), 0);

			// Room[2] has more seats and more whiteboards than Room[0]
			Assert.assertEquals(
					roomComparator.compare(roomList.get(2), roomList.get(0)), 1);

			// Room[0] has less seats and less whiteboards than Room[2]
			Assert.assertEquals(
					roomComparator.compare(roomList.get(0), roomList.get(2)),
					-1);
		} catch (RuntimeException e) {
			Assert.assertTrue(false);
		}

		try {
			// Room[0] has more features then Room[3]
			Assert.assertEquals(
					roomComparator.compare(roomList.get(0), roomList.get(3)), 0);
		} catch (RuntimeException e) {
			Assert.assertTrue(true);
		}

	}

}
