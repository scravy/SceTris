/* Session.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * Maintains data about a Users session
 * <p>
 * Typically such data includes whether the user is logged in or not, at what time he logged in, how
 * long he is logged in already, ...
 */
@Author({ "Julian Fleischer", "André Zoufahl" })
public interface Session {
	/**
	 * 
	 * @return The preferred Content-Type
	 */
	String getContentType();

	/**
	 * Get this Sessions locale (the name of the language associated with this session)
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
	 * 
	 * @param name
	 * @return
	 */
	Object getValue(String name);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 */
	boolean getValue(String name, boolean fallback);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 */
	char getValue(String name, char fallback);

	/**
	 * 
	 * @param <T>
	 * @param name
	 * @param type
	 * @return
	 */
	<T> T getValue(String name, Class<T> type);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 * @since Iteration4
	 */
	double getValue(String name, double fallback);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 */
	float getValue(String name, float fallback);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 */
	int getValue(String name, int fallback);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 * @since Iteration4
	 */
	long getValue(String name, long fallback);

	/**
	 * 
	 * @param name
	 * @param fallback
	 * @return
	 * @since Iteration4
	 */
	String getValue(String name, String fallback);

	/**
	 * Check if the Sessions User is logged in or not.
	 * 
	 * @return true of false
	 */
	boolean isUserLoggedIn();

	/**
	 * Associated a user with this session.
	 * <p>
	 * setUser will be called by LoginHandler and LogoutHandler. setUser(null) shall set isLoggedIn
	 * to false whereas every other user shall set isLoggedIn to true. If a user is newly logged in
	 * his data has to be fetched form the database, or the database has to be passed to the newly
	 * created User objects so that it can construct itself. Implementation detail. You might use
	 * EntityManager.select(...) for this purpose.
	 * 
	 * @param user
	 */
	void setUser(User user);

	/**
	 * 
	 * @param name
	 * @param value
	 */
	void setValue(String name, Object value);
}
