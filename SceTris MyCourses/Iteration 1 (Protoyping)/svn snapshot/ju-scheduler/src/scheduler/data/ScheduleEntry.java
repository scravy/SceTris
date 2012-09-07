package scheduler.data;

// Generated 29.08.2010 23:22:22 by Hibernate Tools 3.2.4.GA

/**
 * 			This class contains the course details.
 *   		
 */
public class ScheduleEntry implements java.io.Serializable {

    private int id;
    private String monday;
    private String tuesday;
    private String wednesday;
    private String thursday;
    private String friday;
    private String room;

    public ScheduleEntry() {
    }

    public ScheduleEntry(String monday, String tuesday, String wednesday,
	    String thursday, String friday, String room) {
	this.monday = monday;
	this.tuesday = tuesday;
	this.wednesday = wednesday;
	this.thursday = thursday;
	this.friday = friday;
	this.room = room;
    }

    public int getId() {
	return this.id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getMonday() {
	return this.monday;
    }

    public void setMonday(String monday) {
	this.monday = monday;
    }

    public String getTuesday() {
	return this.tuesday;
    }

    public void setTuesday(String tuesday) {
	this.tuesday = tuesday;
    }

    public String getWednesday() {
	return this.wednesday;
    }

    public void setWednesday(String wednesday) {
	this.wednesday = wednesday;
    }

    public String getThursday() {
	return this.thursday;
    }

    public void setThursday(String thursday) {
	this.thursday = thursday;
    }

    public String getFriday() {
	return this.friday;
    }

    public void setFriday(String friday) {
	this.friday = friday;
    }

    public String getRoom() {
	return this.room;
    }

    public void setRoom(String room) {
	this.room = room;
    }

}
