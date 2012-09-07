package de.fu.scetris.scheduler.model.data;

import java.util.Collection;

import de.fu.scetris.data.Day;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * Models the timetable for a certain day in a certain room.
 * DayTimetable has a Collection of TimeSlots and a day name.
 * 
 * @author Konrad Reiche
 * 
 */
public class DayTimetable {

	Day day;
	String dayName;
	Collection<TimeSlot> timeSlotCollection;
	

	public DayTimetable(Day day, String dayName,
			Collection<TimeSlot> timeSlotCollection) {
		super();
		this.day = day;
		this.dayName = dayName;
		this.timeSlotCollection = timeSlotCollection;
	}

	
	public Day getDay() {
		return day;
	}

	@XmlCollection("timeSlotCollection")
	public Collection<TimeSlot> getTimeSlotCollection() {
		return timeSlotCollection;
	}

	@XmlAttribute("dayName")
	public String getDayName() {
		return dayName;
	}
}
