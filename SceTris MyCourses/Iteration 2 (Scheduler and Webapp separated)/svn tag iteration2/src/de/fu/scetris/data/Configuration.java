/* Configuration.java / 2010-10-30+02:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

package de.fu.scetris.data;

/**
 * OO-Representation of the entity-relation Configuration
 * <p>
 * 
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Configuration")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Configuration\"")
@de.fu.weave.xml.annotation.XmlElement("Configuration")
public class Configuration extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	String _key;
	boolean changed_key = false;
	boolean fetched_key = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	String _value = null;
	boolean changed_value = false;
	boolean fetched_value = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Configuration(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_key = result.getString("key");
			fetched_key = true;
			_value = result.getString("value");
			fetched_value = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Configuration and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _key 
	 * @since Iteration2
	 */
	Configuration(RelationManager manager, String _key) {
		this.manager = manager;
		this._key = _key;
		this.fetched_key = true;
	}
	
	/**
	 * Constructs the entity Configuration and initializes all attributes.
	 * @param _key 
	 * @param _value 
	 * @since Iteration2
	 */
	Configuration(RelationManager manager, final boolean full, String _key, String _value) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._key = _key;
			this.fetched_key = true;
		}
	}
	
	/**
	 * Returns the unique identifier for this entity.
	 * @since Iteration2
	 */
	public String id() {
		return _key;
	}
	
	@Override
	public String toString() {
		return "Configuration: \"" + this._key + "\" (#" + this._key + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._key);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newConfiguration} rather than {@link RelationManager#createConfiguration).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Configuration\"" + " (\"key\" , \"value\" )"
			+ " VALUES (?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _key);
			
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			stmt.execute();
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Updates the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"Configuration\"" + " SET \"key\" = ?, \"value\" = ? WHERE key = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _key);
			
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			stmt.setString(i, this.id());
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Configuration\"" + " WHERE \"key\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, _key);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Configuration entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Configuration) {
			return compareTo((Configuration) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Configuration entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Configuration) {
			return equals((Configuration) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	public java.sql.Timestamp getTimekey() {
		return _timekey;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedTimekey() {
		return changed_timekey;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("key")
	public String getKey() {
		return _key;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedKey() {
		return changed_key;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("value")
	public String getValue() {
		if (_value == null) {
			return null;
		}
		return _value;
	}
	
	public boolean hasValue() {
		return _value != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedValue() {
		return changed_value;
	}

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setKey(String value) {
		
		changed_key = true;
		_key = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setValue(String value) {
		if (value == null) {
			_value = null;
			return;
		}
		changed_value = true;
		_value = value;
	}
	
	/**
	 * value may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearValue() {
		_value = null;
		changed_value = true;
	}

}
