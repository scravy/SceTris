package model;
import java.util.List;

public class CourseClass {

    private Professor professor;
  
    private Course course;
  
    private List<StudentsGroup> attendedStudentGroups;
    
    private int requiredSeats; // sum of student group's size
    
    private boolean requiresLab;
    
    private int durationInHours;
    
    
    public CourseClass(Professor professor, Course course,
	    List<StudentsGroup> attendedStudentGroups, int requiredSeats,
	    boolean requiresLab, int durationInHours) {
	
	this.professor = professor;
	this.course = course;
	this.attendedStudentGroups = attendedStudentGroups;
	this.requiredSeats = requiredSeats;
	this.requiresLab = requiresLab;
	this.durationInHours = durationInHours;
    }

    
    public boolean GroupsOverlap(final CourseClass c) {
	
	for (StudentsGroup g1 : attendedStudentGroups) {
	    for (StudentsGroup g2 : c.getAttendedStudentGroups()) {
		if (g1.equals(g2)) {
		    return true;
		}
	    }
	}
	
	return false;
	
    }
    
    public boolean professorOverlaps(final CourseClass c) {
	return professor.equals(c.professor);
    }
    
    public Professor getProfessor() {
        return professor;
    }

    public Course getCourse() {
        return course;
    }

    public List<StudentsGroup> getAttendedStudentGroups() {
        return attendedStudentGroups;
    }

    public int getRequiredSeats() {
        return requiredSeats;
    }

    public boolean isLabRequired() {
        return requiresLab;
    }

    public int getDurationInHours() {
        return durationInHours;
    }
    
    public boolean equals(CourseClass e) {
    	System.out.println("compare");
    	if(this.professor == e.professor && this.course == e.course)
    		return true;
    	return false;
    }
    
}
