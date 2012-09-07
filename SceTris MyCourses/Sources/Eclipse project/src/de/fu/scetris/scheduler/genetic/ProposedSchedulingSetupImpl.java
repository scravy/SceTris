package de.fu.scetris.scheduler.genetic;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import de.fu.scetris.data.CourseElementInstance;
import de.fu.scetris.data.Feature;
import de.fu.scetris.data.ProposedScheduling;
import de.fu.scetris.data.Room;
import de.fu.scetris.scheduler.Schedule;
import de.fu.scetris.scheduler.Setup;
import de.fu.scetris.scheduler.data.Configuration;
import de.fu.scetris.scheduler.data.NotScheduleableException;
import de.fu.scetris.scheduler.data.SchedulingException;
import de.fu.weave.orm.DatabaseException;

public class ProposedSchedulingSetupImpl implements Setup {

	Random random;
	Configuration configuration;

	/**
	 * Initialize {@link ProposedSchedulingSetupImpl} in order to fasten the course
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
	ProposedSchedulingSetupImpl(Random random, Configuration configuration)
			throws DatabaseException {
		super();
		this.random = random;
		this.configuration = configuration;
	}

	/**
	 * Initializes the schedule on behalf their proposed schedules.
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

			for (CourseElementInstance course : courseList) {
				allocateProposedScheduling(course, scheduleImpl);
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
}
