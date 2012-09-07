package de.fu.scetris.scheduler.genetic;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeMap;
import java.util.TreeSet;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.RoomComparator;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

public class GreedySetupImpl implements Setup {

	Map<Feature, Set<Room>> featureToRoomSet;
	Map<Room, Map<Feature, Integer>> roomToFeatureQuanity;

	Random random;
	Configuration configuration;

	/**
	 * Initialize {@link GreedySetupImpl} in order to fasten the course
	 * allocation.
	 * <p>
	 * {@link Feature}s are mapped to a set of Rooms providing the
	 * {@link Feature} and Rooms are mapped to their {@link Feature}s and the
	 * quantity of {@link Feature} they provide.
	 * 
	 * @param random
	 *            random generator
	 * @param configuration
	 *            Configuration is set up when the data is read from the
	 *            database.
	 * @throws DatabaseException
	 */
	GreedySetupImpl(Random random, Configuration configuration)
			throws DatabaseException {
		super();
		this.random = random;
		this.featureToRoomSet = new TreeMap<Feature, Set<Room>>();
		this.roomToFeatureQuanity = new TreeMap<Room, Map<Feature, Integer>>();
		this.configuration = configuration;

		for (Room room : configuration.roomList) {

			List<Feature> providedFeatureList = room
					.objectsOfRoomProvidesFeature();

			for (Feature providedFeature : providedFeatureList) {

				Set<Room> roomSet;
				if ((roomSet = featureToRoomSet.get(providedFeature)) == null) {
					featureToRoomSet.put(providedFeature, new TreeSet<Room>());
					roomSet = featureToRoomSet.get(providedFeature);
				}
				roomSet.add(room);
			}

			for (RoomProvidesFeature roomProvidesFeature : room
					.whereSubjectOfRoomProvidesFeature()) {

				if ((roomToFeatureQuanity.get(room)) == null) {
					roomToFeatureQuanity.put(room,
							new TreeMap<Feature, Integer>());
				}

				roomToFeatureQuanity.get(room).put(
						roomProvidesFeature.getFeature(),
						roomProvidesFeature.getQuantity());
			}
		}
	}

	/**
	 * Initializes the schedule with allocating the courses.
	 * <p>
	 * The courses are allocated as follows:
	 * 
	 * (1) The order of the list of course element instances are randomly
	 * permuted in order to gain another course allocation with every
	 * initialization.
	 * 
	 * (2) If there is already a {@link ProposedScheduling} and this
	 * initialization is due to the resume of a scheduling the proposed room and
	 * time slot is chosen instead.
	 * 
	 * (3) Every course with a room or time slot preferences is allocated
	 * directly into the appropriate room or time slot. Further these courses
	 * are removed from the list as they are already allocated.
	 * 
	 * (4) The remaining courses are iterated in order to allocated one course
	 * after the other.
	 * 
	 * (5) First, a number of room candidates is calculated by the feature
	 * requirements of the given course. If the course has no feature
	 * requirements at all every room will be appropriate.
	 * <p>
	 * Otherwise every room will be added to a set of rooms which must cover
	 * every required feature. The set of candidate solution is sorted. This way
	 * the room with the least feature quantity surplus can be selected. This
	 * way the course are allocated as economically as possible.
	 * <p>
	 * The course required features are iterated and in every iteration step the
	 * set of feature covering rooms is intersected with the map indicating
	 * which rooms actually provide these features.
	 * 
	 * (6) Second, the set of feature covering rooms is filtered again in order
	 * to see if the rooms also provide sufficient quantity of the features. The
	 * procedure is analog due to step 5 but using the room to feature quantity
	 * map provided by {@link GreedySetupImpl}.
	 * 
	 * (7) The set of candidate solutions is passed to the method choosing the
	 * time slot. The first room of the candidate solution will be selected due
	 * to sorting reasons.
	 * <p>
	 * The time slot is chosen by the following logic: the time slots of every
	 * day are iterated and in the second inner loop the days itself are
	 * iterated. This way the courses are allocated with the least possible
	 * gaps, at the earliest possible daytime and along the week.
	 * <p>
	 * If there is already a course allocated at the current time slot the next
	 * time slot is checked in order to avoid course overlapping in time and
	 * space.
	 * 
	 * @throws NotScheduleableException
	 * 
	 */
	@Override
	public void initialize(Schedule schedule, Configuration config,
						   boolean isResumedSchedule) throws SchedulingException,
			NotScheduleableException {
		
		try {

			ScheduleImpl scheduleImpl = (ScheduleImpl) schedule;
			List<CourseElementInstance> courseList = new ArrayList<CourseElementInstance>();
			courseList.addAll(config.courseList);
			Collections.shuffle(courseList, random);

			allocateCoursesWithPreferences(courseList, scheduleImpl);

			for (CourseElementInstance course : courseList) {

				if (isResumedSchedule) {
					allocateProposedScheduling(course, scheduleImpl);
				} else {

					SortedSet<Room> roomCandidates = generateRoomCandidates(course);
					chooseTimeSlotAndAllocateCourse(scheduleImpl, roomCandidates,
							course);
				}
			}
		} catch (DatabaseException e) {
			throw new SchedulingException(e);
		}
	}

	private void allocateProposedScheduling(CourseElementInstance course,
											ScheduleImpl schedule) throws DatabaseException {

		ProposedScheduling proposedScheduling = configuration.courseToProposal
				.get(course);

		if (proposedScheduling != null
				&& proposedScheduling.getTimeslot() != null) {

			Room room = proposedScheduling.getRoom();
			int timeSlotIndex = configuration.timeSlotList
					.indexOf(proposedScheduling.getTimeslot());
			int roomIndex = configuration.roomList.indexOf(room);
			schedule.addCourse(course, roomIndex, timeSlotIndex);
		}
	}

	private void allocateCoursesWithPreferences(
												List<CourseElementInstance> courseList,
												ScheduleImpl schedule)
			throws DatabaseException, NotScheduleableException {

		for (Iterator<CourseElementInstance> it = courseList.iterator(); it
				.hasNext();) {

			CourseElementInstance course = it.next();

			SortedSet<Room> roomCandidateSet = new TreeSet<Room>(
					new RoomComparator(random, configuration.roomList, true));

			Room preferredRoom = course.objectOfElementInstancePrefersRoom();
			Timeslot preferredTimeSlot = course
					.objectOfElementInstancePrefersTimeslot();
			
			if (!configuration.roomList.contains(preferredRoom)) {
				preferredRoom = null;
			}

			if (preferredRoom != null && preferredTimeSlot != null) {

				int timeSlotIndex = configuration.timeSlotList
						.indexOf(preferredTimeSlot);

				int roomIndex = configuration.roomList.indexOf(preferredRoom);
				
				schedule.addCourse(course, roomIndex, timeSlotIndex);
				it.remove();

			} else if (preferredRoom != null && preferredTimeSlot == null) {

				roomCandidateSet.add(preferredRoom);
				chooseTimeSlotAndAllocateCourse(schedule, roomCandidateSet,
						course);
				it.remove();

			} else if (preferredRoom == null && preferredTimeSlot != null) {

				int timeSlotIndex = configuration.timeSlotList
						.indexOf(preferredTimeSlot);

				int roomIndex = configuration.roomList
						.indexOf(generateRoomCandidates(course).first());
				
				schedule.addCourse(course, roomIndex, timeSlotIndex);
				it.remove();
			} else {
				// do nothing
			}
		}
	}

	private SortedSet<Room> generateRoomCandidates(CourseElementInstance course)
			throws DatabaseException, NotScheduleableException {

		SortedSet<Room> featureCoveringRoomSet = new TreeSet<Room>(
				new RoomComparator(random, configuration.roomList, true));
		featureCoveringRoomSet.addAll(configuration.roomList);

		List<Feature> requiredFeatureList = course
				.objectsOfElementInstanceRequiresFeature();

		for (Feature requiredFeature : requiredFeatureList) {

			Set<Room> roomSet = featureToRoomSet.get(requiredFeature);
			if (roomSet == null) {
				throw new NotScheduleableException("For a given Course"
						+ " Element Instance the required feature"
						+ " is missing.");
			}

			// removed all rooms from the result set which do no have this
			// feature
			featureCoveringRoomSet.retainAll(featureToRoomSet
					.get(requiredFeature));

			if (featureCoveringRoomSet.isEmpty()) {
				throw new NotScheduleableException("For a given Course"
						+ " Element Instance there is no"
						+ " room satisfying all constraints.");
			}
		}

		for (Iterator<Room> it = featureCoveringRoomSet.iterator(); it
				.hasNext();) {

			Room room = it.next();

			for (ElementInstanceRequiresFeature requiredFeatures : course
					.whereSubjectOfElementInstanceRequiresFeature()) {

				if (roomToFeatureQuanity.get(room).get(
						requiredFeatures.getFeature()) < requiredFeatures
						.getQuantityMin()) {

					it.remove();
					continue;
				}
			}
		}

		if (featureCoveringRoomSet.isEmpty()) {
			throw new NotScheduleableException(
					"There is no room covering every"
							+ " feature with sufficient quantity.");
		}

		return featureCoveringRoomSet;

	}

	/**
	 * 
	 * 
	 * @param schedule
	 * @param roomCandidates
	 * @param course
	 * @throws DatabaseException
	 */
	private void chooseTimeSlotAndAllocateCourse(ScheduleImpl schedule,
												 SortedSet<Room> roomCandidates,
												 CourseElementInstance course)
			throws DatabaseException {

		for (Room room : roomCandidates) {

			int roomIndex = 0;
			for (RoomTimeCourseSlot roomTimeCourseSlot : schedule
					.getRoomTimeCourseSlotList()) {

				if (roomTimeCourseSlot.room.equals(room)) {
					break;
				}
				++roomIndex;
			}

			int currentDay = 0;
			int relativeTimeSlot = 0;
			int absoluteTimeSlot = 0;
			int lastDay = configuration.numberOfDays - 1;
			int latestTimeSlot = 0;

			for (int i = 0; i < configuration.numberOfDays; ++i) {
				latestTimeSlot += configuration.timeSlotsPerDay[i];
			}
			--latestTimeSlot;

			while (currentDay != lastDay || absoluteTimeSlot != latestTimeSlot) {

				boolean hasEnoughTimeSlotsUntilEndOfDay = relativeTimeSlot
						+ course.getDuration() <= configuration.timeSlotsPerDay[currentDay];
				boolean hasEnoughFreeTimeSlots = true;
				for (int i = 0; i < course.getDuration(); ++i) {

					if (absoluteTimeSlot + i >= configuration.numberOfTimeSlots) {
						hasEnoughFreeTimeSlots &= false;
					} else {
						hasEnoughFreeTimeSlots &= schedule.getCourseListAt(
								roomIndex, absoluteTimeSlot + i).size() == 0;
					}

				}

				if (hasEnoughFreeTimeSlots
						&& hasEnoughTimeSlotsUntilEndOfDay
						&& schedule
								.getCourseListAt(roomIndex, absoluteTimeSlot)
								.isEmpty()) {

					schedule.addCourse(course, roomIndex, absoluteTimeSlot);
					return;
				} else {

					if (currentDay == lastDay) {
						++relativeTimeSlot;
						absoluteTimeSlot = 0 + relativeTimeSlot;
						currentDay = 0;
					} else {
						absoluteTimeSlot += configuration.timeSlotsPerDay[currentDay];
						++currentDay;
					}

				}

			}
		}

	}
}
