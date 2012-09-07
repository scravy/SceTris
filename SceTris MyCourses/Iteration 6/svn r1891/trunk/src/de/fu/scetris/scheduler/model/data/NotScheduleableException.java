package de.fu.scetris.scheduler.model.data;

public class NotScheduleableException extends Exception {

	/**
	 *
	 * @since Iteration5
	 */
	private static final long serialVersionUID = 5532590820547517727L;

	
	public NotScheduleableException() {
		super();
	}
	
	public NotScheduleableException(String message) {
		super(message);
	}
	
	public NotScheduleableException(Throwable throwable) {
		super(throwable);
	}
	
}
