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
import de.fu.weave.reflect.ModuleReflector;
import de.fu.weave.xml.PrintableException;

/**
 * @author Julian Fleischer
 * 
 */
public interface Module<R extends RelationManager> {

	/**
	 * 
	 * @param type
	 * @since Iteration4
	 */
	void forceMimeType(String type);

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean forcesMimeType();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean forcesType();

	/**
	 * 
	 * @param type
	 * @since Iteration4
	 */
	void forceType(String type);

	/**
	 * 
	 * @return
	 * @since Iteration4
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
	 * @return null if no filename is specified
	 * @since Iteration4
	 */
	String getFilename();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	String getMimeType();

	/**
	 * Retrieve the Controller of this Module
	 * 
	 * @return The Controller Instance
	 * @since Iteration2
	 */
	Controller<R> getParent();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	Object getRedirect();

	/**
	 * 
	 * @param session
	 * @since Iteration2
	 */
	ModuleReflector<? extends Module<? extends RelationManager>> getReflector();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	R getRelationManager();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	String getType();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean hasBinaryData();

	/**
	 * 
	 * @return
	 * @since Iteration4
	 */
	boolean hasRedirect();

	/**
	 * @param actionName
	 * @param params
	 * @param requestParams
	 * @throws IllegalAccessException
	 * @throws InvocationTargetException
	 * @throws ModuleException
	 * @since Iteration2
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
	 * @since Iteration2
	 */
	public void invokeCommitAction(final String actionName, final String[] params,
								   final Map<String,String[]> requestParams)
			throws IllegalAccessException, InvocationTargetException, ModuleException;

	/**
	 * Appends an Object to an Element already assigned.
	 * <p>
	 * Since the object is translated to XML its name will be taken from the parent that the object
	 * is assigned to. The new element will be created within the XMLNS_ITEM-Namespace instead of
	 * XMLNS_DATA.
	 * <p>
	 * 
	 * @param parentElement
	 *            The parent the new Element will be appended to
	 * @param value
	 *            The object that is to be assigned.
	 * @return The newly created Element
	 * @since Iteration2
	 */
	Element put(final Element parentElement, final Object value);

	/**
	 * 
	 * @param name
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final boolean value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final boolean[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final double value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final double[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final Enumeration<? extends Object> value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final float value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final float[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final int value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final int[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final Iterable<? extends Object> value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final long value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final long[] value);

	/**
	 * 
	 * @param name
	 * @param map
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final Map<? extends Object,? extends Object> map);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final Object value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final Object[] value);

	/**
	 * 
	 * @param name
	 * @param value
	 * @return
	 * @since Iteration2
	 */
	Element put(final String name, final String value);

	/**
	 * 
	 * @param data
	 * @param type
	 * @since Iteration4
	 */
	void setBinaryData(byte[] data);

	/**
	 * 
	 * @param data
	 * @param type
	 * @since Iteration4
	 */
	void setBinaryData(byte[] data, String type);

	/**
	 * 
	 * @param data
	 * @param type
	 * @since Iteration4
	 */
	void setBinaryData(InputStream data);

	/**
	 * 
	 * @param data
	 * @param type
	 * @since Iteration4
	 */
	void setBinaryData(InputStream data, String type);

	/**
	 * 
	 * @param data
	 * @param type
	 * @param filename
	 * @since Iteration4
	 */
	void setBinaryData(InputStream data, String type, String filename);

	/**
	 * @param printableException
	 * @since Iteration2
	 */
	void setException(PrintableException printableException);

	/**
	 * 
	 * @param target
	 * @since Iteration4
	 */
	void setRedirect(String target);

	/**
	 * 
	 * @param url
	 * @since Iteration4
	 */
	void setRedirect(URL url);

	/**
	 * 
	 * @param entityManager
	 * @since Iteration2
	 */
	void setRelationManager(R entityManager);

	/**
	 * 
	 * @param session
	 * @since Iteration2
	 */
	void setSession(Session session);
}
