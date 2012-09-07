/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.scetris.scheduler.model.strategy.test;

import java.util.*;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import de.fu.scetris.data.*;
import de.fu.scetris.scheduler.model.data.AlgorithmConfiguration;
import de.fu.scetris.scheduler.model.strategy.implementations.*;

public class AlgorithmImplTest {

    AlgorithmImpl algorithm;

    Configuration configuration;
    AlgorithmConfiguration algorithmConfiguration;

    @Before
    public void initiliaze() {

	int numberOfTimeSlots = 50;
	int numberOfDays = 5;
	int timeSlotsPerDay[] = { 10, 10, 10, 10, 10 };
	int numberOfRooms = 50;
	int numberOfCourses = 100;

	Random rnd = new Random();
	
	List<Room> roomList = new ArrayList<Room>(numberOfRooms);
	
	for (int i = 1; i <= numberOfRooms; ++i) {
	    roomList.add(new Room(i));
	}

	List<Person> lecturerList = new ArrayList<Person>();
	
	for (int i = 1; i <= 234; ++i) {
	    lecturerList.add(new Person(i));	    
	}
	
	List<CourseElementInstance> courseList = new ArrayList<CourseElementInstance>(
		numberOfCourses);
	
		
	for (int i = 1; i <= numberOfCourses; ++i) {
	    courseList.add(new CourseElementInstance(i, lecturerList.get(rnd.nextInt(lecturerList.size())), rnd.nextInt(3)+1,
		    false, null, null, null, null));	    
	}
	

	List<Feature> featureList = new ArrayList<Feature>();

	List<ProposedScheduling> proposedSchedulingList = new ArrayList<ProposedScheduling>();

	configuration = new Configuration(numberOfTimeSlots, numberOfDays,
		timeSlotsPerDay, numberOfRooms, numberOfCourses, roomList,
		lecturerList, courseList, featureList, proposedSchedulingList);

	algorithmConfiguration = new AlgorithmConfiguration(0.3f, 0.2f, 0.8f, 0.5f, 50,
		8, 100, 1.0);

	algorithm = (AlgorithmImpl) ConcreteFactory.getInstance()
		.createAlgorithm(configuration, algorithmConfiguration);
    }

    /**
     * Postcondition: the algorithm terminated
     * 
     * Postcondition: size of the list of schedules equals populationSize
     * 
     * Postcondition: the best schedules hard fitness equals 1.0
     * 
     * Postcondition: the best schedules soft fitness equals 1.0
     */
    @Test
    public void testAlgorithmImpl() {

	algorithm.start();

	int populationSize = algorithmConfiguration.getPopulationSize();
	
	System.out.println(((ScheduleImpl)algorithm.getBestSchedule()).printSchedule());
	System.out.println(algorithm.getCurrentGeneration());
	
	Assert.assertTrue(populationSize == algorithm.getScheduleList().size());

	Assert.assertTrue(algorithm.getBestSchedule().getHardFitness() == 1.0f);

	Assert.assertTrue(algorithm.getBestSchedule().getSoftFitness() == 1.0f);
    }

}
