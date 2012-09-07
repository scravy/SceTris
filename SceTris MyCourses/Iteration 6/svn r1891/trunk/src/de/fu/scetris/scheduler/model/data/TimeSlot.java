package de.fu.scetris.scheduler.model.data;

import java.util.ArrayList;
import java.util.Collection;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;


/**
 * Models a time slot. TimeSlot has a Collection of Courses and a String for the
 * time. Do not confuse TimeSlot with {@link de.fu.scetris.data.Timeslot} which
 * is an object mapped relation.
 * 
 * @author Konrad Reiche
 * 
 */
public class TimeSlot {

	Collection<Course> CourseCollection;
	String timeName;
	
	public TimeSlot(String time) {
		super();
		CourseCollection = new ArrayList<Course>();
		this.timeName = time;
	}

	@XmlCollection("courseCollection")
	public Collection<Course> getCourseCollection() {
		return CourseCollection;
	}

	@XmlAttribute("timeName")
	public String getTimeName() {
		return timeName;
	}
}
