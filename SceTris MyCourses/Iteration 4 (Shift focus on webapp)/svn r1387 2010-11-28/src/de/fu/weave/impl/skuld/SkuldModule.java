/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.skuld;

import static de.fu.weave.xml.Namespaces.*;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.Controller;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.Session;
import de.fu.weave.reflect.ActionReflector;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.PrintableException;
import de.fu.weave.xml.XmlHelper;

/**
 * Abstract base class for all Application modules.
 * <p>
 * A module is dynamically loaded at startup of a {@link Controller}. The modules to be loaded are
 * specified in web.xml via “module:name_of_the_module”, where “name_of_the_module” is the name that
 * the module itself has propagated via it’s ModuleName-Annotation. This is also the name by which a
 * module is reffered by an HTTP request. See the about-Module for an example.
 * 
 * @author Julian Fleischer
 */
public abstract class SkuldModule<R extends RelationManager> implements Module<R> {

	/**
	 * The Session which is to be used in the Module (null if none is available)
	 */
	protected Session session = null;

	/**
	 * The Document to be constructed by this Module
	 * <p>
	 * Can be retrieved via getDocument()
	 * 
	 * @see de.fu.weave.Module
	 */
	protected final Document document;

	/**
	 * A RelationManager to be used by the Module (null if none is available)
	 * <p>
	 * Whether a Module requires the database or not is designated using the FIXME: This needs to be
	 * parameterized as <M> or so
	 * 
	 * @ModuleInfo-Annotation.
	 * 
	 * @see de.fu.weave.annotation.Action
	 */
	protected R relationManager = null;

	/**
	 * The Controller which is responsible for this Module
	 */
	protected final Controller<R> parent;

	/**
	 * 
	 */
	private InputStream binaryData = null;

	/**
	 * 
	 */
	private String mimeType = null;

	/**
	 * 
	 */
	private String type = null;

	/**
	 * 
	 */
	private Object redirect = null;

	/**
	 * 
	 */
	private String filename = null;

	/**
	 * 
	 * @param parent
	 */
	public SkuldModule(final Controller<R> parent) {
		super();
		this.parent = parent;
		document = parent.getXmlHelper().newDocument();
		document.appendChild(document.createElementNS(XMLNS_DATA, "meta"));
	}

	@Override
	public void forceMimeType(final String type) {
		mimeType = type;
	}

	@Override
	public boolean forcesMimeType() {
		return mimeType != null;
	}

	@Override
	public boolean forcesType() {
		return type != null;
	}

	@Override
	public void forceType(final String type) {
		this.type = type;
	}

	@Override
	public InputStream getBinaryData() {
		return binaryData;
	}

	@Override
	public final Document getDocument() {
		return document;
	}

	@Override
	public File getFile(final String name) {
		return new File(getParent().getContext().getRealPath(name));
	}

	@Override
	public String getFilename() {
		return this.filename;
	}

	@Override
	public String getMimeType() {
		return mimeType;
	}

	@Override
	public final Controller<R> getParent() {
		return parent;
	}

	@Override
	public Object getRedirect() {
		return redirect;
	}

	@Override
	public ModuleReflector<? extends Module<? extends RelationManager>> getReflector() {
		try {
			// XXX: Dont know better.
			@SuppressWarnings("unchecked")
			ModuleReflector<?> reflector = ModuleReflector.forClass(getClass());
			return reflector;
		} catch (ModuleException e) {
			throw new RuntimeException(e);
			// do nothing, should not happen at all
		}
	}

	@Override
	public R getRelationManager() {
		return relationManager;
	}

	@Override
	public String getType() {
		return type;
	}

	@Override
	public boolean hasBinaryData() {
		return binaryData != null;
	}

	@Override
	public boolean hasRedirect() {
		return redirect != null;
	}

	@Override
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {
		@SuppressWarnings("unchecked")
		// XXX: Dont know better.
		Class<? extends SkuldModule<R>> clazz = (Class<? extends SkuldModule<R>>) getClass();
		ModuleReflector<? extends Module<? extends RelationManager>> module =
				ModuleReflector.forClass(clazz);

		ActionReflector<? extends Module<? extends RelationManager>> m =
				module.getAction(actionName);
		m.invokeAction(this, params, requestParams);
	}

	@Override
	public void invokeCommitAction(final String actionName, final String[] params,
								   final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {
		@SuppressWarnings("unchecked")
		// XXX: Dont know better.
		Class<? extends SkuldModule<R>> clazz = (Class<? extends SkuldModule<R>>) getClass();
		ModuleReflector<? extends Module<? extends RelationManager>> module =
				ModuleReflector.forClass(clazz);

		ActionReflector<? extends Module<? extends RelationManager>> m =
				module.getAction(actionName);
		m.invokeCommitAction(this, params, requestParams);
	}

	@Override
	public final Element put(final Element parentElement, final Object value) {
		Element newElement = document.createElementNS(XMLNS_ITEM, parentElement.getLocalName());
		if (value == null) {
			newElement.setAttribute("isNull", "yes");
			return newElement;
		}
		Map<String,Object> attributes = XmlHelper.extractAttributes(value);
		for (String key : attributes.keySet()) {
			try {
				newElement.setAttribute(key, attributes.get(key).toString());
			} catch (Exception e) {
				// XXX: ignore exceptions silenty
			}
		}
		Map<String,Collection<?>> collections = XmlHelper.extractCollections(value);
		for (String key : collections.keySet()) {
			try {
				Element container = document.createElementNS(XMLNS_DATA, key);
				newElement.appendChild(container);
				for (Object item : collections.get(key)) {
					put(container, item);
				}
			} catch (Exception e) {
				// XXX: ignore exceptions silenty
			}
		}
		newElement.appendChild(document.createTextNode(value.toString()));
		parentElement.appendChild(newElement);
		return newElement;
	}

	@Override
	public final Element put(final String name) {
		Element e = document.createElementNS(XMLNS_DATA, name);
		document.getDocumentElement().appendChild(e);
		return e;
	}

	@Override
	public final Element put(final String name, final boolean value) {
		return put(name, value ? "yes" : "no");
	}

	@Override
	public final Element put(final String name, final boolean[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final double value) {
		return put(name, Double.toString(value));
	}

	@Override
	public final Element put(final String name, final double[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final Enumeration<? extends Object> value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		while (value.hasMoreElements()) {
			put(element, value.nextElement());
		}
		return element;
	}

	@Override
	public final Element put(final String name, final float value) {
		return put(name, Float.toString(value));
	}

	@Override
	public final Element put(final String name, final float[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final int value) {
		return put(name, Integer.toString(value));
	}

	@Override
	public final Element put(final String name, final int[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (int object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final Iterable<? extends Object> value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final long value) {
		return put(name, Long.toString(value));
	}

	@Override
	public final Element put(final String name, final long[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final Map<? extends Object,? extends Object> map) {
		Element element = put(name);
		if (map == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object key : map.keySet()) {
			Element item = put(element, name);
			put(item, map.get(key)).setAttribute("key", key.toString());
		}
		return element;
	}

	@Override
	public final Element put(final String name, final Object value) {
		if (value == null) {
			Element element = put(name);
			element.setAttribute("isNull", "yes");
			return element;
		}
		return put(put(name), value);
	}

	@Override
	public final Element put(final String name, final Object[] value) {
		Element element = put(name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		for (Object object : value) {
			put(element, object);
		}
		return element;
	}

	@Override
	public final Element put(final String name, final String value) {
		Element element = document.createElementNS(XMLNS_DATA, name);
		if (value == null) {
			element.setAttribute("isNull", "yes");
			return element;
		}
		element.appendChild(document.createTextNode(value));
		document.getDocumentElement().appendChild(element);
		return element;
	}

	@Override
	public void setBinaryData(final byte[] data) {
		PipedOutputStream dataStream = new PipedOutputStream();
		try {
			binaryData = new PipedInputStream(dataStream);
			dataStream.write(data, 0, data.length);
		} catch (IOException e) {
			binaryData = null;
		}
	}

	@Override
	public void setBinaryData(final byte[] data, final String type) {
		setBinaryData(data);
		if (hasBinaryData()) {
			forceMimeType(type);
		}
	}

	@Override
	public void setBinaryData(final InputStream data) {
		binaryData = data;
	}

	@Override
	public void setBinaryData(final InputStream data, final String type) {
		setBinaryData(data);
		forceMimeType(type);
	}

	@Override
	public void setBinaryData(final InputStream data, final String type, final String filename) {
		setBinaryData(data, type);
		this.filename = filename;
	}

	@Override
	public void setException(final PrintableException printableException) {
		put("exception", printableException);
	}

	@Override
	public void setRedirect(final String target) {
		redirect = target;
	}

	@Override
	public void setRedirect(final URL url) {
		redirect = url;
	}

	@Override
	public void setRelationManager(final R entityMgr) {
		relationManager = entityMgr;
	}

	@Override
	public void setSession(final Session session) {
		this.session = session;
	}

}
