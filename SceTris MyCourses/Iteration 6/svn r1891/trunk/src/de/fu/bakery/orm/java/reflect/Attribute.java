/* Attribute.java / 4:02:13 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java.reflect;

import org.w3c.dom.Element;

import de.fu.weave.xml.annotation.XmlAttribute;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Attribute {

	final RelationReflector parentRelation;

	final String name;

	final Boolean isForeignKey;

	/**
	 * @param relation
	 * @param xmlAttribute
	 * @since Iteration2
	 */
	public Attribute(final RelationReflector parent, final Element xmlAttribute) {
		parentRelation = parent;
		name = xmlAttribute.getAttribute("name");
		isForeignKey = xmlAttribute.hasAttribute("ref");
	}

	@XmlAttribute("name")
	public String getName() {
		return name;
	}

	public EntityReflector getReferencedEntity() {
		return null;
	}

	public Class<?> getType() {
		return getClass();
	}

	@XmlAttribute("type")
	public String getTypeName() {
		return "";
	}

	@XmlAttribute("foreignKey")
	public boolean isForeignKey() {
		return isForeignKey;
	}
}
