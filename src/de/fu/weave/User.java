/* User.java / 4:46:08 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;


import de.fu.junction.annotation.meta.Author;
import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * 
 * @author Julian Fleischer
 */
@Author("Julian Fleischer")
public interface User {

    /**
     * 
     * @param name
     * @return
     */
    boolean hasPrivilege(String name);

    boolean hasPrivilege(String name, Object target);

    /**
     * 
     * @param name
     * @param target
     * @return
     */
    boolean hasPrivilege(String name, String target);

    int id();

    /**
     * 
     * @return
     */
    @XmlAttribute("superuser")
    boolean isSuperUser();

    /**
     * 
     * @return
     * @since Iteration2
     */
    @XmlAttribute("name")
    String loginName();
}
