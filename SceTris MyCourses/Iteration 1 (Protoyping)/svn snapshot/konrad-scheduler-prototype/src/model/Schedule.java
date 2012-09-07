package model;

import java.util.*;
import xml.Builder;

/**
 * @author Konrad Reiche
 * 
 *         Chromosome: Representation of a class schedule.
 * 
 */
public class Schedule {
    
    // Reference to the data
	// TODO: remove static and capsaulate as configuration.java
    public static Builder xmlBuild;

    // Time-space slots, one entry represent one hour in one classroom
    private Vector<List<CourseClass>> slots = new Vector<List<CourseClass>>();

    /*
     * Class table for chromosome Used to determine first time-space slot used
     * by class
     */
    private HashMap<CourseClass,Integer> classes = new HashMap<CourseClass,Integer>();

    // Fitness value for chromosome
    private float fitness;

    // Flags of class requirements satisfaction
    Vector<Boolean> criteria;

    // Chromosome parameters
    int numberOfCrossoverPoints;
    int mutationSize;
    int crossoverProbability;
    int mutationProbability;

	/**
	 *
	 */
    public Schedule(int numberOfCrossoverPoints, int mutationSize,
	    int crossoverProbability, int mutationProbability) {
	
		this.numberOfCrossoverPoints = numberOfCrossoverPoints;
		this.mutationSize = mutationSize;
		this.crossoverProbability = crossoverProbability;
		this.mutationProbability = mutationProbability;
    }

	/**
	 *
	 */
    public Schedule(final Schedule s, boolean setupOnly) {
	
		if (!setupOnly) {
		    slots = s.slots;
		    classes = s.classes;
		    criteria = s.criteria;
		    fitness = s.fitness;
		} else {
		    // reserve space for time-soace slots in chromosome code
		    slots = new Vector<List<CourseClass>>();
		    slots.setSize(xmlBuild.getDaysNum() * xmlBuild.getDaysHours()
			    * xmlBuild.getNumberOfRooms());
	
		    criteria = new Vector<Boolean>();
		    criteria.setSize(xmlBuild.getNumberOfClasses() * 5);
		}
	
		numberOfCrossoverPoints = s.numberOfCrossoverPoints;
		mutationSize = s.mutationSize;
		crossoverProbability = s.crossoverProbability;
		mutationProbability = s.mutationProbability;
    }

	/**
	 *
	 */
    public Schedule makeCopy(boolean setupOnly) {
		return new Schedule(this, setupOnly);
    }

	/**
	 *
	 */
    public Schedule makeNewFromPrototype() {
	
		Random r = new Random();
		Schedule newChromosome = new Schedule(this, true);
		List<CourseClass> c = xmlBuild.getCourseClasses();
	
		for (CourseClass cc : c) {
		    int nr = xmlBuild.getNumberOfRooms();
		    int dur = cc.getDurationInHours();
		    int day = r.nextInt(Integer.MAX_VALUE) % xmlBuild.getDaysNum();
		    int room = r.nextInt(Integer.MAX_VALUE) % nr;
		    int time = r.nextInt(Integer.MAX_VALUE) % (xmlBuild.getDaysHours() + 1 - dur);
		    int pos = day * nr * xmlBuild.getDaysHours() + room * xmlBuild.getDaysHours() + time;
	
		    for (int i = dur - 1; i >= 0; i--) {
				newChromosome.slots.add(pos+i,c);
		    }
	
		    newChromosome.classes.put(cc,pos);
		}
	
		newChromosome.calculateFittness();
		return newChromosome;
    }

    public void calculateFittness() {
		
		// chromosome's score
		int score = 0;
		
		int numberOfRooms = xmlBuild.getNumberOfRooms();
		
		int daySize = xmlBuild.getDaysHours() * xmlBuild.getNumberOfRooms();
		
		int ci = 0;
		
		// check criteria and calculate scores for each class in schedule
		Collection<CourseClass> courseClasses = classes.keySet();
		
		for (CourseClass courseClassKey : courseClasses) {
		    
		    // coordinate of time-space slot
		    int p = classes.get(courseClassKey);
		    int day = p/daySize;
		    int time = p % daySize;
		    int room = time / xmlBuild.getDaysHours();
		    // FIXME: okay, here is room. Shouldnt this be a timeslot?
		    //        does not make sense to me that room is calculated by
		    //        some division of time and hours.
		    
		    		    
		    int dur = courseClassKey.getDurationInHours();
		    
		    // check for room overlapping of classes
		    
		    boolean ro = false;
		    
		    // TODO: check if there should be null elements allowed in the slots
		    // without slots.elementAt(p+1) != null -> null pointer exception
		    // check the c++ code
		    
		    for (int i = dur-1; i >= 0; i--) {
				if (slots.elementAt(p+1) != null && slots.elementAt(p+1).size() > 1) {
				    ro = true;
				    break;
				}
		    }
		    
		    // on room overlapping	    
		    if (!ro) {
				score++;
		    }
		    
		    criteria.set(ci,!ro);
		    
		    CourseClass cc = courseClassKey;
		    Room r = xmlBuild.getRoomByID(room); // FIXME: here `r` can not be retrieved. What is `room`?
		    
		    if (r == null) {
			continue;
		    }
		    
		    // does current room have enough seats
		    criteria.set(ci+1,
		    	r.getNumberOfSeats() // FIXME: here is a null-pointer (which is r)
		    	>= cc.getRequiredSeats()
		    );
		    
		    if (criteria.elementAt(ci+1)) {
				score++;
		    }
		    
		    // does the current room have computers if they are required
		    criteria.set(ci+2, !cc.isLabRequired() || (cc.isLabRequired() && r.isLab()));
		    
		    if (criteria.elementAt(ci+2)) {
				score++;
		    }
		    boolean po = false;
		    boolean go = false;
		    
		    // check overlapping of classes for professors and student groups
		    for (int i = numberOfRooms, t = day * daySize + time; i > 0; i--, t+=xmlBuild.getDaysHours()) {	
				// calculate fitness value based on score
				fitness = (float) score / (xmlBuild.getNumberOfClasses() * xmlBuild.getDaysNum());
		    }
		}
    }

    // Performs crossover operation using chromosomes and returns the offspring
    public Schedule crossover(final Schedule parent2) {
		Random r = new Random();
		if (r.nextInt() % 100 > crossoverProbability) {
		    // no crossover, just copy first parent
		    return new Schedule(this, false);
		}
	
		Schedule n = new Schedule(this, true);
		int size = classes.size();
	
		boolean cp[] = new boolean[size];
	
		// determine crossover points randomly
	
		for (int i = numberOfCrossoverPoints; i > 0; i--) {
	
		    while (true) {
				int p = r.nextInt() % size;
				if (!cp[p]) {
				    cp[p] = true;
				    break;
				}
		    }
		}
	
		Iterator<CourseClass> it1 = classes.keySet().iterator();
		Iterator<CourseClass> it2 = parent2.classes.keySet().iterator();
	
		boolean first = r.nextBoolean();
		CourseClass currentCourseClassKey;
		
		// TODO: Remove redundant Code
		
		for (int i = 0; i < size; i++) {
		    
		    if (first) {
		
			currentCourseClassKey = it1.next();
				// insert class from first parant into new chromosome's class
				// table
			n.classes.put(currentCourseClassKey,classes.get(currentCourseClassKey));
				// all time-space slots of class are copied
				for (int j = currentCourseClassKey.getDurationInHours()-1; j >= 0; j--) {
				    n.slots.elementAt(classes.get(currentCourseClassKey)+i).add(currentCourseClassKey);
				}
		    } else {		
			
			currentCourseClassKey = it2.next();
			
				// insert class from first parant into new chromosome's class
				// table
				n.classes.put(currentCourseClassKey,classes.get(currentCourseClassKey));
				// all time-space slots of class are copied
				for (int j = currentCourseClassKey.getDurationInHours()-1; j >= 0; j--) {
				    n.slots.elementAt(classes.get(currentCourseClassKey)+i).add(currentCourseClassKey);
				}
		    }
	
		    // crossover points
	
		    if (cp[i]) {
				// change source chromosome
				first = !first;
		    }
		}
	
		n.calculateFittness();
	
		return n;
    }

    // Performs mutation on chromosome
    public void mutation() {
	
		Random r = new Random();
	
		if (r.nextInt() % 100 > mutationProbability) {
		    return;
		}
	
		int numberOfClasses = classes.size();
		
		// move selected number of classes at random position
		for (int i = mutationSize; i > 0; i--) {
	
		    // select random chromosome for movement
		    int mpos = r.nextInt() % numberOfClasses;
		    int pos1 = 0;
	
		    Iterator<CourseClass> it = classes.keySet().iterator();
		    CourseClass currentClassCourse = it.next();

		    while (mpos > 0) {
				currentClassCourse = it.next();
				mpos--;
		    }
	
		    // current time-space slot used by class
		    pos1 = classes.get(it);
	
		    CourseClass cc1 = currentClassCourse;
	
		    // determine position of class randomly
		    int nr = xmlBuild.getNumberOfRooms();
		    int dur = cc1.getDurationInHours();
		    int day = r.nextInt() % xmlBuild.getDaysNum();
		    int room = r.nextInt() % nr;
		    int time = r.nextInt() % (xmlBuild.getDaysHours() + 1 - dur);
		    int pos2 = day * nr * xmlBuild.getDaysHours() + room
			    * xmlBuild.getDaysHours() + time;
	
		    // move all time-space slots
		    for (int j = dur - 1; j >= 0; j--) {
				// remove class hour from current time-space slot
				List<CourseClass> c1 = slots.elementAt(pos1 + j);
		
				for (CourseClass cc : c1) {
				    if (cc == cc1) {
						c1.remove(cc);
						break;
				    }
				}
		
				// move class hour to new time-space slot
				slots.elementAt(pos2 + j).add(cc1);
			}
	
		    classes.put(cc1,pos2);
		}
	
		calculateFittness();
    }

    public Vector<List<CourseClass>> getSlots() {
		// TODO
		return slots;
    }

    public HashMap<CourseClass,Integer> getClasses() {
		// TODO
		return classes;
    }

    public float getFitness() {
		return fitness;
    }

    public Vector<Boolean> getCriteria() {
		return criteria;
    }

    public static void setXmlBuild(Builder xmlBuild) {
		Schedule.xmlBuild = xmlBuild;
    }

    public static Builder getXmlBuild() {
		return Schedule.xmlBuild;
    }

}
