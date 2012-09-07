package de.fu.scetris.scheduler.data;

public class SchedulingException extends Exception {

	/**
	 *
	 * @since Iteration5
	 */
	private static final long serialVersionUID = 1359661231858456854L;

	public SchedulingException() {
		super();
	}
	
	public SchedulingException(String message) {
		super(message);
	}
	
	public SchedulingException(Throwable throwable) {
		super(throwable);
	}
}
