package scheduler.strategies.abstracts;

import java.util.Random;

import scheduler.Scheduler;
import scheduler.data.*;
import scheduler.strategies.interfaces.CrossoverOperation;
import scheduler.strategies.interfaces.FitnessFunction;
import scheduler.strategies.interfaces.MutateOperation;
import scheduler.strategies.interfaces.SetupFunction;


public abstract class Individual implements scheduler.strategies.interfaces.Individual{
	/**
	 * 1st dim: timeslots
	 * 2nd dim: rooms
	 * 3rd dim: meta information about this slot:
	 *	- 0: course id
	 *	- 1: lecturer id
	 *	- 2: quality of this gene
	 */
	int[][][] schedule;
	
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
	
	/*
	private Individual(Scheduler scheduler, boolean setup) {
		this.scheduler = scheduler;
		create(scheduler);
	}
	
	public Individual(Scheduler scheduler) {
		this.scheduler = scheduler;
		create(scheduler);
		this.rand = scheduler.getRand();
		setup();
	} */
	
	
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
	
	protected abstract void setup();
	protected abstract void calculateFitness();
	public abstract double getFitness();
	public abstract int[][][] getSchedule();
	public abstract Individual crossover(Individual second);
	public abstract void mutate();
	protected abstract void exchange(int timeslot1, int room1, int timeslot2, int room2);
	public abstract Individual createMutation();
	public abstract boolean equals(Object second);
	public abstract boolean equals(Individual second);
	public abstract int compareTo(Individual secondo);
	public abstract Individual clone();
	
	public String toString() {
		
		StringBuffer buf = new StringBuffer();
		String name;
		int i;
		for (int j = 0; j < nextRoomID; j++) {
			buf.append(String.format("%s (%d seats)%n   ", rooms[j].getName(), rooms[j].getCapacity()));
			for (int l = 0; l < days; l++) {
				buf.append(String.format(" | day %-10d", l+1));
			}
			buf.append(String.format("%n"));
			for (int k = 0; k < hours; k++) {
				buf.append("----+");
				for (int l = 0; l < days; l++) {
					buf.append("----------------+");
				}
				buf.append(String.format("%n%3d", k+1));
				for (int l = 0; l < days; l++) {
					name = "-";
					i = l*hours + k;
					if (schedule[i][j][0] >= 0) {
						name = courses[schedule[i][j][0]].getName() + " ("
								+ courses[schedule[i][j][0]].getDuration() + ")";
					}
					buf.append(String.format(" | %-14s", name));
				}
				buf.append(String.format("%n   "));
				for (int l = 0; l < days; l++) {
					name = "-";
					i = l*hours + k;
					if (schedule[i][j][0] >= 0) {
						name = lecturers[schedule[i][j][1]].getName();
					}
					buf.append(String.format(" | %-14s", name));
				}
				buf.append(String.format("%n"));
			}
		}
		return buf.toString();
	}
}
