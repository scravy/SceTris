/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamSource;

import org.w3c.dom.Attr;
import org.w3c.dom.CDATASection;
import org.w3c.dom.Comment;
import org.w3c.dom.DOMConfiguration;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;

/**
 * Utility methods for dealing with XML.
 * 
 * @author Julian Fleischer
 */
public class XmlHelper {
	/**
	 * Generates a Map of all values that are retrievable via a getter having an
	 * XmlAttribute-annotation
	 * 
	 * @param obj
	 *            The object to extract Attributes from
	 * @return A mapping from String (Attribute-Name) to it’s value. May contain null-values if a
	 *         getter threw an Exception
	 * @see XmlAttribute
	 */
	public static Map<String,Object> extractAttributes(final Object obj) {
		Map<String,Object> attributeValues = new TreeMap<String,Object>();
		Method[] methods = obj.getClass().getMethods();
		Object[] args = new Object[] {};
		for (Method method : methods) {
			if (method.getParameterTypes().length == 0) {
				if (method.isAnnotationPresent(XmlAttribute.class)) {
					String key = method.getAnnotation(XmlAttribute.class).value();
					try {
						attributeValues.put(key, method.invoke(obj, args));
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(
								"this should not happen since we checked it manually before", e);
					} catch (IllegalAccessException e) {
						// we are not allowed to access it. We silently ignore
						// that.
					} catch (InvocationTargetException e) {
						// something went wrong inside the invoked method :-(
						attributeValues.put(key, null);
					}
				}
			}
		}
		return attributeValues;
	}

	/**
	 * Generates a Map of all collections that are retrievable via a getter having an
	 * XmlCollection-annotation
	 * 
	 * @param obj
	 *            The object to extract collections from
	 * @return A Map that maps String keys to the retrieved Collections
	 */
	public static Map<String,Collection<?>> extractCollections(final Object obj) {
		Map<String,Collection<?>> collections = new TreeMap<String,Collection<?>>();
		Method[] methods = obj.getClass().getMethods();
		Object[] args = new Object[] {};
		for (Method method : methods) {
			if (method.getParameterTypes().length == 0) {
				if (method.isAnnotationPresent(XmlCollection.class)) {
					String key = method.getAnnotation(XmlCollection.class).value();
					// XXX: It works, but I think it should better be checked using Reflection,
					// better check the methods first for if it returns Collection<...>
					try {
						Object result = method.invoke(obj, args);
						if (result != null) {
							if (result instanceof Collection<?>) {
								collections.put(key, (Collection<?>) result);
							}
						}
					} catch (IllegalArgumentException e) {
						throw new RuntimeException(
								"this should not happen since we checked it manually before", e);
					} catch (IllegalAccessException e) {
						// we are not allowed to access it. We silently ignore that.
					} catch (InvocationTargetException e) {
						// someting went wrong inside the invoked method :-(
						// let's ignore that!
					}
				}
			}
		}
		return collections;
	}

	/**
	 * 
	 */
	final DocumentBuilder documentBuilder;

	/**
	 * 
	 */
	final TransformerFactory transformerFactory;

	/**
	 * Standard Constructor
	 * 
	 * @throws ParserConfigurationException
	 *             If your JRE is poorly configured
	 */
	public XmlHelper() throws ParserConfigurationException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		factory.setNamespaceAware(true);
		documentBuilder = factory.newDocumentBuilder();
		transformerFactory = TransformerFactory.newInstance();
	}

	/**
	 * 
	 * @param c1
	 * @param c2
	 * @return
	 * @since Iteration3
	 */
	public boolean equals(final Comment c1, final Comment c2) {
		return true;
	}

	/**
	 * 
	 * @param doc1
	 * @param doc2
	 * @return
	 * @since Iteration3
	 */
	public boolean equals(final Document doc1, final Document doc2) {
		Element d1, d2;

		DOMConfiguration c1 = doc1.getDomConfig();
		DOMConfiguration c2 = doc2.getDomConfig();
		c1.setParameter("comments", false);
		c2.setParameter("comments", false);
		c1.setParameter("entities", false);
		c2.setParameter("entities", false);
		c1.setParameter("cdata-sections", false);
		c2.setParameter("cdata-sections", false);
		doc1.normalizeDocument();
		doc2.normalizeDocument();

		d1 = doc1.getDocumentElement();
		d2 = doc2.getDocumentElement();
		return equals(d1, d2);
	}

	/**
	 * 
	 * @param e1
	 * @param e2
	 * @return
	 * @since Iteration3
	 */
	public boolean equals(final Element e1, final Element e2) {
		if ((e1 == null) && (e2 == null)) {
			throw new IllegalArgumentException("Both must not be null, only one is allowed to be");
		}
		if ((e1 == null) || (e2 == null)) {
			return false;
		}
		if (!e1.getNamespaceURI().equals(e2.getNamespaceURI())) {
			return false;
		}
		if (!e1.getLocalName().equals(e2.getLocalName())) {
			return false;
		}
		NamedNodeMap attrs = e1.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Attr attr = (Attr) attrs.item(i);
			if (attr.getLocalName() == null) {
				if (!e2.hasAttribute(attr.getName())) {
					return false;
				}
				if (!attr.getValue().equals(e2.getAttribute(attr.getName()))) {
					return false;
				}
			} else {
				if (!e2.hasAttributeNS(attr.getNamespaceURI(), attr.getLocalName())) {
					return false;
				}
				if (!attr.getValue().equals(e2.getAttributeNS(attr.getNamespaceURI(),
															  attr.getLocalName()))) {
					return false;
				}
			}
		}
		NodeList c1 = e1.getChildNodes();
		NodeList c2 = e2.getChildNodes();
		if (c1.getLength() != c2.getLength()) {
			return false;
		}
		for (int i = 0; i < c1.getLength(); i++) {
			if (!equals(c1.item(i), c2.item(i))) {
				return false;
			}
		}
		return true;
	}

	/**
	 * 
	 * @param n1
	 * @param n2
	 * @return
	 * @since Iteration3
	 */
	public boolean equals(final Node n1, final Node n2) {
		if ((n1 == null) && (n2 == null)) {
			throw new IllegalArgumentException("Both must not be null, only one is allowed to be");
		}
		if ((n1 == null) || (n2 == null)) {
			return false;
		}
		if (n1.getNodeType() != n2.getNodeType()) {
			return false;
		}
		switch (n1.getNodeType()) {
		case Node.ELEMENT_NODE:
			return equals((Element) n1, (Element) n2);
		case Node.DOCUMENT_NODE:
			return equals((Document) n1, (Document) n2);
		case Node.TEXT_NODE:
			return equals(n1, n2);
		case Node.CDATA_SECTION_NODE:
			return equals((CDATASection) n1, (CDATASection) n2);
		default:
			return false;
		}
	}

	/**
	 * 
	 * @param t1
	 * @param t2
	 * @return
	 * @since Iteration3
	 */
	public boolean equals(final Text t1, final Text t2) {
		return t1.getData().equals(t2.getData());
	}

	/**
	 * Creates a new DOMDocument.
	 * 
	 * @return An empty DOMDocument.
	 */
	public Document newDocument() {
		return documentBuilder.newDocument();
	}

	public Document newDocument(final byte[] content) throws IOException, SAXException {
		return documentBuilder.parse(new ByteArrayInputStream(content));
	}

	/**
	 * Creates a new DOMDocument from an existing Element.
	 * 
	 * @param documentElement
	 * @return
	 * @since Iteration3
	 */
	public Document newDocument(final Element element) {
		Document doc = newDocument();
		doc.appendChild(doc.importNode(element, true));
		return doc;
	}

	/**
	 * Creates a new DOMDocument from a given File.
	 * 
	 * @param file
	 *            The file to parse.
	 * @return A new DOMDocument representing the given File.
	 * @throws IOException
	 *             If an IO error occured
	 * @throws SAXException
	 *             If an error occured on parsing, i.e. the document is not well-formed
	 */
	public Document newDocument(final File file) throws IOException, SAXException {
		return documentBuilder.parse(file);
	}

	/**
	 * 
	 * @param filename
	 * @return
	 * @throws IOException
	 * @throws SAXException
	 * @since Iteration2
	 */
	public Document newDocument(final String filename) throws IOException, SAXException {
		return newDocument(new File(filename));
	}

	/**
	 * Creates a new XSLTransformer from a given File.
	 * 
	 * @param file
	 *            The file to look for an XSL-Template
	 * @return An XSL-Transformer containing the compiled XSL-Template
	 * @throws TransformerConfigurationException
	 *             If the Stylesheet contained errors
	 */
	public Transformer newTransformer(final File file) throws TransformerConfigurationException {
		Transformer transformer = transformerFactory.newTransformer(new StreamSource(file));
		transformer.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		return transformer;
	}

	/**
	 * Creates a new XSLTransformer from a given File specified as filename.
	 * 
	 * @param filename
	 *            The path to look for an XSL-Template
	 * @return An XSL-Transformer containing the compiled XSL-Template
	 * @throws TransformerConfigurationException
	 */
	public Transformer newTransformer(final String filename)
			throws TransformerConfigurationException {
		return newTransformer(new File(filename));
	}

	/**
	 * Creates a new XMLWriter
	 * 
	 * @return A newly generated XMLWriter that prints to System.out
	 */
	public XmlWriter newXMLWriter() {
		return new XmlWriter();
	}

	/**
	 * Creates a new XMLWriter
	 * 
	 * @param printWriter
	 *            Where to write to
	 * @return A newly generated XMLWriter that prints to the given PrintWriter
	 */
	public XmlWriter newXMLWriter(final PrintWriter printWriter) {
		return new XmlWriter(printWriter);
	}

	/**
	 * 
	 * @param obj
	 * @param completeSerialization
	 * @return
	 * @since Iteration3
	 */
	public Element serializeObject(final Document document, final Object obj,
								   final boolean completeSerialization) {
		return serializeObject(document, obj, completeSerialization, false);
	}

	/**
	 * 
	 * @param obj
	 * @param completeSerialization
	 * @param ignoreAnnotations
	 * @return
	 * @since Iteration3
	 */
	public Element serializeObject(final Document document, final Object obj,
								   final boolean completeSerialization,
								   final boolean ignoreAnnotations) {
		return null;
	}

	/**
	 * 
	 * @param obj
	 * @param completeSerialization
	 * @return
	 * @since Iteration3
	 */
	public Document serializeObject(final Object obj, final boolean completeSerialization) {
		return serializeObject(obj, completeSerialization, false);
	}

	/**
	 * 
	 * @param obj
	 * @param completeSerialization
	 * @param ignoreAnnotations
	 * @return
	 * @since Iteration3
	 */
	public Document serializeObject(final Object obj, final boolean completeSerialization,
									final boolean ignoreAnnotations) {
		Document document = this.newDocument();
		document.appendChild(serializeObject(obj, completeSerialization, ignoreAnnotations));
		return document;
	}
}
