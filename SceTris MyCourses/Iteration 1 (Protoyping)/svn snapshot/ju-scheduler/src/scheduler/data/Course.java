package scheduler.data;

// Generated 29.08.2010 23:22:22 by Hibernate Tools 3.2.4.GA

/**
 * 			This class contains the course details.
 *   		
 */
public class Course implements java.io.Serializable {

    private int id;
    private String name;
    private int duration;
    private int seats;
    private Lecturer lecturer;

    public Course() {
    }

    public Course(String name, int duration, int seats, Lecturer lecturer) {
	this.name = name;
	this.duration = duration;
	this.seats = seats;
	this.lecturer = lecturer;
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public int getDuration() {
	return this.duration;
    }

    public void setDuration(int duration) {
	this.duration = duration;
    }

    public int getSeats() {
	return this.seats;
    }

    public void setSeats(int seats) {
	this.seats = seats;
    }

    public Lecturer getLecturer() {
	return this.lecturer;
    }

    public void setLecturer(Lecturer lecturer) {
	this.lecturer = lecturer;
    }

}
