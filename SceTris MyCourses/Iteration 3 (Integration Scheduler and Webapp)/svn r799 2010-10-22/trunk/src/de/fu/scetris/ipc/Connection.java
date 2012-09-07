package de.fu.scetris.ipc;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 * Connection is represents an established connection and handles the incoming
 * request.
 * 
 * @author Konrad Reiche
 * 
 */
public class Connection extends Thread {


	private ObjectInputStream ois;
	
	public Connection(Socket clientSocket) throws IpcException {

		try {
			ois = new ObjectInputStream(clientSocket.getInputStream());
			this.start();
		} catch (IOException e) {
			throw new IpcException(e);
		}		
		
	}
	
	@Override
	public void run() {
		
		try {
			Command incomingRequest = (Command)ois.readObject();
			incomingRequest.execute();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
}
