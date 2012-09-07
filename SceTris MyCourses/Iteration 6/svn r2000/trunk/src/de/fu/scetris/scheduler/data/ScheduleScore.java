package de.fu.scetris.scheduler.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstancePrefersRoom;
import de.fu.scetris.data.ElementInstancePrefersTimeslot;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.PersonPrefersTimeslot;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomPrefersTimeslot;
import de.fu.scetris.data.Year;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

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
	Map<CourseElementInstance, Set<ElementInstancePrefersRoom>> courseToRoomPreference;
	Map<CourseElementInstance, Set<ElementInstancePrefersTimeslot>> courseToTimeSlotPreference;
	Map<CourseElementInstance, Set<ElementInstanceRequiresFeature>> courseToFeatureRequirement;
	Map<Person, Set<PersonPrefersTimeslot>> lecturerToTimeSlotPreference;
	Map<Room, Set<RoomPrefersTimeslot>> roomToTimeSlotPreference;
	Map<Year, Set<CourseElementInstance>> yearConstraintToConflictedCourses;

	public ScheduleScore(Configuration configuration) {

		this.courseToRoomOverlap = new TreeMap<CourseElementInstance, Set<CourseElementInstance>>();
		this.lecturerToLecturerOverlap = new TreeMap<Person, Set<CourseElementInstance>>();

		this.courseToRoomPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersRoom>>();
		this.courseToTimeSlotPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersTimeslot>>();
		this.courseToFeatureRequirement = new TreeMap<CourseElementInstance, Set<ElementInstanceRequiresFeature>>();

		this.lecturerToTimeSlotPreference = new TreeMap<Person, Set<PersonPrefersTimeslot>>();
		this.roomToTimeSlotPreference = new TreeMap<Room, Set<RoomPrefersTimeslot>>();

		this.yearConstraintToConflictedCourses = new HashMap<Year, Set<CourseElementInstance>>();
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

	public void setNumberOfHardConstraintsSatisfied(
			int numberOfHardConstraintsSatisfied) {
		this.numberOfHardConstraintsSatisfied = numberOfHardConstraintsSatisfied;
	}

	public void setNumberOfSoftConstraints(int numberOfSoftConstraints) {
		this.numberOfSoftConstraints = numberOfSoftConstraints;
	}

	public void setNumberOfSoftConstraintsSatisfied(
			int numberOfSoftConstraintsSatisfied) {
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

	@XmlCollection(value = "lecturerOverlap")
	public Map<Person, Set<CourseElementInstance>> getlecturerToLecturerOverlap() {
		return lecturerToLecturerOverlap;
	}

	@XmlCollection(value = "courseRoomPreference")
	public Map<CourseElementInstance, Set<ElementInstancePrefersRoom>> getCourseToRoomPreference() {
		return courseToRoomPreference;
	}

	@XmlCollection(value = "courseTimeslotPreference")
	public Map<CourseElementInstance, Set<ElementInstancePrefersTimeslot>> getCourseToTimeSlotPreference() {
		return courseToTimeSlotPreference;
	}

	@XmlCollection(value = "courseFeatureRequirement")
	public Map<CourseElementInstance, Set<ElementInstanceRequiresFeature>> getCourseToFeatureRequirement() {
		return courseToFeatureRequirement;
	}

	@XmlCollection(value = "lecturerTimeslotPreference")
	public Map<Person, Set<PersonPrefersTimeslot>> getLecturerToTimeSlotPreference() {
		return lecturerToTimeSlotPreference;
	}

	@XmlCollection(value = "roomTimeslotPreference")
	public Map<Room, Set<RoomPrefersTimeslot>> getRoomToTimeSlotPreference() {
		return roomToTimeSlotPreference;
	}

	@XmlCollection(value = "yearConstraintToConflictedCourses")
	public Map<Year, Set<CourseElementInstance>> getYearConstraintToConflictedCourses() {
		return yearConstraintToConflictedCourses;
	}

	@Override
	public String toString() {

		StringBuilder stringBuilder = new StringBuilder();

		stringBuilder.append("H: " + numberOfHardConstraintsSatisfied + "/"
				+ numberOfHardConstraints);
		stringBuilder.append("\n");
		stringBuilder.append("S: " + numberOfSoftConstraintsSatisfied + "/"
				+ numberOfSoftConstraints);
		stringBuilder.append("\n");
		stringBuilder.append(courseToRoomOverlap.size() + " "
				+ courseToRoomOverlap);
		stringBuilder.append("\n");
		stringBuilder.append(lecturerToLecturerOverlap.size() + " "
				+ lecturerToLecturerOverlap);
		stringBuilder.append("\n");
		stringBuilder.append(courseToRoomPreference.size() + " "
				+ courseToRoomPreference);
		stringBuilder.append("\n");
		stringBuilder.append(courseToTimeSlotPreference.size() + " "
				+ courseToTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(courseToFeatureRequirement.size() + " "
				+ courseToFeatureRequirement);
		stringBuilder.append("\n");
		stringBuilder.append(lecturerToTimeSlotPreference.size() + " "
				+ lecturerToTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(roomToTimeSlotPreference.size() + " "
				+ roomToTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(yearConstraintToConflictedCourses.size() + " "
				+ yearConstraintToConflictedCourses);
		stringBuilder.append("\n");

		return stringBuilder.toString();
	}

	@XmlCollection(value = "allViolatedConstraints")
	public List<Map<?, ?>> getAllViolatedConstraints() {

		List<Map<?, ?>> allViolatedConstraints = new ArrayList<Map<?, ?>>();

		allViolatedConstraints.add(courseToFeatureRequirement);
		allViolatedConstraints.add(courseToRoomOverlap);
		allViolatedConstraints.add(courseToRoomPreference);
		allViolatedConstraints.add(courseToTimeSlotPreference);
		allViolatedConstraints.add(lecturerToLecturerOverlap);
		allViolatedConstraints.add(lecturerToTimeSlotPreference);
		allViolatedConstraints.add(roomToTimeSlotPreference);

		return allViolatedConstraints;
	}

}
