package de.fu.scetris.scheduler.data;


import org.apache.log4j.Logger;

public class SchedulingException extends Exception {

    /**
     * 
     * @since Iteration5
     */
    private static final long serialVersionUID = 1359661231858456854L;

    public SchedulingException() {
        super();
    }

    public SchedulingException(final String message) {
        super(message);
    }

    public SchedulingException(final Throwable throwable) {
        super(throwable);
        Logger.getLogger("Scheduler").fatal("Exception while scheduling", throwable);
    }
}
