/* Entity.java / 4:01:24 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.reflect;

import org.w3c.dom.Element;

import de.fu.weave.xml.annotation.XmlElement;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@XmlElement("entity")
public class EntityReflector extends RelationReflector {
	EntityReflector(final Schema parent, final Element xmlEntity) {
		super(parent, xmlEntity);
	}
}
