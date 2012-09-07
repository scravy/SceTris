/* Person.java / 2011-03-03+01:00
 * Part of SCORE myCourses
 * 
 * Team Scetris: David Bialik, Julian Fleischer,
 * Hagen Mahnke, Konrad Reiche, André Zoufahl
 *
 * This file was automatically generated
 * using libxslt
 */

/**
 * @formatter:off
 */
package de.fu.scetris.data;

/**
 * OO-Representation of the entity-relation Person
 * <p>
 * 
A Person is a natural person who is able to login to myCourses. Therefore she is having login credentials (e.g. username and password or mail address and password). Every Person has a first name and a last name. There are many different roles at a university and there are persons who act in more than one role (e.g. there are students doing lectures, while not being professors). Every person needs to have a unique identifier regardless of the roles they belong to for administration reasons.
		
 */
@de.fu.weave.orm.annotation.Entity(name = "Person")
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"Person\"", requiredSqlCols = {"first_name", "last_name", "login_name", "login_password"})
@de.fu.weave.xml.annotation.XmlElement("Person")
public class Person extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity , de.fu.weave.User {
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
	public static final String HomeDepartment = "home_department";
	public static final String CurrentYear = "current_year";
	public static final String LastLogin = "last_login";
	public static final String LoginCount = "login_count";
	public static final String IsSuperuser = "is_superuser";
	public static final String Deleted = "deleted";
	public static final String DeletedBy = "deleted_by";
	public static final String IsStudent = "is_student";
	public static final String IsLecturer = "is_lecturer";
	

	/**
	 * 
	 */
	public abstract static class Form extends de.fu.scetris.data.formsupport.AbstractForm {
		public static final long serialVersionUID = 3303272L;
		
		
		@de.fu.weave.Form.Pos(1)
		@de.fu.weave.Form.Field("id") @de.fu.weave.Form.Required
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.HIDDEN)
		@de.fu.weave.Form.ActiveValidator("id$validator")
		@de.fu.weave.Form.ActiveConverter("id$converter")
		public int id;
		
		public java.sql.Timestamp ctime;
		
		public java.sql.Timestamp mtime;
		
		@de.fu.weave.Form.Alternatives("createdBy$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer createdBy;
		
		public java.util.Map<Integer,java.lang.String> createdBy$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Alternatives("modifiedBy$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer modifiedBy;
		
		public java.util.Map<Integer,java.lang.String> modifiedBy$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(6)
		@de.fu.weave.Form.Field("firstName") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("firstName$validator")
		@de.fu.weave.Form.ActiveConverter("firstName$converter")
		public String firstName;
		
		@de.fu.weave.Form.Pos(7)
		@de.fu.weave.Form.Field("additionalNames")
		@de.fu.weave.Form.ActiveValidator("additionalNames$validator")
		@de.fu.weave.Form.ActiveConverter("additionalNames$converter")
		public String additionalNames;
		
		@de.fu.weave.Form.Pos(8)
		@de.fu.weave.Form.Field("lastName") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("lastName$validator")
		@de.fu.weave.Form.ActiveConverter("lastName$converter")
		public String lastName;
		
		@de.fu.weave.Form.Pos(9)
		@de.fu.weave.Form.Field("emailAddress")
		@de.fu.weave.Form.ActiveValidator("emailAddress$validator")
		@de.fu.weave.Form.ActiveConverter("emailAddress$converter")
		public de.fu.junction.data.EMailAddress emailAddress;
		
		@de.fu.weave.Form.Pos(10)
		@de.fu.weave.Form.Field("loginName") @de.fu.weave.Form.Required
		@de.fu.weave.Form.Min(3)
		@de.fu.weave.Form.Max(40)
		@de.fu.weave.Form.Match("^[a-zA-Z][a-zA-Z0-9]+([\\.\\-_][a-zA-Z][a-zA-Z0-9]+)*$")
		@de.fu.weave.Form.ActiveValidator("loginName$validator")
		@de.fu.weave.Form.ActiveConverter("loginName$converter")
		public String loginName;
		
		@de.fu.weave.Form.Pos(11)
		@de.fu.weave.Form.Field("loginPassword") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("loginPassword$validator")
		@de.fu.weave.Form.ActiveConverter("loginPassword$converter")
		public de.fu.junction.data.Password loginPassword;
		
		@de.fu.weave.Form.Pos(12)
		@de.fu.weave.Form.Field("homeDepartment")
		@de.fu.weave.Form.ActiveValidator("homeDepartment$validator")
		@de.fu.weave.Form.ActiveConverter("homeDepartment$converter")
		@de.fu.weave.Form.Alternatives("homeDepartment$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer homeDepartment;
		
		public java.util.Map<Integer,java.lang.String> homeDepartment$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(13)
		@de.fu.weave.Form.Field("currentYear")
		@de.fu.weave.Form.ActiveValidator("currentYear$validator")
		@de.fu.weave.Form.ActiveConverter("currentYear$converter")
		@de.fu.weave.Form.Alternatives("currentYear$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer currentYear;
		
		public java.util.Map<Integer,java.lang.String> currentYear$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(14)
		@de.fu.weave.Form.Field("lastLogin")
		@de.fu.weave.Form.ActiveValidator("lastLogin$validator")
		@de.fu.weave.Form.ActiveConverter("lastLogin$converter")
		public java.sql.Timestamp lastLogin;
		
		@de.fu.weave.Form.Pos(15)
		@de.fu.weave.Form.Field("loginCount") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("loginCount$validator")
		@de.fu.weave.Form.ActiveConverter("loginCount$converter")
		public int loginCount;
		
		@de.fu.weave.Form.Pos(16)
		@de.fu.weave.Form.Field("isSuperuser") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("isSuperuser$validator")
		@de.fu.weave.Form.ActiveConverter("isSuperuser$converter")
		public boolean isSuperuser;
		
		public java.sql.Timestamp deleted;
		
		@de.fu.weave.Form.Alternatives("deletedBy$alternatives")
		@de.fu.weave.Form.FormControl(de.fu.weave.Form.Control.ALTERNATIVES)
		public Integer deletedBy;
		
		public java.util.Map<Integer,java.lang.String> deletedBy$alternatives = new java.util.TreeMap<Integer,java.lang.String>();
		
		@de.fu.weave.Form.Pos(19)
		@de.fu.weave.Form.Field("isStudent") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("isStudent$validator")
		@de.fu.weave.Form.ActiveConverter("isStudent$converter")
		public boolean isStudent;
		
		@de.fu.weave.Form.Pos(20)
		@de.fu.weave.Form.Field("isLecturer") @de.fu.weave.Form.Required
		@de.fu.weave.Form.ActiveValidator("isLecturer$validator")
		@de.fu.weave.Form.ActiveConverter("isLecturer$converter")
		public boolean isLecturer;
		
		
		public Form setValues(Person $object) {
			
			id = $object.getId();
			ctime = $object.getCtime();
			mtime = $object.getMtime();
			createdBy = $object.refCreatedBy();
			modifiedBy = $object.refModifiedBy();
			firstName = $object.getFirstName();
			additionalNames = $object.getAdditionalNames();
			lastName = $object.getLastName();
			try {
			emailAddress = !$object.hasEmailAddress() ? null : new de.fu.junction.data.EMailAddress($object.getEmailAddress());
			} catch (Exception $exc) { }
			loginName = $object.getLoginName();
			try {
			loginPassword = new de.fu.junction.data.Password($object.getLoginPassword());
			} catch (Exception $exc) { }
			homeDepartment = $object.refHomeDepartment();
			currentYear = $object.refCurrentYear();
			lastLogin = $object.getLastLogin();
			loginCount = $object.getLoginCount();
			isSuperuser = $object.getIsSuperuser();
			deleted = $object.getDeleted();
			deletedBy = $object.refDeletedBy();
			isStudent = $object.getIsStudent();
			isLecturer = $object.getIsLecturer();
			return this;
		}
	}
	
	/**
	 * numeric identifier
	 * <p>
	 * 
				An artificial key introduced to allow easy referencing of an entity.
				Once an id has been set it won’t change, therfore avoiding update anomalies.
			
	 */
	int _id;
	boolean changed_id = false;
	boolean fetched_id = false;/**
	 * date of creation
	 * <p>
	 * 
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;/**
	 * date of last edit
	 * <p>
	 * 
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;/**
	 * initially created by
	 * <p>
	 * 
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;/**
	 * least recently modified by
	 * <p>
	 * 
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;/**
	 * first name
	 * <p>
	 * 
				The first name of a Person.
			
	 */
	String _firstName;
	boolean changed_firstName = false;
	boolean fetched_firstName = false;/**
	 * additional names
	 * <p>
	 * 
				Additional names of a Person.
			
	 */
	String _additionalNames = null;
	boolean changed_additionalNames = false;
	boolean fetched_additionalNames = false;/**
	 * last name
	 * <p>
	 * 
				The last name of a Person.
			
	 */
	String _lastName;
	boolean changed_lastName = false;
	boolean fetched_lastName = false;/**
	 * email address
	 * <p>
	 * 
				The email address of a Person. It has to be unique.
			
	 */
	String _emailAddress = null;
	boolean changed_emailAddress = false;
	boolean fetched_emailAddress = false;/**
	 * login name
	 * <p>
	 * 
				The name that is used to log into the system. It has to be unique.
			
	 */
	String _loginName;
	boolean changed_loginName = false;
	boolean fetched_loginName = false;/**
	 * password
	 * <p>
	 * 
				The password that is used together with the login name in order to log in into the system.
			
	 */
	String _loginPassword;
	boolean changed_loginPassword = false;
	boolean fetched_loginPassword = false;/**
	 * Home department
	 * <p>
	 * 
				The department a student is registered at.
			
	 */
	Department _homeDepartment = null;
	boolean changed_homeDepartment = false;
	boolean fetched_homeDepartment = false;
	Integer ref_homeDepartment;/**
	 * 
	 * <p>
	 * 
	 */
	Year _currentYear = null;
	boolean changed_currentYear = false;
	boolean fetched_currentYear = false;
	Integer ref_currentYear;/**
	 * date of most recent login
	 * <p>
	 * 
	 */
	java.sql.Timestamp _lastLogin = null;
	boolean changed_lastLogin = false;
	boolean fetched_lastLogin = false;/**
	 * Zähler über die Anzahl der logins.
	 * <p>
	 * 
	 */
	int _loginCount;
	boolean changed_loginCount = false;
	boolean fetched_loginCount = false;/**
	 * is administrator
	 * <p>
	 * 
				Attribute that shows whether the Person is a superuser or not.
			
	 */
	boolean _isSuperuser;
	boolean changed_isSuperuser = false;
	boolean fetched_isSuperuser = false;/**
	 * deleted
	 * <p>
	 * 
				Attribute that shows on which date a Person was deleted from the system.
			
	 */
	java.sql.Timestamp _deleted = null;
	boolean changed_deleted = false;
	boolean fetched_deleted = false;/**
	 * deleted by
	 * <p>
	 * 
				Attribute that shows by which Person the User was deleted.
			
	 */
	Person _deletedBy = null;
	boolean changed_deletedBy = false;
	boolean fetched_deletedBy = false;
	Integer ref_deletedBy;/**
	 * student number
	 * <p>
	 * 
				The number identifies the Person (student) uniquely.
			
	 */
	boolean _isStudent;
	boolean changed_isStudent = false;
	boolean fetched_isStudent = false;/**
	 * deleted by
	 * <p>
	 * 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 */
	boolean _isLecturer;
	boolean changed_isLecturer = false;
	boolean fetched_isLecturer = false;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 */
	Person(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
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
			ref_homeDepartment = result.getInt("home_department");
			if (result.wasNull()) {
				ref_homeDepartment = null;
			}
			ref_currentYear = result.getInt("current_year");
			if (result.wasNull()) {
				ref_currentYear = null;
			}
			_lastLogin = result.getTimestamp("last_login");
			if (result.wasNull()) {
				_lastLogin = null;
			}
			fetched_lastLogin = true;
			_loginCount = result.getInt("login_count");
			fetched_loginCount = true;
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
			_isStudent = result.getBoolean("is_student");
			fetched_isStudent = true;
			_isLecturer = result.getBoolean("is_lecturer");
			fetched_isLecturer = true;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
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
			
	 * @param _homeDepartment 
				The department a student is registered at.
			
	 * @param _currentYear 
	 * @param _lastLogin 
	 * @param _loginCount 
	 * @param _isSuperuser 
				Attribute that shows whether the Person is a superuser or not.
			
	 * @param _deleted 
				Attribute that shows on which date a Person was deleted from the system.
			
	 * @param _deletedBy 
				Attribute that shows by which Person the User was deleted.
			
	 * @param _isStudent 
				The number identifies the Person (student) uniquely.
			
	 * @param _isLecturer 
				If part of the staff the staff ID will identify the Persion uniquely.
			
	 */
	Person(RelationManager manager, final boolean full, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _firstName, String _additionalNames, String _lastName, String _emailAddress, String _loginName, String _loginPassword, Department _homeDepartment, Year _currentYear, java.sql.Timestamp _lastLogin, int _loginCount, boolean _isSuperuser, java.sql.Timestamp _deleted, Person _deletedBy, boolean _isStudent, boolean _isLecturer) {
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
			this._homeDepartment = _homeDepartment;
			this.ref_homeDepartment = _homeDepartment.id();
			this.fetched_homeDepartment = true;
			this._currentYear = _currentYear;
			this.ref_currentYear = _currentYear.id();
			this.fetched_currentYear = true;
			this._lastLogin = _lastLogin;
			this.fetched_lastLogin = true;
			this._loginCount = _loginCount;
			this.fetched_loginCount = true;
			this._isSuperuser = _isSuperuser;
			this.fetched_isSuperuser = true;
			this._deleted = _deleted;
			this.fetched_deleted = true;
			this._deletedBy = _deletedBy;
			this.ref_deletedBy = _deletedBy.id();
			this.fetched_deletedBy = true;
			this._isStudent = _isStudent;
			this.fetched_isStudent = true;
			this._isLecturer = _isLecturer;
			this.fetched_isLecturer = true;
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
		return de.fu.weave.orm.GenericRelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newPerson} rather than {@link RelationManager#createPerson).
	 */
	@Override
	public Person create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Person\"" + " (\"ctime\", \"created_by\", \"modified_by\", \"first_name\", \"additional_names\", \"last_name\", \"email_address\", \"login_name\", \"login_password\", \"home_department\", \"current_year\", \"last_login\", \"login_count\", \"is_superuser\", \"deleted\", \"deleted_by\", \"is_student\", \"is_lecturer\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
			
			if (ref_homeDepartment != null) {
				stmt.setInt(i++, ref_homeDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_currentYear != null) {
				stmt.setInt(i++, ref_currentYear);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_lastLogin != null) {
				stmt.setTimestamp(i++, _lastLogin);
			} else {
				stmt.setNull(i++, java.sql.Types.TIMESTAMP);
				
			}
			
			if (manager.isNull(_loginCount)) {
				stmt.setInt(i++, _loginCount = 0);
			} else {
				stmt.setInt(i++, _loginCount);
			}
			
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
			
			if (manager.isNull(_isStudent)) {
				stmt.setBoolean(i++, _isStudent = false);
			} else {
				stmt.setBoolean(i++, _isStudent);
			}
			
			if (manager.isNull(_isLecturer)) {
				stmt.setBoolean(i++, _isLecturer = false);
			} else {
				stmt.setBoolean(i++, _isLecturer);
			}
			
			stmt.setTimestamp(i++, _mtime);
			java.sql.ResultSet keys = manager.executeQuery(stmt);
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.weave.orm.DatabaseException("no key was generated. phail.");
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
			changed_homeDepartment = false;
			changed_currentYear = false;
			changed_lastLogin = false;
			changed_loginCount = false;
			changed_isSuperuser = false;
			changed_deleted = false;
			changed_deletedBy = false;
			changed_isStudent = false;
			changed_isLecturer = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_mtime = timekey;
		pushChanges();
	}
	/**
	 * Updates the associated data inside the database
	 */
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
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
			+ ", \"home_department\" = ?"
			+ ", \"current_year\" = ?"
			+ ", \"last_login\" = ?"
			+ ", \"login_count\" = ?"
			+ ", \"is_superuser\" = ?"
			+ ", \"deleted\" = ?"
			+ ", \"deleted_by\" = ?"
			+ ", \"is_student\" = ?"
			+ ", \"is_lecturer\" = ?"
			+ ", \"mtime\" = ? "
			+ "WHERE \"mtime\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
			
			if (ref_homeDepartment != null) {
				stmt.setInt(i++, ref_homeDepartment);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (ref_currentYear != null) {
				stmt.setInt(i++, ref_currentYear);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
				
			}
			
			if (_lastLogin != null) {
				stmt.setTimestamp(i++, _lastLogin);
			} else {
				stmt.setNull(i++, java.sql.Types.TIMESTAMP);
				
			}
			
			if (manager.isNull(_loginCount)) {
				stmt.setInt(i++, 0);
			} else {
				stmt.setInt(i++, _loginCount);
			}
			
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
			
			if (manager.isNull(_isStudent)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _isStudent);
			}
			
			if (manager.isNull(_isLecturer)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _isLecturer);
			}
			
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, this.id());
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException();
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
			changed_homeDepartment = false;
			changed_currentYear = false;
			changed_lastLogin = false;
			changed_loginCount = false;
			changed_isSuperuser = false;
			changed_deleted = false;
			changed_deletedBy = false;
			changed_isStudent = false;
			changed_isLecturer = false;
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	/**
	 */
	public void pullChanges() throws de.fu.weave.orm.DatabaseException {
		pullChanges(0);
	}
	
	/**
	 *
	 */
	@Override
	public void pullChanges(int depth) throws de.fu.weave.orm.DatabaseException {
		throw new RuntimeException("Not yet implemented");
	}
	
	/**
	 * Deletes the associated data inside the database
	 */
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"Person\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
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
		return de.fu.weave.orm.GenericRelationManager.compareValues(id(), entity.id());
	}
	
	public int compareTo(de.fu.weave.orm.Entity entity) {
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
	 * Retrieves the first java-object which is related to this java-object as via PersonEnrolledInCourseInstance.
	 * <p>
	 * This is the subject of PersonEnrolledInCourseInstance.
	 
	 * @return The object regarding PersonEnrolledInCourseInstance where this (the java-object you call objectsOfPersonEnrolledInCourseInstance on) is the subject.
	 * @since Iteration3
	 */
	public CourseInstance objectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
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
	 */
	public java.util.List<CourseInstance> objectsOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseInstance\""
			+ " WHERE \"id\" IN (SELECT \"course_instance\" FROM "
			+ "\"scetris\".\"personEnrolledInCourseInstance\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<CourseInstance> ret = new java.util.ArrayList<CourseInstance>();
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
	 */
	public java.util.List<PersonEnrolledInCourseInstance> whereSubjectOfPersonEnrolledInCourseInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personEnrolledInCourseInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonEnrolledInCourseInstance> ret = new java.util.ArrayList<PersonEnrolledInCourseInstance>();
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
	 */
	public java.util.List<Course> objectsOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE \"id\" IN (SELECT \"course\" FROM "
			+ "\"scetris\".\"personGivesCourse\"" + " WHERE \"person\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Course> ret = new java.util.ArrayList<Course>();
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
	 */
	public java.util.List<PersonGivesCourse> whereSubjectOfPersonGivesCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personGivesCourse\""
			+ " WHERE \"person\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonGivesCourse> ret = new java.util.ArrayList<PersonGivesCourse>();
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
	 */
	public java.util.List<Attribute> objectsOfPersonHasAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Attribute\""
			+ " WHERE \"id\" IN (SELECT \"attribute\" FROM "
			+ "\"scetris\".\"personHasAttribute\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Attribute> ret = new java.util.ArrayList<Attribute>();
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
	 */
	public java.util.List<PersonHasAttribute> whereSubjectOfPersonHasAttribute() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasAttribute\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonHasAttribute> ret = new java.util.ArrayList<PersonHasAttribute>();
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
	 */
	public java.util.List<Privilege> objectsOfPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Privilege\""
			+ " WHERE \"id\" IN (SELECT \"privilege\" FROM "
			+ "\"scetris\".\"personHasPrivilege\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Privilege> ret = new java.util.ArrayList<Privilege>();
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
	 */
	public java.util.List<PersonHasPrivilege> whereSubjectOfPersonHasPrivilege() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasPrivilege\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonHasPrivilege> ret = new java.util.ArrayList<PersonHasPrivilege>();
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
	 */
	public java.util.List<Role> objectsOfPersonHasRole() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Role\""
			+ " WHERE \"id\" IN (SELECT \"role\" FROM "
			+ "\"scetris\".\"personHasRole\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Role> ret = new java.util.ArrayList<Role>();
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
	 */
	public java.util.List<PersonHasRole> whereSubjectOfPersonHasRole() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personHasRole\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonHasRole> ret = new java.util.ArrayList<PersonHasRole>();
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
	 */
	public java.util.List<Timeslot> objectsOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Timeslot\""
			+ " WHERE \"id\" IN (SELECT \"timeslot\" FROM "
			+ "\"scetris\".\"personPrefersTimeslot\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Timeslot> ret = new java.util.ArrayList<Timeslot>();
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
	 */
	public java.util.List<PersonPrefersTimeslot> whereSubjectOfPersonPrefersTimeslot() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personPrefersTimeslot\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonPrefersTimeslot> ret = new java.util.ArrayList<PersonPrefersTimeslot>();
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
	 */
	public java.util.List<Course> objectsOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"Course\""
			+ " WHERE \"id\" IN (SELECT \"course\" FROM "
			+ "\"scetris\".\"personSuccessfullyPassedCourse\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<Course> ret = new java.util.ArrayList<Course>();
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
	 */
	public java.util.List<PersonSuccessfullyPassedCourse> whereSubjectOfPersonSuccessfullyPassedCourse() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personSuccessfullyPassedCourse\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonSuccessfullyPassedCourse> ret = new java.util.ArrayList<PersonSuccessfullyPassedCourse>();
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
	 */
	public java.util.List<CourseElementInstance> objectsOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"CourseElementInstance\""
			+ " WHERE \"id\" IN (SELECT \"element_instance\" FROM "
			+ "\"scetris\".\"personTakesPartInElementInstance\"" + " WHERE \"user\" = ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<CourseElementInstance> ret = new java.util.ArrayList<CourseElementInstance>();
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
	 */
	public java.util.List<PersonTakesPartInElementInstance> whereSubjectOfPersonTakesPartInElementInstance() throws de.fu.weave.orm.DatabaseException {
		String query = "SELECT * FROM " + "\"scetris\".\"personTakesPartInElementInstance\""
			+ " WHERE \"user\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setInt(1, id());
			java.sql.ResultSet result = manager.executeQuery(stmt);
			java.util.List<PersonTakesPartInElementInstance> ret = new java.util.ArrayList<PersonTakesPartInElementInstance>();
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
		if (isSuperUser()) {
			return true;
		}
		String query = "SELECT may(?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			
			java.sql.ResultSet result = manager.executeQuery(stmt);
			result.next();
			return result.getBoolean(1);
		} catch (java.sql.SQLException e) {
			return false;
		}
	}

	@Override
	public boolean hasPrivilege(String privilege, Object target) {
		return hasPrivilege(privilege, target.toString());
	}

	@Override
	public boolean hasPrivilege(String privilege, String target) {
		if (isSuperUser()) {
			return true;
		}
		String query = "SELECT may(?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			stmt.setString(1, getLoginName());
			stmt.setString(2, privilege);
			stmt.setString(3, target);
			
			java.sql.ResultSet result = manager.executeQuery(stmt);
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
	@de.fu.weave.orm.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
	}
	
	
	
	
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	@de.fu.weave.orm.annotation.Attribute(name = "ctime", serial = true, hidden = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "mtime", serial = true, hidden = true, use = "timestamp")
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
		return de.fu.weave.orm.GenericRelationManager.compareValues(_mtime, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
	}

	
	@de.fu.weave.xml.annotation.XmlElement("createdBy")
	@de.fu.weave.xml.annotation.XmlDependency("isCreatedByFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "created_by", serial = true, nullable = true, hidden = true, ref = Person.class)
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
	@de.fu.weave.orm.annotation.Attribute(name = "modified_by", serial = true, nullable = true, hidden = true, ref = Person.class)
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
	@de.fu.weave.orm.annotation.Attribute(name = "email_address", serial = true, nullable = true, use = "user.login")
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
	@de.fu.weave.orm.annotation.Attribute(name = "login_name", serial = true, use = "user.login")
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
	

	
	@de.fu.weave.orm.annotation.Attribute(name = "login_password", serial = true, use = "user.password")
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("homeDepartment")
	@de.fu.weave.xml.annotation.XmlDependency("isHomeDepartmentFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "home_department", serial = true, nullable = true, ref = Department.class)
	public Department getHomeDepartment() throws de.fu.weave.orm.DatabaseException {
		if (ref_homeDepartment == null) {
			return null;
		}
		if (!fetched_homeDepartment) {
			_homeDepartment = manager.getDepartment(ref_homeDepartment);
			fetched_homeDepartment = true;
		}
		return _homeDepartment;
	}
	
	public boolean hasHomeDepartment() {
		return _homeDepartment != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isHomeDepartmentFetched() {
		return fetched_homeDepartment;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isHomeDepartmentChanged() {
		return changed_homeDepartment;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("homeDepartment")
	public Integer refHomeDepartment() {
		return ref_homeDepartment;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("currentYear")
	@de.fu.weave.xml.annotation.XmlDependency("isCurrentYearFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "current_year", serial = true, nullable = true, ref = Year.class)
	public Year getCurrentYear() throws de.fu.weave.orm.DatabaseException {
		if (ref_currentYear == null) {
			return null;
		}
		if (!fetched_currentYear) {
			_currentYear = manager.getYear(ref_currentYear);
			fetched_currentYear = true;
		}
		return _currentYear;
	}
	
	public boolean hasCurrentYear() {
		return _currentYear != null;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isCurrentYearFetched() {
		return fetched_currentYear;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isCurrentYearChanged() {
		return changed_currentYear;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("currentYear")
	public Integer refCurrentYear() {
		return ref_currentYear;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("lastLogin")
	@de.fu.weave.orm.annotation.Attribute(name = "last_login", serial = true, nullable = true)
	public java.sql.Timestamp getLastLogin() {
		if (_lastLogin == null) {
			return null;
		}
		return _lastLogin;
	}
	
	public boolean hasLastLogin() {
		return _lastLogin != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLastLoginChanged() {
		return changed_lastLogin;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("loginCount")
	@de.fu.weave.orm.annotation.Attribute(name = "login_count", serial = true)
	public int getLoginCount() {
		return _loginCount;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isLoginCountChanged() {
		return changed_loginCount;
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
	@de.fu.weave.orm.annotation.Attribute(name = "deleted", serial = true, nullable = true, hidden = true)
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
	@de.fu.weave.orm.annotation.Attribute(name = "deleted_by", serial = true, nullable = true, hidden = true, ref = Person.class)
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("isStudent")
	@de.fu.weave.orm.annotation.Attribute(name = "is_student", serial = true)
	public boolean getIsStudent() {
		return _isStudent;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIsStudentChanged() {
		return changed_isStudent;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("isLecturer")
	@de.fu.weave.orm.annotation.Attribute(name = "is_lecturer", serial = true)
	public boolean getIsLecturer() {
		return _isLecturer;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isIsLecturerChanged() {
		return changed_isLecturer;
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
	public Person setCtime(java.sql.Timestamp value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("ctime is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_ctime, value) == 0) return this;
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
	public Person setCreatedBy(Person value) {
		
		if (value == null) {
			clearCreatedBy();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_createdBy, value._id) == 0) return this;
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
	public Person clearCreatedBy() {
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
	public Person setModifiedBy(Person value) {
		
		if (value == null) {
			clearModifiedBy();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_modifiedBy, value._id) == 0) return this;
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
	public Person clearModifiedBy() {
		if (_modifiedBy == null && ref_modifiedBy == null) {
			return this;
		}
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
		return this;
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
	public Person setFirstName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("firstName is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_firstName, value) == 0) return this;
		changed_firstName = true;
		_firstName = value;
		return this;
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
	public Person setAdditionalNames(String value) {
		
		if (value == null) {
			clearAdditionalNames();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_additionalNames, value) == 0) return this;
		changed_additionalNames = true;
		_additionalNames = value;
		return this;
	}
	
	/**
	 * additionalNames may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearAdditionalNames() {
		if (_additionalNames == null) {
			return this;
		}
		_additionalNames = null;
		changed_additionalNames = true;
		return this;
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
	public Person setLastName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("lastName is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_lastName, value) == 0) return this;
		changed_lastName = true;
		_lastName = value;
		return this;
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
	public Person setEmailAddress(String value) {
		
		if (value == null) {
			clearEmailAddress();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_emailAddress, value) == 0) return this;
		changed_emailAddress = true;
		_emailAddress = value;
		return this;
	}
	
	/**
	 * emailAddress may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearEmailAddress() {
		if (_emailAddress == null) {
			return this;
		}
		_emailAddress = null;
		changed_emailAddress = true;
		return this;
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
	public Person setLoginName(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginName is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_loginName, value) == 0) return this;
		changed_loginName = true;
		_loginName = value;
		return this;
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
	public Person setLoginPassword(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginPassword is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_loginPassword, value) == 0) return this;
		changed_loginPassword = true;
		_loginPassword = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>homeDepartment</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isHomeDepartmentChanged()} will return true. If you specify
	 * the same value as the old value isHomeDepartmentChanged() will return the same as it did
	 * before calling <code>setHomeDepartment(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Person setHomeDepartment(Department value) {
		
		if (value == null) {
			clearHomeDepartment();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_homeDepartment, value._id) == 0) return this;
		changed_homeDepartment = true;
		ref_homeDepartment = value._id;
		fetched_homeDepartment = true;
		_homeDepartment = value;
		return this;
	}
	
	/**
	 * homeDepartment may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearHomeDepartment() {
		if (_homeDepartment == null && ref_homeDepartment == null) {
			return this;
		}
		_homeDepartment = null;
		ref_homeDepartment = null;
		changed_homeDepartment = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>currentYear</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isCurrentYearChanged()} will return true. If you specify
	 * the same value as the old value isCurrentYearChanged() will return the same as it did
	 * before calling <code>setCurrentYear(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Person setCurrentYear(Year value) {
		
		if (value == null) {
			clearCurrentYear();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_currentYear, value._id) == 0) return this;
		changed_currentYear = true;
		ref_currentYear = value._id;
		fetched_currentYear = true;
		_currentYear = value;
		return this;
	}
	
	/**
	 * currentYear may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearCurrentYear() {
		if (_currentYear == null && ref_currentYear == null) {
			return this;
		}
		_currentYear = null;
		ref_currentYear = null;
		changed_currentYear = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>lastLogin</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLastLoginChanged()} will return true. If you specify
	 * the same value as the old value isLastLoginChanged() will return the same as it did
	 * before calling <code>setLastLogin(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public Person setLastLogin(java.sql.Timestamp value) {
		
		if (value == null) {
			clearLastLogin();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_lastLogin, value) == 0) return this;
		changed_lastLogin = true;
		_lastLogin = value;
		return this;
	}
	
	/**
	 * lastLogin may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearLastLogin() {
		if (_lastLogin == null) {
			return this;
		}
		_lastLogin = null;
		changed_lastLogin = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>loginCount</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isLoginCountChanged()} will return true. If you specify
	 * the same value as the old value isLoginCountChanged() will return the same as it did
	 * before calling <code>setLoginCount(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>loginCount</code> is not nullable)
	 * @since Iteration2
	 */
	public Person setLoginCount(int value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("loginCount is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_loginCount, value) == 0) return this;
		changed_loginCount = true;
		_loginCount = value;
		return this;
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
	public Person setIsSuperuser(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("isSuperuser is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_isSuperuser, value) == 0) return this;
		changed_isSuperuser = true;
		_isSuperuser = value;
		return this;
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
	public Person setDeleted(java.sql.Timestamp value) {
		
		if (value == null) {
			clearDeleted();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_deleted, value) == 0) return this;
		changed_deleted = true;
		_deleted = value;
		return this;
	}
	
	/**
	 * deleted may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearDeleted() {
		if (_deleted == null) {
			return this;
		}
		_deleted = null;
		changed_deleted = true;
		return this;
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
	public Person setDeletedBy(Person value) {
		
		if (value == null) {
			clearDeletedBy();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_deletedBy, value._id) == 0) return this;
		changed_deletedBy = true;
		ref_deletedBy = value._id;
		fetched_deletedBy = true;
		_deletedBy = value;
		return this;
	}
	
	/**
	 * deletedBy may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public Person clearDeletedBy() {
		if (_deletedBy == null && ref_deletedBy == null) {
			return this;
		}
		_deletedBy = null;
		ref_deletedBy = null;
		changed_deletedBy = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>isStudent</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isIsStudentChanged()} will return true. If you specify
	 * the same value as the old value isIsStudentChanged() will return the same as it did
	 * before calling <code>setIsStudent(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>isStudent</code> is not nullable)
	 * @since Iteration2
	 */
	public Person setIsStudent(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("isStudent is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_isStudent, value) == 0) return this;
		changed_isStudent = true;
		_isStudent = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>isLecturer</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isIsLecturerChanged()} will return true. If you specify
	 * the same value as the old value isIsLecturerChanged() will return the same as it did
	 * before calling <code>setIsLecturer(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>isLecturer</code> is not nullable)
	 * @since Iteration2
	 */
	public Person setIsLecturer(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("isLecturer is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_isLecturer, value) == 0) return this;
		changed_isLecturer = true;
		_isLecturer = value;
		return this;
	}

}
