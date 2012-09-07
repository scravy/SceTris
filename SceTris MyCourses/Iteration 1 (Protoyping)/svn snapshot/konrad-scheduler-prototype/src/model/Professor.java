package model;
import java.util.List;

public class Professor {

    private long id;
    private String name;
    private List<CourseClass> courseClasses;
    
    
    public Professor(long id, String name, List<CourseClass> courseClasses) {
	this.id = id;
	this.name = name;
	this.courseClasses = courseClasses;
    
    }
    
    public void addCourseClass(CourseClass courseClass) {
	courseClasses.add(courseClass);
    }    
    
    public long getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public List<CourseClass> getCourseClasses() {
        return courseClasses;
    }
    
}
