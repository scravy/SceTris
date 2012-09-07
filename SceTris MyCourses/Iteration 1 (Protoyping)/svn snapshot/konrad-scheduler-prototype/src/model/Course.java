package model;
public class Course {

    private long id;
    private String name;
    
    public Course(long id, String name) {
	this.id = id;
	this.name = name;
    }
    
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    
}
