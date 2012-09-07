package scheduler;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import scheduler.data.Course;
import scheduler.data.Lecturer;
import scheduler.data.Room;
import scheduler.data.ScheduleEntry;
import scheduler.strategies.interfaces.ComponentFactory;
import scheduler.strategies.interfaces.CrossoverOperation;
import scheduler.strategies.interfaces.FitnessFunction;
import scheduler.strategies.interfaces.GeneticAlgorithm;
import scheduler.strategies.interfaces.Individual;
import scheduler.strategies.interfaces.MutateOperation;
import scheduler.strategies.interfaces.SetupFunction;
import scheduler.strategies.ju1.IndividualImpl;
import scheduler.strategies.ju1.JuFactory;

/**
 * @author scravy
 */
public class Scheduler {
	protected Random rand;
	protected long seed;
	
	protected List<Course> coursesList = new ArrayList<Course>();

	protected List<Lecturer> lecturersList = new ArrayList<Lecturer>();
	protected List<Room> roomsList = new ArrayList<Room>();
	
	protected Course[] courses;
	protected Lecturer[] lecturers;
	protected Room[] rooms;
	protected int[] coursesToLecturersMapping;
	
	protected int nextRoomID = 0;
	protected int nextCourseID = 0;
	protected int nextLecturerID = 1;
	
	protected int timeslots;
	protected int days = 5;
	protected int hours = 10;
	protected int perGeneration = 20;
	
	
	protected int maxBest = 10;
	protected double mutationIntensity = 0.01;
	
	protected Individual bestSchedule;

	MutateOperation mutateOperation;
	CrossoverOperation crossoverOperation;
	FitnessFunction fitnessFunction;
	SetupFunction setupFunction;
	GeneticAlgorithm algorithmImplementation;
	
	ComponentFactory factory;
	
	
	public Scheduler() {
		create(new Random(System.nanoTime()).nextLong());
	}
	
	public Scheduler(long seed) {
		create(seed);
	}
	
	
	protected void create(long seed) {
		this.seed = seed;
		rand = new Random(seed);
		factory = new JuFactory(this);
	}
	
	/**
	 * Returns the seed which is used by this Schedulers Random-Number-Generator
	 */
	public long getSeed() {
		return seed;
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
	
	public void setMutationIntensity(double num) {
		if (num <= 0 || num > 1) {
			throw new IllegalArgumentException();
		}
		mutationIntensity = num;
	}
	
	public void setNumberOfDays(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		days = num;
	}
	
	public void setNumberOfHours(int num) {
		if (num < 1) {
			throw new IllegalArgumentException();
		}
		hours = num;
	}
	
	public ComponentFactory getFactory() {
		return factory;
	}

	public void setFactory(ComponentFactory factory) {
		this.factory = factory;
	}

	public Course[] getCourses() {
		return courses;
	}
	
	public Lecturer[] getLecturers() {
		return lecturers;
	}
	
	public Room[] getRooms() {
		return rooms;
	}
	
	public MutateOperation getMutateOperation() {
		return mutateOperation;
	}

	public CrossoverOperation getCrossoverOperation() {
		return crossoverOperation;
	}

	public FitnessFunction getFitnessFunction() {
		return fitnessFunction;
	}

	public SetupFunction getSetupFunction() {
		return setupFunction;
	}

	public Random getRand() {
		return rand;
	}

	public int[] getCoursesToLecturersMapping() {
		return coursesToLecturersMapping;
	}

	public int getNextRoomID() {
		return nextRoomID;
	}

	public int getNextCourseID() {
		return nextCourseID;
	}

	public int getNextLecturerID() {
		return nextLecturerID;
	}

	public int getDays() {
		return days;
	}

	public int getHours() {
		return hours;
	}

	public int getPerGeneration() {
		return perGeneration;
	}

	public int getMaxBest() {
		return maxBest;
	}

	public double getMutationIntensity() {
		return mutationIntensity;
	}
	
	protected void init() {
	    
		courses = coursesList.toArray(new Course[0]);
		lecturers = lecturersList.toArray(new Lecturer[0]);
		rooms = roomsList.toArray(new Room[0]);
		
		if (courses.length < 1) {
			throw new IllegalArgumentException("There are no courses");
		}
		if (rooms.length < 1) {
			throw new IllegalArgumentException("There are no rooms");
		}
		
		timeslots = days * hours;

		// Check data for consistency
		ArrayList<ConsistencyFailure> errors = new ArrayList<ConsistencyFailure>();
		
		coursesToLecturersMapping = new int[courses.length];
		for (int i = 0; i < courses.length; i++) {
			Lecturer lecturer = courses[i].getLecturer();
			if (lecturer == null) {
				errors.add(new ConsistencyFailure(0, courses[i]));
			}
			coursesToLecturersMapping[i] = lecturer.getId();
		}
		
		int duration, durationSum = 0;
		for (Course course : courses) {
			duration = course.getDuration();
			durationSum += duration;
			if (duration > hours) {
				errors.add(new ConsistencyFailure(1, course));
			}
		}
		if (durationSum > timeslots) {
			errors.add(new ConsistencyFailure(2, courses));
		}
		
		if (errors.size() > 0) {
			System.out.println(errors.get(0).getType());
			throw new IllegalArgumentException();
		}
		
		
		coursesList = null;
		lecturersList = null;
		roomsList = null;
		
		mutateOperation = factory.createMutateOperation();
		crossoverOperation = factory.createCrossoverOperation();
		setupFunction = factory.createSetupFunction();
		fitnessFunction = factory.createFitnessFunction();
		algorithmImplementation = factory.createGeneticAlgorithm();
		
		System.gc();
	}
	
	public synchronized Individual solve(Observer observer) {
		if (observer == null) {
			observer = new Observer() {
				public void done(int generation, Individual bestSchedule) { }
				public void nextGeneration(int generation, Individual[] population, TreeSet<Individual> best) { }
			};
		}
		init();
		
		return algorithmImplementation.run(observer);
	}	
	
	public synchronized Course createCourse(String name, int requiredSeats, int duration) {
		Course course = new Course(name, duration, requiredSeats, null);
		coursesList.add(course);
		return course;
	}
	
	public synchronized Course createCourse(String name, Lecturer lecturer, int requiredSeats, int duration) {
		Course course = this.createCourse(name, requiredSeats, duration);
		course.setLecturer(lecturer);
		return course;
	}
	
	public synchronized Lecturer createLecturer(String name) {
		Lecturer lecturer = new Lecturer("",name);
		lecturer.setId(nextLecturerID++);
		lecturersList.add(lecturer);
		return lecturer;
	}
	
	// quick and dirty method - need other method to identify prof // change xml-idea of profs/courses
	public synchronized Lecturer findLecturerById(int id) {
		return lecturersList.get(id-1);
	}
	
	public synchronized Room createRoom(String name, int capacity) {
		Room room = new Room(nextRoomID++, name, capacity);
		roomsList.add(room);
		return room;
	}	
	
	public Individual getBestSchedule() {
		return bestSchedule;
	}

	public void setBestSchedule(Individual bestSchedule) {
		this.bestSchedule = bestSchedule;
	}
	
	public ScheduleEntry[] getScheduleEntries() {
	    return ((IndividualImpl)bestSchedule).fillScheduleEntries();
	}
	
	public void setCoursesList(List<Course> coursesList) {
	    this.coursesList = coursesList;
	}

	public void setLecturersList(List<Lecturer> lecturersList) {
	    this.lecturersList = lecturersList;
	}

	public void setRoomsList(List<Room> roomsList) {
	    this.roomsList = roomsList;
	}
}
