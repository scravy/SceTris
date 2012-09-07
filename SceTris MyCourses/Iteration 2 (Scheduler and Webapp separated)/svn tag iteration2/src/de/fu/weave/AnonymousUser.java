/* AnonymousUser.java / 5:06:09 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

/**
 * An anonymous user (that has not logged in)
 * <p>
 * The implementation is simple: Every function return always false.
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class AnonymousUser implements User {

	@Override
	public boolean hasPrivilege(final String name) {
		return false;
	}

	@Override
	public boolean hasPrivilege(final String name, final String target) {
		return false;
	}

	@Override
	public int id() {
		return -1;
	}

	@Override
	public boolean isSuperUser() {
		return false;
	}

	@Override
	public String loginName() {
		return null;
	}

}
