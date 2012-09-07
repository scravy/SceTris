/* ControllerException.java / 9:59:22 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl;


import de.fu.junction.annotation.meta.Author;

/**
 * A ControllerException is thrown when trying to create a {@see Controller} with invalid
 * parameters.
 * 
 * @since Iteration2
 */
@Author("Julian Fleischer")
public class ControllerException extends Exception {

    /**
     * 
     * @since Iteration2
     */
    private static final long serialVersionUID = -6211693817357617776L;

    /**
     * The Standard-Constructor.
     * 
     * @since Iteration2
     */
    public ControllerException() {

    }

    /**
     * A Constructor with a message.
     * 
     * @param $message
     * @since Iteration2
     */
    public ControllerException(final String $message) {
        super($message);
    }

    /**
     * A constructor with a message and a cause.
     * 
     * @param message
     * @param cause
     *            Another Exception or Error which has caused the ControllerException
     * @since Iteration2
     */
    public ControllerException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * A constructor providing only the cause, not an additional message.
     * <p>
     * The message from the cause will be used for the underlying exception.
     * 
     * @param cause
     * @since Iteration2
     */
    public ControllerException(final Throwable cause) {
        super(cause.getMessage(), cause);
    }

}
