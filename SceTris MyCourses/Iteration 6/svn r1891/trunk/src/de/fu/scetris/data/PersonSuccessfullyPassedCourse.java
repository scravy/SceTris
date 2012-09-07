/* PersonSuccessfullyPassedCourse.java / 2011-01-15+01:00
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
 * OO-Representation of the relationship-relation PersonSuccessfullyPassedCourse
 * <p>
 * 
			This relation tells us that a Person has passed a specific Course.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Relationship(name = "personSuccessfullyPassedCourse", subject = Person.class, object = Course.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"personSuccessfullyPassedCourse\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("personSuccessfullyPassedCourse")
public class PersonSuccessfullyPassedCourse extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String User = "user";
	public static final String Course = "course";
	public static final String Ctime = "ctime";
	public static final String Mtime = "mtime";
	public static final String CreatedBy = "created_by";
	public static final String ModifiedBy = "modified_by";
	public static final String Grade = "grade";
	public static final String Notes = "notes";
	

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
	Course _course;
	boolean changed_course = false;
	boolean fetched_course = false;
	int ref_course;
	/**
	 * date of creation
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;
	
	/**
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;
	
	/**
	 * initially created by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;
	/**
	 * least recently modified by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;
	/**
	 * grade
	 * <p>
	 * 
				The grade the Person got in the specified Course.
			
	 * @since Iteration2
	 */
	String _grade = null;
	boolean changed_grade = false;
	boolean fetched_grade = false;
	
	/**
	 * notes
	 * <p>
	 * 
				Notes regarding the Person in question.
			
	 * @since Iteration2
	 */
	String _notes = null;
	boolean changed_notes = false;
	boolean fetched_notes = false;
	
	
	
	Person subject;
	int subject_refid;
	Course object;
	int object_refid;
	
	
	PersonSuccessfullyPassedCourse(RelationManager manager, Person subject, Course object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_course = result.getInt("course");
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
			_grade = result.getString("grade");
			if (result.wasNull()) {
				_grade = null;
			}
			fetched_grade = true;
			_notes = result.getString("notes");
			if (result.wasNull()) {
				_notes = null;
			}
			fetched_notes = true;	
			this.subject_refid = ref_user;
			this.object_refid = ref_course;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	PersonSuccessfullyPassedCourse(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_course = result.getInt("course");
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
			_grade = result.getString("grade");
			if (result.wasNull()) {
				_grade = null;
			}
			fetched_grade = true;
			_notes = result.getString("notes");
			if (result.wasNull()) {
				_notes = null;
			}
			fetched_notes = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"user\"");
			this.object_refid = result.getInt("\"course\"");
			this.subject = manager.getPerson(this.subject_refid);
			this.object = manager.getCourse(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	PersonSuccessfullyPassedCourse(RelationManager manager, Person subject, Course object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._user = this.subject = subject;
		this.ref_user = this.subject_refid = subject.id();
		this._course = this.object = object;
		this.ref_course = this.object_refid = object.id();
		timekey(true);
		
	}
	
	PersonSuccessfullyPassedCourse(RelationManager manager, boolean full, Person subject, Course object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _grade, String _notes) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._ctime = _ctime;
		this.fetched_ctime = true;
		this._createdBy = _createdBy;
		this.ref_createdBy = _createdBy.id();
		this.fetched_createdBy = true;
		this._modifiedBy = _modifiedBy;
		this.ref_modifiedBy = _modifiedBy.id();
		this.fetched_modifiedBy = true;
		this._grade = _grade;
		this.fetched_grade = true;
		this._notes = _notes;
		this.fetched_notes = true;
	}
	
	@Override
	public Person subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Course object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getCourse(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonSuccessfullyPassedCourse) {
			return equals((PersonSuccessfullyPassedCourse) obj);
		}
		return false;
	}
	
	public boolean equals(PersonSuccessfullyPassedCourse relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public PersonSuccessfullyPassedCourse create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + " (\"user\", \"course\","
			+ "\"ctime\","
			+ "\"created_by\","
			+ "\"modified_by\","
			+ "\"grade\","
			+ "\"notes\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
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
			
			if (_grade != null) {
				stmt.setString(i++, _grade);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (_notes != null) {
				stmt.setString(i++, _notes);
			} else {
				stmt.setNull(i++, java.sql.Types.LONGVARCHAR);
				
			}
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
		return this;
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
		String query = "UPDATE " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + " SET \"ctime\" = ?, "
			+ "\"created_by\" = ?, "
			+ "\"modified_by\" = ?, "
			+ "\"grade\" = ?, "
			+ "\"notes\" = ?, "
			+ ""
			+ "\"mtime\" = ? WHERE "
			+ "\"mtime\" = ?"
			+ " AND \"user\" = ?"
			+ " AND \"course\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setTimestamp(i++, _ctime);
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
			if (_grade != null) {
				stmt.setString(i++, _grade);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			if (_notes != null) {
				stmt.setString(i++, _notes);
			} else {
				stmt.setNull(i++, java.sql.Types.LONGVARCHAR);
			}
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_course);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"personSuccessfullyPassedCourse\"" + '#' + id());
			}
			_mtime = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_mtime = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE "
			+ " \"user\" = ? AND "
			+ " \"course\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_course);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("user")
	@de.fu.weave.xml.annotation.XmlDependency("isUserFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "user", use = "subject", ref = Person.class)
	public Person getUser() throws de.fu.bakery.orm.java.DatabaseException {
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("course")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "course", use = "object", ref = Course.class)
	public Course getCourse() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_course) {
			_course = manager.getCourse(ref_course);
			fetched_course = true;
		}
		return _course;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCourseFetched() {
		return fetched_course;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCourseChanged() {
		return changed_course;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("course")
	public int refCourse() {
		return ref_course;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "ctime", serial = true, hidden = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "mtime", serial = true, hidden = true, use = "timestamp")
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
		return de.fu.bakery.orm.java.GenericRelationManager.compareValues(_mtime, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.bakery.orm.java.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.bakery.orm.java.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlElement("createdBy")
	@de.fu.weave.xml.annotation.XmlDependency("isCreatedByFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "created_by", serial = true, nullable = true, hidden = true, ref = Person.class)
	public Person getCreatedBy() throws de.fu.bakery.orm.java.DatabaseException {
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "modified_by", serial = true, nullable = true, hidden = true, ref = Person.class)
	public Person getModifiedBy() throws de.fu.bakery.orm.java.DatabaseException {
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("grade")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "grade", serial = true, nullable = true)
	public String getGrade() {
		if (_grade == null) {
			return null;
		}
		return _grade;
	}
	
	public boolean hasGrade() {
		return _grade != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isGradeChanged() {
		return changed_grade;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("notes")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "notes", serial = true, nullable = true)
	public String getNotes() {
		if (_notes == null) {
			return null;
		}
		return _notes;
	}
	
	public boolean hasNotes() {
		return _notes != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isNotesChanged() {
		return changed_notes;
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
	public PersonSuccessfullyPassedCourse setUser(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("user is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_user, value._id) == 0) return this;
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>course</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCourseChanged()} will return true. If you specify
	 * the same value as the old value isCourseChanged() will return the same as it did
	 * before calling <code>setCourse(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>course</code> is not nullable)
	 * @since Iteration2
	 */
	public PersonSuccessfullyPassedCourse setCourse(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("course is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_course, value._id) == 0) return this;
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
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
	public PersonSuccessfullyPassedCourse setCtime(java.sql.Timestamp value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_ctime, value) == 0) return this;
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
	public PersonSuccessfullyPassedCourse setCreatedBy(Person value) {
		
		if (value == null) {
			clearCreatedBy();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_createdBy, value._id) == 0) return this;
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
	public PersonSuccessfullyPassedCourse clearCreatedBy() {
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
	public PersonSuccessfullyPassedCourse setModifiedBy(Person value) {
		
		if (value == null) {
			clearModifiedBy();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_modifiedBy, value._id) == 0) return this;
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
	public PersonSuccessfullyPassedCourse clearModifiedBy() {
		if (_modifiedBy == null && ref_modifiedBy == null) {
			return this;
		}
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>grade</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isGradeChanged()} will return true. If you specify
	 * the same value as the old value isGradeChanged() will return the same as it did
	 * before calling <code>setGrade(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public PersonSuccessfullyPassedCourse setGrade(String value) {
		
		if (value == null) {
			clearGrade();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_grade, value) == 0) return this;
		changed_grade = true;
		_grade = value;
		return this;
	}
	
	/**
	 * grade may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse clearGrade() {
		if (_grade == null) {
			return this;
		}
		_grade = null;
		changed_grade = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>notes</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isNotesChanged()} will return true. If you specify
	 * the same value as the old value isNotesChanged() will return the same as it did
	 * before calling <code>setNotes(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public PersonSuccessfullyPassedCourse setNotes(String value) {
		
		if (value == null) {
			clearNotes();
			return this;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_notes, value) == 0) return this;
		changed_notes = true;
		_notes = value;
		return this;
	}
	
	/**
	 * notes may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public PersonSuccessfullyPassedCourse clearNotes() {
		if (_notes == null) {
			return this;
		}
		_notes = null;
		changed_notes = true;
		return this;
	}

}
