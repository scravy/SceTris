/* ElementInstanceTakesPlaceInRoom.java / 2010-11-27+01:00
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
 * OO-Representation of the relationship-relation ElementInstanceTakesPlaceInRoom
 * <p>
 * 
			This relation tells us that the mentioned CourseElementInstance is taking place in the given Room.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Relationship(name = "elementInstanceTakesPlaceInRoom", subject = CourseElementInstance.class, object = Room.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"elementInstanceTakesPlaceInRoom\"", requiredSqlCols = {"timeslot"})
@de.fu.weave.xml.annotation.XmlElement("elementInstanceTakesPlaceInRoom")
public class ElementInstanceTakesPlaceInRoom extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String ElementInstance = "element_instance";
	public static final String Room = "room";
	public static final String Timekey = "timekey";
	public static final String Timeslot = "timeslot";
	

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	CourseElementInstance _elementInstance;
	boolean changed_elementInstance = false;
	boolean fetched_elementInstance = false;
	int ref_elementInstance;
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
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	/**
	 * timeslot
	 * <p>
	 * 
				The Timeslot in which the Course Element Instance will take place in the given Room.
			
	 * @since Iteration2
	 */
	Timeslot _timeslot;
	boolean changed_timeslot = false;
	boolean fetched_timeslot = false;
	int ref_timeslot;
	
	
	CourseElementInstance subject;
	int subject_refid;
	Room object;
	int object_refid;
	
	
	ElementInstanceTakesPlaceInRoom(RelationManager manager, CourseElementInstance subject, Room object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_room = result.getInt("room");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_timeslot = result.getInt("timeslot");	
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_room;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	ElementInstanceTakesPlaceInRoom(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_room = result.getInt("room");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_timeslot = result.getInt("timeslot");
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"element_instance\"");
			this.object_refid = result.getInt("\"room\"");
			this.subject = manager.getCourseElementInstance(this.subject_refid);
			this.object = manager.getRoom(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	ElementInstanceTakesPlaceInRoom(RelationManager manager, CourseElementInstance subject, Room object, Timeslot _timeslot) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._elementInstance = this.subject = subject;
		this.ref_elementInstance = this.subject_refid = subject.id();
		this._room = this.object = object;
		this.ref_room = this.object_refid = object.id();
		timekey(true);
		
		this._timeslot = _timeslot;
		this.ref_timeslot = _timeslot.id();
		this.fetched_timeslot = true;
	}
	
	ElementInstanceTakesPlaceInRoom(RelationManager manager, boolean full, CourseElementInstance subject, Room object, Timeslot _timeslot) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._timeslot = _timeslot;
		this.ref_timeslot = _timeslot.id();
		this.fetched_timeslot = true;
	}
	
	@Override
	public CourseElementInstance subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElementInstance(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Room object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getRoom(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ElementInstanceTakesPlaceInRoom) {
			return equals((ElementInstanceTakesPlaceInRoom) obj);
		}
		return false;
	}
	
	public boolean equals(ElementInstanceTakesPlaceInRoom relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " (\"element_instance\", \"room\","
			+ "\"timeslot\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, ref_timeslot);
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + " SET \"timeslot\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"element_instance\" = ?"
			+ " AND \"room\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_timeslot);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_room);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"elementInstanceTakesPlaceInRoom\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementInstanceTakesPlaceInRoom\""
			+ " WHERE "
			+ " \"element_instance\" = ? AND "
			+ " \"room\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_room);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "element_instance", use = "subject", ref = CourseElementInstance.class)
	public CourseElementInstance getElementInstance() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_elementInstance) {
			_elementInstance = manager.getCourseElementInstance(ref_elementInstance);
			fetched_elementInstance = true;
		}
		return _elementInstance;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isElementInstanceFetched() {
		return fetched_elementInstance;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isElementInstanceChanged() {
		return changed_elementInstance;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("elementInstance")
	public int refElementInstance() {
		return ref_elementInstance;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("room")
	@de.fu.weave.xml.annotation.XmlDependency("isRoomFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "room", use = "object", ref = Room.class)
	public Room getRoom() throws de.fu.bakery.orm.java.DatabaseException {
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

	
	@de.fu.weave.xml.annotation.XmlElement("timeslot")
	@de.fu.weave.xml.annotation.XmlDependency("isTimeslotFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "timeslot", serial = true, ref = Timeslot.class)
	public Timeslot getTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
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
	

	
	
	
	/**
	 * Sets the value of <code>elementInstance</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isElementInstanceChanged()} will return true. If you specify
	 * the same value as the old value isElementInstanceChanged() will return the same as it did
	 * before calling <code>setElementInstance(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>elementInstance</code> is not nullable)
	 * @since Iteration2
	 */
	public void setElementInstance(CourseElementInstance value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("elementInstance is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_elementInstance, value._id) == 0) return;
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
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
	public void setRoom(Room value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("room is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_room, value._id) == 0) return;
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
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
	public void setTimeslot(Timeslot value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timeslot is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_timeslot, value._id) == 0) return;
		changed_timeslot = true;
		ref_timeslot = value._id;
		fetched_timeslot = true;
		_timeslot = value;
	}

}
