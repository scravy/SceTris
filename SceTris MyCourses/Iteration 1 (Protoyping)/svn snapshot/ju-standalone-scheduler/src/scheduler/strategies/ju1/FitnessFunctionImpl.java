package scheduler.strategies.ju1;

import java.util.TreeSet;

import scheduler.Scheduler;
import scheduler.data.Course;
import scheduler.data.Lecturer;
import scheduler.data.Room;
import scheduler.strategies.interfaces.FitnessFunction;
import scheduler.strategies.interfaces.Individual;


public class FitnessFunctionImpl implements FitnessFunction {
	protected Scheduler scheduler;
	
	protected Course[] courses;
	protected Lecturer[] lecturers;
	protected Room[] rooms;
	protected int hours;
	
	public FitnessFunctionImpl(Scheduler scheduler) {
		this.scheduler = scheduler;
		
		courses = scheduler.getCourses();
		lecturers = scheduler.getLecturers();
		rooms = scheduler.getRooms();
		hours = scheduler.getHours();
	}
	
	public double calculateFitness(Individual indiv) {
		if (indiv instanceof IndividualImpl) {
			IndividualImpl individual = (IndividualImpl) indiv;
			Course course;
			Room room;
			int courseID = -1;
			int lecturerID = -1;
			double score = 0;
			for (int i = 0; i < individual.schedule.length; i++) {
				TreeSet<Integer> lecturersInThisTimeslot = new TreeSet<Integer>();
				for (int j = 0; j < individual.schedule[i].length; j++) {
					room = rooms[j];
					if (individual.schedule[i][j][0] >= 0) {
						courseID = individual.schedule[i][j][0];
						course = courses[courseID];
						lecturerID = individual.schedule[i][j][1];
					} else {
						course = null;
					}
					individual.schedule[i][j][2] = 0;
					
					if (course != null) {
						// First criteria: Does a lecturer have two courses at the same time?
						if (!lecturersInThisTimeslot.contains(lecturerID)) {
							score++;
							individual.schedule[i][j][2]++;
							lecturersInThisTimeslot.add(lecturerID);
						}
						
						// Second criteria: Does the room have enough seats?
						if (room.getCapacity() >= course.getRequiredSeats()) {
							individual.schedule[i][j][2]++;
							score++;
						}
						
						// Third criteria: End of day.
						if (hours - (i % hours) >= course.getDuration()) {
							individual.schedule[i][j][2]++;
							score++;
									
							// Fourth criteria: Duration (needs only to be checked if previous check didnt fail)
							dLoop: for (int d = 1; d < course.getDuration(); d++) {
								if (individual.schedule[i+d][j][0] >= 0) {
									score = 0;
									break;
								}
								for (int k = 0; k < individual.schedule[i+d].length; k++) {
									int[] meta = individual.schedule[i+d][k];
									if (meta[1] == lecturerID) {
										score = 0;
										break dLoop;
									}
								}
							}
						}
						
						// Fifth criteria: Students Groups
						// Es ist 03:31 - MOUT
					}
				}
			}
			return score / 3 / courses.length;
		}
		throw new IllegalArgumentException();
	}
}
