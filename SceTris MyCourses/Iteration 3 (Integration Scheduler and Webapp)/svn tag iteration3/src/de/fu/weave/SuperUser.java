/* AnonymousUser.java / 5:06:09 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * The SuperUser – he is allowed to do <b>anything</b>
 * <p>
 * The implementation is simple: If asked for a permission it simply returns true. Only thing to
 * tackel is the checkPassword()-method. Guess a super-user-pw in the config is appropriate. Of
 * course as MD5-hash or something.
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class SuperUser implements User {

	final User originalUser;

	/**
	 * 
	 * @param originalUser
	 * @since Iteration2
	 */
	public SuperUser(final User originalUser) {
		this.originalUser = originalUser;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public User getOriginalUser() {
		return originalUser;
	}

	@Override
	public boolean hasPrivilege(final String name) {
		return true;
	}

	@Override
	public boolean hasPrivilege(final String name, final String target) {
		return true;
	}

	@Override
	public int id() {
		// XXX: ...
		return Integer.MIN_VALUE;
	}

	@Override
	@XmlAttribute("superuser")
	public boolean isSuperUser() {
		return true;
	}

	@Override
	public String loginName() {
		return originalUser.loginName();
	}
}
