/* Server.java / 5:02:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;

/**
 * Server provides an interface for listening on a certain port for incoming
 * commands. When a command approaches it is dispatched and executed when being
 * ready.
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public interface Server extends Runnable {

	static int DEFAULT_PORT = 50000;
	
	/**
	 * 
	 * @throws IpcException
	 * @since Iteration3
	 */
	void startListening() throws IpcException;

	/**
	 * 
	 * @param port
	 * @throws IpcException
	 * @since Iteration3
	 */
	void startListening(int port) throws IpcException;
}
