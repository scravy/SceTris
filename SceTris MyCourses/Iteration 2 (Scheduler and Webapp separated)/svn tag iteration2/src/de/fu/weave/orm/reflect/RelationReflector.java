/* Relation.java / 4:00:59 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.reflect;

import static de.fu.weave.xml.Namespaces.XMLNS_ENTL;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
abstract public class RelationReflector {

	/**
	 * 
	 * @since Iteration2
	 */
	final String name;

	/**
	 * 
	 * @since Iteration2
	 */
	final Schema parentSchema;

	/**
	 * 
	 * @since Iteration2
	 */
	final Map<String,Attribute> attributes;

	/**
	 * @param parent
	 * @param xmlElement
	 * @since Iteration2
	 */
	RelationReflector(final Schema parent, final Element xmlElement) {
		parentSchema = parent;
		name = xmlElement.getAttribute("name");
		attributes = new TreeMap<String,Attribute>();
		NodeList xmlAttributes = xmlElement.getElementsByTagNameNS(XMLNS_ENTL, "attribute");

		for (int i = 0; i < xmlAttributes.getLength(); i++) {
			Element xmlAttribute = (Element) xmlAttributes.item(i);
			attributes.put(xmlAttribute.getAttribute("name"), new Attribute(this, xmlAttribute));
		}
	}

	/**
	 * @param name
	 * @return
	 * @since Iteration2
	 */
	public Attribute getAttribute(final String name) {
		return attributes.get(name);
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	@XmlCollection("attributes")
	public Collection<Attribute> getAttributes() {
		return attributes.values();
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	@XmlAttribute("name")
	public String getName() {
		return name;
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	public Schema getParentSchema() {
		return parentSchema;
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public Key getPrimaryKey() {
		return null;
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public boolean isEntity() {
		return false;
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public boolean isRelationship() {
		return false;
	}
}
