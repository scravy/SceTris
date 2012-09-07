/* Course.java / 2010-11-27+01:00
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
 * OO-Representation of the entity-relation Course
 * <p>
 * 
A Course is a well-defined set of CourseElements. For example there may be a Course which consists of two CourseElements of which one is a mandatory lecture and the other one is an optional seminar.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "Course")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Course\"", requiredSqlCols = {"name"})
@de.fu.weave.xml.annotation.XmlElement("Course")
public class Course extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Name = "name";
	public static final String Timekey = "timekey";
	

	/**
	 * @since Iteration4
	 */
	public static class Stub {
		Stub() {
		}
	
		public int Id;public String Name;public java.sql.Timestamp Timekey;
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
	boolean fetched_timekey = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Course(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Course and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Course(RelationManager manager, String _name) {
		timekey(true);
		this.manager = manager;
		this._name = _name;
		this.fetched_name = true;
	}
	
	/**
	 * Constructs the entity Course and initializes all attributes.
	 * @param _name 
				This is a unique name which is readable by humans and may be set by a user
				working with our application. A name is allowed to change, since it’s not
				a PRIMARY KEY. Typically it is not allowed
				to be NULL, since it acts as a secondary identifier.
			
	 * @since Iteration2
	 */
	Course(RelationManager manager, final boolean full, String _name) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._name = _name;
			this.fetched_name = true;
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
		return "Course: \"" + this._name + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourse} rather than {@link RelationManager#createCourse).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Course\"" + " (\"name\", \"timekey\")"
			+ " VALUES (?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_name = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Course\"" + " SET \"name\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Course\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Course entity) {
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
		if (entity instanceof Course) {
			return compareTo((Course) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Course entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Course) {
			return equals((Course) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseHasCourseAttribute.
	 * <p>
	 * This is the subject of CourseHasCourseAttribute.
	 
	 * @return The object regarding CourseHasCourseAttribute where this (the java-object you call objectsOfCourseHasCourseAttribute on) is the subject.
	 * @since Iteration3
	 */
	public CourseAttribute objectOfCourseHasCourseAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseAttribute> coll = objectsOfCourseHasCourseAttribute();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via CourseHasCourseAttribute. If you know that there will be only
	 * 1:1 relationships of the type CourseHasCourseAttribute, {@link #objectOfCourseHasCourseAttribute} is what you are looking for.
	 * <p>
	 * This is the subject of CourseHasCourseAttribute.
	 * @return The object regarding CourseHasCourseAttribute where this (the java-object you call objectsOfCourseHasCourseAttribute on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<CourseAttribute> objectsOfCourseHasCourseAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseAttribute\""
			+ " WHERE id IN (SELECT attribute FROM "
			+ "\"scetris\".\"courseHasCourseAttribute\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseAttribute> ret = new java.util.ArrayList<CourseAttribute>();
			while (result.next()) {
				ret.add(new CourseAttribute(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<CourseHasCourseAttribute> whereSubjectOfCourseHasCourseAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseHasCourseAttribute\""
			+ " WHERE \"course\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseHasCourseAttribute> ret = new java.util.ArrayList<CourseHasCourseAttribute>();
			while (result.next()) {
				ret.add(new CourseHasCourseAttribute(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRecommendedForYear.
	 * <p>
	 * This is the subject of CourseRecommendedForYear.
	 
	 * @return The object regarding CourseRecommendedForYear where this (the java-object you call objectsOfCourseRecommendedForYear on) is the subject.
	 * @since Iteration3
	 */
	public Year objectOfCourseRecommendedForYear() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Year> coll = objectsOfCourseRecommendedForYear();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via CourseRecommendedForYear. If you know that there will be only
	 * 1:1 relationships of the type CourseRecommendedForYear, {@link #objectOfCourseRecommendedForYear} is what you are looking for.
	 * <p>
	 * This is the subject of CourseRecommendedForYear.
	 * @return The object regarding CourseRecommendedForYear where this (the java-object you call objectsOfCourseRecommendedForYear on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<Year> objectsOfCourseRecommendedForYear() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Year\""
			+ " WHERE id IN (SELECT year FROM "
			+ "\"scetris\".\"courseRecommendedForYear\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Year> ret = new java.util.ArrayList<Year>();
			while (result.next()) {
				ret.add(new Year(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<CourseRecommendedForYear> whereSubjectOfCourseRecommendedForYear() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRecommendedForYear\""
			+ " WHERE \"course\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseRecommendedForYear> ret = new java.util.ArrayList<CourseRecommendedForYear>();
			while (result.next()) {
				ret.add(new CourseRecommendedForYear(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRequiresCourse.
	 * <p>
	 * This is the subject of CourseRequiresCourse.
	 
	 * @return The object regarding CourseRequiresCourse where this (the java-object you call objectsOfCourseRequiresCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Course> coll = objectsOfCourseRequiresCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via CourseRequiresCourse. If you know that there will be only
	 * 1:1 relationships of the type CourseRequiresCourse, {@link #objectOfCourseRequiresCourse} is what you are looking for.
	 * <p>
	 * This is the subject of CourseRequiresCourse.
	 * @return The object regarding CourseRequiresCourse where this (the java-object you call objectsOfCourseRequiresCourse on) is the subject.
	 * @since Iteration3
	 */
	public java.util.List<Course> objectsOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT dependency FROM "
			+ "\"scetris\".\"courseRequiresCourse\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Course> ret = new java.util.ArrayList<Course>();
			while (result.next()) {
				ret.add(new Course(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<CourseRequiresCourse> whereSubjectOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRequiresCourse\""
			+ " WHERE \"course\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseRequiresCourse> ret = new java.util.ArrayList<CourseRequiresCourse>();
			while (result.next()) {
				ret.add(new CourseRequiresCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRequiresCourse.
	 * <p>
	 * This is the object of CourseRequiresCourse.
	 * @return The subject regarding CourseRequiresCourse where this (the java-object you call subjectsOfCourseRequiresCourse on) is the object.
	 * @since Iteration3
	 */
	public Course subjectOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Course> coll = subjectsOfCourseRequiresCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via CourseRequiresCourse. If you know that there will be only
	 * 1:1 relationships of the type CourseRequiresCourse, {@link #subjectOfCourseRequiresCourse} is what you are looking for.
	 * <p>
	 * This is the object of CourseRequiresCourse.
	 * @return A Collection of subjects regarding CourseRequiresCourse where this (the java-object you call subjectsOfCourseRequiresCourse on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Course> subjectsOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"courseRequiresCourse\"" + " WHERE dependency = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Course> ret = new java.util.ArrayList<Course>();
			while (result.next()) {
				ret.add(new Course(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<CourseRequiresCourse> whereObjectOfCourseRequiresCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRequiresCourse\""
			+ " WHERE dependency = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseRequiresCourse> ret = new java.util.ArrayList<CourseRequiresCourse>();
			while (result.next()) {
				ret.add(new CourseRequiresCourse(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonGivesCourse.
	 * <p>
	 * This is the object of PersonGivesCourse.
	 * @return The subject regarding PersonGivesCourse where this (the java-object you call subjectsOfPersonGivesCourse on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonGivesCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonGivesCourse. If you know that there will be only
	 * 1:1 relationships of the type PersonGivesCourse, {@link #subjectOfPersonGivesCourse} is what you are looking for.
	 * <p>
	 * This is the object of PersonGivesCourse.
	 * @return A Collection of subjects regarding PersonGivesCourse where this (the java-object you call subjectsOfPersonGivesCourse on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Person> subjectsOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT person FROM "
			+ "\"scetris\".\"personGivesCourse\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<PersonGivesCourse> whereObjectOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE course = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonGivesCourse> ret = new java.util.ArrayList<PersonGivesCourse>();
			while (result.next()) {
				ret.add(new PersonGivesCourse(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonSuccessfullyPassedCourse.
	 * <p>
	 * This is the object of PersonSuccessfullyPassedCourse.
	 * @return The subject regarding PersonSuccessfullyPassedCourse where this (the java-object you call subjectsOfPersonSuccessfullyPassedCourse on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Person> coll = subjectsOfPersonSuccessfullyPassedCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all java-objects which are related to this java-object as via PersonSuccessfullyPassedCourse. If you know that there will be only
	 * 1:1 relationships of the type PersonSuccessfullyPassedCourse, {@link #subjectOfPersonSuccessfullyPassedCourse} is what you are looking for.
	 * <p>
	 * This is the object of PersonSuccessfullyPassedCourse.
	 * @return A Collection of subjects regarding PersonSuccessfullyPassedCourse where this (the java-object you call subjectsOfPersonSuccessfullyPassedCourse on) is the object.
	 * @since Iteration3
	 */
	public java.util.List<Person> subjectsOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT user FROM "
			+ "\"scetris\".\"personSuccessfullyPassedCourse\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Person> ret = new java.util.ArrayList<Person>();
			while (result.next()) {
				ret.add(new Person(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Object in which
	 * this is the object of the relationship.
	 * @since Iteration3
	 */
	public java.util.List<PersonSuccessfullyPassedCourse> whereObjectOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE course = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonSuccessfullyPassedCourse> ret = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
			while (result.next()) {
				ret.add(new PersonSuccessfullyPassedCourse(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
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
	
	

}
