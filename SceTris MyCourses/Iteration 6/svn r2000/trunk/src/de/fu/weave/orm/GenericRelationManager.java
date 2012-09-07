/* GenericRelationManager.java / 2:05:21 AM
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;

import java.io.InputStream;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLXML;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;

import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import de.fu.junction.Callback;
import de.fu.junction.Strings;
import de.fu.junction.annotation.meta.Author;
import de.fu.junction.functional.F;
import de.fu.weave.orm.filters.All;
import de.fu.weave.orm.filters.Any;
import de.fu.weave.orm.filters.Filters;
import de.fu.weave.orm.filters.Sort;
import de.fu.weave.orm.imex.ImportException;
import de.fu.weave.orm.imex.Importer;
import de.fu.weave.orm.imex.XmlImporter;
import de.fu.weave.xml.Namespaces;

@Author("Julian Fleischer")
abstract public class GenericRelationManager implements RelationManager {

	/**
	 * 
	 */
	public static int _hashCode(final int $id) {
		return $id;
	}

	/**
	 * 
	 */
	public static int _hashCode(final Object $obj) {
		return $obj.hashCode();
	}

	/**
	 * 
	 */
	public static java.sql.PreparedStatement assignFilters(final java.sql.PreparedStatement stmt,
														   final Filter[] filters)
			throws DatabaseException {
		List<Object> values = Filters.extractValues(filters);
		int i = 1;
		try {
			for (Object value : values) {
				if (value instanceof Integer)
					stmt.setInt(i, (Integer) value);
				else if (value instanceof Float)
					stmt.setFloat(i, (java.lang.Float) value);
				else if (value instanceof Double)
					stmt.setDouble(i, (java.lang.Double) value);
				else if (value instanceof Boolean)
					stmt.setBoolean(i, (Boolean) value);
				else if (value instanceof String)
					stmt.setString(i, (String) value);
				else if (value instanceof java.sql.Timestamp)
					stmt.setTimestamp(i, (java.sql.Timestamp) value);
				else if (value instanceof java.sql.Date)
					stmt.setDate(i, (java.sql.Date) value);
				else if (value instanceof java.sql.Time)
					stmt.setTime(i, (java.sql.Time) value);
				else if (value instanceof byte[]) stmt.setBytes(i, (byte[]) value);
				i++;
			}
		} catch (java.sql.SQLException $exc) {
			throw new DatabaseException($exc);
		}
		return stmt;
	}

	public static PreparedStatement assignFilters(final PreparedStatement stmt, final Filter filter)
			throws DatabaseException {
		return assignFilters(stmt, new Filter[] { filter });
	}

	/**
	 * 
	 * @param $bool1
	 * @param $bool2
	 * @return
	 */
	public static int compareValues(final boolean $bool1, final boolean $bool2) {
		if ($bool1 == $bool2) return 0;
		if ($bool1) return 1;
		return -1;
	}

	/**
	 * 
	 * @param $id1
	 * @param $id2
	 * @return
	 */
	public static int compareValues(final Object $id1, final Object $id2) {
		if ($id1 == null) {
			if ($id2 == null) return 0;
			return -1;
		}
		if ($id2 == null) return 1;
		if ($id1.equals($id2)) return 0;
		return ((Integer) $id1.hashCode()).compareTo($id2.hashCode());
	}

	/**
	 * 
	 * @param <T>
	 * @param $id1
	 * @param $id2
	 * @return
	 */
	public static <T extends Comparable<? super T>> int compareValues(final T $id1, final T $id2) {
		if ($id1 == null) {
			if ($id2 == null) return 0;
			return -1;
		}
		if ($id2 == null) return 1;
		if ($id1.equals($id2)) return 0;
		return $id1.compareTo($id2);
	}

	/**
	 * 
	 * @param $filters
	 * @param $target
	 */
	public static void extractSortFilters(final Filter[] $filters, final List<Sort> $target) {
		for (Filter $filter : $filters)
			if ($filter instanceof Sort)
				$target.add((Sort) $filter);
			else if ($filter instanceof Any)
				extractSortFilters(((Any) $filter).filters, $target);
			else if ($filter instanceof All) extractSortFilters(((All) $filter).filters, $target);
	}

	/**
	 * 
	 * @param $filters
	 * @return
	 */
	public static String makeSortString(final List<Sort> $filters) {
		List<String> sortParameters = new LinkedList<String>();
		for (Sort s : $filters)
			F.map(new Callback(Strings.class, "append"),
						   s.sortFields, sortParameters,
						   " " + s.sortOrdering.toString());
		return Strings.implode(", ", sortParameters);
	}

	/**
	 * 
	 * @param $filter
	 * @return
	 */
	public static String makeWhereString(final Filter $filter) {
		return makeWhereString(new Filter[] { $filter });
	}

	/**
	 * 
	 * @param $filters
	 * @return
	 */
	public static String makeWhereString(final Filter[] $filters) {
		List<Filter> whereFilters = new ArrayList<Filter>($filters.length);
		List<Sort> sortFilters = new ArrayList<Sort>();
		extractSortFilters($filters, sortFilters);
		for (Filter filter : $filters) {
			if ((filter == null) || (filter instanceof Sort)) continue;
			whereFilters.add(filter);
		}
		String wherePart = whereFilters.size() > 0 ? " WHERE "
				+ translateToSQL(whereFilters.toArray($filters)) : "";
		String sortPart = sortFilters.size() > 0 ? " ORDER BY "
				+ makeSortString(sortFilters) : "";
		return wherePart + sortPart;
	}

	/**
	 * 
	 * 
	 */
	public static String translateToSQL(final Filter[] filters) {
		StringBuilder sql = new StringBuilder();
		for (Filter filter : filters) {
			sql.append("(");
			sql.append(filter);
			sql.append(") AND ");
		}
		if (sql.length() >= 5) sql.setLength(sql.length() - 5);
		return sql.toString();
	}

	protected Logger $logger;

	protected ConnectionManager connectionManager;

	protected Map<String,ResultSet> $queryCache;

	private boolean $queryCacheEnabled = false;

	/**
	 * 
	 */
	private boolean $offlineModeEnabled = false;

	/**
	 * 
	 * 
	 */
	public GenericRelationManager() {
		connectionManager = null;
		this.enableOfflineMode();
		$queryCache = new HashMap<String,ResultSet>(2000);
		$logger = Logger.getLogger(getClass());
	}

	/**
	 * 
	 * 
	 */
	public GenericRelationManager(final ConnectionManager connectionManager) {
		this.connectionManager = connectionManager;
		$logger = Logger.getLogger(getClass());
		$queryCache = new HashMap<String,ResultSet>(2000);
	}

	/**
	 * 
	 */
	@Override
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
	 */
	@Override
	public void beginTransaction() throws DatabaseException {
		try {
			connectionManager.getConnection().setAutoCommit(false);
		} catch (java.sql.SQLException e) {
			throw new DatabaseException(e);
		}
	}

	@Override
	public GenericRelationManager clone() {
		$logger.info("RelationManager cloned");
		try {
			GenericRelationManager $clone = (GenericRelationManager) super.clone();
			$clone.connectionManager = connectionManager.clone();
			$clone.$queryCache = new HashMap<String,ResultSet>(2000);
			$clone.disableQueryCache();
			return $clone;
		} catch (Exception $exc) {
			return null;
		}
	}

	/**
	 * 
	 */
	@Override
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
	 */
	@Override
	final public ConnectionManager connectionManager() {
		return connectionManager;
	}

	/**
	 * 
	 */
	public void disableOfflineMode() throws DatabaseException {
		enableOfflineMode(false);
	}

	/**
	 * 
	 */
	public void disableQueryCache() {
		$logger.info("Query cache disabled!");
		flushQueryCache();
		$queryCacheEnabled = false;
	}

	/**
	 * 
	 */
	public void enableOfflineMode() {
		try {
			enableOfflineMode(true);
		} catch (DatabaseException $exc) {
			$logger.error($exc);
		}
	}

	/**
	 * 
	 */
	public void enableOfflineMode(final boolean $mode) throws DatabaseException {
		if (!$mode && (connectionManager == null)) throw new DatabaseException(
				"Can not disable offline mode since no ConnectionManager is available");
		$offlineModeEnabled = $mode;
	}

	/**
	 * Enables the query cache.
	 */
	public void enableQueryCache() {
		$logger.info("Query cache enabled!");
		$queryCacheEnabled = true;
	}

	/**
	 * Enables the query cache.
	 * 
	 * @param $mode
	 */
	public void enableQueryCache(final boolean $mode) {

	}

	/**
	 * 
	 * @param $stmt
	 * @return
	 * @throws SQLException
	 */
	protected ResultSet executeQuery(final PreparedStatement $stmt) throws SQLException {
		if (!$queryCacheEnabled) return $stmt.executeQuery();
		String $query = $stmt.toString();
		ResultSet $result;
		if ($queryCache.containsKey($query)) {
			$result = $queryCache.get($query);
			$result.beforeFirst();
			return $result;
		}
		Statement $staticStatement = connectionManager().getConnection()
				.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		$result = $staticStatement.executeQuery($query);
		$queryCache.put($query, $result);
		return $result;
	}

	/**
	 * 
	 * @param with_schema
	 * @return
	 * @throws DatabaseException
	 */
	public SQLXML export(final boolean with_schema)
			throws DatabaseException {
		try {
			String query;
			if (with_schema)
				query = "SELECT schema_to_xml_and_xmlschema(?, ?, ?, ?);";
			else
				query = "SELECT schema_to_xml(?, ?, ?, ?);";
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
	 */
	public SQLXML export(final Class<? extends Relation> relation, final boolean with_schema)
			throws DatabaseException {
		de.fu.weave.orm.annotation.Relation annotation =
				relation.getAnnotation(de.fu.weave.orm.annotation.Relation.class);
		if (annotation == null) throw new RuntimeException("Into the void.");
		return export(annotation.name(), with_schema);
	}

	@SuppressWarnings("unchecked")
	public <T> T export(final Class<? extends Relation> relation, final Class<T> resultType,
						final Filter... filters)
			throws DatabaseException {
		if (resultType.equals(SQLXML.class)) return (T) export(relation, false);
		return null;
	}

	/**
	 * 
	 * @param tableName
	 * @return
	 */
	protected SQLXML export(final String tableName, final boolean with_schema)
			throws DatabaseException {
		try {
			String query;
			if (with_schema)
				query = "SELECT table_to_xml_and_xmlschema(?, ?, ?, ?);";
			else
				query = "SELECT table_to_xml(?, ?, ?, ?);";
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
	 */
	public void flushQueryCache() {
		for (ResultSet $result : $queryCache.values())
			try {
				$result.close();
			} catch (Exception $e) {
			}
		$queryCache.clear();
	}

	/**
	 * 
	 * @param <T>
	 * @param $source
	 * @param $class
	 * @throws DatabaseException
	 */
	public <T> void importCustom(final T $source, final Class<Importer<T>> $class)
			throws ImportException, InstantiationException, IllegalAccessException {
		$class.newInstance().doImport($source);
	}

	/**
	 * 
	 * @param <T>
	 * @param $source
	 * @param $importer
	 */
	public <T> void importCustom(final T $source, final Importer<T> $importer) throws ImportException {
		$importer.setManager(this);
		$importer.doImport($source);
	}

	/**
	 * 
	 */
	public void importXML(final Document $document) throws ImportException {
		new XmlImporter(this).doImport($document);
	}

	/**
	 * 
	 * @param $stream
	 */
	public void importXML(final InputStream $stream) {
		throw new NotImplementedException();
	}

	/**
	 * 
	 * @param $x
	 * @return
	 */
	public boolean isNull(final boolean $x) {
		return false;
	}

	/**
	 * 
	 * @param $x
	 * @return
	 */
	public boolean isNull(final int $x) {
		return false;
	}

	/**
	 * 
	 * @param $object
	 */
	public boolean isNull(final Object $object) {
		return $object == null;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isOfflineModeEnabled() {
		return $offlineModeEnabled;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isQueryCacheEnabled() {
		return $queryCacheEnabled;
	}

}
