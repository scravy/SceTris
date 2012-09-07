package scheduler.data.base;

public abstract class Item {
	private int id;
	
	
	public Item(int id) {
		this.id = id;
	}
	
	public Item() {
	    
	}
	
	public int hashCode() {
		return this.id;
	}
	
	public int getID() {
		return this.id;
	}
	
	public String toString() {
		return this.getClass().getName() + " #" + id;
	}
	
}
