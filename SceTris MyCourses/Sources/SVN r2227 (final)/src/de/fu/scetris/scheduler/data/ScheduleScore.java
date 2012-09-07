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
	Map<Room, Set<CourseElementInstance>> courseConflictsRoom;
	Map<Person, Set<CourseElementInstance>> courseConflictsLecturer;

	// hard and soft constraints both, can be distinguished by reading the
	// priority attribute
	Map<CourseElementInstance, Set<ElementInstancePrefersRoom>> courseConflictsRoomPreference;
	Map<CourseElementInstance, Set<ElementInstancePrefersTimeslot>> courseConflictsTimeSlotPreference;
	Map<CourseElementInstance, Set<ElementInstanceRequiresFeature>> courseConflictsFeatureRequirement;
	Map<Person, Set<PersonPrefersTimeslot>> lecturerConflictsTimeSlotPreference;
	Map<Room, Set<RoomPrefersTimeslot>> roomConflictsTimeSlotPreference;
	Map<Year, Set<CourseElementInstance>> courseConflictsYearConstraint;

	public ScheduleScore(Configuration configuration) {

		this.courseConflictsRoom = new TreeMap<Room, Set<CourseElementInstance>>();
		this.courseConflictsLecturer = new TreeMap<Person, Set<CourseElementInstance>>();

		this.courseConflictsRoomPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersRoom>>();
		this.courseConflictsTimeSlotPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersTimeslot>>();
		this.courseConflictsFeatureRequirement = new TreeMap<CourseElementInstance, Set<ElementInstanceRequiresFeature>>();

		this.lecturerConflictsTimeSlotPreference = new TreeMap<Person, Set<PersonPrefersTimeslot>>();
		this.roomConflictsTimeSlotPreference = new TreeMap<Room, Set<RoomPrefersTimeslot>>();

		this.courseConflictsYearConstraint = new HashMap<Year, Set<CourseElementInstance>>();
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

	public Map<Room, Set<CourseElementInstance>> getCourseConflictsRoom() {
		return courseConflictsRoom;
	}

	@XmlCollection(value = "lecturerOverlap")
	public Map<Person, Set<CourseElementInstance>> getCourseConflictsLecturer() {
		return courseConflictsLecturer;
	}

	@XmlCollection(value = "courseRoomPreference")
	public Map<CourseElementInstance, Set<ElementInstancePrefersRoom>> getCourseConflictsRoomPreference() {
		return courseConflictsRoomPreference;
	}

	@XmlCollection(value = "courseTimeslotPreference")
	public Map<CourseElementInstance, Set<ElementInstancePrefersTimeslot>> getCourseConflictsTimeSlotPreference() {
		return courseConflictsTimeSlotPreference;
	}

	@XmlCollection(value = "courseFeatureRequirement")
	public Map<CourseElementInstance, Set<ElementInstanceRequiresFeature>> getCourseConflictsFeatureRequirement() {
		return courseConflictsFeatureRequirement;
	}

	@XmlCollection(value = "lecturerTimeslotPreference")
	public Map<Person, Set<PersonPrefersTimeslot>> getLecturerConflictsTimeSlotPreference() {
		return lecturerConflictsTimeSlotPreference;
	}

	@XmlCollection(value = "roomTimeslotPreference")
	public Map<Room, Set<RoomPrefersTimeslot>> getRoomConflictsTimeSlotPreference() {
		return roomConflictsTimeSlotPreference;
	}

	@XmlCollection(value = "courseConflictsYearConstraint")
	public Map<Year, Set<CourseElementInstance>> getCourseConflictsYearConstraint() {
		return courseConflictsYearConstraint;
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
		stringBuilder.append(courseConflictsRoom.size() + " "
				+ courseConflictsRoom);
		stringBuilder.append("\n");
		stringBuilder.append(courseConflictsLecturer.size() + " "
				+ courseConflictsLecturer);
		stringBuilder.append("\n");
		stringBuilder.append(courseConflictsRoomPreference.size() + " "
				+ courseConflictsRoomPreference);
		stringBuilder.append("\n");
		stringBuilder.append(courseConflictsTimeSlotPreference.size() + " "
				+ courseConflictsTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(courseConflictsFeatureRequirement.size() + " "
				+ courseConflictsFeatureRequirement);
		stringBuilder.append("\n");
		stringBuilder.append(lecturerConflictsTimeSlotPreference.size() + " "
				+ lecturerConflictsTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(roomConflictsTimeSlotPreference.size() + " "
				+ roomConflictsTimeSlotPreference);
		stringBuilder.append("\n");
		stringBuilder.append(courseConflictsYearConstraint.size() + " "
				+ courseConflictsYearConstraint);
		stringBuilder.append("\n");

		return stringBuilder.toString();
	}

	@XmlCollection(value = "allViolatedConstraints")
	public List<Map<?, ?>> getAllViolatedConstraints() {

		List<Map<?, ?>> allViolatedConstraints = new ArrayList<Map<?, ?>>();

		allViolatedConstraints.add(courseConflictsFeatureRequirement);
		allViolatedConstraints.add(courseConflictsRoom);
		allViolatedConstraints.add(courseConflictsRoomPreference);
		allViolatedConstraints.add(courseConflictsTimeSlotPreference);
		allViolatedConstraints.add(courseConflictsLecturer);
		allViolatedConstraints.add(lecturerConflictsTimeSlotPreference);
		allViolatedConstraints.add(roomConflictsTimeSlotPreference);

		return allViolatedConstraints;
	}

	public void clearConstraintMaps() {
		this.courseConflictsRoom = new TreeMap<Room, Set<CourseElementInstance>>();
		this.courseConflictsLecturer = new TreeMap<Person, Set<CourseElementInstance>>();

		this.courseConflictsRoomPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersRoom>>();
		this.courseConflictsTimeSlotPreference = new TreeMap<CourseElementInstance, Set<ElementInstancePrefersTimeslot>>();
		this.courseConflictsFeatureRequirement = new TreeMap<CourseElementInstance, Set<ElementInstanceRequiresFeature>>();

		this.lecturerConflictsTimeSlotPreference = new TreeMap<Person, Set<PersonPrefersTimeslot>>();
		this.roomConflictsTimeSlotPreference = new TreeMap<Room, Set<RoomPrefersTimeslot>>();

		this.courseConflictsYearConstraint = new HashMap<Year, Set<CourseElementInstance>>();
	}

}
