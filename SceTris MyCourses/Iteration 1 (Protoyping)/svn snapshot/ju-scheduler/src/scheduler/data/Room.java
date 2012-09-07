package scheduler.data;

// Generated 29.08.2010 23:22:22 by Hibernate Tools 3.2.4.GA

/**
 * This class contains the course details.
 * 
 */
public class Room implements java.io.Serializable {

    private int id;
    private String name;
    private int capacity;

    public Room() {
    }

    public Room(String name, int capacity) {
	this.name = name;
	this.capacity = capacity;
    }

    public Room(int id, String name, int capacity) {
	this.id = id;
	this.name = name;
	this.capacity = capacity;
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

    public int getCapacity() {
	return this.capacity;
    }

    public void setCapacity(int capacity) {
	this.capacity = capacity;
    }

}
