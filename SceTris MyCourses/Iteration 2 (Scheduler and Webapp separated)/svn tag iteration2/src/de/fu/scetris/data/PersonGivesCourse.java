/* PersonGivesCourse.java / 2010-10-30+02:00
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
 * OO-Representation of the relationship-relation PersonGivesCourse
 * <p>
 * 
			This relation tells us which Persons are holding which Courses.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personGivesCourse", subject = Person.class, object = Course.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"personGivesCourse\"")
@de.fu.weave.xml.annotation.XmlElement("personGivesCourse")
public class PersonGivesCourse extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _person;
	boolean changed_person = false;
	boolean fetched_person = false;
	int ref_person;
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
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	/**
	 * priority
	 * <p>
	 * 
	 * @since Iteration2
	 */
	int _priority;
	boolean changed_priority = false;
	boolean fetched_priority = false;
	
	
	
	Person subject;
	int subject_refid;
	Course object;
	int object_refid;
	
	
	PersonGivesCourse(RelationManager manager, Person subject, Course object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_person = result.getInt("person");
			ref_course = result.getInt("course");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	PersonGivesCourse(RelationManager manager, Person subject, Course object, int _priority) {
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
	
	PersonGivesCourse(RelationManager manager, boolean full, Person subject, Course object, int _priority) {
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
	public Person subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Course object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getCourse(object_refid);
		}
		return object;
	}
	
	@Deprecated
	public Person getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Course getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonGivesCourse) {
			return equals((PersonGivesCourse) obj);
		}
		return false;
	}
	
	public boolean equals(PersonGivesCourse relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personGivesCourse\"" + " (\"person\", \"course\", \"priority\")"
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
		String query = "UPDATE " + "\"scetris\".\"personGivesCourse\"" + " SET \"priority\" = ? WHERE "
			+ " AND person = ?"
			+ " AND course = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setInt(++i, _priority);
			stmt.setInt(++i, ref_person);
			stmt.setInt(++i, ref_course);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE "
			+ " AND person = ?"
			+ " AND course = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_person);
			stmt.setInt(++i, ref_course);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Person getPerson() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_person) {
			_person = manager.getPerson(ref_person);
			fetched_person = true;
		}
		return _person;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedPerson() {
		return changed_person;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("person")
	public int refPerson() {
		return ref_person;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("priority")
	public int getPriority() {
		return _priority;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedPriority() {
		return changed_priority;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setPerson(Person value) {
		
		changed_person = true;
		ref_person = value._id;
		fetched_person = true;
		_person = value;
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
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPriority(int value) {
		
		changed_priority = true;
		_priority = value;
	}

}
