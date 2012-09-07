/* XmlImporter.java
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */
package de.fu.bakery.orm.java.imex;

import static de.fu.junction.Dynamic.*;

import java.lang.reflect.Method;
import java.sql.Connection;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import de.fu.bakery.orm.java.Entity;
import de.fu.bakery.orm.java.RelationManager;
import de.fu.bakery.orm.java.annotation.Attribute;
import de.fu.bakery.orm.java.annotation.Relation;
import de.fu.junction.ClassesComparator;
import de.fu.junction.Strings;
import de.fu.junction.Tuple;
import de.fu.junction.converter.StringConverterFactory;
import de.fu.weave.xml.Namespaces;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class XmlImporter extends BaseImporter<Document> {

	/**
	 * 
	 * @param doc
	 * @return
	 * @since Iteration4
	 */
	public static Map<String,Integer> countRelations(final Document doc) {
		Element de = doc.getDocumentElement();
		NodeList l = de.getChildNodes();
		Map<String,Integer> num = new TreeMap<String,Integer>();
		for (int i = 0; i < l.getLength(); i++) {
			Node n = l.item(i);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				Element e = (Element) n;
				int rows = e.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, "row").getLength();
				num.put(e.getLocalName(), rows);
			}
		}
		return num;
	}

	/**
	 * 
	 */
	private final StringConverterFactory converters = StringConverterFactory.newFactory();

	/**
	 * 
	 */
	private final Map<String,Element> imports = new TreeMap<String,Element>();

	/**
	 * 
	 */
	private final Map<String,Object> idMapping = new TreeMap<String,Object>();

	/**
	 * 
	 */
	private final Map<String,String> idColumns = new TreeMap<String,String>();

	/**
	 * 
	 */
	private final Map<Class<?>,Object> resources = new TreeMap<Class<?>,Object>(
			ClassesComparator.comparator);

	/**
	 * 
	 */
	private final Map<String,Method> getters = new TreeMap<String,Method>();

	/**
	 * 
	 */
	private final Map<String,Map<String,Method>> requiredSetters = new TreeMap<String,Map<String,Method>>();

	/**
	 * 
	 */
	private final Map<String,Map<String,Method>> additionalSetters = new TreeMap<String,Map<String,Method>>();

	/**
	 * 
	 */
	private final Map<String,Class<?>> idTypes = new TreeMap<String,Class<?>>();

	/**
	 * 
	 */
	final private Set<String> relations = new TreeSet<String>();

	/**
	 * 
	 */
	final private Map<Class<?>,Class<?>[]> requiredTypes = new TreeMap<Class<?>,Class<?>[]>(
			ClassesComparator.comparator);

	/**
	 * 
	 */
	private Document document;

	/**
	 * 
	 */
	final private List<Class<?>> avail;

	/**
	 * 
	 */
	volatile int x = 10;

	/**
	 * @param genericRelationManager
	 * @since Iteration4
	 */
	public XmlImporter(final RelationManager genericRelationManager) {
		setManager(genericRelationManager);

		avail = initDependencies(resources);
		for (Class<?> c : avail) {
			String name = c.getSimpleName();
			Class<?> idType;
			Method getter;

			Map<String,Method> requiredSetters = new TreeMap<String,Method>();
			Map<String,Method> additionalSetters = new TreeMap<String,Method>();
			this.requiredSetters.put(name, requiredSetters);
			this.additionalSetters.put(name, additionalSetters);

			Relation r = c.getAnnotation(Relation.class);
			List<String> required = Arrays.asList(r.requiredSqlCols());
			for (Method m : manager.getClass().getMethods()) {
				if (m.getName().equals("new" + name)) {
					requiredTypes.put(c, m.getParameterTypes());
					break;
				}
			}
			for (Method m : c.getMethods()) {
				if (m.getName().startsWith("get") && m.isAnnotationPresent(Attribute.class)) {
					Attribute annotation = m.getAnnotation(Attribute.class);
					String column = annotation.name();
					if (Arrays.asList(annotation.use()).contains("id")) {
						idColumns.put(name, column);
						continue;
					}
					try {
						Method setter = c.getMethod("set" + m.getName().substring(3), m.getReturnType());
						if (required.contains(column)) {
							requiredSetters.put(column, setter);
						} else {
							additionalSetters.put(column, setter);
						}
					} catch (NoSuchMethodException e) {
						messages.offer(e);
					}
				}
			}

			relations.add(Strings.capitalize(name));
			if (Entity.class.isAssignableFrom(c)) {
				try {
					idType = c.getMethod("id").getReturnType();
					getter = manager.getClass().getMethod("get" + name, idType);
					idTypes.put(name, idType);
					getters.put(name, getter);
				} catch (Exception e) {
					throw new RuntimeException(e);
				}
			}
		}
	}

	@Override
	public void doImport(final Document document) throws ImportException {
		this.document = document;

		Connection conn = manager.connectionManager().getConnection();
		if (conn == null) {
			throw new ImportException("No Database connected to import into");
		}

		gatherImports();
		List<Tuple<Element,Class<?>>> finalizeList = new LinkedList<Tuple<Element,Class<?>>>();
		for (Class<?> c : avail) {
			final String className = c.getSimpleName();
			if (!imports.containsKey(className)) {
				messages.offer(new Warning() {
					@Override
					public String toString() {
						return "No " + className + " found for import";
					}
				});
				continue;
			}
			Element e = imports.get(className);

			if (!e.hasChildNodes()) {
				final String emptyElementName = e.getLocalName();
				messages.offer(new Warning() {
					@Override
					public String toString() {
						return "Empty top level element " + emptyElementName;
					}
				});
				continue;
			}
			importNode(e, c);
			finalizeList.add(new Tuple<Element,Class<?>>(e, c));
		}
		for (Tuple<Element,Class<?>> t : finalizeList) {
			if (Entity.class.isAssignableFrom(t.snd)) {
				finalizeEntity(t.fst, t.snd);
			} else {
				// finalizeRelationship
			}
		}
		messages.offer("Done.");
	}

	/**
	 * @param e
	 * @param c
	 * @since Iteration4
	 */
	private void finalizeEntity(final Element e, final Class<?> c) {
		Node node = e.getFirstChild();
		// Relation r = c.getAnnotation(Relation.class);
		final String className = c.getSimpleName();
		Map<String,Method> additionalSetters = this.additionalSetters.get(className);
		String idColumn = idColumns.get(className);
		EachRow: do {
			if ((node.getNodeType() == Node.ELEMENT_NODE)
					&& ("row".equals(node.getLocalName()))) {
				Element current = (Element) node;
				try {
					NodeList idl = current.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, idColumn);
					if (idl.getLength() == 0) {
						continue EachRow;
					}
					String id = className + "#" + idl.item(0).getFirstChild().getNodeValue();
					Object realID = idMapping.get(id);
					Entity obj = (Entity) getters.get(className).invoke(manager, realID);
					int changes = 0;
					for (String column : additionalSetters.keySet()) {
						NodeList l = current.getElementsByTagNameNS(Namespaces.XMLNS_EXPORT, column);
						if (l.getLength() == 0) {
							continue;
						}
						Method setter = additionalSetters.get(column);
						String javaName = setter.getName().substring(3);
						Element columnE = (Element) l.item(0);
						String nil = columnE.getAttributeNS(Namespaces.XMLNS_XSI, "nil");
						if ((nil != null) && nil.equals("true")) {
							Method clear = obj.getClass().getMethod("clear" + javaName);
							if (clear == null) {
								messages.offer("may not be null");
								continue;
							}
							clear.invoke(obj);
						} else {
							String elementValue = l.item(0).getFirstChild().getNodeValue();
							Class<?> realType = setter.getParameterTypes()[0];
							Object value = converters.newConverter(realType).convert(elementValue);
							setter.invoke(obj, value);
						}
						changes++;
					}
					if (changes > 0) {
						obj.pushChanges();
						messages.offer(new Info() {
							@Override
							public String toString() {
								return "Finalized " + className;
							}
						});
					} else {
						messages.offer(new Info() {
							@Override
							public String toString() {
								return "No need to finalize " + className;
							}
						});
					}
				} catch (Exception exc) {
					messages.offer(exc);
				}
			}
		} while ((node = node.getNextSibling()) != null);
	}

	/**
	 * 
	 * 
	 * @since Iteration4
	 */
	private void gatherImports() {
		NodeList topNodes = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < topNodes.getLength(); i++) {
			Node node = topNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element e = (Element) node;
			if (e.getNamespaceURI() == Namespaces.XMLNS_EXPORT) {
				String name = Strings.capitalize(e.getLocalName());
				if (relations.contains(name)) {
					imports.put(name, e);
					continue;
				}
			}
			messages.offer("Ignoring element " + e.getLocalName());
			e.getParentNode().removeChild(e);
		}

	}

	/**
	 * @param e
	 * @param c
	 * @since Iteration4
	 */
	private void importNode(final Element e, final Class<?> c) {
		Node node = e.getFirstChild();
		Relation r = c.getAnnotation(Relation.class);
		final String className = c.getSimpleName();
		EachRow: do {
			if ((node.getNodeType() == Node.ELEMENT_NODE)
							&& ("row".equals(node.getLocalName()))) {
				Element current = (Element) node;
				try {
					Method createMethod = manager.getClass().getMethod("create" + className,
																		   requiredTypes.get(c));
					String idColumn = idColumns.get(c.getSimpleName());
					final Object[] args = new Object[r.requiredSqlCols().length];
					Class<?>[] types = requiredTypes.get(c);
					String[] cols = r.requiredSqlCols();
					for (int i = 0; i < cols.length; i++) {
						Element valueElement = (Element) current.getElementsByTagName(cols[i])
									.item(0);
						if (valueElement.hasChildNodes()) {
							String elementValue = valueElement.getFirstChild().getNodeValue();
							if (!Entity.class.isAssignableFrom(types[i])) {
								args[i] = converters.newConverter(types[i])
											.convert(elementValue);
							} else {
								String ident = types[i].getSimpleName() + "#" + elementValue;
								if (idMapping.containsKey(ident)) {
									Method getter = getters.get(types[i].getSimpleName());
									args[i] = getter.invoke(manager, idMapping.get(ident));
								} else {
									messages.offer("Required object ("
												+ c.getSimpleName() + "."
												+ types[i].getSimpleName() + ") not found!");
									continue EachRow;
								}
							}
						} else {
							messages.offer(className + "." + types[i].getSimpleName()
										+ " may not be null (but is), since it’s a required field");
							continue EachRow;
						}
					}
					final Object result = createMethod.invoke(manager, args);
					messages.offer(new Info() {
						@Override
						public String toString() {
							return "Created " + result.getClass() + " {"
										+ Strings.implode(", ", args) + "}";
						}
					});
					if (Entity.class.isAssignableFrom(createMethod.getReturnType())) {
						Object realID = callback(result, "id").call();
						String oldID = current.getElementsByTagName(idColumn).item(0)
									.getFirstChild()
									.getNodeValue();
						String mKey = c.getSimpleName() + "#" + oldID;
						idMapping.put(mKey, realID);
						messages.offer("Mapping: " + mKey + " => " + realID);
					}
				} catch (Exception exc) {
					messages.offer(exc);
				}
			}
		} while ((node = node.getNextSibling()) != null);
	}
}
