/* AcademicTerm.java / 2010-11-27+01:00
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
 * OO-Representation of the entity-relation AcademicTerm
 * <p>
 * 
			All Programs happen in a Period. A Period is has a start and end date. No start date of a Program may be set before the periods start Program and no end date of Program may be set after the Periods end date. One can say: A period is the program of the university, where the university consists of all departments.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "AcademicTerm")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"AcademicTerm\"", requiredSqlCols = {"name", "start", "end", "start_lessons", "end_lessons"})
@de.fu.weave.xml.annotation.XmlElement("AcademicTerm")
public class AcademicTerm extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Timekey = "timekey";
	public static final String Start = "start";
	public static final String End = "end";
	public static final String StartLessons = "start_lessons";
	public static final String EndLessons = "end_lessons";
	

	/**
	 * @since Iteration4
	 */
	public static class Stub {
		Stub() {
		}
	
		public int Id;public String Name;public java.sql.Timestamp Timekey;public java.sql.Date Start;public java.sql.Date End;public java.sql.Date StartLessons;public java.sql.Date EndLessons;
	}
	
	/**
	 *
	 */
	interface Message {
	}
	
	/**
	 * @since Iteration4
	 */
	public static Stub makeStub() {
		return new Stub();
	}
	
	
	/**
	 * @since Iteration4
	 */
	public Stub asStub() {
		Stub stub = makeStub();
		stub.Id = _id;
		stub.Name = _name;
		stub.Timekey = _timekey;
		stub.Start = _start;
		stub.End = _end;
		stub.StartLessons = _startLessons;
		stub.EndLessons = _endLessons;
		
		return stub;
	}
	
	/**
	 * @since Iteration4
	 */
	public Message asMessage() {
		return new Message() {
		};
	}

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
	 * unique name
	 * <p>
	 * 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	String _name;
	boolean changed_name = false;
	boolean fetched_name = false;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * start date
	 * <p>
	 * 
				The date at which the Academic turn begins.
			
	 * @since Iteration2
	 */
	java.sql.Date _start;
	boolean changed_start = false;
	boolean fetched_start = false;/**
	 * end date
	 * <p>
	 * 
				The date at which the Academic turn ends.
			
	 * @since Iteration2
	 */
	java.sql.Date _end;
	boolean changed_end = false;
	boolean fetched_end = false;/**
	 * courses starting date
	 * <p>
	 * 
				The date of the first Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	java.sql.Date _startLessons;
	boolean changed_startLessons = false;
	boolean fetched_startLessons = false;/**
	 * courses ending date
	 * <p>
	 * 
				The date of the last Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	java.sql.Date _endLessons;
	boolean changed_endLessons = false;
	boolean fetched_endLessons = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_start = result.getDate("start");
			fetched_start = true;
			_end = result.getDate("end");
			fetched_end = true;
			_startLessons = result.getDate("start_lessons");
			fetched_startLessons = true;
			_endLessons = result.getDate("end_lessons");
			fetched_endLessons = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity AcademicTerm and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _start 
				The date at which the Academic turn begins.
			
	 * @param _end 
				The date at which the Academic turn ends.
			
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
		this._start = _start;
		this.fetched_start = true;
		this._end = _end;
		this.fetched_end = true;
		this._startLessons = _startLessons;
		this.fetched_startLessons = true;
		this._endLessons = _endLessons;
		this.fetched_endLessons = true;
	}
	
	/**
	 * Constructs the entity AcademicTerm and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @param _start 
				The date at which the Academic turn begins.
			
	 * @param _end 
				The date at which the Academic turn ends.
			
	 * @param _startLessons 
				The date of the first Course Element Instance taking place.
			
	 * @param _endLessons 
				The date of the last Course Element Instance taking place.
			
	 * @since Iteration2
	 */
	AcademicTerm(RelationManager manager, final boolean full, String _name, java.sql.Date _start, java.sql.Date _end, java.sql.Date _startLessons, java.sql.Date _endLessons) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
			this._start = _start;
			this.fetched_start = true;
			this._end = _end;
			this.fetched_end = true;
			this._startLessons = _startLessons;
			this.fetched_startLessons = true;
			this._endLessons = _endLessons;
			this.fetched_endLessons = true;
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
		return "AcademicTerm: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newAcademicTerm} rather than {@link RelationManager#createAcademicTerm).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"AcademicTerm\"" + " (\"name\", \"start\", \"end\", \"start_lessons\", \"end_lessons\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setDate(i++, _startLessons);
			
				stmt.setDate(i++, _endLessons);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			changed_start = false;
			changed_end = false;
			changed_startLessons = false;
			changed_endLessons = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
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
		String query = "UPDATE " + "\"scetris\".\"AcademicTerm\"" + " SET \"name\" = ?"
			+ ", \"start\" = ?"
			+ ", \"end\" = ?"
			+ ", \"start_lessons\" = ?"
			+ ", \"end_lessons\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setDate(i++, _startLessons);
			
				stmt.setDate(i++, _endLessons);
			
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
			changed_name = false;
			changed_start = false;
			changed_end = false;
			changed_startLessons = false;
			changed_endLessons = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"AcademicTerm\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(AcademicTerm entity) {
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
		if (entity instanceof AcademicTerm) {
			return compareTo((AcademicTerm) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(AcademicTerm entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof AcademicTerm) {
			return equals((AcademicTerm) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "name", serial = true, use = "title")
	public String getName() {
		return _name;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNameChanged() {
		return changed_name;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("start")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "start", serial = true)
	public java.sql.Date getStart() {
		return _start;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStartChanged() {
		return changed_start;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("end")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "end", serial = true)
	public java.sql.Date getEnd() {
		return _end;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isEndChanged() {
		return changed_end;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("startLessons")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "start_lessons", serial = true)
	public java.sql.Date getStartLessons() {
		return _startLessons;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStartLessonsChanged() {
		return changed_startLessons;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("endLessons")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "end_lessons", serial = true)
	public java.sql.Date getEndLessons() {
		return _endLessons;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isEndLessonsChanged() {
		return changed_endLessons;
	}
	

	
	
	

	
	/**
	 * Sets the value of <code>name</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNameChanged()} will return true. If you specify
	 * the same value as the old value isNameChanged() will return the same as it did
	 * before calling <code>setName(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>name</code> is not nullable)
	 * @since Iteration2
	 */
	public void setName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("name is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_name, value) == 0) return;
		changed_name = true;
		_name = value;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>start</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStartChanged()} will return true. If you specify
	 * the same value as the old value isStartChanged() will return the same as it did
	 * before calling <code>setStart(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>start</code> is not nullable)
	 * @since Iteration2
	 */
	public void setStart(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("start is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_start, value) == 0) return;
		changed_start = true;
		_start = value;
	}

	
	/**
	 * Sets the value of <code>end</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isEndChanged()} will return true. If you specify
	 * the same value as the old value isEndChanged() will return the same as it did
	 * before calling <code>setEnd(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>end</code> is not nullable)
	 * @since Iteration2
	 */
	public void setEnd(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("end is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_end, value) == 0) return;
		changed_end = true;
		_end = value;
	}

	
	/**
	 * Sets the value of <code>startLessons</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStartLessonsChanged()} will return true. If you specify
	 * the same value as the old value isStartLessonsChanged() will return the same as it did
	 * before calling <code>setStartLessons(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>startLessons</code> is not nullable)
	 * @since Iteration2
	 */
	public void setStartLessons(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("startLessons is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_startLessons, value) == 0) return;
		changed_startLessons = true;
		_startLessons = value;
	}

	
	/**
	 * Sets the value of <code>endLessons</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isEndLessonsChanged()} will return true. If you specify
	 * the same value as the old value isEndLessonsChanged() will return the same as it did
	 * before calling <code>setEndLessons(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>endLessons</code> is not nullable)
	 * @since Iteration2
	 */
	public void setEndLessons(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("endLessons is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_endLessons, value) == 0) return;
		changed_endLessons = true;
		_endLessons = value;
	}

}
