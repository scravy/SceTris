/* CourseInstance.java / 2011-02-01+01:00
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
 * OO-Representation of the entity-relation CourseInstance
 * <p>
 * 
			A CourseInstance is created within a Program. It is a Course actually taking place. There can be several CourseInstances of the same Course within a program. There is a main lecturer being primary responsible for any CourseInstance.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "CourseInstance")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"CourseInstance\"", requiredSqlCols = {"program", "instance_of", "start", "end", "main_lecturer"})
@de.fu.weave.xml.annotation.XmlElement("CourseInstance")
public class CourseInstance extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String Program = "program";
	public static final String InstanceOf = "instance_of";
	public static final String Start = "start";
	public static final String End = "end";
	public static final String MainLecturer = "main_lecturer";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 2359440L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp timekey;
		
		@de.fu.weave.Form.Pos(3)
		@de.fu.weave.Form.Field("program") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("program$validator")
		@de.fu.weave.Form.ActiveConverter("program$converter")
		@de.fu.weave.Form.Alternatives("program$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int program;
		
		public java.util.Map<Integer,java.lang.String> program$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(4)
		@de.fu.weave.Form.Field("instanceOf") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("instanceOf$validator")
		@de.fu.weave.Form.ActiveConverter("instanceOf$converter")
		@de.fu.weave.Form.Alternatives("instanceOf$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int instanceOf;
		
		public java.util.Map<Integer,java.lang.String> instanceOf$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(5)
		@de.fu.weave.Form.Field("start") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("start$validator")
		@de.fu.weave.Form.ActiveConverter("start$converter")
		public java.sql.Date start;
		
		@de.fu.weave.Form.Pos(6)
		@de.fu.weave.Form.Field("end") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("end$validator")
		@de.fu.weave.Form.ActiveConverter("end$converter")
		public java.sql.Date end;
		
		@de.fu.weave.Form.Pos(7)
		@de.fu.weave.Form.Field("mainLecturer") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("mainLecturer$validator")
		@de.fu.weave.Form.ActiveConverter("mainLecturer$converter")
		@de.fu.weave.Form.Alternatives("mainLecturer$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public int mainLecturer;
		
		public java.util.Map<Integer,java.lang.String> mainLecturer$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		
		public Form setValues(CourseInstance $object) {
			
			id = $object.getId();
			timekey = $object.getTimekey();
			program = $object.refProgram();
			instanceOf = $object.refInstanceOf();
			start = $object.getStart();
			end = $object.getEnd();
			mainLecturer = $object.refMainLecturer();
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
	 * takes place in
	 * <p>
	 * 
				The Program this Course Instance is running in.
			
	 */
	Program _program;
	boolean changed_program = false;
	boolean fetched_program = false;
	int ref_program;/**
	 * 
	 * <p>
	 * 
	 */
	Course _instanceOf;
	boolean changed_instanceOf = false;
	boolean fetched_instanceOf = false;
	int ref_instanceOf;/**
	 * start date
	 * <p>
	 * 
				The start date of the Course Instance.
			
	 */
	java.sql.Date _start;
	boolean changed_start = false;
	boolean fetched_start = false;/**
	 * end date
	 * <p>
	 * 
				The end date of the Course Instance.
			
	 */
	java.sql.Date _end;
	boolean changed_end = false;
	boolean fetched_end = false;/**
	 * teacher
	 * <p>
	 * 
				The docent for this Course Element.
			
	 */
	Person _mainLecturer;
	boolean changed_mainLecturer = false;
	boolean fetched_mainLecturer = false;
	int ref_mainLecturer;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	CourseInstance(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_program = result.getInt("program");
			ref_instanceOf = result.getInt("instance_of");
			_start = result.getDate("start");
			fetched_start = true;
			_end = result.getDate("end");
			fetched_end = true;
			ref_mainLecturer = result.getInt("main_lecturer");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity CourseInstance and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _program 
				The Program this Course Instance is running in.
			
	 * @param _instanceOf 
	 * @param _start 
				The start date of the Course Instance.
			
	 * @param _end 
				The end date of the Course Instance.
			
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @since Iteration2
	 */
	CourseInstance(RelationManager manager, Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) {
		timekey(true);
		this.manager = manager;
		this._program = _program;
		this.ref_program = _program.id();
		this.fetched_program = true;
		this._instanceOf = _instanceOf;
		this.ref_instanceOf = _instanceOf.id();
		this.fetched_instanceOf = true;
		this._start = _start;
		this.fetched_start = true;
		this._end = _end;
		this.fetched_end = true;
		this._mainLecturer = _mainLecturer;
		this.ref_mainLecturer = _mainLecturer.id();
		this.fetched_mainLecturer = true;
	}
	
	/**
	 * Constructs the entity CourseInstance and initializes all attributes.
	 * @param _program 
				The Program this Course Instance is running in.
			
	 * @param _instanceOf 
	 * @param _start 
				The start date of the Course Instance.
			
	 * @param _end 
				The end date of the Course Instance.
			
	 * @param _mainLecturer 
				The docent for this Course Element.
			
	 * @since Iteration2
	 */
	CourseInstance(RelationManager manager, final boolean full, Program _program, Course _instanceOf, java.sql.Date _start, java.sql.Date _end, Person _mainLecturer) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._program = _program;
			this.ref_program = _program.id();
			this.fetched_program = true;
			this._instanceOf = _instanceOf;
			this.ref_instanceOf = _instanceOf.id();
			this.fetched_instanceOf = true;
			this._start = _start;
			this.fetched_start = true;
			this._end = _end;
			this.fetched_end = true;
			this._mainLecturer = _mainLecturer;
			this.ref_mainLecturer = _mainLecturer.id();
			this.fetched_mainLecturer = true;
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
		return "CourseInstance: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseInstance} rather than {@link RelationManager#createCourseInstance).
	 * @since Iteration2
	 */
	@Override
	public CourseInstance create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseInstance\"" + " (\"program\", \"instance_of\", \"start\", \"end\", \"main_lecturer\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_program);
			
				stmt.setInt(i++, ref_instanceOf);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setInt(i++, ref_mainLecturer);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
			}
			changed_program = false;
			changed_instanceOf = false;
			changed_start = false;
			changed_end = false;
			changed_mainLecturer = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"CourseInstance\"" + " SET \"program\" = ?"
			+ ", \"instance_of\" = ?"
			+ ", \"start\" = ?"
			+ ", \"end\" = ?"
			+ ", \"main_lecturer\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_program);
			
				stmt.setInt(i++, ref_instanceOf);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setInt(i++, ref_mainLecturer);
			
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
			}
			_timekey = newTimekey;
			changed_program = false;
			changed_instanceOf = false;
			changed_start = false;
			changed_end = false;
			changed_mainLecturer = false;
			
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
	 * @since Iteration4
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 * @since Iteration2
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"CourseInstance\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(CourseInstance entity) {
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
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof CourseInstance) {
			return compareTo((CourseInstance) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseInstance entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof CourseInstance) {
			return equals((CourseInstance) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonEnrolledInCourseInstance.
	 * <p>
	 * This is the object of PersonEnrolledInCourseInstance.
	 * @return The subject regarding PersonEnrolledInCourseInstance where this (the java-object you call subjectsOfPersonEnrolledInCourseInstance on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonEnrolledInCourseInstance();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonEnrolledInCourseInstance. If you know that there will be only
	 * 1:1 relationships of the type PersonEnrolledInCourseInstance, {@link #subjectOfPersonEnrolledInCourseInstance} is what you are looking for.
	 * <p>
	 * This is the object of PersonEnrolledInCourseInstance.
	 * @return A Collection of subjects regarding PersonEnrolledInCourseInstance where this (the java-object you call subjectsOfPersonEnrolledInCourseInstance on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Person> subjectsOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE \"id\" IN (SELECT \"user\" FROM "
			+ "\"scetris\".\"personEnrolledInCourseInstance\"" + " WHERE \"course_instance\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
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
	public java.util.List<PersonEnrolledInCourseInstance> whereObjectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\""
			+ " WHERE \"course_instance\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonEnrolledInCourseInstance> ret = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
			while (result.next()) {
				ret.add(new PersonEnrolledInCourseInstance(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
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

	
	@de.fu.weave.xml.annotation.XmlElement("program")
	@de.fu.weave.xml.annotation.XmlDependency("isProgramFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "program", serial = true, ref = Program.class)
	public Program getProgram() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_program) {
			_program = manager.getProgram(ref_program);
			fetched_program = true;
		}
		return _program;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isProgramFetched() {
		return fetched_program;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isProgramChanged() {
		return changed_program;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("program")
	public int refProgram() {
		return ref_program;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("instanceOf")
	@de.fu.weave.xml.annotation.XmlDependency("isInstanceOfFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "instance_of", serial = true, ref = Course.class)
	public Course getInstanceOf() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_instanceOf) {
			_instanceOf = manager.getCourse(ref_instanceOf);
			fetched_instanceOf = true;
		}
		return _instanceOf;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isInstanceOfFetched() {
		return fetched_instanceOf;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isInstanceOfChanged() {
		return changed_instanceOf;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("instanceOf")
	public int refInstanceOf() {
		return ref_instanceOf;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("start")
	@de.fu.weave.orm.annotation.Attribute(name = "start", serial = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "end", serial = true)
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("mainLecturer")
	@de.fu.weave.xml.annotation.XmlDependency("isMainLecturerFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "main_lecturer", serial = true, ref = Person.class)
	public Person getMainLecturer() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_mainLecturer) {
			_mainLecturer = manager.getPerson(ref_mainLecturer);
			fetched_mainLecturer = true;
		}
		return _mainLecturer;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isMainLecturerFetched() {
		return fetched_mainLecturer;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isMainLecturerChanged() {
		return changed_mainLecturer;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("mainLecturer")
	public int refMainLecturer() {
		return ref_mainLecturer;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>program</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isProgramChanged()} will return true. If you specify
	 * the same value as the old value isProgramChanged() will return the same as it did
	 * before calling <code>setProgram(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>program</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseInstance setProgram(Program value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("program is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_program, value._id) == 0) return this;
		changed_program = true;
		ref_program = value._id;
		fetched_program = true;
		_program = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>instanceOf</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isInstanceOfChanged()} will return true. If you specify
	 * the same value as the old value isInstanceOfChanged() will return the same as it did
	 * before calling <code>setInstanceOf(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>instanceOf</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseInstance setInstanceOf(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("instanceOf is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_instanceOf, value._id) == 0) return this;
		changed_instanceOf = true;
		ref_instanceOf = value._id;
		fetched_instanceOf = true;
		_instanceOf = value;
		return this;
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
	public CourseInstance setStart(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("start is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_start, value) == 0) return this;
		changed_start = true;
		_start = value;
		return this;
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
	public CourseInstance setEnd(java.sql.Date value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("end is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_end, value) == 0) return this;
		changed_end = true;
		_end = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>mainLecturer</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isMainLecturerChanged()} will return true. If you specify
	 * the same value as the old value isMainLecturerChanged() will return the same as it did
	 * before calling <code>setMainLecturer(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>mainLecturer</code> is not nullable)
	 * @since Iteration2
	 */
	public CourseInstance setMainLecturer(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("mainLecturer is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_mainLecturer, value._id) == 0) return this;
		changed_mainLecturer = true;
		ref_mainLecturer = value._id;
		fetched_mainLecturer = true;
		_mainLecturer = value;
		return this;
	}

}
