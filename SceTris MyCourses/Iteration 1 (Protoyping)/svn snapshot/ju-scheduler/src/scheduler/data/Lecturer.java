package scheduler.data;

// Generated 29.08.2010 23:22:22 by Hibernate Tools 3.2.4.GA

import java.util.HashSet;
import java.util.Set;

/**
 * 			This class contains the lecturer details.
 * 		
 */
public class Lecturer implements java.io.Serializable {

    private int id;
    private String firstname;
    private String name;
    private Set<Course> myCourses = new HashSet<Course>(0);

    public Lecturer() {
    }

    public Lecturer(String firstname, String name) {
	this.firstname = firstname;
	this.name = name;
    }

    public Lecturer(String firstname, String name, Set<Course> myCourses) {
	this.firstname = firstname;
	this.name = name;
	this.myCourses = myCourses;
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getFirstname() {
	return this.firstname;
    }

    public void setFirstname(String firstname) {
	this.firstname = firstname;
    }

    public String getName() {
	return this.name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Set<Course> getMyCourses() {
	return this.myCourses;
    }

    public void setMyCourses(Set<Course> myCourses) {
	this.myCourses = myCourses;
    }

}
