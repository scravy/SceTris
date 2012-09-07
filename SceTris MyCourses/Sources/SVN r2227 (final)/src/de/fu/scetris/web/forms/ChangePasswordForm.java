/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.junction.annotation.meta.Author;
import de.fu.junction.data.Password;
import de.fu.scetris.data.Person;
import de.fu.scetris.data.formsupport.AbstractForm;
import de.fu.weave.AccessDeniedException;

/**
 * A Form to change ones own password.
 */
@Author("Julian Fleischer")
public class ChangePasswordForm extends AbstractForm {

    @SuppressWarnings("serial")
    public static class PasswordsDoNotMatch extends Exception {
        public PasswordsDoNotMatch(final String $message) {
            super($message);
        }
    }

    private static final long serialVersionUID = -708132292494443346L;

    @Field
    @Required
    @Pos(1.0)
    public Password oldPassword;

    @Field
    @Required
    @Pos(2.1)
    public Password password;

    @Field
    @Required
    @Pos(2.2)
    public Password passwordRepeat;

    @Override
    public boolean commit() throws Exception {
        Person $user = manager().getPerson(user().id());
        if (!$user.getLoginPassword().equals(oldPassword.getMD5())) {
            addMessage("", "", new AccessDeniedException("Incorrect password"));
            return false;
        }
        if (password.equalsMD5(passwordRepeat)) {
            $user
                    .setLoginPassword(password.getMD5())
                    .pushChanges();
            return true;
        }
        addMessage("", "", new PasswordsDoNotMatch("Please retype the new password properly!"));
        return false;
    }
}
