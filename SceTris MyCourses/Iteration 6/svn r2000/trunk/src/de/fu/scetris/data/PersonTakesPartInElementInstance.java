/* PersonTakesPartInElementInstance.java / 2011-02-01+01:00
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
 * OO-Representation of the relationship-relation PersonTakesPartInElementInstance
 * <p>
 * 
			This relation tells us that the given Person takes part in the mentioned CourseElementInstance.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personTakesPartInElementInstance", subject = Person.class, object = CourseElementInstance.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"personTakesPartInElementInstance\"", requiredSqlCols = {"timeslot"})
@de.fu.weave.xml.annotation.XmlElement("personTakesPartInElementInstance")
public class PersonTakesPartInElementInstance extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String User = "user";
	public static final String ElementInstance = "element_instance";
	public static final String Timekey = "timekey";
	public static final String Timeslot = "timeslot";
	

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _user;
	boolean changed_user = false;
	boolean fetched_user = false;
	int ref_user;
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
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	/**
	 * timeslot
	 * <p>
	 * 
				The Timeslot in which the Person will take part in the given Course Element Instance
			
	 * @since Iteration2
	 */
	Timeslot _timeslot;
	boolean changed_timeslot = false;
	boolean fetched_timeslot = false;
	int ref_timeslot;
	
	
	Person subject;
	int subject_refid;
	CourseElementInstance object;
	int object_refid;
	
	
	PersonTakesPartInElementInstance(RelationManager manager, Person subject, CourseElementInstance object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_elementInstance = result.getInt("element_instance");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_timeslot = result.getInt("timeslot");	
			this.subject_refid = ref_user;
			this.object_refid = ref_elementInstance;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	PersonTakesPartInElementInstance(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_elementInstance = result.getInt("element_instance");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_timeslot = result.getInt("timeslot");
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"user\"");
			this.object_refid = result.getInt("\"element_instance\"");
			this.subject = manager.getPerson(this.subject_refid);
			this.object = manager.getCourseElementInstance(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	PersonTakesPartInElementInstance(RelationManager manager, Person subject, CourseElementInstance object, Timeslot _timeslot) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._user = this.subject = subject;
		this.ref_user = this.subject_refid = subject.id();
		this._elementInstance = this.object = object;
		this.ref_elementInstance = this.object_refid = object.id();
		timekey(true);
		
		this._timeslot = _timeslot;
		this.ref_timeslot = _timeslot.id();
		this.fetched_timeslot = true;
	}
	
	PersonTakesPartInElementInstance(RelationManager manager, boolean full, Person subject, CourseElementInstance object, Timeslot _timeslot) {
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
	public Person subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public CourseElementInstance object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getCourseElementInstance(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonTakesPartInElementInstance) {
			return equals((PersonTakesPartInElementInstance) obj);
		}
		return false;
	}
	
	public boolean equals(PersonTakesPartInElementInstance relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public PersonTakesPartInElementInstance create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personTakesPartInElementInstance\"" + " (\"user\", \"element_instance\","
			+ "\"timeslot\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, ref_timeslot);
			
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
		String query = "UPDATE " + "\"scetris\".\"personTakesPartInElementInstance\"" + " SET \"timeslot\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"user\" = ?"
			+ " AND \"element_instance\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_timeslot);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_elementInstance);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"personTakesPartInElementInstance\"" + '#' + id());
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
		String query = "DELETE FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE "
			+ " \"user\" = ? AND "
			+ " \"element_instance\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_elementInstance);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("user")
	@de.fu.weave.xml.annotation.XmlDependency("isUserFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "user", use = "subject", ref = Person.class)
	public Person getUser() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_user) {
			_user = manager.getPerson(ref_user);
			fetched_user = true;
		}
		return _user;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isUserFetched() {
		return fetched_user;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isUserChanged() {
		return changed_user;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("user")
	public int refUser() {
		return ref_user;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("elementInstance")
	@de.fu.weave.xml.annotation.XmlDependency("isElementInstanceFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "element_instance", use = "object", ref = CourseElementInstance.class)
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

	
	@de.fu.weave.xml.annotation.XmlElement("timeslot")
	@de.fu.weave.xml.annotation.XmlDependency("isTimeslotFetched")
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
	 * Sets the value of <code>user</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isUserChanged()} will return true. If you specify
	 * the same value as the old value isUserChanged() will return the same as it did
	 * before calling <code>setUser(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>user</code> is not nullable)
	 * @since Iteration2
	 */
	public PersonTakesPartInElementInstance setUser(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("user is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_user, value._id) == 0) return this;
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
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
	public PersonTakesPartInElementInstance setElementInstance(CourseElementInstance value) {
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
	public PersonTakesPartInElementInstance setTimeslot(Timeslot value) {
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

}
