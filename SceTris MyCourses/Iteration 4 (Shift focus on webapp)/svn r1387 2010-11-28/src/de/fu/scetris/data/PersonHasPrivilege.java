/* PersonHasPrivilege.java / 2010-11-27+01:00
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
@de.fu.bakery.orm.java.annotation.Relationship(name = "personHasPrivilege", subject = Person.class, object = Privilege.class)
@de.fu.bakery.orm.java.annotation.Relation(name = "\"scetris\".\"personHasPrivilege\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("personHasPrivilege")
public class PersonHasPrivilege extends de.fu.bakery.orm.java.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String User = "user";
	public static final String Privilege = "privilege";
	public static final String Ctime = "ctime";
	public static final String Mtime = "mtime";
	public static final String CreatedBy = "created_by";
	public static final String ModifiedBy = "modified_by";
	public static final String Target = "target";
	public static final String BecauseOfRole = "because_of_role";
	

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
	String _target;
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
	
	
	PersonHasPrivilege(RelationManager manager, Person subject, Privilege object, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_privilege = result.getInt("privilege");
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
			_target = result.getString("target");
			fetched_target = true;
			_becauseOfRole = result.getBoolean("because_of_role");
			if (result.wasNull()) {
				_becauseOfRole = null;
			}
			fetched_becauseOfRole = true;	
			this.subject_refid = ref_user;
			this.object_refid = ref_privilege;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	PersonHasPrivilege(RelationManager manager, java.sql.ResultSet result) throws de.fu.bakery.orm.java.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_privilege = result.getInt("privilege");
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
			_target = result.getString("target");
			fetched_target = true;
			_becauseOfRole = result.getBoolean("because_of_role");
			if (result.wasNull()) {
				_becauseOfRole = null;
			}
			fetched_becauseOfRole = true;
			// XXX: Tis required subject and object to be stored in the first and second columns. this is rather not SQLish.
			this.subject_refid = result.getInt("\"user\"");
			this.object_refid = result.getInt("\"privilege\"");
			this.subject = manager.getPerson(this.subject_refid);
			this.object = manager.getPrivilege(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(e);
		}
		exists = true;
	}
	
	PersonHasPrivilege(RelationManager manager, Person subject, Privilege object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._user = this.subject = subject;
		this.ref_user = this.subject_refid = subject.id();
		this._privilege = this.object = object;
		this.ref_privilege = this.object_refid = object.id();
		timekey(true);
		
	}
	
	PersonHasPrivilege(RelationManager manager, boolean full, Person subject, Privilege object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy, String _target, Boolean _becauseOfRole) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._ctime = _ctime;
		this.fetched_ctime = true;
		this._createdBy = _createdBy;
		this.ref_createdBy = _createdBy.id();
		this.fetched_createdBy = true;
		this._modifiedBy = _modifiedBy;
		this.ref_modifiedBy = _modifiedBy.id();
		this.fetched_modifiedBy = true;
		this._target = _target;
		this.fetched_target = true;
		this._becauseOfRole = _becauseOfRole;
		this.fetched_becauseOfRole = true;
	}
	
	@Override
	public Person subject() throws de.fu.bakery.orm.java.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Privilege object() throws de.fu.bakery.orm.java.DatabaseException {
		if (object == null) {
			object = manager.getPrivilege(object_refid);
		}
		return object;
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
		} catch (de.fu.bakery.orm.java.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public void create() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personHasPrivilege\"" + " (\"user\", \"privilege\","
			+ "\"ctime\","
			+ "\"created_by\","
			+ "\"modified_by\","
			+ "\"target\","
			+ "\"because_of_role\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
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
			
			if (manager.isNull(_target)) {
				stmt.setString(i++, _target = "");
			} else {
				stmt.setString(i++, _target);
			}
			
			if (_becauseOfRole != null) {
				stmt.setBoolean(i++, _becauseOfRole);
			} else {
				stmt.setNull(i++, java.sql.Types.BOOLEAN);
				
			}
			
			stmt.setTimestamp(i++, timekey(true));
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = true;
	}
	
	@Override
	public boolean exists() {
		return exists;
	}
	
	@Override
	public void pushChanges() throws de.fu.bakery.orm.java.DatabaseException {
		if (!exists) {
			create();
			return;
		}
		String query = "UPDATE " + "\"scetris\".\"personHasPrivilege\"" + " SET \"ctime\" = ?, "
			+ "\"created_by\" = ?, "
			+ "\"modified_by\" = ?, "
			+ "\"target\" = ?, "
			+ "\"because_of_role\" = ?, "
			+ ""
			+ "\"mtime\" = ? WHERE "
			+ "\"mtime\" = ?"
			+ " AND \"user\" = ?"
			+ " AND \"privilege\" = ?"
			+ " AND \"target\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
				stmt.setTimestamp(i++, _ctime);
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
				stmt.setString(i++, _target);
			if (_becauseOfRole != null) {
				stmt.setBoolean(i++, _becauseOfRole);
			} else {
				stmt.setNull(i++, java.sql.Types.BOOLEAN);
			}
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_privilege);
			stmt.setString(i++, _target);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.bakery.orm.java.OutdatedRecordException("\"scetris\".\"personHasPrivilege\"" + '#' + id());
			}
			_mtime = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.bakery.orm.java.DatabaseException {
		_mtime = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.bakery.orm.java.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personHasPrivilege\""
			+ " WHERE "
			+ " \"user\" = ? AND "
			+ " \"privilege\" = ? AND "
			+ " \"target\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager.getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_privilege);
			stmt.setString(i++, _target);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.bakery.orm.java.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("user")
	@de.fu.weave.xml.annotation.XmlDependency("isUserFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "user", use = "subject", ref = Person.class)
	public Person getUser() throws de.fu.bakery.orm.java.DatabaseException {
		if (!fetched_user) {
			_user = manager.getPerson(ref_user);
			fetched_user = true;
		}
		return _user;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isUserFetched() {
		return fetched_user;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isUserChanged() {
		return changed_user;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("user")
	public int refUser() {
		return ref_user;
	}
	

	
	@de.fu.weave.xml.annotation.XmlElement("privilege")
	@de.fu.weave.xml.annotation.XmlDependency("isPrivilegeFetched")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "privilege", use = "object", ref = Privilege.class)
	public Privilege getPrivilege() throws de.fu.bakery.orm.java.DatabaseException {
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("target")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "target", serial = true)
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
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("becauseOfRole")
	@de.fu.bakery.orm.java.annotation.Attribute(name = "because_of_role", serial = true, nullable = true)
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
	public boolean isBecauseOfRoleChanged() {
		return changed_becauseOfRole;
	}
	

	
	
	
	/**
	 * Sets the value of <code>user</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isUserChanged()} will return true. If you specify
	 * the same value as the old value isUserChanged() will return the same as it did
	 * before calling <code>setUser(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>user</code> is not nullable)
	 * @since Iteration2
	 */
	public void setUser(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("user is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_user, value._id) == 0) return;
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
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
	public void setPrivilege(Privilege value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("privilege is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(ref_privilege, value._id) == 0) return;
		changed_privilege = true;
		ref_privilege = value._id;
		fetched_privilege = true;
		_privilege = value;
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
	public void setTarget(String value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("target is not nullable, null given");
		}
		
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_target, value) == 0) return;
		changed_target = true;
		_target = value;
	}

	
	/**
	 * Sets the value of <code>becauseOfRole</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isBecauseOfRoleChanged()} will return true. If you specify
	 * the same value as the old value isBecauseOfRoleChanged() will return the same as it did
	 * before calling <code>setBecauseOfRole(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public void setBecauseOfRole(Boolean value) {
		
		if (value == null) {
			clearBecauseOfRole();
			return;
		}
		if (de.fu.bakery.orm.java.GenericRelationManager.compareValues(_becauseOfRole, value) == 0) return;
		changed_becauseOfRole = true;
		_becauseOfRole = value;
	}
	
	/**
	 * becauseOfRole may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public void clearBecauseOfRole() {
		if (_becauseOfRole == null) {
			return;
		}
		_becauseOfRole = null;
		changed_becauseOfRole = true;
	}

}
