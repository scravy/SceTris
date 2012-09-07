package scheduler.strategies.ju1;

import java.util.Random;

import scheduler.Scheduler;
import scheduler.data.Course;
import scheduler.strategies.interfaces.Individual;
import scheduler.strategies.interfaces.SetupFunction;


public class SetupFunctionImpl implements SetupFunction {
	protected Scheduler scheduler;
	protected Random rand;
	
	protected int[] coursesToLecturersMapping;
	protected Course[] courses;
	protected int nextRoomID;
	protected int timeslots;
	
	
	public SetupFunctionImpl(Scheduler scheduler) {
		this.scheduler = scheduler;
		rand = scheduler.getRand();
		
		courses = scheduler.getCourses();
		coursesToLecturersMapping = scheduler.getCoursesToLecturersMapping();
		timeslots = scheduler.getDays() * scheduler.getHours();
		nextRoomID = scheduler.getNextRoomID();
	}
	
	
	public void setup(Individual indiv) {
		if (indiv instanceof Individual) {
			IndividualImpl individual = (IndividualImpl) indiv;
	
			// create a random chromosome
			for (int i = 0; i < courses.length; i++) {
				int timeslot, room;
				do {
					timeslot = rand.nextInt(timeslots);
					room = rand.nextInt(nextRoomID);
				} while (individual.schedule[timeslot][room][0] >= 0);
				individual.schedule[timeslot][room][0] = i;
				individual.schedule[timeslot][room][1] = coursesToLecturersMapping[i];
			}
		} else {
			throw new IllegalArgumentException();
		}
	}
}
