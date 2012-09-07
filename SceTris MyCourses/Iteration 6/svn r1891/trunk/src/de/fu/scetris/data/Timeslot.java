/* Timeslot.java / 2011-01-15+01:00
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
@de.fu.bakery.orm.java.annotation.Entity(name = "Timeslot")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Timeslot\"", requiredSqlCols = {"day", "startingTime"})
@de.fu.weave.xml.annotation.XmlElement("Timeslot")
public class Timeslot extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String Day = "day";
	public static final String StartingTime = "startingTime";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 4719080L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("day") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("day$validator")
		@de.fu.weave.Form.ActiveConverter("day$converter")
		@de.fu.weave.Form.Alternatives("day$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int day;
		
		public java.util.Map<Integer,java.lang.String> day$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("startingTime") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("startingTime$validator")
		@de.fu.weave.Form.ActiveConverter("startingTime$converter")
		public java.sql.Time startingTime;
		
		
		public Form setValues(Timeslot $object) {
			
			id = $object.getId();
			timekey = $object.getTimekey();
			day = $object.refDay();
			startingTime = $object.getStartingTime();
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
	 * Day on which this Timeslot takes place.
	 * <p>
	 * 
				The Day on which the Timeslot will be placed.
			
	 */
	Day _day;
	boolean changed_day = false;
	boolean fetched_day = false;
	int ref_day;/**
	 * Time on which the CourseElementInstance will begin.
	 * <p>
	 * 
				Time on which the COurseElementInstance will begin.
			
	 */
	java.sql.Time _startingTime;
	boolean changed_startingTime = false;
	boolean fetched_startingTime = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Timeslot(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_day = result.getInt("day");
			_startingTime = result.getTime("startingTime");
			fetched_startingTime = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Timeslot and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @since Iteration2
	 */
	Timeslot(RelationManager manager, Day _day, java.sql.Time _startingTime) {
		timekey(true);
		this.manager = manager;
		this._day = _day;
		this.ref_day = _day.id();
		this.fetched_day = true;
		this._startingTime = _startingTime;
		this.fetched_startingTime = true;
	}
	
	/**
	 * Constructs the entity Timeslot and initializes all attributes.
	 * @param _day 
				The Day on which the Timeslot will be placed.
			
	 * @param _startingTime 
				Time on which the COurseElementInstance will begin.
			
	 * @since Iteration2
	 */
	Timeslot(RelationManager manager, final boolean full, Day _day, java.sql.Time _startingTime) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._day = _day;
			this.ref_day = _day.id();
			this.fetched_day = true;
			this._startingTime = _startingTime;
			this.fetched_startingTime = true;
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
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newTimeslot} rather than {@link RelationManager#createTimeslot).
	 * @since Iteration2
	 */
	@Override
	public Timeslot create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Timeslot\"" + " (\"day\", \"startingTime\", \"timekey\")"
			+ " VALUES (?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_day);
			
				stmt.setTime(i++, _startingTime);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_day = false;
			changed_startingTime = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Timeslot\"" + " SET \"day\" = ?"
			+ ", \"startingTime\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_day);
			
				stmt.setTime(i++, _startingTime);
			
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
			changed_day = false;
			changed_startingTime = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Timeslot\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Timeslot entity) {
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
		if (entity instanceof Timeslot) {
			return compareTo((Timeslot) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Timeslot entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Timeslot) {
			return equals((Timeslot) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via ElementInstancePrefersTimeslot.
	 * <p>
	 * This is the object of ElementInstancePrefersTimeslot.
	 * @return The subject regarding ElementInstancePrefersTimeslot where this (the java-object you call subjectsOfElementInstancePrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public CourseElementInstance subjectOfElementInstancePrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseElementInstance> coll = subjectsOfElementInstancePrefersTimeslot();
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
	public java.util.List<CourseElementInstance> subjectsOfElementInstancePrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE \"id\" IN (SELECT \"room\" FROM "
			+ "\"scetris\".\"elementInstancePrefersTimeslot\"" + " WHERE \"timeslot\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
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
	public java.util.List<ElementInstancePrefersTimeslot> whereObjectOfElementInstancePrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE \"timeslot\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<ElementInstancePrefersTimeslot> ret = new java.util.ArrayList<ElementInstancePrefersTimeslot>();
			while (result.next()) {
				ret.add(new ElementInstancePrefersTimeslot(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonPrefersTimeslot.
	 * <p>
	 * This is the object of PersonPrefersTimeslot.
	 * @return The subject regarding PersonPrefersTimeslot where this (the java-object you call subjectsOfPersonPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonPrefersTimeslot();
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
	public java.util.List<Person> subjectsOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE \"id\" IN (SELECT \"user\" FROM "
			+ "\"scetris\".\"personPrefersTimeslot\"" + " WHERE \"timeslot\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
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
	public java.util.List<PersonPrefersTimeslot> whereObjectOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\""
			+ " WHERE \"timeslot\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonPrefersTimeslot> ret = new java.util.ArrayList<PersonPrefersTimeslot>();
			while (result.next()) {
				ret.add(new PersonPrefersTimeslot(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via RoomPrefersTimeslot.
	 * <p>
	 * This is the object of RoomPrefersTimeslot.
	 * @return The subject regarding RoomPrefersTimeslot where this (the java-object you call subjectsOfRoomPrefersTimeslot on) is the object.
	 * @since Iteration3
	 */
	public Room subjectOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Room> coll = subjectsOfRoomPrefersTimeslot();
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
	public java.util.List<Room> subjectsOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Room\""
			+ " WHERE \"id\" IN (SELECT \"room\" FROM "
			+ "\"scetris\".\"roomPrefersTimeslot\"" + " WHERE \"timeslot\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Room> ret = new java.util.ArrayList<Room>();
			while (result.next()) {
				ret.add(new Room(manager, result));
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
	public java.util.List<RoomPrefersTimeslot> whereObjectOfRoomPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"roomPrefersTimeslot\""
			+ " WHERE \"timeslot\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<RoomPrefersTimeslot> ret = new java.util.ArrayList<RoomPrefersTimeslot>();
			while (result.next()) {
				ret.add(new RoomPrefersTimeslot(manager, null, this, result));
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

	
	@de.fu.weave.xml.annotation.XmlElement("day")
	@de.fu.weave.xml.annotation.XmlDependency("isDayFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "day", serial = true, ref = Day.class)
	public Day getDay() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_day) {
			_day = manager.getDay(ref_day);
			fetched_day = true;
		}
		return _day;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isDayFetched() {
		return fetched_day;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDayChanged() {
		return changed_day;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("day")
	public int refDay() {
		return ref_day;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("startingTime")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "startingTime", serial = true)
	public java.sql.Time getStartingTime() {
		return _startingTime;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStartingTimeChanged() {
		return changed_startingTime;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>day</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDayChanged()} will return true. If you specify
	 * the same value as the old value isDayChanged() will return the same as it did
	 * before calling <code>setDay(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>day</code> is not nullable)
	 * @since Iteration2
	 */
	public Timeslot setDay(Day value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("day is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_day, value._id) == 0) return this;
		changed_day = true;
		ref_day = value._id;
		fetched_day = true;
		_day = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>startingTime</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStartingTimeChanged()} will return true. If you specify
	 * the same value as the old value isStartingTimeChanged() will return the same as it did
	 * before calling <code>setStartingTime(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>startingTime</code> is not nullable)
	 * @since Iteration2
	 */
	public Timeslot setStartingTime(java.sql.Time value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("startingTime is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_startingTime, value) == 0) return this;
		changed_startingTime = true;
		_startingTime = value;
		return this;
	}

}
