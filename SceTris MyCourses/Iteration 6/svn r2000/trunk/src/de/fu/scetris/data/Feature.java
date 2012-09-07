/* Feature.java / 2011-02-01+01:00
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
 * OO-Representation of the entity-relation Feature
 * <p>
 * 
Rooms do have certain Features which may have a certain quantity. A Feature is identified by a name (“seats”, “beamers”, ...). For example any Room which is suitable to hold a lecture or a seminar in it will have a certain amount of seats, a lab has a certain amount of workplaces. While an auditorium may have 300 seats suitable for doing a lecture it might have only 75 seats suitable for doing an examination.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Feature")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"Feature\"", requiredSqlCols = {"name"})
@de.fu.weave.xml.annotation.XmlElement("Feature")
public class Feature extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Timekey = "timekey";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 3067311L;
		
		
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
		
		
		public Form setValues(Feature $object) {
			
			id = $object.getId();
			name = $object.getName();
			timekey = $object.getTimekey();
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
	boolean fetched_timekey = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Feature(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
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
		exists = true;
	}
	
	/**
	 * Constructs the entity Feature and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Feature(RelationManager manager, String _name) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity Feature and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Feature(RelationManager manager, final boolean full, String _name) {
		timekey(true);
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
		return "Feature: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newFeature} rather than {@link RelationManager#createFeature).
	 * @since Iteration2
	 */
	@Override
	public Feature create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Feature\"" + " (\"name\", \"timekey\")"
			+ " VALUES (?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Feature\"" + " SET \"name\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Feature\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Feature entity) {
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
		if (entity instanceof Feature) {
			return compareTo((Feature) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Feature entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Feature) {
			return equals((Feature) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstanceRequiresFeature.
	 * <p>
	 * This is the object of ElementInstanceRequiresFeature.
	 * @return The subject regarding ElementInstanceRequiresFeature where this (the java-object you call subjectsOfElementInstanceRequiresFeature on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.List<CourseElementInstance> coll = subjectsOfElementInstanceRequiresFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via ElementInstanceRequiresFeature. If you know that there will be only
	 * 1:1 relationships of the type ElementInstanceRequiresFeature, {@link #subjectOfElementInstanceRequiresFeature} is what you are looking for.
	 * <p>
	 * This is the object of ElementInstanceRequiresFeature.
	 * @return A Collection of subjects regarding ElementInstanceRequiresFeature where this (the java-object you call subjectsOfElementInstanceRequiresFeature on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<CourseElementInstance> subjectsOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE \"id\" IN (SELECT \"element_instance\" FROM "
			+ "\"scetris\".\"elementInstanceRequiresFeature\"" + " WHERE \"feature\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				ret.add(new CourseElementInstance(manager, result));
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
	public java.util.List<ElementInstanceRequiresFeature> whereObjectOfElementInstanceRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceRequiresFeature\""
			+ " WHERE \"feature\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstanceRequiresFeature> ret = new java.util.ArrayList<ElementInstanceRequiresFeature>();
			while (result.next()) {
				ret.add(new ElementInstanceRequiresFeature(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via ElementRequiresFeature.
	 * <p>
	 * This is the object of ElementRequiresFeature.
	 * @return The subject regarding ElementRequiresFeature where this (the java-object you call subjectsOfElementRequiresFeature on) is the object.
	 * @since Iteration3
	 */
	public CourseElement subjectOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.List<CourseElement> coll = subjectsOfElementRequiresFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via ElementRequiresFeature. If you know that there will be only
	 * 1:1 relationships of the type ElementRequiresFeature, {@link #subjectOfElementRequiresFeature} is what you are looking for.
	 * <p>
	 * This is the object of ElementRequiresFeature.
	 * @return A Collection of subjects regarding ElementRequiresFeature where this (the java-object you call subjectsOfElementRequiresFeature on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<CourseElement> subjectsOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElement\""
			+ " WHERE \"id\" IN (SELECT \"course_element\" FROM "
			+ "\"scetris\".\"elementRequiresFeature\"" + " WHERE \"feature\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<CourseElement> ret = new java.util.ArrayList<CourseElement>();
			while (result.next()) {
				ret.add(new CourseElement(manager, result));
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
	public java.util.List<ElementRequiresFeature> whereObjectOfElementRequiresFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementRequiresFeature\""
			+ " WHERE \"feature\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementRequiresFeature> ret = new java.util.ArrayList<ElementRequiresFeature>();
			while (result.next()) {
				ret.add(new ElementRequiresFeature(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via RoomProvidesFeature.
	 * <p>
	 * This is the object of RoomProvidesFeature.
	 * @return The subject regarding RoomProvidesFeature where this (the java-object you call subjectsOfRoomProvidesFeature on) is the object.
	 * @since Iteration3
	 */
	public Room subjectOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Room> coll = subjectsOfRoomProvidesFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via RoomProvidesFeature. If you know that there will be only
	 * 1:1 relationships of the type RoomProvidesFeature, {@link #subjectOfRoomProvidesFeature} is what you are looking for.
	 * <p>
	 * This is the object of RoomProvidesFeature.
	 * @return A Collection of subjects regarding RoomProvidesFeature where this (the java-object you call subjectsOfRoomProvidesFeature on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Room> subjectsOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE \"id\" IN (SELECT \"room\" FROM "
			+ "\"scetris\".\"roomProvidesFeature\"" + " WHERE \"feature\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Room> ret = new java.util.ArrayList<Room>();
			while (result.next()) {
				ret.add(new Room(manager, result));
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
	public java.util.List<RoomProvidesFeature> whereObjectOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomProvidesFeature\""
			+ " WHERE \"feature\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<RoomProvidesFeature> ret = new java.util.ArrayList<RoomProvidesFeature>();
			while (result.next()) {
				ret.add(new RoomProvidesFeature(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
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
	public Feature setName(String value) {
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
	
	

}
