package scheduler.data.base;

public abstract class NamedItem extends Item {
	private String name;
	
	public NamedItem(int id, String name) {
		super(id);
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public String toString() {
		return this.getClass().getName() + " \"" + this.name + "\"";
	}
}
