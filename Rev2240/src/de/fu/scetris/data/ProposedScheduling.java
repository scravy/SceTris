/* ProposedScheduling.java / 2011-03-03+01:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

/**
 * @formatter:off
 */
package de.fu.scetris.data;

/**
 * OO-Representation of the entity-relation ProposedScheduling
 * <p>
 * 
 */
@de.fu.weave.orm.annotation.Entity(name = "ProposedScheduling")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"ProposedScheduling\"", requiredSqlCols = {"priority", "element_instance"})
@de.fu.weave.xml.annotation.XmlElement("ProposedScheduling")
public class ProposedScheduling extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Priority = "priority";
	public static final String Ctime = "ctime";
	public static final String Mtime = "mtime";
	public static final String CreatedBy = "created_by";
	public static final String ModifiedBy = "modified_by";
	public static final String ElementInstance = "element_instance";
	public static final String Timeslot = "timeslot";
	public static final String Room = "room";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 4011167L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		@de.fu.weave.Form.Pos(2)
		@de.fu.weave.Form.Field("priority") @de.fu.weave.Form.Required
		@de.fu.weave.Form.Min(0)
		@de.fu.weave.Form.Max(100)
		@de.fu.weave.Form.ActiveValidator("priority$validator")
		@de.fu.weave.Form.ActiveConverter("priority$converter")
		public int priority;
		
		public java.sql.Timestamp ctime;
		
		public java.sql.Timestamp mtime;
		
		@de.fu.weave.Form.Alternatives("createdBy$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer createdBy;
		
		public java.util.Map<Integer,java.lang.String> createdBy$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Alternatives("modifiedBy$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer modifiedBy;
		
		public java.util.Map<Integer,java.lang.String> modifiedBy$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(7)
		@de.fu.weave.Form.Field("elementInstance") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("elementInstance$validator")
		@de.fu.weave.Form.ActiveConverter("elementInstance$converter")
		@de.fu.weave.Form.Alternatives("elementInstance$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int elementInstance;
		
		public java.util.Map<Integer,java.lang.String> elementInstance$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(8)
		@de.fu.weave.Form.Field("timeslot")
		@de.fu.weave.Form.ActiveValidator("timeslot$validator")
		@de.fu.weave.Form.ActiveConverter("timeslot$converter")
		@de.fu.weave.Form.Alternatives("timeslot$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer timeslot;
		
		public java.util.Map<Integer,java.lang.String> timeslot$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(9)
		@de.fu.weave.Form.Field("room")
		@de.fu.weave.Form.ActiveValidator("room$validator")
		@de.fu.weave.Form.ActiveConverter("room$converter")
		@de.fu.weave.Form.Alternatives("room$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer room;
		
		public java.util.Map<Integer,java.lang.String> room$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		
		public Form setValues(ProposedScheduling $object) {
			
			id = $object.getId();
			priority = $object.getPriority();
			ctime = $object.getCtime();
			mtime = $object.getMtime();
			createdBy = $object.refCreatedBy();
			modifiedBy = $object.refModifiedBy();
			elementInstance = $object.refElementInstance();
			timeslot = $object.refTimeslot();
			room = $object.refRoom();
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
	 * priority
	 * <p>
	 * 
	 */
	int _priority;
	boolean changed_priority = false;
	boolean fetched_priority = false;/**
	 * date of creation
	 * <p>
	 * 
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;/**
	 * date of last edit
	 * <p>
	 * 
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;/**
	 * initially created by
	 * <p>
	 * 
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;/**
	 * least recently modified by
	 * <p>
	 * 
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;/**
	 * course element instance
	 * <p>
	 * 
	 */
	CourseElementInstance _elementInstance;
	boolean changed_elementInstance = false;
	boolean fetched_elementInstance = false;
	int ref_elementInstance;/**
	 * timeslot
	 * <p>
	 * 
	 */
	Timeslot _timeslot = null;
	boolean changed_timeslot = false;
	boolean fetched_timeslot = false;
	Integer ref_timeslot;/**
	 * room
	 * <p>
	 * 
	 */
	Room _room = null;
	boolean changed_room = false;
	boolean fetched_room = false;
	Integer ref_room;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	ProposedScheduling(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
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
			if (result.wasNull()) {
				ref_createdBy = null;
			}
			ref_modifiedBy = result.getInt("modified_by");
			if (result.wasNull()) {
				ref_modifiedBy = null;
			}
			ref_elementInstance = result.getInt("element_instance");
			ref_timeslot = result.getInt("timeslot");
			if (result.wasNull()) {
				ref_timeslot = null;
			}
			ref_room = result.getInt("room");
			if (result.wasNull()) {
				ref_room = null;
			}
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity ProposedScheduling and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _priority 
	 * @param _elementInstance 
	 */
	ProposedScheduling(RelationManager manager, int _priority, CourseElementInstance _elementInstance) {
		timekey(true);
		this.manager = manager;
		this._priority = _priority;
		this.fetched_priority = true;
		this._elementInstance = _elementInstance;
		this.ref_elementInstance = _elementInstance.id();
		this.fetched_elementInstance = true;
	}
	
	/**
	 * Constructs the entity ProposedScheduling and initializes all attributes.
	 * @param _priority 
	 * @param _ctime 
	 * @param _createdBy 
	 * @param _modifiedBy 
	 * @param _elementInstance 
	 * @param _timeslot 
	 * @param _room 
	 */
	ProposedScheduling(RelationManager manager, final boolean full, int _priority, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, CourseElementInstance _elementInstance, Timeslot _timeslot, Room _room) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._priority = _priority;
			this.fetched_priority = true;
			this._ctime = _ctime;
			this.fetched_ctime = true;
			this._createdBy = _createdBy;
			this.ref_createdBy = _createdBy.id();
			this.fetched_createdBy = true;
			this._modifiedBy = _modifiedBy;
			this.ref_modifiedBy = _modifiedBy.id();
			this.fetched_modifiedBy = true;
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
	
	/*@Override
	public int hashCode() {
		return de.fu.weave.orm.GenericRelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newProposedScheduling} rather than {@link RelationManager#createProposedScheduling).
	 */
	@Override
	public ProposedScheduling create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"ProposedScheduling\"" + " (\"priority\", \"ctime\", \"created_by\", \"modified_by\", \"element_instance\", \"timeslot\", \"room\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, _ctime = new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_modifiedBy != null) {
				stmt.setInt(i++, ref_modifiedBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, ref_elementInstance);
			
			if (ref_timeslot != null) {
				stmt.setInt(i++, ref_timeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_room != null) {
				stmt.setInt(i++, ref_room);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			stmt.setTimestamp(i++, _mtime);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_priority = false;
			changed_ctime = false;
			changed_createdBy = false;
			changed_modifiedBy = false;
			changed_elementInstance = false;
			changed_timeslot = false;
			changed_room = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_mtime = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"ProposedScheduling\"" + " SET \"priority\" = ?"
			+ ", \"ctime\" = ?"
			+ ", \"created_by\" = ?"
			+ ", \"modified_by\" = ?"
			+ ", \"element_instance\" = ?"
			+ ", \"timeslot\" = ?"
			+ ", \"room\" = ?"
			+ ", \"mtime\" = ? "
			+ "WHERE \"mtime\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_modifiedBy != null) {
				stmt.setInt(i++, ref_modifiedBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setInt(i++, ref_elementInstance);
			
			if (ref_timeslot != null) {
				stmt.setInt(i++, ref_timeslot);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_room != null) {
				stmt.setInt(i++, ref_room);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
			}
			_mtime = newTimekey;
			changed_priority = false;
			changed_ctime = false;
			changed_createdBy = false;
			changed_modifiedBy = false;
			changed_elementInstance = false;
			changed_timeslot = false;
			changed_room = false;
			
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
	 *
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"ProposedScheduling\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(ProposedScheduling entity) {
		if (!exists) {
			if (this == entity) return 0;
			if (this.hashCode() <= entity.hashCode()) return -7;
			return 7;
		}
		if (entity == null) return -4711;
		if (equals(entity)) {
			return 0;
		}
		return de.fu.weave.orm.GenericRelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof ProposedScheduling) {
			return compareTo((ProposedScheduling) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(ProposedScheduling entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
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
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
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
	@de.fu.weave.orm.annotation.Attribute(name = "ctime", serial = true, hidden = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "mtime", serial = true, hidden = true, use = "timestamp")
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
	
	public java.sql.Timestamp timekey() {
		return _mtime;
	}
	
	public boolean isValidTimekey(java.sql.Timestamp key) {
		return de.fu.weave.orm.GenericRelationManager.compareValues(_mtime, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlElement("createdBy")
	@de.fu.weave.xml.annotation.XmlDependency("isCreatedByFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "created_by", serial = true, nullable = true, hidden = true, ref = Person.class)
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
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCreatedByFetched() {
		return fetched_createdBy;
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("modifiedBy")
	@de.fu.weave.xml.annotation.XmlDependency("isModifiedByFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "modified_by", serial = true, nullable = true, hidden = true, ref = Person.class)
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
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isModifiedByFetched() {
		return fetched_modifiedBy;
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("timeslot")
	@de.fu.weave.xml.annotation.XmlDependency("isTimeslotFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "timeslot", serial = true, nullable = true, ref = Timeslot.class)
	public Timeslot getTimeslot() throws de.fu.weave.orm.DatabaseException {
		if (ref_timeslot == null) {
			return null;
		}
		if (!fetched_timeslot) {
			_timeslot = manager.getTimeslot(ref_timeslot);
			fetched_timeslot = true;
		}
		return _timeslot;
	}
	
	public boolean hasTimeslot() {
		return _timeslot != null;
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
	public Integer refTimeslot() {
		return ref_timeslot;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("room")
	@de.fu.weave.xml.annotation.XmlDependency("isRoomFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "room", serial = true, nullable = true, ref = Room.class)
	public Room getRoom() throws de.fu.weave.orm.DatabaseException {
		if (ref_room == null) {
			return null;
		}
		if (!fetched_room) {
			_room = manager.getRoom(ref_room);
			fetched_room = true;
		}
		return _room;
	}
	
	public boolean hasRoom() {
		return _room != null;
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
	public Integer refRoom() {
		return ref_room;
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
	public ProposedScheduling setPriority(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("priority is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_priority, value) == 0) return this;
		changed_priority = true;
		_priority = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>ctime</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCtimeChanged()} will return true. If you specify
	 * the same value as the old value isCtimeChanged() will return the same as it did
	 * before calling <code>setCtime(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>ctime</code> is not nullable)
	 * @since Iteration2
	 */
	public ProposedScheduling setCtime(java.sql.Timestamp value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_ctime, value) == 0) return this;
		changed_ctime = true;
		_ctime = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_mtime = new java.sql.Timestamp(System.currentTimeMillis());
		return _mtime;
	}
	
	

	
	/**
	 * Sets the value of <code>createdBy</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCreatedByChanged()} will return true. If you specify
	 * the same value as the old value isCreatedByChanged() will return the same as it did
	 * before calling <code>setCreatedBy(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public ProposedScheduling setCreatedBy(Person value) {
		
		if (value == null) {
			clearCreatedBy();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_createdBy, value._id) == 0) return this;
		changed_createdBy = true;
		ref_createdBy = value._id;
		fetched_createdBy = true;
		_createdBy = value;
		return this;
	}
	
	/**
	 * createdBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public ProposedScheduling clearCreatedBy() {
		if (_createdBy == null && ref_createdBy == null) {
			return this;
		}
		_createdBy = null;
		ref_createdBy = null;
		changed_createdBy = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>modifiedBy</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isModifiedByChanged()} will return true. If you specify
	 * the same value as the old value isModifiedByChanged() will return the same as it did
	 * before calling <code>setModifiedBy(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public ProposedScheduling setModifiedBy(Person value) {
		
		if (value == null) {
			clearModifiedBy();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_modifiedBy, value._id) == 0) return this;
		changed_modifiedBy = true;
		ref_modifiedBy = value._id;
		fetched_modifiedBy = true;
		_modifiedBy = value;
		return this;
	}
	
	/**
	 * modifiedBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public ProposedScheduling clearModifiedBy() {
		if (_modifiedBy == null && ref_modifiedBy == null) {
			return this;
		}
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
		return this;
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
	public ProposedScheduling setElementInstance(CourseElementInstance value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("elementInstance is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_elementInstance, value._id) == 0) return this;
		changed_elementInstance = true;
		ref_elementInstance = value._id;
		fetched_elementInstance = true;
		_elementInstance = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>timeslot</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTimeslotChanged()} will return true. If you specify
	 * the same value as the old value isTimeslotChanged() will return the same as it did
	 * before calling <code>setTimeslot(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public ProposedScheduling setTimeslot(Timeslot value) {
		
		if (value == null) {
			clearTimeslot();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_timeslot, value._id) == 0) return this;
		changed_timeslot = true;
		ref_timeslot = value._id;
		fetched_timeslot = true;
		_timeslot = value;
		return this;
	}
	
	/**
	 * timeslot may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public ProposedScheduling clearTimeslot() {
		if (_timeslot == null && ref_timeslot == null) {
			return this;
		}
		_timeslot = null;
		ref_timeslot = null;
		changed_timeslot = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>room</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRoomChanged()} will return true. If you specify
	 * the same value as the old value isRoomChanged() will return the same as it did
	 * before calling <code>setRoom(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public ProposedScheduling setRoom(Room value) {
		
		if (value == null) {
			clearRoom();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_room, value._id) == 0) return this;
		changed_room = true;
		ref_room = value._id;
		fetched_room = true;
		_room = value;
		return this;
	}
	
	/**
	 * room may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public ProposedScheduling clearRoom() {
		if (_room == null && ref_room == null) {
			return this;
		}
		_room = null;
		ref_room = null;
		changed_room = true;
		return this;
	}

}
