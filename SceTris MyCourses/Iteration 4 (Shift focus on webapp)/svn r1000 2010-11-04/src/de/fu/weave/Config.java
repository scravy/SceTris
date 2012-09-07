/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.util.Map;
import java.util.Properties;
import java.util.TreeMap;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.fu.weave.xml.XmlHelper;

/**
 * A Wrapper for Key-Value configuration data that allows only reading.
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class Config {
	/**
	 * 
	 */
	final private Map<String,String> map;

	/**
	 * 
	 */
	final private Document xmldoc;

	/**
	 * Constructs the Config-Wrapper form a Map of Key-Value pairs.
	 * 
	 * @param map
	 *            The Map of Key-Value pairs.
	 * @since Iteration2
	 */
	public Config(final Map<String,String> map) {
		super();
		this.map = map;
		xmldoc = null;
	}

	/**
	 * 
	 * @param properties
	 * @since Iteration3
	 */
	public Config(final Properties properties) {
		super();
		map = new TreeMap<String,String>();
		for (String key : properties.stringPropertyNames()) {
			map.put(key, properties.getProperty(key));
		}
		xmldoc = null;
	}

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public Config(final XmlHelper xmlHelper, final Document xml, final String root) {
		super();
		map = null;
		xmldoc = xmlHelper.newDocument();
		String[] path = root.split("/");
		Element current = xml.getDocumentElement();
		Node currentNode = null;
		NodeList children = null;
		for (int i = 0; i < path.length; i++) {
			children = current.getElementsByTagName(path[i]);
			if (children.getLength() != 1) {
				throw new IllegalArgumentException(
						"Given Document does not contain the specified Path.");
			}
			currentNode = children.item(0);
			if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
				throw new IllegalArgumentException(
						"Given Document does not contain an Element at the specified Path.");
			}
			current = (Element) currentNode;
		}
		xmldoc.appendChild(xmldoc.importNode(current, true));
	}

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public Config(final XmlHelper xmlHelper, final Element xml) {
		super();
		map = null;
		xmldoc = xmlHelper.newDocument(xml);
	}

	/**
	 * Retrieves a configuration-item based on an identifying key.
	 * 
	 * @param key
	 *            The key of the configuration-item.
	 * @return The associated value or null.
	 * @since Iteration2
	 */
	public String get(final String key) {
		if (map == null) {
			String[] path = key.split("/");
			Element current = xmldoc.getDocumentElement();
			Node currentNode = null;
			NodeList children = null;
			for (int i = 0; (i + 1) < path.length; i++) {
				children = current.getElementsByTagName(path[i]);
				if (children.getLength() != 1) {
					return null; // XXX: or throw an Exception here?
				}
				currentNode = children.item(0);
				if (currentNode.getNodeType() != Node.ELEMENT_NODE) {
					return null;
				}
				current = (Element) currentNode;
			}
			children = current.getElementsByTagName(path[path.length - 1]);
			if (children.getLength() > 0) {
				current = (Element) children.item(0);
				if (current.hasChildNodes()) {
					return current.getFirstChild().getTextContent();
				}
				return "";
			}
			if (current.hasAttribute(path[path.length - 1])) {
				return current.getAttribute(path[path.length - 1]);
			}
			return null;
		}
		return map.get(key);
	}

	public Object get(final String key, final Object def) {
		String val = get(key);
		if (val == null) {
			return def;
		}
		return val;
	}
}
