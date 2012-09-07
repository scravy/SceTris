/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;


import de.fu.junction.annotation.meta.Author;

/**
 * 
 * 
 */
@Author("Julian Fleischer")
public class UnknownModuleException extends PicnicException {

    private static final long serialVersionUID = -7558834501893250274L;

    /**
	 * 
	 */
    public UnknownModuleException() {

    }

    /**
     * 
     * @param message
     */
    public UnknownModuleException(final String message) {
        super(message);
    }

    /**
     * 
     * @param message
     * @param cause
     */
    public UnknownModuleException(final String message, final Throwable cause) {
        super(message, cause);
    }

    /**
     * 
     * @param cause
     */
    public UnknownModuleException(final Throwable cause) {
        super(cause);
    }

}
