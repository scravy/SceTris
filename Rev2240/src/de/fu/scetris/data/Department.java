/* Department.java / 2011-03-03+01:00
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
 * OO-Representation of the entity-relation Department
 * <p>
 * 
			A logical group of several entities. Persons, Buildings and Courses belong to a Department. Buildings may belong to several Departments.
		
 */
@de.fu.weave.orm.annotation.Entity(name = "Department")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"Department\"", requiredSqlCols = {"name"})
@de.fu.weave.xml.annotation.XmlElement("Department")
public class Department extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Timekey = "timekey";
	public static final String SuperordinateDepartment = "superordinate_department";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 2831352L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		@de.fu.weave.Form.Pos(2)
		@de.fu.weave.Form.Field("name") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("name$validator")
		@de.fu.weave.Form.ActiveConverter("name$converter")
		public String name;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("superordinateDepartment")
		@de.fu.weave.Form.ActiveValidator("superordinateDepartment$validator")
		@de.fu.weave.Form.ActiveConverter("superordinateDepartment$converter")
		@de.fu.weave.Form.Alternatives("superordinateDepartment$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer superordinateDepartment;
		
		public java.util.Map<Integer,java.lang.String> superordinateDepartment$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		
		public Form setValues(Department $object) {
			
			id = $object.getId();
			name = $object.getName();
			timekey = $object.getTimekey();
			superordinateDepartment = $object.refSuperordinateDepartment();
			return this;
		}
	}
	
	/**
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
	 * 
	 * <p>
	 * 
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * superordinated department
	 * <p>
	 * 
				The superordinated department of this department.
			
	 */
	Department _superordinateDepartment = null;
	boolean changed_superordinateDepartment = false;
	boolean fetched_superordinateDepartment = false;
	Integer ref_superordinateDepartment;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Department(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_superordinateDepartment = result.getInt("superordinate_department");
			if (result.wasNull()) {
				ref_superordinateDepartment = null;
			}
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Department and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 */
	Department(RelationManager manager, String _name) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity Department and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _superordinateDepartment 
				The superordinated department of this department.
			
	 */
	Department(RelationManager manager, final boolean full, String _name, Department _superordinateDepartment) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._superordinateDepartment = _superordinateDepartment;
			this.ref_superordinateDepartment = _superordinateDepartment.id();
			this.fetched_superordinateDepartment = true;
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
		return "Department: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return de.fu.weave.orm.GenericRelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newDepartment} rather than {@link RelationManager#createDepartment).
	 */
	@Override
	public Department create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Department\"" + " (\"name\", \"superordinate_department\", \"timekey\")"
			+ " VALUES (?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (ref_superordinateDepartment != null) {
				stmt.setInt(i++, ref_superordinateDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_superordinateDepartment = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Department\"" + " SET \"name\" = ?"
			+ ", \"superordinate_department\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (ref_superordinateDepartment != null) {
				stmt.setInt(i++, ref_superordinateDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
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
			changed_superordinateDepartment = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Department\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Department entity) {
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
		if (entity instanceof Department) {
			return compareTo((Department) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Department entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Department) {
			return equals((Department) obj);
		}
		return false;
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

	
	@de.fu.weave.xml.annotation.XmlElement("superordinateDepartment")
	@de.fu.weave.xml.annotation.XmlDependency("isSuperordinateDepartmentFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "superordinate_department", serial = true, nullable = true, ref = Department.class)
	public Department getSuperordinateDepartment() throws de.fu.weave.orm.DatabaseException {
		if (ref_superordinateDepartment == null) {
			return null;
		}
		if (!fetched_superordinateDepartment) {
			_superordinateDepartment = manager.getDepartment(ref_superordinateDepartment);
			fetched_superordinateDepartment = true;
		}
		return _superordinateDepartment;
	}
	
	public boolean hasSuperordinateDepartment() {
		return _superordinateDepartment != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isSuperordinateDepartmentFetched() {
		return fetched_superordinateDepartment;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isSuperordinateDepartmentChanged() {
		return changed_superordinateDepartment;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("superordinateDepartment")
	public Integer refSuperordinateDepartment() {
		return ref_superordinateDepartment;
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
	public Department setName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("name is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_name, value) == 0) return this;
		changed_name = true;
		_name = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>superordinateDepartment</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isSuperordinateDepartmentChanged()} will return true. If you specify
	 * the same value as the old value isSuperordinateDepartmentChanged() will return the same as it did
	 * before calling <code>setSuperordinateDepartment(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Department setSuperordinateDepartment(Department value) {
		
		if (value == null) {
			clearSuperordinateDepartment();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_superordinateDepartment, value._id) == 0) return this;
		changed_superordinateDepartment = true;
		ref_superordinateDepartment = value._id;
		fetched_superordinateDepartment = true;
		_superordinateDepartment = value;
		return this;
	}
	
	/**
	 * superordinateDepartment may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Department clearSuperordinateDepartment() {
		if (_superordinateDepartment == null && ref_superordinateDepartment == null) {
			return this;
		}
		_superordinateDepartment = null;
		ref_superordinateDepartment = null;
		changed_superordinateDepartment = true;
		return this;
	}

}
