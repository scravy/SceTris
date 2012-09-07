package scheduler.dao.tests;

import scheduler.data.Course;
import scheduler.data.Lecturer;
import scheduler.data.Room;

public class testClasses {
	
	public static Lecturer lecturer = new Lecturer("Klaus-Peter","Löhr");
	public static Course course = new Course("ALP III",90,150, lecturer);
	public static Room room = new Room("HS",150);
	
	static {
	    lecturer.setId(2);
	    course.setId(1);
	}
}
