package de.fu.scetris.scheduler.model.data;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstancePrefersRoom;
import de.fu.scetris.data.ElementInstancePrefersTimeslot;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonPrefersTimeslot;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomPrefersTimeslot;
import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * Provides information about the schedule score.
 * 
 * @author Konrad Reiche
 * 
 */
public class ScheduleScore {

	/**
	 * Soft fitness value reaching from 0.0 to 1.0
	 * 
	 * @since Iteration5
	 */
	double softFitness;
	/**
	 * Hard fitness value reaching from 0.0 to 1.0
	 * 
	 * @since Iteration5
	 */
	double hardFitness;

	int numberOfSoftConstraints;
	int numberOfSoftConstraintsSatisfied;
	int sumSoftConstraints;
	int sumSoftConstraintsSatisfied;

	int numberOfHardConstraints;
	int numberOfHardConstraintsSatisfied;
	int sumHardConstraints;
	int sumHardConstraintsSatisfied;

	// hard constraints only
	Map<CourseElementInstance, Set<CourseElementInstance>> courseToRoomOverlap;
	Map<Person, Set<CourseElementInstance>> lecturerToLecturerOverlap;

	// hard and soft constraints both, can be distinguished by reading the
	// priority attribute
	Map<CourseElementInstance, Map<ElementInstancePrefersRoom, Boolean>> courseToRoomPreference;
	Map<CourseElementInstance, Map<ElementInstancePrefersTimeslot, Boolean>> courseToTimeSlotPreference;
	Map<CourseElementInstance, Map<ElementInstanceRequiresFeature, Boolean>> courseToFeatureRequirement;
	Map<Person, Map<PersonPrefersTimeslot, Boolean>> lecturerToTimeSlotPreference;
	Map<Room, Map<RoomPrefersTimeslot, Boolean>> roomToTimeSlotPreference;

	public ScheduleScore(Configuration configuration) {

		this.courseToRoomOverlap = new TreeMap<CourseElementInstance, Set<CourseElementInstance>>();
		this.lecturerToLecturerOverlap = new TreeMap<Person, Set<CourseElementInstance>>();

		this.courseToRoomPreference = new TreeMap<CourseElementInstance, Map<ElementInstancePrefersRoom, Boolean>>();
		this.courseToTimeSlotPreference = new TreeMap<CourseElementInstance, Map<ElementInstancePrefersTimeslot, Boolean>>();
		this.courseToFeatureRequirement = new TreeMap<CourseElementInstance, Map<ElementInstanceRequiresFeature, Boolean>>();

		this.lecturerToTimeSlotPreference = new TreeMap<Person, Map<PersonPrefersTimeslot, Boolean>>();
		this.roomToTimeSlotPreference = new TreeMap<Room, Map<RoomPrefersTimeslot, Boolean>>();

		for (CourseElementInstance course : configuration.courseList) {
			courseToRoomOverlap.put(course,
					new TreeSet<CourseElementInstance>());
			courseToRoomPreference.put(course,
					new TreeMap<ElementInstancePrefersRoom, Boolean>());
			courseToTimeSlotPreference.put(course,
					new TreeMap<ElementInstancePrefersTimeslot, Boolean>());
			courseToFeatureRequirement.put(course,
					new TreeMap<ElementInstanceRequiresFeature, Boolean>());
		}

		for (Person lecturer : configuration.lecturerList) {
			lecturerToTimeSlotPreference.put(lecturer,
					new TreeMap<PersonPrefersTimeslot, Boolean>());
			lecturerToLecturerOverlap.put(lecturer, new TreeSet<CourseElementInstance>());
		}

		for (Room room : configuration.roomList) {
			roomToTimeSlotPreference.put(room,
					new TreeMap<RoomPrefersTimeslot, Boolean>());
		}

	}

	public double getHardFitness() {
		return hardFitness;
	}

	@XmlAttribute("numberOfHardConstraints")
	public int getNumberOfHardConstraints() {
		return numberOfHardConstraints;
	}

	@XmlAttribute("numberOfHardConstraintsSatisfied")
	public int getNumberOfHardConstraintsSatisfied() {
		return numberOfHardConstraintsSatisfied;
	}

	@XmlAttribute("numberOfSoftConstraints")
	public int getNumberOfSoftConstraints() {
		return numberOfSoftConstraints;
	}

	@XmlAttribute("numberOfSoftConstraintsSatisfied")
	public int getNumberOfSoftConstraintsSatisfied() {
		return numberOfSoftConstraintsSatisfied;
	}

	public double getSoftFitness() {
		return softFitness;
	}

	@XmlAttribute("sumHardConstraints")
	public int getSumHardConstraints() {
		return sumHardConstraints;
	}

	@XmlAttribute("sumHardConstraintsSatisfied")
	public int getSumHardConstraintsSatisfied() {
		return sumHardConstraintsSatisfied;
	}

	@XmlAttribute("sumSoftConstraints")
	public int getSumSoftConstraints() {
		return sumSoftConstraints;
	}

	@XmlAttribute("sumSoftConstraintsSatisfied")
	public int getSumSoftConstraintsSatisfied() {
		return sumSoftConstraintsSatisfied;
	}

	public void setHardFitness(double hardFitness) {
		this.hardFitness = hardFitness;
	}

	public void setNumberOfHardConstraints(int numberOfHardConstraints) {
		this.numberOfHardConstraints = numberOfHardConstraints;
	}

	public void setNumberOfHardConstraintsSatisfied(int numberOfHardConstraintsSatisfied) {
		this.numberOfHardConstraintsSatisfied = numberOfHardConstraintsSatisfied;
	}

	public void setNumberOfSoftConstraints(int numberOfSoftConstraints) {
		this.numberOfSoftConstraints = numberOfSoftConstraints;
	}

	public void setNumberOfSoftConstraintsSatisfied(int numberOfSoftConstraintsSatisfied) {
		this.numberOfSoftConstraintsSatisfied = numberOfSoftConstraintsSatisfied;
	}

	public void setSoftFitness(double softFitness) {
		this.softFitness = softFitness;
	}

	public void setSumHardConstraints(int sumHardConstraints) {
		this.sumHardConstraints = sumHardConstraints;
	}

	public void setSumHardConstraintsSatisfied(int sumHardConstraintsSatisfied) {
		this.sumHardConstraintsSatisfied = sumHardConstraintsSatisfied;
	}

	public void setSumSoftConstraints(int sumSoftConstraints) {
		this.sumSoftConstraints = sumSoftConstraints;
	}

	public void setSumSoftConstraintsSatisfied(int sumSoftConstraintsSatisfied) {
		this.sumSoftConstraintsSatisfied = sumSoftConstraintsSatisfied;
	}

	public Map<CourseElementInstance, Set<CourseElementInstance>> getCourseToRoomOverlap() {
		return courseToRoomOverlap;
	}

	public Map<Person, Set<CourseElementInstance>> getlecturerToLecturerOverlap() {
		return lecturerToLecturerOverlap;
	}

	public Map<CourseElementInstance, Map<ElementInstancePrefersRoom, Boolean>> getCourseToRoomPreference() {
		return courseToRoomPreference;
	}

	public Map<CourseElementInstance, Map<ElementInstancePrefersTimeslot, Boolean>> getCourseToTimeSlotPreference() {
		return courseToTimeSlotPreference;
	}

	public Map<CourseElementInstance, Map<ElementInstanceRequiresFeature, Boolean>> getCourseToFeatureRequirement() {
		return courseToFeatureRequirement;
	}

	public Map<Person, Map<PersonPrefersTimeslot, Boolean>> getLecturerToTimeSlotPreference() {
		return lecturerToTimeSlotPreference;
	}

	public Map<Room, Map<RoomPrefersTimeslot, Boolean>> getRoomToTimeSlotPreference() {
		return roomToTimeSlotPreference;
	}

}
