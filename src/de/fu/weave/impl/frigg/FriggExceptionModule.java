/* FriggExceptionModule.java / 1:11:15 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigg;


import org.apache.log4j.Logger;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.Weavlet;
import de.fu.weave.orm.RelationManager;

/**
 * A special module which sole responsibility is to handle Exceptions gracefully.
 */
@Author("Julian Fleischer")
public class FriggExceptionModule<R extends RelationManager> extends FriggModule<R> {

    /**
     * Standard constructor.
     * 
     * @param $parent
     *            The parent Weavlet that threw the exception
     * @param $exc
     *            The exception that disrupted normal program flow
     */
    public FriggExceptionModule(final Weavlet<R> $parent, final Throwable $exc) {
        super($parent);
        String $name = $exc.getClass().getSimpleName();
        put("exception", $name);
        if ($exc instanceof RuntimeException) {
            Logger.getLogger($parent.getClass()).error(String.format("RuntimeException : %s", $name),
                $exc);
        }
    }
}
