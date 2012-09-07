package model.data;

public class Room {
	
	public Room(int id, int numberOfSeats, boolean isLab) {
		this.id = id;
		// this.raumname = raumname;
		this.numberOfSeats = numberOfSeats;
		this.isLab = isLab;
	}
	public int id;
	public String raumname;	
	public int numberOfSeats;
	public boolean isLab;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	
	
}
