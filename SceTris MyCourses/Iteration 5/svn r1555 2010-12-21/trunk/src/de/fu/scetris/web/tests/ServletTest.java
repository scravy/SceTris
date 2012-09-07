/* ServletTest.java / 11:05:22 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.tests;

import org.json.JSONTokener;
import org.junit.Test;
import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import de.fu.scetris.web.Scetris;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class ServletTest {

	@Test
	public void testJSON() throws Exception {

		JSONTokener tokenizer = new JSONTokener("{a: 1, b: 2}");
		Object obj = tokenizer.nextValue();
		System.out.println(obj.getClass());

	}

	@Test
	public void testServlet() throws Exception {

		Server server = new Server(8081);
		Context context = new Context(server, "/", Context.SESSIONS);
		Scetris fourtris = new Scetris();
		System.out.println(fourtris.loadProperties("scetris.properties", "scetris."));
		context.addServlet(new ServletHolder(fourtris), "/scetris/*");
		server.start();
		Thread.sleep(15000);
		server.stop();
	}

}
