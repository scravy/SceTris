package de.fu.scetris.scheduler.controller;

import java.io.IOException;
import java.util.List;
import java.util.Map.Entry;

import javax.xml.parsers.ParserConfigurationException;

import org.xml.sax.SAXException;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Room;
import de.fu.scetris.data.Timeslot;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.data.Configuration;
import de.fu.scetris.scheduler.model.strategy.implementations.AlgorithmImpl;
import de.fu.scetris.scheduler.model.strategy.implementations.ConcreteFactory;
import de.fu.scetris.scheduler.model.strategy.implementations.RoomTimeIndex;
import de.fu.scetris.scheduler.model.strategy.implementations.ScheduleImpl;
import de.fu.scetris.scheduler.model.strategy.interfaces.Schedule;
import de.fu.weave.orm.ConnectionManagerException;
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

	@Override
	public void run() {
		try {

			// TODO add filter to distinguish for lecturers
			relationManager = DatabaseAccess.connect();
			de.fu.weave.orm.Filter[] noFilter = {};

			List<Room> roomList = (List<Room>) relationManager
					.getRoom(noFilter);
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

			Configuration configuration = new Configuration(roomList,
					lecturerList, courseElementInstanceList, timeSlotList,
					featureList, proposedSchedulingList);

			AlgorithmConfiguration algorithmConfiguration = new AlgorithmConfiguration(
					0.3f, 0.2f, 0.8f, 0.5f, 50, 8, 100, 1.0);

			AlgorithmImpl algorithm = (AlgorithmImpl) ConcreteFactory
					.getInstance().createAlgorithm(configuration,
							algorithmConfiguration);

			algorithm.start();
			setElementInstanceTakesPlaceInRoom(algorithm.getBestSchedule());

		} catch (ConnectionManagerException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (DatabaseException e) {
			e.printStackTrace();
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
	private void setElementInstanceTakesPlaceInRoom(Schedule schedule) throws DatabaseException {

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
}
