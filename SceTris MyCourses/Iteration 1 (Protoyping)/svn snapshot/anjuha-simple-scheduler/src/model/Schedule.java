package model;
import java.util.*;
import model.data.*;

public class Schedule implements Comparable<Schedule>{

	// timespace Slots with Lists to hold all the Courses that START at a given slot
	public Vector<List<Course>> timespace;
	
	public double fitness;
	int mutationsize;
	int numberOfCrossoverPoints;
	public int numberOfTimeslots;
	public Room[] rooms;
	protected boolean evaluated;
	public int maximumFitness;
	public TreeSet<CourseSlot> classToTimespace;
	
	protected DataSource data;

	class CourseSlot {
		private Course course;
		private int timespace;

		CourseSlot(Course course, int timespace){
			this.course = course;
			this.timespace = timespace;
		}

		Course getCourse(){
			return course;
		}

		int getTimespace(){
			return timespace;
		}
	} 
 
	public Schedule(DataSource data){
		this.data = data;
		// each 60 TimeSpaceSlots represent the whole week for one room
		numberOfTimeslots = 5 * 12 * data.getNumberOfRooms();
		timespace = new Vector<List<Course>>(numberOfTimeslots);
		for(int i = 0; i < numberOfTimeslots; i++){
			timespace.add(new LinkedList<Course>());	
		}
		rooms = new Room[data.getNumberOfRooms()];
		// No Fitness assigned yet
		fitness = 0;
		// Fitness has not been evaluated yet
		evaluated = false;
		maximumFitness = 2 * data.getNumberOfCourses();
		// TreeSet because an order and Iterator are necessary	
		classToTimespace = new TreeSet<CourseSlot>(new CourseSlotComparator());
		mutationsize = 2; // Boese hardgecodeter Mist...wo kommt das her? TODO: Als Uebergabewert in den Konstruktor verschieben
		numberOfCrossoverPoints = 3; // ebenso
	}

	public void addRoom(Room room){
		rooms[room.id] = room;	
	}

	public DataSource getDataSource(){
		return data;
	}

	// Evaluate the fitness of a Schedule
	public double evaluateFitness (){

		int sumFitness = 0;
		// check every timespace Slot
		for(List<Course> coursesNow : timespace){
			// check every course at that timespace Slot 			
			for (Course course : coursesNow){

				int tmp = 0;
				// course starts at
				int begins = timespace.indexOf(coursesNow);
				// course is over at
				int ends = course.amountOfHours + timespace.indexOf(coursesNow);
				
				// Check every timespace Slot this Course occupies			
				for(int i = begins; i < ends; i++){
					// Summing up all the courses scheduled for this timespace Slot
					tmp += timespace.get(i).size();
				}
				// if no other course takes place at the same time... BONUS!!! 
				if(tmp == course.amountOfHours){
					sumFitness++;
				}
				tmp = 0;
				
				// check in which rooms the course is scheduled at each time
				boolean seatsOk = true;
				for (int i = 0;i < course.amountOfHours; i++){
					tmp = begins + i / 60;
					//check whether the room has enough seats and if positive... BONUS!!!
					if (!(rooms[tmp].numberOfSeats > course.amountOfSeatsRequired)){
						seatsOk = false;
						break;
					}
				}
				if (seatsOk){
					sumFitness++;
				}
				// TODO Check whether the lecturer has no other course at the time
			}
		}
		fitness = sumFitness/maximumFitness;
		return fitness;
	}

	public void mutate() {
		Course moved;
		CourseSlot movedCourseSlot;
		int from, to, salt, indexForFrom, indexForTo;
		for(int i = 0; i < mutationsize; i++){
			Random rnd = new Random();
			// randomly choose 2 timespace slots from / to a course shall be moved
			from = rnd.nextInt()%numberOfTimeslots;
			to = rnd.nextInt()%numberOfTimeslots;
			// randomly choose 2 indexes to increase randomness
			indexForFrom = rnd.nextInt()%timespace.get(from).size();
			indexForTo = rnd.nextInt()%timespace.get(to).size();
			// get a hold of that reference
			moved = timespace.get(from).remove(indexForFrom);
			// move
			timespace.get(to).add(indexForTo,moved);
			// and move in the Map as well
			movedCourseSlot = new CourseSlot(moved, indexForTo);
			classToTimespace.remove(moved);
			classToTimespace.add(movedCourseSlot);
		}
	}

	public Schedule crossover(Schedule parent){
		
		int counter = 0;
		// for switching between genetic information (Courses -> Timespace)from this and parent
		boolean thisOrParent = true;
		Random rnd = new Random();
		Schedule child = new Schedule(data);
		CourseSlot currentCourse;
		Iterator<CourseSlot> thisIterator = classToTimespace.iterator();
		Iterator<CourseSlot> parentIterator = parent.classToTimespace.iterator();

		Set<Integer> breakpoints = new TreeSet<Integer>();	
		for (int i = 0; i < numberOfCrossoverPoints; i++){
			breakpoints.add(rnd.nextInt(classToTimespace.size()));
		}
		Iterator<Integer> breakIterator = breakpoints.iterator();
		Integer currentBreakpoint = breakIterator.next();

		for (int i = 0; i < classToTimespace.size(); i++){
			// We are encountering a breakpoint. Please remain calm and fasten your Seatbelt.
			if(counter == currentBreakpoint){
				// switch it baby!
				thisOrParent = !thisOrParent;
				if(breakIterator.hasNext()){
					currentBreakpoint = breakIterator.next();			
				}
			}			
			counter++;
			if(thisOrParent){
				currentCourse = thisIterator.next();
				parentIterator.next();
				child.classToTimespace.add(currentCourse);
			}else{
				currentCourse = parentIterator.next();
				thisIterator.next();
				child.classToTimespace.add(currentCourse);
			}			
		}
		// now to transfer this into the timespace Vector
		Iterator<CourseSlot> childIterator = child.classToTimespace.iterator();
		while(childIterator.hasNext()){
			currentCourse = childIterator.next();
			child.timespace.get(currentCourse.getTimespace()).add(currentCourse.getTimespace(), currentCourse.getCourse());
		}
		return child;
	}

	public int compareTo(Schedule schedule){
		// If Fitness of this schedule has not yet been evaluated
		if(!this.evaluated){
			this.evaluateFitness();
		}
		// If Fitness of the schedule compared to has not yet been evaluated
		if(!schedule.evaluated){
			schedule.evaluateFitness();
		}
		if(this.fitness - schedule.fitness < 0){
			return -1;
		} else if(this.fitness - schedule.fitness > 0){
			return 1;
		} else
			return 0;	
	}

	/*public String toString(){
		StringBuffer buffer = new StringBuffer();
		for (int k = 0; k < data.getNumberOfRooms(); k++){
			buffer.append("Room: " ++ k ++"\n");
			for (int i = 0; i < 12; i++){
				buffer.append(String.format("%1$5d", i));
				for (int j = 0; i < 5; i++){
					buffer.append(String.format("%1$25 "
				}
			}
		}
	}
	*/
}
