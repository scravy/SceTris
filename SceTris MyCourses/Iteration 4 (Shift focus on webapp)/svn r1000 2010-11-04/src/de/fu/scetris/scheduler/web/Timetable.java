/* Timetable.java / 11:31:55 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.web;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class Timetable {

	public static class Timeslot {
		final int start;
		final String name;

		Timeslot(final int start, final String name) {
			this.start = start;
			this.name = name;
		}

		@XmlAttribute("courseName")
		public String getCourseName() {
			return name;
		}

		@XmlAttribute("duration")
		public int getDuration() {
			return 2;
		}

		@XmlAttribute("start")
		public int getStartingTime() {
			return start;
		}
	}

	List<Timeslot> slots = new ArrayList<Timeslot>();

	Timetable() {

		slots.add(new Timeslot(10, "Alp1"));
		slots.add(new Timeslot(12, "Alp2"));
		slots.add(new Timeslot(40, "Ti4"));

	}

	@XmlCollection("fixtures")
	public Collection<Timeslot> getTimeslots() {
		return slots;
	}
}
