/* PersonHasPrivilege.java / 2010-10-30+02:00
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
 * OO-Representation of the relationship-relation PersonHasPrivilege
 * <p>
 * 
			This relation tells us that the given Person has the given Privilege.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personHasPrivilege", subject = Person.class, object = Privilege.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"personHasPrivilege\"")
@de.fu.weave.xml.annotation.XmlElement("personHasPrivilege")
public class PersonHasPrivilege extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _user;
	boolean changed_user = false;
	boolean fetched_user = false;
	int ref_user;
	/**
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Privilege _privilege;
	boolean changed_privilege = false;
	boolean fetched_privilege = false;
	int ref_privilege;
	/**
	 * date of creation
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _ctime;
	boolean changed_ctime = false;
	boolean fetched_ctime = false;
	
	/**
	 * date of last edit
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _mtime;
	boolean changed_mtime = false;
	boolean fetched_mtime = false;
	
	/**
	 * initially created by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _createdBy = null;
	boolean changed_createdBy = false;
	boolean fetched_createdBy = false;
	Integer ref_createdBy;
	/**
	 * least recently modified by
	 * <p>
	 * 
	 * @since Iteration2
	 */
	Person _modifiedBy = null;
	boolean changed_modifiedBy = false;
	boolean fetched_modifiedBy = false;
	Integer ref_modifiedBy;
	/**
	 * target
	 * <p>
	 * 
				The target of this relation (should be a Person) will gain the new Privilege.
			
	 * @since Iteration2
	 */
	String _target = null;
	boolean changed_target = false;
	boolean fetched_target = false;
	
	/**
	 * because of role
	 * <p>
	 * 
				This is a explanation because of which Role the Privilege was gained.
			
	 * @since Iteration2
	 */
	Boolean _becauseOfRole = null;
	boolean changed_becauseOfRole = false;
	boolean fetched_becauseOfRole = false;
	
	
	
	Person subject;
	int subject_refid;
	Privilege object;
	int object_refid;
	
	
	PersonHasPrivilege(RelationManager manager, Person subject, Privilege object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_user = result.getInt("user");
			ref_privilege = result.getInt("privilege");
			_ctime = result.getTimestamp("ctime");
			fetched_ctime = true;
			_mtime = result.getTimestamp("mtime");
			fetched_mtime = true;
			ref_createdBy = result.getInt("created_by");
			ref_modifiedBy = result.getInt("modified_by");
			_target = result.getString("target");
			fetched_target = true;
			_becauseOfRole = result.getBoolean("because_of_role");
			fetched_becauseOfRole = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	PersonHasPrivilege(RelationManager manager, Person subject, Privilege object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	PersonHasPrivilege(RelationManager manager, boolean full, Person subject, Privilege object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy, String _target, Boolean _becauseOfRole) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._ctime = _ctime;
		this.fetched_ctime = true;
		this._mtime = _mtime;
		this.fetched_mtime = true;
		this._createdBy = _createdBy;
		this.ref_createdBy = _createdBy.id();
		this.fetched_createdBy = true;
		this._target = _target;
		this.fetched_target = true;
		this._becauseOfRole = _becauseOfRole;
		this.fetched_becauseOfRole = true;
	}
	
	@Override
	public Person subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Privilege object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getPrivilege(object_refid);
		}
		return object;
	}
	
	@Deprecated
	public Person getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Privilege getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonHasPrivilege) {
			return equals((PersonHasPrivilege) obj);
		}
		return false;
	}
	
	public boolean equals(PersonHasPrivilege relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personHasPrivilege\"" + " (\"user\", \"privilege\", \"ctime\", \"mtime\", \"created_by\", \"target\", \"because_of_role\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
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
			
			if (_target != null) {
				stmt.setString(i++, _target);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (_becauseOfRole != null) {
				stmt.setBoolean(i++, _becauseOfRole);
			} else {
				stmt.setNull(i++, java.sql.Types.BOOLEAN);
				
			}
			
			
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"personHasPrivilege\"" + " SET \"ctime\" = ?, \"mtime\" = ?, \"created_by\" = ?, \"target\" = ?, \"because_of_role\" = ? WHERE "
			+ " AND user = ?"
			+ " AND privilege = ?"
			+ " AND target = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setTimestamp(++i, _ctime);
				stmt.setTimestamp(++i, _mtime);
			if (ref_createdBy != null) {
				stmt.setInt(++i, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
			}
			if (_target != null) {
				stmt.setString(++i, _target);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
			if (_becauseOfRole != null) {
				stmt.setBoolean(++i, _becauseOfRole);
			} else {
				stmt.setNull(i++, java.sql.Types.BOOLEAN);
			}
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_privilege);
			stmt.setString(++i, _target);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personHasPrivilege\""
			+ " WHERE "
			+ " AND user = ?"
			+ " AND privilege = ?"
			+ " AND target = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_privilege);
			stmt.setString(++i, _target);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	public Person getUser() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_user) {
			_user = manager.getPerson(ref_user);
			fetched_user = true;
		}
		return _user;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedUser() {
		return changed_user;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("user")
	public int refUser() {
		return ref_user;
	}

	
	public Privilege getPrivilege() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_privilege) {
			_privilege = manager.getPrivilege(ref_privilege);
			fetched_privilege = true;
		}
		return _privilege;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedPrivilege() {
		return changed_privilege;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("privilege")
	public int refPrivilege() {
		return ref_privilege;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("ctime")
	public java.sql.Timestamp getCtime() {
		return _ctime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedCtime() {
		return changed_ctime;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("mtime")
	public java.sql.Timestamp getMtime() {
		return _mtime;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedMtime() {
		return changed_mtime;
	}

	
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
	public boolean isChangedCreatedBy() {
		return changed_createdBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("createdBy")
	public Integer refCreatedBy() {
		return ref_createdBy;
	}

	
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
	public boolean isChangedModifiedBy() {
		return changed_modifiedBy;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("modifiedBy")
	public Integer refModifiedBy() {
		return ref_modifiedBy;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("target")
	public String getTarget() {
		if (_target == null) {
			return null;
		}
		return _target;
	}
	
	public boolean hasTarget() {
		return _target != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedTarget() {
		return changed_target;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("becauseOfRole")
	public Boolean getBecauseOfRole() {
		if (_becauseOfRole == null) {
			return null;
		}
		return _becauseOfRole;
	}
	
	public boolean hasBecauseOfRole() {
		return _becauseOfRole != null;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedBecauseOfRole() {
		return changed_becauseOfRole;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setUser(Person value) {
		
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPrivilege(Privilege value) {
		
		changed_privilege = true;
		ref_privilege = value._id;
		fetched_privilege = true;
		_privilege = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setCtime(java.sql.Timestamp value) {
		
		changed_ctime = true;
		_ctime = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setMtime(java.sql.Timestamp value) {
		
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
	public void setTarget(String value) {
		if (value == null) {
			_target = null;
			return;
		}
		changed_target = true;
		_target = value;
	}
	
	/**
	 * target may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearTarget() {
		_target = null;
		changed_target = true;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setBecauseOfRole(Boolean value) {
		if (value == null) {
			_becauseOfRole = null;
			return;
		}
		changed_becauseOfRole = true;
		_becauseOfRole = value;
	}
	
	/**
	 * becauseOfRole may be null, use this function to clear it’s value.
	 * @since Iteration3
	 */
	public void clearBecauseOfRole() {
		_becauseOfRole = null;
		changed_becauseOfRole = true;
	}

}
