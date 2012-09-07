/* ServerImpl.java / 5:17:12 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.ipc;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * ScheduleServer is the actual server implementation for the Scheduler.
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
public class SchedulerServer implements Server {

	private Socket clientSocket;
	private ServerSocket serverSocket;

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.ipc.Server#startListening()
	 */
	@Override
	public void startListening() throws IpcException {
		startListening(DEFAULT_PORT);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see de.fu.weave.ipc.Server#startListening(int)
	 */
	@Override
	public void startListening(final int port) throws IpcException {

		try {
			serverSocket = new ServerSocket(port);
			System.out.println("Server listening on port " + port + ".");
		} catch (IOException e) {
			throw new IpcException(e);
		}

		new Thread(this).start();

	}

	@Override
	public void run() {

		while (true) {

			try {
				System.out.println("Waiting for connections.");
				clientSocket = serverSocket.accept();
				System.out.println("Accepted a connection from: "
						+ clientSocket.getInetAddress());
				
				new Connection(clientSocket);
				
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

}
