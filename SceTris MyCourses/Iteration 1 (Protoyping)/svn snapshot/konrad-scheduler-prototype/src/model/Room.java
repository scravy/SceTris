package model;
public class Room {

    private static int nextRoomId = 0;
    
    private long id;
    private int numberOfSeats;
    private boolean lab;
    
    
    public Room(long id, int numberOfSeats, boolean lab) {
	this.id = id;
	this.numberOfSeats = numberOfSeats;
	this.lab = lab;
    }


    public static void restartIDs() {
	nextRoomId = 0;
    }
    

    public long getId() {
        return id;
    }
    
    public int getNumberOfSeats() {
        return numberOfSeats;
    }
    
    public boolean isLab() {
        return lab;
    }

    public static int getNextRoomId() {
	return nextRoomId;
    }
    
}
