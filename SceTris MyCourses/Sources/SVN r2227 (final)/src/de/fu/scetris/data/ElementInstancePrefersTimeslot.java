/* ElementInstancePrefersTimeslot.java / 2011-03-03+01:00
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
 * OO-Representation of the relationship-relation ElementInstancePrefersTimeslot
 * <p>
 * 
			This relation describes that the given CourseElementInstance is better to be placed in the given Timeslot.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "elementInstancePrefersTimeslot", subject = CourseElementInstance.class, object = Timeslot.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"elementInstancePrefersTimeslot\"", requiredSqlCols = {"priority"})
@de.fu.weave.xml.annotation.XmlElement("elementInstancePrefersTimeslot")
public class ElementInstancePrefersTimeslot extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String ElementInstance = "element_instance";
	public static final String Timeslot = "timeslot";
	public static final String Priority = "priority";
	public static final String Timekey = "timekey";
	

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
		timekey(true);
		try {
			ref_elementInstance = result.getInt("element_instance");
			ref_timeslot = result.getInt("timeslot");
			_priority = result.getInt("priority");
			fetched_priority = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_timeslot;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	ElementInstancePrefersTimeslot(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_elementInstance = result.getInt("\"element_instance\"");
			this.ref_timeslot = result.getInt("\"timeslot\"");
			this._priority = result.getInt("\"priority\"");
			this.fetched_priority = true;
			this._timekey = result.getTimestamp("\"timekey\"");
			this.fetched_timekey = true;
			this.subject_refid = ref_elementInstance;
			this.object_refid = ref_timeslot;
			this.subject = manager.getCourseElementInstance(this.subject_refid);
			this.object = manager.getTimeslot(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	ElementInstancePrefersTimeslot(RelationManager manager, CourseElementInstance subject, Timeslot object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._elementInstance = this.subject = subject;
		this.ref_elementInstance = this.subject_refid = subject.id();
		this._timeslot = this.object = object;
		this.ref_timeslot = this.object_refid = object.id();
		timekey(true);
		
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
		timekey(true);
		
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
	public ElementInstancePrefersTimeslot create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + " (\"element_instance\", \"timeslot\","
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
		String query = "UPDATE " + "\"scetris\".\"elementInstancePrefersTimeslot\"" + " SET \"priority\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"element_instance\" = ?"
			+ " AND \"timeslot\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_timeslot);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"elementInstancePrefersTimeslot\"" + '#' + id());
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
		String query = "DELETE FROM " + "\"scetris\".\"elementInstancePrefersTimeslot\""
			+ " WHERE "
			+ " \"element_instance\" = ? AND "
			+ " \"timeslot\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_elementInstance);
			stmt.setInt(i++, ref_timeslot);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "element_instance", use = "subject", ref = CourseElementInstance.class)
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
	public ElementInstancePrefersTimeslot setElementInstance(CourseElementInstance value) {
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
	public ElementInstancePrefersTimeslot setTimeslot(Timeslot value) {
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
	public ElementInstancePrefersTimeslot setPriority(int value) {
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
