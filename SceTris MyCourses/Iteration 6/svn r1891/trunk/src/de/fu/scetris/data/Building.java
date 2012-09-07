/* Building.java / 2011-01-15+01:00
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
 * OO-Representation of the entity-relation Building
 * <p>
 * 
A Building> has a unique street address and belongs to a Department. Some Buildings have a special name (“Henry-Ford-Building”) which is unique across the whole university.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "Building")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Building\"", requiredSqlCols = {"address"})
@de.fu.weave.xml.annotation.XmlElement("Building")
public class Building extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String Name = "name";
	public static final String Address = "address";
	public static final String Department = "department";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 707811L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("name")
		@de.fu.weave.Form.ActiveValidator("name$validator")
		@de.fu.weave.Form.ActiveConverter("name$converter")
		public String name;
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("address") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("address$validator")
		@de.fu.weave.Form.ActiveConverter("address$converter")
		public String address;
		
		@de.fu.weave.Form.Pos(5)
		@de.fu.weave.Form.Field("department")
		@de.fu.weave.Form.ActiveValidator("department$validator")
		@de.fu.weave.Form.ActiveConverter("department$converter")
		@de.fu.weave.Form.Alternatives("department$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer department;
		
		public java.util.Map<Integer,java.lang.String> department$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		
		public Form setValues(Building $object) {
			
			id = $object.getId();
			timekey = $object.getTimekey();
			name = $object.getName();
			address = $object.getAddress();
			department = $object.refDepartment();
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
	 * 
	 * <p>
	 * 
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * name of the building
	 * <p>
	 * 
				The name of Building has to be unique so it identifies the Building.
			
	 */
	String _name = null;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * street adress
	 * <p>
	 * 
				The address of the Building has to be unique.
			
	 */
	String _address;
	boolean changed_address = false;
	boolean fetched_address = false;/**
	 * belongs to Department
	 * <p>
	 * 
	 */
	Department _department = null;
	boolean changed_department = false;
	boolean fetched_department = false;
	Integer ref_department;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Building(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_name = result.getString("name");
			if (result.wasNull()) {
				_name = null;
			}
			fetched_name = true;
			_address = result.getString("address");
			fetched_address = true;
			ref_department = result.getInt("department");
			if (result.wasNull()) {
				ref_department = null;
			}
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Building and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @since Iteration2
	 */
	Building(RelationManager manager, String _address) {
		timekey(true);
		this.manager = manager;
		this._address = _address;
		this.fetched_address = true;
	}
	
	/**
	 * Constructs the entity Building and initializes all attributes.
	 * @param _name 
				The name of Building has to be unique so it identifies the Building.
			
	 * @param _address 
				The address of the Building has to be unique.
			
	 * @param _department 
	 * @since Iteration2
	 */
	Building(RelationManager manager, final boolean full, String _name, String _address, Department _department) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._address = _address;
			this.fetched_address = true;
			this._department = _department;
			this.ref_department = _department.id();
			this.fetched_department = true;
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
		return "Building: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newBuilding} rather than {@link RelationManager#createBuilding).
	 * @since Iteration2
	 */
	@Override
	public Building create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Building\"" + " (\"name\", \"address\", \"department\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _address);
			
			if (ref_department != null) {
				stmt.setInt(i++, ref_department);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_address = false;
			changed_department = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Building\"" + " SET \"name\" = ?"
			+ ", \"address\" = ?"
			+ ", \"department\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (_name != null) {
				stmt.setString(i++, _name);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _address);
			
			if (ref_department != null) {
				stmt.setInt(i++, ref_department);
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
				throw new de.fu.bakery.orm.java.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_name = false;
			changed_address = false;
			changed_department = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Building\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Building entity) {
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
		if (entity instanceof Building) {
			return compareTo((Building) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Building entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Building) {
			return equals((Building) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "name", serial = true, nullable = true)
	public String getName() {
		if (_name == null) {
			return null;
		}
		return _name;
	}
	
	public boolean hasName() {
		return _name != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNameChanged() {
		return changed_name;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("address")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "address", serial = true)
	public String getAddress() {
		return _address;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAddressChanged() {
		return changed_address;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("department")
	@de.fu.weave.xml.annotation.XmlDependency("isDepartmentFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "department", serial = true, nullable = true, ref = Department.class)
	public Department getDepartment() throws de.fu.bakery.orm.java.DatabaseException {
		if (ref_department == null) {
			return null;
		}
		if (!fetched_department) {
			_department = manager.getDepartment(ref_department);
			fetched_department = true;
		}
		return _department;
	}
	
	public boolean hasDepartment() {
		return _department != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isDepartmentFetched() {
		return fetched_department;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDepartmentChanged() {
		return changed_department;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("department")
	public Integer refDepartment() {
		return ref_department;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>name</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNameChanged()} will return true. If you specify
	 * the same value as the old value isNameChanged() will return the same as it did
	 * before calling <code>setName(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Building setName(String value) {
		
		if (value == null) {
			clearName();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_name, value) == 0) return this;
		changed_name = true;
		_name = value;
		return this;
	}
	
	/**
	 * name may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Building clearName() {
		if (_name == null) {
			return this;
		}
		_name = null;
		changed_name = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>address</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isAddressChanged()} will return true. If you specify
	 * the same value as the old value isAddressChanged() will return the same as it did
	 * before calling <code>setAddress(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>address</code> is not nullable)
	 * @since Iteration2
	 */
	public Building setAddress(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("address is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_address, value) == 0) return this;
		changed_address = true;
		_address = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>department</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDepartmentChanged()} will return true. If you specify
	 * the same value as the old value isDepartmentChanged() will return the same as it did
	 * before calling <code>setDepartment(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Building setDepartment(Department value) {
		
		if (value == null) {
			clearDepartment();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_department, value._id) == 0) return this;
		changed_department = true;
		ref_department = value._id;
		fetched_department = true;
		_department = value;
		return this;
	}
	
	/**
	 * department may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Building clearDepartment() {
		if (_department == null && ref_department == null) {
			return this;
		}
		_department = null;
		ref_department = null;
		changed_department = true;
		return this;
	}

}
