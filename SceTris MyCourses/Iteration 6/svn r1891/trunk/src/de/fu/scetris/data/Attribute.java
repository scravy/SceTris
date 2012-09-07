/* Attribute.java / 2011-01-15+01:00
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
 * OO-Representation of the entity-relation Attribute
 * <p>
 * 
			The Attribute represents user-created Attributes for Persons which can be used to construct constraints.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "Attribute")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Attribute\"", requiredSqlCols = {"name"})
@de.fu.weave.xml.annotation.XmlElement("Attribute")
public class Attribute extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Timekey = "timekey";
	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Type = "type";
	public static final String UniqueValue = "unique_value";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 471872L;
		
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(2)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("name") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("name$validator")
		@de.fu.weave.Form.ActiveConverter("name$converter")
		public String name;
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("type") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("type$validator")
		@de.fu.weave.Form.ActiveConverter("type$converter")
		public String type;
		
		@de.fu.weave.Form.Pos(5)
		@de.fu.weave.Form.Field("uniqueValue") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("uniqueValue$validator")
		@de.fu.weave.Form.ActiveConverter("uniqueValue$converter")
		public boolean uniqueValue;
		
		
		public Form setValues(Attribute $object) {
			
			timekey = $object.getTimekey();
			id = $object.getId();
			name = $object.getName();
			type = $object.getType();
			uniqueValue = $object.getUniqueValue();
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
	 * numeric identifier
	 * <p>
	 * 
				An artificial key introduced to allow easy referencing of an entity.
				Once an id has been set it won’t change, therfore avoiding update anomalies.
			
	 */
	int _id;
	boolean changed_id = false;
	boolean fetched_id = false;/**
	 * unique name
	 * <p>
	 * 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 */
	String _name;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * type of the attribute
	 * <p>
	 * 
	 */
	String _type;
	boolean changed_type = false;
	boolean fetched_type = false;/**
	 * is unique
	 * <p>
	 * 
	 */
	boolean _uniqueValue;
	boolean changed_uniqueValue = false;
	boolean fetched_uniqueValue = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Attribute(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_type = result.getString("type");
			fetched_type = true;
			_uniqueValue = result.getBoolean("unique_value");
			fetched_uniqueValue = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Attribute and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Attribute(RelationManager manager, String _name) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity Attribute and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _type 
	 * @param _uniqueValue 
	 * @since Iteration2
	 */
	Attribute(RelationManager manager, final boolean full, String _name, String _type, boolean _uniqueValue) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._type = _type;
			this.fetched_type = true;
			this._uniqueValue = _uniqueValue;
			this.fetched_uniqueValue = true;
		}
	}
	
	/**
	 * Returns the unique identifier for this entity.
	 * @since Iteration2
	 */
	public int id() {
		return _id;
	}
	
	@Override
	public String toString() {
		return "Attribute: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newAttribute} rather than {@link RelationManager#createAttribute).
	 * @since Iteration2
	 */
	@Override
	public Attribute create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Attribute\"" + " (\"name\", \"type\", \"unique_value\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (manager.isNull(_type)) {
				stmt.setString(i++, _type = "string");
			} else {
				stmt.setString(i++, _type);
			}
			
			if (manager.isNull(_uniqueValue)) {
				stmt.setBoolean(i++, _uniqueValue = false);
			} else {
				stmt.setBoolean(i++, _uniqueValue);
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_type = false;
			changed_uniqueValue = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"Attribute\"" + " SET \"name\" = ?"
			+ ", \"type\" = ?"
			+ ", \"unique_value\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (manager.isNull(_type)) {
				stmt.setString(i++, "string");
			} else {
				stmt.setString(i++, _type);
			}
			
			if (manager.isNull(_uniqueValue)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _uniqueValue);
			}
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_name = false;
			changed_type = false;
			changed_uniqueValue = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 */
	public void pullChanges() throws de.fu.bakery.orm.java.DatabaseException {
		pullChanges(0);
	}
	
	/**
	 * @since Iteration4
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.bakery.orm.java.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Attribute\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = false;
	}

	@Override
	public boolean exists() {
		return exists;
	}
	
	public int compareTo(Attribute entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() <= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.bakery.orm.java.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof Attribute) {
			return compareTo((Attribute) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Attribute entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Attribute) {
			return equals((Attribute) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasAttribute.
	 * <p>
	 * This is the object of PersonHasAttribute.
	 * @return The subject regarding PersonHasAttribute where this (the java-object you call subjectsOfPersonHasAttribute on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonHasAttribute();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonHasAttribute. If you know that there will be only
	 * 1:1 relationships of the type PersonHasAttribute, {@link #subjectOfPersonHasAttribute} is what you are looking for.
	 * <p>
	 * This is the object of PersonHasAttribute.
	 * @return A Collection of subjects regarding PersonHasAttribute where this (the java-object you call subjectsOfPersonHasAttribute on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Person> subjectsOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE \"id\" IN (SELECT \"user\" FROM "
			+ "\"scetris\".\"personHasAttribute\"" + " WHERE \"attribute\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<PersonHasAttribute> whereObjectOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasAttribute\""
			+ " WHERE \"attribute\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonHasAttribute> ret = new java.util.ArrayList<PersonHasAttribute>();
			while (result.next()) {
				ret.add(new PersonHasAttribute(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via RoleImpliesAttribute.
	 * <p>
	 * This is the object of RoleImpliesAttribute.
	 * @return The subject regarding RoleImpliesAttribute where this (the java-object you call subjectsOfRoleImpliesAttribute on) is the object.
	 * @since Iteration3
	 */
	public Role subjectOfRoleImpliesAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Role> coll = subjectsOfRoleImpliesAttribute();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via RoleImpliesAttribute. If you know that there will be only
	 * 1:1 relationships of the type RoleImpliesAttribute, {@link #subjectOfRoleImpliesAttribute} is what you are looking for.
	 * <p>
	 * This is the object of RoleImpliesAttribute.
	 * @return A Collection of subjects regarding RoleImpliesAttribute where this (the java-object you call subjectsOfRoleImpliesAttribute on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Role> subjectsOfRoleImpliesAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Role\""
			+ " WHERE \"id\" IN (SELECT \"role\" FROM "
			+ "\"scetris\".\"roleImpliesAttribute\"" + " WHERE \"attribute\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Role> ret = new java.util.ArrayList<Role>();
			while (result.next()) {
				ret.add(new Role(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<RoleImpliesAttribute> whereObjectOfRoleImpliesAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roleImpliesAttribute\""
			+ " WHERE \"attribute\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<RoleImpliesAttribute> ret = new java.util.ArrayList<RoleImpliesAttribute>();
			while (result.next()) {
				ret.add(new RoleImpliesAttribute(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "timekey", serial = true, hidden = true, use = "timestamp")
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
		return de.fu.bakery.orm.java.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.bakery.orm.java.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.bakery.orm.java.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "name", serial = true, use = "title")
	public String getName() {
		return _name;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNameChanged() {
		return changed_name;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("type")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "type", serial = true)
	public String getType() {
		return _type;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTypeChanged() {
		return changed_type;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("uniqueValue")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "unique_value", serial = true)
	public boolean getUniqueValue() {
		return _uniqueValue;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isUniqueValueChanged() {
		return changed_uniqueValue;
	}
	

	@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	

	
	/**
	 * Sets the value of <code>name</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNameChanged()} will return true. If you specify
	 * the same value as the old value isNameChanged() will return the same as it did
	 * before calling <code>setName(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>name</code> is not nullable)
	 * @since Iteration2
	 */
	public Attribute setName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("name is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_name, value) == 0) return this;
		changed_name = true;
		_name = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>type</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTypeChanged()} will return true. If you specify
	 * the same value as the old value isTypeChanged() will return the same as it did
	 * before calling <code>setType(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>type</code> is not nullable)
	 * @since Iteration2
	 */
	public Attribute setType(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("type is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_type, value) == 0) return this;
		changed_type = true;
		_type = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>uniqueValue</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isUniqueValueChanged()} will return true. If you specify
	 * the same value as the old value isUniqueValueChanged() will return the same as it did
	 * before calling <code>setUniqueValue(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>uniqueValue</code> is not nullable)
	 * @since Iteration2
	 */
	public Attribute setUniqueValue(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("uniqueValue is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_uniqueValue, value) == 0) return this;
		changed_uniqueValue = true;
		_uniqueValue = value;
		return this;
	}

}
