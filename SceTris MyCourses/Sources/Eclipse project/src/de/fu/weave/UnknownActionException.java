/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;


import de.fu.junction.annotation.meta.Author;

/**
 * -
 * 
 */
@Author("Julian Fleischer")
public class UnknownActionException extends PicnicException {

    private static final long serialVersionUID = -1970236482130160617L;

    /**
	 * 
	 */
    public UnknownActionException() {

    }

    /**
     * 
     * @param message
     *            -
     */
    public UnknownActionException(final String message) {
        super(message);
    }

    /**
     * 
     * @param message
     *            -
     * @param cause
     *            -
     */
    public UnknownActionException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * 
     * @param cause
     *            -
     */
    public UnknownActionException(final Throwable cause) {
        super(cause);
    }

}
