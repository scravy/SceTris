package scheduler.strategies.ju2;

import java.util.Random;

import scheduler.Scheduler;
import scheduler.strategies.interfaces.Individual;
import scheduler.strategies.interfaces.MutateOperation;


public class MutateOperationImpl implements MutateOperation {
	private Random rand;
	
	protected int timeslots;
	protected int days;
	protected int hours;
	protected int nextRoomID;
	protected double mutationIntensity;
	
	
	public MutateOperationImpl(Scheduler scheduler) {
		this.rand = scheduler.getRand();

		days = scheduler.getDays();
		hours = scheduler.getHours();
		timeslots = days * hours;
		nextRoomID = scheduler.getNextRoomID();
		mutationIntensity = scheduler.getMutationIntensity();
	}
	
	
	public void mutate(Individual indiv) {
		if (indiv instanceof IndividualImpl) {
			IndividualImpl individual = (IndividualImpl) indiv;
			int timeslot1, timeslot2, room1, room2, n, nx, t;
			double max = timeslots * nextRoomID * mutationIntensity;
			// The following loop tries to mutate bad genes first
			n = 0; // counts how many mutations already have occured
			nx = 0;
			t = 1; // t is the amount of "badness". Less t, more bad.
			for (int i = 0; i < timeslots; i++) {
				for (int j = 0; j < nextRoomID; j++) {
					if (individual.schedule[i][j][0] >= 0
							&& individual.schedule[i][j][2] < 0
							) {
						if (rand.nextBoolean()) {
							timeslot2 = (i + 1) % timeslots;
							room2 = j;
						} else {
							timeslot2 = i;
							room2 = (j + 1) % nextRoomID;
						}
						individual.exchange(i, j, timeslot2, room2);
						n++;
					}
				}
			}
			
			// Mutate more genes if level of intensity is now already reached
			for (; n < max; n++) {
				do {
					timeslot1 = rand.nextInt(timeslots);
					timeslot2 = rand.nextInt(timeslots);
					room1 = rand.nextInt(nextRoomID);
					room2 = rand.nextInt(nextRoomID);
				} while ((timeslot1 == timeslot2 || room1 == room2)
					&& (individual.schedule[timeslot1][room1][0] >= 0) || (individual.schedule[timeslot2][room2][0] >= 0));
				individual.exchange(timeslot1, room1, timeslot2, room2);
			}
		}
	}
}
