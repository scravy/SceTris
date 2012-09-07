/* Person.java / 2010-11-04+01:00
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
 * OO-Representation of the entity-relation Person
 * <p>
 * 
A Person is a natural person who is able to login to myCourses. Therefore she is having login credentials (e.g. username and password or mail address and password). Every Person has a first name and a last name. There are many different roles at a university and there are persons who act in more than one role (e.g. there are students doing lectures, while not being professors). Every person needs to have a unique identifier regardless of the roles they belong to for administration reasons. Persons may belong to several Groups.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Entity(name = "Person")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Person\"")
@de.fu.weave.xml.annotation.XmlElement("Person")
public class Person extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity , de.fu.weave.User {
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
	 * date of creation
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;/**
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;/**
	 * initially created by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;/**
	 * least recently modified by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;/**
	 * first name
	 * <p>
	 * 
				The first name of a Person.
			
	 * @since Iteration2
	 */
	String _firstName;
	boolean changed_firstName = false;
	boolean fetched_firstName = false;/**
	 * additional names
	 * <p>
	 * 
				Additional names of a Person.
			
	 * @since Iteration2
	 */
	String _additionalNames = null;
	boolean changed_additionalNames = false;
	boolean fetched_additionalNames = false;/**
	 * last name
	 * <p>
	 * 
				The last name of a Person.
			
	 * @since Iteration2
	 */
	String _lastName;
	boolean changed_lastName = false;
	boolean fetched_lastName = false;/**
	 * email address
	 * <p>
	 * 
				The email address of a Person. It has to be unique.
			
	 * @since Iteration2
	 */
	String _emailAddress = null;
	boolean changed_emailAddress = false;
	boolean fetched_emailAddress = false;/**
	 * login name
	 * <p>
	 * 
				The name that is used to log into the system. It has to be unique.
			
	 * @since Iteration2
	 */
	String _loginName;
	boolean changed_loginName = false;
	boolean fetched_loginName = false;/**
	 * password
	 * <p>
	 * 
				The password that is used together with the login name in order to log in into the system.
			
	 * @since Iteration2
	 */
	String _loginPassword;
	boolean changed_loginPassword = false;
	boolean fetched_loginPassword = false;/**
	 * is administrator
	 * <p>
	 * 
				Attribute that shows whether the Person is a superuser or not.
			
	 * @since Iteration2
	 */
	boolean _isSuperuser;
	boolean changed_isSuperuser = false;
	boolean fetched_isSuperuser = false;/**
	 * deleted
	 * <p>
	 * 
				Attribute that shows on which date a Person was deleted from the system.
			
	 * @since Iteration2
	 */
	java.sql.Timestamp _deleted = null;
	boolean changed_deleted = false;
	boolean fetched_deleted = false;/**
	 * deleted by
	 * <p>
	 * 
				Attribute that shows by which Person the User was deleted.
			
	 * @since Iteration2
	 */
	Person _deletedBy = null;
	boolean changed_deletedBy = false;
	boolean fetched_deletedBy = false;
	Integer ref_deletedBy;/**
	 * working hours
	 * <p>
	 * 
				This attribute shows the number of working hours of a Person.
			
	 * @since Iteration2
	 */
	Integer _workingHours = null;
	boolean changed_workingHours = false;
	boolean fetched_workingHours = false;/**
	 * student number
	 * <p>
	 * 
				The number identifies the Person (student) uniquely.
			
	 * @since Iteration2
	 */
	String _studentId = null;
	boolean changed_studentId = false;
	boolean fetched_studentId = false;/**
	 * deleted by
	 * <p>
	 * 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 * @since Iteration2
	 */
	String _staffId = null;
	boolean changed_staffId = false;
	boolean fetched_staffId = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Person(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_ctime = result.getTimestamp("ctime");
			fetched_ctime = true;
			_mtime = result.getTimestamp("mtime");
			fetched_mtime = true;
			ref_createdBy = result.getInt("created_by");
			ref_modifiedBy = result.getInt("modified_by");
			_firstName = result.getString("first_name");
			fetched_firstName = true;
			_additionalNames = result.getString("additional_names");
			fetched_additionalNames = true;
			_lastName = result.getString("last_name");
			fetched_lastName = true;
			_emailAddress = result.getString("email_address");
			fetched_emailAddress = true;
			_loginName = result.getString("login_name");
			fetched_loginName = true;
			_loginPassword = result.getString("login_password");
			fetched_loginPassword = true;
			_isSuperuser = result.getBoolean("is_superuser");
			fetched_isSuperuser = true;
			_deleted = result.getTimestamp("deleted");
			fetched_deleted = true;
			ref_deletedBy = result.getInt("deleted_by");
			_workingHours = result.getInt("working_hours");
			fetched_workingHours = true;
			_studentId = result.getString("student_id");
			fetched_studentId = true;
			_staffId = result.getString("staff_id");
			fetched_staffId = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Constructs the entity Person and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _firstName 
				The first name of a Person.
			
	 * @param _lastName 
				The last name of a Person.
			
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 * @since Iteration2
	 */
	Person(RelationManager manager, String _firstName, String _lastName, String _loginName, String _loginPassword) {
		this.manager = manager;
		this._firstName = _firstName;
		this.fetched_firstName = true;
		this._lastName = _lastName;
		this.fetched_lastName = true;
		this._loginName = _loginName;
		this.fetched_loginName = true;
		this._loginPassword = _loginPassword;
		this.fetched_loginPassword = true;
	}
	
	/**
	 * Constructs the entity Person and initializes all attributes.
	 * @param _ctime 
	 * @param _mtime 
	 * @param _createdBy 
	 * @param _firstName 
				The first name of a Person.
			
	 * @param _additionalNames 
				Additional names of a Person.
			
	 * @param _lastName 
				The last name of a Person.
			
	 * @param _emailAddress 
				The email address of a Person. It has to be unique.
			
	 * @param _loginName 
				The name that is used to log into the system. It has to be unique.
			
	 * @param _loginPassword 
				The password that is used together with the login name in order to log in into the system.
			
	 * @param _isSuperuser 
				Attribute that shows whether the Person is a superuser or not.
			
	 * @param _deleted 
				Attribute that shows on which date a Person was deleted from the system.
			
	 * @param _deletedBy 
				Attribute that shows by which Person the User was deleted.
			
	 * @param _workingHours 
				This attribute shows the number of working hours of a Person.
			
	 * @param _studentId 
				The number identifies the Person (student) uniquely.
			
	 * @param _staffId 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 * @since Iteration2
	 */
	Person(RelationManager manager, final boolean full, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, Integer _workingHours, String _studentId, String _staffId) {
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._firstName = _firstName;
			this.fetched_firstName = true;
			this._lastName = _lastName;
			this.fetched_lastName = true;
			this._loginName = _loginName;
			this.fetched_loginName = true;
			this._loginPassword = _loginPassword;
			this.fetched_loginPassword = true;
		}
	}
	
	@Override
	public String loginName() {
		return getLoginName(); 
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
		return "Person: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newPerson} rather than {@link RelationManager#createPerson).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Person\"" + " (\"ctime\" , \"mtime\" , \"created_by\" , \"first_name\" , \"additional_names\" , \"last_name\" , \"email_address\" , \"login_name\" , \"login_password\" , \"is_superuser\" , \"deleted\" , \"deleted_by\" , \"working_hours\" , \"student_id\" , \"staff_id\" )"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (manager.isNull(_mtime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _mtime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setString(i++, _firstName);
			
			if (_additionalNames != null) {
				stmt.setString(i++, _additionalNames);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _lastName);
			
			if (_emailAddress != null) {
				stmt.setString(i++, _emailAddress);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _loginName);
			
				stmt.setString(i++, _loginPassword);
			
			if (manager.isNull(_isSuperuser)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _isSuperuser);
			}
			
			if (_deleted != null) {
				stmt.setTimestamp(i++, _deleted);
			} else {
				stmt.setNull(i++, java.sql.Types.TIMESTAMP);
				
			}
			
			if (ref_deletedBy != null) {
				stmt.setInt(i++, ref_deletedBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_workingHours != null) {
				stmt.setInt(i++, _workingHours);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_studentId != null) {
				stmt.setString(i++, _studentId);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (_staffId != null) {
				stmt.setString(i++, _staffId);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
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
		String query = "UPDATE " + "\"scetris\".\"Person\"" + " SET \"ctime\" = ?, \"mtime\" = ?, \"created_by\" = ?, \"first_name\" = ?, \"additional_names\" = ?, \"last_name\" = ?, \"email_address\" = ?, \"login_name\" = ?, \"login_password\" = ?, \"is_superuser\" = ?, \"deleted\" = ?, \"deleted_by\" = ?, \"working_hours\" = ?, \"student_id\" = ?, \"staff_id\" = ? WHERE id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _ctime);
			}
			
			if (manager.isNull(_mtime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
			} else {
				stmt.setTimestamp(i++, _mtime);
			}
			
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
				stmt.setString(i++, _firstName);
			
			if (_additionalNames != null) {
				stmt.setString(i++, _additionalNames);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _lastName);
			
			if (_emailAddress != null) {
				stmt.setString(i++, _emailAddress);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
				stmt.setString(i++, _loginName);
			
				stmt.setString(i++, _loginPassword);
			
			if (manager.isNull(_isSuperuser)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _isSuperuser);
			}
			
			if (_deleted != null) {
				stmt.setTimestamp(i++, _deleted);
			} else {
				stmt.setNull(i++, java.sql.Types.TIMESTAMP);
				
			}
			
			if (ref_deletedBy != null) {
				stmt.setInt(i++, ref_deletedBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_workingHours != null) {
				stmt.setInt(i++, _workingHours);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_studentId != null) {
				stmt.setString(i++, _studentId);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (_staffId != null) {
				stmt.setString(i++, _staffId);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Person\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Person entity) {
		if (entity == null) return -1;
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity == null) return -1;
		if (entity instanceof Person) {
			return compareTo((Person) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Person entity) {
		if (entity == null) return false;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Person) {
			return equals((Person) obj);
		}
		return false;
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonBelongsToGroup.
	 * <p>
	 * This is the subject of PersonBelongsToGroup.
	 
	 * @return The object regarding PersonBelongsToGroup where this (the java-object you call objectsOfPersonBelongsToGroup on) is the subject.
	 * @since Iteration3
	 */
	public Group objectOfPersonBelongsToGroup() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Group> coll = objectsOfPersonBelongsToGroup();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonBelongsToGroup. If you know that there will be only
	 * 1:1 relationships of the type PersonBelongsToGroup, {@link #objectOfPersonBelongsToGroup} is what you are looking for.
	 * <p>
	 * This is the subject of PersonBelongsToGroup.
	 * @return The object regarding PersonBelongsToGroup where this (the java-object you call objectsOfPersonBelongsToGroup on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Group> objectsOfPersonBelongsToGroup() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Group\""
			+ " WHERE id IN (SELECT group FROM "
			+ "\"scetris\".\"personBelongsToGroup\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Group> ret = new java.util.ArrayList<Group>();
			while (result.next()) {
				ret.add(new Group(manager, result));
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
	public java.util.Collection<PersonBelongsToGroup> whereSubjectOfPersonBelongsToGroup() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personBelongsToGroup\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonBelongsToGroup> ret = new java.util.ArrayList<PersonBelongsToGroup>();
			while (result.next()) {
				ret.add(new PersonBelongsToGroup(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonEnrolledInCourseInstance.
	 * <p>
	 * This is the subject of PersonEnrolledInCourseInstance.
	 
	 * @return The object regarding PersonEnrolledInCourseInstance where this (the java-object you call objectsOfPersonEnrolledInCourseInstance on) is the subject.
	 * @since Iteration3
	 */
	public CourseInstance objectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseInstance> coll = objectsOfPersonEnrolledInCourseInstance();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonEnrolledInCourseInstance. If you know that there will be only
	 * 1:1 relationships of the type PersonEnrolledInCourseInstance, {@link #objectOfPersonEnrolledInCourseInstance} is what you are looking for.
	 * <p>
	 * This is the subject of PersonEnrolledInCourseInstance.
	 * @return The object regarding PersonEnrolledInCourseInstance where this (the java-object you call objectsOfPersonEnrolledInCourseInstance on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseInstance> objectsOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseInstance\""
			+ " WHERE id IN (SELECT course_instance FROM "
			+ "\"scetris\".\"personEnrolledInCourseInstance\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseInstance> ret = new java.util.ArrayList<CourseInstance>();
			while (result.next()) {
				ret.add(new CourseInstance(manager, result));
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
	public java.util.Collection<PersonEnrolledInCourseInstance> whereSubjectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonEnrolledInCourseInstance> ret = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
			while (result.next()) {
				ret.add(new PersonEnrolledInCourseInstance(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonGivesCourse.
	 * <p>
	 * This is the subject of PersonGivesCourse.
	 
	 * @return The object regarding PersonGivesCourse where this (the java-object you call objectsOfPersonGivesCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Course> coll = objectsOfPersonGivesCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonGivesCourse. If you know that there will be only
	 * 1:1 relationships of the type PersonGivesCourse, {@link #objectOfPersonGivesCourse} is what you are looking for.
	 * <p>
	 * This is the subject of PersonGivesCourse.
	 * @return The object regarding PersonGivesCourse where this (the java-object you call objectsOfPersonGivesCourse on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Course> objectsOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"personGivesCourse\"" + " WHERE person = ?);";
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
	public java.util.Collection<PersonGivesCourse> whereSubjectOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE \"person\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonGivesCourse> ret = new java.util.ArrayList<PersonGivesCourse>();
			while (result.next()) {
				ret.add(new PersonGivesCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasAttribute.
	 * <p>
	 * This is the subject of PersonHasAttribute.
	 
	 * @return The object regarding PersonHasAttribute where this (the java-object you call objectsOfPersonHasAttribute on) is the subject.
	 * @since Iteration3
	 */
	public Attribute objectOfPersonHasAttribute() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Attribute> coll = objectsOfPersonHasAttribute();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonHasAttribute. If you know that there will be only
	 * 1:1 relationships of the type PersonHasAttribute, {@link #objectOfPersonHasAttribute} is what you are looking for.
	 * <p>
	 * This is the subject of PersonHasAttribute.
	 * @return The object regarding PersonHasAttribute where this (the java-object you call objectsOfPersonHasAttribute on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Attribute> objectsOfPersonHasAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Attribute\""
			+ " WHERE id IN (SELECT attribute FROM "
			+ "\"scetris\".\"personHasAttribute\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Attribute> ret = new java.util.ArrayList<Attribute>();
			while (result.next()) {
				ret.add(new Attribute(manager, result));
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
	public java.util.Collection<PersonHasAttribute> whereSubjectOfPersonHasAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasAttribute\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonHasAttribute> ret = new java.util.ArrayList<PersonHasAttribute>();
			while (result.next()) {
				ret.add(new PersonHasAttribute(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasPrivilege.
	 * <p>
	 * This is the subject of PersonHasPrivilege.
	 
	 * @return The object regarding PersonHasPrivilege where this (the java-object you call objectsOfPersonHasPrivilege on) is the subject.
	 * @since Iteration3
	 */
	public Privilege objectOfPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Privilege> coll = objectsOfPersonHasPrivilege();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonHasPrivilege. If you know that there will be only
	 * 1:1 relationships of the type PersonHasPrivilege, {@link #objectOfPersonHasPrivilege} is what you are looking for.
	 * <p>
	 * This is the subject of PersonHasPrivilege.
	 * @return The object regarding PersonHasPrivilege where this (the java-object you call objectsOfPersonHasPrivilege on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Privilege> objectsOfPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Privilege\""
			+ " WHERE id IN (SELECT privilege FROM "
			+ "\"scetris\".\"personHasPrivilege\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Privilege> ret = new java.util.ArrayList<Privilege>();
			while (result.next()) {
				ret.add(new Privilege(manager, result));
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
	public java.util.Collection<PersonHasPrivilege> whereSubjectOfPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasPrivilege\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonHasPrivilege> ret = new java.util.ArrayList<PersonHasPrivilege>();
			while (result.next()) {
				ret.add(new PersonHasPrivilege(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasRole.
	 * <p>
	 * This is the subject of PersonHasRole.
	 
	 * @return The object regarding PersonHasRole where this (the java-object you call objectsOfPersonHasRole on) is the subject.
	 * @since Iteration3
	 */
	public Role objectOfPersonHasRole() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Role> coll = objectsOfPersonHasRole();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonHasRole. If you know that there will be only
	 * 1:1 relationships of the type PersonHasRole, {@link #objectOfPersonHasRole} is what you are looking for.
	 * <p>
	 * This is the subject of PersonHasRole.
	 * @return The object regarding PersonHasRole where this (the java-object you call objectsOfPersonHasRole on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Role> objectsOfPersonHasRole() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Role\""
			+ " WHERE id IN (SELECT role FROM "
			+ "\"scetris\".\"personHasRole\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Role> ret = new java.util.ArrayList<Role>();
			while (result.next()) {
				ret.add(new Role(manager, result));
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
	public java.util.Collection<PersonHasRole> whereSubjectOfPersonHasRole() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasRole\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonHasRole> ret = new java.util.ArrayList<PersonHasRole>();
			while (result.next()) {
				ret.add(new PersonHasRole(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonPrefersTimeslot.
	 * <p>
	 * This is the subject of PersonPrefersTimeslot.
	 
	 * @return The object regarding PersonPrefersTimeslot where this (the java-object you call objectsOfPersonPrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public Timeslot objectOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Timeslot> coll = objectsOfPersonPrefersTimeslot();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonPrefersTimeslot. If you know that there will be only
	 * 1:1 relationships of the type PersonPrefersTimeslot, {@link #objectOfPersonPrefersTimeslot} is what you are looking for.
	 * <p>
	 * This is the subject of PersonPrefersTimeslot.
	 * @return The object regarding PersonPrefersTimeslot where this (the java-object you call objectsOfPersonPrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Timeslot> objectsOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE id IN (SELECT timeslot FROM "
			+ "\"scetris\".\"personPrefersTimeslot\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<Timeslot> ret = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				ret.add(new Timeslot(manager, result));
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
	public java.util.Collection<PersonPrefersTimeslot> whereSubjectOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonPrefersTimeslot> ret = new java.util.ArrayList<PersonPrefersTimeslot>();
			while (result.next()) {
				ret.add(new PersonPrefersTimeslot(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonSuccessfullyPassedCourse.
	 * <p>
	 * This is the subject of PersonSuccessfullyPassedCourse.
	 
	 * @return The object regarding PersonSuccessfullyPassedCourse where this (the java-object you call objectsOfPersonSuccessfullyPassedCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<Course> coll = objectsOfPersonSuccessfullyPassedCourse();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonSuccessfullyPassedCourse. If you know that there will be only
	 * 1:1 relationships of the type PersonSuccessfullyPassedCourse, {@link #objectOfPersonSuccessfullyPassedCourse} is what you are looking for.
	 * <p>
	 * This is the subject of PersonSuccessfullyPassedCourse.
	 * @return The object regarding PersonSuccessfullyPassedCourse where this (the java-object you call objectsOfPersonSuccessfullyPassedCourse on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<Course> objectsOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"personSuccessfullyPassedCourse\"" + " WHERE user = ?);";
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
	public java.util.Collection<PersonSuccessfullyPassedCourse> whereSubjectOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonSuccessfullyPassedCourse> ret = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
			while (result.next()) {
				ret.add(new PersonSuccessfullyPassedCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonTakesPartInElementInstance.
	 * <p>
	 * This is the subject of PersonTakesPartInElementInstance.
	 
	 * @return The object regarding PersonTakesPartInElementInstance where this (the java-object you call objectsOfPersonTakesPartInElementInstance on) is the subject.
	 * @since Iteration3
	 */
	public CourseElementInstance objectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		java.util.Collection<CourseElementInstance> coll = objectsOfPersonTakesPartInElementInstance();
		if (coll.isEmpty()) {
			return null;
		}
		return coll.iterator().next();
	}
	
	/**
	 * Retrieves all objects which are related to this object as via PersonTakesPartInElementInstance. If you know that there will be only
	 * 1:1 relationships of the type PersonTakesPartInElementInstance, {@link #objectOfPersonTakesPartInElementInstance} is what you are looking for.
	 * <p>
	 * This is the subject of PersonTakesPartInElementInstance.
	 * @return The object regarding PersonTakesPartInElementInstance where this (the java-object you call objectsOfPersonTakesPartInElementInstance on) is the subject.
	 * @since Iteration3
	 */
	public java.util.Collection<CourseElementInstance> objectsOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"personTakesPartInElementInstance\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				ret.add(new CourseElementInstance(manager, result));
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
	public java.util.Collection<PersonTakesPartInElementInstance> whereSubjectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.Collection<PersonTakesPartInElementInstance> ret = new java.util.ArrayList<PersonTakesPartInElementInstance>();
			while (result.next()) {
				ret.add(new PersonTakesPartInElementInstance(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}
	
	
	
	@Override
	public boolean hasPrivilege(String privilege) {
		String query = "SELECT may(?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			
			java.sql.ResultSet result = stmt.executeQuery();
			result.next();
			return result.getBoolean(1);
		} catch (java.sql.SQLException e) {
			return false;
		}
	}

	@Override
	public boolean hasPrivilege(String privilege, String target) {
		String query = "SELECT may(?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			
			java.sql.ResultSet result = stmt.executeQuery();
			result.next();
			return result.getBoolean(1);
		} catch (java.sql.SQLException e) {
			return false;
		}
	}
	
	@Override
	public boolean isSuperUser() {
		return getIsSuperuser();
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true)
	public int getId() {
		return _id;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIdChanged() {
		return changed_id;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	@de.fu.weave.orm.annotation.Attribute(name = "ctime", serial = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "mtime", serial = true)
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

	
	@de.fu.weave.orm.annotation.Attribute(name = "created_by", serial = true, nullable = true, ref = Person.class)
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
	public boolean isCreatedByChanged() {
		return changed_createdBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("createdBy")
	public Integer refCreatedBy() {
		return ref_createdBy;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "modified_by", serial = true, nullable = true, ref = Person.class)
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
	public boolean isModifiedByChanged() {
		return changed_modifiedBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("modifiedBy")
	public Integer refModifiedBy() {
		return ref_modifiedBy;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("firstName")
	@de.fu.weave.orm.annotation.Attribute(name = "first_name", serial = true)
	public String getFirstName() {
		return _firstName;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isFirstNameChanged() {
		return changed_firstName;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("additionalNames")
	@de.fu.weave.orm.annotation.Attribute(name = "additional_names", serial = true, nullable = true)
	public String getAdditionalNames() {
		if (_additionalNames == null) {
			return null;
		}
		return _additionalNames;
	}
	
	public boolean hasAdditionalNames() {
		return _additionalNames != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAdditionalNamesChanged() {
		return changed_additionalNames;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("lastName")
	@de.fu.weave.orm.annotation.Attribute(name = "last_name", serial = true)
	public String getLastName() {
		return _lastName;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLastNameChanged() {
		return changed_lastName;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("emailAddress")
	@de.fu.weave.orm.annotation.Attribute(name = "email_address", serial = true, nullable = true)
	public String getEmailAddress() {
		if (_emailAddress == null) {
			return null;
		}
		return _emailAddress;
	}
	
	public boolean hasEmailAddress() {
		return _emailAddress != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isEmailAddressChanged() {
		return changed_emailAddress;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("loginName")
	@de.fu.weave.orm.annotation.Attribute(name = "login_name", serial = true)
	public String getLoginName() {
		return _loginName;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLoginNameChanged() {
		return changed_loginName;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("loginPassword")
	@de.fu.weave.orm.annotation.Attribute(name = "login_password", serial = true)
	public String getLoginPassword() {
		return _loginPassword;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLoginPasswordChanged() {
		return changed_loginPassword;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("isSuperuser")
	@de.fu.weave.orm.annotation.Attribute(name = "is_superuser", serial = true)
	public boolean getIsSuperuser() {
		return _isSuperuser;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIsSuperuserChanged() {
		return changed_isSuperuser;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("deleted")
	@de.fu.weave.orm.annotation.Attribute(name = "deleted", serial = true, nullable = true)
	public java.sql.Timestamp getDeleted() {
		if (_deleted == null) {
			return null;
		}
		return _deleted;
	}
	
	public boolean hasDeleted() {
		return _deleted != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDeletedChanged() {
		return changed_deleted;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "deleted_by", serial = true, nullable = true, ref = Person.class)
	public Person getDeletedBy() throws de.fu.weave.orm.DatabaseException {
		if (ref_deletedBy == null) {
			return null;
		}
		if (!fetched_deletedBy) {
			_deletedBy = manager.getPerson(ref_deletedBy);
			fetched_deletedBy = true;
		}
		return _deletedBy;
	}
	
	public boolean hasDeletedBy() {
		return _deletedBy != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDeletedByChanged() {
		return changed_deletedBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("deletedBy")
	public Integer refDeletedBy() {
		return ref_deletedBy;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("workingHours")
	@de.fu.weave.orm.annotation.Attribute(name = "working_hours", serial = true, nullable = true)
	public Integer getWorkingHours() {
		if (_workingHours == null) {
			return null;
		}
		return _workingHours;
	}
	
	public boolean hasWorkingHours() {
		return _workingHours != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isWorkingHoursChanged() {
		return changed_workingHours;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("studentId")
	@de.fu.weave.orm.annotation.Attribute(name = "student_id", serial = true, nullable = true)
	public String getStudentId() {
		if (_studentId == null) {
			return null;
		}
		return _studentId;
	}
	
	public boolean hasStudentId() {
		return _studentId != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStudentIdChanged() {
		return changed_studentId;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("staffId")
	@de.fu.weave.orm.annotation.Attribute(name = "staff_id", serial = true, nullable = true)
	public String getStaffId() {
		if (_staffId == null) {
			return null;
		}
		return _staffId;
	}
	
	public boolean hasStaffId() {
		return _staffId != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isStaffIdChanged() {
		return changed_staffId;
	}

	
	/**
	 *
	 * @since Iteration2
	 */
	public void setId(int value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("id is not nullable, null given");
		}
		changed_id = true;
		_id = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCtime(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		changed_ctime = true;
		_ctime = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setMtime(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("mtime is not nullable, null given");
		}
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
	 * createdBy may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearCreatedBy() {
		_createdBy = null;
		ref_createdBy = null;
		changed_createdBy = true;
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
	 * modifiedBy may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearModifiedBy() {
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setFirstName(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("firstName is not nullable, null given");
		}
		changed_firstName = true;
		_firstName = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setAdditionalNames(String value) {
		if (value == null) {
			_additionalNames = null;
			return;
		}
		
		changed_additionalNames = true;
		_additionalNames = value;
	}
	
	/**
	 * additionalNames may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearAdditionalNames() {
		_additionalNames = null;
		changed_additionalNames = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setLastName(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("lastName is not nullable, null given");
		}
		changed_lastName = true;
		_lastName = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setEmailAddress(String value) {
		if (value == null) {
			_emailAddress = null;
			return;
		}
		
		changed_emailAddress = true;
		_emailAddress = value;
	}
	
	/**
	 * emailAddress may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearEmailAddress() {
		_emailAddress = null;
		changed_emailAddress = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setLoginName(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginName is not nullable, null given");
		}
		changed_loginName = true;
		_loginName = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setLoginPassword(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginPassword is not nullable, null given");
		}
		changed_loginPassword = true;
		_loginPassword = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setIsSuperuser(boolean value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("isSuperuser is not nullable, null given");
		}
		changed_isSuperuser = true;
		_isSuperuser = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setDeleted(java.sql.Timestamp value) {
		if (value == null) {
			_deleted = null;
			return;
		}
		
		changed_deleted = true;
		_deleted = value;
	}
	
	/**
	 * deleted may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearDeleted() {
		_deleted = null;
		changed_deleted = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setDeletedBy(Person value) {
		if (value == null) {
			_deletedBy = null;
			ref_deletedBy = null;
			return;
		}
		
		changed_deletedBy = true;
		ref_deletedBy = value._id;
		fetched_deletedBy = true;
		_deletedBy = value;
	}
	
	/**
	 * deletedBy may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearDeletedBy() {
		_deletedBy = null;
		ref_deletedBy = null;
		changed_deletedBy = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setWorkingHours(Integer value) {
		if (value == null) {
			_workingHours = null;
			return;
		}
		
		changed_workingHours = true;
		_workingHours = value;
	}
	
	/**
	 * workingHours may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearWorkingHours() {
		_workingHours = null;
		changed_workingHours = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStudentId(String value) {
		if (value == null) {
			_studentId = null;
			return;
		}
		
		changed_studentId = true;
		_studentId = value;
	}
	
	/**
	 * studentId may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearStudentId() {
		_studentId = null;
		changed_studentId = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setStaffId(String value) {
		if (value == null) {
			_staffId = null;
			return;
		}
		
		changed_staffId = true;
		_staffId = value;
	}
	
	/**
	 * staffId may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearStaffId() {
		_staffId = null;
		changed_staffId = true;
	}

}
