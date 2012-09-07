package de.fu.scetris.scheduler.data;

import java.util.Collection;

import de.fu.scetris.data.Room;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * Models a finished schedule as nested structure.
 * RoomTimetable has a room name and a Collection of 
 * DayTimetables.
 * 
 * @author Konrad Reiche
 *
 */
public class RoomTimetable {

	Room room;
	String roomName;
	Collection<DayTimetable> dayTimetableCollection;

	
	public RoomTimetable(Room room, String roomName,
			Collection<DayTimetable> dayTimetableCollection) {
		super();
		this.room = room;
		this.roomName = roomName;
		this.dayTimetableCollection = dayTimetableCollection;
	}
	
	@XmlAttribute("roomName")
	public String getRoomName() {
		return roomName;
	}

	public Room getRoom() {
		return room;
	}

	@XmlCollection("dayTimetableCollection")
	public Collection<DayTimetable> getDayTimetableCollection() {
		return dayTimetableCollection;
	}
}
