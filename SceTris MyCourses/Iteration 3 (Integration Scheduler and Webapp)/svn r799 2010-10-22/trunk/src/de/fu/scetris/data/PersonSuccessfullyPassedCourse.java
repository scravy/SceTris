/* PersonSuccessfullyPassedCourse.java / 2010-10-22+02:00
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
@de.fu.weave.orm.annotation.Relationship(name = "personSuccessfullyPassedCourse", subject = Person.class, object = Course.class)
@de.fu.weave.xml.annotation.XmlElement("personSuccessfullyPassedCourse")
public class PersonSuccessfullyPassedCourse extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

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
	
	
	PersonSuccessfullyPassedCourse(RelationManager manager, Person subject, Course object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_user = result.getInt("user");
			ref_course = result.getInt("course");
			_ctime = result.getTimestamp("ctime");
			fetched_ctime = true;
			_mtime = result.getTimestamp("mtime");
			fetched_mtime = true;
			ref_createdBy = result.getInt("created_by");
			ref_modifiedBy = result.getInt("modified_by");
			_grade = result.getString("grade");
			fetched_grade = true;
			_notes = result.getString("notes");
			fetched_notes = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	PersonSuccessfullyPassedCourse(RelationManager manager, Person subject, Course object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	PersonSuccessfullyPassedCourse(RelationManager manager, boolean full, Person subject, Course object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _grade, String _notes) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._ctime = _ctime;
		this.fetched_ctime = true;
		this._mtime = _mtime;
		this.fetched_mtime = true;
		this._createdBy = _createdBy;
		this.ref_createdBy = _createdBy.id();
		this.fetched_createdBy = true;
		this._grade = _grade;
		this.fetched_grade = true;
		this._notes = _notes;
		this.fetched_notes = true;
	}
	
	@Override
	public Person getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Course getObject() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getCourse(object_refid);
		}
		return object;
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + " (Person, Course, , \"ctime\", \"mtime\", \"created_by\", \"grade\", \"notes\")"
			+ " VALUES (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
				stmt.setTimestamp(i++, _ctime);
				stmt.setTimestamp(i++, _mtime);
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
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
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"personSuccessfullyPassedCourse\"" + " SET \"ctime\" = ?, \"mtime\" = ?, \"created_by\" = ?, \"grade\" = ?, \"notes\" = ? WHERE "
			+ " AND user = ?"
			+ " AND course = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setTimestamp(++i, _ctime);
				stmt.setTimestamp(++i, _mtime);
			if (ref_createdBy != null) {
				stmt.setInt(++i, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
			}
			if (_grade != null) {
				stmt.setString(++i, _grade);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			if (_notes != null) {
				stmt.setString(++i, _notes);
			} else {
				stmt.setNull(i++, java.sql.Types.LONGVARCHAR);
			}
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_course);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE "
			+ " AND user = ?"
			+ " AND course = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_course);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Person getUser() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_user) {
			_user = manager.getPerson(ref_user);
			fetched_user = true;
		}
		return _user;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedUser() {
		return changed_user;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("user")
	public int refUser() {
		return ref_user;
	}

	
	public Course getCourse() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_course) {
			_course = manager.getCourse(ref_course);
			fetched_course = true;
		}
		return _course;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedCourse() {
		return changed_course;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("course")
	public int refCourse() {
		return ref_course;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	public java.sql.Timestamp getCtime() {
		return _ctime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedCtime() {
		return changed_ctime;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("mtime")
	public java.sql.Timestamp getMtime() {
		return _mtime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedMtime() {
		return changed_mtime;
	}

	
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
	public boolean isChangedCreatedBy() {
		return changed_createdBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("createdBy")
	public Integer refCreatedBy() {
		return ref_createdBy;
	}

	
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
	public boolean isChangedModifiedBy() {
		return changed_modifiedBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("modifiedBy")
	public Integer refModifiedBy() {
		return ref_modifiedBy;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("grade")
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
	public boolean isChangedGrade() {
		return changed_grade;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("notes")
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
	public boolean isChangedNotes() {
		return changed_notes;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setUser(Person value) {
		
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCourse(Course value) {
		
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCtime(java.sql.Timestamp value) {
		
		changed_ctime = true;
		_ctime = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setMtime(java.sql.Timestamp value) {
		
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
	 *
	 * @since Iteration2
	 */
	public void setGrade(String value) {
		if (value == null) {
			_grade = null;
			return;
		}
		changed_grade = true;
		_grade = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setNotes(String value) {
		if (value == null) {
			_notes = null;
			return;
		}
		changed_notes = true;
		_notes = value;
	}

}
