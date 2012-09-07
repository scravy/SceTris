/* ChildNodes.java / 1:16:37 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.junction.xml;


import java.util.Iterator;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 *
 */
public class XmlChildElements implements Iterable<Element> {

    final private NodeList $children;

    public XmlChildElements(final Node $node) {
        $children = $node.getChildNodes();
    }

    @Override
    public Iterator<Element> iterator() {
        return new ElementsIterator($children);
    }

}
