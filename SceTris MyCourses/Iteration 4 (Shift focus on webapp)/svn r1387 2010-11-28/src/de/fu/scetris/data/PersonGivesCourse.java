/* PersonGivesCourse.java / 2010-11-27+01:00
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
@de.fu.bakery.orm.java.annotation.Relationship(name = "personGivesCourse", subject = Person.class, object = Course.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"personGivesCourse\"", requiredSqlCols = {"priority"})
@de.fu.weave.xml.annotation.XmlElement("personGivesCourse")
public class PersonGivesCourse extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Person = "person";
	public static final String Course = "course";
	public static final String Timekey = "timekey";
	public static final String Priority = "priority";
	

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
	
	
	PersonGivesCourse(RelationManager manager, Person subject, Course object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_person = result.getInt("person");
			ref_course = result.getInt("course");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;	
			this.subject_refid = ref_person;
			this.object_refid = ref_course;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	PersonGivesCourse(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_person = result.getInt("person");
			ref_course = result.getInt("course");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_priority = result.getInt("priority");
			fetched_priority = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"person\"");
			this.object_refid = result.getInt("\"course\"");
			this.subject = manager.getPerson(this.subject_refid);
			this.object = manager.getCourse(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	PersonGivesCourse(RelationManager manager, Person subject, Course object, int _priority) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._person = this.subject = subject;
		this.ref_person = this.subject_refid = subject.id();
		this._course = this.object = object;
		this.ref_course = this.object_refid = object.id();
		timekey(true);
		
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
		timekey(true);
		
		this._priority = _priority;
		this.fetched_priority = true;
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
		if (obj instanceof PersonGivesCourse) {
			return equals((PersonGivesCourse) obj);
		}
		return false;
	}
	
	public boolean equals(PersonGivesCourse relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personGivesCourse\"" + " (\"person\", \"course\","
			+ "\"priority\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
				stmt.setInt(i++, _priority);
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
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
		String query = "UPDATE " + "\"scetris\".\"personGivesCourse\"" + " SET \"priority\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"person\" = ?"
			+ " AND \"course\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, _priority);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_person);
			stmt.setInt(i++, ref_course);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"personGivesCourse\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE "
			+ " \"person\" = ? AND "
			+ " \"course\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_person);
			stmt.setInt(i++, ref_course);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("person")
	@de.fu.weave.xml.annotation.XmlDependency("isPersonFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "person", use = "subject", ref = Person.class)
	public Person getPerson() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_person) {
			_person = manager.getPerson(ref_person);
			fetched_person = true;
		}
		return _person;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isPersonFetched() {
		return fetched_person;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isPersonChanged() {
		return changed_person;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("person")
	public int refPerson() {
		return ref_person;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("priority")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "priority", serial = true)
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
	

	
	
	
	/**
	 * Sets the value of <code>person</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPersonChanged()} will return true. If you specify
	 * the same value as the old value isPersonChanged() will return the same as it did
	 * before calling <code>setPerson(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>person</code> is not nullable)
	 * @since Iteration2
	 */
	public void setPerson(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("person is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_person, value._id) == 0) return;
		changed_person = true;
		ref_person = value._id;
		fetched_person = true;
		_person = value;
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
	public void setCourse(Course value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("course is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_course, value._id) == 0) return;
		changed_course = true;
		ref_course = value._id;
		fetched_course = true;
		_course = value;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
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
	public void setPriority(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("priority is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_priority, value) == 0) return;
		changed_priority = true;
		_priority = value;
	}

}
