package model.data;

import java.util.*;

public class Lecturer{

	public int id;
	public String name;
	public List<Course> courses = new LinkedList<Course>();

	public Lecturer(int id, String name){
		this.id = id;
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Course> getCourses() {
		return courses;
	}
	
	public void addCourses(Course e) {
		this.courses.add(e);
	}

	public void setCourses(List<Course> courses) {
		this.courses = courses;
	}
}
