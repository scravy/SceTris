/* Day.java / 2010-11-04+01:00
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
 * OO-Representation of the entity-relation Day
 * <p>
 * 
			A Day holds a set of Timeslots.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Day")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Day\"")
@de.fu.weave.xml.annotation.XmlElement("Day")
public class Day extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	boolean fetched_timekey = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Day(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Day and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Day(RelationManager manager, String _name) {
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity Day and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Day(RelationManager manager, final boolean full, String _name) {
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
		return "Day: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newDay} rather than {@link RelationManager#createDay).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Day\"" + " (\"name\" )"
			+ " VALUES (?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
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
		String query = "UPDATE " + "\"scetris\".\"Day\"" + " SET \"name\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Day\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Day entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof Day) {
			return compareTo((Day) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Day entity) {
		if (entity == null) return false;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Day) {
			return equals((Day) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIdChanged() {
		return changed_id;
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

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("id is not nullable, null given");
		}
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setName(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("name is not nullable, null given");
		}
		changed_name = true;
		_name = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timekey is not nullable, null given");
		}
		changed_timekey = true;
		_timekey = value;
	}

}
