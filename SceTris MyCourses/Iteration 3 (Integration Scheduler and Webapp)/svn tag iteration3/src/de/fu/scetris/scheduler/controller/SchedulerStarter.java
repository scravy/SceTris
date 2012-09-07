package de.fu.scetris.scheduler.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.Map.Entry;
import java.util.TreeSet;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Day;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.data.TimeSlotComparator;
import de.fu.scetris.scheduler.model.strategy.implementations.AlgorithmImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.ConcreteFactory;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.scetris.scheduler.web.RoomTimetable;
import de.fu.scetris.scheduler.web.RoomTimetable.DayTableEntry;
import de.fu.scetris.scheduler.web.RoomTimetable.DayTableEntry.TimetableEntry;
import de.fu.weave.orm.DatabaseException;

/**
 * 
 * Starts the scheduling on behalf of the current database state.
 * 
 * @author Konrad Reiche
 * 
 */
public class SchedulerStarter implements Runnable {

	private RelationManager relationManager;
	private AlgorithmImpl algorithm;

	de.fu.weave.orm.Filter[] noFilter = {};

	public Map<Room, RoomTimetable> roomTimetableMap;

	public SchedulerStarter(RelationManager relationManager) throws ParserConfigurationException, IOException,
			SAXException, DatabaseException {

		this.relationManager = relationManager;
		roomTimetableMap = new TreeMap<Room, RoomTimetable>();

		// TODO add filter to distinguish for lecturers

		List<Room> roomList = (List<Room>) relationManager.getRoom(noFilter);
		List<Person> lecturerList = (List<Person>) relationManager
				.getPerson(noFilter);
		List<CourseElementInstance> courseElementInstanceList = (List<CourseElementInstance>) relationManager
				.getCourseElementInstance(noFilter);
		List<Timeslot> timeSlotList = (List<Timeslot>) relationManager
				.getTimeslot(noFilter);
		List<Feature> featureList = (List<Feature>) relationManager
				.getFeature(noFilter);
		List<ProposedScheduling> proposedSchedulingList = (List<ProposedScheduling>) relationManager
				.getProposedScheduling(noFilter);

		Configuration configuration = new Configuration(roomList, lecturerList,
				courseElementInstanceList, timeSlotList, featureList,
				proposedSchedulingList);

		AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
				0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);

		algorithm = (AlgorithmImpl) ConcreteFactory.getInstance()
				.createAlgorithm(configuration, algorithmConfiguration);
	}

	@Override
	public void run() {

		try {
			algorithm.start();
			setElementInstanceTakesPlaceInRoom(algorithm.getBestSchedule());
			updateRoomTimeTables();
			System.out.println(((ScheduleImpl)algorithm.getBestSchedule()).printSchedule());
		} catch (DatabaseException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	private void updateRoomTimeTables() throws DatabaseException {

		
		
		List<Room> roomList = (List<Room>) relationManager.getRoom(noFilter);
		List<Timeslot> timeSlotList = (List<Timeslot>) relationManager
				.getTimeslot(noFilter);
		List<CourseElementInstance> courseElementInstanceList = (List<CourseElementInstance>) relationManager
				.getCourseElementInstance(noFilter);

		System.out.println("huhu");
		System.out.println(roomList.size());
		
		RoomTimetable roomTimetable;
		List<DayTableEntry> timeDayEntryList = new ArrayList<DayTableEntry>();
		List<TimetableEntry> timetableEntryList = new ArrayList<TimetableEntry>();
		Collections.sort(timeSlotList, new TimeSlotComparator());
		
		int i = 1;
		
		for (Room room : roomList) {
			
			for (Timeslot timeSlot : timeSlotList) {
							
				timetableEntryList.add(new TimetableEntry(timeSlot.getId(),
						timeSlot.getStartingTime(), null, null));
				
				if (i % 12 == 0) {	
					timeDayEntryList.add(new DayTableEntry(timetableEntryList, timeSlot.getDay()));
					timetableEntryList = new ArrayList<TimetableEntry>();
					System.out.println(timeDayEntryList.size() + " " + room.getName());
				}
				++i;
				
			}

			
			
			roomTimetable = new RoomTimetable(timeDayEntryList, room);
			roomTimetableMap.put(room, roomTimetable);
			timeDayEntryList = new ArrayList<DayTableEntry>();
			timetableEntryList = new ArrayList<TimetableEntry>();

			for (CourseElementInstance courseElementInstance : courseElementInstanceList) {

				for (DayTableEntry daytableEntry : roomTimetable
						.getTimeslots()) {
					
					for (TimetableEntry timetableEntry : daytableEntry.getTimetableEntryList()) {
						
						if (timetableEntry.getId() >= courseElementInstance
								.getStartingTimeslot().getId() && timetableEntry.getId() < courseElementInstance
								.getStartingTimeslot().getId() + courseElementInstance.getDuration()) {
							
							timetableEntry.setLecturerName(courseElementInstance
									.getCourseInstance().getMainLecturer()
									.getFirstName()
									+ " "
									+ courseElementInstance.getCourseInstance()
									.getMainLecturer().getLastName());
							
							timetableEntry.setCourseName(courseElementInstance
									.getCourseInstance().getInstanceOf().getName());
						}
					}


				}

			}
		}

	}

	/**
	 * Since only the starting time slots are set in the
	 * <code>AlgorithmImpl</code> this method sets the rooms for each
	 * <code>CourseElementInstance</code>.
	 * 
	 * @param schedule
	 *            the schedule of which the rooms will be set.
	 * @throws DatabaseException
	 */
	private void setElementInstanceTakesPlaceInRoom(Schedule schedule)
			throws DatabaseException {

		de.fu.weave.orm.Filter[] noFilter = {};
		relationManager.deleteElementInstanceTakesPlaceInRoom(noFilter);

		for (Entry<CourseElementInstance, RoomTimeIndex> entry : ((ScheduleImpl) schedule)
				.getCourseToSlot().entrySet()) {

			int roomIndex = entry.getValue().getRoomIndex();
			Room scheduledRoom = ((ScheduleImpl) schedule)
					.getRoomTimeCourseSlotList().get(roomIndex).getRoom();

			relationManager.createElementInstanceTakesPlaceInRoom(entry
					.getKey(), scheduledRoom, entry.getKey()
					.getStartingTimeslot());

			entry.getKey().pushChanges();
		}

	}

	public void pause() throws InterruptedException {
		algorithm.pause();
	}

	public void resume() {
		algorithm.resume();
	}
}
