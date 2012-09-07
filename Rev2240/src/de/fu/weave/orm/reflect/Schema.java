/* Scheme.java / 4:00:49 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.reflect;

import static de.fu.weave.xml.Namespaces.XMLNS_ENTC;

import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import de.fu.weave.xml.annotation.XmlCollection;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Schema {

	/**
	 * 
	 */
	final Map<String,EntityReflector> entities;

	/**
	 * 
	 */
	final Map<String,RelationshipReflector> relationships;

	/**
	 * 
	 */
	final Map<String,RelationReflector> relations;

	/**
	 * 
	 * @param definition
	 * @since Iteration2
	 */
	public Schema(final Document definition) {
		NodeList xmlEntities = definition.getElementsByTagNameNS(XMLNS_ENTC, "entity");
		NodeList xmlRelationships = definition.getElementsByTagNameNS(XMLNS_ENTC, "relationship");

		entities = new TreeMap<String,EntityReflector>();
		relationships = new TreeMap<String,RelationshipReflector>();
		relations = new TreeMap<String,RelationReflector>();

		for (int i = 0; i < xmlEntities.getLength(); i++) {
			Element xmlEntity = (Element) xmlEntities.item(i);
			if (!xmlEntity.hasAttribute("name")) {
				throw new IllegalArgumentException();
			}
			String name = xmlEntity.getAttribute("name");
			if (relations.containsKey(name)) {
				throw new IllegalArgumentException();
			}
			EntityReflector entity = new EntityReflector(this, xmlEntity);
			relations.put(name, entity);
			entities.put(name, entity);
		}
		for (int i = 0; i < xmlRelationships.getLength(); i++) {
			Element xmlRelationship = (Element) xmlRelationships.item(i);
			if (!xmlRelationship.hasAttribute("name")) {
				throw new IllegalArgumentException();
			}
			String name = xmlRelationship.getAttribute("name");
			if (relations.containsKey(name)) {
				throw new IllegalArgumentException();
			}
			RelationshipReflector relationship = new RelationshipReflector(this, xmlRelationship);
			relations.put(name, relationship);
			relationships.put(name, relationship);
		}
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	@XmlCollection("entities")
	public Collection<EntityReflector> getEntities() {
		return entities.values();
	}

	/**
	 * 
	 * @param name
	 * @return
	 * @since Iteration2
	 */
	public EntityReflector getEntity(final String name) {
		return entities.get(name);

	}

	/**
	 * 
	 * @param name
	 * @return
	 * @since Iteration2
	 */
	public RelationReflector getRelation(final String name) {
		return relations.get(name);
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public Collection<RelationReflector> getRelations() {
		return relations.values();
	}

	/**
	 * @return
	 * @since Iteration2
	 */
	public RelationshipReflector getRelationship(final String name) {
		return relationships.get(name);
	}

	/**
	 * 
	 * @return
	 * @since Iteration2
	 */
	@XmlCollection("relationships")
	public Collection<RelationshipReflector> getRelationships() {
		return relationships.values();
	}

}
