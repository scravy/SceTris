/* ReadXMLPrivileges.java / 1:11:42 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.scetris.build;


import java.io.File;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.junction.xml.XmlChildElements;
import de.fu.junction.xml.XmlHelper;

/**
 *
 */
public class ReadXMLPrivileges {
    public static void main(final String... $args) throws Exception {
        Document $privileges = XmlHelper.parse(new File("privileges.xml"));
        for (Element $e : new XmlChildElements($privileges.getDocumentElement())) {
            System.out.println($e.getTextContent());
        }
    }
}
