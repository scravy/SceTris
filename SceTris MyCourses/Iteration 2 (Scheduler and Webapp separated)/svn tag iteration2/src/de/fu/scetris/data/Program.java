/* Program.java / 2010-10-30+02:00
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
@de.fu.weave.orm.annotation.Entity(name = "Program")
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"Program\"")
@de.fu.weave.xml.annotation.XmlElement("Program")
public class Program extends de.fu.weave.orm.GenericEntity implements de.fu.weave.orm.Entity  {
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
	Program(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
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
			throw new de.fu.weave.orm.DatabaseException(e);
		}
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
	
	@Override
	public int hashCode() {
		return RelationManager._hashCode(this._id);
	}
	
	/**
	 * Creates the entity in the database if it does not exist in the database (this is the case if it was constructed using {@link RelationManager#newProgram} rather than {@link RelationManager#createProgram).
	 * @since Iteration2
	 */
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"Program\"" + " (\"academic_term\" , \"department\" , \"freezed\" , \"published\" , \"program_manager\" )"
			+ " VALUES (?, ?, ?, ?, ?) RETURNING id;";
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
		String query = "UPDATE " + "\"scetris\".\"Program\"" + " SET \"academic_term\" = ?, \"department\" = ?, \"freezed\" = ?, \"published\" = ?, \"program_manager\" = ? WHERE id = ?;";
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
		String query = "DELETE FROM " + "\"scetris\".\"Program\"" + " WHERE \"id\" = ?;";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, _id);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
	}

	public int compareTo(Program entity) {
		if (equals(entity)) {
			return 0;
		}
		return RelationManager.compareIds(id(), entity.id());
	}

	@Override
	public int compareTo(de.fu.weave.orm.Entity entity) {
		if (entity instanceof Program) {
			return compareTo((Program) entity);
		}
		return getClass().getName().compareTo(entity.getClass().getName());
	}
	
	public boolean equals(Program entity) {
		return entity.id() == id();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Program) {
			return equals((Program) obj);
		}
		return false;
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

	
	public AcademicTerm getAcademicTerm() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_academicTerm) {
			_academicTerm = manager.getAcademicTerm(ref_academicTerm);
			fetched_academicTerm = true;
		}
		return _academicTerm;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedAcademicTerm() {
		return changed_academicTerm;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("academicTerm")
	public int refAcademicTerm() {
		return ref_academicTerm;
	}

	
	public Department getDepartment() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_department) {
			_department = manager.getDepartment(ref_department);
			fetched_department = true;
		}
		return _department;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedDepartment() {
		return changed_department;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("department")
	public int refDepartment() {
		return ref_department;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("freezed")
	public boolean getFreezed() {
		return _freezed;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedFreezed() {
		return changed_freezed;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("published")
	public boolean getPublished() {
		return _published;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedPublished() {
		return changed_published;
	}

	
	public Person getProgramManager() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_programManager) {
			_programManager = manager.getPerson(ref_programManager);
			fetched_programManager = true;
		}
		return _programManager;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedProgramManager() {
		return changed_programManager;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("programManager")
	public int refProgramManager() {
		return ref_programManager;
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
	public void setTimekey(java.sql.Timestamp value) {
		
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setAcademicTerm(AcademicTerm value) {
		
		changed_academicTerm = true;
		ref_academicTerm = value._id;
		fetched_academicTerm = true;
		_academicTerm = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setDepartment(Department value) {
		
		changed_department = true;
		ref_department = value._id;
		fetched_department = true;
		_department = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setFreezed(boolean value) {
		
		changed_freezed = true;
		_freezed = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPublished(boolean value) {
		
		changed_published = true;
		_published = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setProgramManager(Person value) {
		
		changed_programManager = true;
		ref_programManager = value._id;
		fetched_programManager = true;
		_programManager = value;
	}

}
