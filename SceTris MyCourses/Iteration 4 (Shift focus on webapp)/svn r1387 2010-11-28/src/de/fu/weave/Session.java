/* Session.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * Maintains data about a Users session
 * <p>
 * Typically such data includes whether the user is logged in or not, at what time he logged in, how
 * long he is logged in already, ...
 * 
 * @author Julian Fleischer
 * @author André Zoufahl
 */
public interface Session {
	/**
	 * 
	 * @return The preferred Content-Type
	 */
	String getContentType();

	/**
	 * 
	 * @return The Sessions locale
	 */
	String getLanguage();

	/**
	 * 
	 * @return The Sessions stylesheet
	 */
	String getStylesheet();

	/**
	 * Retrieve the Sessions owner
	 * 
	 * @return A User-object representing the current User
	 */
	@XmlAttribute("user")
	User getUser();

	/**
	 * Check if the Sessions User is logged in or not.
	 * 
	 * @return true of false
	 */
	boolean isUserLoggedIn();

	/**
	 * 
	 * @param user
	 * @since Iteration2
	 */
	void setUser(User user);
}
