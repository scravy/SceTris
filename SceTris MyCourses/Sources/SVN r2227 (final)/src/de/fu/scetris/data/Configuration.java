/* Configuration.java / 2011-03-03+01:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

/**
 * @formatter:off
 */
package de.fu.scetris.data;

/**
 * OO-Representation of the entity-relation Configuration
 * <p>
 * 
 */
@de.fu.weave.orm.annotation.Entity(name = "Configuration")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"Configuration\"", requiredSqlCols = {"key"})
@de.fu.weave.xml.annotation.XmlElement("Configuration")
public class Configuration extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Timekey = "timekey";
	public static final String Key = "key";
	public static final String Value = "value";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 943752L;
		
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(2)
		@de.fu.weave.Form.Field("key") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("key$validator")
		@de.fu.weave.Form.ActiveConverter("key$converter")
		public String key;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("value")
		@de.fu.weave.Form.ActiveValidator("value$validator")
		@de.fu.weave.Form.ActiveConverter("value$converter")
		public String value;
		
		
		public Form setValues(Configuration $object) {
			
			timekey = $object.getTimekey();
			key = $object.getKey();
			value = $object.getValue();
			return this;
		}
	}
	
	/**
	 * 
	 * <p>
	 * 
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * 
	 * <p>
	 * 
	 */
	String _key;
	boolean changed_key = false;
	boolean fetched_key = false;/**
	 * 
	 * <p>
	 * 
	 */
	String _value = null;
	boolean changed_value = false;
	boolean fetched_value = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Configuration(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_key = result.getString("key");
			fetched_key = true;
			_value = result.getString("value");
			if (result.wasNull()) {
				_value = null;
			}
			fetched_value = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Configuration and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _key 
	 */
	Configuration(RelationManager manager, String _key) {
		timekey(true);
		this.manager = manager;
		this._key = _key;
		this.fetched_key = true;
	}
	
	/**
	 * Constructs the entity Configuration and initializes all attributes.
	 * @param _key 
	 * @param _value 
	 */
	Configuration(RelationManager manager, final boolean full, String _key, String _value) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._key = _key;
			this.fetched_key = true;
			this._value = _value;
			this.fetched_value = true;
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
	
	/*@Override
	public int hashCode() {
		return de.fu.weave.orm.GenericRelationManager._hashCode(this._key);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newConfiguration} rather than {@link RelationManager#createConfiguration).
	 */
	@Override
	public Configuration create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Configuration\"" + " (\"key\", \"value\", \"timekey\")"
			+ " VALUES (?, ?, ?);";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _key);
			
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			stmt.setTimestamp(i++, _timekey);
			stmt.execute();
			
			changed_key = false;
			changed_value = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"Configuration\"" + " SET \"key\" = ?"
			+ ", \"value\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND key = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _key);
			
			if (_value != null) {
				stmt.setString(i++, _value);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setString(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_key = false;
			changed_value = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 */
	public void pullChanges() throws de.fu.weave.orm.DatabaseException {
		pullChanges(0);
	}
	
	/**
	 *
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Configuration\"" + " WHERE \"key\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, _key);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = false;
	}

	@Override
	public boolean exists() {
		return exists;
	}
	
	public int compareTo(Configuration entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() <= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return de.fu.weave.orm.GenericRelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof Configuration) {
			return compareTo((Configuration) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Configuration entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Configuration) {
			return equals((Configuration) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	@de.fu.weave.orm.annotation.Attribute(name = "timekey", serial = true, hidden = true, use = "timestamp")
	public java.sql.Timestamp getTimekey() {
		return _timekey;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTimekeyChanged() {
		return changed_timekey;
	}
	
	public java.sql.Timestamp timekey() {
		return _timekey;
	}
	
	public boolean isValidTimekey(java.sql.Timestamp key) {
		return de.fu.weave.orm.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("key")
	@de.fu.weave.orm.annotation.Attribute(name = "key", serial = true, primary = true, use = "id")
	public String getKey() {
		return _key;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("value")
	@de.fu.weave.orm.annotation.Attribute(name = "value", serial = true, nullable = true)
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
	public boolean isValueChanged() {
		return changed_value;
	}
	

	@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	

	
	/**
	 * Sets the value of <code>value</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isValueChanged()} will return true. If you specify
	 * the same value as the old value isValueChanged() will return the same as it did
	 * before calling <code>setValue(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Configuration setValue(String value) {
		
		if (value == null) {
			clearValue();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_value, value) == 0) return this;
		changed_value = true;
		_value = value;
		return this;
	}
	
	/**
	 * value may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Configuration clearValue() {
		if (_value == null) {
			return this;
		}
		_value = null;
		changed_value = true;
		return this;
	}

}
