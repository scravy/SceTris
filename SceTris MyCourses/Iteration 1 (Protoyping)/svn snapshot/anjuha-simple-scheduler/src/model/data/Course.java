package model.data;

public class Course {
	private static int i = 1;
	
	public int id;
	public String name;
	public int amountOfHours;
	public int amountOfSeatsRequired;
	public Lecturer heldBy;
	public Room heldIn;

	public Course( int id, String name, int amountOfHours, Room heldIn, int amountOfSeatsRequired){
		this.id = id;
		this.name = name;
		this.amountOfHours = amountOfHours;
		this.heldIn = heldIn;
		this.amountOfSeatsRequired = amountOfSeatsRequired;
	}

	public int getID(){
		return id;
	}

	public Course(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	public Course(String string, int duration, int seats, Lecturer p) {
		this.id = i++;
		this.name = string;
		this.amountOfHours = duration;
		this.amountOfSeatsRequired = seats;
		this.heldBy = p;
	}

	/*
	@Override
	public boolean equals(Object e) {
		if (!(e instanceof Course))
			return false;
		Course tmp = (Course) e;
		System.out.println("tmp :: "+tmp.name+"  this :: "+this.name);
		if( tmp.name == this.name)
			return true;
		return false;
	} */
	
	public void isHeldBy(Lecturer lecturer){
		this.heldBy = lecturer;	
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getAmountOfHours() {
		return amountOfHours;
	}

	public void setAmountOfHours(int amountOfHours) {
		this.amountOfHours = amountOfHours;
	}

	public int getAmountOfSeatsRequired() {
		return amountOfSeatsRequired;
	}

	public void setAmountOfSeatsRequired(int amountOfSeatsRequired) {
		this.amountOfSeatsRequired = amountOfSeatsRequired;
	}

	public String getShortInfo() {
		String ret = this.id+" - "+this.name;
		return ret;
	}
	
}
