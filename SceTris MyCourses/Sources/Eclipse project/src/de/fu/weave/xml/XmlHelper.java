/* XmlHelper.java / 3:17:18 PM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.xml;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.xml.parsers.ParserConfigurationException;

import de.fu.junction.Tuple;
import de.fu.weave.xml.annotation.XmlAttribute;
import de.fu.weave.xml.annotation.XmlCollection;
import de.fu.weave.xml.annotation.XmlDependency;
import de.fu.weave.xml.annotation.XmlElement;

/**
 *
 */
public class XmlHelper extends de.fu.junction.xml.XmlHelper {
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
		for (Method method : methods)
			if (method.getParameterTypes().length == 0)
				if (method.isAnnotationPresent(XmlAttribute.class)) {
				String key = method.getAnnotation(XmlAttribute.class).value();
				try {
					attributeValues.put(key, method.invoke(obj));
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
		for (Method method : methods)
			if (method.getParameterTypes().length == 0)
				if (method.isAnnotationPresent(XmlCollection.class)) {
				String key = method.getAnnotation(XmlCollection.class).value();
				// XXX: It works, but I think it should better be checked using Reflection,
				// better check the methods first for if it returns Collection<...>
				try {
					Object result = method.invoke(obj);
					if (result != null)
						if (result instanceof Collection<?>)
							collections.put(key, (Collection<?>) result);
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
		return collections;
	}

	/**
	 * @param value
	 * @return
	 * @since Iteration4
	 */
	public static List<Tuple<String,Object>> extractElements(final Object obj) {
		List<Tuple<String,Object>> elements = new LinkedList<Tuple<String,Object>>();
		Method[] methods = obj.getClass().getMethods();
		for (Method m : methods)
			if (m.isAnnotationPresent(XmlElement.class)) {
				XmlElement annotation = m.getAnnotation(XmlElement.class);
				String name = annotation.value();
				if (m.isAnnotationPresent(XmlDependency.class)) {
					String depends = m.getAnnotation(XmlDependency.class).value();
					try {
						if (obj.getClass().getMethod(depends).invoke(obj).equals(false)) continue;
					} catch (Exception e) {
						continue;
					}
				}
				try {
					elements.add(new Tuple<String,Object>(name, m.invoke(obj)));
				} catch (Exception e) {
					elements.add(new Tuple<String,Object>(name, null));
				}
			}
		return elements;
	}

	/**
	 * @throws ParserConfigurationException
	 */
	public XmlHelper() throws ParserConfigurationException {
		super();
	}
}
