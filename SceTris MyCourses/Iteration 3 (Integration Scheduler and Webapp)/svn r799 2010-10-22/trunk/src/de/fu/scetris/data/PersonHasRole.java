/* PersonHasRole.java / 2010-10-22+02:00
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
 * OO-Representation of the relationship-relation PersonHasRole
 * <p>
 * 
			This relation tells us that the given Person has the given Role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personHasRole", subject = Person.class, object = Role.class)
@de.fu.weave.xml.annotation.XmlElement("personHasRole")
public class PersonHasRole extends de.fu.weave.orm.GenericRelationship {
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
	Role _role;
	boolean changed_role = false;
	boolean fetched_role = false;
	int ref_role;
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
	
	
	Person subject;
	int subject_refid;
	Role object;
	int object_refid;
	
	
	PersonHasRole(RelationManager manager, Person subject, Role object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_user = result.getInt("user");
			ref_role = result.getInt("role");
			_ctime = result.getTimestamp("ctime");
			fetched_ctime = true;
			_mtime = result.getTimestamp("mtime");
			fetched_mtime = true;
			ref_createdBy = result.getInt("created_by");
			ref_modifiedBy = result.getInt("modified_by");	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	PersonHasRole(RelationManager manager, Person subject, Role object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	PersonHasRole(RelationManager manager, boolean full, Person subject, Role object, java.sql.Timestamp _ctime, java.sql.Timestamp _mtime, Person _createdBy) {
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
	}
	
	@Override
	public Person getSubject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Role getObject() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getRole(object_refid);
		}
		return object;
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personHasRole\"" + " (Person, Role, , \"ctime\", \"mtime\", \"created_by\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			stmt.setInt(1, subject.id());
			stmt.setInt(2, object.id());
			int i = 3;
				stmt.setTimestamp(i++, _ctime);
				stmt.setTimestamp(i++, _mtime);
			if (ref_createdBy != null) {
				stmt.setInt(i++, ref_createdBy);
			} else {
				stmt.setNull(i++, java.sql.Types.INTEGER);
			}
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		String query = "UPDATE " + "\"scetris\".\"personHasRole\"" + " SET \"ctime\" = ?, \"mtime\" = ?, \"created_by\" = ? WHERE "
			+ " AND user = ?"
			+ " AND role = ?";
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
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_role);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personHasRole\""
			+ " WHERE "
			+ " AND user = ?"
			+ " AND role = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_user);
			stmt.setInt(++i, ref_role);
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

	
	public Role getRole() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_role) {
			_role = manager.getRole(ref_role);
			fetched_role = true;
		}
		return _role;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isChangedRole() {
		return changed_role;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("role")
	public int refRole() {
		return ref_role;
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
	public void setRole(Role value) {
		
		changed_role = true;
		ref_role = value._id;
		fetched_role = true;
		_role = value;
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

}
