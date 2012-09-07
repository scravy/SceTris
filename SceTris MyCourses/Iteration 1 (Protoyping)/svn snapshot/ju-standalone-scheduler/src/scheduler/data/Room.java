package scheduler.data;

import scheduler.data.base.NamedItem;


public class Room extends NamedItem {

	private int capacity;
	
	
	public Room(int id, String name, int capacity) {
		super(id, name);
		this.capacity = capacity;
	}
	
	
	public int getCapacity() {
		return capacity;
	}
}
