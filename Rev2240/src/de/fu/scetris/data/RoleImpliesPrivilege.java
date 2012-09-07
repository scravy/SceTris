/* RoleImpliesPrivilege.java / 2011-03-03+01:00
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
 * OO-Representation of the relationship-relation RoleImpliesPrivilege
 * <p>
 * 
			This relation tells us what Privileges are implied by a given Role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roleImpliesPrivilege", subject = Role.class, object = Privilege.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"roleImpliesPrivilege\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("roleImpliesPrivilege")
public class RoleImpliesPrivilege extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Role = "role";
	public static final String Privilege = "privilege";
	public static final String Timekey = "timekey";
	public static final String Target = "target";
	

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
		timekey(true);
		try {
			ref_role = result.getInt("role");
			ref_privilege = result.getInt("privilege");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_target = result.getString("target");
			fetched_target = true;	
			this.subject_refid = ref_role;
			this.object_refid = ref_privilege;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	RoleImpliesPrivilege(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_role = result.getInt("\"role\"");
			this.ref_privilege = result.getInt("\"privilege\"");
			this._timekey = result.getTimestamp("\"timekey\"");
			this.fetched_timekey = true;
			this._target = result.getString("\"target\"");
			this.fetched_target = true;
			this.subject_refid = ref_role;
			this.object_refid = ref_privilege;
			this.subject = manager.getRole(this.subject_refid);
			this.object = manager.getPrivilege(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	RoleImpliesPrivilege(RelationManager manager, Role subject, Privilege object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._role = this.subject = subject;
		this.ref_role = this.subject_refid = subject.id();
		this._privilege = this.object = object;
		this.ref_privilege = this.object_refid = object.id();
		timekey(true);
		
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
		timekey(true);
		
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
	public RoleImpliesPrivilege create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roleImpliesPrivilege\"" + " (\"role\", \"privilege\","
			+ "\"target\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
			if (manager.isNull(_target)) {
				stmt.setString(i++, _target = "");
			} else {
				stmt.setString(i++, _target);
			}
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = true;
		return this;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.weave.orm.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"roleImpliesPrivilege\"" + " SET \"target\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"role\" = ?"
			+ " AND \"privilege\" = ?"
			+ " AND \"target\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
				stmt.setString(i++, _target);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_role);
			stmt.setInt(i++, ref_privilege);
			stmt.setString(i++, _target);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"roleImpliesPrivilege\"" + '#' + id());
			}
			_timekey = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_timekey = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"roleImpliesPrivilege\""
			+ " WHERE "
			+ " \"role\" = ? AND "
			+ " \"privilege\" = ? AND "
			+ " \"target\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_role);
			stmt.setInt(i++, ref_privilege);
			stmt.setString(i++, _target);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("role")
	@de.fu.weave.xml.annotation.XmlDependency("isRoleFetched")
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
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isRoleFetched() {
		return fetched_role;
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("privilege")
	@de.fu.weave.xml.annotation.XmlDependency("isPrivilegeFetched")
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
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isPrivilegeFetched() {
		return fetched_privilege;
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
	
	public java.sql.Timestamp timekey() {
		return _timekey;
	}
	
	public boolean isValidTimekey(java.sql.Timestamp key) {
		return de.fu.weave.orm.GenericRelationManager.compareValues(_timekey, key) == 0;
	}
	
	public void checkTimekey(java.sql.Timestamp key) throws de.fu.weave.orm.OutdatedRecordException {
		if (!isValidTimekey(key)) {
			throw new de.fu.weave.orm.OutdatedRecordException();
		}
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
	 * Sets the value of <code>role</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRoleChanged()} will return true. If you specify
	 * the same value as the old value isRoleChanged() will return the same as it did
	 * before calling <code>setRole(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>role</code> is not nullable)
	 * @since Iteration2
	 */
	public RoleImpliesPrivilege setRole(Role value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("role is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_role, value._id) == 0) return this;
		changed_role = true;
		ref_role = value._id;
		fetched_role = true;
		_role = value;
		return this;
	}

	
	/**
	 * Sets the value of <code>privilege</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isPrivilegeChanged()} will return true. If you specify
	 * the same value as the old value isPrivilegeChanged() will return the same as it did
	 * before calling <code>setPrivilege(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>privilege</code> is not nullable)
	 * @since Iteration2
	 */
	public RoleImpliesPrivilege setPrivilege(Privilege value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("privilege is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_privilege, value._id) == 0) return this;
		changed_privilege = true;
		ref_privilege = value._id;
		fetched_privilege = true;
		_privilege = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>target</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isTargetChanged()} will return true. If you specify
	 * the same value as the old value isTargetChanged() will return the same as it did
	 * before calling <code>setTarget(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>target</code> is not nullable)
	 * @since Iteration2
	 */
	public RoleImpliesPrivilege setTarget(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("target is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_target, value) == 0) return this;
		changed_target = true;
		_target = value;
		return this;
	}

}
