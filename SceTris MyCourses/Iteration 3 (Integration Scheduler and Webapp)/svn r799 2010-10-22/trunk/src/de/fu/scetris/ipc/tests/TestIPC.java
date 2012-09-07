package de.fu.scetris.ipc.tests;

import org.junit.Test;

import de.fu.scetris.ipc.IpcException;
import de.fu.scetris.ipc.ScheduleCommand;
import de.fu.scetris.ipc.SchedulerServer;
import de.fu.scetris.ipc.WebClient;

public class TestIPC {

	
	@Test
	public void testIPC() throws IpcException {
		
		SchedulerServer server = new SchedulerServer();
		server.startListening();
		
		WebClient client1 = new WebClient();
		client1.connect("127.0.0.1", 50000);
		client1.sendCommand(new ScheduleCommand());
		
		
	}
	
}
