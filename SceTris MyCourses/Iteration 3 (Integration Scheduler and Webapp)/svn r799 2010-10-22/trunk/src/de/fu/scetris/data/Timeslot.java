/* Timeslot.java / 2010-10-22+02:00
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
 * OO-Representation of the entity-relation Timeslot
 * <p>
 * 
			A Timeslot represents a duration in time in which a CourseElementInstance can take place.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Timeslot")
@de.fu.weave.xml.annotation.XmlElement("Timeslot")
public class Timeslot extends de.fu.weave.orm.GenericEntity implements Comparable<de.fu.weave.orm.Entity> {
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
	 * Day on which this Timeslot takes place.
	 * <p>
	 * 
				The Day on which the Timeslot will be placed.
			
	 * @since Iteration2
	 */
	Day _day;
	boolean changed_day = false;
	boolean fetched_day = false;
	int ref_day;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Timeslot(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_day = result.getInt("day");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Timeslot and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @since Iteration2
	 */
	Timeslot(RelationManager manager, Day _day) {
		this.manager = manager;
		this._day = _day;
		this.ref_day = _day.id();
		this.fetched_day = true;
	}
	
	/**
	 * Constructs the entity Timeslot and initializes all attributes.
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @since Iteration2
	 */
	Timeslot(RelationManager manager, final boolean full, Day _day) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._day = _day;
			this.ref_day = _day.id();
			this.fetched_day = true;
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
		return "Timeslot: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newTimeslot} rather than {@link RelationManager#createTimeslot).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Timeslot\"" + " (\"day\" )"
			+ " VALUES (?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_day);
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
		String query = "UPDATE " + "\"scetris\".\"Timeslot\"" + " SET \"day\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_day);
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
		String query = "DELETE FROM " + "\"scetris\".\"Timeslot\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Timeslot entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Timeslot) {
			return compareTo((Timeslot) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Timeslot entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersTimeslot.
	 * <p>
	 * This is the object of ElementInstancePrefersTimeslot.
	 * @return The subject regarding ElementInstancePrefersTimeslot where this (the java-object you call subjectsOfElementInstancePrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseElementInstance> coll = subjectsOfElementInstancePrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via ElementInstancePrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type ElementInstancePrefersTimeslot, {@link #subjectOfElementInstancePrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the object of ElementInstancePrefersTimeslot.
	 * @return A Collection of subjects regarding ElementInstancePrefersTimeslot where this (the java-object you call subjectsOfElementInstancePrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseElementInstance> subjectsOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT room FROM "
			+ "\"scetris\".\"elementInstancePrefersTimeslot\"" + " WHERE timeslot = ?);";
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
	public java.util.Collection<ElementInstancePrefersTimeslot> whereObjectOfElementInstancePrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<ElementInstancePrefersTimeslot> ret = new java.util.ArrayList<ElementInstancePrefersTimeslot>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersTimeslot(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonPrefersTimeslot.
	 * <p>
	 * This is the object of PersonPrefersTimeslot.
	 * @return The subject regarding PersonPrefersTimeslot where this (the java-object you call subjectsOfPersonPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Person> coll = subjectsOfPersonPrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonPrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type PersonPrefersTimeslot, {@link #subjectOfPersonPrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the object of PersonPrefersTimeslot.
	 * @return A Collection of subjects regarding PersonPrefersTimeslot where this (the java-object you call subjectsOfPersonPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public java.util.Collection<Person> subjectsOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT user FROM "
			+ "\"scetris\".\"personPrefersTimeslot\"" + " WHERE timeslot = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
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
	public java.util.Collection<PersonPrefersTimeslot> whereObjectOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonPrefersTimeslot> ret = new java.util.ArrayList<PersonPrefersTimeslot>();
			while (result.next()) {
				ret.add(new PersonPrefersTimeslot(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via RoomPrefersTimeslot.
	 * <p>
	 * This is the object of RoomPrefersTimeslot.
	 * @return The subject regarding RoomPrefersTimeslot where this (the java-object you call subjectsOfRoomPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public Room subjectOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Room> coll = subjectsOfRoomPrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via RoomPrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type RoomPrefersTimeslot, {@link #subjectOfRoomPrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the object of RoomPrefersTimeslot.
	 * @return A Collection of subjects regarding RoomPrefersTimeslot where this (the java-object you call subjectsOfRoomPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public java.util.Collection<Room> subjectsOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE id IN (SELECT room FROM "
			+ "\"scetris\".\"roomPrefersTimeslot\"" + " WHERE timeslot = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Room> ret = new java.util.ArrayList<Room>();
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
	public java.util.Collection<RoomPrefersTimeslot> whereObjectOfRoomPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomPrefersTimeslot\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<RoomPrefersTimeslot> ret = new java.util.ArrayList<RoomPrefersTimeslot>();
			while (result.next()) {
				ret.add(new RoomPrefersTimeslot(manager, null, this, result));
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

	
	public Day getDay() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_day) {
			_day = manager.getDay(ref_day);
			fetched_day = true;
		}
		return _day;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedDay() {
		return changed_day;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("day")
	public int refDay() {
		return ref_day;
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
	public void setDay(Day value) {
		
		changed_day = true;
		ref_day = value._id;
		fetched_day = true;
		_day = value;
	}

}
