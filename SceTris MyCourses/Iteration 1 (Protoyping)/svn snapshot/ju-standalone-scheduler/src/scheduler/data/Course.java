package scheduler.data;

import scheduler.data.base.NamedItem;


public class Course extends NamedItem {
	private Lecturer lecturer = null;
	
	private int duration;
	private int requiredSeats;

	
	public Course(int id, String name, int requiredSeats, int duration) {
		super(id, name);
		this.requiredSeats = requiredSeats;
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
	
	public int getRequiredSeats() {
		return this.requiredSeats;
	}
}
