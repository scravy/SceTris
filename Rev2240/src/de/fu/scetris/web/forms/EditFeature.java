/* NewUser.java / 7:19:33 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web.forms;


import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.Feature;
import de.fu.weave.AccessDeniedException;
import de.fu.weave.annotation.CheckPrivilege;

/**
 * A Form to edit a {@see Feature}.
 */
@Author("Julian Fleischer")
public class EditFeature extends Feature.Form {

    /**
	 * 
	 */
    private static final long serialVersionUID = -708132292494443346L;

    @Override
    @CheckPrivilege("admin.editPrivilege")
    public boolean commit() throws Exception {
        if (!user().hasPrivilege("admin.editPrivilege")
                && !user().hasPrivilege("admin.editPrivilege", id)) {
            throw new AccessDeniedException();
        }
        Feature $feature = manager().getFeature(id);
        $feature
                .setName(name)
                .setDescription(description)
                .pushChanges();
        return true;
    }
}
