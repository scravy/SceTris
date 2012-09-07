package de.fu.scetris.meta.meta.web;

import javax.servlet.http.HttpServletRequest;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * 
 * @author scravy
 *
 */
public abstract class Module {
	protected Session session;
	protected HttpServletRequest request;
	protected Document output;
	protected Controller parent;
	
	protected void init(Controller parent, Session session, HttpServletRequest request, Document output) {
		this.session = session;
		this.request = request;
		this.output = output;
		this.parent = parent;
		
		Element e = output.createElementNS("http://technodrom.scravy.de/2010/data", "data");
		output.appendChild(e);
	}
	
	protected Element put(String name, String value) {
		Element e = output.createElementNS("http://technodrom.scravy.de/2010/data", name);
		e.appendChild(output.createTextNode(value));
		return (Element) output.getDocumentElement().appendChild(e);
	}
}
