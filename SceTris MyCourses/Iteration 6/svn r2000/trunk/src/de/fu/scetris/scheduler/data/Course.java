package de.fu.scetris.scheduler.data;

import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * Models a course for the timetable. Course has a name and a lecturer. Do not
 * confuse Course with {@link de.fu.scetris.data.Course} which is an object
 * mapped relation.
 * 
 * @author Konrad Reiche
 * 
 */
public class Course {

	String lecturerName;
	String name;
	
	
	public Course(String lecturer, String name) {
		super();
		this.lecturerName = lecturer;
		this.name = name;
	}


	@XmlAttribute("lecturerName")
	public String getLecturer() {
		return lecturerName;
	}

	@XmlAttribute("courseName")
	public String getName() {
		return name;
	}
	
}
