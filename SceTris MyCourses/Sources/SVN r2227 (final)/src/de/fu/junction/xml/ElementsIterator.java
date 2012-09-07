/* NodeListIterator.java / 12:38:36 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.xml;


import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

/**
 *
 */
public class ElementsIterator implements Iterator<Element> {

    private int $i = 0;

    private final NodeList $nodeList;

    public ElementsIterator(final NodeList $nodeList) {
        this.$nodeList = $nodeList;
        do {
            $i++;
        } while (($i < $nodeList.getLength()) && !($nodeList.item($i) instanceof Element));
    }

    @Override
    public boolean hasNext() {
        return $i < $nodeList.getLength();
    }

    @Override
    public Element next() {
        Element $e = (Element) $nodeList.item($i);
        do {
            $i++;
        } while (($i < $nodeList.getLength()) && !($nodeList.item($i) instanceof Element));
        return $e;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException();
    }

}
