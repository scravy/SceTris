/* CourseAttribute.java / 2010-10-30+02:00
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
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"CourseAttribute\"")
@de.fu.weave.xml.annotation.XmlElement("CourseAttribute")
public class CourseAttribute extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	 * type of the attribute
	 * <p>
	 * 
	 * @since Iteration2
	 */
	String _type;
	boolean changed_type = false;
	boolean fetched_type = false;/**
	 * is unique
	 * <p>
	 * 
	 * @since Iteration2
	 */
	boolean _uniqueValue;
	boolean changed_uniqueValue = false;
	boolean fetched_uniqueValue = false;/**
	 * obligatory
	 * <p>
	 * 
				Whether or not this Course Attribute is obligatory.
			
	 * @since Iteration2
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	CourseAttribute(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
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
		return "CourseAttribute: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseAttribute} rather than {@link RelationManager#createCourseAttribute).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseAttribute\"" + " (\"name\" , \"type\" , \"unique_value\" , \"required\" )"
			+ " VALUES (?, ?, ?, ?) RETURNING id;";
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
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _required);
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
		String query = "UPDATE " + "\"scetris\".\"CourseAttribute\"" + " SET \"name\" = ?, \"type\" = ?, \"unique_value\" = ?, \"required\" = ? WHERE id = ?;";
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
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _required);
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
		String query = "DELETE FROM " + "\"scetris\".\"CourseAttribute\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(CourseAttribute entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof CourseAttribute) {
			return compareTo((CourseAttribute) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseAttribute entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
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
		java.util.Collection<Course> coll = subjectsOfCourseHasCourseAttribute();
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
	public java.util.Collection<Course> subjectsOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"courseHasCourseAttribute\"" + " WHERE attribute = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Course> ret = new java.util.ArrayList<Course>();
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
	public java.util.Collection<CourseHasCourseAttribute> whereObjectOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseHasCourseAttribute\""
			+ " WHERE attribute = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseHasCourseAttribute> ret = new java.util.ArrayList<CourseHasCourseAttribute>();
			while (result.next()) {
				ret.add(new CourseHasCourseAttribute(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("type")
	public String getType() {
		return _type;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedType() {
		return changed_type;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("uniqueValue")
	public boolean getUniqueValue() {
		return _uniqueValue;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedUniqueValue() {
		return changed_uniqueValue;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("required")
	public boolean getRequired() {
		return _required;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedRequired() {
		return changed_required;
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
	public void setType(String value) {
		
		changed_type = true;
		_type = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setUniqueValue(boolean value) {
		
		changed_uniqueValue = true;
		_uniqueValue = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setRequired(boolean value) {
		
		changed_required = true;
		_required = value;
	}

}
