/* Department.java / 2010-10-30+02:00
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
 * OO-Representation of the entity-relation Department
 * <p>
 * 
			A logical group of several entities. Persons, Buildings and Courses belong to a Department. Buildings may belong to several Departments.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Department")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Department\"")
@de.fu.weave.xml.annotation.XmlElement("Department")
public class Department extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;

	/**
	 * numeric identifier
	 * <p>
	 * 
				An artificial key introduced to allow easy referencing of an entity.
				Once an id has been set it won’t change, therfore avoiding update anomalies.
			
	 * @since Iteration2
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
			
	 * @since Iteration2
	 */
	String _name;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * superordinated department
	 * <p>
	 * 
				The superordinated department of this department.
			
	 * @since Iteration2
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
	 * @since Iteration2
	 */
	Department(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_superordinateDepartment = result.getInt("superordinate_department");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Department and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Department(RelationManager manager, String _name) {
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
			
	 * @since Iteration2
	 */
	Department(RelationManager manager, final boolean full, String _name, Department _superordinateDepartment) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newDepartment} rather than {@link RelationManager#createDepartment).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Department\"" + " (\"name\" , \"superordinate_department\" )"
			+ " VALUES (?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (ref_superordinateDepartment != null) {
				stmt.setInt(i++, ref_superordinateDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
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
		String query = "UPDATE " + "\"scetris\".\"Department\"" + " SET \"name\" = ?, \"superordinate_department\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			if (ref_superordinateDepartment != null) {
				stmt.setInt(i++, ref_superordinateDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			stmt.setInt(i, this.id());
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
		String query = "DELETE FROM " + "\"scetris\".\"Department\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Department entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Department) {
			return compareTo((Department) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Department entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Department) {
			return equals((Department) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	public int getId() {
		return _id;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedId() {
		return changed_id;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	public String getName() {
		return _name;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedName() {
		return changed_name;
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
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedSuperordinateDepartment() {
		return changed_superordinateDepartment;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("superordinateDepartment")
	public Integer refSuperordinateDepartment() {
		return ref_superordinateDepartment;
	}

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setName(String value) {
		
		changed_name = true;
		_name = value;
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
	public void setSuperordinateDepartment(Department value) {
		if (value == null) {
			_superordinateDepartment = null;
			ref_superordinateDepartment = null;
			return;
		}
		changed_superordinateDepartment = true;
		ref_superordinateDepartment = value._id;
		fetched_superordinateDepartment = true;
		_superordinateDepartment = value;
	}
	
	/**
	 * superordinateDepartment may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearSuperordinateDepartment() {
		_superordinateDepartment = null;
		ref_superordinateDepartment = null;
		changed_superordinateDepartment = true;
	}

}
