/* Environment.java / 5:27:01 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction;

import de.fu.weave.annotation.meta.Author;

/**
 *
 */
@Author("Julian Fleischer")
public class Environment {

	/**
	 * 
	 */
	interface Context {
		public void error(String $info);

		public void info(String $info);
	}

	private static Context $context = new Context() {
		@Override
		public void error(final String $message) {
			System.err.println($message);
		}

		@Override
		public void info(final String $message) {
			System.out.println($message);
		}
	};

	public static void println(final String $message) {
		$context.info($message);
	}
}
