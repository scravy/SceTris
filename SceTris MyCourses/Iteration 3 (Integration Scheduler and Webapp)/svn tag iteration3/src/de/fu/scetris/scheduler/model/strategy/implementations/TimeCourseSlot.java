/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.scheduler.model.strategy.implementations;

import java.util.ArrayList;
import java.util.List;

import de.fu.scetris.data.CourseElementInstance;

/**
 * TimeCourseTable represents a time slot. The time slot has a list of courses
 * because there might be more than one courseTable in the same room at the same
 * time. This would be a conflict.
 * 
 * @author Hagen Mahnke, Konrad Reiche
 * 
 */
public class TimeCourseSlot {

    /**
     * 
     * @since Iteration2
     */
    private List<CourseElementInstance> courseList;

    /**
     * 
     * @since Iteration2
     */
    public TimeCourseSlot() {
	courseList = new ArrayList<CourseElementInstance>();
    }

    void addCourse(CourseElementInstance course) {
	courseList.add(course);
    }
    
    boolean removeCourse(CourseElementInstance course) {
	return courseList.remove(course);
    }
    
    public boolean containsCourse(CourseElementInstance course) {
	return courseList.contains(course);
    }
    
    public List<CourseElementInstance> getCourseList() {
	return courseList;
    }
    
    public int getCourseListSize() {
	return courseList.size();
    }
}
