package de.fu.scetris.scheduler.model.strategy.implementations.genetic;

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

import de.fu.bakery.orm.java.DatabaseException;
import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.ElementInstanceRequiresFeature;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.RoomProvidesFeature;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.RoomComparator;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.model.strategy.interfaces.Setup;

public class GreedySetupImpl implements Setup {

	Map<Feature, Set<Room>> featureToRoomSet;
	Map<Room, Map<Feature, Integer>> roomToFeatureQuanity;

	Random random;
	Configuration configuration;

	public GreedySetupImpl(Random random, Configuration configuration)
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

	@Override
	public void initialize(Schedule schedule, Configuration config,
			boolean isResumedSchedule) throws Exception {

		List<CourseElementInstance> courseList = new ArrayList<CourseElementInstance>();
		courseList.addAll(config.courseList);
		Collections.shuffle(courseList,random);
		
		for (CourseElementInstance courseElementinstance : courseList) {

			ProposedScheduling proposedScheduling = config.courseToProposal
					.get(courseElementinstance);

			if (proposedScheduling != null
					&& proposedScheduling.getTimeslot() != null
					&& isResumedSchedule) {

				Room room = proposedScheduling.getRoom();
				int timeSlotIndex = config.timeSlotList
						.indexOf(proposedScheduling.getTimeslot());
				int roomIndex = config.roomList.indexOf(room);
				((ScheduleImpl) schedule).addCourse(courseElementinstance,
						roomIndex, timeSlotIndex);
			} else {

				Set<Room> featureCoveringRoomSet = new TreeSet<Room>();
				featureCoveringRoomSet.addAll(config.roomList);

				for (Feature requiredFeature : courseElementinstance
						.objectsOfElementInstanceRequiresFeature()) {

					featureCoveringRoomSet.retainAll(featureToRoomSet
							.get(requiredFeature));

					if (featureCoveringRoomSet.isEmpty()) {
						throw new Exception("not scheduleable");
					}
				}

				SortedSet<Room> featureSortedRoomSet = new TreeSet<Room>(
						new RoomComparator(random));
				featureSortedRoomSet.addAll(featureCoveringRoomSet);

				for (Iterator<Room> it = featureSortedRoomSet.iterator(); it
						.hasNext();) {
					Room room = it.next();

					for (ElementInstanceRequiresFeature requiredFeatures : courseElementinstance
							.whereSubjectOfElementInstanceRequiresFeature()) {

						if (roomToFeatureQuanity.get(room)
								.get(requiredFeatures.getFeature()) < requiredFeatures
								.getQuantityMin()) {

							it.remove();
							continue;
						}
					}
				}

				if (featureSortedRoomSet.isEmpty()) {
					throw new Exception("not scheduleable");
				}

				chooseTimeSlot(schedule, featureSortedRoomSet,
						courseElementinstance);

			}
		}

	}

	public void chooseTimeSlot(Schedule schedule,
			SortedSet<Room> featureSortedRoomSet, CourseElementInstance course) {

		ScheduleImpl scheduleImpl = (ScheduleImpl) schedule;

		for (Room room : featureSortedRoomSet) {

			int roomIndex = 0;
			for (RoomTimeCourseSlot roomTimeCourseSlot : scheduleImpl
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
						hasEnoughFreeTimeSlots &= scheduleImpl.getCourseListAt(
								roomIndex, absoluteTimeSlot + i).size() == 0;						
					}
					
				}

				if (hasEnoughFreeTimeSlots && hasEnoughTimeSlotsUntilEndOfDay) {
					scheduleImpl.addCourse(course, roomIndex, absoluteTimeSlot);
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
