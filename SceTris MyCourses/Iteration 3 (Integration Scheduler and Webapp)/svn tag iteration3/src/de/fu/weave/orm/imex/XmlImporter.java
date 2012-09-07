/* XmlImporter.java / 8:52:24 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm.imex;

import java.lang.reflect.Method;
import java.sql.Connection;
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

import de.fu.weave.orm.ImportException;
import de.fu.weave.orm.RelationManager;
import de.fu.weave.util.ClassesComparator;
import de.fu.weave.util.Strings;
import de.fu.weave.xml.Namespaces;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration4
 */
public class XmlImporter extends BaseImporter<Document> {

	public XmlImporter() {

	}

	/**
	 * @param genericRelationManager
	 * @since Iteration4
	 */
	public XmlImporter(final RelationManager genericRelationManager) {
		setManager(genericRelationManager);
	}

	@Override
	public void doImport(final Document document) throws ImportException {

		Connection conn = manager.connectionManager().getConnection();
		if (conn == null) {
			throw new ImportException("No Database connected to import to");
		}

		Map<Class<?>,Object> resources = new TreeMap<Class<?>,Object>(new ClassesComparator());
		List<Class<?>> avail = initDependencies(resources);
		Set<String> relations = new TreeSet<String>();
		for (Class<?> c : avail) {
			relations.add(c.getSimpleName());
		}
		// lists the elements we *do* import
		List<Element> imports = new LinkedList<Element>();

		// maps the already found ids (per relation) to their real ids
		Map<String,Object> idMapping = new TreeMap<String,Object>();

		// determine what we are going to import
		/*
		 * TODO: The XML-Structure should be analyzed for consistency at this point
		 */
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
					imports.add(e);
					continue;
				}
			}
			System.out.println("Ignoring element " + e.getLocalName());
			e.getParentNode().removeChild(e);
		}

		// prepare methods for creating objects
		/*
		 * TODO: I guess it will be better to not rely on the native RelationManager-Methods, but to
		 * prepare a statement once which could then be used again and again
		 */
		Method[] managerMethods = manager.getClass().getMethods();
		Map<String,Method> methodsByName = new TreeMap<String,Method>();
		for (Method m : managerMethods) {
			methodsByName.put(m.getName(), m);
		}
		Map<Class<?>,Class<?>[]> requiredTypes =
				new TreeMap<Class<?>,Class<?>[]>(new ClassesComparator());
		Map<Class<?>,Class<?>[]> settersBySqlName =
				new TreeMap<Class<?>,Class<?>[]>(new ClassesComparator());
		for (Class<?> c : avail) {
			System.out.println("--" + c.getSimpleName());
			requiredTypes.put(c, methodsByName.get("new" + c.getSimpleName()).getParameterTypes());

		}

	}
}
