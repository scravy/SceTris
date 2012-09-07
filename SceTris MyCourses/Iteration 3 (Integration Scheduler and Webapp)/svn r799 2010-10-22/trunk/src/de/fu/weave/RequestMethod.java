/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * An Enumeration of HTTP-Request-Methods
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public enum RequestMethod {
	/**
	 * GET: A simple query
	 * 
	 * @since Iteration2
	 */
	GET {
		@Override
		public String toString() {
			return "get";
		}
	},

	/**
	 * POST: A more complidated query, typically used to pass form data or other data that is not
	 * posted twice normally
	 * 
	 * @since Iteration2
	 */
	POST {
		@Override
		public String toString() {
			return "post";
		}
	},

	/**
	 * PUT: A resource should be created (typically a file)
	 * 
	 * @since Iteration2
	 */
	PUT,

	/**
	 * DELETE: A resource should be deleted (typically a file)
	 * 
	 * @since Iteration2
	 */
	DELETE,

	/**
	 * HEAD: Like get, but return Headers only
	 * 
	 * @since Iteration2
	 */
	HEAD;
}
