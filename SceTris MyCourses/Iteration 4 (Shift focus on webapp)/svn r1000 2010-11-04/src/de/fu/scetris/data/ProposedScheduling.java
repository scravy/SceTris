/* ProposedScheduling.java / 2010-11-04+01:00
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
 * OO-Representation of the entity-relation ProposedScheduling
 * <p>
 * 
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "ProposedScheduling")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"ProposedScheduling\"")
@de.fu.weave.xml.annotation.XmlElement("ProposedScheduling")
public class ProposedScheduling extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	 * priority
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _priority;
	boolean changed_priority = false;
	boolean fetched_priority = false;/**
	 * date of creation
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;/**
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;/**
	 * initially created by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;/**
	 * least recently modified by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;/**
	 * course element instance
	 * <p>
	 * 
	 * @since Iteration2
	 */
	CourseElementInstance _elementInstance;
	boolean changed_elementInstance = false;
	boolean fetched_elementInstance = false;
	int ref_elementInstance;/**
	 * timeslot
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Timeslot _timeslot;
	boolean changed_timeslot = false;
	boolean fetched_timeslot = false;
	int ref_timeslot;/**
	 * room
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Room _room;
	boolean changed_room = false;
	boolean fetched_room = false;
	int ref_room;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	ProposedScheduling(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_priority = result.getInt("priority");
			fetched_priority = true;
			_ctime = result.getTimestamp("ctime");
			fetched_ctime = true;
			_mtime = result.getTimestamp("mtime");
			fetched_mtime = true;
			ref_createdBy = result.getInt("created_by");
			ref_modifiedBy = result.getInt("modified_by");
			ref_elementInstance = result.getInt("element_instance");
			ref_timeslot = result.getInt("timeslot");
			ref_room = result.getInt("room");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity ProposedScheduling and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _priority 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 * @since Iteration2
	 */
	ProposedScheduling(RelationManager manager, int _priority, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) {
		this.manager = manager;
		this._priority = _priority;
		this.fetched_priority = true;
		this._elementInstance = _elementInstance;
		this.ref_elementInstance = _elementInstance.id();
		this.fetched_elementInstance = true;
		this._timeslot = _timeslot;
		this.ref_timeslot = _timeslot.id();
		this.fetched_timeslot = true;
		this._room = _room;
		this.ref_room = _room.id();
		this.fetched_room = true;
	}
	
	/**
	 * Constructs the entity ProposedScheduling and initializes all attributes.
	 * @param _priority 
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 * @since Iteration2
	 */
	ProposedScheduling(RelationManager manager, final boolean full, int _priority, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._priority = _priority;
			this.fetched_priority = true;
			this._elementInstance = _elementInstance;
			this.ref_elementInstance = _elementInstance.id();
			this.fetched_elementInstance = true;
			this._timeslot = _timeslot;
			this.ref_timeslot = _timeslot.id();
			this.fetched_timeslot = true;
			this._room = _room;
			this.ref_room = _room.id();
			this.fetched_room = true;
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
		return "ProposedScheduling: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newProposedScheduling} rather than {@link RelationManager#createProposedScheduling).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"ProposedScheduling\"" + " (\"priority\" , \"ctime\" , \"mtime\" , \"created_by\" , \"element_instance\" , \"timeslot\" , \"room\" )"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (manager.isNull(_mtime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _mtime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, ref_elementInstance);
			
				stmt.setInt(i++, ref_timeslot);
			
				stmt.setInt(i++, ref_room);
			
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
		String query = "UPDATE " + "\"scetris\".\"ProposedScheduling\"" + " SET \"priority\" = ?, \"ctime\" = ?, \"mtime\" = ?, \"created_by\" = ?, \"element_instance\" = ?, \"timeslot\" = ?, \"room\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (manager.isNull(_mtime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _mtime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, ref_elementInstance);
			
				stmt.setInt(i++, ref_timeslot);
			
				stmt.setInt(i++, ref_room);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"ProposedScheduling\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(ProposedScheduling entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof ProposedScheduling) {
			return compareTo((ProposedScheduling) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(ProposedScheduling entity) {
		if (entity == null) return false;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof ProposedScheduling) {
			return equals((ProposedScheduling) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true)
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	@de.fu.weave.orm.annotation.Attribute(name = "ctime", serial = true)
	public java.sql.Timestamp getCtime() {
		return _ctime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCtimeChanged() {
		return changed_ctime;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("mtime")
	@de.fu.weave.orm.annotation.Attribute(name = "mtime", serial = true)
	public java.sql.Timestamp getMtime() {
		return _mtime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isMtimeChanged() {
		return changed_mtime;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "created_by", serial = true, nullable = true, ref = Person.class)
	public Person getCreatedBy() throws de.fu.weave.orm.DatabaseException {
		if (ref_createdBy == null) {
			return null;
		}
		if (!fetched_createdBy) {
			_createdBy = manager.getPerson(ref_createdBy);
			fetched_createdBy = true;
		}
		return _createdBy;
	}
	
	public boolean hasCreatedBy() {
		return _createdBy != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCreatedByChanged() {
		return changed_createdBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("createdBy")
	public Integer refCreatedBy() {
		return ref_createdBy;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "modified_by", serial = true, nullable = true, ref = Person.class)
	public Person getModifiedBy() throws de.fu.weave.orm.DatabaseException {
		if (ref_modifiedBy == null) {
			return null;
		}
		if (!fetched_modifiedBy) {
			_modifiedBy = manager.getPerson(ref_modifiedBy);
			fetched_modifiedBy = true;
		}
		return _modifiedBy;
	}
	
	public boolean hasModifiedBy() {
		return _modifiedBy != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isModifiedByChanged() {
		return changed_modifiedBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("modifiedBy")
	public Integer refModifiedBy() {
		return ref_modifiedBy;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "element_instance", serial = true, ref = CourseElementInstance.class)
	public CourseElementInstance getElementInstance() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_elementInstance) {
			_elementInstance = manager.getCourseElementInstance(ref_elementInstance);
			fetched_elementInstance = true;
		}
		return _elementInstance;
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "timeslot", serial = true, ref = Timeslot.class)
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "room", serial = true, ref = Room.class)
	public Room getRoom() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_room) {
			_room = manager.getRoom(ref_room);
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
	public void setCtime(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		changed_ctime = true;
		_ctime = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setMtime(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("mtime is not nullable, null given");
		}
		changed_mtime = true;
		_mtime = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCreatedBy(Person value) {
		if (value == null) {
			_createdBy = null;
			ref_createdBy = null;
			return;
		}
		
		changed_createdBy = true;
		ref_createdBy = value._id;
		fetched_createdBy = true;
		_createdBy = value;
	}
	
	/**
	 * createdBy may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearCreatedBy() {
		_createdBy = null;
		ref_createdBy = null;
		changed_createdBy = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setModifiedBy(Person value) {
		if (value == null) {
			_modifiedBy = null;
			ref_modifiedBy = null;
			return;
		}
		
		changed_modifiedBy = true;
		ref_modifiedBy = value._id;
		fetched_modifiedBy = true;
		_modifiedBy = value;
	}
	
	/**
	 * modifiedBy may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearModifiedBy() {
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setElementInstance(CourseElementInstance value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("elementInstance is not nullable, null given");
		}
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
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
	public void setRoom(Room value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("room is not nullable, null given");
		}
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
	}

}
