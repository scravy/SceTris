/* Timetable.java / 11:31:55 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.web;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.fu.scetris.data.Day;
import de.fu.scetris.data.Room;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class RoomTimetable {

	public static class DayTableEntry {

		Day day;
		List<TimetableEntry> timetableEntryList;
		
		public DayTableEntry(List<TimetableEntry> timetableEntryList, Day day) {
			this.timetableEntryList = timetableEntryList;
			this.day = day;
		}
		
		
		@XmlAttribute("day")		
		public String getDay() {
			return day.getName();
		}

		@XmlCollection("timetableList")
		public Collection<TimetableEntry> getTimetableEntryList() {
			return timetableEntryList;
		}



		public static class TimetableEntry {

			int id;
			Time time;
			String courseName;
			String lecturerName;

			public TimetableEntry(int id, Time time, String courseName,
					String lecturerName) {
				super();
				this.id = id;
				this.time = time;
				this.courseName = courseName;
				this.lecturerName = lecturerName;
			}

			@XmlAttribute("courseName")
			public String getCourseName() {
				return courseName;
			}

			@XmlAttribute("lecturerName")
			public String getLecturerName() {
				return lecturerName;
			}

			@XmlAttribute("time")
			public String getTime() {
				return time.toString().substring(0, 5);
			}

			public int getId() {
				return id;
			}

			public void setLecturerName(String lecturerName) {
				this.lecturerName = lecturerName;
			}

			public void setCourseName(String courseName) {
				this.courseName = courseName;
			}

		}

	}

	List<DayTableEntry> slots = new ArrayList<DayTableEntry>();
	String roomName;

	public RoomTimetable(Collection<DayTableEntry> timeSlotCollection, Room room) {
		slots = (List<DayTableEntry>) timeSlotCollection;
		roomName = room.getName();
	}

	@XmlAttribute("roomName")
	public String getRoomName() {
		return roomName;
	}

	@XmlCollection("fixtures")
	public Collection<DayTableEntry> getTimeslots() {
		return slots;
	}
}
