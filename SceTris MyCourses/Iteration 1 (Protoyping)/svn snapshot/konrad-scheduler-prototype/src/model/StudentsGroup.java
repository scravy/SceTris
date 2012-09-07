package model;
import java.util.List;

public class StudentsGroup {

    private long id;
    private String name;
    private int numberOfStudents;
    private List<CourseClass> attendedClasses;
    
    
    public StudentsGroup(long id, String name, int numberOfStudents,
	    List<CourseClass> attendedClasses) {
	this.id = id;
	this.name = name;
	this.numberOfStudents = numberOfStudents;
	this.attendedClasses = attendedClasses;
    }
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public int getNumberOfStudents() {
        return numberOfStudents;
    }
    
    public List<CourseClass> getAttendedClasses() {
        return attendedClasses;
    }
    
    public void addClass(CourseClass c) {
	attendedClasses.add(c);
    }
    
    
    
    
}
