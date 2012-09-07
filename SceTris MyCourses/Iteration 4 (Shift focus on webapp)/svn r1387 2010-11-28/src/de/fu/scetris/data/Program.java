/* Program.java / 2010-11-27+01:00
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
 * OO-Representation of the entity-relation Program
 * <p>
 * 
			A Program is a set of CourseInstances. It has a running period (start and end date). A program manager is responsible for a Program. A Program belongs to a Department and to one Department only.
		
 * @since Iteration2
 */
@de.fu.bakery.orm.java.annotation.Entity(name = "Program")
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"Program\"", requiredSqlCols = {"academic_term", "department", "program_manager"})
@de.fu.weave.xml.annotation.XmlElement("Program")
public class Program extends de.fu.bakery.orm.java.GenericEntity implements de.fu.bakery.orm.java.Entity  {
	final RelationManager manager;
	boolean exists = false;

	public static final String Id = "id";
	public static final String Timekey = "timekey";
	public static final String AcademicTerm = "academic_term";
	public static final String Department = "department";
	public static final String Freezed = "freezed";
	public static final String Published = "published";
	public static final String ProgramManager = "program_manager";
	

	/**
	 * @since Iteration4
	 */
	public static class Stub {
		Stub() {
		}
	
		public int Id;public java.sql.Timestamp Timekey;public AcademicTerm AcademicTerm;public Department Department;public boolean Freezed;public boolean Published;public Person ProgramManager;
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
		stub.Timekey = _timekey;
		stub.AcademicTerm = _academicTerm;
		stub.Department = _department;
		stub.Freezed = _freezed;
		stub.Published = _published;
		stub.ProgramManager = _programManager;
		
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
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;/**
	 * belongs to academic term
	 * <p>
	 * 
				The academic turn this Program is running it.
			
	 * @since Iteration2
	 */
	AcademicTerm _academicTerm;
	boolean changed_academicTerm = false;
	boolean fetched_academicTerm = false;
	int ref_academicTerm;/**
	 * belongs to department
	 * <p>
	 * 
				The department to which this Program is belonging.
			
	 * @since Iteration2
	 */
	Department _department;
	boolean changed_department = false;
	boolean fetched_department = false;
	int ref_department;/**
	 * freezed(got ready)
	 * <p>
	 * 
				Whether or not the Program is freezed i.e. no Courses Element Instances will change their place in the Shedule any more.
			
	 * @since Iteration2
	 */
	boolean _freezed;
	boolean changed_freezed = false;
	boolean fetched_freezed = false;/**
	 * published
	 * <p>
	 * 
				Whether or not the Program is already released or not.
			
	 * @since Iteration2
	 */
	boolean _published;
	boolean changed_published = false;
	boolean fetched_published = false;/**
	 * program manager
	 * <p>
	 * 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @since Iteration2
	 */
	Person _programManager;
	boolean changed_programManager = false;
	boolean fetched_programManager = false;
	int ref_programManager;
	
	/**
	 * Constructs the entity from an SQL ResultSet.
	 * <p>
	 * This Constructor is not publicly available as it should be used by {@link RelationManager} only.
	 * @param result The SQL ResultSet
	 * @since Iteration2
	 */
	Program(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			_id = result.getInt("id");
			fetched_id = true;
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			ref_academicTerm = result.getInt("academic_term");
			ref_department = result.getInt("department");
			_freezed = result.getBoolean("freezed");
			fetched_freezed = true;
			_published = result.getBoolean("published");
			fetched_published = true;
			ref_programManager = result.getInt("program_manager");
			
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	/**
	 * Constructs the entity Program and initializes only the required attributes (i.e. the fields which are neither nullable nor have a default value) 
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 * @param _department 
				The department to which this Program is belonging.
			
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @since Iteration2
	 */
	Program(RelationManager manager, AcademicTerm _academicTerm, Department _department, Person _programManager) {
		timekey(true);
		this.manager = manager;
		this._academicTerm = _academicTerm;
		this.ref_academicTerm = _academicTerm.id();
		this.fetched_academicTerm = true;
		this._department = _department;
		this.ref_department = _department.id();
		this.fetched_department = true;
		this._programManager = _programManager;
		this.ref_programManager = _programManager.id();
		this.fetched_programManager = true;
	}
	
	/**
	 * Constructs the entity Program and initializes all attributes.
	 * @param _academicTerm 
				The academic turn this Program is running it.
			
	 * @param _department 
				The department to which this Program is belonging.
			
	 * @param _freezed 
				Whether or not the Program is freezed i.e. no Courses Element Instances will change their place in the Shedule any more.
			
	 * @param _published 
				Whether or not the Program is already released or not.
			
	 * @param _programManager 
				The program manager for this Program i.e. the person which is responsible for it.
			
	 * @since Iteration2
	 */
	Program(RelationManager manager, final boolean full, AcademicTerm _academicTerm, Department _department, boolean _freezed, boolean _published, Person _programManager) {
		timekey(true);
		if (!full) {
			this.manager = null;
		} else {
			this.manager = manager;
			this._academicTerm = _academicTerm;
			this.ref_academicTerm = _academicTerm.id();
			this.fetched_academicTerm = true;
			this._department = _department;
			this.ref_department = _department.id();
			this.fetched_department = true;
			this._freezed = _freezed;
			this.fetched_freezed = true;
			this._published = _published;
			this.fetched_published = true;
			this._programManager = _programManager;
			this.ref_programManager = _programManager.id();
			this.fetched_programManager = true;
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
		return "Program: \"" + this._id + "\" (#" + this._id + ")";
	}
	
	/*@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}*/
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newProgram} rather than {@link RelationManager#createProgram).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Program\"" + " (\"academic_term\", \"department\", \"freezed\", \"published\", \"program_manager\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?, ?) RETURNING id;";
		timekey(true);
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_academicTerm);
			
				stmt.setInt(i++, ref_department);
			
			if (manager.isNull(_freezed)) {
				stmt.setBoolean(i++, _freezed = false);
			} else {
				stmt.setBoolean(i++, _freezed);
			}
			
			if (manager.isNull(_published)) {
				stmt.setBoolean(i++, _published = false);
			} else {
				stmt.setBoolean(i++, _published);
			}
			
				stmt.setInt(i++, ref_programManager);
			
			stmt.setTimestamp(i++, _timekey);
			java.sql.ResultSet keys = stmt.executeQuery();
			if (keys.next()) {
				_id = keys.getInt(1);
			} else {
				throw new de.fu.bakery.orm.java.DatabaseException("no key was generated. phail.");
			}
			changed_academicTerm = false;
			changed_department = false;
			changed_freezed = false;
			changed_published = false;
			changed_programManager = false;
			
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
		String query = "UPDATE " + "\"scetris\".\"Program\"" + " SET \"academic_term\" = ?"
			+ ", \"department\" = ?"
			+ ", \"freezed\" = ?"
			+ ", \"published\" = ?"
			+ ", \"program_manager\" = ?"
			+ ", \"timekey\" = ? "
			+ "WHERE \"timekey\" = ? AND id = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setInt(i++, ref_academicTerm);
			
				stmt.setInt(i++, ref_department);
			
			if (manager.isNull(_freezed)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _freezed);
			}
			
			if (manager.isNull(_published)) {
				stmt.setBoolean(i++, false);
			} else {
				stmt.setBoolean(i++, _published);
			}
			
				stmt.setInt(i++, ref_programManager);
			
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
			changed_academicTerm = false;
			changed_department = false;
			changed_freezed = false;
			changed_published = false;
			changed_programManager = false;
			
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
		String query = "DELETE FROM " + "\"scetris\".\"Program\"" + " WHERE \"id\" = ?;";
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
	
	public int compareTo(Program entity) {
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
		if (entity instanceof Program) {
			return compareTo((Program) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Program entity) {
		if (entity == null) return false;
		if (!exists) return this == entity;
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj == null) return false;
		if (obj instanceof Program) {
			return equals((Program) obj);
		}
		return false;
	}
	
	
	
	
	
	@de.fu.weave.xml.annotation.XmlAttribute("id")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "id", serial = true, primary = true, use = "id")
	public int getId() {
		return _id;
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

	
	@de.fu.weave.xml.annotation.XmlElement("academicTerm")
	@de.fu.weave.xml.annotation.XmlDependency("isAcademicTermFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "academic_term", serial = true, ref = AcademicTerm.class)
	public AcademicTerm getAcademicTerm() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_academicTerm) {
			_academicTerm = manager.getAcademicTerm(ref_academicTerm);
			fetched_academicTerm = true;
		}
		return _academicTerm;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isAcademicTermFetched() {
		return fetched_academicTerm;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAcademicTermChanged() {
		return changed_academicTerm;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("academicTerm")
	public int refAcademicTerm() {
		return ref_academicTerm;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("department")
	@de.fu.weave.xml.annotation.XmlDependency("isDepartmentFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "department", serial = true, ref = Department.class)
	public Department getDepartment() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_department) {
			_department = manager.getDepartment(ref_department);
			fetched_department = true;
		}
		return _department;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isDepartmentFetched() {
		return fetched_department;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDepartmentChanged() {
		return changed_department;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("department")
	public int refDepartment() {
		return ref_department;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("freezed")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "freezed", serial = true)
	public boolean getFreezed() {
		return _freezed;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isFreezedChanged() {
		return changed_freezed;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("published")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "published", serial = true)
	public boolean getPublished() {
		return _published;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isPublishedChanged() {
		return changed_published;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("programManager")
	@de.fu.weave.xml.annotation.XmlDependency("isProgramManagerFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "program_manager", serial = true, ref = Person.class)
	public Person getProgramManager() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_programManager) {
			_programManager = manager.getPerson(ref_programManager);
			fetched_programManager = true;
		}
		return _programManager;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isProgramManagerFetched() {
		return fetched_programManager;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isProgramManagerChanged() {
		return changed_programManager;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("programManager")
	public int refProgramManager() {
		return ref_programManager;
	}
	

	
	
	
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>academicTerm</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isAcademicTermChanged()} will return true. If you specify
	 * the same value as the old value isAcademicTermChanged() will return the same as it did
	 * before calling <code>setAcademicTerm(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>academicTerm</code> is not nullable)
	 * @since Iteration2
	 */
	public void setAcademicTerm(AcademicTerm value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("academicTerm is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_academicTerm, value._id) == 0) return;
		changed_academicTerm = true;
		ref_academicTerm = value._id;
		fetched_academicTerm = true;
		_academicTerm = value;
	}

	
	/**
	 * Sets the value of <code>department</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDepartmentChanged()} will return true. If you specify
	 * the same value as the old value isDepartmentChanged() will return the same as it did
	 * before calling <code>setDepartment(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>department</code> is not nullable)
	 * @since Iteration2
	 */
	public void setDepartment(Department value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("department is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_department, value._id) == 0) return;
		changed_department = true;
		ref_department = value._id;
		fetched_department = true;
		_department = value;
	}

	
	/**
	 * Sets the value of <code>freezed</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isFreezedChanged()} will return true. If you specify
	 * the same value as the old value isFreezedChanged() will return the same as it did
	 * before calling <code>setFreezed(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>freezed</code> is not nullable)
	 * @since Iteration2
	 */
	public void setFreezed(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("freezed is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_freezed, value) == 0) return;
		changed_freezed = true;
		_freezed = value;
	}

	
	/**
	 * Sets the value of <code>published</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPublishedChanged()} will return true. If you specify
	 * the same value as the old value isPublishedChanged() will return the same as it did
	 * before calling <code>setPublished(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>published</code> is not nullable)
	 * @since Iteration2
	 */
	public void setPublished(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("published is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_published, value) == 0) return;
		changed_published = true;
		_published = value;
	}

	
	/**
	 * Sets the value of <code>programManager</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isProgramManagerChanged()} will return true. If you specify
	 * the same value as the old value isProgramManagerChanged() will return the same as it did
	 * before calling <code>setProgramManager(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>programManager</code> is not nullable)
	 * @since Iteration2
	 */
	public void setProgramManager(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("programManager is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_programManager, value._id) == 0) return;
		changed_programManager = true;
		ref_programManager = value._id;
		fetched_programManager = true;
		_programManager = value;
	}

}
