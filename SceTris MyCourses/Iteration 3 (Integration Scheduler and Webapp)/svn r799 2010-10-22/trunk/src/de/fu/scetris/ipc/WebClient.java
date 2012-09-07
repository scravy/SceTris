/* ClientImpl.java / 5:16:52 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.*;

/**
 * WebClient is the actual client implementation for the web interface.
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class WebClient implements Client {

	private Socket socket;
	private ObjectOutputStream oos;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.ipc.Client#connect(java.lang.String)
	 */
	@Override
	public void connect(final String host) throws IpcException {
		connect(host, DEFAULT_PORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.ipc.Client#connect(java.lang.String, int)
	 */
	@Override
	public void connect(final String host, final int port) throws IpcException {

		try {
			socket = new Socket(host, port);
			oos = new ObjectOutputStream(socket.getOutputStream());

		} catch (Exception e) {
			throw new IpcException(e);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.ipc.Client#sendCommand(de.fu.weave.ipc.Command)
	 */
	@Override
	public void sendCommand(final Command request) throws IpcException {

		try {
			oos.writeObject(request);
			oos.flush();
		} catch (IOException e) {
			throw new IpcException(e);
		}
	}

}
