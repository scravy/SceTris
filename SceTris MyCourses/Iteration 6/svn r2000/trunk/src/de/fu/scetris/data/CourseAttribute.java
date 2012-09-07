/* CourseAttribute.java / 2011-02-01+01:00
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
 * OO-Representation of the entity-relation CourseAttribute
 * <p>
 * 
			CourseAttribute represents user-created Attributes for Courses which can be used to construct constraints.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "CourseAttribute")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"CourseAttribute\"", requiredSqlCols = {"name"})
@de.fu.weave.xml.annotation.XmlElement("CourseAttribute")
public class CourseAttribute extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Timekey = "timekey";
	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Type = "type";
	public static final String UniqueValue = "unique_value";
	public static final String Required = "required";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 1415640L;
		
		
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
		
		@de.fu.weave.Form.Pos(6)
		@de.fu.weave.Form.Field("required") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("required$validator")
		@de.fu.weave.Form.ActiveConverter("required$converter")
		public boolean required;
		
		
		public Form setValues(CourseAttribute $object) {
			
			timekey = $object.getTimekey();
			id = $object.getId();
			name = $object.getName();
			type = $object.getType();
			uniqueValue = $object.getUniqueValue();
			required = $object.getRequired();
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
	boolean fetched_uniqueValue = false;/**
	 * obligatory
	 * <p>
	 * 
				Whether or not this Course Attribute is obligatory.
			
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	CourseAttribute(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
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
			_required = result.getBoolean("required");
			fetched_required = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity CourseAttribute and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	CourseAttribute(RelationManager manager, String _name) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity CourseAttribute and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _type 
	 * @param _uniqueValue 
	 * @param _required 
				Whether or not this Course Attribute is obligatory.
			
	 * @since Iteration2
	 */
	CourseAttribute(RelationManager manager, final boolean full, String _name, String _type, boolean _uniqueValue, boolean _required) {
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
			this._required = _required;
			this.fetched_required = true;
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
		return "CourseAttribute: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseAttribute} rather than {@link RelationManager#createCourseAttribute).
	 * @since Iteration2
	 */
	@Override
	public CourseAttribute create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseAttribute\"" + " (\"name\", \"type\", \"unique_value\", \"required\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, _required = false);
			} else {
				stmt.setBoolean(i++, _required);
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_type = false;
			changed_uniqueValue = false;
			changed_required = false;
			
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
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"CourseAttribute\"" + " SET \"name\" = ?"
			+ ", \"type\" = ?"
			+ ", \"unique_value\" = ?"
			+ ", \"required\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _required);
			}
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_name = false;
			changed_type = false;
			changed_uniqueValue = false;
			changed_required = false;
			
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
	 * @since Iteration4
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseAttribute\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
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
	
	public int compareTo(CourseAttribute entity) {
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
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseAttribute) {
			return compareTo((CourseAttribute) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseAttribute entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof CourseAttribute) {
			return equals((CourseAttribute) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseHasCourseAttribute.
	 * <p>
	 * This is the object of CourseHasCourseAttribute.
	 * @return The subject regarding CourseHasCourseAttribute where this (the java-object you call subjectsOfCourseHasCourseAttribute on) is the object.
	 * @since Iteration3
	 */
	public Course subjectOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Course> coll = subjectsOfCourseHasCourseAttribute();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via CourseHasCourseAttribute. If you know that there will be only
	 * 1:1 relationships of the type CourseHasCourseAttribute, {@link #subjectOfCourseHasCourseAttribute} is what you are looking for.
	 * <p>
	 * This is the object of CourseHasCourseAttribute.
	 * @return A Collection of subjects regarding CourseHasCourseAttribute where this (the java-object you call subjectsOfCourseHasCourseAttribute on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Course> subjectsOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE \"id\" IN (SELECT \"course\" FROM "
			+ "\"scetris\".\"courseHasCourseAttribute\"" + " WHERE \"attribute\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Course> ret = new java.util.ArrayList<Course>();
			while (result.next()) {
				ret.add(new Course(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<CourseHasCourseAttribute> whereObjectOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseHasCourseAttribute\""
			+ " WHERE \"attribute\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<CourseHasCourseAttribute> ret = new java.util.ArrayList<CourseHasCourseAttribute>();
			while (result.next()) {
				ret.add(new CourseHasCourseAttribute(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.weave.orm.annotation.Attribute(name = "name", serial = true, use = "title")
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
	@de.fu.weave.orm.annotation.Attribute(name = "type", serial = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "unique_value", serial = true)
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("required")
	@de.fu.weave.orm.annotation.Attribute(name = "required", serial = true)
	public boolean getRequired() {
		return _required;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isRequiredChanged() {
		return changed_required;
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
	public CourseAttribute setName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("name is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_name, value) == 0) return this;
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
	public CourseAttribute setType(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("type is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_type, value) == 0) return this;
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
	public CourseAttribute setUniqueValue(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("uniqueValue is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_uniqueValue, value) == 0) return this;
		changed_uniqueValue = true;
		_uniqueValue = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>required</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRequiredChanged()} will return true. If you specify
	 * the same value as the old value isRequiredChanged() will return the same as it did
	 * before calling <code>setRequired(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>required</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseAttribute setRequired(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("required is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_required, value) == 0) return this;
		changed_required = true;
		_required = value;
		return this;
	}

}
