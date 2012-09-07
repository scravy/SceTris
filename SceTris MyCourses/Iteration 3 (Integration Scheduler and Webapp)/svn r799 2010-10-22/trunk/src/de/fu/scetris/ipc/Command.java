/* IpcCommand.java / 5:09:14 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;

import java.io.Serializable;

/**
 * Command provides an interface for executing operations.
 * 
 * @author Julian Fleischer
 * @author Konrad Reiche
 * @since Iteration3
 */
public interface Command extends Serializable {

	/**
	 * Executes the command on the receiver.
	 */
	public void execute();

}
