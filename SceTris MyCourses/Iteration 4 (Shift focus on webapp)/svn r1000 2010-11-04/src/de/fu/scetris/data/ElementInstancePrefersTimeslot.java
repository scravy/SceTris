/* ElementInstancePrefersTimeslot.java / 2010-11-04+01:00
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
 * OO-Representation of the relationship-relation ElementInstancePrefersTimeslot
 * <p>
 * 
			This relation describes that the given CourseElementInstance is better to be placed in the given Timeslot.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "elementInstancePrefersTimeslot", subject = CourseElementInstance.class, object = Timeslot.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"elementInstancePrefersTimeslot\"")
@de.fu.weave.xml.annotation.XmlElement("elementInstancePrefersTimeslot")
public class ElementInstancePrefersTimeslot extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	CourseElementInstance _room;
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
	
	
	
	CourseElementInstance subject;
	int subject_refid;
	Timeslot object;
	int object_refid;
	
	
	ElementInstancePrefersTimeslot(RelationManager manager, CourseElementInstance subject, Timeslot object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_room = result.getInt("room");
			ref_timeslot = result.getInt("timeslot");
			_priority = result.getInt("priority");
			fetched_priority = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	ElementInstancePrefersTimeslot(RelationManager manager, CourseElementInstance subject, Timeslot object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
		this._priority = _priority;
		this.fetched_priority = true;
	}
	
	ElementInstancePrefersTimeslot(RelationManager manager, boolean full, CourseElementInstance subject, Timeslot object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._priority = _priority;
		this.fetched_priority = true;
	}
	
	@Override
	public CourseElementInstance subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getCourseElementInstance(subject_refid);
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
	
	@Deprecated
	public CourseElementInstance getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Timeslot getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof ElementInstancePrefersTimeslot) {
			return equals((ElementInstancePrefersTimeslot) obj);
		}
		return false;
	}
	
	public boolean equals(ElementInstancePrefersTimeslot relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + " (\"room\", \"timeslot\", \"priority\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, _priority);
			
			
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
	
		String query = "UPDATE " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + " SET \"priority\" = ? WHERE "
			+ " \"room\" = ?  AND "
			+ " \"timeslot\" = ? ";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setInt(++i, _priority);
			stmt.setInt(++i, ref_room);
			stmt.setInt(++i, ref_timeslot);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE "
			+ " \"room\" = ? AND "
			+ " \"timeslot\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_room);
			stmt.setInt(++i, ref_timeslot);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	@de.fu.weave.orm.annotation.Attribute(name = "room", ref = CourseElementInstance.class)
	public CourseElementInstance getRoom() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_room) {
			_room = manager.getCourseElementInstance(ref_room);
			fetched_room = true;
		}
		return _room;
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "timeslot", ref = Timeslot.class)
	public Timeslot getTimeslot() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_timeslot) {
			_timeslot = manager.getTimeslot(ref_timeslot);
			fetched_timeslot = true;
		}
		return _timeslot;
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
	@de.fu.weave.orm.annotation.Attribute(name = "timekey", serial = true)
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
	public void setRoom(CourseElementInstance value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("room is not nullable, null given");
		}
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimeslot(Timeslot value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timeslot is not nullable, null given");
		}
		changed_timeslot = true;
		ref_timeslot = value._id;
		fetched_timeslot = true;
		_timeslot = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPriority(int value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("priority is not nullable, null given");
		}
		changed_priority = true;
		_priority = value;
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
