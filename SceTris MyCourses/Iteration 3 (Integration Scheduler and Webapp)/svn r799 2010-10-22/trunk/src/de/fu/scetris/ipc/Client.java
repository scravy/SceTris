/* Client.java / 5:02:20 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;


/**
 * Client provides an interface for creating a connecting to the Server. When a
 * connection is established different commands can be sent.
 * 
 * @author Julian Fleischer
 * @author Konrad Reiche
 * @since Iteration3
 */
public interface Client {

	static int DEFAULT_PORT = 50000;

	/**
	 * Connecets to the specified IP address.
	 * 
	 * @param the
	 *            IP address.
	 * @throws IpcException
	 *             if an error occurs during the connection.
	 * @since Iteration3
	 */
	void connect(String host) throws IpcException;

	/**
	 * Connecets to the specified IP address and the specified port number.
	 * 
	 * @param host
	 *            the IP address.
	 * @param port
	 *            the port number.
	 * @throws IpcException
	 *             if an error occurs during the connection.
	 * @since Iteration3
	 */
	void connect(String host, int port) throws IpcException;

	/**
	 * Sends a command to the connected server.
	 * 
	 * @param request
	 *            the requested command.
	 * @throws IpcException 
	 * @since Iteration3
	 */
	void sendCommand(Command request) throws  IpcException;

}
