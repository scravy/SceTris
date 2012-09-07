/* RoomPrefersTimeslot.java / 2011-02-01+01:00
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
 * OO-Representation of the relationship-relation RoomPrefersTimeslot
 * <p>
 * 
			This relation describes that the given Room should be booked at the given Timeslot.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roomPrefersTimeslot", subject = Room.class, object = Timeslot.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"roomPrefersTimeslot\"", requiredSqlCols = {"priority"})
@de.fu.weave.xml.annotation.XmlElement("roomPrefersTimeslot")
public class RoomPrefersTimeslot extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Room = "room";
	public static final String Timeslot = "timeslot";
	public static final String Priority = "priority";
	public static final String Timekey = "timekey";
	

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Room _room;
	boolean changed_room = false;
	boolean fetched_room = false;
	int ref_room;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Timeslot _timeslot;
	boolean changed_timeslot = false;
	boolean fetched_timeslot = false;
	int ref_timeslot;
	/**
	 * priority
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _priority;
	boolean changed_priority = false;
	boolean fetched_priority = false;
	
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	
	
	Room subject;
	int subject_refid;
	Timeslot object;
	int object_refid;
	
	
	RoomPrefersTimeslot(RelationManager manager, Room subject, Timeslot object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_room = result.getInt("room");
			ref_timeslot = result.getInt("timeslot");
			_priority = result.getInt("priority");
			fetched_priority = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = ref_room;
			this.object_refid = ref_timeslot;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	RoomPrefersTimeslot(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_room = result.getInt("room");
			ref_timeslot = result.getInt("timeslot");
			_priority = result.getInt("priority");
			fetched_priority = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"room\"");
			this.object_refid = result.getInt("\"timeslot\"");
			this.subject = manager.getRoom(this.subject_refid);
			this.object = manager.getTimeslot(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	RoomPrefersTimeslot(RelationManager manager, Room subject, Timeslot object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._room = this.subject = subject;
		this.ref_room = this.subject_refid = subject.id();
		this._timeslot = this.object = object;
		this.ref_timeslot = this.object_refid = object.id();
		timekey(true);
		
		this._priority = _priority;
		this.fetched_priority = true;
	}
	
	RoomPrefersTimeslot(RelationManager manager, boolean full, Room subject, Timeslot object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._priority = _priority;
		this.fetched_priority = true;
	}
	
	@Override
	public Room subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getRoom(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Timeslot object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getTimeslot(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoomPrefersTimeslot) {
			return equals((RoomPrefersTimeslot) obj);
		}
		return false;
	}
	
	public boolean equals(RoomPrefersTimeslot relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public RoomPrefersTimeslot create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roomPrefersTimeslot\"" + " (\"room\", \"timeslot\","
			+ "\"priority\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, _priority);
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"roomPrefersTimeslot\"" + " SET \"priority\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"room\" = ?"
			+ " AND \"timeslot\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_room);
			stmt.setInt(i++, ref_timeslot);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"roomPrefersTimeslot\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"roomPrefersTimeslot\""
			+ " WHERE "
			+ " \"room\" = ? AND "
			+ " \"timeslot\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_room);
			stmt.setInt(i++, ref_timeslot);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("room")
	@de.fu.weave.xml.annotation.XmlDependency("isRoomFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "room", use = "subject", ref = Room.class)
	public Room getRoom() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_room) {
			_room = manager.getRoom(ref_room);
			fetched_room = true;
		}
		return _room;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isRoomFetched() {
		return fetched_room;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isRoomChanged() {
		return changed_room;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("room")
	public int refRoom() {
		return ref_room;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("timeslot")
	@de.fu.weave.xml.annotation.XmlDependency("isTimeslotFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "timeslot", use = "object", ref = Timeslot.class)
	public Timeslot getTimeslot() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_timeslot) {
			_timeslot = manager.getTimeslot(ref_timeslot);
			fetched_timeslot = true;
		}
		return _timeslot;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isTimeslotFetched() {
		return fetched_timeslot;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTimeslotChanged() {
		return changed_timeslot;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("timeslot")
	public int refTimeslot() {
		return ref_timeslot;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("priority")
	@de.fu.weave.orm.annotation.Attribute(name = "priority", serial = true)
	public int getPriority() {
		return _priority;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isPriorityChanged() {
		return changed_priority;
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
	 * Sets the value of <code>room</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRoomChanged()} will return true. If you specify
	 * the same value as the old value isRoomChanged() will return the same as it did
	 * before calling <code>setRoom(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>room</code> is not nullable)
	 * @since Iteration2
	 */
	public RoomPrefersTimeslot setRoom(Room value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("room is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_room, value._id) == 0) return this;
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>timeslot</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTimeslotChanged()} will return true. If you specify
	 * the same value as the old value isTimeslotChanged() will return the same as it did
	 * before calling <code>setTimeslot(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>timeslot</code> is not nullable)
	 * @since Iteration2
	 */
	public RoomPrefersTimeslot setTimeslot(Timeslot value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timeslot is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_timeslot, value._id) == 0) return this;
		changed_timeslot = true;
		ref_timeslot = value._id;
		fetched_timeslot = true;
		_timeslot = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>priority</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPriorityChanged()} will return true. If you specify
	 * the same value as the old value isPriorityChanged() will return the same as it did
	 * before calling <code>setPriority(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>priority</code> is not nullable)
	 * @since Iteration2
	 */
	public RoomPrefersTimeslot setPriority(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("priority is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_priority, value) == 0) return this;
		changed_priority = true;
		_priority = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

}
