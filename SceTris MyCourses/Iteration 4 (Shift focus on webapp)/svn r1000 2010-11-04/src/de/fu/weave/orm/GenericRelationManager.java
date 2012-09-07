/* GenericRelationManager.java / 2:05:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.io.InputStream;
import java.lang.reflect.Method;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Iterator;
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

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.fu.scetris.data.tests.TestStringGenerator;
import de.fu.weave.orm.filters.Filters;
import de.fu.weave.util.ClassesComparator;
import de.fu.weave.xml.Namespaces;

/**
 * 
 * @author Julian Fleischer
 * @since Iteration3
 */
abstract public class GenericRelationManager implements RelationManager {

	/**
	 * @since Iteration3
	 */
	public static int _hashCode(final int id) {
		return id;
	}

	/**
	 * @since Iteration3
	 */
	public static int _hashCode(final Object obj) {
		return obj.hashCode();
	}

	/**
	 * @since Iteration3
	 */
	public static java.sql.PreparedStatement assignFilters(final java.sql.PreparedStatement stmt,
														   final Filter[] filters)
			throws DatabaseException {
		List<Object> values = Filters.extractValues(filters);
		int i = 1;
		try {
			for (Object value : values) {
				if (value instanceof Integer) {
					stmt.setInt(i, (Integer) value);
				} else if (value instanceof Float) {
					stmt.setFloat(i, (java.lang.Float) value);
				} else if (value instanceof Double) {
					stmt.setDouble(i, (java.lang.Double) value);
				} else if (value instanceof Boolean) {
					stmt.setBoolean(i, (Boolean) value);
				} else if (value instanceof String) {
					stmt.setString(i, (String) value);
				} else if (value instanceof java.sql.Timestamp) {
					stmt.setTimestamp(i, (java.sql.Timestamp) value);
				} else if (value instanceof java.sql.Date) {
					stmt.setDate(i, (java.sql.Date) value);
				} else if (value instanceof java.sql.Time) {
					stmt.setTime(i, (java.sql.Time) value);
				} else if (value instanceof byte[]) {
					stmt.setBytes(i, (byte[]) value);
				}
				i++;
			}
		} catch (java.sql.SQLException e) {
			throw new DatabaseException(e);
		}
		return stmt;
	}

	/**
	 * <!-- compareIds(int, int) -->
	 * 
	 * @since Iteration3
	 */
	public static int compareIds(final int id1, final int id2) {
		if (id1 < id2) {
			return -1;
		}
		return 1;
	}

	/**
	 * <!-- compareIds(String, String) -->
	 * 
	 * @since Iteration3
	 */
	public static int compareIds(final String id1, final String id2) {
		return id1.compareTo(id2);
	}

	/**
	 * <!-- translateToSQL -->
	 * 
	 * @since Iteration2
	 */
	public static String translateToSQL(final Filter[] filters) {
		StringBuilder sql = new StringBuilder();
		for (Filter filter : filters) {
			sql.append("(");
			sql.append(filter);
			sql.append(") AND ");
		}
		if (sql.length() >= 5) {
			sql.setLength(sql.length() - 5);
		}
		return sql.toString();
	}

	final public ConnectionManager connectionManager;

	/**
	 * The cache holds references to all objects created or retrieved
	 * 
	 * @since Iteration3
	 */
	final OfflineCache offlineCache = new OfflineCache();

	/**
	 * The query cache maps Queries to DTOs NOTE: ((org.postgresql.jdbc2.PreparedStatement)
	 * stmt).toString() will give the query
	 * 
	 * @since Iteration3
	 */
	final Map<String,Result<? extends Relation>> queryCache =
			new TreeMap<String,Result<? extends Relation>>();

	/**
	 * 
	 * @since Iteration3
	 */
	private boolean queryCacheEnabled = false;

	/**
	 * 
	 * @since Iteration3
	 */
	private boolean offlineModeEnabled = false;

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public GenericRelationManager() {
		connectionManager = null;
		this.enableOfflineMode();
	}

	/**
	 * 
	 * 
	 * @since Iteration3
	 */
	public GenericRelationManager(final ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void abortTransaction() throws DatabaseException {
		try {
			connectionManager.getConnection().rollback();
			connectionManager.getConnection().setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void beginTransaction() throws DatabaseException {
		try {
			connectionManager.getConnection().setAutoCommit(false);
		} catch (java.sql.SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void commitTransaction() throws DatabaseException {
		try {
			connectionManager.getConnection().commit();
			connectionManager.getConnection().setAutoCommit(true);
		} catch (java.sql.SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	@Override
	final public ConnectionManager connectionManager() {
		return connectionManager;
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void disableOfflineMode() throws DatabaseException {
		enableOfflineMode(false);
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void disableQueryCache() {
		enableQueryCache(false);
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void enableOfflineMode() {
		try {
			enableOfflineMode(true);
		} catch (DatabaseException e) {
			// the all-famous noop
		}
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void enableOfflineMode(final boolean mode) throws DatabaseException {
		if (!mode && (connectionManager == null)) {
			throw new DatabaseException(
					"Can not disable offline mode since no ConnectionManager is available");
		}
		offlineModeEnabled = mode;
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void enableQueryCache() {
		enableQueryCache(true);
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void enableQueryCache(final boolean mode) {
		queryCacheEnabled = mode;
	}

	public SQLXML export(final boolean with_schema)
			throws DatabaseException {
		try {
			String query;
			if (with_schema) {
				query = "SELECT schema_to_xml_and_xmlschema(?, ?, ?, ?);";
			} else {
				query = "SELECT schema_to_xml(?, ?, ?, ?);";
			}
			PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, "scetris");
			stmt.setBoolean(2, true);
			stmt.setBoolean(3, false);
			stmt.setString(4, "http://technodrom.scravy.de/2010/pgsqlxml");
			ResultSet result = stmt.executeQuery();
			result.next();
			return result.getSQLXML(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public SQLXML export(final Class<? extends Relation> relation, final boolean with_schema)
			throws DatabaseException {
		de.fu.weave.orm.annotation.Relation annotation =
				relation.getAnnotation(de.fu.weave.orm.annotation.Relation.class);
		if (annotation == null) {
			throw new RuntimeException("Into the void.");
		}
		return export(annotation.value(), with_schema);
	}

	@SuppressWarnings("unchecked")
	public <T> T export(final Class<? extends Relation> relation, final Class<T> resultType,
						final Filter... filters)
			throws DatabaseException {
		if (resultType.equals(SQLXML.class)) {
			return (T) export(relation, false);
		}
		return null;
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 * @since Iteration3
	 */
	protected SQLXML export(final String tableName, final boolean with_schema)
			throws DatabaseException {
		try {
			String query;
			if (with_schema) {
				query = "SELECT table_to_xml_and_xmlschema(?, ?, ?, ?);";
			} else {
				query = "SELECT table_to_xml(?, ?, ?, ?);";
			}
			PreparedStatement stmt = connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, tableName);
			stmt.setBoolean(2, true);
			stmt.setBoolean(3, false);
			stmt.setString(4, Namespaces.XMLNS_EXPORT);
			ResultSet result = stmt.executeQuery();
			result.next();
			return result.getSQLXML(1);
		} catch (SQLException e) {
			throw new DatabaseException(e);
		}
	}

	/**
	 * 
	 * @since Iteration3
	 */
	public void flushQueryCache() {
		queryCache.clear();
	}

	/**
	 * 
	 * @since Iteration4
	 */
	public void importXML(final Document document) throws DatabaseException {
		Map<Class<?>,Object> resources = new TreeMap<Class<?>,Object>();
		List<Class<?>> avail = initDependencies(resources);
		Set<String> relations = new TreeSet<String>();
		for (Class<?> c : avail) {
			relations.add(c.getSimpleName());
		}
		List<Element> imports = new LinkedList<Element>();

		Map<Class<Relation>,Map<Object,Object>> idMapping =
				new TreeMap<Class<Relation>,Map<Object,Object>>(new ClassesComparator());

		NodeList topNodes = document.getDocumentElement().getChildNodes();
		for (int i = 0; i < topNodes.getLength(); i++) {
			Node node = topNodes.item(i);
			if (node.getNodeType() != Node.ELEMENT_NODE) {
				continue;
			}
			Element e = (Element) node;
			if (e.getNamespaceURI() == Namespaces.XMLNS_EXPORT) {
				if (relations.contains(e.getLocalName())) {
					imports.add(e);
					continue;
				}
			}
			e.getParentNode().removeChild(e);
		}

	}

	/**
	 * 
	 * @param stream
	 * @since Iteration4
	 */
	public void importXML(final InputStream stream) {
		throw new NotImplementedException();
	}

	protected List<Class<?>> initDependencies(final Map<Class<?>,Object> resources) {

		List<Class<?>> avail = new ArrayList<Class<?>>();

		ClassesComparator cc = new ClassesComparator();
		Map<Class<?>,Method> relationCreators = new TreeMap<Class<?>,Method>(cc);

		Method[] methods = getClass().getMethods();
		for (Method m : methods) {
			if (m.getName().startsWith("new")) {
				relationCreators.put(m.getReturnType(), m);
			}
		}
		Set<Class<?>> needs = new TreeSet<Class<?>>(cc);
		needs.addAll(relationCreators.keySet());

		avail.add(boolean.class);
		avail.add(int.class);
		avail.add(long.class);
		avail.add(float.class);
		avail.add(double.class);
		avail.add(Boolean.class);
		avail.add(Integer.class);
		avail.add(Long.class);
		avail.add(Float.class);
		avail.add(Double.class);
		avail.add(String.class);
		avail.add(Date.class);
		avail.add(Time.class);

		resources.put(boolean.class, true);
		resources.put(Boolean.class, true);
		resources.put(int.class, 74123);
		resources.put(Integer.class, 74123);
		resources.put(long.class, 9872334);
		resources.put(Long.class, 9872334);
		resources.put(float.class, Math.E);
		resources.put(Float.class, Math.E);
		resources.put(double.class, Math.PI);
		resources.put(Double.class, Math.PI);
		resources.put(Time.class, new Time(System.currentTimeMillis()));
		resources.put(Date.class, new Date(System.currentTimeMillis()));
		resources.put(String.class, new TestStringGenerator());

		for (Class<?> c : avail) {
			if (!resources.containsKey(c)) {
				try {
					resources.put(c, c.newInstance());
				} catch (IllegalAccessException e) {
					e.printStackTrace(System.err);
				} catch (InstantiationException e) {
					e.printStackTrace(System.err);
				}
			}
		}

		while (!needs.isEmpty()) {
			Set<Class<?>> currentNeeds = new TreeSet<Class<?>>(cc);
			currentNeeds.addAll(needs);
			outer: for (Class<?> relation : currentNeeds) {
				Class<?>[] params = relationCreators.get(relation).getParameterTypes();
				for (Class<?> param : params) {
					if (!avail.contains(param)) {
						continue outer;
					}
				}
				avail.add(relation);
				needs.remove(relation);
			}
		}

		Class<?> clazz = null;
		List<Class<?>> list = new ArrayList<Class<?>>();
		for (Iterator<Class<?>> it = avail.iterator(); it.hasNext(); clazz = it.next()) {
			if (!relationCreators.containsKey(clazz)) {
				list.add(clazz);
			}
		}
		for (Class<?> claZZ : list) {
			avail.remove(claZZ);
		}

		return avail;
	}

	/**
	 * 
	 * @param x
	 * @return
	 * @since Iteration3
	 */
	public boolean isNull(final boolean x) {
		return false;
	}

	/**
	 * 
	 * @param x
	 * @return
	 * @since Iteration3
	 */
	public boolean isNull(final int x) {
		return false;
	}

	/**
	 * 
	 * @param object
	 * @since Iteration3
	 */
	public boolean isNull(final Object object) {
		return object == null;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public boolean isOfflineModeEnabled() {
		return offlineModeEnabled;
	}

	/**
	 * 
	 * @return
	 * @since Iteration3
	 */
	public boolean isQueryCacheEnabled() {
		return queryCacheEnabled;
	}

}
