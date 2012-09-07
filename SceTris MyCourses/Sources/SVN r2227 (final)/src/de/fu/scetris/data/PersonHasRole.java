/* PersonHasRole.java / 2011-03-03+01:00
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
 * OO-Representation of the relationship-relation PersonHasRole
 * <p>
 * 
			This relation tells us that the given Person has the given Role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "personHasRole", subject = Person.class, object = Role.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"personHasRole\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("personHasRole")
public class PersonHasRole extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String User = "user";
	public static final String Role = "role";
	public static final String Ctime = "ctime";
	public static final String Mtime = "mtime";
	public static final String CreatedBy = "created_by";
	public static final String ModifiedBy = "modified_by";
	

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
		timekey(true);
		try {
			ref_user = result.getInt("user");
			ref_role = result.getInt("role");
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
			this.subject_refid = ref_user;
			this.object_refid = ref_role;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	PersonHasRole(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_user = result.getInt("\"user\"");
			this.ref_role = result.getInt("\"role\"");
			this._ctime = result.getTimestamp("\"ctime\"");
			this.fetched_ctime = true;
			this._mtime = result.getTimestamp("\"mtime\"");
			this.fetched_mtime = true;
			this.ref_createdBy = result.getInt("\"created_by\"");
			if (result.wasNull()) {
				this.ref_createdBy = null;
			}
			this.ref_modifiedBy = result.getInt("\"modified_by\"");
			if (result.wasNull()) {
				this.ref_modifiedBy = null;
			}
			this.subject_refid = ref_user;
			this.object_refid = ref_role;
			this.subject = manager.getPerson(this.subject_refid);
			this.object = manager.getRole(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	PersonHasRole(RelationManager manager, Person subject, Role object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._user = this.subject = subject;
		this.ref_user = this.subject_refid = subject.id();
		this._role = this.object = object;
		this.ref_role = this.object_refid = object.id();
		timekey(true);
		
	}
	
	PersonHasRole(RelationManager manager, boolean full, Person subject, Role object, java.sql.Timestamp _ctime, Person _createdBy, Person _modifiedBy) {
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
	}
	
	@Override
	public Person subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getPerson(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Role object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getRole(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof PersonHasRole) {
			return equals((PersonHasRole) obj);
		}
		return false;
	}
	
	public boolean equals(PersonHasRole relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public PersonHasRole create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"personHasRole\"" + " (\"user\", \"role\","
			+ "\"ctime\","
			+ "\"created_by\","
			+ "\"modified_by\", \"mtime\")"
			+ " VALUES (?, ?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
		String query = "UPDATE " + "\"scetris\".\"personHasRole\"" + " SET \"ctime\" = ?, "
			+ "\"created_by\" = ?, "
			+ "\"modified_by\" = ?, "
			+ ""
			+ "\"mtime\" = ? WHERE "
			+ "\"mtime\" = ?"
			+ " AND \"user\" = ?"
			+ " AND \"role\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
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
			java.sql.Timestamp currentTimekey = _mtime;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_role);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"personHasRole\"" + '#' + id());
			}
			_mtime = newTimekey;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
	}
	
	public void pushChanges(java.sql.Timestamp timekey) throws de.fu.weave.orm.DatabaseException {
		_mtime = timekey;
		pushChanges();
	}
	
	@Override
	public void delete() throws de.fu.weave.orm.DatabaseException {
		String query = "DELETE FROM " + "\"scetris\".\"personHasRole\""
			+ " WHERE "
			+ " \"user\" = ? AND "
			+ " \"role\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_user);
			stmt.setInt(i++, ref_role);
			stmt.execute();
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(query, e);
		}
		exists = false;
	}
	
	
	
	@de.fu.weave.xml.annotation.XmlElement("user")
	@de.fu.weave.xml.annotation.XmlDependency("isUserFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "user", use = "subject", ref = Person.class)
	public Person getUser() throws de.fu.weave.orm.DatabaseException {
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("role")
	@de.fu.weave.xml.annotation.XmlDependency("isRoleFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "role", use = "object", ref = Role.class)
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
	public PersonHasRole setUser(Person value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("user is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_user, value._id) == 0) return this;
		changed_user = true;
		ref_user = value._id;
		fetched_user = true;
		_user = value;
		return this;
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
	public PersonHasRole setRole(Role value) {
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
	public PersonHasRole setCtime(java.sql.Timestamp value) {
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
	public PersonHasRole setCreatedBy(Person value) {
		
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
	public PersonHasRole clearCreatedBy() {
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
	public PersonHasRole setModifiedBy(Person value) {
		
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
	public PersonHasRole clearModifiedBy() {
		if (_modifiedBy == null && ref_modifiedBy == null) {
			return this;
		}
		_modifiedBy = null;
		ref_modifiedBy = null;
		changed_modifiedBy = true;
		return this;
	}

}
