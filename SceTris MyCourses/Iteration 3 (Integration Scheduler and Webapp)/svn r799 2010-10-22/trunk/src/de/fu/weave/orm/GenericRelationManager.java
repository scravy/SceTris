/* GenericRelationManager.java / 2:05:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import de.fu.weave.orm.filters.Filters;

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

	/**
	 * 
	 * @since Iteration3
	 */
	public void flushQueryCache() {
		queryCache.clear();
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
