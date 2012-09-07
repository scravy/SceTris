package de.fu.scetris.scheduler.model.strategy.test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map.Entry;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import de.fu.scetris.data.*;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeCourseSlot;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.TimeCourseSlot;

/**
 * 
 * @author Hagen Mahnke
 * @author Konrad Reiche
 */
public class TestScheduleImpl {

    private RelationManager relationManager = new RelationManager(null);
    private Configuration configuration;
    private List<Room> roomList;

    @Before
    public void initialize() {

	int numberOfTimeSlots = 38;
	int numberOfRooms = 3;
	int numberofCourses = 5;

	roomList = new ArrayList<Room>(numberOfRooms);

	List<CourseElementInstance> courseList = new ArrayList<CourseElementInstance>(
		numberofCourses);

	List<ElementInstancePrefersTimeslot> courseOnePreferredTimeSlotList = new ArrayList<ElementInstancePrefersTimeslot>();
	List<ElementInstancePrefersTimeslot> courseTwoPreferredTimeSlotList = new ArrayList<ElementInstancePrefersTimeslot>();
	List<ElementInstancePrefersTimeslot> courseThreePreferredTimeSlotList = new ArrayList<ElementInstancePrefersTimeslot>();
	List<ElementInstancePrefersTimeslot> courseFourPreferredTimeSlotList = new ArrayList<ElementInstancePrefersTimeslot>();
	List<ElementInstancePrefersTimeslot> courseFivePreferredTimeSlotList = new ArrayList<ElementInstancePrefersTimeslot>();

	List<ElementInstancePrefersRoom> courseOnePrefersRoomList = new ArrayList<ElementInstancePrefersRoom>();
	List<ElementInstancePrefersRoom> courseTwoPrefersRoomList = new ArrayList<ElementInstancePrefersRoom>();
	List<ElementInstancePrefersRoom> courseThreePrefersRoomList = new ArrayList<ElementInstancePrefersRoom>();
	List<ElementInstancePrefersRoom> courseFourPrefersRoomList = new ArrayList<ElementInstancePrefersRoom>();
	List<ElementInstancePrefersRoom> courseFivePrefersRoomList = new ArrayList<ElementInstancePrefersRoom>();

	List<ElementInstanceRequiresFeature> courseOneRequiresFeatureList = new ArrayList<ElementInstanceRequiresFeature>();
	List<ElementInstanceRequiresFeature> courseTwoRequiresFeatureList = new ArrayList<ElementInstanceRequiresFeature>();
	List<ElementInstanceRequiresFeature> courseThreeRequiresFeatureList = new ArrayList<ElementInstanceRequiresFeature>();
	List<ElementInstanceRequiresFeature> courseFourRequiresFeatureList = new ArrayList<ElementInstanceRequiresFeature>();
	List<ElementInstanceRequiresFeature> courseFiveRequiresFeatureList = new ArrayList<ElementInstanceRequiresFeature>();

	// initialize Rooms
	for (int i = 0; i < numberOfRooms; ++i) {
	    roomList.add(i, relationManager.newRoom(null, null));
	}

	// initialize courseList
	AcademicTerm academicTerm = relationManager.newAcademicTerm(null, null,
		null, null, null);
	Department department = relationManager.newDepartment(null);
	Person programManager = relationManager.newPerson(null, null, null,
		null);
	Program program = relationManager.newProgram(academicTerm, department,
		programManager);

	Person mainLecturer = relationManager.newPerson(null, null, null, null);

	Course course = relationManager.newCourse(null);
	CourseElement courseElement = relationManager.newCourseElement(course,
		10);
	CourseInstance courseInstance = relationManager.newCourseInstance(program, course, null, null, mainLecturer);

	courseList.add(relationManager.newCourseElementInstance(courseInstance,
		courseElement, 2));
	courseList.add(relationManager.newCourseElementInstance(courseInstance,
		courseElement, 2));
	courseList.add(relationManager.newCourseElementInstance(courseInstance,
		courseElement, 2));
	courseList.add(relationManager.newCourseElementInstance(courseInstance,
		courseElement, 1));
	courseList.add(relationManager.newCourseElementInstance(courseInstance,
		courseElement, 3));

	// preferred Rooms
	courseOnePrefersRoomList.add(relationManager
		.newElementInstancePrefersRoom(courseList.get(0),
			roomList.get(0), 100));
	courseTwoPrefersRoomList.add(relationManager
		.newElementInstancePrefersRoom(courseList.get(1),
			roomList.get(0), 100));

	// preferred TimeSlots
	Day day = relationManager.newDay(null);
	Timeslot timeSlot1 = relationManager.newTimeslot(day);
	Timeslot timeSlot2 = relationManager.newTimeslot(day);

	courseOnePreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(0),
			timeSlot1, 100));
	courseOnePreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(0),
			timeSlot1, 100));
	courseTwoPreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(1),
			timeSlot1, 100));
	courseTwoPreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(1),
			timeSlot2, 100));
	courseFourPreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(0),
			timeSlot1, 100));
	courseFourPreferredTimeSlotList.add(relationManager
		.newElementInstancePrefersTimeslot(courseList.get(0),
			timeSlot1, 100));

	// required Features
	Feature feature = relationManager.newFeature(null);
	
	courseOneRequiresFeatureList.add(relationManager.newElementInstanceRequiresFeature(courseList.get(0), feature, 100, 1, 2));
	courseTwoRequiresFeatureList.add(relationManager.newElementInstanceRequiresFeature(courseList.get(1), feature, 100, 1, 2));
	courseThreeRequiresFeatureList.add(relationManager.newElementInstanceRequiresFeature(courseList.get(2), feature, 100, 1, 2));
	courseFourRequiresFeatureList.add(relationManager.newElementInstanceRequiresFeature(courseList.get(3), feature, 100, 1, 2));
	courseFiveRequiresFeatureList.add(relationManager.newElementInstanceRequiresFeature(courseList.get(4), feature, 100, 1, 2));

	// ProposedSchedulingList is null as this is the first scheduling
	configuration = new Configuration(numberOfTimeSlots, 0, null,
		numberOfRooms, numberofCourses, roomList, null, courseList,
		null, null);

    }

    /**
     * ?
     * <p>
     * Postcondition: schedule.areConstraintsSatisfied has a length equals to
     * the number of courses
     * <p>
     * Postcondition: schedule.areConstraintsSatisfied values have a length
     * equal to the number of constraints for the courseTable which is the key.
     * <p>
     * Postcondition: Every value list is filled with booleans set to false.
     */
    @Test
    public void testInitializeAreConstraintsSatisfied() {

	ScheduleImpl schedule = new ScheduleImpl(configuration);

	Assert.assertTrue(schedule.getCourseToConstraints().size() == configuration.numberOfCourses);

	for (Entry<CourseElementInstance, List<Boolean>> entry : schedule
		.getCourseToConstraints().entrySet()) {

	    // TODO: getRelation... waiting
	    int n = 0;
	    n += entry.getKey().
	    n += entry.getKey().getPreferredTimeSlotList().size();
	    n += entry.getKey().getRequiredFeatureList().size();

	    Assert.assertTrue(entry.getValue().size() == n);

	    for (Boolean b : entry.getValue()) {
		Assert.assertFalse(b);
	    }
	}
    }

    /**
     * Postcondition: All rooms are set.
     * 
     * Postcondition: The number of time slots corresponds to the number of time
     * slots as defined by the configuration.
     * 
     * Postconditon: Every course list is not null and empty.
     */
    @Test
    public void testInitializeRoomTimeCourseSlotList() {

	ScheduleImpl schedule = new ScheduleImpl(configuration);

	List<Room> scheduleRooms = new ArrayList<Room>();

	for (RoomTimeCourseSlot roomTimeCourseSlot : schedule
		.getRoomTimeCourseSlotList()) {
	    scheduleRooms.add(roomTimeCourseSlot.getRoom());
	}

	for (Room room : roomList) {
	    Assert.assertTrue(scheduleRooms.contains(room));
	}

	Assert.assertTrue(scheduleRooms.size() == configuration.numberOfRooms);

	for (RoomTimeCourseSlot roomTimeCourseSlot : schedule
		.getRoomTimeCourseSlotList()) {

	    Assert.assertTrue(roomTimeCourseSlot.getTimeCourseSlots().size() == configuration.numberOfTimeSlots);

	    for (TimeCourseSlot timeCourseSlot : roomTimeCourseSlot
		    .getTimeCourseSlots()) {
		Assert.assertTrue(timeCourseSlot.getCourseListSize() == 0);
	    }

	}

    }
}
