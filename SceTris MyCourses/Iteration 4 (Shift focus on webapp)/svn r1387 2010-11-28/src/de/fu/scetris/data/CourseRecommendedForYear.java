/* CourseRecommendedForYear.java / 2010-11-27+01:00
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
 * OO-Representation of the relationship-relation CourseRecommendedForYear
 * <p>
 * 
			This relation gives the Person(students) a hint in which Course they should enroll in order to maintain their studies in order.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Relationship(name = "courseRecommendedForYear", subject = Course.class, object = Year.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"courseRecommendedForYear\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("courseRecommendedForYear")
public class CourseRecommendedForYear extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Course = "course";
	public static final String Year = "year";
	public static final String Timekey = "timekey";
	

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
	Year _year;
	boolean changed_year = false;
	boolean fetched_year = false;
	int ref_year;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	
	
	Course subject;
	int subject_refid;
	Year object;
	int object_refid;
	
	
	CourseRecommendedForYear(RelationManager manager, Course subject, Year object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_course = result.getInt("course");
			ref_year = result.getInt("year");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;	
			this.subject_refid = ref_course;
			this.object_refid = ref_year;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	CourseRecommendedForYear(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_course = result.getInt("course");
			ref_year = result.getInt("year");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"course\"");
			this.object_refid = result.getInt("\"year\"");
			this.subject = manager.getCourse(this.subject_refid);
			this.object = manager.getYear(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	CourseRecommendedForYear(RelationManager manager, Course subject, Year object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._course = this.subject = subject;
		this.ref_course = this.subject_refid = subject.id();
		this._year = this.object = object;
		this.ref_year = this.object_refid = object.id();
		timekey(true);
		
	}
	
	CourseRecommendedForYear(RelationManager manager, boolean full, Course subject, Year object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
	}
	
	@Override
	public Course subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getCourse(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Year object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getYear(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof CourseRecommendedForYear) {
			return equals((CourseRecommendedForYear) obj);
		}
		return false;
	}
	
	public boolean equals(CourseRecommendedForYear relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"courseRecommendedForYear\"" + " (\"course\", \"year\", \"timekey\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
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
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"courseRecommendedForYear\""
			+ " WHERE "
			+ " \"course\" = ? AND "
			+ " \"year\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_course);
			stmt.setInt(i++, ref_year);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("course")
	@de.fu.weave.xml.annotation.XmlDependency("isCourseFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "course", use = "subject", ref = Course.class)
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("year")
	@de.fu.weave.xml.annotation.XmlDependency("isYearFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "year", use = "object", ref = Year.class)
	public Year getYear() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_year) {
			_year = manager.getYear(ref_year);
			fetched_year = true;
		}
		return _year;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isYearFetched() {
		return fetched_year;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isYearChanged() {
		return changed_year;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("year")
	public int refYear() {
		return ref_year;
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

	
	/**
	 * Sets the value of <code>year</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isYearChanged()} will return true. If you specify
	 * the same value as the old value isYearChanged() will return the same as it did
	 * before calling <code>setYear(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>year</code> is not nullable)
	 * @since Iteration2
	 */
	public void setYear(Year value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("year is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_year, value._id) == 0) return;
		changed_year = true;
		ref_year = value._id;
		fetched_year = true;
		_year = value;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

}
