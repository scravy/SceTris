/* PicnicException.java / 1:03:41 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * Problem in chair, not in computer
 */
public class PicnicException extends Exception {

    private static final long serialVersionUID = 8928894907102255503L;

    public PicnicException() {
        super();

    }

    public PicnicException(final String arg0) {
        super(arg0);

    }

    public PicnicException(final String arg0, final Throwable arg1) {
        super(arg0, arg1);

    }

    public PicnicException(final Throwable arg0) {
        super(arg0);

    }

}
