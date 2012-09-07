/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave;

import java.io.File;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;

import java.net.URL;
import java.util.Enumeration;
import java.util.Map;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

import de.fu.bakery.orm.java.RelationManager;
import de.fu.weave.annotation.meta.Author;
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.PrintableException;

/**
 * A Module is a pluggable component of a weavlet.
 */
@Author("Julian Fleischer")
public interface Module<R extends RelationManager> {

	/**
	 * Forces the output to be sent with the specified Content-Type
	 * 
	 * @param type
	 *            The Content-Type. This should be a recognized MIME-Type.
	 */
	void forceMimeType(String type);

	/**
	 * Checks whether a MIME-Type has been enforced using {@see #forceMimeType(String)}.
	 * 
	 * @return true if a MIME-Type has been forced.
	 */
	boolean forcesMimeType();

	/**
	 * Checks whether an output-type has been enforced using {@see #forcesType()}.
	 * 
	 * @return true if a Type as been forced.
	 */
	boolean forcesType();

	/**
	 * 
	 * @param type
	 */
	void forceType(String type);

	/**
	 * Retrieves an InputStream from which the binary data can be read.
	 * <p>
	 * Binary data may be set using with one of the various setBinaryData-methods ({@see
	 * #setBinaryData(byte[])}, {@see #setBinaryData(InputStream)}, ...)
	 * 
	 * @return An InputStream if binary data has been set or null otherwise
	 */
	InputStream getBinaryData();

	/**
	 * Retrieve the Document which is built by the Module during processing of the request
	 * 
	 * @return The DOMDocument
	 * @since Iteration2
	 */
	Document getDocument();

	/**
	 * 
	 * @param name
	 * @return
	 * @since Iteration4
	 */
	File getFile(String name);

	/**
	 * 
	 * @return null if no filename was specified previously
	 */
	String getFilename();

	/**
	 * 
	 * @return
	 */
	String getMimeType();

	/**
	 * Retrieve the Controller of this Module
	 * 
	 * @return The Controller Instance
	 */
	Controller<R> getParent();

	/**
	 * 
	 * @return
	 */
	Object getRedirect();

	/**
	 * 
	 */
	ModuleReflector<? extends Module<? extends RelationManager>> getReflector();

	/**
	 * 
	 * @return
	 */
	String getType();

	/**
	 * 
	 * @return
	 */
	boolean hasBinaryData();

	/**
	 * 
	 * @return
	 */
	boolean hasRedirect();

	/**
	 * 
	 * @param actionName
	 * @param params
	 * @param requestParams
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ModuleException
	 */
	public void invokeAction(final String actionName, final String[] params,
							 final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException;

	/**
	 * @param actionName
	 * @param params
	 * @param requestParams
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ModuleException
	 */
	public void invokeCommitAction(final String actionName, final String[] params,
								   final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException;

	/**
	 * 
	 * @return This modules RelationManager
	 */
	R manager();

	/**
	 * Appends an Object to an Element already assigned.
	 * <p>
	 * Since the object is translated to XML its name will be taken from the parent that the object
	 * is assigned to. The new element will be created within the XMLNS_ITEM-Namespace instead of
	 * XMLNS_DATA.
	 * 
	 * @param parentElement
	 *            The parent the new Element will be appended to
	 * @param value
	 *            The object that is to be assigned.
	 * @return The newly created Element
	 */
	Element put(final Element parentElement, final Object value);

	/**
	 * 
	 * @param name
	 * @return
	 */
	Element put(final String name);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final boolean value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final boolean[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final double value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final double[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final Enumeration<? extends Object> value);

	/**
	 * 
	 * @param name
	 * @param form
	 */
	<F extends Form> void put(final String name, final F form);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final float value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final float[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final int value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final int[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final Iterable<? extends Object> value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final long value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final long[] value);

	/**
	 * 
	 * @param name
	 * @param map
	 * @return
	 */
	Element put(final String name, final Map<? extends Object,? extends Object> map);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final Object value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final Object[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 */
	Element put(final String name, final String value);

	/**
	 * 
	 * @return The Session-object associated with the current request
	 */
	Session session();

	/**
	 * 
	 * @param data
	 * @param type
	 */
	void setBinaryData(byte[] data);

	/**
	 * 
	 * @param data
	 * @param type
	 */
	void setBinaryData(byte[] data, String type);

	/**
	 * 
	 * @param data
	 * @param type
	 */
	void setBinaryData(InputStream data);

	/**
	 * 
	 * @param data
	 * @param type
	 */
	void setBinaryData(InputStream data, String type);

	/**
	 * 
	 * @param data
	 * @param type
	 * @param filename
	 */
	void setBinaryData(InputStream data, String type, String filename);

	/**
	 * @param printableException
	 */
	void setException(PrintableException printableException);

	/**
	 * 
	 * @param target
	 */
	void setRedirect(String target);

	/**
	 * 
	 * @param url
	 */
	void setRedirect(URL url);

	/**
	 * 
	 * @param entityManager
	 */
	void setRelationManager(R entityManager);

	/**
	 * 
	 * @param session
	 */
	void setSession(Session session);

	/**
	 * 
	 * @return
	 */
	User user();
}
