/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.scetris.data.Feature;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.annotation.CheckPrivilege;

public class NewFeature extends Feature.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Override
    @CheckPrivilege("admin.createFeature")
    public boolean commit() throws Exception {
        if (!user().hasPrivilege("admin.createFeature")) {
            throw new AccessDeniedException();
        }
        manager().fullyCreateFeature(name, description);
        return true;
    }
}
