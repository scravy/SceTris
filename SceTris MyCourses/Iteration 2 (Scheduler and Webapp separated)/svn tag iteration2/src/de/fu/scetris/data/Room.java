/* Room.java / 2010-10-30+02:00
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
@de.fu.weave.orm.annotation.Entity(name = "Room")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Room\"")
@de.fu.weave.xml.annotation.XmlElement("Room")
public class Room extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	Room(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_name = result.getString("name");
			fetched_name = true;
			_number = result.getString("number");
			fetched_number = true;
			ref_building = result.getInt("building");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newRoom} rather than {@link RelationManager#createRoom).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Room\"" + " (\"name\" , \"number\" , \"building\" )"
			+ " VALUES (?, ?, ?) RETURNING id;";
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
		String query = "UPDATE " + "\"scetris\".\"Room\"" + " SET \"name\" = ?, \"number\" = ?, \"building\" = ? WHERE id = ?;";
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
		String query = "DELETE FROM " + "\"scetris\".\"Room\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Room entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Room) {
			return compareTo((Room) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Room entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
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
	public Timeslot objectOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Timeslot> coll = objectsOfRoomPrefersTimeslot();
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
	public java.util.Collection<Timeslot> objectsOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE id IN (SELECT timeslot FROM "
			+ "\"scetris\".\"roomPrefersTimeslot\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Timeslot> ret = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				ret.add(new Timeslot(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.Collection<RoomPrefersTimeslot> whereSubjectOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomPrefersTimeslot\""
			+ " WHERE \"room\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<RoomPrefersTimeslot> ret = new java.util.ArrayList<RoomPrefersTimeslot>();
			while (result.next()) {
				ret.add(new RoomPrefersTimeslot(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via RoomProvidesFeature.
	 * <p>
	 * This is the subject of RoomProvidesFeature.
	 
	 * @return The object regarding RoomProvidesFeature where this (the java-object you call objectsOfRoomProvidesFeature on) is the subject.
	 * @since Iteration3
	 */
	public Feature objectOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Feature> coll = objectsOfRoomProvidesFeature();
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
	public java.util.Collection<Feature> objectsOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Feature\""
			+ " WHERE id IN (SELECT feature FROM "
			+ "\"scetris\".\"roomProvidesFeature\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Feature> ret = new java.util.ArrayList<Feature>();
			while (result.next()) {
				ret.add(new Feature(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.Collection<RoomProvidesFeature> whereSubjectOfRoomProvidesFeature() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomProvidesFeature\""
			+ " WHERE \"room\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<RoomProvidesFeature> ret = new java.util.ArrayList<RoomProvidesFeature>();
			while (result.next()) {
				ret.add(new RoomProvidesFeature(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersRoom.
	 * <p>
	 * This is the object of ElementInstancePrefersRoom.
	 * @return The subject regarding ElementInstancePrefersRoom where this (the java-object you call subjectsOfElementInstancePrefersRoom on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseElementInstance> coll = subjectsOfElementInstancePrefersRoom();
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
	public java.util.Collection<CourseElementInstance> subjectsOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"elementInstancePrefersRoom\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
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
	public java.util.Collection<ElementInstancePrefersRoom> whereObjectOfElementInstancePrefersRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersRoom\""
			+ " WHERE room = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstancePrefersRoom> ret = new java.util.ArrayList<ElementInstancePrefersRoom>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersRoom(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstanceTakesPlaceInRoom.
	 * <p>
	 * This is the object of ElementInstanceTakesPlaceInRoom.
	 * @return The subject regarding ElementInstanceTakesPlaceInRoom where this (the java-object you call subjectsOfElementInstanceTakesPlaceInRoom on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseElementInstance> coll = subjectsOfElementInstanceTakesPlaceInRoom();
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
	public java.util.Collection<CourseElementInstance> subjectsOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " WHERE room = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
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
	public java.util.Collection<ElementInstanceTakesPlaceInRoom> whereObjectOfElementInstanceTakesPlaceInRoom() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\""
			+ " WHERE room = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstanceTakesPlaceInRoom> ret = new java.util.ArrayList<ElementInstanceTakesPlaceInRoom>();
			while (result.next()) {
				ret.add(new ElementInstanceTakesPlaceInRoom(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
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
	public boolean isChangedName() {
		return changed_name;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("number")
	public String getNumber() {
		return _number;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedNumber() {
		return changed_number;
	}

	
	public Building getBuilding() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_building) {
			_building = manager.getBuilding(ref_building);
			fetched_building = true;
		}
		return _building;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedBuilding() {
		return changed_building;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("building")
	public int refBuilding() {
		return ref_building;
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
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setName(String value) {
		if (value == null) {
			_name = null;
			return;
		}
		changed_name = true;
		_name = value;
	}
	
	/**
	 * name may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearName() {
		_name = null;
		changed_name = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setNumber(String value) {
		
		changed_number = true;
		_number = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setBuilding(Building value) {
		
		changed_building = true;
		ref_building = value._id;
		fetched_building = true;
		_building = value;
	}

}
