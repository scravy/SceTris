/* Relationship.java / 4:01:43 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.reflect;

import org.w3c.dom.Element;

import de.fu.weave.xml.annotation.XmlElement;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
@XmlElement("relationship")
public class RelationshipReflector extends RelationReflector {
	/**
	 * 
	 * @since Iteration2
	 */
	final EntityReflector object;

	/**
	 * 
	 * @since Iteration2
	 */
	final EntityReflector subject;

	/**
	 * @param xmlRelationship
	 * @since Iteration2
	 */
	RelationshipReflector(final Schema parent, final Element xmlRelationship) {
		super(parent, xmlRelationship);

		subject = parent.entities.get(xmlRelationship.getAttribute("subject"));
		object = parent.entities.get(xmlRelationship.getAttribute("object"));
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public EntityReflector getObject() {
		return object;
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public EntityReflector getSubject() {
		return subject;
	}
}
