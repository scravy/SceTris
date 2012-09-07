import java.util.List;
import java.util.ArrayList;
import java.util.TreeSet;
import java.util.Random;
import java.util.Arrays;

/**
 *
 */
public class Scheduler
{
	abstract private class Item {
		private int id;
		
		public Item(int id) {
			this.id = id;
		}
		
		public int hashCode() {
			return this.id;
		}
		
		public int getID() {
			return this.id;
		}
		
		public String toString() {
			return this.getClass().getName() + " #" + id;
		}
	}
	
	abstract private class NamedItem extends Item {
		private String name;
		
		public NamedItem(int id, String name) {
			super(id);
			this.name = name;
		}
		
		public String getName() {
			return name;
		}
		
		public String toString() {
			return this.getClass().getName() + " \"" + this.name + "\"";
		}
	}
	
	private class Course extends NamedItem {
		private Lecturer lecturer = null;
		private int duration;
		
		public Course(int id, String name, int duration) {
			super(id, name);
			this.duration = duration;
		}
		
		public void setLecturer(Lecturer lecturer) {
			if (this.lecturer != null) {
				this.lecturer.removeCourse(this);
			}
			this.lecturer = lecturer;
			this.lecturer.addCourse(this);
		}
		
		public Lecturer getLecturer() {
			return this.lecturer;
		}
		
		public int getDuration() {
			return this.duration;
		}
	}
	
	private class Lecturer extends NamedItem {
		private List<Course> myCourses = new ArrayList<Course>();
		
		public Lecturer(int id, String name) {
			super(id, name);
		}
		
		public void addCourse(Course course) {
			myCourses.add(course);
			if (course.getLecturer() != this) {
				course.setLecturer(this);
			}
		}
		
		public void removeCourse(Course course) {
			myCourses.remove(course);
		}
	}
	
	private class Room extends NamedItem {
		private int capacity;
		
		public Room(int id, String name, int capacity) {
			super(id, name);
		}
		
		public int getCapacity() {
			return capacity;
		}
	}
	
	
	private class Chromosome implements Cloneable, Comparable<Chromosome> {
		/**
		 * 1st dim: timeslots
		 * 2nd dim: rooms
		 * 3rd dim: tupel of course and lecturer
		 */
		private int[][][] schedule;
		private double fitness = 0;
		private Random rand;
		
		
		private Chromosome() {
			create();
		}
		
		public Chromosome(Random rand) {
			create();
			this.rand = rand;
			setup();
		}
		
		
		private void create() {
			schedule = new int[timeslots][nextRoomID][2];
			for (int i = 0; i < timeslots; i++) {
				for (int j = 0; j < nextRoomID; j++) {
					schedule[i][j][0] = -1;
					schedule[i][j][1] = -1;
				}
			}
		}
		
		private void setup() {
			// create a random chromosome
			for (int i = 0; i < courses.length; i++) {
				int timeslot, room;
				do {
					timeslot = rand.nextInt(timeslots);
					room = rand.nextInt(nextRoomID);
				} while (schedule[timeslot][room][0] >= 0);
				schedule[timeslot][room][0] = i;
				schedule[timeslot][room][1] = coursesToLecturersMapping[i];
			}
		}		
		
		private void calculateFitness() {
			int score = 0;
			for (int i = 0; i < schedule.length; i++) {
				TreeSet<Integer> lecturersInThisTimeslot = new TreeSet<Integer>();
				for (int j = 0; j < schedule[i].length; j++) {
					if (schedule[i][j][0] >= 0) {
						score++;
						if (lecturersInThisTimeslot.contains(schedule[i][j][1])) {
							score--;
						} else {
							lecturersInThisTimeslot.add(schedule[i][j][1]);
						}
					}
				}
			}
			fitness = (double) score / (double) courses.length;
		}
		
		public double getFitness() {
			return fitness;
		}
		
		public int[][][] getSchedule() {
			return schedule;
		}
		
		public Chromosome crossover(Chromosome second) {
			return second;
			/*
			Chromosome offspring = new Chromosome();
			offspring.rand = this.rand;
			
			// calculate offsprings genome (based on this and second)
			// TODO: nothing done here ATM
			
			offspring.calculateFitness();
			return offspring;
			*/
		}
		
		public synchronized void mutate() {
			double intensity = 0.01; // How many percent of the genome should be mutated
			double max = timeslots * nextRoomID * intensity;
			for (int i = 0; i < max; i++) {
				int timeslot1, timeslot2, room1, room2;
				do {
					timeslot1 = rand.nextInt(timeslots);
					timeslot2 = rand.nextInt(timeslots);
					room1 = rand.nextInt(nextRoomID);
					room2 = rand.nextInt(nextRoomID);
				} while (timeslot1 == timeslot2 || room1 == room2);
				exchange(timeslot1, room1, timeslot2, room2);
			}
			
			calculateFitness();
		}
		
		private void exchange(int timeslot1, int room1, int timeslot2, int room2) {
			int[] tmp = new int[2];
			tmp[0] = schedule[timeslot2][room2][0];
			tmp[1] = schedule[timeslot2][room2][1];
			schedule[timeslot2][room2][0] = schedule[timeslot1][room1][0];
			schedule[timeslot2][room2][1] = schedule[timeslot1][room1][1];
			schedule[timeslot1][room1][0] = tmp[0];
			schedule[timeslot1][room1][1] = tmp[1];
		}
		
		public Chromosome createMutation() {
			Chromosome mutant = this.clone();
			mutant.mutate();
			return mutant;
		}
		
		public boolean equals(Object second) {
			return false;
		}
		
		public boolean equals(Chromosome second) {
			return this.compareTo(second) == 0;
		}
		
		public int compareTo(Chromosome second) {
			if (this.fitness < second.fitness) {
				return -1;
			} else if (this.fitness > second.fitness) {
				return 1;
			}
			return 0;
		}
		
		public Chromosome clone() {
			Chromosome clone = new Chromosome();
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
			StringBuffer buf = new StringBuffer();
			for (int j = 0; j < nextRoomID; j++) {
				buf.append(rooms[j].toString() + "\n");
				for (int i = 0; i < timeslots; i++) {
					String course, lecturer;
					buf.append(String.format("%3d: ", i));
					if (schedule[i][j][0] >= 0) {
						buf.append(String.format("%-17s | %-15s%n", courses[schedule[i][j][0]].getName(), lecturers[schedule[i][j][1]].getName()));
					} else {
						buf.append(String.format("%-17s | %-15s%n", "-", "-"));
					}
				}
			}
			return buf.toString();
		}
	}
	
	public interface Observer {
		public void done(int generation, Chromosome bestSchedule);
		public void nextGeneration(int generation, Chromosome[] population, TreeSet<Chromosome> best);
	}
	
	
	private List<Course> coursesList = new ArrayList<Course>();
	private List<Lecturer> lecturersList = new ArrayList<Lecturer>();
	private List<Room> roomsList = new ArrayList<Room>();
	
	private Course[] courses;
	private Lecturer[] lecturers;
	private Room[] rooms;
	private int[] coursesToLecturersMapping;
	
	private int nextRoomID = 0;
	private int nextCourseID = 0;
	private int nextLecturerID = 0;
	
	private int timeslots = 15;
	private int perGeneration = 20;
	private int maxBest = 10;
	
	
	public Scheduler() {
		
	}
	
	
	public void setNumberOfTimeslots(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		timeslots = num;
	}
	
	public void setNumberOfIndividualsPerGeneration(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		perGeneration = num;
	}
	
	public void setNumberOfBest(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		maxBest = num;
	}
	
	private void init() {
		courses = coursesList.toArray(new Course[0]);
		lecturers = lecturersList.toArray(new Lecturer[0]);
		rooms = roomsList.toArray(new Room[0]);
		
		if (courses.length < 1) {
			throw new IllegalArgumentException("There are no courses");
		}
		if (rooms.length < 1) {
			throw new IllegalArgumentException("There are no rooms");
		}
		
		coursesToLecturersMapping = new int[courses.length];
		for (int i = 0; i < courses.length; i++) {
			Lecturer lecturer = courses[i].getLecturer();
			if (lecturer == null) {
				throw new IllegalArgumentException("A course can not exist without a main-lecturer");
			}
			coursesToLecturersMapping[i] = lecturer.getID();
		}
		
		coursesList = null;
		lecturersList = null;
		roomsList = null;
		
		System.gc();
	}
	
	public synchronized Chromosome solve(Observer observer) {
		if (observer == null) {
			observer = new Observer() {
				public void done(int generation, Chromosome bestSchedule) { }
				public void nextGeneration(int generation, Chromosome[] population, TreeSet<Chromosome> best) { }
			};
		}
		init();
		
		TreeSet<Chromosome> bestChromosomes = new TreeSet<Chromosome>();
		Chromosome[] population = new Chromosome[perGeneration];
		Random rand = new Random();
		
		int generation = 0;
		
		// Initialisierung der ersten Generation
		for (int i = 0; i < population.length; i++) {
			population[i] = new Chromosome(rand);
			bestChromosomes.add(population[i]);
		}
		
		do {
			observer.nextGeneration(generation++, population, bestChromosomes);
			
			// Neue Generation
			Chromosome[] bestChromosomesArray = bestChromosomes.toArray(new Chromosome[0]);
			for (int i = 0; i < bestChromosomesArray.length; i++) {
				Chromosome parent = bestChromosomesArray[rand.nextInt(bestChromosomesArray.length)];
				// Rekombination durch Kreuzung
				population[i] = parent.crossover(bestChromosomesArray[rand.nextInt(bestChromosomesArray.length)]);
				// Mutation
				population[i].mutate();
			}
			
			bestChromosomes.addAll(Arrays.asList(population));
			
			// Selektion
			while (bestChromosomes.size() > maxBest) {
				bestChromosomes.pollFirst();
			}
		} while (bestChromosomes.last().getFitness() < 1);
		
		observer.done(generation, bestChromosomes.last());
		return bestChromosomes.last();
	}	
	
	public synchronized Course createCourse(String name, int duration) {
		Course course = new Course(nextCourseID++, name, duration);
		coursesList.add(course);
		return course;
	}
	
	public synchronized Course createCourse(String name, int duration, Lecturer lecturer) {
		Course course = this.createCourse(name, duration);
		course.setLecturer(lecturer);
		return course;
	}
	
	public synchronized Lecturer createLecturer(String name) {
		Lecturer lecturer = new Lecturer(nextLecturerID++, name);
		lecturersList.add(lecturer);
		return lecturer;
	}
	
	public synchronized Room createRoom(String name) {
		Room room = new Room(nextRoomID++, name, 150);
		roomsList.add(room);
		return room;
	}
	
	
	public static void main(String... args) {
		Scheduler scheduler = new Scheduler();
		
		Scheduler.Lecturer schweppe = scheduler.createLecturer("Schweppe");
		Lecturer esponda = scheduler.createLecturer("Esponda");
		Lecturer loehr = scheduler.createLecturer("Loehr");
		Lecturer kyas = scheduler.createLecturer("Kyas");
		Lecturer liers = scheduler.createLecturer("Liers");
		Lecturer kriegel = scheduler.createLecturer("Kriegel");
		Lecturer prechelt = scheduler.createLecturer("Prechelt");
		
		scheduler.createCourse("Alp1", 1, schweppe);
		scheduler.createCourse("Alp2", 1, esponda);
		scheduler.createCourse("Alp3", 1, loehr);
		scheduler.createCourse("Alp4", 1, kyas);
		scheduler.createCourse("Alp5", 1, loehr);
		
		scheduler.createCourse("Ti1", 1, liers);
		scheduler.createCourse("Ti2", 1, esponda);
		scheduler.createCourse("Ti3", 1, kyas);
		scheduler.createCourse("Ti4", 1, liers);
		
		scheduler.createCourse("Mafi1", 1, kriegel);
		scheduler.createCourse("Mafi2", 1, kriegel);
		scheduler.createCourse("Mafi3", 1, kriegel);
		
		scheduler.createCourse("GTI", 1, kyas);
		scheduler.createCourse("DBS", 1, schweppe);
		scheduler.createCourse("SWT", 1, prechelt);
		scheduler.createCourse("AWS", 1, prechelt);
		scheduler.createCourse("SWP1", 1, prechelt);
		scheduler.createCourse("SWP2", 1, schweppe);
		scheduler.createCourse("Model-Checking", 1, kyas);
		scheduler.createCourse("Compiler-Bau", 1, prechelt);
		
		scheduler.createRoom("Taku9 Großer HS");
		scheduler.createRoom("Arnim22 Hörsaal A");
		scheduler.createRoom("Arnim22 Hörsaal B");
		scheduler.createRoom("Taku9 Großer HS");
		
		scheduler.setNumberOfTimeslots(5);
		
		Chromosome finalSchedule = scheduler.solve(new Observer() {
			public void done(int generation, Chromosome bestSchedule) {
				System.out.printf("Done. (Generation %d)%n", generation);
				System.out.println("Fitness of best: " + bestSchedule.getFitness());
				System.out.println(bestSchedule);
			}
			public void nextGeneration(int generation, Chromosome[] population, TreeSet<Chromosome> best) {
				System.out.println("Generation " + generation);
				System.out.println("Fitness of best: " + best.last().getFitness());
				System.out.println(best.last());
			}			
		});
		
		int[][][] schedule = finalSchedule.getSchedule();
	}
}
