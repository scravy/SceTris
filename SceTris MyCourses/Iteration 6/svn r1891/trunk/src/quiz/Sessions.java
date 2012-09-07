/* Sessions.java / 4:30:32 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package quiz;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.mortbay.jetty.Server;
import org.mortbay.jetty.servlet.Context;
import org.mortbay.jetty.servlet.ServletHolder;

/**
 *
 */
public class Sessions extends HttpServlet {

	public static void main(final String... $args) throws Exception {
		int $port = 1234;
		try {
			$port = Integer.parseInt($args[0]);
		} catch (Exception $exc) {
		}
		Server server = new Server(1234);

		Context context = new Context(Context.SESSIONS);
		context.setContextPath("/");
		server.setHandler(context);

		context.addServlet(new ServletHolder(new Sessions()), "/*");

		server.start();
		server.join();
	}

	@Override
	protected void doGet(final HttpServletRequest $req, final HttpServletResponse $resp)
		throws ServletException,
		IOException {
		$resp.setContentType("text/plain");
		HttpSession $s = $req.getSession();
		if ($s == null) {
			$s = $req.getSession(true);
		}
		Integer $count = (Integer) $s.getAttribute("foo");
		if ($count == null) {
			System.out.println("not set");
			$count = 1;
		}
		$s.setAttribute("foo", ++$count);
		PrintWriter $p = $resp.getWriter();

		$p.println($count);
		$p.println($s.getCreationTime());
		$p.flush();
		$p.close();
	}

}
