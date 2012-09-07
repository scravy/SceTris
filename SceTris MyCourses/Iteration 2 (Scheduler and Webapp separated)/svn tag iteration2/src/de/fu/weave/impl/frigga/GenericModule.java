/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.impl.frigga;

import static de.fu.weave.xml.Namespaces.XMLNS_DATA;
import static de.fu.weave.xml.Namespaces.XMLNS_ITEM;

import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.weave.Controller;
import de.fu.weave.Module;
import de.fu.weave.ModuleException;
import de.fu.weave.Session;
import de.fu.weave.orm.RelationManager;
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
public abstract class GenericModule<R extends RelationManager> implements Module<R> {

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
	 * @param parent
	 */
	public GenericModule(final Controller<R> parent) {
		super();
		this.parent = parent;
		document = parent.getXmlHelper().newDocument();
		document.appendChild(document.createElementNS(XMLNS_DATA, "meta"));
	}

	@Override
	public final Document getDocument() {
		return document;
	}

	@Override
	public final Controller<R> getParent() {
		return parent;
	}

	@Override
	public ModuleReflector<? extends Module<? extends RelationManager>> getReflector() {
		try {
			// XXX: Dont know better.
			@SuppressWarnings("unchecked")
			ModuleReflector<?> reflector = ModuleReflector.forClass(getClass());
			return reflector;
		} catch (ModuleException e) {
			// do nothing, should not happen at all
		}
		return null;
	}

	@Override
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException {
		@SuppressWarnings("unchecked")
		// XXX: Dont know better.
		Class<? extends GenericModule<R>> clazz = (Class<? extends GenericModule<R>>) getClass();
		ModuleReflector<? extends Module<? extends RelationManager>> module =
				ModuleReflector.forClass(clazz);

		ActionReflector<? extends Module<? extends RelationManager>> m =
				module.getAction(actionName);
		m.invokeAction(this, params, requestParams);
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
	public void setEntityManager(final R entityMgr) {
		relationManager = entityMgr;
	}

	@Override
	public void setException(final PrintableException printableException) {
		put("exception", printableException);
	}

	@Override
	public void setSession(final Session session) {
		this.session = session;
	}

}
