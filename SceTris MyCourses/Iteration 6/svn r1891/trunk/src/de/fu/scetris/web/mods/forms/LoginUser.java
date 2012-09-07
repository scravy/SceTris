/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.mods.forms;


import de.fu.weave.annotation.meta.Author;
import de.fu.weave.reflect.ValidationException;


@Author("Andre Zoufahl")
public class LoginUser extends de.fu.scetris.data.formsupport.AbstractForm {

	private static final long serialVersionUID = -708132292494443346L;


	@Field
	@Required
	@Pos(1)
	public String username;

	@Field
	@Required
	@Pos(2)
	public de.fu.junction.data.Password password;

	@Override
	public boolean commit() throws Exception {
		if(username.length() > 5 && password.length() > 5) {
			return true;
		}
		addMessage("username", username,  new ValidationException(
		"could not match credentials"));
		return false;
	}

	@Override
	public void init() {
		
	}
}
