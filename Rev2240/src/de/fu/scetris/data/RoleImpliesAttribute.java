/* RoleImpliesAttribute.java / 2011-03-03+01:00
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
 * OO-Representation of the relationship-relation RoleImpliesAttribute
 * <p>
 * 
			This relation keeps track of user-defined Attributes that are implied by a role.
		
 * @since Iteration2
 */
@de.fu.weave.orm.annotation.Relationship(name = "roleImpliesAttribute", subject = Role.class, object = Attribute.class)
@de.fu.weave.orm.annotation.Relation(name = "\"scetris\".\"roleImpliesAttribute\"", requiredSqlCols = {})
@de.fu.weave.xml.annotation.XmlElement("roleImpliesAttribute")
public class RoleImpliesAttribute extends de.fu.weave.orm.GenericRelationship {

	final RelationManager manager;
	boolean exists = false;

	public static final String Role = "role";
	public static final String Attribute = "attribute";
	public static final String Timekey = "timekey";
	public static final String Default = "default";
	public static final String Required = "required";
	

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
	Attribute _attribute;
	boolean changed_attribute = false;
	boolean fetched_attribute = false;
	int ref_attribute;
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
	 * default
	 * <p>
	 * 
				Describes whether or not this attribute is set by default.
			
	 * @since Iteration2
	 */
	String _default = null;
	boolean changed_default = false;
	boolean fetched_default = false;
	
	/**
	 * required
	 * <p>
	 * 
				Whether or not this Attribute is required or not.
			
	 * @since Iteration2
	 */
	boolean _required;
	boolean changed_required = false;
	boolean fetched_required = false;
	
	
	
	Role subject;
	int subject_refid;
	Attribute object;
	int object_refid;
	
	
	RoleImpliesAttribute(RelationManager manager, Role subject, Attribute object, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			ref_role = result.getInt("role");
			ref_attribute = result.getInt("attribute");
			_timekey = result.getTimestamp("timekey");
			fetched_timekey = true;
			_default = result.getString("default");
			if (result.wasNull()) {
				_default = null;
			}
			fetched_default = true;
			_required = result.getBoolean("required");
			fetched_required = true;	
			this.subject_refid = ref_role;
			this.object_refid = ref_attribute;
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.object = object;
		this.subject = subject;
		exists = true;
	}
	
	RoleImpliesAttribute(RelationManager manager, java.sql.ResultSet result) throws de.fu.weave.orm.DatabaseException {
		this.manager = manager;
		timekey(true);
		try {
			this.ref_role = result.getInt("\"role\"");
			this.ref_attribute = result.getInt("\"attribute\"");
			this._timekey = result.getTimestamp("\"timekey\"");
			this.fetched_timekey = true;
			this._default = result.getString("\"default\"");
			if (result.wasNull()) {
				this._default = null;
			}
			this.fetched_default = true;
			this._required = result.getBoolean("\"required\"");
			this.fetched_required = true;
			this.subject_refid = ref_role;
			this.object_refid = ref_attribute;
			this.subject = manager.getRole(this.subject_refid);
			this.object = manager.getAttribute(this.object_refid);
		} catch (java.sql.SQLException e) {
			throw new de.fu.weave.orm.DatabaseException(e);
		}
		this.exists = true;
	}
	
	RoleImpliesAttribute(RelationManager manager, Role subject, Attribute object) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this._role = this.subject = subject;
		this.ref_role = this.subject_refid = subject.id();
		this._attribute = this.object = object;
		this.ref_attribute = this.object_refid = object.id();
		timekey(true);
		
	}
	
	RoleImpliesAttribute(RelationManager manager, boolean full, Role subject, Attribute object, String _default, boolean _required) {
		if (subject == null || object == null) {
			throw new IllegalArgumentException();
		}
		this.manager = manager;
		this.subject = subject;
		this.subject_refid = subject.id();
		this.object = object;
		this.object_refid = object.id();
		timekey(true);
		
		this._default = _default;
		this.fetched_default = true;
		this._required = _required;
		this.fetched_required = true;
	}
	
	@Override
	public Role subject() throws de.fu.weave.orm.DatabaseException {
		if (subject == null) {
			subject = manager.getRole(subject_refid);
		}
		return subject;
	}
	
	@Override
	public Attribute object() throws de.fu.weave.orm.DatabaseException {
		if (object == null) {
			object = manager.getAttribute(object_refid);
		}
		return object;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (obj instanceof RoleImpliesAttribute) {
			return equals((RoleImpliesAttribute) obj);
		}
		return false;
	}
	
	public boolean equals(RoleImpliesAttribute relationship) {
		try {
			return relationship.subject().equals(subject())
				&& relationship.object().equals(object());
		} catch (de.fu.weave.orm.DatabaseException e) {
			return false;
		}
	}
	
	@Override
	public RoleImpliesAttribute create() throws de.fu.weave.orm.DatabaseException {
		String query = "INSERT INTO " + "\"scetris\".\"roleImpliesAttribute\"" + " (\"role\", \"attribute\","
			+ "\"default\","
			+ "\"required\", \"timekey\")"
			+ " VALUES (?, ?, ?, ?, ?);";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, subject().id());
			stmt.setInt(i++, object().id());
			if (_default != null) {
				stmt.setString(i++, _default);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
				
			}
			
			if (manager.isNull(_required)) {
				stmt.setBoolean(i++, _required = false);
			} else {
				stmt.setBoolean(i++, _required);
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
		String query = "UPDATE " + "\"scetris\".\"roleImpliesAttribute\"" + " SET \"default\" = ?, "
			+ "\"required\" = ?, "
			+ ""
			+ "\"timekey\" = ? WHERE "
			+ "\"timekey\" = ?"
			+ " AND \"role\" = ?"
			+ " AND \"attribute\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			if (_default != null) {
				stmt.setString(i++, _default);
			} else {
				stmt.setNull(i++, java.sql.Types.VARCHAR);
			}
				stmt.setBoolean(i++, _required);
			java.sql.Timestamp currentTimekey = _timekey;
			java.sql.Timestamp newTimekey = new java.sql.Timestamp(System.currentTimeMillis());
			stmt.setTimestamp(i++, newTimekey);
			stmt.setTimestamp(i++, currentTimekey);
			stmt.setInt(i++, ref_role);
			stmt.setInt(i++, ref_attribute);
			int rowsUpdated = stmt.executeUpdate();
			if (rowsUpdated == 0) {
				throw new de.fu.weave.orm.OutdatedRecordException("\"scetris\".\"roleImpliesAttribute\"" + '#' + id());
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
		String query = "DELETE FROM " + "\"scetris\".\"roleImpliesAttribute\""
			+ " WHERE "
			+ " \"role\" = ? AND "
			+ " \"attribute\" = ?";
		try {
			java.sql.PreparedStatement stmt = manager.connectionManager().getConnection().prepareStatement(query);
			int i = 1;
			stmt.setInt(i++, ref_role);
			stmt.setInt(i++, ref_attribute);
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
	

	
	@de.fu.weave.xml.annotation.XmlElement("attribute")
	@de.fu.weave.xml.annotation.XmlDependency("isAttributeFetched")
	@de.fu.weave.orm.annotation.Attribute(name = "attribute", use = "object", ref = Attribute.class)
	public Attribute getAttribute() throws de.fu.weave.orm.DatabaseException {
		if (!fetched_attribute) {
			_attribute = manager.getAttribute(ref_attribute);
			fetched_attribute = true;
		}
		return _attribute;
	}
	
	/**
	 *
	 * @return true if the resource has already been fetched from the database
	 */
	public boolean isAttributeFetched() {
		return fetched_attribute;
	}
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isAttributeChanged() {
		return changed_attribute;
	}
	
	@de.fu.weave.xml.annotation.XmlAttribute("attribute")
	public int refAttribute() {
		return ref_attribute;
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

	
	@de.fu.weave.xml.annotation.XmlAttribute("default")
	@de.fu.weave.orm.annotation.Attribute(name = "default", serial = true, nullable = true)
	public String getDefault() {
		if (_default == null) {
			return null;
		}
		return _default;
	}
	
	public boolean hasDefault() {
		return _default != null;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isDefaultChanged() {
		return changed_default;
	}
	

	
	@de.fu.weave.xml.annotation.XmlAttribute("required")
	@de.fu.weave.orm.annotation.Attribute(name = "required", serial = true)
	public boolean getRequired() {
		return _required;
	}
	
	
	
	/**
	 *
	 * @return true if the resource has been changed since the last commit
	 */
	public boolean isRequiredChanged() {
		return changed_required;
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
	public RoleImpliesAttribute setRole(Role value) {
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
	 * Sets the value of <code>attribute</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isAttributeChanged()} will return true. If you specify
	 * the same value as the old value isAttributeChanged() will return the same as it did
	 * before calling <code>setAttribute(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>attribute</code> is not nullable)
	 * @since Iteration2
	 */
	public RoleImpliesAttribute setAttribute(Attribute value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("attribute is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(ref_attribute, value._id) == 0) return this;
		changed_attribute = true;
		ref_attribute = value._id;
		fetched_attribute = true;
		_attribute = value;
		return this;
	}
@Override
	protected java.sql.Timestamp timekey(boolean update) {
		_timekey = new java.sql.Timestamp(System.currentTimeMillis());
		return _timekey;
	}
	
	

	
	/**
	 * Sets the value of <code>default</code>. You may specify null as value.
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isDefaultChanged()} will return true. If you specify
	 * the same value as the old value isDefaultChanged() will return the same as it did
	 * before calling <code>setDefault(...)</code>.
	 * 
	 * @since Iteration2
	 */
	public RoleImpliesAttribute setDefault(String value) {
		
		if (value == null) {
			clearDefault();
			return this;
		}
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_default, value) == 0) return this;
		changed_default = true;
		_default = value;
		return this;
	}
	
	/**
	 * default may be null, use this function to clear its value.
	 * @since Iteration3
	 */
	public RoleImpliesAttribute clearDefault() {
		if (_default == null) {
			return this;
		}
		_default = null;
		changed_default = true;
		return this;
	}

	
	/**
	 * Sets the value of <code>required</code>. 
	 *
	 * If the new value is the same as the old value, this method has no effect, i.e. if you
	 * specify a new value, than {@see isRequiredChanged()} will return true. If you specify
	 * the same value as the old value isRequiredChanged() will return the same as it did
	 * before calling <code>setRequired(...)</code>.
	 * 
	 * @throws IllegalArgumentException if the value is <code>null</code> (since <code>required</code> is not nullable)
	 * @since Iteration2
	 */
	public RoleImpliesAttribute setRequired(boolean value) {
		if (manager.isNull(value)) {
			throw new java.lang.IllegalArgumentException("required is not nullable, null given");
		}
		
		if (de.fu.weave.orm.GenericRelationManager.compareValues(_required, value) == 0) return this;
		changed_required = true;
		_required = value;
		return this;
	}

}
