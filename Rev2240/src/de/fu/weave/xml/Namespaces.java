/* Namespaces.java / 11:36:20 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import de.fu.junction.annotation.meta.Author;

/**
 * This class contains the XML-Namespaces used within this application as constant Strings.
 */
@Author("Julian Fleischer")
public final class Namespaces {
	/**
	 * The XMLNamespace for HTML (XHTML, HTML5)
	 */
	public static final String XMLNS_HTML = "http://www.w3.org/1999/xhtml";

	/**
	 * The XMLNamespace for XHTML (XHTML, HTML5)
	 */
	public static final String XMLNS_XHTML = "http://www.w3.org/1999/xhtml";

	/**
	 * The XMLNamespace for XHTML (XHTML, HTML5)
	 */
	public static final String XMLNS_HTML5 = "http://www.w3.org/1999/xhtml";

	/**
	 * The XMLNamespace for XMLSchema Instances
	 */
	public static final String XMLNS_XSI = "http://www.w3.org/2001/XMLSchema-instance";

	/**
	 * The XMLNamespace for ordinary data.
	 */
	public static final String XMLNS_DATA = "http://technodrom.scravy.de/2010/data";

	/**
	 * The XMLNamespace for items.
	 */
	public static final String XMLNS_ITEM = "http://technodrom.scravy.de/2010/item";

	/**
	 * The XMLNamespace for forms.
	 */
	public static final String XMLNS_FORM = "http://technodrom.scravy.de/2010/form";

	/**
	 * The XMLNamespace for exported xml-data
	 * 
	 * @since Iteration3
	 */
	public static final String XMLNS_EXPORT = "http://technodrom.scravy.de/2010/pgsqlxml";

	/**
	 * The XMLNamespace for raw request data.
	 */
	public static final String XMLNS_REQ = "http://technodrom.scravy.de/2010/request";

	/**
	 * 
	 */
	public static final String XMLNS_ENT = "http://technodrom.scravy.de/2010/relations";

	/**
	 * 
	 */
	public static final String XMLNS_ENTC = "http://technodrom.scravy.de/2010/relations/c";

	/**
	 * 
	 */
	public static final String XMLNS_XSLT = "http://www.w3.org/1999/XSL/Transform";

	/**
	 * 
	 */
	public static final String XMLNS_DC = "http://dublincore.org/documents/dcmi-namespace/";

	/**
	 * 
	 */
	public static final String XMLNS_HTML5_ANNOTATION = "http://technodrom.scravy.de/2010/html5-annotation";

	/**
	 * 
	 */
	public static final String XMLNS_PDF_ANNOTATION = "http://technodrom.scravy.de/2010/pdf-annotation";
}
