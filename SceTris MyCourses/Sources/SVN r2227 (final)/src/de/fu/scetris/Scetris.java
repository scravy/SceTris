/* Scetris.java / 6:03:26 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.handler.ResourceHandler;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

import de.fu.scetris.web.ScetrisServlet;
import de.fu.weave.WeavletInstantiationException;

/**
 * A standalone version of SceTris that is able to run from the command-line directly using an
 * embedded servlet container.
 */
public class Scetris {

	/**
	 * Runs SceTris using an embedded servlet container.
	 * <p>
	 * The main-methods takes up two (optional) arguments, the first being the port used for serving
	 * (defaults to 8081) and the second being the path to configuration file (defaults to
	 * “../scetris.properties”).
	 * <p>
	 * All necessary files (templates etc.) need to be in their appropriate sub-directories
	 * contained in the working directory of Scetris. It is recommended to place the configuration
	 * file outside of the working directory (thus the default to the upper level), since everything
	 * in the working directory and it’s subdirectories will be made accessible via Web.
	 * 
	 * @param $args
	 *            The list of Arguments (0: port, 2: path to configuration-file).
	 */
	public static void main(final String... $args) {
		int $port = 8081;
		String $properties = "../scetris.properties";
		if ($args.length > 1) $properties = $args[1];
		try {
			$port = Integer.parseInt($args[0]);
		} catch (Exception $exc) {
		}
		Server $server = new Server($port);
		ResourceHandler $fileHandler = new ResourceHandler();
		$fileHandler.setResourceBase(".");
		$fileHandler.setWelcomeFiles(new String[] { "index.htm" });
		$server.addHandler($fileHandler);
		Context $context = new Context($server, "/", Context.SESSIONS);
		try {
			ScetrisServlet $scetris = new ScetrisServlet();
			$scetris.loadProperties($properties, "scetris.");
			$context.addServlet(new ServletHolder($scetris), "/scetris/*");
			$server.start();
			$server.join();
		} catch (WeavletInstantiationException $exc) {
			System.err.println($exc);
		} catch (InterruptedException $exc) {
			System.err.println("Could not join server-thread.");
		} catch (Exception $exc) {
			System.err.println($exc);
		}
	}
}
