package model;

import java.util.Comparator;
import model.*;

public class CourseSlotComparator implements Comparator<Schedule.CourseSlot>{
	
	public int compare (Schedule.CourseSlot one, Schedule.CourseSlot two){
	
		CourseComparator comparator = new CourseComparator();
		return comparator.compare(one.getCourse(), two.getCourse());
	}

}
