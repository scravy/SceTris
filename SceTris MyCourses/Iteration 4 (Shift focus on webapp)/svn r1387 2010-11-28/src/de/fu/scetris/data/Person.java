/* Person.java / 2010-11-27+01:00
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
@de.fu.bakery.orm.java.annotation.Entity(name = "Person")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Person\"", requiredSqlCols = {"first_name", "last_name", "login_name", "login_password"})
@de.fu.weave.xml.annotation.XmlElement("Person")
public class Person extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity , de.fu.weave.User {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Ctime = "ctime";
	public static final String Mtime = "mtime";
	public static final String CreatedBy = "created_by";
	public static final String ModifiedBy = "modified_by";
	public static final String FirstName = "first_name";
	public static final String AdditionalNames = "additional_names";
	public static final String LastName = "last_name";
	public static final String EmailAddress = "email_address";
	public static final String LoginName = "login_name";
	public static final String LoginPassword = "login_password";
	public static final String IsSuperuser = "is_superuser";
	public static final String Deleted = "deleted";
	public static final String DeletedBy = "deleted_by";
	public static final String WorkingHours = "working_hours";
	public static final String StudentId = "student_id";
	public static final String StaffId = "staff_id";
	

	/**
	 * @since Iteration4
	 */
	public static class Stub {
		Stub() {
		}
	
		public int Id;public java.sql.Timestamp Ctime;public java.sql.Timestamp Mtime;public Person CreatedBy;public Person ModifiedBy;public String FirstName;public String AdditionalNames;public String LastName;public String EmailAddress;public String LoginName;public String LoginPassword;public boolean IsSuperuser;public java.sql.Timestamp Deleted;public Person DeletedBy;public Integer WorkingHours;public String StudentId;public String StaffId;
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
		stub.Ctime = _ctime;
		stub.Mtime = _mtime;
		stub.CreatedBy = _createdBy;
		stub.ModifiedBy = _modifiedBy;
		stub.FirstName = _firstName;
		stub.AdditionalNames = _additionalNames;
		stub.LastName = _lastName;
		stub.EmailAddress = _emailAddress;
		stub.LoginName = _loginName;
		stub.LoginPassword = _loginPassword;
		stub.IsSuperuser = _isSuperuser;
		stub.Deleted = _deleted;
		stub.DeletedBy = _deletedBy;
		stub.WorkingHours = _workingHours;
		stub.StudentId = _studentId;
		stub.StaffId = _staffId;
		
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
	Person(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
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
			_firstName = result.getString("first_name");
			fetched_firstName = true;
			_additionalNames = result.getString("additional_names");
			if (result.wasNull()) {
				_additionalNames = null;
			}
			fetched_additionalNames = true;
			_lastName = result.getString("last_name");
			fetched_lastName = true;
			_emailAddress = result.getString("email_address");
			if (result.wasNull()) {
				_emailAddress = null;
			}
			fetched_emailAddress = true;
			_loginName = result.getString("login_name");
			fetched_loginName = true;
			_loginPassword = result.getString("login_password");
			fetched_loginPassword = true;
			_isSuperuser = result.getBoolean("is_superuser");
			fetched_isSuperuser = true;
			_deleted = result.getTimestamp("deleted");
			if (result.wasNull()) {
				_deleted = null;
			}
			fetched_deleted = true;
			ref_deletedBy = result.getInt("deleted_by");
			if (result.wasNull()) {
				ref_deletedBy = null;
			}
			_workingHours = result.getInt("working_hours");
			if (result.wasNull()) {
				_workingHours = null;
			}
			fetched_workingHours = true;
			_studentId = result.getString("student_id");
			if (result.wasNull()) {
				_studentId = null;
			}
			fetched_studentId = true;
			_staffId = result.getString("staff_id");
			if (result.wasNull()) {
				_staffId = null;
			}
			fetched_staffId = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
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
		timekey(true);
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
	 * @param _createdBy 
	 * @param _modifiedBy 
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
	Person(RelationManager manager, final boolean full, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, Integer _workingHours, String _studentId, String _staffId) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._ctime = _ctime;
			this.fetched_ctime = true;
			this._createdBy = _createdBy;
			this.ref_createdBy = _createdBy.id();
			this.fetched_createdBy = true;
			this._modifiedBy = _modifiedBy;
			this.ref_modifiedBy = _modifiedBy.id();
			this.fetched_modifiedBy = true;
			this._firstName = _firstName;
			this.fetched_firstName = true;
			this._additionalNames = _additionalNames;
			this.fetched_additionalNames = true;
			this._lastName = _lastName;
			this.fetched_lastName = true;
			this._emailAddress = _emailAddress;
			this.fetched_emailAddress = true;
			this._loginName = _loginName;
			this.fetched_loginName = true;
			this._loginPassword = _loginPassword;
			this.fetched_loginPassword = true;
			this._isSuperuser = _isSuperuser;
			this.fetched_isSuperuser = true;
			this._deleted = _deleted;
			this.fetched_deleted = true;
			this._deletedBy = _deletedBy;
			this.ref_deletedBy = _deletedBy.id();
			this.fetched_deletedBy = true;
			this._workingHours = _workingHours;
			this.fetched_workingHours = true;
			this._studentId = _studentId;
			this.fetched_studentId = true;
			this._staffId = _staffId;
			this.fetched_staffId = true;
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
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newPerson} rather than {@link RelationManager#createPerson).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Person\"" + " (\"ctime\", \"created_by\", \"modified_by\", \"first_name\", \"additional_names\", \"last_name\", \"email_address\", \"login_name\", \"login_password\", \"is_superuser\", \"deleted\", \"deleted_by\", \"working_hours\", \"student_id\", \"staff_id\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
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
				stmt.setBoolean(i++, _isSuperuser = false);
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
			
			stmt.setTimestamp(i++, _mtime);
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_ctime = false;
			changed_createdBy = false;
			changed_modifiedBy = false;
			changed_firstName = false;
			changed_additionalNames = false;
			changed_lastName = false;
			changed_emailAddress = false;
			changed_loginName = false;
			changed_loginPassword = false;
			changed_isSuperuser = false;
			changed_deleted = false;
			changed_deletedBy = false;
			changed_workingHours = false;
			changed_studentId = false;
			changed_staffId = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_mtime = timekey;
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
		String query = "UPDATE " + "\"scetris\".\"Person\"" + " SET \"ctime\" = ?"
			+ ", \"created_by\" = ?"
			+ ", \"modified_by\" = ?"
			+ ", \"first_name\" = ?"
			+ ", \"additional_names\" = ?"
			+ ", \"last_name\" = ?"
			+ ", \"email_address\" = ?"
			+ ", \"login_name\" = ?"
			+ ", \"login_password\" = ?"
			+ ", \"is_superuser\" = ?"
			+ ", \"deleted\" = ?"
			+ ", \"deleted_by\" = ?"
			+ ", \"working_hours\" = ?"
			+ ", \"student_id\" = ?"
			+ ", \"staff_id\" = ?"
			+ ", \"mtime\" = ? "
			+ "WHERE \"mtime\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			if (manager.isNull(_ctime)) {
				stmt.setTimestamp(i++, new java.sql.Timestamp(System.currentTimeMillis()));
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
			
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException();
			}
			_mtime = newTimekey;
			changed_ctime = false;
			changed_createdBy = false;
			changed_modifiedBy = false;
			changed_firstName = false;
			changed_additionalNames = false;
			changed_lastName = false;
			changed_emailAddress = false;
			changed_loginName = false;
			changed_loginPassword = false;
			changed_isSuperuser = false;
			changed_deleted = false;
			changed_deletedBy = false;
			changed_workingHours = false;
			changed_studentId = false;
			changed_staffId = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Person\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Person entity) {
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
		if (entity instanceof Person) {
			return compareTo((Person) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Person entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
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
	public Group objectOfPersonBelongsToGroup() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Group> coll = objectsOfPersonBelongsToGroup();
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
	public java.util.List<Group> objectsOfPersonBelongsToGroup() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Group\""
			+ " WHERE id IN (SELECT group FROM "
			+ "\"scetris\".\"personBelongsToGroup\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Group> ret = new java.util.ArrayList<Group>();
			while (result.next()) {
				ret.add(new Group(manager, result));
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
	public java.util.List<PersonBelongsToGroup> whereSubjectOfPersonBelongsToGroup() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personBelongsToGroup\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonBelongsToGroup> ret = new java.util.ArrayList<PersonBelongsToGroup>();
			while (result.next()) {
				ret.add(new PersonBelongsToGroup(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonEnrolledInCourseInstance.
	 * <p>
	 * This is the subject of PersonEnrolledInCourseInstance.
	 
	 * @return The object regarding PersonEnrolledInCourseInstance where this (the java-object you call objectsOfPersonEnrolledInCourseInstance on) is the subject.
	 * @since Iteration3
	 */
	public CourseInstance objectOfPersonEnrolledInCourseInstance() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseInstance> coll = objectsOfPersonEnrolledInCourseInstance();
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
	public java.util.List<CourseInstance> objectsOfPersonEnrolledInCourseInstance() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseInstance\""
			+ " WHERE id IN (SELECT course_instance FROM "
			+ "\"scetris\".\"personEnrolledInCourseInstance\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseInstance> ret = new java.util.ArrayList<CourseInstance>();
			while (result.next()) {
				ret.add(new CourseInstance(manager, result));
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
	public java.util.List<PersonEnrolledInCourseInstance> whereSubjectOfPersonEnrolledInCourseInstance() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonEnrolledInCourseInstance> ret = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
			while (result.next()) {
				ret.add(new PersonEnrolledInCourseInstance(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonGivesCourse.
	 * <p>
	 * This is the subject of PersonGivesCourse.
	 
	 * @return The object regarding PersonGivesCourse where this (the java-object you call objectsOfPersonGivesCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Course> coll = objectsOfPersonGivesCourse();
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
	public java.util.List<Course> objectsOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"personGivesCourse\"" + " WHERE person = ?);";
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
	public java.util.List<PersonGivesCourse> whereSubjectOfPersonGivesCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE \"person\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonGivesCourse> ret = new java.util.ArrayList<PersonGivesCourse>();
			while (result.next()) {
				ret.add(new PersonGivesCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasAttribute.
	 * <p>
	 * This is the subject of PersonHasAttribute.
	 
	 * @return The object regarding PersonHasAttribute where this (the java-object you call objectsOfPersonHasAttribute on) is the subject.
	 * @since Iteration3
	 */
	public Attribute objectOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Attribute> coll = objectsOfPersonHasAttribute();
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
	public java.util.List<Attribute> objectsOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Attribute\""
			+ " WHERE id IN (SELECT attribute FROM "
			+ "\"scetris\".\"personHasAttribute\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Attribute> ret = new java.util.ArrayList<Attribute>();
			while (result.next()) {
				ret.add(new Attribute(manager, result));
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
	public java.util.List<PersonHasAttribute> whereSubjectOfPersonHasAttribute() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasAttribute\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonHasAttribute> ret = new java.util.ArrayList<PersonHasAttribute>();
			while (result.next()) {
				ret.add(new PersonHasAttribute(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasPrivilege.
	 * <p>
	 * This is the subject of PersonHasPrivilege.
	 
	 * @return The object regarding PersonHasPrivilege where this (the java-object you call objectsOfPersonHasPrivilege on) is the subject.
	 * @since Iteration3
	 */
	public Privilege objectOfPersonHasPrivilege() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Privilege> coll = objectsOfPersonHasPrivilege();
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
	public java.util.List<Privilege> objectsOfPersonHasPrivilege() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Privilege\""
			+ " WHERE id IN (SELECT privilege FROM "
			+ "\"scetris\".\"personHasPrivilege\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Privilege> ret = new java.util.ArrayList<Privilege>();
			while (result.next()) {
				ret.add(new Privilege(manager, result));
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
	public java.util.List<PersonHasPrivilege> whereSubjectOfPersonHasPrivilege() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasPrivilege\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonHasPrivilege> ret = new java.util.ArrayList<PersonHasPrivilege>();
			while (result.next()) {
				ret.add(new PersonHasPrivilege(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonHasRole.
	 * <p>
	 * This is the subject of PersonHasRole.
	 
	 * @return The object regarding PersonHasRole where this (the java-object you call objectsOfPersonHasRole on) is the subject.
	 * @since Iteration3
	 */
	public Role objectOfPersonHasRole() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Role> coll = objectsOfPersonHasRole();
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
	public java.util.List<Role> objectsOfPersonHasRole() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Role\""
			+ " WHERE id IN (SELECT role FROM "
			+ "\"scetris\".\"personHasRole\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Role> ret = new java.util.ArrayList<Role>();
			while (result.next()) {
				ret.add(new Role(manager, result));
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
	public java.util.List<PersonHasRole> whereSubjectOfPersonHasRole() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasRole\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonHasRole> ret = new java.util.ArrayList<PersonHasRole>();
			while (result.next()) {
				ret.add(new PersonHasRole(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonPrefersTimeslot.
	 * <p>
	 * This is the subject of PersonPrefersTimeslot.
	 
	 * @return The object regarding PersonPrefersTimeslot where this (the java-object you call objectsOfPersonPrefersTimeslot on) is the subject.
	 * @since Iteration3
	 */
	public Timeslot objectOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Timeslot> coll = objectsOfPersonPrefersTimeslot();
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
	public java.util.List<Timeslot> objectsOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE id IN (SELECT timeslot FROM "
			+ "\"scetris\".\"personPrefersTimeslot\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<Timeslot> ret = new java.util.ArrayList<Timeslot>();
			while (result.next()) {
				ret.add(new Timeslot(manager, result));
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
	public java.util.List<PersonPrefersTimeslot> whereSubjectOfPersonPrefersTimeslot() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonPrefersTimeslot> ret = new java.util.ArrayList<PersonPrefersTimeslot>();
			while (result.next()) {
				ret.add(new PersonPrefersTimeslot(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonSuccessfullyPassedCourse.
	 * <p>
	 * This is the subject of PersonSuccessfullyPassedCourse.
	 
	 * @return The object regarding PersonSuccessfullyPassedCourse where this (the java-object you call objectsOfPersonSuccessfullyPassedCourse on) is the subject.
	 * @since Iteration3
	 */
	public Course objectOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<Course> coll = objectsOfPersonSuccessfullyPassedCourse();
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
	public java.util.List<Course> objectsOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE id IN (SELECT course FROM "
			+ "\"scetris\".\"personSuccessfullyPassedCourse\"" + " WHERE user = ?);";
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
	public java.util.List<PersonSuccessfullyPassedCourse> whereSubjectOfPersonSuccessfullyPassedCourse() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonSuccessfullyPassedCourse> ret = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
			while (result.next()) {
				ret.add(new PersonSuccessfullyPassedCourse(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
	}
	
	/**
	 * Retrieves the first java-object which is related to this java-object as via PersonTakesPartInElementInstance.
	 * <p>
	 * This is the subject of PersonTakesPartInElementInstance.
	 
	 * @return The object regarding PersonTakesPartInElementInstance where this (the java-object you call objectsOfPersonTakesPartInElementInstance on) is the subject.
	 * @since Iteration3
	 */
	public CourseElementInstance objectOfPersonTakesPartInElementInstance() throws de.fu.bakery.orm.java.DatabaseException {
		java.util.List<CourseElementInstance> coll = objectsOfPersonTakesPartInElementInstance();
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
	public java.util.List<CourseElementInstance> objectsOfPersonTakesPartInElementInstance() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE id IN (SELECT element_instance FROM "
			+ "\"scetris\".\"personTakesPartInElementInstance\"" + " WHERE user = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
			while (result.next()) {
				ret.add(new CourseElementInstance(manager, result));
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
	public java.util.List<PersonTakesPartInElementInstance> whereSubjectOfPersonTakesPartInElementInstance() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = stmt.executeQuery();
			java.util.List<PersonTakesPartInElementInstance> ret = new java.util.ArrayList<PersonTakesPartInElementInstance>();
			while (result.next()) {
				ret.add(new PersonTakesPartInElementInstance(manager, this, null, result));
			}
			return ret;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "ctime", serial = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "mtime", serial = true, use = "timestamp")
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "created_by", serial = true, nullable = true, ref = Person.class)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "modified_by", serial = true, nullable = true, ref = Person.class)
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("firstName")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "first_name", serial = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "additional_names", serial = true, nullable = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "last_name", serial = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "email_address", serial = true, nullable = true, use = "user.login")
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "login_name", serial = true, use = "user.login")
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "login_password", serial = true, use = "user.password")
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "is_superuser", serial = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "deleted", serial = true, nullable = true)
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("deletedBy")
	@de.fu.weave.xml.annotation.XmlDependency("isDeletedByFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "deleted_by", serial = true, nullable = true, ref = Person.class)
	public Person getDeletedBy() throws de.fu.bakery.orm.java.DatabaseException {
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
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isDeletedByFetched() {
		return fetched_deletedBy;
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "working_hours", serial = true, nullable = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "student_id", serial = true, nullable = true)
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
	@de.fu.bakery.orm.java.annotation.Attribute(name = "staff_id", serial = true, nullable = true)
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
	public void setCtime(java.sql.Timestamp value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_ctime, value) == 0) return;
		changed_ctime = true;
		_ctime = value;
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
	public void setCreatedBy(Person value) {
		
		if (value == null) {
			clearCreatedBy();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_createdBy, value._id) == 0) return;
		changed_createdBy = true;
		ref_createdBy = value._id;
		fetched_createdBy = true;
		_createdBy = value;
	}
	
	/**
	 * createdBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearCreatedBy() {
		if (_createdBy == null && ref_createdBy == null) {
			return;
		}
		_createdBy = null;
		ref_createdBy = null;
		changed_createdBy = true;
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
	public void setModifiedBy(Person value) {
		
		if (value == null) {
			clearModifiedBy();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_modifiedBy, value._id) == 0) return;
		changed_modifiedBy = true;
		ref_modifiedBy = value._id;
		fetched_modifiedBy = true;
		_modifiedBy = value;
	}
	
	/**
	 * modifiedBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearModifiedBy() {
		if (_modifiedBy == null && ref_modifiedBy == null) {
			return;
		}
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
	}

	
	/**
	 * Sets the value of <code>firstName</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isFirstNameChanged()} will return true. If you specify
	 * the same value as the old value isFirstNameChanged() will return the same as it did
	 * before calling <code>setFirstName(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>firstName</code> is not nullable)
	 * @since Iteration2
	 */
	public void setFirstName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("firstName is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_firstName, value) == 0) return;
		changed_firstName = true;
		_firstName = value;
	}

	
	/**
	 * Sets the value of <code>additionalNames</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isAdditionalNamesChanged()} will return true. If you specify
	 * the same value as the old value isAdditionalNamesChanged() will return the same as it did
	 * before calling <code>setAdditionalNames(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setAdditionalNames(String value) {
		
		if (value == null) {
			clearAdditionalNames();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_additionalNames, value) == 0) return;
		changed_additionalNames = true;
		_additionalNames = value;
	}
	
	/**
	 * additionalNames may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearAdditionalNames() {
		if (_additionalNames == null) {
			return;
		}
		_additionalNames = null;
		changed_additionalNames = true;
	}

	
	/**
	 * Sets the value of <code>lastName</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLastNameChanged()} will return true. If you specify
	 * the same value as the old value isLastNameChanged() will return the same as it did
	 * before calling <code>setLastName(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>lastName</code> is not nullable)
	 * @since Iteration2
	 */
	public void setLastName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("lastName is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_lastName, value) == 0) return;
		changed_lastName = true;
		_lastName = value;
	}

	
	/**
	 * Sets the value of <code>emailAddress</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isEmailAddressChanged()} will return true. If you specify
	 * the same value as the old value isEmailAddressChanged() will return the same as it did
	 * before calling <code>setEmailAddress(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setEmailAddress(String value) {
		
		if (value == null) {
			clearEmailAddress();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_emailAddress, value) == 0) return;
		changed_emailAddress = true;
		_emailAddress = value;
	}
	
	/**
	 * emailAddress may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearEmailAddress() {
		if (_emailAddress == null) {
			return;
		}
		_emailAddress = null;
		changed_emailAddress = true;
	}

	
	/**
	 * Sets the value of <code>loginName</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLoginNameChanged()} will return true. If you specify
	 * the same value as the old value isLoginNameChanged() will return the same as it did
	 * before calling <code>setLoginName(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>loginName</code> is not nullable)
	 * @since Iteration2
	 */
	public void setLoginName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginName is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_loginName, value) == 0) return;
		changed_loginName = true;
		_loginName = value;
	}

	
	/**
	 * Sets the value of <code>loginPassword</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLoginPasswordChanged()} will return true. If you specify
	 * the same value as the old value isLoginPasswordChanged() will return the same as it did
	 * before calling <code>setLoginPassword(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>loginPassword</code> is not nullable)
	 * @since Iteration2
	 */
	public void setLoginPassword(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginPassword is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_loginPassword, value) == 0) return;
		changed_loginPassword = true;
		_loginPassword = value;
	}

	
	/**
	 * Sets the value of <code>isSuperuser</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isIsSuperuserChanged()} will return true. If you specify
	 * the same value as the old value isIsSuperuserChanged() will return the same as it did
	 * before calling <code>setIsSuperuser(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>isSuperuser</code> is not nullable)
	 * @since Iteration2
	 */
	public void setIsSuperuser(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("isSuperuser is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_isSuperuser, value) == 0) return;
		changed_isSuperuser = true;
		_isSuperuser = value;
	}

	
	/**
	 * Sets the value of <code>deleted</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDeletedChanged()} will return true. If you specify
	 * the same value as the old value isDeletedChanged() will return the same as it did
	 * before calling <code>setDeleted(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setDeleted(java.sql.Timestamp value) {
		
		if (value == null) {
			clearDeleted();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_deleted, value) == 0) return;
		changed_deleted = true;
		_deleted = value;
	}
	
	/**
	 * deleted may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearDeleted() {
		if (_deleted == null) {
			return;
		}
		_deleted = null;
		changed_deleted = true;
	}

	
	/**
	 * Sets the value of <code>deletedBy</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDeletedByChanged()} will return true. If you specify
	 * the same value as the old value isDeletedByChanged() will return the same as it did
	 * before calling <code>setDeletedBy(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setDeletedBy(Person value) {
		
		if (value == null) {
			clearDeletedBy();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_deletedBy, value._id) == 0) return;
		changed_deletedBy = true;
		ref_deletedBy = value._id;
		fetched_deletedBy = true;
		_deletedBy = value;
	}
	
	/**
	 * deletedBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearDeletedBy() {
		if (_deletedBy == null && ref_deletedBy == null) {
			return;
		}
		_deletedBy = null;
		ref_deletedBy = null;
		changed_deletedBy = true;
	}

	
	/**
	 * Sets the value of <code>workingHours</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isWorkingHoursChanged()} will return true. If you specify
	 * the same value as the old value isWorkingHoursChanged() will return the same as it did
	 * before calling <code>setWorkingHours(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setWorkingHours(Integer value) {
		
		if (value == null) {
			clearWorkingHours();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_workingHours, value) == 0) return;
		changed_workingHours = true;
		_workingHours = value;
	}
	
	/**
	 * workingHours may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearWorkingHours() {
		if (_workingHours == null) {
			return;
		}
		_workingHours = null;
		changed_workingHours = true;
	}

	
	/**
	 * Sets the value of <code>studentId</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStudentIdChanged()} will return true. If you specify
	 * the same value as the old value isStudentIdChanged() will return the same as it did
	 * before calling <code>setStudentId(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setStudentId(String value) {
		
		if (value == null) {
			clearStudentId();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_studentId, value) == 0) return;
		changed_studentId = true;
		_studentId = value;
	}
	
	/**
	 * studentId may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearStudentId() {
		if (_studentId == null) {
			return;
		}
		_studentId = null;
		changed_studentId = true;
	}

	
	/**
	 * Sets the value of <code>staffId</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isStaffIdChanged()} will return true. If you specify
	 * the same value as the old value isStaffIdChanged() will return the same as it did
	 * before calling <code>setStaffId(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setStaffId(String value) {
		
		if (value == null) {
			clearStaffId();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_staffId, value) == 0) return;
		changed_staffId = true;
		_staffId = value;
	}
	
	/**
	 * staffId may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearStaffId() {
		if (_staffId == null) {
			return;
		}
		_staffId = null;
		changed_staffId = true;
	}

}
