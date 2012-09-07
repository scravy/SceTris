/* GenericRelationManager.java / 2:05:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.bakery.orm.java;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import org.w3c.dom.Document;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.fu.bakery.orm.java.filters.Filters;
import de.fu.bakery.orm.java.filters.Sort;
import de.fu.bakery.orm.java.imex.ImportException;
import de.fu.bakery.orm.java.imex.Importer;
import de.fu.bakery.orm.java.imex.XmlImporter;
import de.fu.junction.dynamic.Callback;
import de.fu.junction.functional.F;
import de.fu.junction.functional.S;
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

	public static PreparedStatement assignFilters(final PreparedStatement stmt, final Filter filter)
			throws DatabaseException {
		return assignFilters(stmt, new Filter[] { filter });
	}

	/**
	 * 
	 * @param bool1
	 * @param bool2
	 * @return
	 * @since Iteration4
	 */
	public static int compareValues(final boolean bool1, final boolean bool2) {
		if (bool1 == bool2) {
			return 0;
		}
		if (bool1) {
			return 1;
		}
		return -1;
	}

	/**
	 * 
	 * @param id1
	 * @param id2
	 * @return
	 * @since Iteration3
	 */
	public static int compareValues(final Object id1, final Object id2) {
		if (id1 == null) {
			if (id2 == null) {
				return 0;
			}
			return -1;
		}
		if (id2 == null) {
			return 1;
		}
		if (id1.equals(id2)) {
			return 0;
		}
		return ((Integer) id1.hashCode()).compareTo(id2.hashCode());
	}

	/**
	 * 
	 * @param <T>
	 * @param id1
	 * @param id2
	 * @return
	 * @since Iteration4
	 */
	public static <T extends Comparable<? super T>> int compareValues(final T id1, final T id2) {
		if (id1 == null) {
			if (id2 == null) {
				return 0;
			}
			return -1;
		}
		if (id2 == null) {
			return 1;
		}
		if (id1.equals(id2)) {
			return 0;
		}
		return id1.compareTo(id2);
	}

	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration4
	 */
	public static String makeSortString(final Sort... filters) {
		List<String> sortParameters = new LinkedList<String>();
		for (Sort s : filters) {
			F.map(new Callback(S.class, "append"),
						   s.sortFields, sortParameters,
						   " " + s.sortOrdering.toString());
		}
		return S.implode(", ", sortParameters);
	}

	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration4
	 */
	public static String makeWhereString(final Filter filter) {
		return makeWhereString(new Filter[] { filter });
	}

	/**
	 * 
	 * @param filters
	 * @return
	 * @since Iteration4
	 */
	public static String makeWhereString(final Filter[] filters) {
		List<Filter> whereFilters = new ArrayList<Filter>(filters.length);
		List<Sort> sortFilters = new ArrayList<Sort>();
		for (Filter filter : filters) {
			if (filter == null) {
				continue;
			}
			if (filter instanceof Sort) {
				sortFilters.add((Sort) filter);
			} else {
				whereFilters.add(filter);
			}
		}
		String wherePart = whereFilters.size() > 0 ? " WHERE "
				+ translateToSQL(whereFilters.toArray(filters)) : "";
		String sortPart = sortFilters.size() > 0 ? " SORT " :
				makeSortString(sortFilters.toArray(new Sort[] {}));
		return wherePart + sortPart;
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
		de.fu.bakery.orm.java.annotation.Relation annotation =
				relation.getAnnotation(de.fu.bakery.orm.java.annotation.Relation.class);
		if (annotation == null) {
			throw new RuntimeException("Into the void.");
		}
		return export(annotation.name(), with_schema);
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
	 * @param <T>
	 * @param source
	 * @param clazz
	 * @throws DatabaseException
	 * @since Iteration4
	 */
	public <T> void importCustom(final T source, final Class<Importer<T>> clazz)
			throws ImportException, InstantiationException, IllegalAccessException {
		clazz.newInstance().doImport(source);
	}

	/**
	 * 
	 * @param <T>
	 * @param source
	 * @param importer
	 * @since Iteration4
	 */
	public <T> void importCustom(final T source, final Importer<T> importer) throws ImportException {
		importer.setManager(this);
		importer.doImport(source);
	}

	/**
	 * 
	 * @since Iteration4
	 */
	public void importXML(final Document document) throws ImportException {
		new XmlImporter(this).doImport(document);
	}

	/**
	 * 
	 * @param stream
	 * @since Iteration4
	 */
	public void importXML(final InputStream stream) {
		throw new NotImplementedException();
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
