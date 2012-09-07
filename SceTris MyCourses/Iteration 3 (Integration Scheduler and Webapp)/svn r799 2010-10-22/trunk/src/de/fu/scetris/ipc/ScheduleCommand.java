package de.fu.scetris.ipc;

/**
 * The ScheduleCommand is a concrete command which requests a schedule of the
 * current database.
 * 
 * @author Konrad Reiche
 * 
 */
public class ScheduleCommand implements Command {

	/**
	 * Generated serial unique identifier
	 */
	private static final long serialVersionUID = -4200798463153977370L;

	@Override
	public void execute() {
		System.out.println("Scheduling has started... lol you wish! *fart*");
	}

}
