package scheduler.strategies.ju1;

import java.util.Random;

import scheduler.Scheduler;
import scheduler.data.Course;
import scheduler.data.Lecturer;
import scheduler.data.Room;
import scheduler.data.ScheduleEntry;
import scheduler.strategies.interfaces.CrossoverOperation;
import scheduler.strategies.interfaces.FitnessFunction;
import scheduler.strategies.interfaces.Individual;
import scheduler.strategies.interfaces.MutateOperation;
import scheduler.strategies.interfaces.SetupFunction;

public class IndividualImpl implements Individual {
    /**
     * 1st dim: timeslots 2nd dim: rooms 3rd dim: meta information about this
     * slot: - 0: course id - 1: lecturer id - 2: quality of this gene
     */
    int[][][] schedule;
    protected ScheduleEntry[] scheduleEntries;

    protected double fitness = 0;
    protected Random rand;
    protected Scheduler scheduler;
    protected Course[] courses;
    protected Lecturer[] lecturers;
    protected Room[] rooms;
    protected int days;
    protected int hours;
    protected int nextRoomID;
    protected int timeslots;

    protected CrossoverOperation crossoverOperation;
    protected MutateOperation mutateOperation;
    protected SetupFunction setupFunction;
    protected FitnessFunction fitnessFunction;

    private IndividualImpl(Scheduler scheduler, boolean setup) {
	this.scheduler = scheduler;
	create(scheduler);
    }

    public IndividualImpl(Scheduler scheduler) {
	this.scheduler = scheduler;
	create(scheduler);
	this.rand = scheduler.getRand();
	setup();
    }

    /**
     * initializes the schedule, the course-ids are set to -1
     */
    private void create(Scheduler scheduler) {
	courses = scheduler.getCourses();
	lecturers = scheduler.getLecturers();
	rooms = scheduler.getRooms();
	days = scheduler.getDays();
	hours = scheduler.getHours();
	nextRoomID = scheduler.getNextRoomID();
	timeslots = days * hours;
	crossoverOperation = scheduler.getCrossoverOperation();
	mutateOperation = scheduler.getMutateOperation();
	setupFunction = scheduler.getSetupFunction();
	fitnessFunction = scheduler.getFitnessFunction();

	schedule = new int[timeslots][nextRoomID][4];
	for (int i = 0; i < timeslots; i++) {
	    for (int j = 0; j < nextRoomID; j++) {
		schedule[i][j][0] = -1;
	    }
	}
    }

    private void setup() {
	setupFunction.setup(this);
	calculateFitness();
    }

    void calculateFitness() {
	fitness = fitnessFunction.calculateFitness(this);
    }

    public double getFitness() {
	return fitness;
    }

    public int[][][] getSchedule() {
	return schedule;
    }

    public IndividualImpl crossover(Individual second) {
	return (IndividualImpl) crossoverOperation.crossover(this, second);
    }

    public synchronized void mutate() {
	mutateOperation.mutate(this);
	calculateFitness();
    }

    void exchange(int timeslot1, int room1, int timeslot2, int room2) {
	int[] tmp = new int[2];
	tmp[0] = schedule[timeslot2][room2][0];
	tmp[1] = schedule[timeslot2][room2][1];
	schedule[timeslot2][room2][0] = schedule[timeslot1][room1][0];
	schedule[timeslot2][room2][1] = schedule[timeslot1][room1][1];
	schedule[timeslot1][room1][0] = tmp[0];
	schedule[timeslot1][room1][1] = tmp[1];
    }

    public IndividualImpl createMutation() {
	IndividualImpl mutant = this.clone();
	mutant.mutate();
	return mutant;
    }

    public boolean equals(Object second) {
	return false;
    }

    public boolean equals(IndividualImpl second) {
	return this.compareTo(second) == 0;
    }

    @Override
    public int compareTo(Individual secondo) {
	if (secondo instanceof IndividualImpl) {
	    IndividualImpl second = (IndividualImpl) secondo;
	    if (this.fitness < second.fitness) {
		return -1;
	    } else if (this.fitness > second.fitness) {
		return 1;
	    }
	}
	return 0;
    }

    public IndividualImpl clone() {
	IndividualImpl clone = new IndividualImpl(scheduler, false);
	for (int i = 0; i < timeslots; i++) {
	    for (int j = 0; j < nextRoomID; j++) {
		clone.schedule[i][j][0] = this.schedule[i][j][0];
		clone.schedule[i][j][1] = this.schedule[i][j][1];
	    }
	}
	clone.fitness = this.fitness;
	clone.rand = this.rand;
	return clone;
    }

    public String toString() {
	/*
	 * StringBuffer buf = new StringBuffer(); for (int j = 0; j <
	 * nextRoomID; j++) { buf.append(rooms[j].toString() + "\n"); for (int i
	 * = 0; i < timeslots; i++) { String course, lecturer;
	 * buf.append(String.format("%3d: ", i)); if (schedule[i][j][0] >= 0) {
	 * buf.append(String.format("%-17s | %-15s%n",
	 * courses[schedule[i][j][0]].getName(),
	 * lecturers[schedule[i][j][1]].getName())); } else {
	 * buf.append(String.format("%-17s | %-15s%n", "-", "-")); } } } return
	 * buf.toString();
	 */

	StringBuffer buf = new StringBuffer();
	String name;
	int i;
	for (int j = 0; j < nextRoomID; j++) {
	    buf.append(String.format("%s (%d seats)%n   ", rooms[j].getName(),
		    rooms[j].getCapacity()));
	    for (int l = 0; l < days; l++) {
		buf.append(String.format(" | day %-10d", l + 1));
	    }
	    buf.append(String.format("%n"));
	    for (int k = 0; k < hours; k++) {
		buf.append("----+");
		for (int l = 0; l < days; l++) {
		    buf.append("----------------+");
		}
		buf.append(String.format("%n%3d", k + 1));
		for (int l = 0; l < days; l++) {
		    name = "-";
		    i = l * hours + k;
		    if (schedule[i][j][0] >= 0) {
			name = courses[schedule[i][j][0]].getName() + " ("
				+ courses[schedule[i][j][0]].getDuration()
				+ ")";
		    }
		    buf.append(String.format(" | %-14s", name));
		}
		buf.append(String.format("%n   "));
		for (int l = 0; l < days; l++) {
		    name = "-";
		    i = l * hours + k;
		    if (schedule[i][j][0] >= 0) {
			name = lecturers[schedule[i][j][1]-1].getName();		/* -1 sonst knallt es, ändern an scheduler.nextLecturerID(nun 1) bringt nix, klären mit julian */
		    }
		    buf.append(String.format(" | %-14s", name));
		}
		buf.append(String.format("%n"));
	    }
	}
	return buf.toString();
    }

    public void toXML(String outputFilename) {
	XMLWriter xml = new XMLWriter(this, "testdata/out/"+outputFilename);
	xml.generate();
    }

    public ScheduleEntry[] fillScheduleEntries() {

	ScheduleEntry[] result = new ScheduleEntry[nextRoomID * hours];
	ScheduleEntry currentScheduleEntry = new ScheduleEntry();

	String name = null;
	int i;

	String currentRoom;

	for (int j = 0; j < nextRoomID; j++) {

	    currentRoom = rooms[j].getName();

	    for (int k = 0; k < hours; k++) {
		for (int l = 0; l < days; l++) {
		    i = l * hours + k;
		    if (schedule[i][j][0] >= 0) {
			name = courses[schedule[i][j][0]].getName() + " ("
				+ courses[schedule[i][j][0]].getDuration()
				+ ") " + lecturers[schedule[i][j][1]].getName();
		    }

		    switch (l) {
		    case 0:
			currentScheduleEntry.setMonday(name);
			break;
		    case 1:
			currentScheduleEntry.setTuesday(name);
			break;
		    case 2:
			currentScheduleEntry.setWednesday(name);
			break;
		    case 3:
			currentScheduleEntry.setThursday(name);
			break;
		    case 4:
			currentScheduleEntry.setFriday(name);
			break;
		    }

		    currentScheduleEntry.setRoom(currentRoom);
		}
		result[(j * hours) + k] = currentScheduleEntry;
		currentScheduleEntry = new ScheduleEntry();
		name = null;
		
	    }
	}
	return result;
    }
}
