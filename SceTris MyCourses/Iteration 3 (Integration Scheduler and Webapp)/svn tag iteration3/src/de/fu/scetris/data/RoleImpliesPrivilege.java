/* RoleImpliesPrivilege.java / 2010-11-04+01:00
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
 * OO-Representation of the relationship-relation RoleImpliesPrivilege
 * <p>
 * 
			This relation tells us what Privileges are implied by a given Role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roleImpliesPrivilege", subject = Role.class, object = Privilege.class)
@de.fu.weave.orm.annotation.Relation("\"scetris\".\"roleImpliesPrivilege\"")
@de.fu.weave.xml.annotation.XmlElement("roleImpliesPrivilege")
public class RoleImpliesPrivilege extends de.fu.weave.orm.GenericRelationship {
	final RelationManager manager;

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
	 * 
	 * <p>
	 * 
	 * @since Iteration2
	 */
	java.sql.Timestamp _timekey;
	boolean changed_timekey = false;
	boolean fetched_timekey = false;
	
	/**
	 * target
	 * <p>
	 * 
				The target of this relation (should be a Role) will gain the new Privilege.
			
	 * @since Iteration2
	 */
	String _target;
	boolean changed_target = false;
	boolean fetched_target = false;
	
	
	
	Role subject;
	int subject_refid;
	Privilege object;
	int object_refid;
	
	
	RoleImpliesPrivilege(RelationManager manager, Role subject, Privilege object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		try {
			ref_role = result.getInt("role");
			ref_privilege = result.getInt("privilege");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_target = result.getString("target");
			fetched_target = true;	
			this.subject_refid = result.getInt(1);
			this.object_refid = result.getInt(2);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
	}
	
	RoleImpliesPrivilege(RelationManager manager, Role subject, Privilege object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		
	}
	
	RoleImpliesPrivilege(RelationManager manager, boolean full, Role subject, Privilege object, String _target) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		
		this._target = _target;
		this.fetched_target = true;
	}
	
	@Override
	public Role subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getRole(subject_refid);
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
	public Role getSubject() throws de.fu.weave.orm.DatabaseException {
		return subject();
	}
	
	@Deprecated
	public Privilege getObject() throws de.fu.weave.orm.DatabaseException {
		return object();
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleImpliesPrivilege) {
			return equals((RoleImpliesPrivilege) obj);
		}
		return false;
	}
	
	public boolean equals(RoleImpliesPrivilege relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roleImpliesPrivilege\"" + " (\"role\", \"privilege\", \"target\")"
			+ " VALUES (?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
			if (manager.isNull(_target)) {
				stmt.setString(i++, "");
			} else {
				stmt.setString(i++, _target);
			}
			
			
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
	
		String query = "UPDATE " + "\"scetris\".\"roleImpliesPrivilege\"" + " SET \"target\" = ? WHERE "
			+ " \"role\" = ?  AND "
			+ " \"privilege\" = ?  AND "
			+ " \"target\" = ? ";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
				stmt.setString(++i, _target);
			stmt.setInt(++i, ref_role);
			stmt.setInt(++i, ref_privilege);
			stmt.setString(++i, _target);
			stmt.execute();	
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"roleImpliesPrivilege\""
			+ " WHERE "
			+ " \"role\" = ? AND "
			+ " \"privilege\" = ? AND "
			+ " \"target\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 0;
			stmt.setInt(++i, ref_role);
			stmt.setInt(++i, ref_privilege);
			stmt.setString(++i, _target);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	
	
	@de.fu.weave.orm.annotation.Attribute(name = "role", use = "subject", ref = Role.class)
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
	public boolean isRoleChanged() {
		return changed_role;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("role")
	public int refRole() {
		return ref_role;
	}

	
	@de.fu.weave.orm.annotation.Attribute(name = "privilege", use = "object", ref = Privilege.class)
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
	public boolean isPrivilegeChanged() {
		return changed_privilege;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("privilege")
	public int refPrivilege() {
		return ref_privilege;
	}

	
	@de.fu.weave.xml.annotation.XmlAttribute("timekey")
	@de.fu.weave.orm.annotation.Attribute(name = "timekey", serial = true, hidden = true, use = "timestamp")
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("target")
	@de.fu.weave.orm.annotation.Attribute(name = "target", serial = true)
	public String getTarget() {
		return _target;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isTargetChanged() {
		return changed_target;
	}

	
	
	/**
	 *
	 * @since Iteration2
	 */
	public void setRole(Role value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("role is not nullable, null given");
		}
		changed_role = true;
		ref_role = value._id;
		fetched_role = true;
		_role = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setPrivilege(Privilege value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("privilege is not nullable, null given");
		}
		changed_privilege = true;
		ref_privilege = value._id;
		fetched_privilege = true;
		_privilege = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTimekey(java.sql.Timestamp value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("timekey is not nullable, null given");
		}
		changed_timekey = true;
		_timekey = value;
	}

	/**
	 *
	 * @since Iteration2
	 */
	public void setTarget(String value) {
		
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("target is not nullable, null given");
		}
		changed_target = true;
		_target = value;
	}

}
