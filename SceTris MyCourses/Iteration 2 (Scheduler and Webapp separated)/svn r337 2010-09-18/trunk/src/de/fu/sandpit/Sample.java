package de.fu.sandpit;

/**
 * A Sample class to be tested using jUnit.
 */
public class Sample {
	private long store = 0;
	private int numberOfValues = 0;
	
	
	public void add(int i) {
		if (i < 0) {
			throw new IllegalArgumentException();
		}
		store += i;
		numberOfValues++;
	}
	
	public long getSum() {
		return store;
	}
	
	public double getAverage() {
		return store / numberOfValues;
	}
}
