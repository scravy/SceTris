/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.web;


import javax.xml.transform.TransformerFactory;

import de.fu.junction.annotation.meta.Author;
import de.fu.scetris.data.RelationManager;
import de.fu.weave.annotation.Action;
import de.fu.weave.impl.frigg.FriggModule;
import de.fu.weave.orm.DatabaseException;

/**
 * A listing of people involved at this institution.
 */
@Author({ "Julian Fleischer", "Andre Zoufahl" })
public class Help extends FriggModule<RelationManager> {

    /**
     * Standard constructor.
     * 
     * @param parent
     *            The parent Servlet.
     */
    public Help(final ScetrisServlet parent) {
        super(parent);
    }

    @Action(template = "help/help.xsl")
    public void _default() throws Exception {
        put("TRANSFORMER", TransformerFactory.newInstance().getClass());
    }

    @Action(template = "help/help.topic.xsl")
    public void showTopic(final String[] $path) throws DatabaseException {
        if (($path.length > 0) && $path[0].matches("^[a-zA-Z0-9_-]+$")) {
            put("page", $path[0]);
        }
    }
}
