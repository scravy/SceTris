package scheduler;

import java.io.File;


/**
 * Loads data form an XML file and creates objects in a given scheduler or creates a new scheduler
 */
public class XMLLoader {
	/**
	 * The scheduler which data is loaded into
	 */
	private Scheduler scheduler;
	
	
	/**
	 * Creates a new Scheduler for loading data into.
	 */
	XMLLoader() {
		this.scheduler = new Scheduler();
	}
	
	/**
	 * Uses an existing scheduler for laoding data into.
	 */
	XMLLoader(Scheduler scheduler) {
		this.scheduler = scheduler;
	}
	
	
	/**
	 * Loads an XML-File from a given Filename.
	 */
	public boolean load(String filename) {
		return load(new File(filename));
	}
	
	/**
	 * Loads an XML-File which is specified by a File-Object.
	 */
	public boolean load(File filename) {
		// TODO: Implement this function. Code maybe copied form branches/konrad-... or branches/anjuha...
		return false;
	}
	
	/**
	 * Returns the associated Scheduler-Object.
	 */
	public Scheduler getScheduler() {
		return scheduler;
	}
}
