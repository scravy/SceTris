/* Room.java / 2010-11-27+01:00
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
 * OO-Representation of the entity-relation Room
 * <p>
 * 
A Room is a physical location where CourseElementInstances take place. A Room has an identifier which uniquely identifies it across the whole university. A Room is located inside a Building. Every Room has a number which is unique in the Building. A Room has Features and it may have a certain quantity of any Feature (e.g. 300 seats, a beamer, 20 workstations). Some Rooms do have a special name (“Audimax”) which is unique in the building. A Room may be of a certain type (auditorium, lab, ...). Not all rooms are always available.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "Room")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Room\"", requiredSqlCols = {"number", "building"})
@de.fu.weave.xml.annotation.XmlElement("Room")
public class Room extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String Name = "name";
	public static final String Number = "number";
	public static final String Building = "building";
	

	/**
	 * @since Iteration4
	 */
	public static class Stub {
		Stub() {
		}
	
		public int Id;public java.sql.Timestamp Timekey;public String Name;public String Number;public Building Building;
	}
	
	/**
	 *
	 */
	interface Message {
	}
	
	/**
	 * @since Iteration4
	 */
	public static Stub makeStub() {
		return new Stub();
	}
	
	
	/**
	 * @since Iteration4
	 */
	public Stub asStub() {
		Stub stub = makeStub();
		stub.Id = _id;
		stub.Timekey = _timekey;
		stub.Name = _name;
		stub.Number = _number;
		stub.Building = _building;
		
		return stub;
	}
	
	/**
	 * @since Iteration4
	 */
	public Message asMessage() {
		return new Message() {
		};
	}

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
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * name of the room
	 * <p>
	 * 
				The Roomname is unique per Building and identifies the Room in this Building.
			
	 * @since Iteration2
	 */
	String _name = null;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * room number
	 * <p>
	 * 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @since Iteration2
	 */
	String _number;
	boolean changed_number = false;
	boolean fetched_number = false;/**
	 * located in
	 * <p>
	 * 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @since Iteration2
	 */
	Building _building;
	boolean changed_building = false;
	boolean fetched_building = false;
	int ref_building;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Room(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
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
			_number = result.getString("number");
			fetched_number = true;
			ref_building = result.getInt("building");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Room and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @since Iteration2
	 */
	Room(RelationManager manager, String _number, Building _building) {
		timekey(true);
		this.manager = manager;
		this._number = _number;
		this.fetched_number = true;
		this._building = _building;
		this.ref_building = _building.id();
		this.fetched_building = true;
	}
	
	/**
	 * Constructs the entity Room and initializes all attributes.
	 * @param _name 
				The Roomname is unique per Building and identifies the Room in this Building.
			
	 * @param _number 
				The Roomnumber has not to be unique. It is an alternative name for the Room.
			
	 * @param _building 
				The Buildingname is unique and represents a location where the Room is.
			
	 * @since Iteration2
	 */
	Room(RelationManager manager, final boolean full, String _name, String _number, Building _building) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._number = _number;
			this.fetched_number = true;
			this._building = _building;
			this.ref_building = _building.id();
			this.fetched_building = true;
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
		return "Room: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newRoom} rather than {@link RelationManager#createRoom).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Room\"" + " (\"name\", \"number\", \"building\", \"timekey\")"
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
			
				stmt.setString(i++, _number);
			
				stmt.setInt(i++, ref_building);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_number = false;
			changed_building = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
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
		String query = "UPDATE " + "\"scetris\".\"Room\"" + " SET \"name\" = ?"
			+ ", \"number\" = ?"
			+ ", \"building\" = ?"
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
			
				stmt.setString(i++, _number);
			
				stmt.setInt(i++, ref_building);
			
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
			changed_number = false;
			changed_building = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Room\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Room entity) {
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
		if (entity instanceof Room) {
			return compareTo((Room) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Room entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Room) {
			return equals((Room) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via RoomPrefersTimeslot.
	 * <p>
	 * This is the subject of RoomPrefersTimeslot.
	 
	 * @return The object regarding RoomPrefersTimeslot where this (the java-object you call objectsOfRoomPrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public Timeslot objectOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Timeslot> coll = objectsOfRoomPrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via RoomPrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type RoomPrefersTimeslot, {@link #objectOfRoomPrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the subject of RoomPrefersTimeslot.
	 * @return The object regarding RoomPrefersTimeslot where this (the java-object you call objectsOfRoomPrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<Timeslot> objectsOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE id IN (SELECT timeslot FROM "
			+ "\"scetris\".\"roomPrefersTimeslot\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Timeslot> ret = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				ret.add(new Timeslot(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<RoomPrefersTimeslot> whereSubjectOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomPrefersTimeslot\""
			+ " WHERE \"room\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<RoomPrefersTimeslot> ret = new java.util.ArrayList<RoomPrefersTimeslot>();
			while (result.next()) {
				ret.add(new RoomPrefersTimeslot(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via RoomProvidesFeature.
	 * <p>
	 * This is the subject of RoomProvidesFeature.
	 
	 * @return The object regarding RoomProvidesFeature where this (the java-object you call objectsOfRoomProvidesFeature on) is the subject.
	 * @since Iteration3
	 */
	public Feature objectOfRoomProvidesFeature() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Feature> coll = objectsOfRoomProvidesFeature();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via RoomProvidesFeature. If you know that there will be only
	 * 1:1 relationships of the type RoomProvidesFeature, {@link #objectOfRoomProvidesFeature} is what you are looking for.
	 * <p>
	 * This is the subject of RoomProvidesFeature.
	 * @return The object regarding RoomProvidesFeature where this (the java-object you call objectsOfRoomProvidesFeature on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<Feature> objectsOfRoomProvidesFeature() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE id IN (SELECT feature FROM "
			+ "\"scetris\".\"roomProvidesFeature\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Feature> ret = new java.util.ArrayList<Feature>();
			while (result.next()) {
				ret.add(new Feature(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<RoomProvidesFeature> whereSubjectOfRoomProvidesFeature() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomProvidesFeature\""
			+ " WHERE \"room\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<RoomProvidesFeature> ret = new java.util.ArrayList<RoomProvidesFeature>();
			while (result.next()) {
				ret.add(new RoomProvidesFeature(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersRoom.
	 * <p>
	 * This is the object of ElementInstancePrefersRoom.
	 * @return The subject regarding ElementInstancePrefersRoom where this (the java-object you call subjectsOfElementInstancePrefersRoom on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstancePrefersRoom() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseElementInstance> coll = subjectsOfElementInstancePrefersRoom();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via ElementInstancePrefersRoom. If you know that there will be only
	 * 1:1 relationships of the type ElementInstancePrefersRoom, {@link #subjectOfElementInstancePrefersRoom} is what you are looking for.
	 * <p>
	 * This is the object of ElementInstancePrefersRoom.
	 * @return A Collection of subjects regarding ElementInstancePrefersRoom where this (the java-object you call subjectsOfElementInstancePrefersRoom on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<CourseElementInstance> subjectsOfElementInstancePrefersRoom() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"elementInstancePrefersRoom\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				ret.add(new CourseElementInstance(manager, result));
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
	public java.util.List<ElementInstancePrefersRoom> whereObjectOfElementInstancePrefersRoom() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersRoom\""
			+ " WHERE room = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<ElementInstancePrefersRoom> ret = new java.util.ArrayList<ElementInstancePrefersRoom>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersRoom(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstanceTakesPlaceInRoom.
	 * <p>
	 * This is the object of ElementInstanceTakesPlaceInRoom.
	 * @return The subject regarding ElementInstanceTakesPlaceInRoom where this (the java-object you call subjectsOfElementInstanceTakesPlaceInRoom on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstanceTakesPlaceInRoom() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseElementInstance> coll = subjectsOfElementInstanceTakesPlaceInRoom();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via ElementInstanceTakesPlaceInRoom. If you know that there will be only
	 * 1:1 relationships of the type ElementInstanceTakesPlaceInRoom, {@link #subjectOfElementInstanceTakesPlaceInRoom} is what you are looking for.
	 * <p>
	 * This is the object of ElementInstanceTakesPlaceInRoom.
	 * @return A Collection of subjects regarding ElementInstanceTakesPlaceInRoom where this (the java-object you call subjectsOfElementInstanceTakesPlaceInRoom on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<CourseElementInstance> subjectsOfElementInstanceTakesPlaceInRoom() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				ret.add(new CourseElementInstance(manager, result));
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
	public java.util.List<ElementInstanceTakesPlaceInRoom> whereObjectOfElementInstanceTakesPlaceInRoom() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\""
			+ " WHERE room = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<ElementInstanceTakesPlaceInRoom> ret = new java.util.ArrayList<ElementInstanceTakesPlaceInRoom>();
			while (result.next()) {
				ret.add(new ElementInstanceTakesPlaceInRoom(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("number")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "number", serial = true)
	public String getNumber() {
		return _number;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNumberChanged() {
		return changed_number;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("building")
	@de.fu.weave.xml.annotation.XmlDependency("isBuildingFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "building", serial = true, ref = Building.class)
	public Building getBuilding() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_building) {
			_building = manager.getBuilding(ref_building);
			fetched_building = true;
		}
		return _building;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isBuildingFetched() {
		return fetched_building;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isBuildingChanged() {
		return changed_building;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("building")
	public int refBuilding() {
		return ref_building;
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
	public void setName(String value) {
		
		if (value == null) {
			clearName();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_name, value) == 0) return;
		changed_name = true;
		_name = value;
	}
	
	/**
	 * name may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearName() {
		if (_name == null) {
			return;
		}
		_name = null;
		changed_name = true;
	}

	
	/**
	 * Sets the value of <code>number</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNumberChanged()} will return true. If you specify
	 * the same value as the old value isNumberChanged() will return the same as it did
	 * before calling <code>setNumber(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>number</code> is not nullable)
	 * @since Iteration2
	 */
	public void setNumber(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("number is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_number, value) == 0) return;
		changed_number = true;
		_number = value;
	}

	
	/**
	 * Sets the value of <code>building</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isBuildingChanged()} will return true. If you specify
	 * the same value as the old value isBuildingChanged() will return the same as it did
	 * before calling <code>setBuilding(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>building</code> is not nullable)
	 * @since Iteration2
	 */
	public void setBuilding(Building value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("building is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_building, value._id) == 0) return;
		changed_building = true;
		ref_building = value._id;
		fetched_building = true;
		_building = value;
	}

}
