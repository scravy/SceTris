/*
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 */

package de.fu.weave.orm;


import java.io.InputStream;
import java.sql.SQLXML;
import java.util.Collection;

import org.w3c.dom.Document;

import de.fu.junction.annotation.meta.Author;
import de.fu.weave.User;
import de.fu.weave.orm.imex.ImportException;
import de.fu.weave.orm.imex.Importer;

/**
 * 
 */
@Author("Julian Fleischer")
public interface RelationManager extends Cloneable {

    /**
     * 
     * @throws DatabaseException
     */
    void abortTransaction() throws DatabaseException;

    /**
     * 
     * @throws DatabaseException
     */
    void beginTransaction() throws DatabaseException;

    /**
     * 
     * @return
     */
    RelationManager clone();

    /**
	 * 
	 */
    void commitTransaction() throws DatabaseException;

    /**
     * 
     * @return
     */
    ConnectionManager connectionManager();

    /**
	 * 
	 */
    void disableOfflineMode() throws DatabaseException;

    /**
	 * 
	 */
    void disableQueryCache();

    /**
	 * 
	 */
    boolean dropSchema() throws DatabaseException;

    /**
	 * 
	 */
    void enableOfflineMode();

    /**
	 * 
	 */
    void enableOfflineMode(final boolean $mode) throws DatabaseException;

    /**
     * Enables the query cache.
     */
    void enableQueryCache();

    /**
     * Enables the query cache.
     * 
     * @param $mode
     */
    void enableQueryCache(final boolean $mode);

    /**
     * 
     * @param with_schema
     * @return
     * @throws DatabaseException
     */
    SQLXML export(final boolean with_schema)
            throws DatabaseException;

    /**
     * 
     * @return
     */
    SQLXML export(final Class<? extends Relation> relation, final boolean with_schema)
            throws DatabaseException;

    @SuppressWarnings("unchecked")
    <T> T export(final Class<? extends Relation> relation, final Class<T> resultType,
                final Filter... filters)
        throws DatabaseException;

    /**
     * 
     * @param id
     * @return
     * @throws DatabaseException
     */
    public User fetchUser(int id) throws DatabaseException;

    /**
     * Returns a list of users.
     * <p>
     * {@see User}
     * 
     * @return
     */
    public Collection<? extends User> fetchUsers(Filter filter) throws DatabaseException;

    /**
	 * 
	 */
    void flushQueryCache();

    /**
     * 
     * @param <T>
     * @param $source
     * @param $class
     * @throws DatabaseException
     */
    <T> void importCustom(final T $source, final Class<Importer<T>> $class)
            throws ImportException, InstantiationException, IllegalAccessException;

    /**
     * 
     * @param <T>
     * @param $source
     * @param $importer
     */
    <T> void importCustom(final T $source, final Importer<T> $importer) throws ImportException;

    /**
	 * 
	 */
    void importXML(final Document $document) throws ImportException;

    /**
     * 
     * @param $stream
     */
    void importXML(final InputStream $stream);

    /**
     * 
     * @return
     */
    boolean install() throws DatabaseException;

    /**
     * 
     * @param $x
     * @return
     */
    boolean isNull(final boolean $x);

    /**
     * 
     * @param $x
     * @return
     */
    boolean isNull(final int $x);

    /**
     * 
     * @param $object
     */
    boolean isNull(final Object $object);

    /**
     * 
     * @return
     */
    boolean isOfflineModeEnabled();

    /**
     * 
     * @return
     */
    boolean isQueryCacheEnabled();

}
