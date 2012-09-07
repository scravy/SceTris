/* Course.java / 2010-10-22+02:00
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
@de.fu.weave.orm.annotation.Entity(name = "Course")
@de.fu.weave.xml.annotation.XmlElement("Course")
public class Course extends de.fu.weave.orm.GenericEntity implements Comparable<de.fu.weave.orm.Entity> {
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
	Course(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_name = result.getString("name");
			fetched_name = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newCourse} rather than {@link RelationManager#createCourse).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Course\"" + " (\"name\" )"
			+ " VALUES (?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
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
		String query = "UPDATE " + "\"scetris\".\"Course\"" + " SET \"name\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _name);
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
		String query = "DELETE FROM " + "\"scetris\".\"Course\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Course entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override	
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Course) {
			return compareTo((Course) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Course entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseHasCourseAttribute.
	 * <p>
	 * This is the subject of CourseHasCourseAttribute.
	 
	 * @return The object regarding CourseHasCourseAttribute where this (the java-object you call objectsOfCourseHasCourseAttribute on) is the subject.
	 * @since Iteration3
	 */
	public CourseAttribute objectOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseAttribute> coll = objectsOfCourseHasCourseAttribute();
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
	public java.util.Collection<CourseAttribute> objectsOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseAttribute\""
			+ " WHERE id IN (SELECT attribute FROM "
			+ "\"scetris\".\"courseHasCourseAttribute\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseAttribute> ret = new java.util.ArrayList<CourseAttribute>();
			while (result.next()) {
				ret.add(new CourseAttribute(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseHasCourseAttribute> whereSubjectOfCourseHasCourseAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseHasCourseAttribute\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseHasCourseAttribute> ret = new java.util.ArrayList<CourseHasCourseAttribute>();
			while (result.next()) {
				ret.add(new CourseHasCourseAttribute(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRecommendedForYear.
	 * <p>
	 * This is the subject of CourseRecommendedForYear.
	 
	 * @return The object regarding CourseRecommendedForYear where this (the java-object you call objectsOfCourseRecommendedForYear on) is the subject.
	 * @since Iteration3
	 */
	public Year objectOfCourseRecommendedForYear() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Year> coll = objectsOfCourseRecommendedForYear();
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
	public java.util.Collection<Year> objectsOfCourseRecommendedForYear() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Year\""
			+ " WHERE id IN (SELECT year FROM "
			+ "\"scetris\".\"courseRecommendedForYear\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Year> ret = new java.util.ArrayList<Year>();
			while (result.next()) {
				ret.add(new Year(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseRecommendedForYear> whereSubjectOfCourseRecommendedForYear() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRecommendedForYear\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseRecommendedForYear> ret = new java.util.ArrayList<CourseRecommendedForYear>();
			while (result.next()) {
				ret.add(new CourseRecommendedForYear(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRequiresCourse.
	 * <p>
	 * This is the subject of CourseRequiresCourse.
	 
	 * @return The object regarding CourseRequiresCourse where this (the java-object you call objectsOfCourseRequiresCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Course> coll = objectsOfCourseRequiresCourse();
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
	public java.util.Collection<Course> objectsOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT dependency FROM "
			+ "\"scetris\".\"courseRequiresCourse\"" + " WHERE course = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Course> ret = new java.util.ArrayList<Course>();
			while (result.next()) {
				ret.add(new Course(manager, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the actual Relationship-Objects in which
	 * this is the subject of the relationship.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseRequiresCourse> whereSubjectOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRequiresCourse\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseRequiresCourse> ret = new java.util.ArrayList<CourseRequiresCourse>();
			while (result.next()) {
				ret.add(new CourseRequiresCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via CourseRequiresCourse.
	 * <p>
	 * This is the object of CourseRequiresCourse.
	 * @return The subject regarding CourseRequiresCourse where this (the java-object you call subjectsOfCourseRequiresCourse on) is the object.
	 * @since Iteration3
	 */
	public Course subjectOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Course> coll = subjectsOfCourseRequiresCourse();
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
	public java.util.Collection<Course> subjectsOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"courseRequiresCourse\"" + " WHERE dependency = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Course> ret = new java.util.ArrayList<Course>();
			while (result.next()) {
				ret.add(new Course(manager, result));
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
	public java.util.Collection<CourseRequiresCourse> whereObjectOfCourseRequiresCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"courseRequiresCourse\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseRequiresCourse> ret = new java.util.ArrayList<CourseRequiresCourse>();
			while (result.next()) {
				ret.add(new CourseRequiresCourse(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonGivesCourse.
	 * <p>
	 * This is the object of PersonGivesCourse.
	 * @return The subject regarding PersonGivesCourse where this (the java-object you call subjectsOfPersonGivesCourse on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Person> coll = subjectsOfPersonGivesCourse();
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
	public java.util.Collection<Person> subjectsOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT person FROM "
			+ "\"scetris\".\"personGivesCourse\"" + " WHERE course = ?);";
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
	public java.util.Collection<PersonGivesCourse> whereObjectOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonGivesCourse> ret = new java.util.ArrayList<PersonGivesCourse>();
			while (result.next()) {
				ret.add(new PersonGivesCourse(manager, null, this, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}/**
	 * Retrieves the first java-object which is related to this java-object as via PersonSuccessfullyPassedCourse.
	 * <p>
	 * This is the object of PersonSuccessfullyPassedCourse.
	 * @return The subject regarding PersonSuccessfullyPassedCourse where this (the java-object you call subjectsOfPersonSuccessfullyPassedCourse on) is the object.
	 * @since Iteration3
	 */
	public Person subjectOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Person> coll = subjectsOfPersonSuccessfullyPassedCourse();
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
	public java.util.Collection<Person> subjectsOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Person\""
			+ " WHERE id IN (SELECT user FROM "
			+ "\"scetris\".\"personSuccessfullyPassedCourse\"" + " WHERE course = ?);";
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
	public java.util.Collection<PersonSuccessfullyPassedCourse> whereObjectOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonSuccessfullyPassedCourse> ret = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
			while (result.next()) {
				ret.add(new PersonSuccessfullyPassedCourse(manager, null, this, result));
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("name")
	public String getName() {
		return _name;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedName() {
		return changed_name;
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
	public void setName(String value) {
		
		changed_name = true;
		_name = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

}
