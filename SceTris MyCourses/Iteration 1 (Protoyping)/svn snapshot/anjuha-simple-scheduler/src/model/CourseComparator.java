package model;

import model.data.*;

import java.util.Comparator;
/*
* @author Hagen
* @version Iteration 1
* Used by the TreeMap to compare two courses and thus guarantee an order.
*
*/
public class CourseComparator implements Comparator<Course>{
	@Override
	public int compare(Course courseOne, Course courseTwo){
		return courseOne.getID() - courseTwo.getID();
	}
}
