/* Configuration.aj / 5:17:34 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.data.aspects;

import java.util.List;

import org.apache.log4j.Logger;

import de.fu.scetris.data.Privilege;
import de.fu.scetris.data.RelationManager;
import de.fu.scetris.data.Role;
import de.fu.weave.orm.DatabaseException;
import de.fu.weave.orm.filters.Filters;

/**
 *
 * @author Julian Fleischer
 */
public aspect InstallPrivileges {

	/**
	 * 
	 * @param key
	 * @param defaultValue
	 */
	public synchronized boolean RelationManager.installPrivileges(org.w3c.dom.Document $privileges) throws DatabaseException {
    	String[][] $roles = {
                {"User management (create)", "admin.users", "admin.createUser", "admin.createRole", "admin.createPrivilege"},
                {"Course management (create)", "admin.courses", "admin.createCourse", "admin.createCourseElement", "admin.createCourseElementInstance", "admin.createCourseInstance", "admin.createProgram", "admin.createAcademicTerm", "admin.createYear"},
                {"System configuration", "admin.sysconfig"}};
    	
	    try {
	        this.beginTransaction();
            for (org.w3c.dom.Element $e : new de.fu.junction.xml.XmlChildElements($privileges.getDocumentElement())) {
                this.createPrivilege($e.getTextContent());
            }
            this.commitTransaction();
	    } catch (Exception $exc) {
	        this.abortTransaction();
	        return false;
	    }
	    RelationManager $this = this;
	    Logger $logger = Logger.getLogger($this.getClass());
	    
        String $cur = null;
	    try {
	        for (int $i = 0; $i < $roles.length; $i++) {
	            $cur = $roles[$i][0];
	            Role $role = $this.createRole($cur);
	            for (int $j = 1; $j < $roles[$i].length; $j++) {
	                List<Privilege> $privs = $this.getPrivilege(Filters.eq("name", $roles[$i][$j]));
	                if ($privs.size() > 0) {
	                    $this.createRoleImpliesPrivilege($role, $privs.get(0));
	                }
	            }
	        }
	    } catch (DatabaseException $exc) {
	        $logger.warn(String.format("Failed: %s", $cur), $exc);
	    }
	    
        return true;
	}
	
}
