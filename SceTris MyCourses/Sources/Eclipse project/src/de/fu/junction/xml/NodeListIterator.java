/* NodeListIterator.java / 12:38:36 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.xml;


import java.util.Iterator;

import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 */
public class NodeListIterator implements Iterator<Node> {

    private int $i = 0;

    private final NodeList $nodeList;

    public NodeListIterator(final NodeList $nodeList) {
        this.$nodeList = $nodeList;
    }

    @Override
    public boolean hasNext() {
        return $i < $nodeList.getLength();
    }

    @Override
    public Node next() {
        return $nodeList.item($i++);
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
