package scheduler.data;

import scheduler.data.base.NamedItem;



public class Lecturer extends NamedItem {
	private java.util.List<Course> myCourses = new java.util.ArrayList<Course>();

	
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
