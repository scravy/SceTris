/* CourseInstance.java / 2010-10-30+02:00
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
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"CourseInstance\"")
@de.fu.weave.xml.annotation.XmlElement("CourseInstance")
public class CourseInstance extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * takes place in
	 * <p>
	 * 
				The Program this Course Instance is running in.
			
	 * @since Iteration2
	 */
	Program _program;
	boolean changed_program = false;
	boolean fetched_program = false;
	int ref_program;/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Course _instanceOf;
	boolean changed_instanceOf = false;
	boolean fetched_instanceOf = false;
	int ref_instanceOf;/**
	 * start date
	 * <p>
	 * 
				The start date of the Course Instance.
			
	 * @since Iteration2
	 */
	java.sql.Date _start;
	boolean changed_start = false;
	boolean fetched_start = false;/**
	 * end date
	 * <p>
	 * 
				The end date of the Course Instance.
			
	 * @since Iteration2
	 */
	java.sql.Date _end;
	boolean changed_end = false;
	boolean fetched_end = false;/**
	 * docent
	 * <p>
	 * 
				The docent for this Course Element.
			
	 * @since Iteration2
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
	 * @since Iteration2
	 */
	CourseInstance(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourseInstance} rather than {@link RelationManager#createCourseInstance).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"CourseInstance\"" + " (\"program\" , \"instance_of\" , \"start\" , \"end\" , \"main_lecturer\" )"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_program);
			
				stmt.setInt(i++, ref_instanceOf);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setInt(i++, ref_mainLecturer);
			
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
		String query = "UPDATE " + "\"scetris\".\"CourseInstance\"" + " SET \"program\" = ?, \"instance_of\" = ?, \"start\" = ?, \"end\" = ?, \"main_lecturer\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_program);
			
				stmt.setInt(i++, ref_instanceOf);
			
				stmt.setDate(i++, _start);
			
				stmt.setDate(i++, _end);
			
				stmt.setInt(i++, ref_mainLecturer);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"CourseInstance\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(CourseInstance entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof CourseInstance) {
			return compareTo((CourseInstance) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(CourseInstance entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
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
		java.util.Collection<Person> coll = subjectsOfPersonEnrolledInCourseInstance();
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
	public java.util.Collection<Person> subjectsOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT user FROM "
			+ "\"scetris\".\"personEnrolledInCourseInstance\"" + " WHERE course_instance = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Person> ret = new java.util.ArrayList<Person>();
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
	public java.util.Collection<PersonEnrolledInCourseInstance> whereObjectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\""
			+ " WHERE course_instance = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonEnrolledInCourseInstance> ret = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
			while (result.next()) {
				ret.add(new PersonEnrolledInCourseInstance(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	public int getId() {
		return _id;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedId() {
		return changed_id;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	public java.sql.Timestamp getTimekey() {
		return _timekey;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedTimekey() {
		return changed_timekey;
	}

	
	public Program getProgram() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_program) {
			_program = manager.getProgram(ref_program);
			fetched_program = true;
		}
		return _program;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedProgram() {
		return changed_program;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("program")
	public int refProgram() {
		return ref_program;
	}

	
	public Course getInstanceOf() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_instanceOf) {
			_instanceOf = manager.getCourse(ref_instanceOf);
			fetched_instanceOf = true;
		}
		return _instanceOf;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedInstanceOf() {
		return changed_instanceOf;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("instanceOf")
	public int refInstanceOf() {
		return ref_instanceOf;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("start")
	public java.sql.Date getStart() {
		return _start;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedStart() {
		return changed_start;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("end")
	public java.sql.Date getEnd() {
		return _end;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedEnd() {
		return changed_end;
	}

	
	public Person getMainLecturer() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_mainLecturer) {
			_mainLecturer = manager.getPerson(ref_mainLecturer);
			fetched_mainLecturer = true;
		}
		return _mainLecturer;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedMainLecturer() {
		return changed_mainLecturer;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("mainLecturer")
	public int refMainLecturer() {
		return ref_mainLecturer;
	}

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setProgram(Program value) {
		
		changed_program = true;
		ref_program = value._id;
		fetched_program = true;
		_program = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setInstanceOf(Course value) {
		
		changed_instanceOf = true;
		ref_instanceOf = value._id;
		fetched_instanceOf = true;
		_instanceOf = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStart(java.sql.Date value) {
		
		changed_start = true;
		_start = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setEnd(java.sql.Date value) {
		
		changed_end = true;
		_end = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setMainLecturer(Person value) {
		
		changed_mainLecturer = true;
		ref_mainLecturer = value._id;
		fetched_mainLecturer = true;
		_mainLecturer = value;
	}

}
