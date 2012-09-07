/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

/**
 * Utiliy for writing XML.
 * <p>
 * XMLWriter writes an XML-Source (e.g. a Document) represented in memory into a stream-based object
 * (a file, a String, ...). Currently only writing to a PrintWriter is supported.
 * <p>
 * Please not that this XMLWriter is not suitable for writing XMLSchema or XSLT-Stylesheet, since it
 * changes the prefixes of namespaces and
 * 
 * @author Julian Fleischer
 * @since Iteration2
 */
public class XmlWriter {
	/**
	 * Escape a String so that it is suitable for inclusion in XML Element Content
	 * 
	 * @param text
	 *            The String to escape
	 * @return The escaped String
	 * @since Iteration2
	 */
	public static String escape(final String text) {
		return text.replace("&", "&amp;").replace("<", "&lt;");
	}

	/**
	 * Escape a String so that it is suitable for inclusion in an XML Attribute Value
	 * 
	 * @param text
	 *            The String to escape
	 * @return The escaped string
	 * @since Iteration2
	 */
	public static String escapeValue(final String text) {
		return escape(text).replace("\"", "&quot;").replace("\n", "&#xA;");
	}

	/**
	 * 
	 * @since Iteration2
	 */
	private final PrintWriter out;

	/**
	 * This map holds the prefix table, a mapping from namespace-uri to prefix
	 * <p>
	 * Since this is an XML<b>Writer</b> we are interested in looking up prefixes by namespace, not
	 * the other way around as one might expect
	 * 
	 * @since Iteration2
	 */
	private final Map<String,String> namespaces;

	/**
	 * The default constructor. Will write to System.out
	 * 
	 * @since Iteration2
	 */
	public XmlWriter() {
		super();
		out = new PrintWriter(System.out);
		namespaces = new TreeMap<String,String>();
	}

	/**
	 * This constructor will write to the given PrintWriter
	 * 
	 * @param printWriter
	 *            A PrintWriter to write to
	 * @since Iteration2
	 */
	public XmlWriter(final PrintWriter printWriter) {
		super();
		out = printWriter;
		namespaces = new TreeMap<String,String>();
	}

	/**
	 * Put a namespace into the prefix-table
	 * 
	 * @param namespace
	 *            A namespace-uri
	 * @since Iteration2
	 */
	private void createNamespace(final String namespace) {
		String prefix = "n" + namespaces.size();
		namespaces.put(namespace, prefix);
	}

	/**
	 * Gather all namespaces used in an Element and its children
	 * <p>
	 * If a new namespace is found it is inserted to the prefix-table using
	 * {@link #createNamespace(String)}.
	 * 
	 * @param element
	 *            The element to be scanned
	 * @since Iteration2
	 */
	private void findNamespaces(final Element element) {
		NodeList nodes = element.getChildNodes();
		NamedNodeMap attrs = element.getAttributes();
		String namespace = element.getNamespaceURI();
		if (!namespaces.containsKey(namespace)) {
			createNamespace(namespace);
		}
		findNamespaces(attrs);
		findNamespaces(nodes);
	}

	/**
	 * Gather all namespaces used in a List of Attributes
	 * <p>
	 * If a new namespace is found it is inserted to the prefix-table using
	 * {@link #createNamespace(String)}.
	 * 
	 * @param attrs
	 *            The NamednodeMap containing Attributes to be scanned
	 * @since Iteration2
	 */
	private void findNamespaces(final NamedNodeMap attrs) {
		for (int i = 0; i < attrs.getLength(); i++) {
			Attr attr = (Attr) attrs.item(i);
			String namespace = attr.getNamespaceURI();
			if (namespace != null) {
				if (!namespaces.containsKey(namespace)) {
					createNamespace(namespace);
				}
			}
		}
	}

	/**
	 * Gather all namespaces used in a List of any Nodes
	 * <p>
	 * If elements are found in the nodeList, {@link #findNamespaces(Element)} will be called.
	 * 
	 * @param nodes
	 *            A NodeList to be scanned
	 * @since Iteration2
	 */
	private void findNamespaces(final NodeList nodes) {
		for (int i = 0; i < nodes.getLength(); i++) {
			Node node = nodes.item(i);
			if (node.getNodeType() == Node.ELEMENT_NODE) {
				Element element = (Element) node;
				findNamespaces(element);
			}
		}
	}

	/**
	 * Generate the fully qualified Name for the Output
	 * 
	 * @param node
	 *            The node whose name is to be printed
	 * @return A fully qualified Name
	 * @since Iteration2
	 */
	private String name(final Node node) {
		if (node.getLocalName() == null) {
			return node.getNodeName();
		} else {
			return nsPrefix(node) + ((nsPrefix(node) == null) ? "" : ":") + node.getLocalName();
		}
	}

	/**
	 * Lookup the prefix for a namespace
	 * 
	 * @param node
	 *            The node from which the namespace will be taken
	 * @return The prefix
	 * @since Iteration2
	 */
	private String nsPrefix(final Node node) {
		return namespaces.get(node.getNamespaceURI());
	}

	/**
	 * Write a Document to the associated PrintWriter, including an XML-Prolog
	 * 
	 * @param document
	 *            The Document to be written.
	 * @throws IOException
	 *             In case of an Input/Output Error.
	 * @since Iteration2
	 */
	public synchronized void write(final Document document) throws IOException {
		write(document, "<?xml version=\"1.0\"?>\n");
	}

	/**
	 * Write a Document to the associated PrintWriter.
	 * 
	 * @param document
	 *            The Document to be written.
	 * @param prolog
	 *            The XML-Prolog to be written (e.g. a <tt><?xml-header?></tt>)
	 * @throws IOException
	 *             In case of an Input/Output Error.
	 */
	public synchronized void write(final Document document, final String prolog) throws IOException {
		Element documentElement = document.getDocumentElement();

		if (documentElement != null) {
			findNamespaces(documentElement);
			out.print(prolog);
			out.print('<' + name(documentElement));
			for (String namespace : namespaces.keySet()) {
				String prefix = namespaces.get(namespace);
				StringBuffer xmlns = new StringBuffer(" xmlns");
				if (!prefix.equals("")) {
					xmlns.append(':');
				}
				out.print(xmlns + prefix + "=\"" + escapeValue(namespace) + "\"");
			}
			writeAttrs(documentElement);
			out.print('>');
			for (int i = 0; i < documentElement.getChildNodes().getLength(); i++) {
				if (documentElement.getChildNodes().item(i).getNodeType() == Node.ELEMENT_NODE) {
					writeElement((Element) documentElement.getChildNodes().item(i));
				}
			}
			out.print("</" + name(documentElement) + ">\n");
		}
	}

	/**
	 * Write the Attributes for a given Element
	 * 
	 * @param element
	 *            The element form which the attributes are to be written
	 * @since Iteration2
	 */
	private void writeAttrs(final Element element) {
		NamedNodeMap attrs = element.getAttributes();
		for (int i = 0; i < attrs.getLength(); i++) {
			Attr attr = (Attr) attrs.item(i);
			out.print(" " + name(attr) + "=\"" + escapeValue(attr.getValue()) + "\"");
		}
	}

	/**
	 * Write an Element and its children
	 * 
	 * @param element
	 *            The element to be processed
	 * @since Iteration2
	 */
	private void writeElement(final Element element) {
		NodeList nodes = element.getChildNodes();
		out.print('<' + name(element));
		writeAttrs(element);
		if (nodes.getLength() == 0) {
			out.print("/>");
		} else {
			out.print('>');
			for (int i = 0; i < nodes.getLength(); i++) {
				Node node = nodes.item(i);
				if (node.getNodeType() == Node.ELEMENT_NODE) {
					writeElement((Element) node);
				} else if (node.getNodeType() == Node.TEXT_NODE) {
					out.print(escapeValue(node.getTextContent()));
				}
			}
			out.print("</" + name(element) + '>');
		}
	}
}
