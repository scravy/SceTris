/* Like.java / 11:38:05 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.filters;


import de.fu.junction.annotation.meta.Author;
import de.fu.weave.orm.Filter;

/**
 * 
 */
@Author("Julian Fleischer")
public class IsFalse implements Filter {

    private final String fieldName;

    /**
     * 
     * @param field
     * @param likeString
     */
    public IsFalse(final String field) {
        fieldName = field;
    }

    @Override
    public String toString() {
        // http://www.postgresql.org/docs/8.0/interactive/functions-comparison.html
        return "\"" + fieldName + "\" = 'f'";
    }
}
